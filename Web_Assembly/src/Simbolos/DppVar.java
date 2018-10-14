/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

/**
 * clase que manejara los Simbolos del D++
 * @author richard
 */
public class DppVar extends Simbolo{
    int posicionRelativa;
    int size;
    String ambito;
    /**
     * Constructor del Simbolo que sirve para representar a las varaibles en cada ambito
     * @param idSimbolo IDENTIFICADOR DEL SIMBOLO
     * @param esVector SI ES VECTOR O NO
     * @param tipo EL TIPO
     * @param posicionRelativa LA POSICION EN LA PILA (TOMANDO EL 0 RELATIVO)
     * @param size TAMANIO DEL MISMO
     * @param ambito
     */
    public DppVar(String idSimbolo, Boolean esVector, String tipo, int posicionRelativa, int size, String ambito) {
        super(idSimbolo, esVector, tipo);
        this.posicionRelativa = posicionRelativa;
        this.size = size;
        this.ambito = ambito;
    }
    /**
     * Getter de la posicion relatiba
     * @return posicion relativa
     */
    public int getPosicionRelativa() {
        return posicionRelativa;
    }
    /**
     * Getter del tamanio
     * @return size
     */
    public int getSize() {
        return size;
    }

    public String getAmbito() {
        return ambito;
    }
    
}
