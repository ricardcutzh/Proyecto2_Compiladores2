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
 * Instruccion que hace la operacion de resta en la pila
 * @author richard
 */
public class InstDiff extends  NodoAST implements SentenciaDasm{
    
    public InstDiff(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }

    @Override
    public Object Ejecuta(Object objeto) {
        try {
            if (objeto instanceof Pilita) {
                Pilita pilita = (Pilita) objeto;
                NodoPilita valor2 = pilita.pop();
                NodoPilita valor1 = pilita.pop();
                NodoPilita res = new NodoPilita(1, 0, valor1.getValor() - valor2.getValor());
                pilita.push(res);
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("DIFF", "Error al ejecutar DIFF: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        return null;
    }
    
}
