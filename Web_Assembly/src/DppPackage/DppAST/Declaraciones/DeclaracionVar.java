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
            for(NodoIDValor n : this.declaraciones)
            {
                Expresion aux = (Expresion)n.getValor(); // OBTENGO EL TIPO DE LA EXPRESION DE LA DECLARACION
                if(aux!=null) // SI EXISTE CODIGO DE LA EXPRESION
                {
                    NodoAST nodo = (NodoAST)aux;
                    String expCode = (String)nodo.generateByteCode(ambito);
                    System.out.print(expCode); // para mientras....
                }
                else // EN CASO QUE NO EXISTA SE ASIGNA UN VALOR POR DEFECTO
                {
                    System.out.println("AUN SIN IMPLEMENTACION");
                }
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al traducir la declaracion de var: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(),false, ambito.getArchivo()));
        }
        return "";
    }
    
    
}
