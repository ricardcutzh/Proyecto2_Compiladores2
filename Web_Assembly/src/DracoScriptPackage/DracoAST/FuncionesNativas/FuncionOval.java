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
 * Clase que maneja El nodo del arbol que pinta un ovalo
 * @author richard
 */
public class FuncionOval extends NodoAST implements Instruccion{
    Expresion posx, posy, color, alto, ancho;
    /**
     * 
     * @param linea
     * @param columna
     * @param Archivo
     * @param posx posicion en x
     * @param posy posicion en y
     * @param color color con el que se llena la figura
     * @param alto entero representa el alto
     * @param ancho entero que represent al ancho
     */
    public FuncionOval(int linea, int columna, String Archivo, Expresion posx, Expresion posy, Expresion color, Expresion alto, Expresion ancho) {
        super(linea, columna, Archivo);
        this.posx = posx;
        this.posy = posy;
        this.color = color;
        this.alto = alto;
        this.ancho = ancho;
    }

    @Override
    public Object Ejecutar(Ambito ambito) {
        try {
            Object px = posx.getValor(ambito);
            String tipopx = posx.getTipo(ambito);
            
            Object py = posy.getValor(ambito);
            String tipoy = posy.getTipo(ambito);
            
            Object c = color.getValor(ambito);
            String tipoc = color.getTipo(ambito);
            
            Object h = alto.getValor(ambito);
            String tipoh = alto.getTipo(ambito);
            
            Object w = ancho.getValor(ambito);
            Object tipow = ancho.getTipo(ambito);
            
            if( tipopx.equals("NUMERICO") && tipoy.equals("NUMERICO") && tipoc.equals("CADENA") && tipoh.equals("NUMERICO") && tipow.equals("NUMERICO") )
            {
                Conversor conv = new Conversor();
                Double x = conv.convertirADouble(px);
                Double y = conv.convertirADouble(py);
                String color = (String)c;
                Double altura = conv.convertirADouble(h);
                Double ancho = conv.convertirADouble(w);
                InfoEstatica.Estatico.navegador.getPanel().addOval(x.intValue(), y.intValue(), color, ancho.intValue(), altura.intValue());
            }
            else
            {
                String err = "se encontraron parametros: ("+tipopx+","+tipoy+","+tipoc+","+tipoh+","+tipow+")";
                String mensaje = "se esperaban parametro: (NUMERICO, NUMERICO, CADENA, NUMERICO, NUMERICO)";
                InfoEstatica.Estatico.agregarError(new TError(err, mensaje, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejecutar Oval: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
        }
        return new Nulo();
    }
    
}
