/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage;

import DppPackage.Analizador.DPPLex;
import DppPackage.Analizador.DppParser;
import ErrorManager.TError;
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
            DppParser parser = new DppParser(lex);
            parser.parse();
            // AQUI TENGO QUE BUSCAR LA RAIZ DEL ARBOL QUE FORME
            ArrayList<TError> errores = DppParser.errores;
            if(errores.isEmpty())
            {
                return null;
            }
            else 
            {
                for(TError err:errores)
                {
                    InfoEstatica.Estatico.errores.add(err);
                }
                InfoEstatica.Estatico.ActualizarTablaDeErrores();
                return null;
            }
        } catch (Exception e) {
            
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
            /*DESPUES TENDRIA QUE PREGUNTAR SI EL ANALISIS FUE NULO ENTONCES.....*/
        } catch (Exception e) {
        }
    }
    
}
