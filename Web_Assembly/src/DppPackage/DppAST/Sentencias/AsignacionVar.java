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
import Simbolos.DppVar;
import Simbolos.Simbolo;

/**
 * Clase que maneja la traduccion de una asignacion de variables
 * @author richard
 */
public class AsignacionVar extends NodoAST{
    String id;
    Expresion exp;
    /**
     * Constructor de la asignacion de Var
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
        try 
        {
            if(ambito.existeVariable(id))
            {
                Simbolo s = (Simbolo)ambito.getSimbolo(id);
                if(s instanceof DppVar)
                {
                    DppVar simbolo = (DppVar)s;
                    String expCode = (String)((NodoAST)exp).generateByteCode(ambito);// GENERO EL CODIGO RELACIONADO A LA EXPRESION
                    String tipoObtenido = exp.getTipo(ambito);
                    switch(simbolo.getTipo())
                    {
                        case "CADENA":
                        {
                            break;
                        }
                        case "ENTERO":
                        {
                            if(tipoObtenido.equals(simbolo.getTipo()))
                            {
                                String cad = "\n/**************************************************************************/\n";
                                cad += "// "+id+" = ENTERO \n";
                                cad += "get_local 0 // OBTENIENDO EL PUNTERO DE LA STACK\n";
                                cad += simbolo.getPosicionRelativa()+" // POSICION RELATIVA DE LA VARIABLE A ASIGNAR\n";
                                cad += "ADD // ENCONTRANDO LA POSICION Y METIENDOLO AL FONDO DEL STACK\n";
                                cad += expCode+"\n";
                                cad += "set_local $calc // COLOCANDO EL VALOR EN LA POSICION AL FONDO DE LA PILA\n";
                                cad += "/**************************************************************************/\n";
                                return cad;
                            }
                            break;
                        }
                        case "DECIMAL":
                        {
                            if(tipoObtenido.equals(simbolo.getTipo()))
                            {
                                String cad = "\n/**************************************************************************/\n";
                                cad += "// "+id+" = DECIMAL \n";
                                cad += "get_local 0 // OBTENIENDO EL PUNTERO DE LA STACK\n";
                                cad += simbolo.getPosicionRelativa()+" // POSICION RELATIVA DE LA VARIABLE A ASIGNAR\n";
                                cad += "ADD // ENCONTRANDO LA POSICION Y METIENDOLO AL FONDO DEL STACK\n";
                                cad += expCode+"\n";
                                cad += "set_local $calc // COLOCANDO EL VALOR EN LA POSICION AL FONDO DE LA PILA\n";
                                cad += "/**************************************************************************/\n";
                                return cad;
                            }
                            break;
                        }
                        case "BOOLEAN":
                        {
                            if(tipoObtenido.equals(simbolo.getTipo()))
                            {
                                String cad = "\n/**************************************************************************/\n";
                                cad += "// "+id+" = BOOLEAN \n";
                                cad += "get_local 0 // OBTENIENDO EL PUNTERO DE LA STACK\n";
                                cad += simbolo.getPosicionRelativa()+" // POSICION RELATIVA DE LA VARIABLE A ASIGNAR\n";
                                cad += "ADD // ENCONTRANDO LA POSICION Y METIENDOLO AL FONDO DEL STACK\n";
                                cad += expCode+"\n";
                                cad += "set_local $calc // COLOCANDO EL VALOR EN LA POSICION AL FONDO DE LA PILA\n";
                                cad += "/**************************************************************************/\n";
                                return cad;
                            }
                            break;
                        }
                        case "CARACTER":
                        {
                            if(tipoObtenido.equals(simbolo.getTipo()))
                            {
                                String cad = "\n/**************************************************************************/\n";
                                cad += "// "+id+" = CARACTER \n";
                                cad += "get_local 0 // OBTENIENDO EL PUNTERO DE LA STACK\n";
                                cad += simbolo.getPosicionRelativa()+" // POSICION RELATIVA DE LA VARIABLE A ASIGNAR\n";
                                cad += "ADD // ENCONTRANDO LA POSICION Y METIENDOLO AL FONDO DEL STACK\n";
                                cad += expCode+"\n";
                                cad += "set_local $calc // COLOCANDO EL VALOR EN LA POSICION AL FONDO DE LA PILA\n";
                                cad += "/**************************************************************************/\n";
                                return cad;
                            }
                            break;
                        }
                        default:
                        {
                            
                        }
                    }
                }
                else // CUANDO SEA ARREGLOS
                {
                    
                }
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError("Variable: "+id, "No existe el Simbolo en Este contexto", "Semantico", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al Ejecutar la asignacin de Variables: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        return "";
    }
    
    
}