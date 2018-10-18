/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Sentencias;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import ErrorManager.TError;
import ObjsComun.Clave;
import ObjsComun.NodoParametro;
import Simbolos.Ambito;
import java.util.ArrayList;

/**
 *
 * @author richard
 */
public class LlamadoMetodo extends NodoAST{
    String idMetodo;
    ArrayList<Expresion> parametros;
    /**
     * Nodo del arbol que traduce el llamado a un metodo (NO SE HARA REFERENCIA AL RETURN)
     * @param linea
     * @param columna
     * @param Archivo
     * @param idMetodo
     * @param parametros 
     */
    public LlamadoMetodo(int linea, int columna, String Archivo, String idMetodo, ArrayList<Expresion> parametros) {
        super(linea, columna, Archivo);
        this.idMetodo = idMetodo;
        this.parametros = parametros;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            ArrayList<NodoParametro> nodos = getParams(ambito);
            Clave key = new Clave(idMetodo, nodos);
            Ambito aux = buscarFuncion(ambito);
            if(aux.existeFuncion(key))
            {
                String cadena = "\n/**************************************************************************/\n";
                cadena += "get_local 0 // INICIO LLAMADO\n";
                cadena += (ambito.getSize()-1)+" // SIZE DEL AMBITO PARA AVANZAR\n";
                cadena += "ADD // SUMA PARA MOVERME\n";
                cadena += "set_local 0 // ACTUALIZA EL PUNTERO\n";
                for(int x = 0; x < codigoParametros.size(); x++)
                {
                    cadena += getCadenaParametro(x+1, codigoParametros.get(x), nodos.get(x).getTipo());
                }
                cadena += "\nCall $"+key.toString()+"// LLAMADO DE FUNCION\n";
                cadena += "get_local 0 // REGRESANDO AL AMBITO ANTERIOR\n";
                cadena += (ambito.getSize()-1)+" // SIZE DEL AMBITO PARA REGRESAR\n";
                cadena += "DIFF // RESTAR EL AMBITO\n";
                cadena += "set_local 0 // ACTUALIZA EL PUNTERO\n";
                cadena += "/**************************************************************************/\n";
                return cadena;
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(idMetodo, "Llamado a metodo que no existe", "Semantico"
                        , super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al traducir llamado a Metodo: "+e.getMessage()
                    , "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        return "";
    }
    
    ArrayList<String> codigoParametros;
    private ArrayList<NodoParametro> getParams(Ambito ambito)
    {
        codigoParametros = new ArrayList<>();
        ArrayList<NodoParametro> nodoParametros = new ArrayList<>();
        for(Expresion e : this.parametros)
        {
            NodoAST aux = (NodoAST)e;
            codigoParametros.add((String)aux.generateByteCode(ambito));// GENERA EL CODIGO DE CADA UNA DE LAS EXPRESIONES
            NodoParametro n = new NodoParametro("aux", e.getTipo(ambito), Boolean.FALSE);
            nodoParametros.add(n);
        }
        return nodoParametros;
    }
    
    private Ambito buscarFuncion(Ambito ambito)
    {
        Ambito aux = ambito;
        while(aux.getAnterior()!=null)
        {
            aux = aux.getAnterior();
        }
        return aux;
    }
    
    private String getCadenaParametro(int indice, String expcode, String tipo)
    {
        String cad = "";
        switch(tipo)
        {
            case "CADENA":
            {
                break;
            }
            case "ENTERO":
            {
                cad += "\n/**************************************************************************/\n";
                cad += "// PARAM: "+indice+" de Tipo Entero \n";
                cad += "get_local 0 //PUNTERO DEL STACK\n";
                cad += indice +" // NUMERO DE PARAMETRO A COLOCAR\n";
                cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
                cad += expcode+"\n";
                cad += "set_local $calc // COLOCAR EN LA POSICION QUE LE TOCA\n";
                cad += "/**************************************************************************/\n";
                break;
            }
            case "DECIMAL":
            {
                cad += "\n/**************************************************************************/\n";
                cad += "// PARAM: "+indice+" de Tipo Entero \n";
                cad += "get_local 0 //PUNTERO DEL STACK\n";
                cad += indice +" // NUMERO DE PARAMETRO A COLOCAR\n";
                cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
                cad += expcode+"\n";
                cad += "set_local $calc // COLOCAR EN LA POSICION QUE LE TOCA\n";
                cad += "/**************************************************************************/\n";
                break;
            }
            case "BOOLEAN":
            {
                cad += "\n/**************************************************************************/\n";
                cad += "// PARAM: "+indice+" de Tipo Entero \n";
                cad += "get_local 0 //PUNTERO DEL STACK\n";
                cad += indice +" // NUMERO DE PARAMETRO A COLOCAR\n";
                cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
                cad += expcode+"\n";
                cad += "set_local $calc // COLOCAR EN LA POSICION QUE LE TOCA\n";
                cad += "/**************************************************************************/\n";
                break;
            }
            case "CARACTER":
            {
                cad += "\n/**************************************************************************/\n";
                cad += "// PARAM: "+indice+" de Tipo Entero \n";
                cad += "get_local 0 //PUNTERO DEL STACK\n";
                cad += indice +" // NUMERO DE PARAMETRO A COLOCAR\n";
                cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
                cad += expcode+"\n";
                cad += "set_local $calc // COLOCAR EN LA POSICION QUE LE TOCA\n";
                cad += "/**************************************************************************/\n";
                break;
            }
        }
        return cad;
    }
   
}
