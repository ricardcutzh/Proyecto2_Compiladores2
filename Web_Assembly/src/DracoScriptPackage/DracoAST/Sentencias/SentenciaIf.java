/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Sentencias;

import Abstraccion.*;
import ErrorManager.TError;
import InfoEstatica.Estatico;
import ObjsComun.NodoELIF;
import ObjsComun.Nulo;
import ObjsComun.Romper;
import Simbolos.Ambito;
import java.util.ArrayList;

/**
 * Nodo del arbol que maneja la sentencia de control IF
 *
 * @author richard
 */
public class SentenciaIf extends NodoAST implements Instruccion {

    Expresion condicion;
    ArrayList<Instruccion> sentencias;
    ArrayList<NodoELIF> elifs;

    /**
     * Constructor de la instruccion IF
     *
     * @param linea
     * @param columna
     * @param Archivo
     * @param condicion Expresion de la condicion
     * @param sentencias Sentencias que se deben ejecutar si se cumple la
     * condicion
     * @param elifs las siguientes condiciones que se deben de evaluar de no
     * coincidir
     */
    public SentenciaIf(int linea, int columna, String Archivo, Expresion condicion, ArrayList<Instruccion> sentencias, ArrayList<NodoELIF> elifs) {
        super(linea, columna, Archivo);
        this.condicion = condicion;
        this.sentencias = sentencias;
        this.elifs = elifs;
    }

    @Override
    public Object Ejecutar(Ambito ambito) {
        try {
            Object condi = this.condicion.getValor(ambito);
            String tipo = this.condicion.getTipo(ambito);
            if (condi instanceof Boolean) {
                Boolean val = (Boolean) condi;
                // AMBITOS DEL IF
                Ambito ambitoIF = new Ambito(ambito.getIdAmbito() + "_" + "IF", ambito, ambito.getArchivo());
                ambitoIF.tomaValoresDeAmbito(ambito);
                if (val) // SI CUMPLE LA CONDICION...
                {
                    for (Instruccion ins : this.sentencias) {
                        if (InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE) {
                            NodoAST aux = (NodoAST) ins;
                            if (InfoEstatica.Estatico.esLinea) {
                                //Estatico.MarcaLinea(aux.getLinea());
                                Estatico.MarcarLineaArchivo(ambito.getArchivo(), aux.getLinea());
                                Estatico.suspended = true;
                                Estatico.hilo.suspend();
                            } else {
                                String key = aux.getLinea() + "_" + aux.getArchivo();
                                if (Estatico.breakPoints.containsKey(key)) {
                                    Estatico.MarcaLinea(aux.getLinea());
                                    Estatico.suspended = true;
                                    Estatico.hilo.suspend();
                                }
                            }
                        }
                        Object resultado = ins.Ejecutar(ambitoIF);
                        if (resultado instanceof Romper) {
                            if (Estatico.pilaCiclos.peek()) {
                                return new Romper();
                            } else {
                                TError error = new TError("Smash", "Sentencia Smash que no esta en un ciclo", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                                Estatico.agregarError(error);
                            }
                        }
                    }
                } else if (elifs != null) // SI NO CUMPLE LA CONDICION Y ADEMAS TIENE ELIFS
                {
                    for (NodoELIF n : elifs)// PARA CADA NODO ELIF
                    {
                        if (InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE) {
                            if (InfoEstatica.Estatico.esLinea) {
                                //Estatico.MarcaLinea(n.getLinea());
                                Estatico.MarcarLineaArchivo(ambito.getArchivo(), n.getLinea());
                                Estatico.suspended = true;
                                Estatico.hilo.suspend();
                            } else {
                                String key = n.getLinea() + "_" + n.getArchivo();
                                if (Estatico.breakPoints.containsKey(key)) {
                                    //Estatico.MarcaLinea(n.getLinea());
                                    Estatico.MarcarLineaArchivo(ambito.getArchivo(), n.getLinea());
                                    Estatico.suspended = true;
                                    Estatico.hilo.suspend();
                                }
                            }
                        }
                        Expresion condAuxiliar = n.getCondicion();
                        if (condAuxiliar == null) {
                            ArrayList<Instruccion> sentenciasdefecto = n.getSentencias();
                            for (Instruccion ins : sentenciasdefecto) {
                                if (InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE) {
                                    NodoAST aux = (NodoAST) ins;
                                    if (InfoEstatica.Estatico.esLinea) {
                                        //Estatico.MarcaLinea(aux.getLinea());
                                        Estatico.MarcarLineaArchivo(ambito.getArchivo(), aux.getLinea());
                                        Estatico.suspended = true;
                                        Estatico.hilo.suspend();
                                    } else {
                                        String key = aux.getLinea() + "_" + aux.getArchivo();
                                        if (Estatico.breakPoints.containsKey(key)) {
                                            //Estatico.MarcaLinea(aux.getLinea());
                                            Estatico.MarcarLineaArchivo(ambito.getArchivo(), aux.getLinea());
                                            Estatico.suspended = true;
                                            Estatico.hilo.suspend();
                                        }
                                    }
                                }
                                Object resultado = ins.Ejecutar(ambitoIF); //OJO CON EL SMASH
                                if (resultado instanceof Romper) {
                                    if (Estatico.pilaCiclos.peek()) {
                                        return new Romper();
                                    } else {
                                        TError error = new TError("Smash", "Sentencia Smash que no esta en un ciclo", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                                        Estatico.agregarError(error);
                                    }
                                }
                            }
                            break; // TERMINA!
                        } else {
                            Object condiaux = condAuxiliar.getValor(ambito);
                            if (condiaux instanceof Boolean) {
                                Boolean valor = (Boolean) condiaux;
                                if (valor) {
                                    ArrayList<Instruccion> instruccElif = n.getSentencias();
                                    for (Instruccion in : instruccElif) {
                                        if (InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE) {
                                            NodoAST aux = (NodoAST) in;
                                            if (InfoEstatica.Estatico.esLinea) {
                                                //Estatico.MarcaLinea(aux.getLinea());
                                                Estatico.MarcarLineaArchivo(ambito.getArchivo(), aux.getLinea());
                                                Estatico.suspended = true;
                                                Estatico.hilo.suspend();
                                            } else {
                                                String key = aux.getLinea() + "_" + aux.getArchivo();
                                                if (Estatico.breakPoints.containsKey(key)) {
                                                    //Estatico.MarcaLinea(aux.getLinea());
                                                    Estatico.MarcarLineaArchivo(ambito.getArchivo(), aux.getLinea());
                                                    Estatico.suspended = true;
                                                    Estatico.hilo.suspend();
                                                }
                                            }
                                        }
                                        Object resultado = in.Ejecutar(ambitoIF); // OJO CON EL SMASH
                                        if (resultado instanceof Romper) {
                                            if (Estatico.pilaCiclos.peek()) {
                                                return new Romper();
                                            } else {
                                                TError error = new TError("Smash", "Sentencia Smash que no esta en un ciclo", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                                                Estatico.agregarError(error);
                                            }
                                        }
                                    }
                                    break; //TERMINA!
                                }
                            } else {
                                InfoEstatica.Estatico.agregarError(new TError("Tipo de Expresion: " + condAuxiliar.getTipo(ambito), "Para la sentencia ELIF, se requiere una condicion (Boolean)", "Semantico", n.getLinea(), n.getCol(), false, ambito.getArchivo()));
                            }
                        }
                    }
                }
                // SINO ... TERMINA
            } else {
                InfoEstatica.Estatico.agregarError(new TError("Tipo de Expresion: " + tipo, "Para la sentencia IF, se requiere una condicion (Boolean)", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
            }
        } catch (Exception e) {
            TError error = new TError("No Aplica", "Error al ejecutar if: " + e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            InfoEstatica.Estatico.agregarError(error);
        }
        return new Nulo();
    }

}
