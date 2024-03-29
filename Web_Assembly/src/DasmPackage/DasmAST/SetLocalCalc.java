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
 * Clase que maneja la instruccion set_local $calc
 * @author richard
 */
public class SetLocalCalc extends NodoAST implements SentenciaDasm{
    /**
     * Ejecuta el setear en una posicion en stack de ambito
     * @param linea
     * @param columna
     * @param Archivo 
     */
    public SetLocalCalc(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }
    /**
     * Metodo que setea un valor en el stack de ambitos de acuerdo a valores
     * que estan en la pila auxiliar
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
            NodoPilita pos = entorno.getPilita().pop();
            if(val !=null && pos != null)
            {
                entorno.getAmbitos().set_local(new NodoStack(pos.getValor().intValue(), val.getValor(), 1, "Variable"), pos.getValor().intValue());
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(
                    "Posicion o Valor Nulos"
                    , "Error al ejecutar 'set_local "+"$calc"+"': Referencia a valores que no existen"
                    , "Semantico"
                    , super.getLinea()
                    , super.getColumna()
                    , Boolean.FALSE
                    , super.getArchivo()));
            }
            /*Double valor = entorno.getPilita().pop().getValor(); // VALOR QUE VOY A ASIGNAR EN LA POSICION ANTERIOR
            Integer posicion = entorno.getPilita().pop().getValor().intValue(); // EN LA CIMA ESTA LA POSICION DE MEMORIA A COLOCAR
            entorno.getAmbitos().set_local(new NodoStack(posicion, valor, 1, "Variable"), posicion); // ASIGNANDO A LOS AMBITOS LA POSICION */
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejetucar 'set_local $calc': "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);// AUMENTO DEL IP
        return null;
    }
    
}
