/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Sentencias;

import Abstraccion.Expresion;
import Abstraccion.Instruccion;
import Abstraccion.NodoAST;
import Abstraccion.SentenciaDasm;
import ErrorManager.TError;
import ObjsComun.Nulo;
import Simbolos.Ambito;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase que inicia la ejecucion de los DASM
 * @author richard
 */
public class RunMultDasm extends NodoAST implements Instruccion{
    ArrayList<Expresion> expresiones;
    /**
     * Constructor del nodo que ejecuta el correr de los DASM
     * @param linea
     * @param columna
     * @param Archivo
     * @param expresiones 
     */
    public RunMultDasm(int linea, int columna, String Archivo, ArrayList<Expresion> expresiones) {
        super(linea, columna, Archivo);
        this.expresiones = expresiones;
    }

    @Override
    public Object Ejecutar(Ambito ambito) {
        try 
        {
            for(Expresion ex : expresiones)
            {
                Object valor = ex.getValor(ambito);
                String tipo = ex.getTipo(ambito);
                if(tipo.equals("CADENA"))
                {
                    String archivo = (String)valor;
                    iniciaDASM(archivo);
                }
                else
                {
                    InfoEstatica.Estatico.agregarError(new TError(tipo, "La funcion RunMultDasm solo recibe parametros Cadenas", "Semantico", super.getLinea(), super.getColumna()
                            , Boolean.FALSE, super.getArchivo()));
                }
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al correr DASM: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        return new Nulo();
    }
    
    
    private void iniciaDASM(String archivo)
    {
        try 
        {
            if(esDasmValido(archivo))
            {
                DasmPackage.DasmAnalizador analizador = new DasmPackage.DasmAnalizador(textoLeido(InfoEstatica.Estatico.proyectPath+"/"+archivo),archivo, InfoEstatica.Estatico.proyectPath);
                ArrayList<SentenciaDasm> instrucciones = (ArrayList<SentenciaDasm>)analizador.analizarDasm();
                if(instrucciones!=null)
                {
                    DasmPackage.DasmExecutor exe = new DasmPackage.DasmExecutor(new HashMap<String, Integer>(), instrucciones);
                    exe.EjecutarDasm();
                }
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(archivo, "El archivo indicado no es un Archivo DASM valido", "Semantico",super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError(archivo, "Error al intentar abrir el archivo: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
    }
    
    private String textoLeido(String archivo)
    {
        try 
        {
            String texto = "";
            FileReader file = new FileReader(archivo);
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine();
            while(line!=null)
            {
                texto += line+"\n";
                line = reader.readLine();
            }
            return texto;
        } catch (Exception e) 
        {
            return "";
        }
    }
    
    private boolean esDasmValido(String nombre)
    {
        try 
        {
            if(nombre.contains(".dasm"))
            {
                File f = new File(InfoEstatica.Estatico.proyectPath+"/"+nombre);
                return f.exists();
            }
            else
            {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
