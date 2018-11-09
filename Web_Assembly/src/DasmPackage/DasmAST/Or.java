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
 * Clase que maneja la intruccion OR en DASM
 * @author richard
 */
public class Or extends NodoAST implements SentenciaDasm{
    /**
     * Constructor de la intrucción NOT
     * @param linea
     * @param columna
     * @param Archivo 
     */
    public Or(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }
    /**
     * Toma dos valores de la pila auxiliar y luego hace la operacion
     * bit a bit e inserta el resultado en la pila auxiliar
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
            NodoPilita valor2 = entorno.getPilita().pop();
            NodoPilita valor1 = entorno.getPilita().pop();
            Double respuesta = Double.valueOf(valor1.getValor().intValue() | valor2.getValor().intValue());
            entorno.getPilita().push(new NodoPilita(1, 0, respuesta));
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError(
                    "No Aplica", 
                    "Error al ejecutar intruccion OR: "+e.getMessage(), 
                    "Ejecucion", 
                    super.getLinea(), 
                    super.getColumna(), 
                    Boolean.FALSE, 
                    super.getArchivo()
            ));
        }
        instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);// AUMENTO DEL IP
        return null;
    }
    
}
