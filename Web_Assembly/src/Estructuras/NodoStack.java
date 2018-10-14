/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 * Nodo del stack para manejar ambitos
 * 
 * @author richard
 */
public class NodoStack {
    int indice; // INDICE DEL NODO DONDE SE POSICIONA
    Double valorAlamacenado; // VALOR QUE SE ESTA GUARDANDO
    int tamanio; // TAMANIO DEL TIPO
    String tipo; // TIPO SI LO NECESITO
    
    /**
     * Puntero del stack
     * @param indice
     * @param valor
     * @param tamanio
     * @param tipo 
     */
    public NodoStack(int indice, Double valor, int tamanio, String tipo)
    {
        this.indice = indice;
        this.valorAlamacenado = valor;
        this.tamanio = tamanio;
        this.tipo = tipo;
    }
    
    /**
     * GETTER DEL INDICE
     * @return 
     */
    public int getIndice() {
        return indice;
    }

    /**
     * Getter del valor alamacenado
     * @return 
     */
    public Double getValorAlamacenado() {
        return valorAlamacenado;
    }

    /**
     * obtener el tamanio del Tamanio del valor que se esta almacenando
     * @return 
     */
    public int getTamanio() {
        return tamanio;
    }

    /**
     * si necesito el tipo que se esta almacenando en esa posicion (puntero)
     * @return 
     */
    public String getTipo() {
        return tipo;
    }
    
    
}
