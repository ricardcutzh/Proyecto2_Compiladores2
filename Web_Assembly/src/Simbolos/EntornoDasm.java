/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;
import Estructuras.*;
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
    
    
}
