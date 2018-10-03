/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Sentencias;
import Abstraccion.*;
import ErrorManager.TError;
import ObjsComun.Nulo;
import Simbolos.Ambito;
import Simbolos.Variable;
/**
 * Clase que maneja la instruccion de decremento
 * @author richard
 */
public class SentDec extends NodoAST implements Instruccion{
    String identificador;
    /**
     * Constructor del Nodo de Decremento
     * @param linea 
     * @param columna
     * @param Archivo 
     * @param identificador
     */
    public SentDec(int linea, int columna, String Archivo, String identificador) {
        super(linea, columna, Archivo);
        this.identificador = identificador;
    }

    @Override
    public Object Ejecutar(Ambito ambito) {
        try {
            Simbolos.Simbolo s = ambito.getSimbolo(identificador.toLowerCase());
            if(s!=null)
            {
                Variable v = (Variable)s;
                if(v.getTipo().equals("NUMERICO"))
                {
                    Double val = 0.0;
                    if(v.getValor() instanceof Integer)
                    {
                        val = Double.valueOf((Integer)v.getValor());
                    }
                    else
                    {
                        val = (Double)v.getValor();
                    }
                    val = val - 1.0;
                    v.setValor(val);
                }
                else
                {
                    InfoEstatica.Estatico.agregarError(new TError(identificador, "Se espera un simbolo de tipo: (NUMERICO) y se encontro: ("+v.getTipo()+")", "Semantico", super.getLinea(), super.getColumna(), false,ambito.getArchivo()));
                }
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(identificador, "Simbolo No existe en este contexto", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejecutar decremento: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
        }
        return new Nulo();
    }
    
}
