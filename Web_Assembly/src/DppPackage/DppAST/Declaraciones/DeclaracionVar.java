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
                String id = n.getIdentificador();
                if(!ambito.existeVariable(id))
                {
                    Expresion aux = n.getValor();
                    if(aux!=null)
                    {
                        // SI SI CONTIENE UNA EXPRESION
                        String expCode = (String)n.generateByteCode(ambito); // CODIGO GENERADO DE LA EXPRESION
                        String tipovar = aux.getTipo(ambito); // TIPO RESULTANTE
                        if(this.tipo.equals("CADENA")) // SI EL TIPO ESPERADO ES UNA CADENA...
                        {
                            
                        }
                        else // SI FUESE OTRO....
                        {
                            
                        }
                    }
                    else
                    {
                        // ASIGNACION POR DEFECTO
                    }
                }
                else
                {
                    InfoEstatica.Estatico.agregarError(new TError("Simbolo: "+n.getIdentificador(), "Ya existe una definicion del Simbolo en este Ambito", "Semantico", super.getLinea(), super.getColumna(), Boolean.FALSE, ambito.getArchivo()));
                }
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al traducir la declaracion de var: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(),false, ambito.getArchivo()));
        }
        return "";
    }
    
    
}
