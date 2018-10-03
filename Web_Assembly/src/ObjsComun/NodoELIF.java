/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjsComun;
import java.util.ArrayList;
import Abstraccion.*;
/**
 *
 * @author richard
 */
public class NodoELIF {
    
    ArrayList<Instruccion> sentencias;
    Expresion condicion;
    int linea, col;
    String archivo;
    /**
     * Constructor del nodo auxiliar para poder manejar los elif
     * @param sentencias
     * @param condicion
     * @param linea
     * @param columna
     * @param archivo 
     */
    public NodoELIF(ArrayList<Instruccion> sentencias, Expresion condicion, int linea, int columna, String archivo)
    {
        this.sentencias = sentencias;
        this.condicion = condicion;
        this.linea = linea;
        this.col = columna;
        this.archivo = archivo;
    }
    
    public NodoELIF(ArrayList<Instruccion> sentencias, int linea, int columna, String archivo)
    {
        this.sentencias = sentencias;
        this.linea = linea;
        this.col = columna;
        this.archivo = archivo;
    }
    
    /**
     * Getter de la lista de sentencias
     * @return ArrayList de sentencias
     */
    public ArrayList<Instruccion> getSentencias() {
        return sentencias;
    }
    /**
     * Devuelve la expresion que representa la condicion
     * @return la condicion
     */
    public Expresion getCondicion() {
        return condicion;
    }
    /**
     * Getter de la linea donde se encuentra
     * @return linea de la coincidencia del elif
     */
    public int getLinea() {
        return linea;
    }
    /**
     * Getter de la columna donde se encuentra
     * @return columna de la coincidencia del elif
     */
    public int getCol() {
        return col;
    }
    /**
     * Archivo donde se encuentra
     * @return retorna el archivo
     */
    public String getArchivo() {
        return archivo;
    }
    
    
}
