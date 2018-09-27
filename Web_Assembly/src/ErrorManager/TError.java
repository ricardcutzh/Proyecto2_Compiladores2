/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ErrorManager;

/**
 * Clase que Almacena los errores con sus atributos
 * @author richard
 */
public class TError {
    String lexema;
    String Mensaje;
    String Tipo;
    int linea;
    int columna;
    Boolean esAdvertencia;
    String Archivo;
    /**
     * Constructor del Manejador de Errores
     * @param lexema la representacion del error
     * @param mensaje descripcion del error que ocurrio
     * @param tipo muestra el tipo de error, Sintatico, Lexico, Semantico
     * @param linea la linea donde ocurrio el error
     * @param columna la columna donde ocurrio el error
     * @param esAdvertencia permite diferenciar si es un error o Advertencia o posible error
     * @param Archivo el archivo donde ocurrio el error
     */
    public TError(String lexema, String mensaje, String tipo, int linea, int columna, Boolean esAdvertencia, String Archivo)
    {
        this.lexema = lexema;
        this.Mensaje = mensaje;
        this.Tipo = tipo;
        this.linea = linea;
        this.columna = columna;
        this.esAdvertencia = esAdvertencia;
        this.Archivo = Archivo;
    }
    
    /**
     * Getter del lexema del error
     * @return cadena que representa el error
     */
    public String getLexema() {
        return lexema;
    }
    
    /**
     * Getter del mensaje sobre el error
     * @return cadena que representa el Mensaje del error
     */
    public String getMensaje() {
        return Mensaje;
    }

    /***
     * Getter del TIpo de Error
     * @return retorna la cadena que representa el tipo de error
     */
    public String getTipo() {
        return Tipo;
    }

    /**
     * Getter del numero de linea del error
     * @return entero que representa la linea
     */
    public int getLinea() {
        return linea;
    }

    /**
     * Getter de la columna del error
     * @return entero que representa la columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Getter para saber si el error es Advertencia o Error
     * @return Booleano que permite saber si es error
     */
    public Boolean getEsAdvertencia() {
        return esAdvertencia;
    }
    
    /**
     * Getter para saber en que Archivo Ocurrio el Error
     * @return  Cadena que representa el error
     */
    public String getArchivo() {
        return Archivo;
    }
    
    
}
