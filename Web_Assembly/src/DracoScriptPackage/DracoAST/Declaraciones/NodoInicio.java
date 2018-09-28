/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Declaraciones;
import Abstraccion.Instruccion;
import ErrorManager.TError;
import ObjsComun.Nulo;
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
                ins.Ejecutar(ambito);
            }
        } catch (Exception e) {
            TError error = new TError("No Aplica", e.getMessage(), "Ejecucion", 0, 0, false, "");
        }
        return new Nulo();
    }
    
}
