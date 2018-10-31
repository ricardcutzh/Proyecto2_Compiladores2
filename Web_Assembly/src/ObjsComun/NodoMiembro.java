/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjsComun;

import Abstraccion.NodoAST;
import ErrorManager.TError;
import Simbolos.Ambito;

/**
 *
 * @author richard
 */
public class NodoMiembro extends NodoAST{
    String tipo;
    String identificador;
    int indiceMiembro;
    boolean esArreglo;
    
    public NodoMiembro(int linea, int columna, String Archivo, String tipo, String identificador, int indiceMiembro) {
        super(linea, columna, Archivo);
        this.tipo = tipo;
        this.identificador = identificador;
        this.indiceMiembro = indiceMiembro;
    }

    public String getTipo() {
        return tipo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public int getIndiceMiembro() {
        return indiceMiembro;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            if(esPrimitivo())
            {
                if(!esArreglo)
                {
                    String cad = "// MIEMBRO: "+identificador+" | INDEX: "+indiceMiembro+"\n";
                    cad += "get_global 0 // METO EL PUNTERO ACTUAL DEL HEAP\n";
                    cad += (indiceMiembro-1)+" // INDICE DEL MIEMBRO\n";
                    cad += "ADD // POSICION ABSOULTA EN EL HEAP\n";
                    cad += "0 // VALOR POR DEFECTO\n";
                    cad += "set_global $calc // LO COLOCO EN LA POSICION ABSOLUTA\n";
                    return cad;
                }
                else
                {
                    // ESTO SI FUESE ARREGLO
                }
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(
                        tipo, 
                        "Miembros de una estructura no pueden ser de tipo: "+tipo,
                        "Semantico", 
                        super.getLinea(), 
                        super.getColumna(), 
                        false, 
                        super.getArchivo()
                ));
            }
            
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError(
                    "Miembro: "+identificador
                    , "Error al traducir el miembro: "+e.getMessage()
                    , "Ejecucion"
                    , super.getLinea()
                    , super.getColumna()
                    , Boolean.FALSE
                    , super.getArchivo()));
        }
        return "";
    }
    
    private Boolean esPrimitivo()
    {
        switch(tipo)
        {
            case "CADENA":
            {
                return true;
            }
            case "ENTERO":
            {
                return  true;
            }
            case "DECIMAL":
            {
                return true;
            }
            case "BOOLEAN":
            {
                return true;
            }
            case "CARACTER":
            {
                return true;
            }
            default:{
                return false;
            }
        }
    }
    
}
