/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Valores;
import Abstraccion.*;
import InfoEstatica.Estatico;
import Simbolos.Ambito;
/**
 * Clase que maneja las expresiones que tienen Operaciones
 * @author richard
 */
public class Operacion extends NodoAST implements Expresion {
    
    Expresion op1;
    Expresion op2;
    Estatico.OPERADORES operador;
    Boolean esUnario;
    /**
     * Constructor para las operaciones Binarias
     * @param linea  linea donde se encuentra al Expresion
     * @param columna columna donde se encuentra la expresion
     * @param Archivo Archivo donde se esta realizando
     * @param op1 Expresion izquierda
     * @param op2 Expresion derecha
     * @param operador Operador que va a decir que tipo de operacion se realizara
     */
    public Operacion(int linea, int columna, String Archivo, Expresion op1, Expresion op2, Estatico.OPERADORES operador) {
        super(linea, columna, Archivo);
        esUnario = false;
        this.op1 = op1;
        this.op2 = op2;
        this.operador = operador;
    }
    
    /**
     * Constructor para los operaciones Unarias
     * @param linea linea donde se encuentra al Expresion
     * @param columna columna donde se encuentra la expresion
     * @param Archivo Archivo donde se esta realizando
     * @param op1 unica expresion
     * @param operador OPerador Unario
     */
    public Operacion(int linea, int columna, String Archivo, Expresion op1, Estatico.OPERADORES operador)
    {
        super(linea, columna, Archivo);
        esUnario = true;
        this.op1 = op1;
        this.operador = operador;
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
