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
public class PushDecimal extends NodoAST implements SentenciaDasm {

    Double valor;

    /**
     * Instruccion que se encargara de meter un valor en la pila
     *
     * @param linea linea de la instruccion
     * @param columna columna de la instruccion
     * @param Archivo archivo donde se mando a llamar
     * @param valor valor double que se insertara en la pila auxiliar
     */
    public PushDecimal(int linea, int columna, String Archivo, Double valor) {
        super(linea, columna, Archivo);
        this.valor = valor;
    }

    @Override
    public Object Ejecuta(Object objeto) {
        try {
            if (objeto instanceof Pilita) {
                Pilita pilita = (Pilita) objeto;
                pilita.push(new NodoPilita(1, 0, this.valor)); //EJECUTO EL PUSH ALA PILITA
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("Push: "+this.valor.toString(), "Error al ejecutar", "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        return null;
    }

}
