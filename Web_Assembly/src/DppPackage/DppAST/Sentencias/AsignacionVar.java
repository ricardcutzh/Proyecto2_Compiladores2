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
import Simbolos.DppArr;
import Simbolos.DppVar;
import Simbolos.Simbolo;

/**
 * Clase que maneja la traduccion de una asignacion de variables
 *
 * @author richard
 */
public class AsignacionVar extends NodoAST {

    String id;
    Expresion exp;

    /**
     * Constructor de la asignacion de Var
     *
     * @param linea
     * @param columna
     * @param Archivo
     * @param exp expresion a traducir
     * @param id identificador donde se asignara
     */
    public AsignacionVar(int linea, int columna, String Archivo, Expresion exp, String id) {
        super(linea, columna, Archivo);
        this.id = id;
        this.exp = exp;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try {
            if (ambito.existeVariable(id)) {
                Simbolo s = (Simbolo) ambito.getSimbolo(id);
                if (s instanceof DppVar) {
                    DppVar simbolo = (DppVar) s;
                    String expCode = (String) ((NodoAST) exp).generateByteCode(ambito);// GENERO EL CODIGO RELACIONADO A LA EXPRESION
                    String tipoObtenido = exp.getTipo(ambito);
                    switch (simbolo.getTipo()) {
                        case "CADENA": {
                            if (tipoObtenido.equals(simbolo.getTipo()) || tipoObtenido.equals("NULO")) //PARA QUE VALGA ASIGNACION NULA
                            {
                                String cad = "\n/**************************************************************************/\n";
                                if (simbolo.getAmbito().equals("Global")) {
                                    cad += "// " + id + " = " + tipoObtenido + "\n";
                                    cad += simbolo.getPosicionRelativa() + "// SERA LA POSICION ABSOLUTA DONDE SE ENCONTRARA SIN PUNTERO\n";
                                    cad += expCode + "\n";
                                    cad += "set_local $calc // LA POSICIONA AL INICIO DEL STACK \n";
                                    cad += "/**************************************************************************/\n";
                                } else {
                                    cad += "// " + id + " = PUNTERO A CADENA EN EL HEAP \n";
                                    cad += "get_local 0 // OBTENIENDO EL PUNTERO DE LA STACK\n";
                                    cad += simbolo.getPosicionRelativa() + " // POSICION RELATIVA DE LA VARIABLE A ASIGNAR\n";
                                    cad += "ADD // ENCONTRANDO LA POSICION Y METIENDOLO AL FONDO DEL STACK\n";
                                    cad += expCode + "\n";
                                    cad += "set_local $calc // COLOCANDO EL VALOR EN LA POSICION AL FONDO DE LA PILA\n";
                                    cad += "/**************************************************************************/\n";
                                }
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
                                InfoEstatica.Estatico.agregarError(new TError(
                                        simbolo.getTipo() + "=" + tipoObtenido,
                                        "Los tipos que se pretende asignar no son compatibles",
                                        "Semantico",
                                        super.getLinea(),
                                        super.getColumna(),
                                        Boolean.FALSE,
                                        super.getArchivo()
                                ));
                            }
                        }
                        case "ENTERO": {
                            if (tipoObtenido.equals(simbolo.getTipo())) {
                                String cad = "\n/**************************************************************************/\n";
                                if (simbolo.getAmbito().equals("Global")) {
                                    cad += "// " + id + " = " + tipoObtenido + "\n";
                                    cad += simbolo.getPosicionRelativa() + "// SERA LA POSICION ABSOLUTA DONDE SE ENCONTRARA SIN PUNTERO\n";
                                    cad += expCode + "\n";
                                    cad += "set_local $calc // LA POSICIONA AL INICIO DEL STACK \n";
                                    cad += "/**************************************************************************/\n";
                                } else {
                                    cad += "// " + id + " = ENTERO \n";
                                    cad += "get_local 0 // OBTENIENDO EL PUNTERO DE LA STACK\n";
                                    cad += simbolo.getPosicionRelativa() + " // POSICION RELATIVA DE LA VARIABLE A ASIGNAR\n";
                                    cad += "ADD // ENCONTRANDO LA POSICION Y METIENDOLO AL FONDO DEL STACK\n";
                                    cad += expCode + "\n";
                                    cad += "set_local $calc // COLOCANDO EL VALOR EN LA POSICION AL FONDO DE LA PILA\n";
                                    cad += "/**************************************************************************/\n";
                                }
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
                            } else if (tipoObtenido.equals("DECIMAL")) {
                                String cad = "\n/**************************************************************************/\n";
                                if (simbolo.getAmbito().equals("Global")) {
                                    cad += "// " + id + " = ENTERO (CASTEANDO)\n";
                                    cad += simbolo.getPosicionRelativa() + "// SERA LA POSICION ABSOLUTA DONDE SE ENCONTRARA SIN PUNTERO\n";
                                    cad += "// CASTEANDO LA EXPRESION A ENTERO\n";
                                    cad += "// MANDANDO COMO PARAMETRO A LA FUNCION DE CASTEO\n";
                                    cad += "get_local 0\n";
                                    cad += (ambito.getSize() - 1) + "\n";
                                    cad += "ADD\n";
                                    cad += "1\n";
                                    cad += "ADD\n";
                                    cad += expCode + "\n";
                                    cad += "// LLAMANDO A LA FUNCION DE CASTEO\n";
                                    cad += "get_local 0\n";
                                    cad += (ambito.getSize() - 1) + "\n";
                                    cad += "ADD\n";
                                    cad += "set_local 0\n";
                                    cad += "Call $_CAST_TO_INT\n";
                                    cad += "get_local 0\n";
                                    cad += (ambito.getSize() - 1) + "\n";
                                    cad += "DIFF\n";
                                    cad += "set_local 0\n";
                                    cad += "// OBTENIENDO EL RETORNO\n";
                                    cad += "get_local $ret\n";
                                    cad += "//ASIGNANDO VALOR A LA POSICION\n";
                                    cad += "set_local $calc";
                                    cad += "\n/**************************************************************************/\n";
                                } else {
                                    cad += "// " + id + " = ENTERO \n";
                                    cad += "get_local 0 // OBTENIENDO EL PUNTERO DE LA STACK\n";
                                    cad += simbolo.getPosicionRelativa() + " // POSICION RELATIVA DE LA VARIABLE A ASIGNAR\n";
                                    cad += "ADD // ENCONTRANDO LA POSICION Y METIENDOLO AL FONDO DEL STACK\n";
                                    cad += "// CASTEANDO LA EXPRESION A ENTERO\n";
                                    cad += "// MANDANDO COMO PARAMETRO A LA FUNCION DE CASTEO\n";
                                    cad += "get_local 0\n";
                                    cad += (ambito.getSize() - 1) + "\n";
                                    cad += "ADD\n";
                                    cad += "1\n";
                                    cad += "ADD\n";
                                    cad += expCode + "\n";
                                    cad += "// LLAMANDO A LA FUNCION DE CASTEO\n";
                                    cad += "get_local 0\n";
                                    cad += (ambito.getSize() - 1) + "\n";
                                    cad += "ADD\n";
                                    cad += "set_local 0\n";
                                    cad += "Call $_CAST_TO_INT\n";
                                    cad += "get_local 0\n";
                                    cad += (ambito.getSize() - 1) + "\n";
                                    cad += "DIFF\n";
                                    cad += "set_local 0\n";
                                    cad += "// OBTENIENDO EL RETORNO\n";
                                    cad += "get_local $ret\n";
                                    cad += "// ASIGNANDO VALOR A LA POSICION\n";
                                    cad += "set_local $calc";
                                    cad += "\n/**************************************************************************/\n";
                                }
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
                                InfoEstatica.Estatico.agregarError(new TError(
                                        simbolo.getTipo() + "=" + tipoObtenido,
                                        "Los tipos que se pretende asignar no son compatibles",
                                        "Semantico",
                                        super.getLinea(),
                                        super.getColumna(),
                                        Boolean.FALSE,
                                        super.getArchivo()
                                ));
                            }
                            break;
                        }
                        case "DECIMAL": {
                            if (tipoObtenido.equals(simbolo.getTipo()) || tipoObtenido.equals("ENTERO")) {
                                String cad = "\n/**************************************************************************/\n";
                                if (simbolo.getAmbito().equals("Global")) {
                                    cad += "// " + id + " = " + tipoObtenido + "\n";
                                    cad += simbolo.getPosicionRelativa() + "// SERA LA POSICION ABSOLUTA DONDE SE ENCONTRARA SIN PUNTERO\n";
                                    cad += expCode + "\n";
                                    cad += "set_local $calc // LA POSICIONA AL INICIO DEL STACK \n";
                                    cad += "/**************************************************************************/\n";
                                } else {
                                    cad += "// " + id + " = DECIMAL \n";
                                    cad += "get_local 0 // OBTENIENDO EL PUNTERO DE LA STACK\n";
                                    cad += simbolo.getPosicionRelativa() + " // POSICION RELATIVA DE LA VARIABLE A ASIGNAR\n";
                                    cad += "ADD // ENCONTRANDO LA POSICION Y METIENDOLO AL FONDO DEL STACK\n";
                                    cad += expCode + "\n";
                                    cad += "set_local $calc // COLOCANDO EL VALOR EN LA POSICION AL FONDO DE LA PILA\n";
                                    cad += "/**************************************************************************/\n";
                                }
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
                                InfoEstatica.Estatico.agregarError(new TError(simbolo.getTipo() + "=" + tipoObtenido,
                                        "Tipos que se pretende asignar no son compatibles",
                                        "Semantico",
                                        super.getLinea(),
                                        super.getColumna(),
                                        Boolean.FALSE,
                                        super.getArchivo()
                                ));
                            }
                            break;
                        }
                        case "BOOLEAN": {
                            if (tipoObtenido.equals(simbolo.getTipo())) {
                                String cad = "\n/**************************************************************************/\n";
                                if (simbolo.getAmbito().equals("Global")) {
                                    cad += "// " + id + " = " + tipoObtenido + "\n";
                                    cad += simbolo.getPosicionRelativa() + "// SERA LA POSICION ABSOLUTA DONDE SE ENCONTRARA SIN PUNTERO\n";
                                    cad += expCode + "\n";
                                    cad += "set_local $calc // LA POSICIONA AL INICIO DEL STACK \n";
                                    cad += "/**************************************************************************/\n";
                                } else {
                                    cad += "// " + id + " = BOOLEAN \n";
                                    cad += "get_local 0 // OBTENIENDO EL PUNTERO DE LA STACK\n";
                                    cad += simbolo.getPosicionRelativa() + " // POSICION RELATIVA DE LA VARIABLE A ASIGNAR\n";
                                    cad += "ADD // ENCONTRANDO LA POSICION Y METIENDOLO AL FONDO DEL STACK\n";
                                    cad += expCode + "\n";
                                    cad += "set_local $calc // COLOCANDO EL VALOR EN LA POSICION AL FONDO DE LA PILA\n";
                                    cad += "/**************************************************************************/\n";
                                }
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
                                InfoEstatica.Estatico.agregarError(new TError(
                                        simbolo.getTipo() + "=" + tipoObtenido,
                                        "Tipos que se pretende asignar no son compatibles",
                                        "Semantico",
                                        super.getLinea(),
                                        super.getColumna(),
                                        Boolean.FALSE,
                                        super.getArchivo()
                                ));
                            }
                            break;
                        }
                        case "CARACTER": {
                            if (tipoObtenido.equals(simbolo.getTipo())) {
                                String cad = "\n/**************************************************************************/\n";
                                if (simbolo.getAmbito().equals("Global")) {
                                    cad += "// " + id + " = " + tipoObtenido + "\n";
                                    cad += simbolo.getPosicionRelativa() + "// SERA LA POSICION ABSOLUTA DONDE SE ENCONTRARA SIN PUNTERO\n";
                                    cad += expCode + "\n";
                                    cad += "set_local $calc // LA POSICIONA AL INICIO DEL STACK \n";
                                    cad += "/**************************************************************************/\n";
                                } else {
                                    cad += "// " + id + " = CARACTER \n";
                                    cad += "get_local 0 // OBTENIENDO EL PUNTERO DE LA STACK\n";
                                    cad += simbolo.getPosicionRelativa() + " // POSICION RELATIVA DE LA VARIABLE A ASIGNAR\n";
                                    cad += "ADD // ENCONTRANDO LA POSICION Y METIENDOLO AL FONDO DEL STACK\n";
                                    cad += expCode + "\n";
                                    cad += "set_local $calc // COLOCANDO EL VALOR EN LA POSICION AL FONDO DE LA PILA\n";
                                    cad += "/**************************************************************************/\n";
                                }
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
                                InfoEstatica.Estatico.agregarError(new TError(
                                        simbolo.getTipo() + "=" + tipoObtenido,
                                        "Tipos que se pretende asignar no son compatibles",
                                        "Semantico",
                                        super.getLinea(),
                                        super.getColumna(),
                                        Boolean.FALSE,
                                        super.getArchivo()
                                ));
                            }
                            break;
                        }
                        default: {
                            if (tipoObtenido.equals(simbolo.getTipo()))// ESTRUCTURAS IGUALANDO
                            {
                                String cad = "\n/**************************************************************************/\n";
                                if (simbolo.getAmbito().equals("Global")) {
                                    cad += "// " + id + " = " + tipoObtenido + "\n";
                                    cad += simbolo.getPosicionRelativa() + "// SERA LA POSICION ABSOLUTA DONDE SE ENCONTRARA SIN PUNTERO\n";
                                    cad += expCode + "\n";
                                    cad += "set_local $calc // LA POSICIONA AL INICIO DEL STACK \n";
                                    cad += "/**************************************************************************/\n";
                                } else {
                                    cad += "// " + simbolo.getTipo() + " = " + tipoObtenido + "\n";
                                    cad += "get_local 0 // OBTENIENDO EL PUNTERO DE LA STACK\n";
                                    cad += simbolo.getPosicionRelativa() + " // POSICION RELATIVA DE LA VARIABLE A ASIGNAR\n";
                                    cad += "ADD // ENCONTRANDO LA POSICION Y METIENDOLO AL FONDO DEL STACK\n";
                                    cad += expCode + "\n";
                                    cad += "set_local $calc // COLOCANDO EL VALOR EN LA POSICION AL FONDO DE LA PILA\n";
                                    cad += "/**************************************************************************/\n";
                                }
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
                                InfoEstatica.Estatico.agregarError(new TError(
                                        simbolo.getTipo() + "=" + tipoObtenido,
                                        "Tipos que se pretende asignar no son compatibles",
                                        "Semantico",
                                        super.getLinea(),
                                        super.getColumna(),
                                        Boolean.FALSE,
                                        super.getArchivo()
                                ));
                            }
                        }
                    }
                } else // CUANDO SEA ARREGLOS
                {
                    DppArr simbolo = (DppArr) s;
                    String expCode = (String) ((NodoAST) exp).generateByteCode(ambito);// GENERO EL CODIGO RELACIONADO A LA EXPRESION
                    String tipoObtenido = exp.getTipo(ambito);
                    if (tipoObtenido.equals(simbolo.getTipo())) {
                        if (InfoEstatica.Estatico.dims == simbolo.getNumDimensiones()) {
                            String cad = "\n/**************************************************************************/\n";
                            if (simbolo.getAmbito().equals("Global")) {
                                cad += "// " + id + " = " + tipoObtenido + "\n";
                                cad += simbolo.getPosicionRelativa() + "// SERA LA POSICION ABSOLUTA DONDE SE ENCONTRARA SIN PUNTERO\n";
                                cad += expCode + "\n";
                                cad += "set_local $calc // LA POSICIONA AL INICIO DEL STACK \n";
                                cad += "/**************************************************************************/\n";
                            } else {
                                cad += "// " + simbolo.getTipo() + " = " + tipoObtenido + "\n";
                                cad += "get_local 0 // OBTENIENDO EL PUNTERO DE LA STACK\n";
                                cad += simbolo.getPosicionRelativa() + " // POSICION RELATIVA DE LA VARIABLE A ASIGNAR\n";
                                cad += "ADD // ENCONTRANDO LA POSICION Y METIENDOLO AL FONDO DEL STACK\n";
                                cad += expCode + "\n";
                                cad += "set_local $calc // COLOCANDO EL VALOR EN LA POSICION AL FONDO DE LA PILA\n";
                                cad += "/**************************************************************************/\n";
                            }
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
                            InfoEstatica.Estatico.agregarError(new TError(simbolo.getNumDimensiones() + "=" + InfoEstatica.Estatico.dims,
                                    "Dimensiones no coinciden",
                                    "Semantico",
                                    super.getLinea(),
                                    super.getColumna(),
                                    Boolean.FALSE, super.getArchivo()));
                        }
                        InfoEstatica.Estatico.dims = 0;
                        InfoEstatica.Estatico.valorArreglo = false;
                    } else {
                        InfoEstatica.Estatico.agregarError(new TError(tipoObtenido + "=" + simbolo.getTipo(),
                                "Los tipos asignandos no coinciden",
                                "Semantico",
                                super.getLinea(),
                                super.getColumna(),
                                Boolean.FALSE, super.getArchivo()));
                    }
                }
            } else {
                InfoEstatica.Estatico.agregarError(new TError("Variable: " + id, "No existe el Simbolo en Este contexto", "Semantico", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al Ejecutar la asignacin de Variables: " + e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        return "";
    }

}
