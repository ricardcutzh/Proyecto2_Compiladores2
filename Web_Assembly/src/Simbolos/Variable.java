/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

/**
 * Clase que maneja las Variables existentes en el Lenguaje
 * @author richard
 */
public class Variable extends Simbolo {
    
    Object valor;
    /**
     * Clase HIja de Simbolo que representa las variables
     * @param idSimbolo
     * @param esVector
     * @param tipo
     * @param Valor Valor de la Variable
     */
    public Variable(String idSimbolo, Boolean esVector, String tipo, Object Valor)
    {
        super(idSimbolo, esVector, tipo);
        this.valor = Valor;
    }
    
    /**
     * Getter del Valor de la variable
     * @return Object que representa el valor de la Variable 
     */
    public Object getValor() {
        return valor;
    }
    
    
}
