/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 * Puntero de instrucciones
 * @author richard
 */
public class IP {
    // indice puntero de instrucciones
    int indiceActual;
    
    public IP(int indiceinicial)
    {
        this.indiceActual = indiceinicial;
    }
    /**
     * Obtiene el indice actual
     * @return 
     */
    public int getIndiceActual() {
        return indiceActual;
    }
    
    /**
     * Setea el indice actual
     * @param indiceActual 
     */
    public void setIndiceActual(int indiceActual) {
        this.indiceActual = indiceActual;
    }
    
    
}
