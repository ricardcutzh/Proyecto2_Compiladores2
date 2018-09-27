/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;
import java.util.HashMap;
/**
 * Clase manejadora de las variables existentes en un Ambito
 * @author richard
 */
public class TablaDeVariables {
    
    HashMap<String, Simbolo> variables;
    
    /**
     * Constructor que se encarga de inicializar el HasMap que contiene
     * Todas las variables existentes
     * HasMap (String, Simbolo)
     */
    public TablaDeVariables()
    {
        this.variables = new HashMap<>();
    }
    
    /**
     * Verifica la existencia de una Variable en el Hash
     * @param id Key del Simbolo que se desea buscar
     * @return Verdadero si existe, Falso si no existe...
     */
    public Boolean existeVariable(String id)
    {
        return this.variables.containsKey(id);
    }
    
    /**
     * Agrega un nuevo Simbolo al HashMap de la tabla
     * @param id Clave del SImbolo (Cadena)
     * @param s El simbolo que se desea almacenar (Simbolo)
     */
    public void agregarVariabe(String id, Simbolo s)
    {
        this.variables.put(id, s);
    }
    
    /**
     * Metodo que devuelve el SImbolo especificado
     * @param id clave del Simbolo
     * @return SI existe devuelve el Simbolo sino devuelve Null
     */
    public Simbolo getVariable(String id)
    {
        if(existeVariable(id))
        {
            return variables.get(id);
        }
        else
        {
            return null;
        }
    }
    
}
