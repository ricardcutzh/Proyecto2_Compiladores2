/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.Global;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 * Clase que representa la estructura dinamica del HEAP
 * @author richard
 */
public class DasmHeap {
    // REPRESENTACION DEL ESPACIO DE MEMORIA DEL HEAP
    ArrayList<NodoHeap> nodos;
    int punteroHeap;
    /**
     * Constructor del la estructura dinamica que maneja as cadenas
     * las estructuras
     */
    public DasmHeap()
    {
        this.nodos = new ArrayList<>();
        this.nodos.add(new NodoHeap(1.0, 1, "HP", Boolean.FALSE, 0));// INGRESANDO EL PUNTERO
    }
    
    /**
     * Agrega un nodo en una posicion del heap
     * @param index
     * @param nodo 
     */
    public void addNodoAt(int index, NodoHeap nodo)
    {
        if(existePosicion(index))
        {
            this.nodos.remove(index);
        }
        this.nodos.add(index, nodo);
    }
    
    private Boolean existePosicion(int index)
    {
        try {
            NodoHeap aux = this.nodos.get(index);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    /**
     * Retorna un nodo especifico en una posicion asignada
     * @param index
     * @return 
     */
    public NodoHeap getNodoAt(int index)
    {
        return this.nodos.get(index);
    }
    
    
    public void graficarHeap(DefaultTableModel model)
    {
        try
        {
            for(NodoHeap n:this.nodos)
            {
                Object rowData[] = new Object[2];
                rowData[0] = n.getValor();
                rowData[1] = n.posicionRelativa;
                model.addRow(rowData);
            }
        } catch (Exception e) 
        {
            
        }
    }
    
    
}
