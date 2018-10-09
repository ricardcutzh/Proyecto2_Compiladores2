/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Valores;
import Abstraccion.*;
import Simbolos.Ambito;
/**
 *
 * @author richard
 */
public class Operacion extends NodoAST implements Expresion {
    
    /**
     * Constructor para operaciones binarias
     * @param linea
     * @param columna
     * @param Archivo
     * @param op1
     * @param op2
     * @param operador
     */
    public Operacion(int linea, int columna, String Archivo, Expresion op1, Expresion op2, InfoEstatica.Estatico.OPERADORES operador) {
        super(linea, columna, Archivo);
    }
    /**
     * Constructor para operaciones no binarias
     * @param linea
     * @param columna
     * @param archivo
     * @param op1
     * @param operador 
     */
    public Operacion(int linea, int columna, String archivo, Expresion op1, InfoEstatica.Estatico.OPERADORES operador)
    {
        super(linea, columna, archivo);
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        return super.generateByteCode(ambito); //To change body of generated methods, choose Tools | Templates.
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
