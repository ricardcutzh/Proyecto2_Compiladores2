/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

/**
 * Clase que representa el Simbolo de Un Lenguaje
 * @author richard
 */
public class Simbolo {
    String idSimbolo;
    Boolean esVector;
    String tipo;
    
    /**
     * Constructor de la clase Simbolo
     * Esta clase representa a cada simbolo del lenguage cada, Esta clase es
     * padre de todo objeto almacenado en la tabla de simbolos
     * @param idSimbolo
     * @param esVector
     * @param tipo 
     */
    public Simbolo(String idSimbolo, Boolean esVector, String tipo)
    {
        this.idSimbolo = idSimbolo;
        this.esVector = esVector;
        this.tipo = tipo;
    }

    /**
     * Getter de la propiedad IdSimbolo
     * @return Cadena que representa el Id de Simbolo
     */
    public String getIdSimbolo() {
        return idSimbolo;
    }
    
    /**
     * Getter de la propiedad esVector la cual permite saber si un simbolo es vector
     * @return Booleano, False o True
     */
    public Boolean getEsVector() {
        return esVector;
    }
    
    /**
     * Getter de la propiedad Tipo del Simbolo
     * @return cadena Que representa el Tipo del Simbolo 
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Override del Metodo que representa al SImbolo en una Cadena
     * @return representacion del Simbolo en Cadena
     */
    @Override
    public String toString() {
        return "Simbolo{" + "idSimbolo=" + idSimbolo + ", esVector=" + esVector + ", tipo=" + tipo + '}';
    }
    
    
}
