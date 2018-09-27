/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstraccion;
import Simbolos.Ambito;
/**
 * La Interface que servire a todo nodo que ejecuta una instruccion y que
 * Posiblemente no devuelve un valor
 * @author richard
 */
public interface Intruccion {
    
    /**
     * Metodo que permitira a cad instruccion ejecutarse
     * @param ambito AMbito donde se encuentra la instrccuion
     * @return Posible valor si la instruccion lo requiere
     */
    Object Ejecutar(Ambito ambito);
}
