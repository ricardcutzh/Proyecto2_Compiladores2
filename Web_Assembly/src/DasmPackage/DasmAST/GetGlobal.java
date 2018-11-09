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
 * Clase que maneja la instrucción get_global de DASM   
 * @author richard
 */
public class GetGlobal extends NodoAST implements SentenciaDasm{
    Integer indice;
    /**
     * Constructor del nodo getblobal
     * @param linea
     * @param columna
     * @param Archivo
     * @param indice representa el indice que se quiere obtener del HEAP
     */
    public GetGlobal(int linea, int columna, String Archivo, Integer indice) {
        super(linea, columna, Archivo);
        this.indice = indice;
    }
    /**
     * Metodo que se encarga de obtener un valor del Heap y meterlo a la pila Auxiliar
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
            NodoHeap n = entorno.getHeap().getNodoAt(this.indice);
            if(n != null)
            {
                Double valor = n.getValor();
                entorno.getPilita().push(new NodoPilita(1, 0, valor));
            }
            else 
            {
                InfoEstatica.Estatico.agregarError(new TError(
                        "Nulo en posicion: "+indice
                        , "Referencia a una posicion Nula"
                        , "Semantico"
                        , super.getLinea()
                        , super.getColumna()
                        , Boolean.FALSE
                        , super.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica"
                    , "Error al ejecutar 'get_global "+indice+"': "+e.getMessage()
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
