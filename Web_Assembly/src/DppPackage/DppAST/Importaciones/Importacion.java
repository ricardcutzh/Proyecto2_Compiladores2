/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Importaciones;

import Abstraccion.NodoAST;
import DppPackage.DppAnalizador;
import ErrorManager.TError;
import Simbolos.Ambito;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author richard
 */
public class Importacion extends NodoAST{
    String ruta;
    String path;
    /**
     * Constructor del nodo que se encargara de las importaciones
     * de los archivos Dpp
     * @param linea linea donde se encuentra la sentencia de importacion
     * @param columna columna donde se encuentra la sentencia de importacion
     * @param Archivo Archivo donde se encuentra la importacion
     * @param ruta ruta donde se encuentra la importacion
     * @param pathProyecto
     */
    public Importacion(int linea, int columna, String Archivo, String ruta, String pathProyecto) {
        super(linea, columna, Archivo);
        this.ruta = ruta.replace("\"", "");
        this.path = pathProyecto;
    }
    /**
     * Metodo que iniciara la generacion de codigo de las importaciones
     * @param ambito Ambito donde se generara el codigo
     * @return Objeto representando el codigo de bytecode
     */
    @Override
    public Object generateByteCode(Ambito ambito) {
        try {
            String entrada = readFile();
            DppAnalizador analizador = new DppAnalizador(entrada, getFileName(), path);
            Object r = analizador.getRootFromImport();
            if(r!=null)
            {
                return r;
            }
        } catch (Exception e) {
            
        }
        return null;
    }
    
    private String readFile()
    {
        try {
            FileReader file = new FileReader(path+"/"+ruta);
            BufferedReader reader = new BufferedReader(file);
            String text = "";
            String line = reader.readLine();
            while(line != null)
            {
                text += line+"\n";
                line = reader.readLine();
            }
            return text;
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error en importacion: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        return "";
    }
    public String getFileName()
    {
        try {
            String arr[] = this.ruta.split("/");
            String filename = arr[arr.length-1];
            return filename;
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error en importacion: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        return "";
    }
    
}
