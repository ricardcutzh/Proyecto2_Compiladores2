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
 * Clase que va a manejar la declaracion de las Funciones declaradas en D++
 * @author richard
 */
public class DeclaracionFuncion extends NodoAST{
    ArrayList<Object> sentencias;
    ArrayList<NodoParametro> parametros;
    String tipo;
    String id;
    /**
     * Constructor del nodo que se va a encargar de la traduccion del la funcion
     * @param linea
     * @param columna
     * @param Archivo
     * @param tipo tipo de la funcion
     * @param id identificador de la funcion
     * @param parametros parametros que recibe la funcion
     * @param sentencias sentencias de la funcion
     */
    public DeclaracionFuncion(int linea, int columna, String Archivo, String tipo, String id, ArrayList<NodoParametro> parametros, ArrayList<Object> sentencias) {
        super(linea, columna, Archivo);
        this.id = id;
        this.tipo = tipo;
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
                //ambito.agregarMetodoFuncion(key, new MetodoFuncion(id, Boolean.FALSE, tipo));
                cad += "\n/* CODIGO DE FUNCION: "+id+"*/\n";
                cad += "Function $"+key.toString()+"\n";
                for(Object o : this.sentencias)
                {
                    if(o instanceof NodoAST)
                    {
                        NodoAST aux = (NodoAST)o;
                        cad += (String)aux.generateByteCode(new Ambito("Local", ambito, ambito.getArchivo()));
                    }
                }
                cad +="\nEnd\n\n";
                ambito.agregarMetodoFuncion(key, new MetodoFuncion(id, Boolean.FALSE, tipo, 0)); // FALTA DE SABER EL SIZE DEL METODO
                return cad;
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError("Funcion: "+id, "Ya existe una definicion de una funcion: "+id+" que recibe la mismca cantidad de parametros", "Semantico", super.getLinea(), super.getColumna(), Boolean.FALSE, ambito.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error en la traduccion de Funcion: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(),false, ambito.getArchivo()));
        }
        return "";
    }
    
    
}
