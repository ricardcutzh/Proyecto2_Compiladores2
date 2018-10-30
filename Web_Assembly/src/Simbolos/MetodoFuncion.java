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
            String aux = reservaEspacioParametros(ambito);
            insertParams(ambito);// INSERTO LOS PARAMETROS SI HAY SOLO PARA EL CONTADOR AUMENTA
            cad += aux+"\n";
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
    
    private String reservaEspacioParametros(Ambito ambito)
    {
        try {
            String cad = "/*RESERVANDO ESPACIO PARA PARAMETROS*/\n";
            int x = 1;
            for(NodoParametro n : this.parametros)
            {
                cad += "// PARAMETRO: "+x+"\n";
                cad += "get_local 0 \n";
                cad += x+"\n";
                cad += "ADD // SUMA \n";
                cad += 0+" // VALOR TEMPORAL..\n";
                cad += "set_local $calc // guardo el temporal\n";
                x++;
            }
            cad += "/*FIN DE RESERVA DE ESPACIO DE PARAMETROS */";
            
            cad += "// RECUPERANDO PARAMETROS EN PILA\n";
            for(NodoParametro n : this.parametros)
            {
                cad += "set_local $calc\n";
            }
            cad += "// FIN DE RECUPERACION DE PARAMETROS\n";
            return cad;
        } catch (Exception e) {
        }
        return "";
    }
    
}
