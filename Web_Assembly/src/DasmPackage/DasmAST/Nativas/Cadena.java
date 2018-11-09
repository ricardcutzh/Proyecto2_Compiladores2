/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DasmPackage.DasmAST.Nativas;

import Abstraccion.NodoAST;
import Abstraccion.SentenciaDasm;
import ErrorManager.TError;
import Estructuras.Global.DasmHeap;
import Estructuras.Global.NodoHeap;
import Estructuras.IP;
import Estructuras.NodoPilita;
import Simbolos.EntornoDasm;

/**
 * Clase que maneja la instrccion String
 * @author richard
 */
public class Cadena extends NodoAST implements SentenciaDasm{
    /**
     * Constructor de la instruccion que maneja la funcion de dibujo string
     * @param linea
     * @param columna
     * @param Archivo 
     */
    public Cadena(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }
    /**
     * Toma los parametros de la pila auxiliar y ejecuta la funcion de dibujo
     * @param entorno
     * @param instrucctionPointer
     * @return 
     */
    @Override
    public Object Ejecuta(EntornoDasm entorno, IP instrucctionPointer) {
        try {
            NodoPilita punteroCadena = entorno.getPilita().pop();
            NodoPilita color = entorno.getPilita().pop();
            NodoPilita y = entorno.getPilita().pop();
            NodoPilita x = entorno.getPilita().pop();
            // MANDANDO A PINTAR LA CADENA
            InfoEstatica.Estatico.navegador.getPanel().addString(x.getValor().intValue(), 
                    y.getValor().intValue(), 
                    getColor(color.getValor().intValue()), 
                    getCadena(punteroCadena.getValor().intValue(), entorno)
            );
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", 
                    "Error al ejecutar Funcion String de DASM: "+e.getMessage(), 
                    "Ejecucion", 
                    super.getLinea(), 
                    super.getColumna(), 
                    Boolean.FALSE, 
                    super.getArchivo()));
        }
        return null;
    }
    
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
    
    private String getCadena(int index, EntornoDasm entorno)
    {
        try {
            DasmHeap h = entorno.getHeap();
            NodoHeap aux = h.getNodoAt(index);
            String cad = "";
            int x = index + 1;
            do {                
                Character c = (char)aux.getValor().intValue();
                cad += c.toString();
                aux = h.getNodoAt(x);
                x++;
            } while (aux.getValor()!=0.0);
            return  cad;
        } catch (Exception e) {
        }
        return "error";
    }
}
