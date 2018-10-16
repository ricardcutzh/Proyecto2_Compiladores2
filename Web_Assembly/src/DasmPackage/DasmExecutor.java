/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DasmPackage;

import Abstraccion.SentenciaDasm;
import DasmPackage.DasmAST.*;
import ErrorManager.TError;
import java.util.ArrayList;
import java.util.HashMap;
import Estructuras.*;
/**
 * El que inicia la ejecucion del DASM
 * @author richard
 */
public class DasmExecutor {
    ////////////////////////////////////////////////////////////////////////////
    // ATRIBUTOS DE LA CLASE
    
    HashMap<String, Integer> posicionFunciones; // EL DICCIONARIO DE LAS FUNCIONES DISPONIBLES
    ArrayList<SentenciaDasm> instrucciones; // LAS INSTRUCCIONES QUE SE DEBEN DE EJECUTAR
    Integer IP; // PUNTERO QUE SE ENCARGA DE MOVER LAS INSTRUCCIONES
    
    ////////////////////////////////////////////////////////////////////////////
    // ESTRUCTURAS
    StackAmbito Stack;
    Pilita pilaAuxlilar;
    
    ////////////////////////////////////////////////////////////////////////////
    // VARAIBLES AUXILIARES
    Boolean stop;
    enum TipoSentencia
    {
        OP_PILITA,
        OP_STACK,
        OP_HEAP
    }
    
    public DasmExecutor(HashMap<String, Integer> posicionFunciones, ArrayList<SentenciaDasm> instrucciones)
    {
        this.posicionFunciones = posicionFunciones;
        this.instrucciones = instrucciones;
        this.IP = 0;// VA A EMPEZAR AL INICIO
        Stack = new StackAmbito();
        pilaAuxlilar = new Pilita();
        stop = false;
    }
    
    
    public void EjecutarDasm()
    {
        try 
        {
            while(!stop && this.IP < this.instrucciones.size())
            {
                SentenciaDasm i = this.instrucciones.get(this.IP);
                TipoSentencia t = getTipo(i);
                switch(t)
                {
                    case OP_PILITA:
                    {
                        i.Ejecuta(this.pilaAuxlilar);
                        break;
                    }
                }
                this.IP ++;
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejecutar DASM: "+e.getMessage(), "Ejecucion", 0, 0, Boolean.FALSE, "NAN"));
        }
        this.pilaAuxlilar.printPilita();
    }
    
    private TipoSentencia getTipo(SentenciaDasm i)
    {
        if(i instanceof PushDecimal || i instanceof PushEntero)
        {
            return TipoSentencia.OP_PILITA;
        }
        if(i instanceof InstAdd || i instanceof InstDiff)
        {
            return TipoSentencia.OP_PILITA;
        }
        return TipoSentencia.OP_PILITA;
    }
}
