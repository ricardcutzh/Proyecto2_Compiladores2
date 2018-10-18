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
 *
 * @author richard
 */
public class GetLocalCalc extends NodoAST implements SentenciaDasm{
    
    /**
     * Instruccion que va a obtener un valor de la Stack de ambitos
     * @param linea
     * @param columna
     * @param Archivo 
     */
    public GetLocalCalc(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }

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
                        InfoEstatica.Estatico.hilo.suspend();
                    }
                }
            }
            Integer posicion = entorno.getPilita().pop().getValor().intValue();// POSICION DE MEMORIA A STACK ---> EN LA CIMA DE LA PILA
            Double valor = entorno.getAmbitos().get_local(posicion).getValorAlamacenado(); // OBTENGO EL VALOR EN LA PILA DE STACK
            entorno.getPilita().push(new NodoPilita(0, 0, valor)); // LO SETEO EN LA CIMA DE LA PILA
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejecutar 'get_local $calc'", "Ejecucion"
                    , super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);// AUMENTO DEL IP
        return null;
    }
    
}
