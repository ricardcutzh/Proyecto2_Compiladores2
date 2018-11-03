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
public class Linea extends NodoAST{
    Expresion x1;
    Expresion y1;
    Expresion x2;
    Expresion y2;
    String color;
    Expresion grosor;
    public Linea(int linea, int columna, String Archivo, Expresion x1, Expresion y1, Expresion x2, Expresion y2, String color, Expresion grosor) {
        super(linea, columna, Archivo);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.grosor = grosor;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            /*****************************************************************/
            String x1code = (String)((NodoAST)x1).generateByteCode(ambito);
            String x1tipo = x1.getTipo(ambito);
            /*****************************************************************/
            String y1code = (String)((NodoAST)y1).generateByteCode(ambito);
            String y1tipo = y1.getTipo(ambito);
            /*****************************************************************/
            String x2code = (String)((NodoAST)x2).generateByteCode(ambito);
            String x2tipo = x2.getTipo(ambito);
            /*****************************************************************/
            String y2code = (String)((NodoAST)y2).generateByteCode(ambito);
            String y2tipo = y2.getTipo(ambito);
            /*****************************************************************/
            Integer col = getIntValueOfHex(color);
            /*****************************************************************/
            String gcode = (String)((NodoAST)grosor).generateByteCode(ambito);
            String gtipo = grosor.getTipo(ambito);
            /*****************************************************************/
            if((x1tipo.equals("ENTERO")|| x1tipo.equals("DECIMAL")) && (x2tipo.equals("ENTERO")||x2tipo.equals("DECIMAL"))
                    && (y1tipo.equals("ENTERO")||y1tipo.equals("DECIMAL")) 
                    && (y2tipo.equals("ENTERO")||y2tipo.equals("DECIMAL")) 
                    && (gtipo.equals("ENTERO")|| gtipo.equals("DECIMAL")))
            {
                String cad = "\n/*********************************************************/\n";
                cad += "// METIENDO EN PILA PARAMETROS DE FUNCION LINEA\n";
                cad += "// COORDENADA X1\n";
                cad += x1code+"\n";
                cad += "// COORDENADA Y1\n";
                cad += y1code+"\n";
                cad += "// COORDENADA X2\n";
                cad += x2code+"\n";
                cad += "// COORDENADA Y2\n";
                cad += y2code+"\n";
                cad += "// COLOR\n";
                cad += col+"\n";
                cad += "// GROSOR\n";
                cad += gcode+"\n";
                cad += "Call $Line\n";
                cad += "/*********************************************************/\n";
                // DETENIENDO DEBUG
                if (InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE) {
                    if (InfoEstatica.Estatico.esLinea) {
                        InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                        InfoEstatica.Estatico.suspended = true;
                        InfoEstatica.Estatico.OutPutCode.setText(cad);
                        InfoEstatica.Estatico.hilo.suspend();
                    } else {
                        String key1 = super.getLinea() + "_" + super.getArchivo();
                        if (InfoEstatica.Estatico.breakPoints.containsKey(key1)) {
                            InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                            InfoEstatica.Estatico.suspended = true;
                            InfoEstatica.Estatico.OutPutCode.setText(cad);
                            InfoEstatica.Estatico.hilo.suspend();
                        }
                    }
                }
                return  cad;
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError("Parametros no Corresponden", 
                    "Funcion Punto recibe parametros: (ENTERO, ENTERO, ENTERO, ENTERO, CADENA, ENTERO) | se encontro: ("+x1tipo+","+y1tipo+","+x2tipo+","+y2tipo+",CADENA,"+gtipo+")" , 
                    "Ejecucion", 
                    super.getLinea(), 
                    super.getColumna(), 
                    Boolean.FALSE, 
                    super.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", 
                    "Error al traducir la funcion de Linea: "+e.getMessage(), 
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
