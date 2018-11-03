/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Sentencias.For;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import DppPackage.DppAST.Declaraciones.DeclaracionVar;
import DppPackage.DppAST.Sentencias.AsignacionVar;
import ErrorManager.TError;
import Simbolos.Ambito;
import java.util.ArrayList;

/**
 *
 * @author richard
 */
public class Para extends NodoAST {

    NodoAST inicializacion;
    NodoAST actualizacion;
    Expresion condicion;
    ArrayList<Object> sentencias;

    public Para(int linea, int columna, String Archivo, NodoAST inicializacion, Expresion condicion, NodoAST actualizacion, ArrayList<Object> sentencias) {
        super(linea, columna, Archivo);
        this.inicializacion = inicializacion;
        this.actualizacion = actualizacion;
        this.condicion = condicion;
        this.sentencias = sentencias;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        InfoEstatica.Estatico.display.PushToDisplay("FOR", Boolean.TRUE);
        try {
            Ambito ambitoFor = new Ambito("Local | FOR", ambito, super.getArchivo());
            ambito.tomarDatosParaVariables(ambitoFor);// TOMA LOS VALORES DEL FOR

            String cad = "\n/**************************************************************************/\n";
            cad += "// FOR....\n";
            cad += "// INICIALIZACION: \n";
            cad += escribeInicializacion(ambito, ambitoFor) + "\n";
            cad += "// FIN INICIALIZACION \n";
            ////////////////////////////////////////////////////////////////////
            String expCode = (String) ((NodoAST) condicion).generateByteCode(ambitoFor);
            String tipo = condicion.getTipo(ambito);

            if (!tipo.equals("BOOLEAN"))// SI LA CONDICION NO SE CUMPLE..
            {
                InfoEstatica.Estatico.agregarError(
                        new TError(tipo, "La sentencia For requiere una condicion BOOLEANA", "Semantico",
                                 super.getLinea(),
                                 super.getColumna(),
                                 Boolean.FALSE,
                                 super.getArchivo())
                );
                InfoEstatica.Estatico.display.PopFromDisplay();
                return "";
            }
            ////////////////////////////////////////////////////////////////////

            cad += "// CONDICION: \n";
            cad += "$" + InfoEstatica.Estatico.display.Peek().toString() + "_CONDICION\n";
            cad += expCode + "\n";
            cad += "// FIN CONDICION\n";

            cad += "BR_IF $" + InfoEstatica.Estatico.display.Peek() + "_FALSO\n";

            ////////////////////////////////////////////////////////////////////
            String auxiliar = "";
            for (Object o : this.sentencias) {
                auxiliar += (String) ((NodoAST) o).generateByteCode(ambitoFor);
            }

            auxiliar += "\n// ACTUALIZACION DE VARIABLE...\n";
            auxiliar += "$" + InfoEstatica.Estatico.display.Peek().toString() + "_CAMBIO //ETIQUETA DE CAMBIO\n";
            auxiliar += actualizacion.generateByteCode(ambitoFor);
            auxiliar += "\n// FIN ACTUALIZACION\n";
            auxiliar += "BR $" + InfoEstatica.Estatico.display.Peek().toString() + "_CONDICION // SALTO A LA CONDICION...\n";

            InfoEstatica.Estatico.tabula();
            cad += InfoEstatica.Estatico.aplicaTabulaciones(auxiliar);
            InfoEstatica.Estatico.destabula();
            ////////////////////////////////////////////////////////////////////

            cad += "$" + InfoEstatica.Estatico.display.Peek().toString() + "_FALSO // SALIDA WHILE\n";
            InfoEstatica.Estatico.display.PopFromDisplay();
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
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(
                    new TError("No Aplica", "Error al traducir For: " + e.getMessage(),
                             "Ejecucion",
                             super.getLinea(),
                             super.getColumna(),
                             Boolean.FALSE,
                             super.getArchivo())
            );
        }
        InfoEstatica.Estatico.display.PopFromDisplay();
        return "";
    }

    private String escribeInicializacion(Ambito ambito, Ambito ambitoFor) {
        String cad = "";
        try {
            if (inicializacion instanceof DeclaracionVar) {
                cad = (String) inicializacion.generateByteCode(ambitoFor);
            } else if (inicializacion instanceof AsignacionVar) {
                cad = (String) inicializacion.generateByteCode(ambito); //
            }
            return cad;
        } catch (Exception e) {
        }
        return cad;
    }
}
