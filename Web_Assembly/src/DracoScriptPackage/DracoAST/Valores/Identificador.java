/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Valores;
import Abstraccion.*;
import ErrorManager.TError;
import InfoEstatica.Estatico;
import Simbolos.Ambito;
import Simbolos.Simbolo;
import Simbolos.Variable;
/**
 * Expresiones derivadas de un identificador
 * @author richard
 */
public class Identificador extends NodoAST implements Expresion{
    String id;
    
    /**
     * Constructor de la clase que maneja expresiones con Identificadores
     * @param linea linea donde ocurre la expresion
     * @param columna columna donde ocurre la expresion
     * @param Archivo archivo donde ocurre la expresion
     * @param identificador  el id que se quiere mandar a llamar
     */
    public Identificador(int linea, int columna, String Archivo, String identificador) {
        super(linea, columna, Archivo);
        this.id = identificador;
    }
    Object valorAux = null;
    @Override
    public String getTipo(Ambito ambito) {
        if(this.valorAux == null)
        {
            valorAux = getValor(ambito);
        }
        ManejadorTipo m = new ManejadorTipo(valorAux);
        return m.evaluaValor();
    }

    @Override
    public Object getValor(Ambito ambito) {
        try 
        {
            Simbolo s = ambito.getSimbolo(this.id.toLowerCase());
            if(s!=null)
            {
                Variable v = (Variable)s;
                this.valorAux = v.getValor();
                return valorAux;
            }
            else
            {
                TError error = new TError(this.id, "Se hace referencia a una Variable que no Existe en este ambito", "Semantico", super.getLinea(), super.getColumna(),false, ambito.getArchivo());
                Estatico.agregarError(error);
            }
        } catch (Exception e) 
        {
            TError erro = new TError("No Aplica","Error: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(erro);
        }
        valorAux = 0;
        return valorAux;
    }
    
}
