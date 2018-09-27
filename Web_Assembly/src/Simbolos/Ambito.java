/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

/**
 * Clase manejadora de los Ambitos en la ejecucion
 * @author richard
 */
public class Ambito {
    String idAmbito;
    Ambito Anterior;
    TablaDeVariables tablaVars;
    String Archivo;
    
    /**
     * Constructode del Ambito nuevo
     * @param idAmbito Nombre que se le dara al Ambito
     * @param Anterior Apuntador al ambito anteorior (Para Busquedad)
     * @param Archivo  Archivo donde se genero el Ambito
     */
    public Ambito(String idAmbito, Ambito Anterior, String Archivo)
    {
        this.idAmbito = idAmbito;
        this.Archivo = Archivo;
        this.Anterior = Anterior;
    }
    
    /**
     * Getter del Identificador de Ambito
     * @return cadena que representa el ID
     */
    public String getIdAmbito() {
        return idAmbito;
    }

    /**
     * Getter del Apuntador hacia el Ambito Anterior
     * @return Objeto de tipo Ambito que representa al Anterior
     */
    public Ambito getAnterior() {
        return Anterior;
    }

    /**
     * Getter del Archivo donde se encuentra el Ambito
     * @return Cadena que representa el Path del archivo
     */
    public String getArchivo() {
        return Archivo;
    }
    
    /**
     * Metodo que permite Buscar si existe un Simbolo en el Ambito
     * @param id Clave del Simbolo
     * @return Simbolo si Existe o Nulo si no
     */
    public Simbolo getSimbolo(String id)
    {
        Ambito auxiliar = this;
        while(auxiliar!=null)
        {
            if(auxiliar.tablaVars.existeVariable(id))
            {
                return auxiliar.tablaVars.getVariable(id);
            }
            
            auxiliar = auxiliar.getAnterior();
        }
        return null;
    }
    
    /**
     * Metodo que agrega una variable al ambito
     * @param id clave del simbolo
     * @param sim El simbolo como tal
     */
    public void AgregarVariable(String id, Simbolo sim)
    {
        this.tablaVars.agregarVariabe(id, sim);
    }
    
}
