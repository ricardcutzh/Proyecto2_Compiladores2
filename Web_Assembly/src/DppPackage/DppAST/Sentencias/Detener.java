/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Sentencias;

import Abstraccion.NodoAST;
import ErrorManager.TError;
import Estructuras.NodoDisplay;
import Simbolos.Ambito;

/**
 *
 * @author richard
 */
public class Detener extends NodoAST {

    public Detener(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try {
            if (!InfoEstatica.Estatico.display.estVacio()) {
                NodoDisplay n = InfoEstatica.Estatico.display.getLastCicle();
                if (n.isEsCiclo()) {
                    String cad = "// BREAK... \n";
                    cad += "BR $" + n.toString() + "_FALSO // SALIDA....\n";
                    // DETENIENDO DEBUG
                    if (InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE) {
                        if (InfoEstatica.Estatico.esLinea) {
                            InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                            InfoEstatica.Estatico.suspended = true;
                            InfoEstatica.Estatico.OutPutCode.setText(cad);
                            InfoEstatica.Estatico.hilo.suspend();
                        } else {
                            String key = super.getLinea() + "_" + super.getArchivo();
                            if (InfoEstatica.Estatico.breakPoints.containsKey(key)) {
                                InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                                InfoEstatica.Estatico.suspended = true;
                                InfoEstatica.Estatico.OutPutCode.setText(cad);
                                InfoEstatica.Estatico.hilo.suspend();
                            }
                        }
                    }
                    return cad;
                } else {
                    InfoEstatica.Estatico.agregarError(new TError("Detener",
                             "Sentencia Detener no viene dentro de un Ciclo", "Semantico",
                             super.getLinea(),
                             super.getColumna(),
                             Boolean.FALSE,
                             super.getArchivo()));
                }
            } else {
                InfoEstatica.Estatico.agregarError(new TError("Detener", "Sentencia Detener no viene dentro de un Ciclo",
                         "Semantico", super.getLinea(), super.getColumna(),
                         Boolean.FALSE, super.getArchivo()));
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(
                    new TError(
                            "No Aplica",
                             "Error al traducir el Detener: " + e.getMessage(),
                             "Ejecucion",
                             super.getLinea(),
                             super.getColumna(),
                             Boolean.FALSE,
                             super.getArchivo()
                    )
            );
        }
        return "";
    }

}
