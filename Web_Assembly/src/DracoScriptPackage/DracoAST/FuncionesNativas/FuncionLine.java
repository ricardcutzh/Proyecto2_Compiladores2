/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.FuncionesNativas;
import Abstraccion.*;
import DracoScriptPackage.DracoAST.Valores.Conversor;
import ErrorManager.TError;
import ObjsComun.Nulo;
import Simbolos.Ambito;
/**
 * Nodo que manje la funcion que dibuja una linea
 * @author richard
 */
public class FuncionLine extends NodoAST implements Instruccion{
    Expresion x1, y1, x2, y2, color, grosor;
    /**
     * Metodo constructor de la funcion de linea
     * @param linea
     * @param columna
     * @param Archivo
     * @param x1 coordenada inicial x
     * @param y1 coordenana y inicial
     * @param x2 coordenada final x
     * @param y2 coordenada final y
     * @param color color de la linea
     * @param grosor grosor de la linea
     */
    public FuncionLine(int linea, int columna, String Archivo, Expresion x1, Expresion y1, Expresion x2, Expresion y2, Expresion color, Expresion grosor) {
        super(linea, columna, Archivo);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.grosor = grosor;
    }

    @Override
    public Object Ejecutar(Ambito ambito) {
        try {
            Object xini = x1.getValor(ambito);
            String tipoxini = x1.getTipo(ambito);
            
            Object yini = y1.getValor(ambito);
            String tipoyini = y1.getTipo(ambito);
            
            Object xfini = x2.getValor(ambito);
            String tipoxfini = x2.getTipo(ambito);
            
            Object yfini = y2.getValor(ambito);
            String tipoyfini = y2.getTipo(ambito);
            
            Object c = color.getValor(ambito);
            String tipoc = color.getTipo(ambito);
            
            Object g = grosor.getValor(ambito);
            String tipog = grosor.getTipo(ambito);
            
            if(tipoxini.equals("NUMERICO") && tipoyini.equals("NUMERICO") && tipoxfini.equals("NUMERICO") && tipoyfini.equals("NUMERICO") && tipoc.equals("CADENA") && tipog.equals("NUMERICO"))
            {
                Conversor conv = new Conversor();
                Double xi1 = conv.convertirADouble(xini);
                Double yi1 = conv.convertirADouble(yini);
                Double xf2 = conv.convertirADouble(xfini);
                Double yf2 = conv.convertirADouble(yfini);
                Double gro = conv.convertirADouble(g);
                String col = (String)c;
                InfoEstatica.Estatico.navegador.getPanel().addLinea(xi1.intValue(), yi1.intValue(), xf2.intValue(), yf2.intValue(), col, gro.intValue());
            }
            else
            {
                String error = "se encontraron parametros: ("+tipoxini+","+tipoyini+","+tipoxfini+","+tipoyfini+","+tipoc+","+tipog+")";
                String mensaje = "se esperaban parametros: (NUMERICO, NUMERICO, NUMERICO, NUMERICO, CADENA, NUMERICO)";
                InfoEstatica.Estatico.agregarError(new TError(error, mensaje, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No aplica", "Error al ejecutar Line: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
        }
        return new Nulo();
    }
    
}
