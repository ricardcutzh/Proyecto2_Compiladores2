/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DasmPackage;

import DasmPackage.Analizador.DasmLex;
import DasmPackage.Analizador.DasmParser;
import ErrorManager.TError;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Clase que maneja el analisis de DASM
 * @author richard
 */
public class DasmAnalizador {
    String cadena;
    String archivo;
    String pathProyecto;
    /**
     * Constructor del analizador de DASM
     * @param cadena
     * @param archivo
     * @param pathProyecto 
     */
    public DasmAnalizador(String cadena, String archivo, String pathProyecto)
    {
        this.cadena = cadena;
        this.archivo = archivo;
        this.pathProyecto = pathProyecto;
    }
    /**
     * Cadena que se va a Leer
     * @return 
     */
    public String getCadena() {
        return cadena;
    }
    /**
     * Obtener el archivo de entrada de donde se leyo
     * @return 
     */
    public String getArchivo() {
        return archivo;
    }
    /**
     * Path del proyecto donde se encuentra el archivo
     * @return 
     */
    public String getPathProyecto() {
        return pathProyecto;
    }
    
    public Object analizarDasm()
    {
        try 
        {
            DasmPackage.Analizador.DasmLex lex = new DasmLex(new StringReader(cadena));
            lex.setNombreArchivo(archivo);
            DasmParser parser = new DasmParser(lex);
            DasmParser.ArchivoOrigen = archivo;
            DasmParser.PathProyecto = pathProyecto;
            parser.parse();
            ArrayList<TError> errores = DasmParser.errores;
            if(errores.isEmpty())
            {
                // AQUI RETORNO EL LISTADO PARA EJECUTAR
                return DasmParser.gestor;
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
        } catch (Exception e) 
        {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
}
