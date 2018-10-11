/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjsComun;
import java.util.ArrayList;
/**
 * Clase que representara el hashmap de las funciones que existen en la definicion
 * de lenguage D++
 * @author richard
 */
public class Clave {
    String idFuncion;
    ArrayList<NodoParametro> parametros;
    /**
     * Constructor de la clave de las funciones y metodos
     * @param idFuncion
     * @param parametros 
     */
    public Clave(String idFuncion, ArrayList<NodoParametro> parametros)
    {
        this.parametros = parametros;
        this.idFuncion = idFuncion;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj!=null && obj instanceof Clave)
        {
            Clave aux = (Clave)obj;
            if(this.idFuncion.equals(aux.idFuncion) && this.parametros.size() == aux.parametros.size()) // SI EL NOMBRE DE LA FUNCION Y EL NUMERO DE PARAMETROS ES IGUAL ENTONCES...
            {
                for(int x = 0; x < this.parametros.size(); x++) // CADA PARAMETRO ES IGUAL?
                {
                    if(!this.parametros.get(x).equals(aux.parametros.get(x)))// SI NO ES IGUAL ALGUN PARAMETRO ENTONCES DEVUELVE FALSE
                    {
                        return false;
                    }
                }
                return true; // SI LLEGA A ESTE PUNTO ES DECIR QUE LA FUNCION NO ES LA MISMA
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "F_"+this.idFuncion+paramsID();
    }
    
    private String paramsID()
    {
        String cad = "";
        for(NodoParametro n : this.parametros)
        {
            cad += n.toString();
        }
        return cad;
    }
    
    @Override
    public int hashCode() {
        return this.idFuncion.hashCode();
    }
    
    
}
