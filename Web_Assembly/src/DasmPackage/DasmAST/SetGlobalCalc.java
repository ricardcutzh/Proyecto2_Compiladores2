/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DasmPackage.DasmAST;

import Abstraccion.NodoAST;
import Abstraccion.SentenciaDasm;
import ErrorManager.TError;
import Estructuras.Global.NodoHeap;
import Estructuras.IP;
import Estructuras.NodoPilita;
import Simbolos.EntornoDasm;

/**
 * Clase que maneja la instrucción set_global $calc
 * @author richard
 */
public class SetGlobalCalc extends NodoAST implements SentenciaDasm{
    /**
     * Constructor de la instrucción set_global $calc
     * @param linea
     * @param columna
     * @param Archivo 
     */
    public SetGlobalCalc(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }
    /**
     * Metodo toma el valor del HEAP de acuerdo a valores que se obtiene de
     * la pila auxiliar
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
                entorno.getHeap().addNodoAt(pos.getValor().intValue(), new NodoHeap(val.getValor(), 1, "Valor", Boolean.FALSE, pos.getValor().intValue()));
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(
                    "Posicion o Valor Nulos"
                    , "Error al ejecutar 'set_global "+"$calc"+"': Referencia a valores que no existen"
                    , "Semantico"
                    , super.getLinea()
                    , super.getColumna()
                    , Boolean.FALSE
                    , super.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError(
                    "No Aplica"
                    , "Error al ejecutar 'set_global "+"$calc"+"': "+e.getMessage()
                    , "Ejecucion"
                    , super.getLinea()
                    , super.getColumna()
                    , Boolean.FALSE
                    , super.getArchivo()));
        }
        instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);// AUMENTO DEL IP
        return null;
    }
    
}
