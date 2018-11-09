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
 * <h1> Clase que se encarga de la instruccion ADD para DASM </h1>
 * @author richard
 */
public class Add extends NodoAST implements SentenciaDasm{
    /**
     * Constructor que opera datos sobre la pila
     * @param linea
     * @param columna
     * @param Archivo 
     */
    public Add(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }
    
    /**
     * Ejecuta la instruccion de suma haciendo Pop 2 veces, sumando el resultado y metiendo
     * de nuevo a la pila auxiliar
     * @param entorno <h3> Objeto que contiene la Pila Auxiliar, el Heap y el Stack de Ambitos </h3>
     * @param instrucctionPointer Puntero de instrucciones
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
            NodoPilita valor2 = entorno.getPilita().pop();
            NodoPilita valor1 = entorno.getPilita().pop();
            NodoPilita res = new NodoPilita(0, 0, valor1.getValor()+valor2.getValor());
            entorno.getPilita().push(res);
            
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejecutar ADD: "+e.getMessage(), "Ejecucion", 
                    super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);// AUMENTO DEL IP
        return null;
    }
    
}
