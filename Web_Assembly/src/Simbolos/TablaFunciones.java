/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

import ObjsComun.Clave;
import java.util.HashMap;

/**
 * Clase que maneja las funciones existentes en el lenguaje, es utilizado
 * para aplicar la sobrecarga de metodos
 * @author richard
 */
public class TablaFunciones {
    
    HashMap<Clave, MetodoFuncion> funciones;
    /**
     * Constructor del manejador de las funciones que se pueden llamar
     * Todas las funciones y metodos que existen en el lenguaje
     * HasMap(Clave, MetodoFuncion)
     */
    public TablaFunciones()
    {
        this.funciones = new HashMap<>();
    }
    
    /**
     * funcion que comprueba si existe una determinada funcion en el ambito
     * @param key llave de la funcion
     * @return retorna verdadero si exsiste, de lo contrario devuelve falso
     */
    public boolean existeFuncionMetodo(Clave key)
    {
        return this.funciones.containsKey(key);
    }
    
    /**
     * Metodo que permite anadir una funcion o metodo al hash
     * @param key llave identificadora
     * @param fun funcion o metodo a almacenar
     */
    public void agregarMetodoFuncion(Clave key, MetodoFuncion fun)
    {
        this.funciones.put(key, fun);
    }
    
    
}
