/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Sentencias.Nativas;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import ErrorManager.TError;
import Simbolos.Ambito;

/**
 *
 * @author richard
 */
public class Punto extends NodoAST{
    Expresion posx;
    Expresion posy;
    Expresion diametro;
    String color;
    
    public Punto(int linea, int columna, String Archivo, Expresion posx, Expresion posy, String color, Expresion diametro) {
        super(linea, columna, Archivo);
        this.posx = posx;
        this.posy = posy;
        this.diametro = diametro;
        this.color = color;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            /*****************************************************************/
            String xcode = (String)((NodoAST)posx).generateByteCode(ambito);
            String tipox = posx.getTipo(ambito);
            /*****************************************************************/
            String ycode = (String)((NodoAST)posy).generateByteCode(ambito);
            String tipoy = posy.getTipo(ambito);
            /*****************************************************************/
            Integer col = getIntValueOfHex(color);
            /*****************************************************************/
            String dcode = (String)((NodoAST)diametro).generateByteCode(ambito);
            String tipod = diametro.getTipo(ambito);
            /*****************************************************************/
            if((tipox.equals("ENTERO")||tipox.equals("DECIMAL")) && 
                    (tipoy.equals("ENTERO")||tipoy.equals("DECIMAL")) &&  
                    (tipod.equals("ENTERO")||tipod.equals("DECIMAL")))
            {
                String cad = "\n/*********************************************************/\n";
                cad += "// METIENDO EN PILA PARAMETROS DE FUNCION PUNTO\n";
                cad += "// COORDENADA X\n";
                cad += xcode+"\n";
                cad += "// COORDENADA Y\n";
                cad += ycode+"\n";
                cad += "// COLOR QUE SE VA A UTILIZAR\n";
                cad += col+"\n";
                cad += "// DIAMETRO\n";
                cad += dcode+"\n";
                cad += "Call $Point\n";
                cad += "/*********************************************************/\n";
                return cad;
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(
                        "Parametros no corresponden", 
                        "Funcion Punto recibe parametros: (ENTERO, ENTERO, CADENA, ENTERO) | se encontro: ("+tipox+","+tipoy+",CADENA"+","+tipod+")", 
                        "Semantico", 
                        super.getLinea(), 
                        super.getColumna(), 
                        Boolean.FALSE, 
                        super.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError(
                    "No Aplica", 
                    "Error al traducir la funcion nativa de Punto: "+e.getMessage(), 
                    "Ejecucion", 
                    super.getLinea(), 
                    super.getColumna(), 
                    Boolean.FALSE, 
                    super.getArchivo()));
        }
        return "";
    }
    
    
    private Integer getIntValueOfHex(String hex)
    {
        try 
        {
            hex = hex.replace("#", "");
            hex = hex.replace("\"", "");
            return Integer.valueOf(hex, 16);
        } catch (Exception e) 
        {
            System.err.println(e.getMessage());
        }
        return Integer.valueOf("000000", 16);
    }
    
}
