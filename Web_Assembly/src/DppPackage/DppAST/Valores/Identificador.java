/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Valores;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import ErrorManager.TError;
import Simbolos.Ambito;
import Simbolos.DppVar;
import Simbolos.Simbolo;

/**
 * Clase que maneja los identificadores como expresiones
 * @author richard
 */
public class Identificador extends NodoAST implements Expresion{
    String identificador;
    /**
     * Constructor del nodo
     * @param linea
     * @param columna
     * @param Archivo
     * @param id 
     */
    public Identificador(int linea, int columna, String Archivo, String id) {
        super(linea, columna, Archivo);
        this.identificador = id;
    }
    String tipo = "";
    @Override
    public String getTipo(Ambito ambito) {
        if(tipo.equals(""))
        {
            generateByteCode(ambito);
        }
        return tipo;
    }

    @Override
    public Object getValor(Ambito ambito) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            Simbolo s = ambito.getSimbolo(identificador);
            if(s!=null)
            {
               
                this.tipo = s.getTipo();
                if(s instanceof DppVar)
                {
                    DppVar simbolo = (DppVar)s;
                    String cad = simbolo.getAmbito().equals("Global")?"\nget_global 0 // OBTENIENDO PUNTERO\n":"\nget_local 0 // OBTENIENDO PUNTERO STACK\n";
                    cad += simbolo.getPosicionRelativa() +" // POSICION DE LA VARIABLE\n";
                    cad += "ADD // SUMA PARA ENCONTRAR SU POSICION REAL\n";
                    cad += simbolo.getAmbito().equals("Global")?"get_global $calc //OBTENIENDO EL VALOR \n":"get_local $calc // OBTENIENDO EL VALOR DE VARIABLE\n";
                    return cad;
                }
                else
                {
                    // SI HACE REFERENCIA A UNA ESTRUCTURA O ARREGLO
                }
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError("Simbolo: "+identificador,"Simbolo no existe en este ambito", "Semantico", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error de Traduccion del identificador: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        return "";
    }
    
    
}
