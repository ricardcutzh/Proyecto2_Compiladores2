/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Sentencias.IF;

import Abstraccion.NodoAST;
import ErrorManager.TError;
import Simbolos.Ambito;
import java.util.ArrayList;

/**
 *
 * @author richard
 */
public class Sino extends NodoAST {

    ArrayList<Object> sentencias;

    public Sino(int linea, int columna, String Archivo, ArrayList<Object> sentencias) {
        super(linea, columna, Archivo);
        this.sentencias = sentencias;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try {
            String cad = "// SENTENCIA POR DEFAULT SINO\n";
            InfoEstatica.Estatico.tabula();
            String auxiliar = "\n//---------- SENTENCIAS ----------\n";
            // CREO EL NUEVO AMBITO
            Ambito ambitoIf = new Ambito("Local | IF", ambito, super.getArchivo());
            ambito.tomarDatosParaVariables(ambitoIf);
            for (Object o : sentencias) {
                // FALTA GENERAR EL NUEVO AMBITO
                auxiliar += (String) ((NodoAST) o).generateByteCode(ambitoIf);
            }
            auxiliar += "\n//---------- FIN SENTENCIAS ----------\n";
            cad += InfoEstatica.Estatico.aplicaTabulaciones(auxiliar);
            InfoEstatica.Estatico.destabula();
            cad += "/**************************************************************************/\n";
            return cad;
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError(
                    "No Aplica",
                     "No se pudo traducir el SIno: " + e.getMessage(),
                     "Ejecucion",
                     super.getLinea(),
                     super.getColumna(),
                     Boolean.FALSE,
                     super.getArchivo()));
        }
        return "";
    }

}
