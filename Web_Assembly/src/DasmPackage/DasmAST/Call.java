/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DasmPackage.DasmAST;

import Abstraccion.NodoAST;
import Abstraccion.SentenciaDasm;
import ErrorManager.TError;
import Estructuras.FuncionDasm;
import Estructuras.IP;
import Simbolos.EntornoDasm;

/**
 *
 * @author richard
 */
public class Call extends NodoAST implements SentenciaDasm{
    String id;
    /**
     * Instruccion que se encarga del llamado de las funciones en DASM
     * @param linea
     * @param columna
     * @param Archivo
     * @param id 
     */
    public Call(int linea, int columna, String Archivo, String id) {
        super(linea, columna, Archivo);
        this.id  = id;
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
                    InfoEstatica.Estatico.hilo.suspend();
                }
                else
                {
                    String key = super.getLinea() + "_" + super.getArchivo();
                    if(InfoEstatica.Estatico.breakPoints.containsKey(key))
                    {
                        InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                        InfoEstatica.Estatico.suspended = true;
                        InfoEstatica.Estatico.hilo.suspend();
                    }
                }
            }
            FuncionDasm f = entorno.getGestor().getFuncion(id);
            if(f!=null) 
            {
                f.Ejecuta(entorno, null);// LE MANDO NULL PORQUE TOMARE EL INSTRUCCTION POINTER DE LA FUNCION QUE SE EJECUTA
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(id, "Funcion no esta definida: "+id, "Semantico", 
                        super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
            }
            
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejecutar llamado: "+e.getMessage(), "Ejecucion"
                    , super.getLinea(),super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);// AUMENTO DEL IP
        return null;
    }
    
}
