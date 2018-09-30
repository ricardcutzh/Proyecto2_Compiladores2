/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage;
import DracoScriptPackage.Analizador.*;
import DracoScriptPackage.DracoAST.Declaraciones.NodoInicio;
import InfoEstatica.Estatico;
import Simbolos.Ambito;
import java.io.StringReader;
/**
 * Clase que inicializa el Analisis para el lenguage DracoScript
 * @author richard
 */
public class DracoAnalizador implements Runnable{
    String cadena;
    String Archivo;
    String pathProyecto;
    
    /**
     * Constructor del Analizador de Draco Script
     * @param cadena Texto a ser analizado
     * @param Archivo Nombre del archivo donde se esta analizando
     * @param pathProyecto Path donde esta almacenado el proyecto que contiene el Archivo
     */
    public DracoAnalizador(String cadena, String Archivo, String pathProyecto)
    {
        this.cadena = cadena;
        this.Archivo = Archivo;
        this.pathProyecto = pathProyecto;
    }
    
    /**
     * Getter de la cadena que se analiza
     * @return texto analizado
     */
    public String getCadena() {
        return cadena;
    }

    /**
     * Getter del Archivo que contiene el Texto
     * @return Nombre del archivo
     */
    public String getArchivo() {
        return Archivo;
    }

    /**
     * Getter de la ruta del proyecto
     * @return path del proyecto 
     */
    public String getPathProyecto() {
        return pathProyecto;
    }
    
    public Boolean analizar()
    {
        try 
        {
            DSLex lex = new DSLex(new StringReader(cadena));
            lex.setNombreArchivo(Archivo);
            DracoParser.ArchivoOrigen = Archivo;
            DracoParser parser = new DracoParser(lex);
            parser.parse();
            NodoInicio iniciar = (NodoInicio)DracoParser.root;
            if(iniciar!=null)
            {
                Ambito nuevo = new Ambito("Global", null, Archivo);
                iniciar.Ejecutar(nuevo);
            }
            return true;
        } catch (Exception e) 
        {
            System.err.println("Error: "+e.getMessage());
            return false;
        }
    }
    
    /**
     * Metodo que permite levantar el hilo de ejecucion de draco Script
     */
    @Override
    public void run() {
        if(analizar())
        {
            Estatico.ImprimeEnConsola("Ejecucion Finalizada con Exito");
        }
        else
        {
            Estatico.ImprimeEnConsola("Error Al Ejecutar DracoScript");
        }
    }
    
}
