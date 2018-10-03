/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Sentencias;
import Abstraccion.*;
import ObjsComun.Romper;
import Simbolos.Ambito;
/**
 *
 * @author richard
 */
public class SentenciaSmash extends NodoAST implements Instruccion{
    /**
     *  Constructor del nodo de Romper
     * @param linea
     * @param columna
     * @param Archivo 
     */
    public SentenciaSmash(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }

    @Override
    public Object Ejecutar(Ambito ambito) {
        return new Romper();
    }
    
}
