/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Valores;

/**
 * Clase comodin para manjer los tipos de maneja centralizada
 * @author richard
 */
public class ManejadorTipo {
    Object valor;
    
    public ManejadorTipo(Object valor)
    {
        this.valor = valor;
    }
    
    public String evaluaValor()
    {
        if(this.valor instanceof Integer)
        {
            return "ENTERO";
        }
        if(this.valor instanceof String)
        {
            return "CADENA";
        }
        if(this.valor instanceof Double)
        {
            return "DECIMAL";
        }
        if(this.valor instanceof Boolean)
        {
            return "BOOLEAN";
        }
        if(this.valor instanceof Character)
        {
            return "CARACTER";
        }
        return null;
    }
}
