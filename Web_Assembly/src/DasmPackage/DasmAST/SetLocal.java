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
import Estructuras.NodoStack;
import Simbolos.EntornoDasm;

/**
 * Clase que maneja la instruccion set_local pos
 * @author richard
 */
public class SetLocal extends NodoAST implements SentenciaDasm{
    Integer posicion;
    /**
     * Constructor de la instruccion set_local pos
     * @param linea
     * @param columna
     * @param Archivo
     * @param posicion indice donde se desa coloar un dato
     */
    public SetLocal(int linea, int columna, String Archivo, Integer posicion) {
        super(linea, columna, Archivo);
        this.posicion = posicion;
    }
    /**
     * Metodo que inserta un dato en el stack de ambitos de acuerdo a la posicion
     * dada
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
            NodoPilita val = entorno.getPilita().pop();
            if(val !=null)
            {
                entorno.getAmbitos().set_local(new NodoStack(posicion, val.getValor(), 1, "Variable"), posicion);
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError("Pila Auxliliar vacia"
                        , "Error al ejecutar 'set_local "+posicion+"': Valor Nulo"
                        , "Semantico"
                        , super.getLinea()
                        , super.getColumna()
                        , Boolean.FALSE
                        , super.getArchivo()));
            }
            /*Double valor = entorno.getPilita().pop().getValor();
            entorno.getAmbitos().set_local(new NodoStack(posicion, valor, 1, "Variable"), posicion);// COLOCANDO EN STACK EL VALOR INDICADO*/
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejecutar: setlocal: "+e.getMessage()
                    , "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);// AUMENTO DEL IP
        return null;
    }
    
}
