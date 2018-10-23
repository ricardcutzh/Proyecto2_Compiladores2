/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Sentencias.IF;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import ErrorManager.TError;
import Simbolos.Ambito;
import java.util.ArrayList;

/**
 * Clase de nodo que traduce el if simple
 * @author richard
 */
public class IfSimple extends NodoAST{
    Expresion condicion;
    ArrayList<Object> sentencias;
    /**
     * Nodo que traduce el if simple sin ningun otra condicion
     * @param linea
     * @param columna
     * @param Archivo
     * @param condicion
     * @param sentencias 
     */
    public IfSimple(int linea, int columna, String Archivo, Expresion condicion, ArrayList<Object> sentencias) {
        super(linea, columna, Archivo);
        this.condicion = condicion;
        this.sentencias = sentencias;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        InfoEstatica.Estatico.display.PushToDisplay("IF", false);// METO A PILA
        try 
        {
            String expCode = (String)((NodoAST)condicion).generateByteCode(ambito);// GENERO EL CODIGO RELACIONADO A LA EXPRESION
            String tipo = condicion.getTipo(ambito);
            if(tipo.equals("BOOLEAN"))
            {
                String cad = "\n/**************************************************************************/\n";
                cad += "//*           IF_SIMPLE\n";
                cad += "/**************************************************************************/\n";
                cad += "// CONDICION\n";
                cad += expCode+"\n";
                cad += "// FIN CONDICION\n";
                cad += "BR_IF $"+InfoEstatica.Estatico.display.Peek().toString()+"_FALSO // SI LA CONDICION ES FALSA...\n";
                InfoEstatica.Estatico.tabula();
                String auxiliar = "\n//---------- SENTENCIAS ----------\n";
                // CREO EL NUEVO AMBITO
                Ambito ambitoIf = new Ambito("Local | IF", ambito, super.getArchivo());
                ambito.tomarDatosParaVariables(ambitoIf);
                for(Object o : sentencias)
                {
                    // FALTA GENERAR EL NUEVO AMBITO
                    auxiliar += (String)((NodoAST)o).generateByteCode(ambitoIf);
                }
                auxiliar += "\n//---------- FIN SENTENCIAS ----------\n";
                cad += InfoEstatica.Estatico.aplicaTabulaciones(auxiliar);
                InfoEstatica.Estatico.destabula();
                cad += "$"+InfoEstatica.Estatico.display.Peek().toString()+"_FALSO // ETIQUETA DE NO CUMPLIR LA CONDICION\n";
                cad += "/**************************************************************************/\n";
                InfoEstatica.Estatico.display.PopFromDisplay();// SACO DEL DISPLAY
                return  cad;
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(tipo, "La sentencia 'SI' espera una expresion que retorne BOOLEAN"
                        , "Semantico", super.getLinea()
                        , super.getColumna()
                        , Boolean.FALSE
                        , super.getArchivo()));
            }
        } 
        catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al traducir el 'SI Simple': "+e.getMessage()
                    , "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        InfoEstatica.Estatico.display.PopFromDisplay();// SACO DEL DISPLAY
        return "";
    }
    
    
    
}
