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
public class Display {
    ArrayList<NodoDisplay> nodos;
    NodoDisplay ultimoCiclo;
    int counter;
    public Display()
    {
        this.nodos = new ArrayList<>();
        this.counter = 0;
    }
    /**
     * 
     * @param tipo
     * @param esCiclo 
     */
    public void PushToDisplay(String tipo, Boolean esCiclo)
    {
        counter++;
        this.nodos.add(0,new NodoDisplay(tipo, this.nodos.size(), esCiclo, counter));// EN LA CIMA LO COLOCO
    }
    /**
     * Obtiene el elemento en la cima
     * @return 
     */
    public NodoDisplay PopFromDisplay()
    {
        if(!this.nodos.isEmpty())
        {
            NodoDisplay n = this.nodos.get(0);
            this.nodos.remove(0);
            return n;
        }
        return null;
    }
    
    public Boolean estVacio()
    {
        return this.nodos.isEmpty();
    }
    
    public NodoDisplay Peek()
    {
        if(!this.nodos.isEmpty())
        {
            return this.nodos.get(0);
        }
        return null;
    }
    
    public NodoDisplay getLastCicle()
    {
        for(NodoDisplay n : this.nodos)
        {
            if(n.esCiclo)
            {
                return n;
            }
        }
        return null;
    }
}

