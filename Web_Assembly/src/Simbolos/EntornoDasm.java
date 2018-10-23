/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;
import Estructuras.*;
import java.util.HashMap;
/**
 * CLASE QUE VA A CONTENER LAS ESTRUCTURAS NECESARIAS PARA REALIZAR LA EJECUCION
 * DEL DASM
 * @author richard
 */
public class EntornoDasm {
    Pilita pilita;
    StackAmbito ambitos;
    GestorFunciones gestor;
    Ret posicionRet;
    HashMap<String, Integer> etiquetas;
    /**
     * Constructor del ambito DASM para el manejo de la ejecucion de todo lo que se necesita
     * @param gestor 
     */
    public EntornoDasm(GestorFunciones gestor)
    {
        this.pilita = new Pilita();
        this.ambitos = new StackAmbito();
        this.gestor = gestor;
        posicionRet = new Ret();
    }
    
    /**
     * Obtiene la estructura de la pilita
     * @return 
     */
    public Pilita getPilita() {
        return pilita;
    }

    /**
     * Obtiene la pila de ambitos
     * @return 
     */
    public StackAmbito getAmbitos() {
        return ambitos;
    }

    /**
     * Obtiene el gestor de funciones
     * @return 
     */
    public GestorFunciones getGestor() {
        return gestor;
    }

    public Ret getPosicionRet() {
        return posicionRet;
    }
    /**
     * SETEA UN NUEVO CONJUNTO DE ETIQUETAS
     * @param etiquetas 
     */
    public void setEtiquetas(HashMap<String, Integer> etiquetas)
    {
        this.etiquetas = etiquetas;
    }
    /**
     * Obtiene el indice actual de la etiqueta
     * @param etiqueta
     * @return 
     */
    public Integer getIndiceDe(String etiqueta)
    {
        return this.etiquetas.containsKey(etiqueta)?this.etiquetas.get(etiqueta):-1;
    }
}
