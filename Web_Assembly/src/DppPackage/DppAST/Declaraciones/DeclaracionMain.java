    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Declaraciones;

import Abstraccion.NodoAST;
import ErrorManager.TError;
import Estructuras.Display;
import Simbolos.Ambito;
import Simbolos.SimboloTabla;
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
        this.sentencias = sentencias;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            InfoEstatica.Estatico.display = new Display();
            InfoEstatica.Estatico.tipoFuncion = "VACIO";
            String cad = "/*METODO PRINCIPAL*/\nFunction $principal\n";
            Ambito local = new Ambito("Local | Principal", ambito, ambito.getArchivo());
            InfoEstatica.Estatico.tabula();
            String aux = "";
            for(Object i : sentencias)
            {
                if(i instanceof NodoAST)
                {
                    aux = (String)((NodoAST)i).generateByteCode(local);
                    cad += InfoEstatica.Estatico.aplicaTabulaciones(aux);
                }
            }
            InfoEstatica.Estatico.destabula();
            cad += "\n\t$e_retornar";
            cad += "\nEnd\n";
            InfoEstatica.Estatico.AgregarTablaSimbolos(new SimboloTabla("Principal", Boolean.FALSE, "VACIO", "Global | Principal",super.getLinea()
                    , super.getColumna(), ambito.getSize() - 1, "Metodo Main"));
            InfoEstatica.Estatico.tipoFuncion = "";// QUITO EL TIPO DE LA FUNCION QUE SE ESTA TRADUCIENDO
            return cad;
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error en la traduccion del metodo Main: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo()));
        }
        return "";
    }
    
}
