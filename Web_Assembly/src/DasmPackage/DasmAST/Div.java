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
 * Clase que se encarga de la instrucción de DIV de DASM    
 * @author richard
 */
public class Div extends NodoAST implements SentenciaDasm{
    /**
     * Constructor del nodo que se encarga de la Division
     * @param linea
     * @param columna
     * @param Archivo 
     */
    public Div(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }
    /**
     * Metodo que toma dos valores de la pila auxiliar y hace si division
     * y mete el resultado en la pila auxiliar
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
            Double respuesta = 0.0;
            try {
                respuesta = valor1.getValor() /  valor2.getValor();
            } catch (Exception e) {
                InfoEstatica.Estatico.agregarError(new TError(valor1.getValor()+"/"+valor2.getValor(), "Division entre 0 invalido!", "Semantico"
                        , super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
            }
            NodoPilita res = new NodoPilita(0, 0, respuesta);
            entorno.getPilita().push(res);
        } catch (Exception e)
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejecutar DIV: "+e.getMessage(), "Ejecucion",
                    super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);// AUMENTO DEL IP
        return null;
    }
    
}
