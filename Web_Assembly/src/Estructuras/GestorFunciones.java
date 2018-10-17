/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import java.util.HashMap;

/**
 * Encargado de las funciones que se deben de almacenar
 * @author richard
 */
public class GestorFunciones {
    HashMap<String, FuncionDasm> funciones;
    
    /**
     * Constructor del gestor de funciones que  las almacena
     */
    public GestorFunciones()
    {
        this.funciones = new HashMap<>();
    }
    /**
     * Verifica la existencia de una funcion
     * @param id
     * @return 
     */
    public boolean ExisteFuncion(String id)
    {
        return this.funciones.containsKey(id);
    }
    /**
     * Obtiene la funcion que que corresponde por el ID
     * @param id identificador de la funcion
     * @return 
     */
    public FuncionDasm getFuncion(String id)
    {
        return this.funciones.get(id);
    }
    /**
     * Agrega una funcion a la estructura
     * @param id
     * @param funcion 
     */
    public void addFuncion(String id, FuncionDasm funcion)
    {
        this.funciones.put(id, funcion);
    }
}
