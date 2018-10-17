/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Abstraccion.NodoAST;
import Abstraccion.SentenciaDasm;
import Simbolos.EntornoDasm;
import java.util.ArrayList;

/**
 *
 * @author richard
 */
public class FuncionDasm extends NodoAST implements SentenciaDasm{
    
    String idFuncion; // NOMBRE DE LA FUNCION
    ArrayList<SentenciaDasm> intrucciones; //INSTRUCCIONES DE LA FUNCION
    IP instrucctionPointer; //PUNTERO DE INSTRUCCIONES

    public FuncionDasm(int linea, int columna, String Archivo, String idFuncion, ArrayList<SentenciaDasm> intrucciones) {
        super(linea, columna, Archivo);
        this.instrucctionPointer = new IP(0);
        this.intrucciones = intrucciones;
    }

    @Override
    public Object Ejecuta(EntornoDasm entorno, IP instrucctionPointer) {
        instrucctionPointer = this.instrucctionPointer; 
        try 
        {
            while(this.instrucctionPointer.getIndiceActual() < this.intrucciones.size())
            {
                SentenciaDasm i = this.intrucciones.get(this.instrucctionPointer.getIndiceActual());
                i.Ejecuta(entorno, instrucctionPointer);
            }
        } catch (Exception e) 
        {
            
        }
        return null;
    }

    
    
}
