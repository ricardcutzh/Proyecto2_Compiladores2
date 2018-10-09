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
 * Nodo del arbol que maneja el pintar una figura en el panel
 * @author richard
 */
public class FuncionString extends NodoAST implements Instruccion{
    Expresion posx, posy, cadena, color;
    /**
     * Constructor de la funcion cadena que pinta en el panel
     * @param linea
     * @param columna
     * @param Archivo
     * @param posx coordenada en x
     * @param posy coordenada en y
     * @param cadena cadena a pintar
     * @param color color que se pintara
     */
    public FuncionString(int linea, int columna, String Archivo, Expresion posx, Expresion posy, Expresion cadena, Expresion color) {
        super(linea, columna, Archivo);
        this.posx = posx;
        this.posy = posy;
        this.cadena = cadena;
        this.color = color;
    }

    @Override
    public Object Ejecutar(Ambito ambito) {
        try {
            Object px = posx.getValor(ambito);
            String tipopx = posx.getTipo(ambito);
            
            Object py = posy.getValor(ambito);
            String tipopy = posy.getTipo(ambito);
            
            Object cad = cadena.getValor(ambito);
            String tipocad = cadena.getTipo(ambito);
            
            Object c = color.getValor(ambito);
            String tipoc = color.getTipo(ambito);
            
            if(tipopx.equals("NUMERICO") && tipopy.equals("NUMERICO") && tipocad.equals("CADENA") && tipoc.equals("CADENA"))
            {
                Conversor conv = new Conversor();
                Double x = conv.convertirADouble(px);
                Double y = conv.convertirADouble(py);
                String cade = (String)cad;
                String col = (String)c;
                InfoEstatica.Estatico.navegador.getPanel().addString(x.intValue(), y.intValue(), col, cade);
            }
            else
            {
                String err = "se encontraron parametros tipo: ("+tipopx+","+tipopy+","+tipocad+","+tipoc+")";
                String mensaje = "se esperaban parametros tipo: (NUMERICO, NUMERICO, CADENA, CADENA)";
                InfoEstatica.Estatico.agregarError(new TError(err, mensaje, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejecutar la funcion String: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
        }
        return new Nulo();
    }
    
}
