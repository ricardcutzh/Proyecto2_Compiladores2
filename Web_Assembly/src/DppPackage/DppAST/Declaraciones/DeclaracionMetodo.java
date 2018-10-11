/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Declaraciones;

import Abstraccion.NodoAST;
import ErrorManager.TError;
import ObjsComun.Clave;
import ObjsComun.NodoParametro;
import Simbolos.Ambito;
import Simbolos.MetodoFuncion;
import java.util.ArrayList;
/**
 * Clase que maneja la traduccion de los metodos
 * @author richard
 */
public class DeclaracionMetodo extends NodoAST{
    String id;
    ArrayList<Object> sentencias;
    ArrayList<NodoParametro> parametros;
    /**
     * Constructor del nodo que se encargara de la traduccion de un metodo
     * @param linea
     * @param columna
     * @param Archivo
     * @param id identificador del metodo
     * @param parametros parametros que recibe el metodo
     * @param sentencias sentencias que tiene el metodo
     */
    public DeclaracionMetodo(int linea, int columna, String Archivo, String id, ArrayList<NodoParametro> parametros, ArrayList<Object> sentencias) {
        super(linea, columna, Archivo);
        this.id = id;
        this.parametros = parametros;
        this.sentencias = sentencias;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            String cad = "";
            Clave key = new Clave(id, parametros);
            if(!ambito.existeFuncion(key))
            {
                //ambito.agregarMetodoFuncion(key, new MetodoFuncion(id, false, "vacio"));
                cad += "\n/* CODIGO DE METODO: "+id+"*/\n";
                cad += "Function $"+key.toString()+"\n";
                for(Object o : sentencias)
                {
                    if(o instanceof NodoAST)
                    {
                        NodoAST aux = (NodoAST)o;
                        cad += (String)aux.generateByteCode(new Ambito("Local", ambito, ambito.getArchivo()));
                    }
                }
                cad +="\nEnd\n\n";
                ambito.agregarMetodoFuncion(key, new MetodoFuncion(id, Boolean.FALSE, "VACIO", 0)); // FALTA DE SABER EL SIZE DEL METODO
                return cad;
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError("Metodo: "+id, "Ya existe la definicion de un metodo: "+id+" que recibe la misma cantidad y tipo de parametros", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al traducir un metodo: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), true, ambito.getArchivo()));
        }
        return "";
    }
    
}
