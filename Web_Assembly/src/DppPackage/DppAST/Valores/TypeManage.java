/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Valores;

/**
 * Manejador de tipos para D++
 * @author richard
 */
public class TypeManage {
    Object valor;
    
    public TypeManage(Object valor)
    {
        this.valor = valor;
    }
    
    public String evaluaValor()
    {
        if(this.valor instanceof Boolean)
        {
            return "BOOLEAN";
        }
        if(this.valor instanceof String)
        {
            return "CADENA";
        }
        if(this.valor instanceof Integer)
        {
            return "ENTERO";
        }
        if(this.valor instanceof Double)
        {
            return "DECIMAL";
        }
        if(this.valor instanceof Character)
        {
            return "CARACTER";
        }
        // FALTA SABER SI ES UNA ESTRUCTURA
        // SI ES UN ARREGLO
        return "";
    }
}
