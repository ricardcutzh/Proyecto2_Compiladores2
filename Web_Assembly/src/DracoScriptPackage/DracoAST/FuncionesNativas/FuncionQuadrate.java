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
 *
 * @author richard
 */
public class FuncionQuadrate extends NodoAST implements Instruccion{
    
    Expresion posx, posy, color, alto, ancho;
    /**
     * Constructor de Funcion de Quadrate
     * @param linea
     * @param columna
     * @param Archivo
     * @param posx posicion en x
     * @param posy posicion en y
     * @param color color que se desea
     * @param alto alto del cuadrado
     * @param ancho ancho del cuadrado
     */
    public FuncionQuadrate(int linea, int columna, String Archivo, Expresion posx, Expresion posy, Expresion color, Expresion alto, Expresion ancho) {
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
            Object x = posx.getValor(ambito);
            String tipox = posx.getTipo(ambito);
            
            Object y = posy.getValor(ambito);
            String tipoy = posy.getTipo(ambito);
            
            Object col = color.getValor(ambito);
            String tipocol = color.getTipo(ambito);
            
            Object height = alto.getValor(ambito);
            String heighttipo = alto.getTipo(ambito);
            
            Object width = ancho.getValor(ambito);
            String widthtipo = ancho.getTipo(ambito);
            
            if(tipox.equals("NUMERICO") && tipoy.equals("NUMERICO") && tipocol.equals("CADENA") && heighttipo.equals("NUMERICO") && widthtipo.equals("NUMERICO"))
            {
                Conversor c  = new Conversor();
                Double X = c.convertirADouble(x);
                Double Y = c.convertirADouble(y);
                String colo = (String)col;
                Double altura = c.convertirADouble(height);
                Double ancho = c.convertirADouble(width);
                InfoEstatica.Estatico.navegador.getPanel().addCuadrado(X.intValue(), Y.intValue(), colo, ancho.intValue(), altura.intValue());
            }
            else
            {
                String mer = "se encontro parametros: ("+tipox+","+tipoy+","+tipocol+","+heighttipo+","+widthtipo+")";
                String mensaje = "Se esperaban parametros (NUMERICO, NUMERICO, CADENA, NUMERICO, NUMERICO) para funcion Quadrate";
                InfoEstatica.Estatico.agregarError(new TError(mer, mensaje, "Semantico", super.getLinea(), super.getColumna(), false,ambito.getArchivo()));
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejecutar funcion Quadrate: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
        }
        return new Nulo();
    }
    
}
