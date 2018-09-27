/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstraccion;
import Simbolos.Ambito;
/**
 * Clase padre de Arbol AST formado por nodos Expresion e Instruccion
 * @author richard
 */
public class NodoAST {
    
    int linea;
    int columna;
    String Archivo;
    
    /**
     * Clase que va a ser padre de todos los nodos de un arbol AST 
     * Esta clase permite almacenar valores importantes para el analisis
     * y reporte de errores
     * @param linea coordenada en y donde se encuentra el simbolo o el nodo
     * @param columna coordenada en x donde se encuentra el simbolo o nodo
     * @param Archivo el lugar donde se esta tomando la informacion
     */
    public NodoAST(int linea, int columna, String Archivo)
    {
        this.linea = linea;
        this.columna = columna;
        this.Archivo = Archivo;
    }
    
    /**
     * Getter para la propiedad de linea del Nodo
     * @return entero que representa la linea
     */
    public int getLinea() {
        return linea;
    }

    /**
     * Getter para la propiedad de la columna del Nodo
     * @return entero que representa la columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Getter del archivo donde se tomo la informacion del nodo (Puede servir para reporte de errores)
     * @return cadena que representa el archivo
     */
    public String getArchivo() {
        return Archivo;
    }
    
    /**
     * Metodo que generara codigo ByteCode cuando se necesite
     * @param ambito
     * @return COdigo Bytecode de cada nodo
     */
    public Object generateByteCode(Ambito ambito)
    {
        throw new UnsupportedOperationException();
    }
}
