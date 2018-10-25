/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Sentencias.While;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import ErrorManager.TError;
import Simbolos.Ambito;
import java.util.ArrayList;
/**
 *
 * @author richard
 */
public class Mientras extends NodoAST{
    Expresion condicion;
    ArrayList<Object> sentencias;
    public Mientras(int linea, int columna, String Archivo, Expresion condicion, ArrayList<Object> sentencias) {
        super(linea, columna, Archivo);
        this.condicion = condicion;
        this.sentencias = sentencias;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        InfoEstatica.Estatico.display.PushToDisplay("WHILE", Boolean.TRUE);
        try 
        {
            String cad = "";
            String expcode = (String)((NodoAST)condicion).generateByteCode(ambito);
            String tipo = condicion.getTipo(ambito);
            if(tipo.equals("BOOLEAN"))
            {
                cad += "\n/**************************************************************************/\n";
                cad += "// CONDICION: \n";
                cad += "$"+InfoEstatica.Estatico.display.Peek().toString()+"_CONDICION // ETIQUETA DE CONDICION\n";
                cad += expcode+"\n";
                cad += "BR_IF $"+InfoEstatica.Estatico.display.Peek().toString()+"_FALSO // SI LA CONDICION NO CUMPLE SALE...\n";
                cad += "// SI ES VERDADERO: SENTENCIAS \n";
                String auxiliar = "// SENTENCIAS: \n";
                Ambito ambitoWhile = new Ambito("LOCAL | WHILE", ambito, super.getArchivo());
                ambito.tomarDatosParaVariables(ambitoWhile);
                for(Object o : this.sentencias)
                {
                    if(o instanceof NodoAST)
                    {
                        auxiliar += (String)((NodoAST)o).generateByteCode(ambitoWhile);
                    }
                }
                auxiliar += "\nBR $"+InfoEstatica.Estatico.display.Peek().toString()+"_CONDICION // VUELTA A LA CONDICION \n";
                InfoEstatica.Estatico.tabula();
                cad += InfoEstatica.Estatico.aplicaTabulaciones(auxiliar);
                InfoEstatica.Estatico.destabula();
                cad += "$"+InfoEstatica.Estatico.display.Peek().toString()+"_FALSO\n//SALIDA DE WHILE...\n";
                InfoEstatica.Estatico.display.PopFromDisplay();
                return cad;
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(tipo
                        , "La sentencia Mientras espera una expresion Booleana", "Semantico"
                        , super.getLinea(), super.getColumna()
                        , Boolean.FALSE
                        , super.getArchivo()));
            }
            InfoEstatica.Estatico.display.PopFromDisplay();
            return  cad;
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica"
                    , "Error al traducir sentencia While: "+e.getMessage()
                    , "Ejecucion", super.getLinea(), super.getColumna()
                    , Boolean.FALSE, super.getArchivo()));
        }
        InfoEstatica.Estatico.display.PopFromDisplay();
        return "";
    }
    
}
