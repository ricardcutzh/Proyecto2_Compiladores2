/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

import Abstraccion.NodoAST;
import ErrorManager.TError;
import Estructuras.Display;
import ObjsComun.NodoParametro;
import java.util.ArrayList;

/**
 * Clase que representa las referencias a las funciones que deben existir 
 * para su traduccion
 * @author richard
 */
public class MetodoFuncion extends  NodoAST{
    ArrayList<Object> sentencias;
    ArrayList<NodoParametro> parametros;
    String id;
    String tipo;
    
    
    public MetodoFuncion(int linea, int col, String archivo, String id, String tipo, ArrayList<NodoParametro> parametros, ArrayList<Object> sentencias) {
        super(linea, col, archivo); 
        this.id = id;
        this.tipo = tipo;
        this.parametros = parametros;
        this.sentencias = sentencias;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try {
            InfoEstatica.Estatico.display = new Display();
            String cad = "";
            insertParams(ambito);// INSERTO LOS PARAMETROS SI HAY SOLO PARA EL CONTADOR AUMENTA
            for(Object o : sentencias)
            {
                if(o instanceof NodoAST)
                {
                    cad += ((NodoAST)o).generateByteCode(ambito);
                }
            }
            InfoEstatica.Estatico.AgregarTablaSimbolos(new SimboloTabla(id, Boolean.FALSE, tipo, "Global" + " | "+id, super.getLinea()
                        , super.getColumna(), ambito.getSize()-1,"Metodo - Funcion"));
            return cad;
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al traducir Metodo | Funcion: "+id+" | "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        return "";
    }
    
    public void insertParams(Ambito ambito)
    {
        for(NodoParametro n : this.parametros)
        {
            ambito.addDppSimbol(n.getIdParametro(), new DppVar(n.getIdParametro(), Boolean.FALSE, n.getTipo(), ambito.getSize(), 1, ambito.getIdAmbito()));
            InfoEstatica.Estatico.AgregarTablaSimbolos(new SimboloTabla(n.getIdParametro(), Boolean.FALSE, n.getTipo(), "Local | "+id
                    , super.getLinea(), super.getColumna(), 1, "Parametro"));
        }
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }
    
    
}
