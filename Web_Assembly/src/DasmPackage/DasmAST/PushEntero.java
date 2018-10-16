/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DasmPackage.DasmAST;

import Abstraccion.NodoAST;
import Abstraccion.SentenciaDasm;
import ErrorManager.TError;
import Estructuras.NodoPilita;
import Estructuras.Pilita;

/**
 *
 * @author richard
 */
public class PushEntero extends NodoAST implements SentenciaDasm{
    int valor;
    /**
     * Nodo de la ejecucion de DASM que lo que va a hacer es hacer push al
     * entero indicado
     * @param linea linea de la instruccion
     * @param columna columna de la instruccion
     * @param Archivo Archivo donde se esta ejecutando
     * @param valor valor entero que se va a hacer push a la pilita
     */
    public PushEntero(int linea, int columna, String Archivo, int valor) {
        super(linea, columna, Archivo);
        this.valor = valor;
    }

    @Override
    public Object Ejecuta(Object objeto) {
        try {
            if (objeto instanceof Pilita) {
                Pilita pilita = (Pilita) objeto;
                pilita.push(new NodoPilita(1, 0, Double.valueOf(this.valor))); //EJECUTO EL PUSH ALA PILITA
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("Push: "+this.valor, "Error al ejecutar", "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        return null;
    }
    
}
