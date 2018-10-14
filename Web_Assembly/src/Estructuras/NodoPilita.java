/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 * Nodo utilizado para poder realizar las operaciones como temporales
 * OPeraciones como ADD, DIV, MOD, ETC
 * @author richard
 */
public class NodoPilita {
    int tamanio;
    int tipo;
    Double valor;
    /**
     * Constructor del nodo de la pilita
     * @param tamanio
     * @param tipo
     * @param valor 
     */
    public NodoPilita(int tamanio, int tipo, Double valor)
    {
        this.tamanio = tamanio;
        this.tipo = tipo;
        this.valor = valor;
    }
    
    /**
     * Obtener el tamanio del valor
     * @return tamanio del valor
     */
    public int getTamanio() {
        return tamanio;
    }

    /**
     * Obtener el tipo (POR SI ME SIRVE DESPUES SI SABER QUE ES)
     * @return 
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * obtener el valor que se esta guardando en la pilita
     * @return valor que se encuentra en la pila
     */
    public Double getValor() {
        return valor;
    }
    
    
    
}
