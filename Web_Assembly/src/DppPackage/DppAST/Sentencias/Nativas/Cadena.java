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
public class Cadena extends NodoAST {

    Expresion posx;
    Expresion posy;
    String color;
    Expresion cadena;

    public Cadena(int linea, int columna, String Archivo, Expresion posx, Expresion posy, String color, Expresion cadena) {
        super(linea, columna, Archivo);
        this.posx = posx;
        this.posy = posy;
        this.color = color;
        this.cadena = cadena;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try {
            /**
             * **************************************************************
             */
            String xcode = (String) ((NodoAST) posx).generateByteCode(ambito);
            String tipox = posx.getTipo(ambito);
            /**
             * **************************************************************
             */
            String ycode = (String) ((NodoAST) posy).generateByteCode(ambito);
            String tipoy = posy.getTipo(ambito);
            /**
             * **************************************************************
             */
            Integer col = getIntValueOfHex(color);
            /**
             * **************************************************************
             */
            String cadcode = (String) ((NodoAST) cadena).generateByteCode(ambito);
            String cadTipo = cadena.getTipo(ambito);
            /**
             * **************************************************************
             */
            if ((tipox.equals("ENTERO") || tipox.equals("DECIMAL")) && (tipoy.equals("ENTERO") || tipoy.equals("DECIMAL")) && cadTipo.equals("CADENA")) {
                String cad = "\n/*********************************************************/\n";
                cad += "// METIENDO EN PILA PARAMETROS DE FUNCION PUNTO\n";
                cad += "// COORDENADA X\n";
                cad += xcode + "\n";
                cad += "// COORDENADA Y\n";
                cad += ycode + "\n";
                cad += "// COLOR QUE SE VA A UTILIZAR\n";
                cad += col + "\n";
                cad += "// CADENA A ESCRIBIR\n";
                cad += cadcode + "\n";
                cad += "Call $String\n";
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
                return cad;
            } else {
                InfoEstatica.Estatico.agregarError(new TError(
                        "Parametros no corresponden",
                        "Funcion Punto recibe parametros: (ENTERO, ENTERO, CADENA, CADENA) | se encontro: (" + tipox + "," + tipoy + ",CADENA" + "," + cadTipo + ")",
                        "Semantico",
                        super.getLinea(),
                        super.getColumna(),
                        Boolean.FALSE,
                        super.getArchivo()));
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError(
                    "No Aplica",
                    "Error al traducir Funcion de Linea: " + e.getMessage(),
                    "Ejecucion",
                    super.getLinea(),
                    super.getColumna(),
                    Boolean.FALSE,
                    super.getArchivo()
            ));
        }
        return "";
    }

    private Integer getIntValueOfHex(String hex) {
        try {
            hex = hex.replace("#", "");
            hex = hex.replace("\"", "");
            return Integer.valueOf(hex, 16);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return Integer.valueOf("000000", 16);
    }
}
