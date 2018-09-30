/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Declaraciones;
import Abstraccion.*;
import ErrorManager.TError;
import InfoEstatica.Estatico;
import ObjsComun.Nulo;
import Simbolos.Ambito;
import Simbolos.Simbolo;
import Simbolos.Variable;
/**
 *
 * @author richard
 */
public class DeclaracionVar extends  NodoAST implements Instruccion{
    Expresion expresion;
    String identificador;
    
    /**
     * Constructor inicial cuando la variable ya tiene un valor asociado
     * @param linea  linea del identificador
     * @param columna columna del identificador
     * @param Archivo Archivo donde se encuentra
     * @param identificador nombre de la variable
     * @param expresion  la expresion en cuestiom
     */
    public DeclaracionVar(int linea, int columna, String Archivo, String identificador, Expresion expresion) {
        super(linea, columna, Archivo);
        this.expresion = expresion;
        this.identificador = identificador;
    }
    
    /**
     * 
     * COnstructor inicial cuando la variable no ha sido inicializada
     * @param linea  linea del identificador
     * @param columna columna del identificador
     * @param Archivo Archivo donde se encuentra
     * @param identificador nombre de la variable
     */
    public DeclaracionVar(int linea, int columna, String Archivo, String identificador)
    {
        super(linea, columna, Archivo);
        this.identificador = identificador;
        this.expresion = null;
    }

    @Override
    public Object Ejecutar(Ambito ambito) {
        try 
        {
            if(!ambito.existeVariable(identificador.toLowerCase()))
            {
                if(this.expresion == null)
                {
                    Variable var = new Variable(identificador, false, "NULO", new Nulo());
                    ambito.AgregarVariable(identificador.toLowerCase(), var);
                }
                else
                {
                    Object valor = this.expresion.getValor(ambito);
                    String tipo = this.expresion.getTipo(ambito);
                    Variable var = new Variable(identificador, false, tipo, valor);
                    ambito.AgregarVariable(this.identificador.toLowerCase(), var);
                }
            }
            else
            {
                TError error = new TError(identificador, "Ya existe una declaracion de: "+identificador+" en este ambito", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                Estatico.agregarError(error);
            }
        } catch (Exception e) 
        {
            TError error = new TError("No Aplica", "Error al ejecutar declaracion: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        return new Nulo();
    }
    
    
}
