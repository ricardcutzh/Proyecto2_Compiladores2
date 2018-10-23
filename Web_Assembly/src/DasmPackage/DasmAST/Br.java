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
 *
 * @author richard
 */
public class Br extends NodoAST implements SentenciaDasm{
    String etiqueta;
    /**
     * 
     * @param linea
     * @param columna
     * @param Archivo
     * @param etiqueta 
     */
    public Br(int linea, int columna, String Archivo, String etiqueta) {
        super(linea, columna, Archivo);
        this.etiqueta = etiqueta;
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
            Integer indice = entorno.getIndiceDe(etiqueta);
            if(indice<0)
            {
                InfoEstatica.Estatico.agregarError(new TError(etiqueta, "Etiqueta no esta declarada, no se logro el salto", "Semantico",
                        super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
                
                instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);
            }
            else
            {
                instrucctionPointer.setIndiceActual(indice-1);// CAMBIO EL PUNTERO PARA LA SIGUIENTE ITERACION
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("Br "+etiqueta, "Error al ejecutar el salto: "+e.getMessage(), "Ejecucion"
                    , super.getLinea(), super.getColumna(),false, super.getArchivo()));
            instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);
        }
        instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);
        return null;
    }
    
}
