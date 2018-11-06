/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage;

import DppPackage.Analizador.DPPLex;
import DppPackage.Analizador.DppParser;
import DppPackage.DppAST.Declaraciones.DppASTTree;
import ErrorManager.TError;
import Simbolos.Ambito;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Clase que maneja el Analisis de el Lenguaje D++
 * @author richard
 */
public class DppAnalizador implements Runnable{
    
    String cadena;
    String archivo;
    String PathProyecto;
    
    /**
     * Constructor del analizador para D++
     * @param cadena
     * @param archivo
     * @param PathProyecto 
     */
    public DppAnalizador(String cadena, String archivo, String PathProyecto) {
        this.cadena = cadena;
        this.archivo = archivo;
        this.PathProyecto = PathProyecto;
    }
    /**
     * Getter de la cadena a analizar
     * @return cadena que se va analizar 
     */
    public String getCadena() {
        return cadena;
    }
    
    /**
     * Getter del archivo que se esta anaizando
     * @return archivo que se esta analizando
     */
    public String getArchivo() {
        return archivo;
    }
    
    /**
     * Getter de la cadena donde se encuentra el proyecto
     * @return path donde esta el proyecto
     */
    public String getPathProyecto() {
        return PathProyecto;
    }
    /**
     * Metodo auxiliar que me ayudara a poder analizar los imports
     * @return Nodo de inicio del analisis
     */
    public Object getRootFromImport()
    {
        try {
            DppPackage.Analizador.DPPLex lex = new DPPLex(new StringReader(cadena));
            lex.setNombreArchivo(archivo);
            DppParser.ArchivoOrigen = archivo;
            DppParser.PathProyecto = PathProyecto;
            DppParser parser = new DppParser(lex);
            parser.parse();
            // AQUI TENGO QUE BUSCAR LA RAIZ DEL ARBOL QUE FORME
            ArrayList<TError> errores = DppParser.errores;
            if(errores.isEmpty() && DppParser.inicial!=null)
            {
                return DppParser.inicial;// RETORNA EL NODO
            }
            else 
            {
                for(TError err:errores)
                {
                    InfoEstatica.Estatico.errores.add(err);
                }
                return null;
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("FATAL", "No se analizo la cadena: "+e.getMessage(), 
                    archivo, 0, 0, Boolean.FALSE, archivo));
            return null;
        }
    }
    
    /**
     * Metodo que inicia el analisis dentro de un hilo
     */
    @Override
    public void run() {
        try {
            Object r = getRootFromImport();  
            if(InfoEstatica.Estatico.errores.size()>0)
            {
                InfoEstatica.Estatico.ActualizarTablaDeErrores();
            }
            else if(r!=null)
            {
                DppASTTree aux = (DppASTTree)r;
                aux.setPathEscritura(PathProyecto);
                aux.generateByteCode(new Ambito("Global", null, archivo));
            }
        } catch (Exception e) {
            
        }
    }
    
}
