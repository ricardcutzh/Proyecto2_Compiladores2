/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

/**
 *
 * @author richard
 */
public class DppArr extends Simbolo{
    int posicionRelativa;
    int numDimensiones;
    int Size;
    String ambito;
    public DppArr(String idSimbolo, Boolean esVector, String tipo, int posicionRelativa, int numeDimensiones, int Size, String ambito) {
        super(idSimbolo, esVector, tipo);
        this.posicionRelativa = posicionRelativa;
        this.numDimensiones = numeDimensiones;
        this.Size = Size;
        this.ambito = ambito;
    }

    public int getPosicionRelativa() {
        return posicionRelativa;
    }

    public int getNumDimensiones() {
        return numDimensiones;
    }

    public int getSize() {
        return Size;
    }

    public String getAmbito() {
        return ambito;
    }
    
    
    
}
