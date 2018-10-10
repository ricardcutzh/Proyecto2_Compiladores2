/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Importaciones;

import Abstraccion.NodoAST;
import Simbolos.Ambito;

/**
 *
 * @author richard
 */
public class Importacion extends NodoAST{
    String ruta;
    /**
     * Constructor del nodo que se encargara de las importaciones
     * de los archivos Dpp
     * @param linea linea donde se encuentra la sentencia de importacion
     * @param columna columna donde se encuentra la sentencia de importacion
     * @param Archivo Archivo donde se encuentra la importacion
     * @param ruta ruta donde se encuentra la importacion
     */
    public Importacion(int linea, int columna, String Archivo, String ruta) {
        super(linea, columna, Archivo);
        this.ruta = ruta;
    }
    /**
     * Metodo que iniciara la generacion de codigo de las importaciones
     * @param ambito Ambito donde se generara el codigo
     * @return Objeto representando el codigo de bytecode
     */
    @Override
    public Object generateByteCode(Ambito ambito) {
        return super.generateByteCode(ambito); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
