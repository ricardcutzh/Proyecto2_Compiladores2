/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Valores;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import ErrorManager.TError;
import Simbolos.Ambito;

/**
 *
 * @author richard
 */
public class Ternario extends NodoAST implements Expresion{
    Expresion condicion;
    Expresion verdadero;
    Expresion falso;
    public Ternario(int linea, int columna, String Archivo, Expresion condicion, Expresion verdadero, Expresion falso) {
        super(linea, columna, Archivo);
        this.condicion = condicion;
        this.verdadero = verdadero;
        this.falso = falso;
    }
    
    String tipo;
    @Override
    public String getTipo(Ambito ambito) {
        return tipo;
    }

    @Override
    public Object getValor(Ambito ambito) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try {
            InfoEstatica.Estatico.display.PushToDisplay("TERNARIO", Boolean.FALSE);
            /**********************************************************************/
            String cond = (String)((NodoAST)condicion).generateByteCode(ambito);
            String tipocond = condicion.getTipo(ambito);
            /**********************************************************************/
            String v = (String)((NodoAST)verdadero).generateByteCode(ambito);
            String tipov = verdadero.getTipo(ambito);
            /**********************************************************************/
            String f = (String)((NodoAST)falso).generateByteCode(ambito);
            String tipof = falso.getTipo(ambito);
            /**********************************************************************/
            if(tipocond.equals("BOOLEAN"))
            {
                if(tipof.equals(tipov))
                {
                    this.tipo = tipof;
                    String cad = "/****************** OPERADOR TERNARIO ****************************/\n";
                    cad += "/*CONDICION: */\n";
                    cad += cond+"\n";
                    cad += "BR_IF $"+InfoEstatica.Estatico.display.Peek().toString()+"_VALOR_FALSO\n";
                    cad += "/******* VALOR VERDADERO ************/\n";
                    cad += v+"\n";
                    cad += "BR $"+InfoEstatica.Estatico.display.Peek().toString()+"_SALIR\n";
                    cad += "$"+InfoEstatica.Estatico.display.Peek().toString()+"_VALOR_FALSO\n";
                    cad += "/******  VALOR FALSO ****************/\n";
                    cad += f+"\n";
                    cad += "$"+InfoEstatica.Estatico.display.Peek().toString()+"_SALIR\n";
                    InfoEstatica.Estatico.display.PopFromDisplay();
                    return cad;
                }
                else
                {
                    InfoEstatica.Estatico.agregarError(new TError("Tipo Verdadero: "+tipov+" | Tipo Falso: "+tipof, 
                            "Las expresiones de resultado deben ser del mismo tipo", 
                            "Semantico", 
                            super.getLinea(), 
                            super.getColumna(), 
                            Boolean.FALSE, 
                            super.getArchivo()
                    ));
                }
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(
                        tipocond, 
                        "La condicion para el operador ternario debe de ser una condicion BOOLEANA", 
                        "Semantico", 
                        super.getLinea(), 
                        super.getColumna(), 
                        Boolean.FALSE, 
                        super.getArchivo()
                ));
            }
        } catch (Exception e) {
        }
        InfoEstatica.Estatico.display.PopFromDisplay();
        return "";
    }
    
    
}
