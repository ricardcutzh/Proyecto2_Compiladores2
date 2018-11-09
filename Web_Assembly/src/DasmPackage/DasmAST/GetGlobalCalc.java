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
 * Clase que maneja la instrucción Get_global de DASM   
 * @author richard
 */
public class GetGlobalCalc extends NodoAST implements SentenciaDasm {
    /**
     * Constructor del nodo get_global $calc
     * @param linea
     * @param columna
     * @param Archivo 
     */
    public GetGlobalCalc(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }
    /**
     * Metodo que toma un valor de la pila auxiliar (representa la posicion)
     * y luego basado en el valor anterior toma un valor que existe en el HEAP
     * @param entorno
     * @param instrucctionPointer
     * @return 
     */
    @Override
    public Object Ejecuta(EntornoDasm entorno, IP instrucctionPointer) {
        try {
            if (InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE) {
                if (InfoEstatica.Estatico.esLinea) {
                    InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                    InfoEstatica.Estatico.suspended = true;
                    InfoEstatica.Estatico.ActualizaPilita(entorno.getPilita());
                    InfoEstatica.Estatico.ActualizaStack(entorno.getAmbitos());
                    InfoEstatica.Estatico.ActualizaHeap(entorno.getHeap());
                    InfoEstatica.Estatico.hilo.suspend();
                } else {
                    String key = super.getLinea() + "_" + super.getArchivo();
                    if (InfoEstatica.Estatico.breakPoints.containsKey(key)) {
                        InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                        InfoEstatica.Estatico.suspended = true;
                        InfoEstatica.Estatico.ActualizaPilita(entorno.getPilita());
                        InfoEstatica.Estatico.ActualizaStack(entorno.getAmbitos());
                        InfoEstatica.Estatico.ActualizaHeap(entorno.getHeap());
                        InfoEstatica.Estatico.hilo.suspend();
                    }
                }
            }

            NodoPilita pos = entorno.getPilita().pop();
            if (pos != null) {
                NodoHeap val = entorno.getHeap().getNodoAt(pos.getValor().intValue());
                if (val != null) {
                    entorno.getPilita().push(new NodoPilita(1, 0, val.getValor()));
                } else {
                    InfoEstatica.Estatico.agregarError(new TError("get_global $calc (Posicion Heap: " + pos.getValor().intValue() + ")",
                             "La posicion a la que se hace referencia no existe: Nulo",
                             "Semantico",
                             super.getLinea(),
                             super.getColumna(),
                             Boolean.FALSE,
                             super.getArchivo()));
                }
            } else {
                InfoEstatica.Estatico.agregarError(new TError("get_global $calc",
                         "No hay datos en La Pila Auxiliar",
                         "Semantico",
                         super.getLinea(),
                         super.getColumna(),
                         Boolean.FALSE,
                         super.getArchivo()));
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError(
                    "No Aplica",
                     "Error al ejecutar 'get_global $calc': " + e.getMessage(),
                     "Ejecucion",
                     super.getLinea(),
                     super.getColumna(),
                     Boolean.FALSE,
                     super.getArchivo()));
        }
        instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);// AUMENTO DEL IP
        return null;
    }

}
