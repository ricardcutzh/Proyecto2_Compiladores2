/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Declaraciones;
import Abstraccion.*;
import Simbolos.Ambito;
/**
 *
 * @author richard
 */
public class DeclaracionVar extends  NodoAST implements Instruccion{
    Expresion expresion;
    String identificador;
    
    /**
     * Constructor inicial cuando la variable ya tiene un valor asociado
     * @param linea  linea del identificador
     * @param columna columna del identificador
     * @param Archivo Archivo donde se encuentra
     * @param identificador nombre de la variable
     * @param expresion  la expresion en cuestiom
     */
    public DeclaracionVar(int linea, int columna, String Archivo, String identificador, Expresion expresion) {
        super(linea, columna, Archivo);
        this.expresion = expresion;
        this.identificador = identificador;
    }
    
    /**
     * 
     * COnstructor inicial cuando la variable no ha sido inicializada
     * @param linea  linea del identificador
     * @param columna columna del identificador
     * @param Archivo Archivo donde se encuentra
     * @param identificador nombre de la variable
     */
    public DeclaracionVar(int linea, int columna, String Archivo, String identificador)
    {
        super(linea, columna, Archivo);
        this.identificador = identificador;
        this.expresion = null;
    }

    @Override
    public Object Ejecutar(Ambito ambito) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
