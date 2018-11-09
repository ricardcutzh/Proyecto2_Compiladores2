/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DasmPackage.DasmAST;

import Abstraccion.NodoAST;
import Abstraccion.SentenciaDasm;
import DasmPackage.DasmAST.Nativas.*;
import ErrorManager.TError;
import Estructuras.FuncionDasm;
import Estructuras.IP;
import Simbolos.EntornoDasm;
import java.util.HashMap;

/**
 * Clase que maneja los llamados a instrucciones Call
 * @author richard
 */
public class Call extends NodoAST implements SentenciaDasm{
    String id;
    /**
     * Instruccion que se encarga del llamado de las funciones en DASM
     * @param linea
     * @param columna
     * @param Archivo
     * @param id Identificador de la instruccion
     */
    public Call(int linea, int columna, String Archivo, String id) {
        super(linea, columna, Archivo);
        this.id  = id;
    }
    
    /**
     * Metodo que realiza la busquedad de la funcion que se necesita y hace 
     * la ejecucion de la misma
     * @param entorno
     * @param instrucctionPointer
     * @return 
     */
    @Override
    public Object Ejecuta(EntornoDasm entorno, IP instrucctionPointer) {
        try 
        {
            if(InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE)
            {
                if(InfoEstatica.Estatico.esLinea)
                {
                    InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                    InfoEstatica.Estatico.suspended = true;
                    InfoEstatica.Estatico.ActualizaPilita(entorno.getPilita());
                    InfoEstatica.Estatico.ActualizaStack(entorno.getAmbitos());
                    InfoEstatica.Estatico.ActualizaHeap(entorno.getHeap());
                    InfoEstatica.Estatico.hilo.suspend();
                }
                else
                {
                    String key = super.getLinea() + "_" + super.getArchivo();
                    if(InfoEstatica.Estatico.breakPoints.containsKey(key))
                    {
                        InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                        InfoEstatica.Estatico.suspended = true;
                        InfoEstatica.Estatico.ActualizaPilita(entorno.getPilita());
                        InfoEstatica.Estatico.ActualizaStack(entorno.getAmbitos());
                        InfoEstatica.Estatico.ActualizaHeap(entorno.getHeap());
                        InfoEstatica.Estatico.hilo.suspend();
                    }
                }
            }
            FuncionDasm f = entorno.getGestor().getFuncion(id);
            if(f!=null) 
            {
                HashMap<String, Integer> etiquetasaux = entorno.getEtiquetas(); // GUARDO... 
                IP aux = instrucctionPointer;
                f.Ejecuta(entorno, new IP(0));// LE MANDO NULL PORQUE TOMARE EL INSTRUCCTION POINTER DE LA FUNCION QUE SE EJECUTA EJECUTO
                instrucctionPointer = aux;
                entorno.setEtiquetas(etiquetasaux); // COLOCO
            }
            else
            {
                switch(id)
                {
                    case "$Point":
                    {
                        Punto p = new Punto(super.getLinea(), super.getColumna(), super.getArchivo());
                        p.Ejecuta(entorno, instrucctionPointer);
                        break;
                    }
                    case "$Quadrate":
                    {
                        Cuadrado c = new Cuadrado(super.getLinea(), super.getColumna(), super.getArchivo());
                        c.Ejecuta(entorno, instrucctionPointer);
                        break;
                    }
                    case "$Oval":
                    {
                        Ovalo o = new Ovalo(super.getLinea(), super.getColumna(), super.getArchivo());
                        o.Ejecuta(entorno, instrucctionPointer);
                        break;
                    }
                    case "$String":
                    {
                        Cadena c = new Cadena(super.getLinea(), super.getColumna(), super.getArchivo());
                        c.Ejecuta(entorno, instrucctionPointer);
                        break;
                    }
                    case "$Line":
                    {
                        Linea l = new Linea(super.getLinea(), super.getColumna(), super.getArchivo());
                        l.Ejecuta(entorno, instrucctionPointer);
                        break;
                    }
                    default:
                    {
                        InfoEstatica.Estatico.agregarError(new TError(id, "Funcion no esta definida: "+id, "Semantico", 
                        super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
                    }
                }
            }
            
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejecutar llamado: "+e.getMessage(), "Ejecucion"
                    , super.getLinea(),super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        instrucctionPointer.setIndiceActual(instrucctionPointer.getIndiceActual() + 1);// AUMENTO DEL IP
        return null;
    }
    
}
