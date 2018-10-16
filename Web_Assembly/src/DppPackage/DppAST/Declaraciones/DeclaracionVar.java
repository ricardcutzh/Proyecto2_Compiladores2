/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Declaraciones;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import ErrorManager.TError;
import ObjsComun.NodoIDValor;
import Simbolos.Ambito;
import Simbolos.DppVar;
import Simbolos.SimboloTabla;
import java.util.ArrayList;

/**
 *
 * @author richard
 */
public class DeclaracionVar extends NodoAST {
    ArrayList<NodoIDValor> declaraciones;
    String tipo;
    /**
     * Constructor del nodo que se va a encargar de poder traducir las declaraciones de variables
     * @param linea
     * @param columna
     * @param Archivo
     * @param declaraciones declaraciones de las demas variables
     * @param tipo Tipo que se espera de la variable
     */
    public DeclaracionVar(int linea, int columna, String Archivo, ArrayList<NodoIDValor> declaraciones, String tipo) {
        super(linea, columna, Archivo);
        this.declaraciones = declaraciones;
        this.tipo = tipo;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            String cad = "";
            for(NodoIDValor n : this.declaraciones)
            {
                if(InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE)
                {
                    if(InfoEstatica.Estatico.esLinea)
                    {
                        InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), n.getLinea());
                        InfoEstatica.Estatico.suspended = true;
                        InfoEstatica.Estatico.hilo.suspend();
                    }
                    else
                    {
                        String key = n.getLinea()+"_"+super.getArchivo();
                        if(InfoEstatica.Estatico.breakPoints.containsKey(key))
                        {
                            InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), n.getLinea());
                            InfoEstatica.Estatico.suspended = true;
                            InfoEstatica.Estatico.hilo.suspend();
                        }
                    }
                }
                String id = n.getIdentificador();
                if(!ambito.existeVariable(id))
                {
                    DppVar simbolo = new DppVar(id, Boolean.FALSE, tipo, ambito.getSize(), 1, ambito.getIdAmbito());
                    Expresion aux = n.getValor();
                    if(aux!=null)
                    {
                        // SI SI CONTIENE UNA EXPRESION
                        String expCode = (String)((NodoAST)aux).generateByteCode(ambito);
                        String tipoObtenido = aux.getTipo(ambito);
                        switch(tipo)
                        {
                            case "CADENA":
                            {
                                break;
                            }
                            case "ENTERO":
                            {
                                if(tipoObtenido.equals("ENTERO"))
                                {
                                    cad += "\n/**************************************************************************/\n";
                                    cad += "// "+id+" = "+tipoObtenido+"\n";
                                    cad += puntero(ambito.getIdAmbito())+" 0 // TOMO EL PUNTERO DEL AMBITO ACTUAL\n";
                                    cad += simbolo.getPosicionRelativa() + "// POSICION RELATIVA AL 0 DEL AMBITO ACTUAL\n";
                                    cad += "ADD // SUMO PARA ENCONTRAR LA POSICION REAL DE LA VARAIABLE ENTERA \n";
                                    cad += expCode+"\n";
                                    cad += getSet(ambito.getIdAmbito())+" $calc // PONGO EL VALOR EN LA POSICION QUE ESTA AL FONDO DE LA PILA\n";
                                    cad += "/**************************************************************************/\n";
                                    ambito.addDppSimbol(id, simbolo);
                                    InfoEstatica.Estatico.AgregarTablaSimbolos(new SimboloTabla(id, Boolean.FALSE, tipo, ambito.getIdAmbito(), super.getLinea(), super.getColumna(), 1, "Variable"));
                                }
                                else
                                {
                                    InfoEstatica.Estatico.agregarError(new TError("Tipo Obtenido: "+tipoObtenido, "No se puede asignar: "+tipoObtenido+" a una variable de tipo: Entero", "Semantico", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
                                }
                                continue;
                            }
                            case "DECIMAL":
                            {
                                if(tipoObtenido.equals("DECIMAL"))
                                {
                                    cad += "\n/**************************************************************************/\n";
                                    cad += "// "+id+" = "+tipoObtenido+"\n";
                                    cad += puntero(ambito.getIdAmbito())+" 0 // TOMO EL PUNTERO DEL AMBITO ACTUAL\n";
                                    cad += simbolo.getPosicionRelativa() + "// POSICION RELATIVA AL 0 DEL AMBITO ACTUAL\n";
                                    cad += "ADD // SUMO PARA ENCONTRAR LA POSICION REAL DE LA VARAIABLE DECIMAL \n";
                                    cad += expCode+"\n";
                                    cad += getSet(ambito.getIdAmbito())+" $calc // PONGO EL VALOR EN LA POSICION QUE ESTA AL FONDO DE LA PILA\n";
                                    cad += "/**************************************************************************/\n";
                                    ambito.addDppSimbol(id, simbolo);
                                    InfoEstatica.Estatico.AgregarTablaSimbolos(new SimboloTabla(id, Boolean.FALSE, tipo, ambito.getIdAmbito(), super.getLinea(), super.getColumna(), 1, "Variable"));
                                }
                                else
                                {
                                    InfoEstatica.Estatico.agregarError(new TError("Tipo Obtenido: "+tipoObtenido, "No se puede asignar: "+tipoObtenido+" a una variable de tipo: Decimal", "Semantico", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
                                }
                                continue;
                            }
                            case "BOOLEAN":
                            {
                                if(tipoObtenido.equals("BOOLEAN"))
                                {
                                    cad += "\n/**************************************************************************/\n";
                                    cad += "// "+id+" = "+tipoObtenido+"\n";
                                    cad += puntero(ambito.getIdAmbito())+" 0 // TOMO EL PUNTERO DEL AMBITO ACTUAL\n";
                                    cad += simbolo.getPosicionRelativa() + "// POSICION RELATIVA AL 0 DEL AMBITO ACTUAL\n";
                                    cad += "ADD // SUMO PARA ENCONTRAR LA POSICION REAL DE LA VARAIABLE BOOLEANA \n";
                                    cad += expCode+"\n";
                                    cad += getSet(ambito.getIdAmbito())+" $calc // PONGO EL VALOR EN LA POSICION QUE ESTA AL FONDO DE LA PILA\n";
                                    cad += "/**************************************************************************/\n";
                                    ambito.addDppSimbol(id, simbolo);
                                    InfoEstatica.Estatico.AgregarTablaSimbolos(new SimboloTabla(id, Boolean.FALSE, tipo, ambito.getIdAmbito(), super.getLinea(), super.getColumna(), 1, "Variable"));
                                }
                                else
                                {
                                    InfoEstatica.Estatico.agregarError(new TError("Tipo Obtenido: "+tipoObtenido, "No se puede asignar: "+tipoObtenido+" a una variable de tipo: Boolean", "Semantico", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
                                }
                                continue;
                            }
                            case "CARACTER":
                            {
                                if(tipoObtenido.equals("CARACTER"))
                                {
                                    cad += "\n/**************************************************************************/\n";
                                    cad += "// "+id+" = "+tipoObtenido+"\n";
                                    cad += puntero(ambito.getIdAmbito())+" 0 // TOMO EL PUNTERO DEL AMBITO ACTUAL\n";
                                    cad += simbolo.getPosicionRelativa() + "// POSICION RELATIVA AL 0 DEL AMBITO ACTUAL\n";
                                    cad += "ADD // SUMO PARA ENCONTRAR LA POSICION REAL DE LA VARAIABLE CARACTER \n";
                                    cad += expCode+"\n";
                                    cad += getSet(ambito.getIdAmbito())+" $calc // PONGO EL VALOR EN LA POSICION QUE ESTA AL FONDO DE LA PILA\n";
                                    cad += "/**************************************************************************/\n";
                                    ambito.addDppSimbol(id, simbolo);
                                    InfoEstatica.Estatico.AgregarTablaSimbolos(new SimboloTabla(id, Boolean.FALSE, tipo, ambito.getIdAmbito(), super.getLinea(), super.getColumna(), 1, "Variable"));
                                }
                                else
                                {
                                    InfoEstatica.Estatico.agregarError(new TError("Tipo Obtenido: "+tipoObtenido, "No se puede asignar: "+tipoObtenido+" a una variable de tipo: Caracter", "Semantico", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
                                }
                            }
                        }
                    }
                    else
                    {
                        // ASIGNACION POR DEFECTO
                        switch(tipo)
                        {
                            case "CADENA":
                            {
                                break;
                            }
                            case "ENTERO":
                            {
                                cad += "\n/**************************************************************************/\n";
                                cad += "// "+id+"; (Entero sin asignacion)\n";
                                cad += puntero(ambito.getIdAmbito())+" 0 // TOMO EL PUNTERO DEL AMBITO ACTUAL\n";
                                cad += simbolo.getPosicionRelativa()+" // POSICION RELATIVA AL 0 DEL AMBITO ACTUAL\n";
                                cad += "ADD // SUMO PARA ENCONTRAR SU POSICION REAL \n";
                                cad += "0 //ASIGNACION DE VALOR 0 POR DEFECTO\n";
                                cad += getSet(ambito.getIdAmbito())+" $calc // PONGO EL VALOR EN LA POSICION QUE ESTA AL FONDO DE LA PILA\n";
                                cad += "/**************************************************************************/\n";
                                ambito.addDppSimbol(id, simbolo);
                                InfoEstatica.Estatico.AgregarTablaSimbolos(new SimboloTabla(id, Boolean.FALSE, tipo, ambito.getIdAmbito(), super.getLinea(), super.getColumna(), 1, "Variable"));
                                continue;
                            }
                            case "DECIMAL":
                            {
                                cad += "\n/**************************************************************************/\n";
                                cad += "// "+id+"; (Decimal sin asignacion)\n";
                                cad += puntero(ambito.getIdAmbito())+" 0 // TOMO EL PUNTERO DEL AMBITO ACTUAL\n";
                                cad += simbolo.getPosicionRelativa()+" // POSICION RELATIVA AL 0 DEL AMBITO ACTUAL\n";
                                cad += "ADD // SUMO PARA ENCONTRAR SU POSICION REAL \n";
                                cad += "0.0 //ASIGNACION DE VALOR 0 POR DEFECTO\n";
                                cad += getSet(ambito.getIdAmbito())+" $calc // PONGO EL VALOR EN LA POSICION QUE ESTA AL FONDO DE LA PILA\n";
                                cad += "/**************************************************************************/\n";
                                ambito.addDppSimbol(id, simbolo);
                                InfoEstatica.Estatico.AgregarTablaSimbolos(new SimboloTabla(id, Boolean.FALSE, tipo, ambito.getIdAmbito(), super.getLinea(), super.getColumna(), 1, "Variable"));
                                continue;
                            }
                            case "BOOLEAN":
                            {
                                cad += "\n/**************************************************************************/\n";
                                cad += "// "+id+"; (Boolean sin asignacion)\n";
                                cad += puntero(ambito.getIdAmbito())+" 0 // TOMO EL PUNTERO DEL AMBITO ACTUAL\n";
                                cad += simbolo.getPosicionRelativa()+" // POSICION RELATIVA AL 0 DEL AMBITO ACTUAL\n";
                                cad += "ADD // SUMO PARA ENCONTRAR SU POSICION REAL \n";
                                cad += "0 //ASIGNACION DE VALOR 0 POR DEFECTO\n";
                                cad += getSet(ambito.getIdAmbito())+" $calc // PONGO EL VALOR EN LA POSICION QUE ESTA AL FONDO DE LA PILA\n";
                                cad += "/**************************************************************************/\n";
                                ambito.addDppSimbol(id, simbolo);
                                InfoEstatica.Estatico.AgregarTablaSimbolos(new SimboloTabla(id, Boolean.FALSE, tipo, ambito.getIdAmbito(), super.getLinea(), super.getColumna(), 1, "Variable"));
                                continue;
                            }
                            case "CARACTER":
                            {
                                cad += "\n/**************************************************************************/\n";
                                cad += "// "+id+"; (Caracter sin asignacion)\n";
                                cad += puntero(ambito.getIdAmbito())+" 0 // TOMO EL PUNTERO DEL AMBITO ACTUAL\n";
                                cad += simbolo.getPosicionRelativa()+" // POSICION RELATIVA AL 0 DEL AMBITO ACTUAL\n";
                                cad += "ADD // SUMO PARA ENCONTRAR SU POSICION REAL \n";
                                cad += "0 //ASIGNACION DE VALOR 0 POR DEFECTO\n";
                                cad += getSet(ambito.getIdAmbito())+" $calc // PONGO EL VALOR EN LA POSICION QUE ESTA AL FONDO DE LA PILA\n";
                                cad += "/**************************************************************************/\n";
                                ambito.addDppSimbol(id, simbolo);
                                InfoEstatica.Estatico.AgregarTablaSimbolos(new SimboloTabla(id, Boolean.FALSE, tipo, ambito.getIdAmbito(), super.getLinea(), super.getColumna(), 1, "Variable"));
                                continue;
                            }
                            default:// SON ESTRUCTURAS
                            {
                                
                            }
                        }
                    }
                }
                else
                {
                    InfoEstatica.Estatico.agregarError(new TError("Simbolo: "+n.getIdentificador(), "Ya existe una definicion del Simbolo en este Ambito", "Semantico", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
                }
            }
            return cad;
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al traducir la declaracion de var: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(),false, super.getArchivo()));
        }
        return "";
    }
    
    private String puntero(String tipoAmbito)
    {
        if(tipoAmbito.equals("Global"))
        {
            return "get_global";
        }
        else
        {
            return "get_local";
        }
    }
    
    private String getSet(String tipoAmbito)
    {
        if(tipoAmbito.equals("Global"))
        {
            return "set_global";
        }
        return "set_local";
    }
}