/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Valores;
import Abstraccion.*;
import Simbolos.Ambito;
/**
 * Expresiones derivadas de un identificador
 * @author richard
 */
public class Identificador extends NodoAST implements Expresion{
    
    /**
     * Constructor de la clase que maneja expresiones con Identificadores
     * @param linea linea donde ocurre la expresion
     * @param columna columna donde ocurre la expresion
     * @param Archivo archivo donde ocurre la expresion
     * @param identificador  el id que se quiere mandar a llamar
     */
    public Identificador(int linea, int columna, String Archivo, String identificador) {
        super(linea, columna, Archivo);
    }

    @Override
    public String getTipo(Ambito ambito) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValor(Ambito ambito) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
