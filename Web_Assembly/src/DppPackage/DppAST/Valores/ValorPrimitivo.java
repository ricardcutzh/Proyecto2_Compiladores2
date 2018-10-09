/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Valores;
import Abstraccion.*;
import Simbolos.Ambito;
/**
 * Clase que manejara la traduccion de un valor primitivo
 * @author richard
 */
public class ValorPrimitivo extends NodoAST implements Expresion{
    
    Object valor;
    /**
     * Constructor de la clase de los metodos primitivos
     * @param linea linea donde se encuentra
     * @param columna columna donde se encuentra
     * @param Archivo el archivo donde se ubica
     * @param valor el valor que se va a guardar
     */
    public ValorPrimitivo(int linea, int columna, String Archivo, Object valor) {
        super(linea, columna, Archivo);
        this.valor = valor;
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
