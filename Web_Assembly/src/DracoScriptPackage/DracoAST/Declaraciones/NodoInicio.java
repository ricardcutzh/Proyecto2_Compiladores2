/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Declaraciones;
import Abstraccion.Instruccion;
import Abstraccion.NodoAST;
import ErrorManager.TError;
import InfoEstatica.Estatico;
import ObjsComun.Nulo;
import ObjsComun.Romper;
import Simbolos.Ambito;
import java.util.ArrayList;
/**
 * Clase que representa el inicio de la ejecucion que Guardara el Arbol de instrucciones
 * @author richard
 */
public class NodoInicio implements Instruccion{
    ArrayList<Instruccion> instrucciones;
    /**
     * Constructor del Nodo Principal del Arbol
     * @param instrucciones ArrayList de Instrucciones
     */
    
    public NodoInicio(ArrayList<Instruccion> instrucciones)
    {
        this.instrucciones = instrucciones;
    }
    
    @Override
    public Object Ejecutar(Ambito ambito) {
        try 
        {
            for(Instruccion ins : instrucciones)// OJO QUE HACE FALTA LO DEL DEBUGGER
            {
                NodoAST aux = (NodoAST)ins;
                if(InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE)
                {
                    if(Estatico.esLinea)
                    {
                        //Estatico.MarcaLinea(aux.getLinea());
                        Estatico.MarcarLineaArchivo(ambito.getArchivo(), aux.getLinea());
                        Estatico.suspended = true;
                        Estatico.hilo.suspend();
                    }
                    else
                    {
                        String key = aux.getLinea() + "_" +aux.getArchivo();
                        if(Estatico.breakPoints.containsKey(key))
                        {
                            //Estatico.MarcaLinea(aux.getLinea());
                            Estatico.MarcarLineaArchivo(ambito.getArchivo(), aux.getLinea());
                            Estatico.suspended = true;
                            Estatico.hilo.suspend();
                        }
                    }
                    Object resulado = ins.Ejecutar(ambito);
                    if(resulado instanceof Romper)
                    {
                        Estatico.agregarError(new TError("Smash", "Sentencia Smash no viende dentro de Ciclo", "Semantico", aux.getLinea(), aux.getColumna(), false, ambito.getArchivo()));
                    }
                }
                else
                {
                    Object resulado = ins.Ejecutar(ambito);
                    if(resulado instanceof Romper)
                    {
                        Estatico.agregarError(new TError("Smash", "Sentencia Smash no viende dentro de Ciclo", "Semantico", aux.getLinea(), aux.getColumna(), false, ambito.getArchivo()));
                    }
                }
            }
        } catch (Exception e) {
            TError error = new TError("No Aplica", e.getMessage(), "Ejecucion", 0, 0, false, "");
            InfoEstatica.Estatico.agregarError(error);
        }
        return new Nulo();
    }
    
}
