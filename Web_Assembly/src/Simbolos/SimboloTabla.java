/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

/**
 * Clase para colocar el simbolo en tabla de simbolos
 * @author richard
 */
public class SimboloTabla extends Simbolo{
    int linea, columna, tamanio;
    String ambito, rol;
    /**
     * Constructor por defecto
     * @param idSimbolo
     * @param esVector
     * @param tipo
     * @param ambito
     * @param linea
     * @param columna
     * @param tamanio
     * @param rol 
     */
    public SimboloTabla(String idSimbolo, Boolean esVector, String tipo, String ambito, int linea, int columna, int tamanio, String rol) {
        super(idSimbolo, esVector, tipo);
        this.linea = linea;
        this.columna = columna;
        this.tamanio = tamanio;
        this.rol = rol;
        this.ambito = ambito;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }

    public int getTamanio() {
        return tamanio;
    }

    public String getAmbito() {
        return ambito;
    }

    public String getRol() {
        return rol;
    }

    @Override
    public String getTipo() {
        return super.getTipo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean getEsVector() {
        return super.getEsVector(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIdSimbolo() {
        return super.getIdSimbolo(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
