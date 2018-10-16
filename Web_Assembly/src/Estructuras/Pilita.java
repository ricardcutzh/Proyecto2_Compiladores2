/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;
import java.util.ArrayList;
/**
 *
 * @author richard
 */
public class Pilita {
  
    ArrayList<NodoPilita> pila;
    
    /**
     * Constructor de la pila que va a funcionar como una Lista
     * para facilitar la visualizacion de la pila
     */
    public Pilita()
    {
        this.pila = new ArrayList<>();
    }
    
    /**
     * Metodo de Pila para obtener la cima
     * @return la cima de la pila
     */
    public NodoPilita pop()
    {
        if(pila.size() > 0)
        {
            NodoPilita val = pila.get(0); // GUARDO EL VALOR AUXILIAR
            pila.remove(0);
            return val;
        }
        return null;
    }
    
    /**
     * Inserta un nodo al fondo de la pila
     * @param nodo nodo que se va a meter en la pila
     */
    public void push(NodoPilita nodo)
    {
        this.pila.add(0, nodo);
    }
    
    /**
     * Solo permite visualizar el valor en la cima de la pila
     * @return 
     */
    public Double peek()
    {
        if(pila.size()>0)
        {
            return pila.get(0).getValor();
        }
        return null;
    }
    
    public void printPilita()
    {
        System.out.println("****************************");
        for(NodoPilita n : this.pila)
        {
            System.out.println("| "+n.valor+" |");
        }
        System.out.println("****************************");
    }
}
