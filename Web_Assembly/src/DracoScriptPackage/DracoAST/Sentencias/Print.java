/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Sentencias;
import Abstraccion.*;
import ErrorManager.TError;
import Simbolos.Ambito;
import ObjsComun.*;
/**
 * Clase que maneja la instruccion de impresion en pantalla
 * @author richard
 */
public class Print extends NodoAST implements Instruccion{
    Expresion expresion;
    /**
     * COnstructor del Nodo Print del arbol AST
     * @param linea Datos del AST
     * @param columna Datos del AST
     * @param Archivo Archivo de Origen
     * @param expresion Expresion a imprimir en pantalla
     */
    public Print(int linea, int columna, String Archivo, Expresion expresion) {
        super(linea, columna, Archivo);
        this.expresion = expresion;
    }

    /**
     * Ejecuta la impresion en consola
     * @param ambito Ambito donde se da la impresion
     * @return No retorna nada
     */
    @Override
    public Object Ejecutar(Ambito ambito) {
        try 
        {
            Object valor = this.expresion.getValor(ambito);
            InfoEstatica.Estatico.ImprimeEnConsola(valor.toString());
        } catch (Exception e) {
            TError error = new TError("No Aplica", e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo());
            InfoEstatica.Estatico.agregarError(error);
        }
        return new Nulo();
    }
    
}
