/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;
import java.util.ArrayList;
/**
 * Stack de los ambitos que se van a manejar
 * 
 * @author richard
 */
public class StackAmbito {
    int punteroStack; // PUNTERO PARA MOVERME EN EL STACK
    ArrayList<NodoStack> Pila; //PILA QUE SE MANEJARA PARA LOS AMBITOS
    /**
     * Constructor de la estructura de la pila
     */
    public StackAmbito()
    {
        this.Pila = new ArrayList<>();
        this.punteroStack = 0; // PUNTERO INICIARA EN 0
        NodoStack puntero = new NodoStack(punteroStack,Double.valueOf(punteroStack),1,"STACKPOINTER");
        set_local(puntero, punteroStack);// SETEAL EL PUNTERO DE LA STACK EN LA POSICION 0
    }
    
    /**
     * va a colocar el nodo en la pocision correspondiente
     * @param nodo
     * @param indice 
     */
    public void set_local(NodoStack nodo, int indice)
    {
        if(existePosicion(indice))
        {
            this.Pila.remove(indice);
        }
        this.Pila.add(indice, nodo);
    }
    
    /**
     * Va a obtener el valor en el indice correspondiente
     * @param indice
     * @return 
     */
    public NodoStack get_local(int indice)
    {
        return this.Pila.get(indice);
    }
    
    /**
     * Obtiene el puntero de la stack
     * @return 
     */
    public Integer getPuntero()
    {
        return this.punteroStack;
    }
    
    /**
     * Seteal el puntero con un nuevo valor
     * @param nuevoValor 
     */
    public void SetPuntero(int nuevoValor)
    {
        this.punteroStack = nuevoValor;
    }
    
    public void printStack()
    {
        System.out.println("*****************************************");
        System.out.println("*                AMBITOS                *");
        System.out.println("*****************************************");
        for(NodoStack n: this.Pila)
        {
            System.out.println("| "+n.valorAlamacenado+" |");
        }
        System.out.println("*****************************************");
    }
    
    private Boolean existePosicion(int pos)
    {
        try {
            NodoStack aux = Pila.get(pos);
            return  true;
        } catch (Exception e) {
            return  false;
        }
    }
}
