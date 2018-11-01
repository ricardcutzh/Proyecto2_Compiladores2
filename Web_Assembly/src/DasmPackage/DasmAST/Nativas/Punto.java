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
 *
 * @author richard
 */
public class Punto extends NodoAST implements SentenciaDasm{

    public Punto(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
    }

    @Override
    public Object Ejecuta(EntornoDasm entorno, IP instrucctionPointer) {
        try 
        {
            //RECUPERO LOS VALORES DE FINAL HASTA EL PRINCIPIO
            NodoPilita diametro = entorno.getPilita().pop();
            NodoPilita color = entorno.getPilita().pop();
            NodoPilita posy = entorno.getPilita().pop();
            NodoPilita posx = entorno.getPilita().pop();
            // AGREGO EL PUNTO PARA QUE SEA DIBUJADO EN EL NAVEGADOR
            InfoEstatica.Estatico.navegador.getPanel().addPunto(
                    posx.getValor().intValue(), 
                    posy.getValor().intValue(), 
                    getColor(color.getValor().intValue()), 
                    diametro.getValor().intValue()
            );
        } catch (Exception e)
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", 
                    "Error al ejecutar Funcion Point de DASM: "+e.getMessage(), 
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
}
