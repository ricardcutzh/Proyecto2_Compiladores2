/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Sentencias;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import ErrorManager.TError;
import ObjsComun.NodoMiembro;
import Simbolos.Ambito;
import Simbolos.DppVar;
import Simbolos.Simbolo;
import Simbolos.Struct;

/**
 *
 * @author richard
 */
public class AsignacionMiembroSimple extends NodoAST {

    String id;
    String id2;
    Expresion exp;

    public AsignacionMiembroSimple(int linea, int columna, String Archivo, String id1, String id2, Expresion exp) {
        super(linea, columna, Archivo);
        this.id = id1;
        this.id2 = id2;
        this.exp = exp;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try {
            if (ambito.existeVariable(id)) {
                Simbolo ss = ambito.getSimbolo(id);
                if (ss instanceof DppVar) {
                    DppVar simbolo = (DppVar) ss;
                    Ambito aux = getGlobal(ambito);
                    Struct s = aux.getStruct(simbolo.getTipo()); // OBTENGO EL STRUCT QUE SE ESPECIFICO DEL TIPO
                    if (s == null) {
                        InfoEstatica.Estatico.agregarError(new TError(id + " de tipo: " + simbolo.getTipo(),
                                "No es una estructura que exista o este definida",
                                "Semantico", super.getLinea(), super.getColumna(),
                                Boolean.FALSE, super.getArchivo()));
                        return "";
                    }
                    if (s.existeMiembro(id2)) {
                        NodoMiembro m = s.getByID(id2);
                        String expCode = (String) ((NodoAST) exp).generateByteCode(ambito);// GENERO EL CODIGO RELACIONADO A LA EXPRESION
                        String tipoObtenido = exp.getTipo(ambito);
                        if (tipoObtenido.equals(m.getTipo()) && !m.isEsArreglo()) {
                            String cad = "// ASIGNACION DE MIEMBRO DE ESTRUCTURA: " + id + " de TIPO: " + m.getTipo() + "\n";
                            if (simbolo.getAmbito().equals("Global")) {
                                cad += simbolo.getPosicionRelativa() + "\n"; // YA QUE ES GLOBAL
                            } else {
                                cad += "get_local 0\n";// AQUI VERIFICAR DE DONDE ESTOY LLAMANDO LA ESTRUCTURA PORQUE SI ES GLOBAL DEBE SER DESDE OTRO PUNTO
                                cad += simbolo.getPosicionRelativa() + "\n";
                                cad += "ADD\n";
                            }
                            cad += "get_local $calc // POSICION DONDE ESTA EL PUNTERO A LA ESTRUCTURA EN HEAP\n";
                            cad += "// POSICION RELATIVA EN EL HEAP\n";
                            cad += (m.getIndiceMiembro() - 1) + "\n";
                            cad += "ADD // ENCONTRANDO POSICION ABSOLUTA EN EL HEAP\n";
                            cad += expCode + "\n";
                            cad += "set_global $calc //ASIGNA LA POSICION EN EL HEAP\n";
                            // AQUI ESTA EL STOP DEL DEBUG
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
                        } else if (tipoObtenido.equals(m.getTipo()) && (m.isEsArreglo())) {
                            if (m.getDimensiones().size() == InfoEstatica.Estatico.dims) {
                                String cad = "// ASIGNACION DE MIEMBRO DE ESTRUCTURA: " + id + " de TIPO: " + m.getTipo() + "\n";
                                if (simbolo.getAmbito().equals("Global")) {
                                    cad += simbolo.getPosicionRelativa() + "\n"; // YA QUE ES GLOBAL
                                } else {
                                    cad += "get_local 0\n";// AQUI VERIFICAR DE DONDE ESTOY LLAMANDO LA ESTRUCTURA PORQUE SI ES GLOBAL DEBE SER DESDE OTRO PUNTO
                                    cad += simbolo.getPosicionRelativa() + "\n";
                                    cad += "ADD\n";
                                }
                                cad += "get_local $calc // POSICION DONDE ESTA EL PUNTERO A LA ESTRUCTURA EN HEAP\n";
                                cad += "// POSICION RELATIVA EN EL HEAP\n";
                                cad += (m.getIndiceMiembro() - 1) + "\n";
                                cad += "ADD // ENCONTRANDO POSICION ABSOLUTA EN EL HEAP\n";
                                cad += expCode + "\n";
                                cad += "set_global $calc //ASIGNA LA POSICION EN EL HEAP\n";
                                InfoEstatica.Estatico.dims = 0;
                                InfoEstatica.Estatico.valorArreglo = false;
                                // AQUI ESTA EL STOP DEL DEBUG
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
                                InfoEstatica.Estatico.agregarError(new TError(
                                        m.getDimensiones().size() + " = " + InfoEstatica.Estatico.dims,
                                        "Asignacion de Tipos Invalidos ya que dimensiones no concuerdan",
                                        "Semantico",
                                        super.getLinea(),
                                        super.getColumna(),
                                        Boolean.FALSE,
                                        super.getArchivo()));
                            }
                        } else {
                            InfoEstatica.Estatico.agregarError(new TError(
                                    m.getTipo() + "=" + tipoObtenido,
                                    "Asignacion de Tipos Invalidos",
                                    "Semantico",
                                    super.getLinea(),
                                    super.getColumna(),
                                    Boolean.FALSE,
                                    super.getArchivo()));
                        }
                    } else {
                        InfoEstatica.Estatico.agregarError(new TError(
                                id2,
                                "La estructura de tipo: " + simbolo.getTipo() + " no cuenta con un miembro: " + id2,
                                "Semantico",
                                super.getLinea(),
                                super.getColumna(),
                                Boolean.FALSE,
                                super.getArchivo()));
                    }
                } else {
                    //ES LA INSTANCIA DE UN ARREGLO
                    InfoEstatica.Estatico.agregarError(new TError(id, "El simbolo especificado no es una estructura",
                            "Semantico",
                            super.getLinea(), super.getColumna(),
                            Boolean.FALSE, super.getArchivo()));
                }
            } else {
                InfoEstatica.Estatico.agregarError(new TError(
                        id, "Simbolo especificado no existe en el Ambito",
                        "Semantico",
                        super.getLinea(),
                        super.getColumna(),
                        Boolean.FALSE,
                        super.getArchivo()));
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError(
                    "No Aplica",
                    "Error al traducir Asignacion a Miembro: " + e.getMessage(),
                    "Ejecucion",
                    super.getLinea(),
                    super.getColumna(),
                    Boolean.FALSE,
                    super.getArchivo()));
        }
        return "";
    }

    private Ambito getGlobal(Ambito actual) {
        Ambito aux = actual;
        while (aux.getAnterior() != null) {
            aux = aux.getAnterior();
        }
        return aux;
    }
}
