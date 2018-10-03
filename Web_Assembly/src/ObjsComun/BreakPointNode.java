/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjsComun;

/**
 * Clase que va a manejar los puntos de quiebre en la aplicacion al momento de llamar al debugger
 * @author richard
 */
public class BreakPointNode {
    String archivo;
    Integer linea;
    /**
     * Constructor del nodo de breakPoint
     * @param Archivo Nombre del archivo
     * @param Linea Numero de Linea
     */
    public BreakPointNode(String Archivo, Integer Linea)
    {
        this.archivo = Archivo;
        this.linea = Linea;
    }
    /**
     * Getter del archivo!
     * @return Cadena que representa el archivo
     */
    public String getArchivo() {
        return archivo;
    }
    /**
     * Getter de la linea
     * @return numero de linea
     */
    public Integer getLinea() {
        return linea;
    }
    
}
