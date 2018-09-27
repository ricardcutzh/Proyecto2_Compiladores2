/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstraccion;
import Simbolos.Ambito;
/**
 * Interfaz que van a implementar todas las intrucciones que devuelven un Valor
 * @author richard
 */
public interface Expresion {
    
    /**
     * Metodo que devuelve el tipo del la Expresion
     * @param ambito Ambito donde se encuentra la expresion
     * @return retorna la cadena que representa el tipo de la Expresion
     */
    String getTipo(Ambito ambito);
    
    /**
     * Metodo que devuelve el Valor de la Expresion
     * @param ambito Ambito donde se encuentra la expresion
     * @return  Objecto que representa el valor
     */
    Object getValor(Ambito ambito);
}
