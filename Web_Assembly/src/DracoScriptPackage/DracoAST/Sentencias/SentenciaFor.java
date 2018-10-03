/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Sentencias;
import Abstraccion.*;
import DracoScriptPackage.DracoAST.Declaraciones.DeclaracionesVar;
import ErrorManager.TError;
import ObjsComun.Nulo;
import ObjsComun.Romper;
import Simbolos.Ambito;
import java.util.ArrayList;
/**
 * Clase que maneja el ciclo For
 * @author richard
 */
public class SentenciaFor extends NodoAST implements Instruccion{
    Instruccion ins1;
    Instruccion ins2;
    Expresion condicion;
    ArrayList<Instruccion> sentencias;
    /**
     * Constructor del nodo que maneja el ciclo For
     * @param linea
     * @param columna
     * @param Archivo
     * @param ins1
     * @param condicion
     * @param ins2
     * @param sentencias 
     */
    public SentenciaFor(int linea, int columna, String Archivo, Instruccion ins1, Expresion condicion, Instruccion ins2, ArrayList<Instruccion> sentencias) {
        super(linea, columna, Archivo);
        this.ins1 = ins1;
        this.ins2 = ins2;
        this.condicion = condicion;
        this.sentencias = sentencias;
    }

    @Override
    public Object Ejecutar(Ambito ambito) {
        try {
            Ambito intermedio = new Ambito("inerFor", ambito, ambito.getArchivo());
            Ambito ambitoFor = new Ambito("For", intermedio, ambito.getArchivo());
            
            if(ins1 instanceof DeclaracionesVar)
            {
                intermedio.tomaValoresDeAmbito(ambito);
                ins1.Ejecutar(intermedio);
            }
            else
            {
                ins1.Ejecutar(ambito);
                intermedio.tomaValoresDeAmbito(ambito);
            }
            
            ambitoFor.tomaValoresDeAmbito(intermedio);
            if(condicion.getTipo(intermedio).equals("BOOLEAN"))
            {
                Boolean val = (Boolean)condicion.getValor(intermedio);
                InfoEstatica.Estatico.pilaCiclos.push(true);
                Boolean bandera = false;
                while(val)
                {
                    
                    for(Instruccion ins : this.sentencias)
                    {
                        if(InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE)
                        {
                            NodoAST aux = (NodoAST)ins;
                            if(InfoEstatica.Estatico.esLinea)
                            {
                                InfoEstatica.Estatico.MarcaLinea(aux.getLinea());
                                InfoEstatica.Estatico.suspended = true;
                                InfoEstatica.Estatico.hilo.suspend();
                            }
                            else
                            {
                                String key = aux.getLinea() + "_" + aux.getArchivo();
                                if(InfoEstatica.Estatico.breakPoints.containsKey(key))
                                {
                                    InfoEstatica.Estatico.MarcaLinea(aux.getLinea());
                                    InfoEstatica.Estatico.suspended = true;
                                    InfoEstatica.Estatico.hilo.suspend();
                                }
                            }
                        }
                        Object resultado = ins.Ejecutar(ambitoFor);
                        if(resultado instanceof Romper)
                        {
                            bandera = true;
                            break;
                        }
                    }
                    
                    if(bandera){break;}
                    ins2.Ejecutar(intermedio); // AUMENTO
                    val = (Boolean)condicion.getValor(intermedio); // EVALUA CONDICION
                    if(InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE)
                    {
                        InfoEstatica.Estatico.MarcaLinea(super.getLinea());
                        InfoEstatica.Estatico.suspended = true;
                        InfoEstatica.Estatico.hilo.suspend();
                    }
                    ambitoFor = new Ambito("For", intermedio, ambito.getArchivo());// NUEVO AMBITO FOR
                    ambitoFor.tomaValoresDeAmbito(intermedio);
                }
                InfoEstatica.Estatico.pilaCiclos.pop();
                return new Nulo();
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(condicion.getTipo(ambitoFor), "Para ciclo for se espera condicion BOOLEAN", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al Ejecutar el ciclo For: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
        }
        return new Nulo();
    }
    
}
