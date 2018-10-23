/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Sentencias.IF;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import ErrorManager.TError;
import Simbolos.Ambito;
import java.util.ArrayList;

/**
 *
 * @author richard
 */
public class SinoSi extends NodoAST{
    Expresion condicion;
    ArrayList<Object> sentencias;
    public SinoSi(int linea, int columna, String Archivo, Expresion condicion, ArrayList<Object> sentencias) {
        super(linea, columna, Archivo);
        this.sentencias = sentencias;
        this.condicion = condicion;
    }

    String etiquetaIF;
    
    public void SetEtiquetaIF(String etiqueta)
    {
        this.etiquetaIF = etiqueta;
    }
    
    String etiquetaSalida;
    public void setEtiquetaSalid(String etiqueta)
    {
        this.etiquetaSalida = etiqueta;
    }
    
    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            String expCode = (String)((NodoAST)condicion).generateByteCode(ambito);// GENERO EL CODIGO RELACIONADO A LA EXPRESION
            String tipo = condicion.getTipo(ambito);
            if(tipo.equals("BOOLEAN"))
            {
                String cad = "\n/**************************************************************************/\n";
                cad += "// SINO\n";
                cad += "// SINO: CONDICION\n";
                cad += expCode+"\n";
                cad += "// FIN CONDICION SINO\n";
                cad += "BR_IF $"+etiquetaIF+" // ETIQUETA FALSO\n";
                cad += "// SENTENCIAS VERDADERAS\n";
                Ambito ambitoIf = new Ambito("Local | IF", ambito, super.getArchivo());
                ambito.tomarDatosParaVariables(ambitoIf);
                String auxiliar = "";
                for(Object o : sentencias)
                {
                    // FALTA GENERAR EL NUEVO AMBITO
                    auxiliar += (String)((NodoAST)o).generateByteCode(ambitoIf);
                }
                auxiliar += "\n// FIN SENTENCIAS VERDADERAS\n";
                auxiliar += "BR $"+etiquetaSalida+" // SALE...\n";
                InfoEstatica.Estatico.tabula();
                cad += InfoEstatica.Estatico.aplicaTabulaciones(auxiliar);
                InfoEstatica.Estatico.destabula();
                cad += "\n$"+etiquetaIF+"// ETIQUETA FALSA\n";
                cad += "/**************************************************************************/\n";
                return cad;
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(tipo, "La sentencia 'SINO' espera una expresion que retorne BOOLEAN"
                        , "Semantico", super.getLinea()
                        , super.getColumna()
                        , Boolean.FALSE
                        , super.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica"
                    , "Error al Traducir SINO: "+e.getMessage()
                    , "Ejecucion"
                    , super.getLinea()
                    , super.getColumna()
                    , Boolean.FALSE
                    , super.getArchivo()));
        }
        return "";
    }
    
    
    
}
