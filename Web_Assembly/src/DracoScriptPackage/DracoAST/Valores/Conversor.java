/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Valores;

/**
 * Clase auxiliar para convertir Objetos numericos a Double
 * @author richard
 */
public class Conversor {
    
    public Conversor()
    {
        //
    }
    
    public Double convertirADouble(Object valor)
    {
        Double val = 0.0;
        if(valor instanceof Integer)
        {
            Integer aux = (Integer)valor;
            val = Double.valueOf(aux);
        }
        else if(valor instanceof Double)
        {
            val = (Double)valor;
        }
        return val;
    }
}
