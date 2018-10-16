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
 * INstruccion para realizar una suma con elementos de la pila
 * @author richard
 */
public class InstAdd extends NodoAST implements SentenciaDasm{
    
    public InstAdd(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }

    @Override
    public Object Ejecuta(Object objeto) {
        try {
            if (objeto instanceof Pilita) {
                Pilita pilita = (Pilita) objeto;
                NodoPilita valor2 = pilita.pop();
                NodoPilita valor1 = pilita.pop();
                NodoPilita res = new NodoPilita(1, 0, valor1.getValor() + valor2.getValor());
                pilita.push(res);
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("ADD", "Error al ejecutar ADD: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        return null;
    }
    
}
