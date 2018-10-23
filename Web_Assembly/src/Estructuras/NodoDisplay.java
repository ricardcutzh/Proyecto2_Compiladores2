/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author richard
 */
public class NodoDisplay {
    String tipo;
    int id;
    boolean esCiclo;
    int idUnico;
    
    public NodoDisplay(String tipo, int id, boolean esCiclo, int idUnico)
    {
        this.tipo = tipo;
        this.id = id;
        this.esCiclo = esCiclo;
        this.idUnico = idUnico;
    }

    public String getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    public boolean isEsCiclo() {
        return esCiclo;
    }

    @Override
    public String toString() {
        return tipo + "_"+id+"_"+idUnico;
    }
    
    
    
    
}
