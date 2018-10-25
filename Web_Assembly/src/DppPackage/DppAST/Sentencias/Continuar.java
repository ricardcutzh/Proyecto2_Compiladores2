/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Sentencias;

import Abstraccion.NodoAST;
import ErrorManager.TError;
import Estructuras.NodoDisplay;
import Simbolos.Ambito;

/**
 *
 * @author richard
 */
public class Continuar extends NodoAST{
    
    public Continuar(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            if(!InfoEstatica.Estatico.display.estVacio())
            {
                NodoDisplay ciclo = InfoEstatica.Estatico.display.getLastCicle();
                if(ciclo !=null)
                {
                    String cad = "// CONTINUAR... \n";
                    if(ciclo.getTipo().equals("FOR"))
                    {
                        cad += "BR $"+ciclo.toString()+"_CAMBIO // salto a la siguiente iteracion...\n";
                        return cad;
                    }
                    else
                    {
                        cad += "BR $"+ciclo.toString()+"_CONDICION // salto a la condicion para la siguiente iteracion...\n";
                    }
                }
                else
                {
                    InfoEstatica.Estatico.agregarError(new TError("Detener"
                            , "Sentencia Detener no viene dentro de un Ciclo", "Semantico"
                            , super.getLinea()
                            , super.getColumna()
                            , Boolean.FALSE
                            , super.getArchivo()));
                }
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError("Detener", "Sentencia Detener no viene dentro de un Ciclo"
                        , "Semantico", super.getLinea(), super.getColumna()
                        , Boolean.FALSE, super.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(
                    new TError(
                            "No Aplica"
                            ,"Error al traducir el Detener: "+e.getMessage()
                            ,"Ejecucion"
                            ,super.getLinea()
                            ,super.getColumna()
                            ,Boolean.FALSE
                            ,super.getArchivo()
                    )
            );
        }
        return "";
    }
    
    
}
