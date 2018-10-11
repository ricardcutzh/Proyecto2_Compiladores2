/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Declaraciones;

import Abstraccion.NodoAST;
import ErrorManager.TError;
import Simbolos.Ambito;
import java.util.ArrayList;

/**
 *
 * @author richard
 */
public class DeclaracionMain extends NodoAST{
    ArrayList<Object> sentencias;
    /**
     * Constructor del nodo del arbol que va a contener el metodo principal (MAIN)
     * @param linea linea donde se encuentran
     * @param columna columna donde se encuentra la coincidencia
     * @param Archivo Archivo donde fue encontrado el main
     * @param sentencias sentencias que lleva el main
     */
    public DeclaracionMain(int linea, int columna, String Archivo, ArrayList<Object> sentencias) {
        super(linea, columna, Archivo);
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            String cad = "/*METODO PRINCIPAL DONDE INICIA LA EJECUCION: */\nFunction $principal\n";
            cad += "\nEnd\n\n";
            return cad;
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error en la traduccion del metodo Main: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
        }
        return "Main";
    }
    
}
