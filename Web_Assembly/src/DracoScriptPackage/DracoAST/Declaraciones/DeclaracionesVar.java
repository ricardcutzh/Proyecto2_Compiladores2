/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Declaraciones;
import Abstraccion.*;
import Simbolos.Ambito;
import java.util.ArrayList;
/**
 * Clase que se encarga de ejecutar las declaraciones de las variables
 * @author richard
 */
public class DeclaracionesVar extends NodoAST implements Instruccion{
    ArrayList<Instruccion> declaraciones;
    
    /**
     * COnstructor de la ejecucion de declaracion de variables
     * @param linea | Linea donde se encuentra la lista de declaraciones
     * @param columna | Columna donde se encuentra la lista de declaraciones
     * @param Archivo | Archivo donde se hizo la declaracion
     * @param declaraciones | todas las declaraciones de variables
     */
    public DeclaracionesVar(int linea, int columna, String Archivo, ArrayList<Instruccion> declaraciones) {
        super(linea, columna, Archivo);
        this.declaraciones = declaraciones;
    }

    @Override
    public Object Ejecutar(Ambito ambito) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
