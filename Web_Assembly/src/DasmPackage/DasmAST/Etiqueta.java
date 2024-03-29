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
import Simbolos.EntornoDasm;

/**
 * Clase que maneja las etiquetas que existen en lenguage DASM 
 * @author richard
 */
public class Etiqueta extends NodoAST implements SentenciaDasm{
    String etiqueta;
    /**
     * El que solo muevoe el IP a la siguiente instruccion
     * @param linea
     * @param columna
     * @param Archivo
     * @param etiqueta  la etiqueta que representa el inicio de un set de Instrucciones
     */
    public Etiqueta(int linea, int columna, String Archivo, String etiqueta) {
        super(linea, columna, Archivo);
        this.etiqueta = etiqueta;
    }

    /**
     * Getter de la etiqueta
     * @return String con el nombre de la etiqueta
     */
    public String getEtiqueta() {
        return etiqueta;
    }
    
    
    /**
     * Método encargado de solo mover el IP  al siguiente instrucción
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
            instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);// AUMENTO DEL IP
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError(etiqueta, "Error al guardar la etiqueta: "+e.getMessage(), "Ejecucion", 
                    super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        return null;
    }
    
}
