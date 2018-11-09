/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DasmPackage.DasmAST;

import Abstraccion.NodoAST;
import Abstraccion.SentenciaDasm;
import ErrorManager.TError;
import Estructuras.IP;
import Estructuras.NodoPilita;
import Simbolos.EntornoDasm;

/**
 * Clase que maneja la ejecución de la instrucción BR_IF
 * @author richard
 */
public class BrIf extends NodoAST implements SentenciaDasm{
    String etiqueta;
    /**
     * Constructor del Nodo BR_IF
     * @param linea
     * @param columna
     * @param Archivo
     * @param etiqueta 
     */
    public BrIf(int linea, int columna, String Archivo, String etiqueta) {
        super(linea, columna, Archivo);
        this.etiqueta = etiqueta;
    }
    
    /**
     * Mueve el IP a la suguiente instruccion si en la Pila auxiliar 
     * en la cima existe un 0
     * @param entorno
     * @param instrucctionPointer
     * @return 
     */
    @Override
    public Object Ejecuta(EntornoDasm entorno, IP instrucctionPointer) {
        try 
        {
            if(InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE)
            {
                if(InfoEstatica.Estatico.esLinea)
                {
                    InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                    InfoEstatica.Estatico.suspended = true;
                    InfoEstatica.Estatico.ActualizaPilita(entorno.getPilita());
                    InfoEstatica.Estatico.ActualizaStack(entorno.getAmbitos());
                    InfoEstatica.Estatico.ActualizaHeap(entorno.getHeap());
                    InfoEstatica.Estatico.hilo.suspend();
                }
                else
                {
                    String key = super.getLinea() + "_" + super.getArchivo();
                    if(InfoEstatica.Estatico.breakPoints.containsKey(key))
                    {
                        InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                        InfoEstatica.Estatico.suspended = true;
                        InfoEstatica.Estatico.ActualizaPilita(entorno.getPilita());
                        InfoEstatica.Estatico.ActualizaStack(entorno.getAmbitos());
                        InfoEstatica.Estatico.ActualizaHeap(entorno.getHeap());
                        InfoEstatica.Estatico.hilo.suspend();
                    }
                }
            }
            Integer indice = entorno.getIndiceDe(etiqueta);
            if(indice < 0)
            {
                InfoEstatica.Estatico.agregarError(new TError(etiqueta, "Etiqueta no esta declarada, no se logro el salto", "Semantico",
                        super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
            }
            else
            {
                NodoPilita valor = entorno.getPilita().pop();
                if(valor.getValor() == 0.0)
                {
                    instrucctionPointer.setIndiceActual(indice-1);// HACE EL SALTO
                }
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica"
                    , "Error al ejecutar BR_IF: "+e.getMessage()
                    , "Ejecucion"
                    , super.getLinea()
                    , super.getColumna()
                    , Boolean.FALSE
                    , super.getArchivo()));
        }
        instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);
        return null;
    }
    
}
