/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DasmPackage.DasmAST.Nativas;

import Abstraccion.NodoAST;
import Abstraccion.SentenciaDasm;
import ErrorManager.TError;
import Estructuras.IP;
import Estructuras.NodoPilita;
import Simbolos.EntornoDasm;

/**
 * Clase que maneja la instruccion de linea de DASM
 * @author richard
 */
public class Linea extends NodoAST implements SentenciaDasm{
    /**
     * Constructor de la instruccion line 
     * @param linea
     * @param columna
     * @param Archivo 
     */
    public Linea(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }
    
    /**
     * Obtniene el color a partir del valor entero dado
     * @param valor
     * @return cadena que representa el color
     */
    private String getColor(int valor)
    {
        try {
            String hex = Integer.toHexString(valor);
            hex = "#"+hex;
            return hex;
        } catch (Exception e) {
        }
        return "";
    }
    /**
     * Metodo que se encarga de tomar los parametros de la pila auxiliar y ejecuta
     * la funcion de linea
     * @param entorno
     * @param instrucctionPointer
     * @return 
     */
    @Override
    public Object Ejecuta(EntornoDasm entorno, IP instrucctionPointer) {
        try {
            NodoPilita grosor = entorno.getPilita().pop();
            NodoPilita color = entorno.getPilita().pop();
            NodoPilita y2 = entorno.getPilita().pop();
            NodoPilita x2 = entorno.getPilita().pop();
            NodoPilita y1 = entorno.getPilita().pop();
            NodoPilita x1 = entorno.getPilita().pop();
            // MANDANDO A PINTAR EN EL NAVEGADOR
            InfoEstatica.Estatico.navegador.getPanel().addLinea(
                    x1.getValor().intValue(), 
                    y1.getValor().intValue(), 
                    x2.getValor().intValue(), 
                    y2.getValor().intValue(), 
                    getColor(color.getValor().intValue()), 
                    grosor.getValor().intValue()
            );
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", 
                    "Error al ejecutar Funcion Line de DASM: "+e.getMessage(), 
                    "Ejecucion", 
                    super.getLinea(), 
                    super.getColumna(), 
                    Boolean.FALSE, 
                    super.getArchivo()));
        }
        return "";
    }
}
