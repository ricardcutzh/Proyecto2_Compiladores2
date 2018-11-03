/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Sentencias;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import ErrorManager.TError;
import Simbolos.Ambito;

/**
 *
 * @author richard
 */
public class Retornar extends NodoAST {

    Expresion exp;

    /**
     * Nodo que traduce e return
     *
     * @param linea
     * @param columna
     * @param Archivo
     * @param exp
     */
    public Retornar(int linea, int columna, String Archivo, Expresion exp) {
        super(linea, columna, Archivo);
        this.exp = exp;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try {
            if (exp == null) {
                // NO GENERO NADA
                if (InfoEstatica.Estatico.tipoFuncion.equals("VACIO")) {
                    String cad = "\n/**************************************************************************/\n";
                    cad += "// RETORNO SIN VALOR\n";
                    cad += "\nBR $e_retornar // SALTO A LA ETIQUETA DE RETURN\n";
                    cad += "/**************************************************************************/\n";
                    // DETENIENDO DEBUG
                    if (InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE) {
                        if (InfoEstatica.Estatico.esLinea) {
                            InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                            InfoEstatica.Estatico.suspended = true;
                            InfoEstatica.Estatico.OutPutCode.setText(cad);
                            InfoEstatica.Estatico.hilo.suspend();
                        } else {
                            String key1 = super.getLinea() + "_" + super.getArchivo();
                            if (InfoEstatica.Estatico.breakPoints.containsKey(key1)) {
                                InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                                InfoEstatica.Estatico.suspended = true;
                                InfoEstatica.Estatico.OutPutCode.setText(cad);
                                InfoEstatica.Estatico.hilo.suspend();
                            }
                        }
                    }
                    return cad;
                } else {
                    InfoEstatica.Estatico.agregarError(new TError("Sin valor de retorno", "Se espera devolver valor de tipo: " + InfoEstatica.Estatico.tipoFuncion,
                            "Semantico", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
                }
            } else {
                String expCode = (String) ((NodoAST) exp).generateByteCode(ambito); // GENERO EL CODIGO DE LA EXPRESION
                String tipoEsperado = exp.getTipo(ambito);
                if (InfoEstatica.Estatico.tipoFuncion.equals(tipoEsperado)) {
                    String cad = "\n/**************************************************************************/\n";
                    cad += "// RETORNO CON VALOR\n";
                    cad += expCode + "\n";
                    cad += "set_local $ret // PONGO EL RETORNO EN LA POSICION DE RET\n";
                    cad += "\nBR $e_retornar // SALTO A LA ETIQUETA DE RETORNAR\n";
                    cad += "/**************************************************************************/\n";
                    // DETENIENDO DEBUG
                    if (InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE) {
                        if (InfoEstatica.Estatico.esLinea) {
                            InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                            InfoEstatica.Estatico.suspended = true;
                            InfoEstatica.Estatico.OutPutCode.setText(cad);
                            InfoEstatica.Estatico.hilo.suspend();
                        } else {
                            String key1 = super.getLinea() + "_" + super.getArchivo();
                            if (InfoEstatica.Estatico.breakPoints.containsKey(key1)) {
                                InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                                InfoEstatica.Estatico.suspended = true;
                                InfoEstatica.Estatico.OutPutCode.setText(cad);
                                InfoEstatica.Estatico.hilo.suspend();
                            }
                        }
                    }
                    return cad;
                } else {
                    InfoEstatica.Estatico.agregarError(new TError("NO coincide valor de retorno", "Se espera devolver valor de tipo: " + InfoEstatica.Estatico.tipoFuncion,
                            "Semantico", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

}
