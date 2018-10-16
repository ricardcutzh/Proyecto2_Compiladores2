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
public class InstDiv extends NodoAST implements SentenciaDasm{
    
    public InstDiv(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }

    @Override
    public Object Ejecuta(Object objeto) {
        try {
            if (objeto instanceof Pilita) {
                Pilita pilita = (Pilita) objeto;
                NodoPilita valor2 = pilita.pop();
                NodoPilita valor1 = pilita.pop();
                NodoPilita res = new NodoPilita(1, 0, 0.0);
                try {
                    res = new NodoPilita(1, 0, valor1.getValor() / valor2.getValor());
                } catch (Exception e) {
                    InfoEstatica.Estatico.agregarError(new TError("Division enter 0", "La division entre 0 provoco error", "Semantico", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
                }
                pilita.push(res);
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("DIV", "Error al ejecutar DIV: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        return null;
    }
    
}
