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
public class FuncionPoint extends NodoAST implements Instruccion{
    Expresion posx;
    Expresion posy;
    Expresion color;
    Expresion diametro;
    /**
     * Nodo de Funcion de Point para el NodoAST
     * @param linea posicion en linea
     * @param columna posicion en columna
     * @param Archivo Archivo donde se encuentra
     * @param posx Expresion que mantiene la posicion en x
     * @param posy Expresione que mantiene la posicion en y
     * @param color Expresion que mantiene la cadena que representa el color
     * @param diametro Expresion que representa el diametro
     */
    public FuncionPoint(int linea, int columna, String Archivo, Expresion posx, Expresion posy, Expresion color, Expresion diametro) {
        super(linea, columna, Archivo);
        this.posx = posx;
        this.posy = posy;
        this.color = color;
        this.diametro = diametro;   
    }

    @Override
    public Object Ejecutar(Ambito ambito) {
        try {
            Object x = posx.getValor(ambito);
            String tipox = posx.getTipo(ambito);
            
            Object y = posy.getValor(ambito);
            String tipoy = posy.getTipo(ambito);
            
            Object c = color.getValor(ambito);
            String tipoc = color.getTipo(ambito);
            
            Object d = diametro.getValor(ambito);
            String tipod = diametro.getTipo(ambito);
            
            if(tipox.equals("NUMERICO") && tipoy.equals("NUMERICO") && tipoc.equals("CADENA") && tipod.equals("NUMERICO"))
            {
                Conversor conv = new Conversor();
                Double posx = conv.convertirADouble(x);
                Double posy = conv.convertirADouble(y);
                String col = (String)c;
                Double diametro = conv.convertirADouble(d);
                InfoEstatica.Estatico.navegador.getPanel().addPunto(posx.intValue(), posy.intValue(), col, diametro.intValue());
            }
            else
            {
                String mensaje = "La funcion Point espera parametro(NUMERICO, NUMERICO, CADENA, NUMERICO)";
                String err = "Se encontro: ("+tipox+","+tipoy+","+tipoc+","+tipod+")";
                InfoEstatica.Estatico.agregarError(new TError(err, mensaje, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejecutar funcion Point: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(),false, ambito.getArchivo()));
        }
        return new Nulo();
    }
    
}
