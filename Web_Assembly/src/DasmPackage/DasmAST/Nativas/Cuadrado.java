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
 * Clase que maneja la instruccion Quadrate
 * @author richard
 */
public class Cuadrado extends NodoAST implements SentenciaDasm{
    /**
     * Constructor de la instruccion Quadrate
     * @param linea
     * @param columna
     * @param Archivo 
     */
    public Cuadrado(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }
    /**
     * Metodo que devuelve el color de acuerdo a un numero entero
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
     * Metodo que toma los parametros que existen en la pila auxiliar y ejecuta
     * la funcion de pintado del cuadrado
     * @param entorno
     * @param instrucctionPointer
     * @return 
     */
    @Override
    public Object Ejecuta(EntornoDasm entorno, IP instrucctionPointer) {
        try {
            NodoPilita alto = entorno.getPilita().pop();
            NodoPilita ancho = entorno.getPilita().pop();
            NodoPilita color = entorno.getPilita().pop();
            NodoPilita y = entorno.getPilita().pop();
            NodoPilita x = entorno.getPilita().pop();
            // MANDANDO A PINTAR EN EL NAVEGADOR
            InfoEstatica.Estatico.navegador.getPanel().addCuadrado(x.getValor().intValue(), 
                    y.getValor().intValue(), 
                    getColor(color.getValor().intValue()), 
                    ancho.getValor().intValue(), 
                    alto.getValor().intValue());
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", 
                    "Error al ejecutar Funcion Quadrate de DASM: "+e.getMessage(), 
                    "Ejecucion", 
                    super.getLinea(), 
                    super.getColumna(), 
                    Boolean.FALSE, 
                    super.getArchivo()));
        }
        return "";
    }
}
