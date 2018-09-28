/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjsComun;
import Abstraccion.Expresion;
/**
 *
 * @author richard
 */
public class NodoIDValor {
    
    String identificador;
    
    Expresion valor; /*EN ESTE VA IMPLICITO LA LINEA Y LA COLUMNA DONDE SE PREDUCEN*/
    
    /**
     * Nodo que me va a permitir sintetizar los datos en la construccion del AST
     *  
     * @param identificador el ID de la variable que se va a declarar
     * @param Valor Un nodo EXPRESION  que va a contener el valor de la variable
     */
    public NodoIDValor(String identificador, Expresion Valor)
    {
        this.identificador = identificador;
        this.valor = Valor;
    }
    
    /**
     * Getter del ID
     * @return identeificador del Nodo
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Getter del Valor
     * @return NOdo Expresion
     */
    public Expresion getValor() {
        return valor;
    }
    
    
}
