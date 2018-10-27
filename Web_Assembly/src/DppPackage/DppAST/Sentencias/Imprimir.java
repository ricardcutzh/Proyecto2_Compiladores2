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
                    cad += "// PASANDO EL PUNTERO DEL HEAP A LA FUNCION DE IMPRIMIR\n";
                    cad += "get_local 0 // PTR VIRTUAL\n";
                    cad += (ambito.getSize()-1)+" // TAMANIO DEL PUNTERO\n";
                    cad += "ADD \n";
                    cad += "1 // ES EL UNICO PARAMETRO QUE RECIBE\n";
                    cad += "ADD// sumo\n";
                    cad += expCode +" // EN EL FONDO ESTA EL PUNTERO DEL HEAP QUE ES LO QUE PASARE\n";
                    cad += "set_local $calc // COLOCO EL PUNTERO EN LA POSICION\n";
                    cad += "get_local 0 // CAMBIANDO AMBITO\n";
                    cad += (ambito.getSize()-1)+"\n";
                    cad += "ADD // SUMANDO\n";
                    cad += "set_local 0 // ACTUALIZANDO PUNTERO\n";
                    cad += "Call $_PRINT_STRING\n";
                    cad += "get_local 0 // REGRESANDO\n";
                    cad += (ambito.getSize()-1)+"\n";
                    cad += "DIFF // RESTANDO\n";
                    cad += "set_local 0 // ACTUALIZANDO PUNTERO\n";
                    return  cad;
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
