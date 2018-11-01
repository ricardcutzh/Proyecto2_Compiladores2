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
public class ValorNulo extends NodoAST implements Expresion{

    public ValorNulo(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }

    @Override
    public String getTipo(Ambito ambito) {
        return "NULO";
    }

    @Override
    public Object getValor(Ambito ambito) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            return "0";
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError(
                    "No Aplica", 
                    "Error al trducir el valor nulo", 
                    "Ejecucion", 
                    super.getLinea(), 
                    super.getColumna(), 
                    Boolean.FALSE, 
                    super.getArchivo()
            ));
        }
        return "";
    }
    
    
    
}
