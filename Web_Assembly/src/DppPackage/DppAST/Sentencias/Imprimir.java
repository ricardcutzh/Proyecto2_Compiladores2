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

/**
 *
 * @author richard
 */
public class Imprimir extends NodoAST{
    Expresion expe;
    public Imprimir(int linea, int columna, String Archivo, Expresion expe) {
        super(linea, columna, Archivo);
        this.expe = expe;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            String expCode = (String)((NodoAST)expe).generateByteCode(ambito);
            String tipo = expe.getTipo(ambito);
            String cad = "\n//IMPRIMIEDO...\n";
            switch(tipo)
            {
                case "CADENA":
                {
                    break;
                }
                case "":
                {
                    break;
                }
                case "ENTERO":
                {
                    cad += "\"%d\"\n // PARA IMPRIMR ENTERO\n";
                    cad += expCode+"\n";
                    cad += "PRINT // FUNCION DE IMPRIMIR...\n";
                    return cad;
                }
                case "BOOLEAN":
                {
                    cad += "\"%d\"\n // PARA IMPRIMR BOOLEAN\n";
                    cad += expCode+"\n";
                    cad += "PRINT // FUNCION DE IMPRIMIR...\n";
                    return cad;
                }
                case "DECIMAL":
                {
                    cad += "\"%f\"\n // PARA IMPRIMR DECIMALES\n";
                    cad += expCode+"\n";
                    cad += "PRINT // FUNCION DE IMPRIMIR...\n";
                    return cad;
                }
                case "CARACTER":
                {
                    cad += "\"%c\"\n // PARA IMPRIMR CARACTERES\n";
                    cad += expCode+"\n";
                    cad += "PRINT // FUNCION DE IMPRIMIR...\n";
                    return cad;
                }
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError(
                    "No Aplica"
                    , "Error al traducir imprimir: "+e.getMessage(), "Ejecucion"
                    , super.getLinea(), super.getColumna()
                    , Boolean.FALSE
                    , super.getArchivo()));
        }
        return "";
    }
    
    
}
