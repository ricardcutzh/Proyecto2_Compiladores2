/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DasmPackage;

import Estructuras.FuncionDasm;
import Estructuras.GestorFunciones;
import Simbolos.EntornoDasm;

/**
 *
 * @author richard
 */
public class DasmExe {
    EntornoDasm entorno;
    /**
     * El encargado de ejecutar la ejecucion del dasm
     * @param gestor 
     */
    public DasmExe(GestorFunciones gestor)
    {
        entorno = new EntornoDasm(gestor);
    }
    
    
    public void iniciarDasm()
    {
        try 
        {
            FuncionDasm inicio = entorno.getGestor().getFuncion("$r_global");
            if(inicio!=null)
            {
                inicio.Ejecuta(entorno, null);
            }
            //entorno.getPilita().printPilita();
            //entorno.getAmbitos().printStack();
        } catch (Exception e) 
        {
            System.err.println(e.getMessage());
        }
    }
}
