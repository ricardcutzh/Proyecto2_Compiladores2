/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Valores;
import Abstraccion.*;
import Simbolos.Ambito;
/**
 * Nodo del arbol que maneja los valores Primitivos que existen en el Lenguaje
 * Draco Script
 * @author richard
 */
public class ValorPrimitivo extends NodoAST implements Expresion{
    
    Object Valor;
    /**
     * Constructor del Nodo Valor Primitivo para manejo de valores Nativos del lenguaje
     * @param linea linea donde se encuentra el valor
     * @param columna columna donde se encuentra el valor
     * @param Archivo Archivo donde se esta haciendo el analisis
     * @param Valor el valor en si que se necesita
     */
    public ValorPrimitivo(int linea, int columna, String Archivo, Object Valor) {
        super(linea, columna, Archivo);
        this.Valor = Valor;
    }
    
    Object valorAux;
    @Override
    public String getTipo(Ambito ambito) {
        if(valorAux == null)
        {
            valorAux = getValor(ambito);
        }
        ManejadorTipo m = new ManejadorTipo(valorAux);
        String tipo = m.evaluaValor();
        return tipo;
    }

    @Override
    public Object getValor(Ambito ambito) {
        valorAux = this.Valor;
        return Valor;
    }
    
}
