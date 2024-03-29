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
 * Clase que maneja la instruccion get_local entero
 * @author richard
 */
public class GetLocal extends NodoAST implements SentenciaDasm{
    Integer posicion;
    /**
     * Constructor de la instruccion get_local pos
     * @param linea
     * @param columna
     * @param Archivo
     * @param posicion indice que se desea tomar del Stack de Ámbitos
     */
    public GetLocal(int linea, int columna, String Archivo, Integer posicion) {
        super(linea, columna, Archivo);
        this.posicion = posicion;
    }
    /**
     * Metodo que toma la posicion descrita y consulta una posicion del stack 
     * de ámbitos
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
            NodoStack r = entorno.getAmbitos().get_local(posicion);
            if(r != null)
            {
                Double recuperado = r.getValorAlamacenado();
                entorno.getPilita().push(new NodoPilita(1, 0, recuperado));
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(
                        "Nulo en posicion: "+posicion
                        , "Referencia a una posicion Nula"
                        , "Semantico"
                        , super.getLinea()
                        , super.getColumna()
                        , Boolean.FALSE
                        , super.getArchivo()));
            }
            /*Double recuperado = entorno.getAmbitos().get_local(0).getValorAlamacenado();// OBTENGO LA POSICION EN EL STACK DE AMBITOS
            entorno.getPilita().push(new NodoPilita(0, 0, recuperado));*/
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejetucar 'get_local entero': "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);// AUMENTO DEL IP
        return null;
    }
    
}
