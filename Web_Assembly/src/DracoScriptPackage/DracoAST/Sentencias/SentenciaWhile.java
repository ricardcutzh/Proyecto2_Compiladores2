/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Sentencias;
import Abstraccion.*;
import ErrorManager.TError;
import ObjsComun.Nulo;
import ObjsComun.Romper;
import Simbolos.Ambito;
import java.util.ArrayList;
/**
 *
 * @author richard
 */
public class SentenciaWhile extends NodoAST implements Instruccion{
    Expresion condicion;
    ArrayList<Instruccion> sentencias;
    /**
     * Constructor del nodo que maneja el nodo While
     * @param linea linea donde se encuentra
     * @param columna columna donde se encuentra
     * @param Archivo Archivo donde se encuentra
     * @param condicion La expresion que representa la condicion
     * @param sentencias Lista de sentencias que se cumplira
     */
    public SentenciaWhile(int linea, int columna, String Archivo, Expresion condicion, ArrayList<Instruccion>sentencias) {
        super(linea, columna, Archivo);
        this.condicion = condicion;
        this.sentencias = sentencias;
    }

    @Override
    public Object Ejecutar(Ambito ambito) {
        try {
            Object comprobacion = condicion.getValor(ambito);
            String tipo = condicion.getTipo(ambito);
            if(comprobacion instanceof Boolean)
            {
                Boolean val = (Boolean)comprobacion;
                Ambito ambitoWhile = new Ambito("While", ambito,ambito.getArchivo());
                ambitoWhile.tomaValoresDeAmbito(ambito);
                InfoEstatica.Estatico.pilaCiclos.push(true);
                Boolean bandera = false;
                while(val)
                {
                    
                    for(Instruccion ins: this.sentencias)
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
                                if (InfoEstatica.Estatico.breakPoints.containsKey(key))
                                {
                                    InfoEstatica.Estatico.MarcaLinea(aux.getLinea());
                                    InfoEstatica.Estatico.suspended = true;
                                    InfoEstatica.Estatico.hilo.suspend();
                                }
                            }        
                        }
                        Object resultado = ins.Ejecutar(ambitoWhile);
                        if(resultado instanceof Romper)
                        {
                            bandera = true;
                            break;
                        }
                    }
                    if(bandera){break;}
                    comprobacion = condicion.getValor(ambito);
                    val = (Boolean)comprobacion;
                    if(InfoEstatica.Estatico.esLinea)
                    {
                        InfoEstatica.Estatico.MarcaLinea(super.getLinea());
                        InfoEstatica.Estatico.suspended = true;
                        InfoEstatica.Estatico.hilo.suspend();
                    }
                    ambitoWhile = new Ambito("While", ambito, ambito.getArchivo());
                    ambitoWhile.tomaValoresDeAmbito(ambito);
                }
                InfoEstatica.Estatico.pilaCiclos.pop();
                return new Nulo();
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError("Tipo: "+tipo, "Para la sentencia WHILE se espera una condicion: Boolean", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
            }
        } catch (Exception e) {
            TError error = new TError("No Aplica", "Error al ejecutar while...", "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            InfoEstatica.Estatico.agregarError(error);
        }
        return new Nulo();
    }
    
}
