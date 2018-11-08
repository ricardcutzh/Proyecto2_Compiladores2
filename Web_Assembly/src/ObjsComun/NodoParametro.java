/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjsComun;

/**
 * Clase que me va a ayudar a poder diferenciar las funciones para realizar la sobrecaga de metodos
 * @author richard
 */
public class NodoParametro {
    
    String idParametro;
    String tipo;
    Boolean soyVector;
    int dimensiones;
    /**
     * Constructor del Objeto que manejara la diferenciacion de funciones 
     * @param idParametro identificador del parametro
     * @param tipo tipo del parametro que se esta realizando
     * @param soyVector si es o no es vector
     */
    public NodoParametro(String idParametro, String tipo, Boolean soyVector)
    {
        this.idParametro = idParametro;
        this.tipo = tipo;
        this.soyVector = soyVector;
        this.dimensiones = 0;
    }
    
    public NodoParametro(String idParametro, String tipo, Boolean soyVectero, int dimensiones)
    {
        this.idParametro = idParametro;
        this.tipo = tipo;
        this.soyVector = soyVectero;
        this.dimensiones = dimensiones;
        
    }
    
    /**
     * Getter del Identificador del Parametro
     * @return el identificador del parametro
     */
    public String getIdParametro() {
        return idParametro;
    }
    
    /**
     * Getter del tipo de parametro
     * @return el tipo del parametro 
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Getter de la bandera de si es o no vector
     * @return la bandera de vector
     */
    public Boolean getSoyVector() {
        return soyVector;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj !=null && obj instanceof NodoParametro)
        {
            NodoParametro aux = (NodoParametro)obj;
            if(aux.tipo.equals(this.tipo) && (aux.soyVector.equals(this.soyVector)) && this.dimensiones == aux.dimensiones)
            {
                return true;
            }
        }
        return false;
    }

    public int getDimensiones() {
        return dimensiones;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "_P_"+this.tipo;
    }
    
    
}
