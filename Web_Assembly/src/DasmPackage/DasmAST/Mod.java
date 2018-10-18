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
public class Mod extends NodoAST implements SentenciaDasm{
    /**
     * Clase que se encarga de realizar el modulo en la pilita
     * @param linea
     * @param columna
     * @param Archivo 
     */
    public Mod(int linea, int columna, String Archivo) {
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
            NodoPilita valor2 = entorno.getPilita().pop();
            NodoPilita valor1 = entorno.getPilita().pop();
            Double respuesta = 0.0;
            try {
                respuesta = valor1.getValor() % valor2.getValor();
            } catch (Exception e) {
                InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error! en division y obtener el resto!", "Semantico"
                        , super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
            }
            entorno.getPilita().push(new NodoPilita(0, 0, respuesta));
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejecutar Mod: "+e.getMessage(), "Ejecucion"
                    , super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);// AUMENTO DEL IP
        return null;
    }
    
}
