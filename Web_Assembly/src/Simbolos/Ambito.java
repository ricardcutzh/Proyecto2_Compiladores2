/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

import ObjsComun.Clave;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase manejadora de los Ambitos en la ejecucion
 *
 * @author richard
 */
public class Ambito {

    String idAmbito;
    Ambito Anterior;
    TablaDeVariables tablaVars;
    TablaFunciones tablaFunciones;
    String Archivo;
    int tamanio;
    HashMap<String, Struct> Structs;

    /**
     * Constructode del Ambito nuevo
     *
     * @param idAmbito Nombre que se le dara al Ambito
     * @param Anterior Apuntador al ambito anteorior (Para Busquedad)
     * @param Archivo Archivo donde se genero el Ambito
     */
    public Ambito(String idAmbito, Ambito Anterior, String Archivo) {
        this.idAmbito = idAmbito;
        this.Archivo = Archivo;
        this.Anterior = Anterior;
        this.tablaVars = new TablaDeVariables();
        this.tablaFunciones = new TablaFunciones();
        this.tamanio = 1;
        Structs = new HashMap<>();
    }

    /**
     * Getter del Identificador de Ambito
     *
     * @return cadena que representa el ID
     */
    public String getIdAmbito() {
        return idAmbito;
    }

    /**
     * Getter del Apuntador hacia el Ambito Anterior
     *
     * @return Objeto de tipo Ambito que representa al Anterior
     */
    public Ambito getAnterior() {
        return Anterior;
    }

    /**
     * Getter del Archivo donde se encuentra el Ambito
     *
     * @return Cadena que representa el Path del archivo
     */
    public String getArchivo() {
        return Archivo;
    }

    /**
     * Metodo que permite Buscar si existe un Simbolo en el Ambito
     *
     * @param id Clave del Simbolo
     * @return Simbolo si Existe o Nulo si no
     */
    public Simbolo getSimbolo(String id) {
        Ambito auxiliar = this;
        while (auxiliar != null) {
            if (auxiliar.tablaVars.existeVariable(id)) {
                return auxiliar.tablaVars.getVariable(id);
            }

            auxiliar = auxiliar.getAnterior();
        }
        return null;
    }

    /**
     * Metodo que agrega una variable al ambito
     *
     * @param id clave del simbolo
     * @param sim El simbolo como tal
     */
    public void AgregarVariable(String id, Simbolo sim) {
        this.tablaVars.agregarVariabe(id, sim);
    }

    /**
     * ME VA A SERVIR PARA LLEVAR EL CONTROL DE LOS SIMBOLOS EXISTENTES EN EL
     * AMBITO
     *
     * @param id
     * @param sim
     */
    public void addDppSimbol(String id, Simbolo sim) {
        this.tablaVars.agregarVariabe(id, sim);
        this.tamanio++;
    }

    /**
     * Getter del tamanio del Ambito
     *
     * @return
     */
    public int getSize() {
        return this.tamanio;
    }

    /**
     * Comprueba si la variable especificada existe en el ambito
     *
     * @param id clave de la variable
     * @return Verdadero si existe, Falso si no existe
     */
    public Boolean existeVariable(String id) {
        if (this.tablaVars.existeVariable(id)) {
            return true;
        } else {
            Ambito auxiliar = this;
            while (auxiliar != null) {
                if (auxiliar.tablaVars.existeVariable(id)) {
                    return true;
                }

                auxiliar = auxiliar.getAnterior();
            }
            return false;
        }
    }

    public void tomaValoresDeAmbito(Ambito padre) {
        TomaVariables(padre.tablaVars);
    }

    public void TomaVariables(TablaDeVariables tablaPadre) {
        for (Map.Entry<String, Simbolo> entry : tablaPadre.variables.entrySet()) {
            Simbolo s = entry.getValue();
            if (s instanceof Variable) {
                Variable v = (Variable) s;
                if (!this.existeVariable(v.idSimbolo.toLowerCase())) {
                    this.AgregarVariable(v.idSimbolo.toLowerCase(), v);
                }
            }
            // AQUI IRIRA LO DE LOS ARREGLOS
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    /**
     * Metodo que comprueba si una funcion existe en el metodo indicado
     *
     * @param key clave a buscar en la tabla
     * @return retorna verdadero o falso dependiendo si existe o no
     */
    public Boolean existeFuncion(Clave key) {
        return this.tablaFunciones.existeFuncionMetodo(key);
    }

    /**
     * Metodo que anande una nueva referencia a una funcion o metodo al ambito
     *
     * @param key clave del metodo o funcion
     * @param fun la referencia
     */
    public void agregarMetodoFuncion(Clave key, MetodoFuncion fun) {
        this.tablaFunciones.agregarMetodoFuncion(key, fun);
    }

    //METODO QUE ME DEVUELVE EL HASH PARA REALIZAR LA TRADUCCION
    public HashMap<Clave, MetodoFuncion> getTablaFunciones() {
        return this.tablaFunciones.funciones;
    }

    public void tomarDatosParaVariables(Ambito ambitoHijo) {
        for (Map.Entry<String, Simbolo> entry : this.tablaVars.variables.entrySet()) {
            ambitoHijo.addDppSimbol(entry.getKey(), entry.getValue());// SI HUBIESE ERROR EN DRACO... ES POR AQUI
        }
    }

    public Boolean existeStruct(String id) {
        return this.Structs.containsKey(id);
    }

    public Struct getStruct(String id) {
        return this.Structs.get(id);
    }

    public HashMap<String, Struct> getStructs() {
        return Structs;
    }

    public void AddStruct(String id, Struct s) {
        this.Structs.put(id, s);
    }
}
