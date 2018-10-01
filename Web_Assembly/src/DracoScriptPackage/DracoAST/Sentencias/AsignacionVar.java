/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Sentencias;
import Abstraccion.*;
import ErrorManager.TError;
import ObjsComun.Nulo;
import Simbolos.Ambito;
import Simbolos.Simbolo;
import Simbolos.Variable;
/**
 * Clase que maneja las asignaciones de variables
 * @author richard
 */
public class AsignacionVar extends NodoAST implements Instruccion{
    String identificador;
    Expresion expresion;
    /**
     * Constructor del Nodo encargado de realizar las asignaciones de variables
     * @param linea linea donde se encuntra la signacion
     * @param columna columna donde se encuentra la asignacion
     * @param Archivo archivo donde existe la asignacion
     * @param identificador Identificador de la variable a la que se hace referencia
     * @param expresion  expresion que representa el valor de la variable
     */
    public AsignacionVar(int linea, int columna, String Archivo, String identificador, Expresion expresion) {
        super(linea, columna, Archivo);
        this.expresion = expresion;
        this.identificador = identificador;
    }

    @Override
    public Object Ejecutar(Ambito ambito) {
        try {
            Simbolos.Simbolo s = (Simbolo)ambito.getSimbolo(identificador.toLowerCase());
            if(s!=null)
            {
                Variable v = (Variable)s;
                Object nuevoval = expresion.getValor(ambito);
                String nuevoTipo = expresion.getTipo(ambito);
                v.setTipo(nuevoTipo);
                v.setValor(nuevoval);
            }
            else
            {
                TError error = new TError(identificador, "Se hace referencia a este Simbolo que no existe!", "Semantico", super.getLinea(),super.getColumna(),false, ambito.getArchivo());
                InfoEstatica.Estatico.agregarError(error);
            }
        } catch (Exception e) {
            TError error = new TError("No aplica", "Error al ejecutar la asignacion: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            InfoEstatica.Estatico.agregarError(error);
        }
        return new Nulo();
    }
    
}
