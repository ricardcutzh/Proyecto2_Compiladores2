/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.Global;

/**
 * Clase que representa el nodo de la estructura del HEAP
 * es decir de la estructura dinamica
 * @author richard
 */
public class NodoHeap {
    
    Double valor; // VALOR QUE SOSTIENE EL NODO
    Integer tamanio; // TAMANIO DEL DATO QUE SE ENCUENTRA EN EL NODO
    String tipo; // EL TIPO DE DATO QUE SE ESTA ALMACENANDO
    Boolean esNulo; // VER SI ES NULO O APUNTA A NULOS
    Integer posicionRelativa; // POSICION
    /**
     * Constructor del Nodo del HEAP
     * @param valor
     * @param tamanio
     * @param tipo
     * @param esNulo 
     * @param posicionRelativa
     */
    public NodoHeap(Double valor, Integer tamanio, String tipo, Boolean esNulo, Integer posicionRelativa) {
        this.valor = valor;
        this.tamanio = tamanio;
        this.tipo = tipo;
        this.esNulo = esNulo;
        this.posicionRelativa = posicionRelativa;
    }
    /**
     * Getter del valor del nodo
     * @return 
     */
    public Double getValor() {
        return valor;
    }
    /**
     * Setter del valor del nodo por si se quiere actualizar
     * @param valor 
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }
    /**
     * Getter del tamanio del nodo
     * @return 
     */
    public Integer getTamanio() {
        return tamanio;
    }
    /**
     * Setter del tamanio del nodo
     * @param tamanio 
     */
    public void setTamanio(Integer tamanio) {
        this.tamanio = tamanio;
    }
    /**
     * Getter del tipo de dato que almacena el nodo
     * @return 
     */
    public String getTipo() {
        return tipo;
    }
    /**
     * Setter del tipo del nodo
     * @param tipo 
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /**
     * Getter para ver si el valor es Nulo
     * @return 
     */
    public Boolean getEsNulo() {
        return esNulo;
    }
    /**
     * Setea la bandera para saber si el nodo es nulo
     * @param esNulo 
     */
    public void setEsNulo(Boolean esNulo) {
        this.esNulo = esNulo;
    }
    
    /**
     * Getter de la posicion relativa
     * @return 
     */
    public Integer getPosicionRelativa() {
        return posicionRelativa;
    }
    /**
     * Setter de la posicion relativa
     * @param posicionRelativa 
     */
    public void setPosicionRelativa(Integer posicionRelativa) {
        this.posicionRelativa = posicionRelativa;
    }

    
    
    
    
    
}
