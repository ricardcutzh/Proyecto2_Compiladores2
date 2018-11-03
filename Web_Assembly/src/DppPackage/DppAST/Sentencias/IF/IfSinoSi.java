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
public class IfSinoSi extends NodoAST{
    Expresion condicion;
    ArrayList<Object> sentencias;
    NodoAST sino;
    ArrayList<NodoAST> sinos;
    public IfSinoSi(int linea, int columna, String Archivo, Expresion condicion, ArrayList<Object> sentencias, ArrayList<NodoAST> sinos, NodoAST sino) {
        super(linea, columna, Archivo);
        this.condicion = condicion;
        this.sentencias = sentencias;
        this.sino = sino;
        this.sinos = sinos;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        InfoEstatica.Estatico.display.PushToDisplay("IF", false);// METO A PILA
        try 
        {
            String expCode = (String)((NodoAST)condicion).generateByteCode(ambito);// GENERO EL CODIGO RELACIONADO A LA EXPRESION
            String tipo = condicion.getTipo(ambito);
            if(tipo.equals("BOOLEAN"))
            {
                String cad = "\n/**************************************************************************/\n";
                cad += "//*           IF_SINO\n";
                cad += "/**************************************************************************/\n";
                cad += "// CONDICION\n";
                cad += expCode+"\n";
                cad += "// FIN CONDICION\n";
                cad += "BR_IF $"+InfoEstatica.Estatico.display.Peek().toString()+"_FALSO_0 // SI LA CONDICION ES FALSA...\n";
                InfoEstatica.Estatico.tabula();
                String auxiliar = "\n//---------- SENTENCIAS ----------\n";
                // CREO EL NUEVO AMBITO
                Ambito ambitoIf = new Ambito("Local | IF", ambito, super.getArchivo());
                ambito.tomarDatosParaVariables(ambitoIf);
                for(Object o : sentencias)
                {
                    // FALTA GENERAR EL NUEVO AMBITO
                    auxiliar += (String)((NodoAST)o).generateByteCode(ambitoIf);
                }
                auxiliar += "\n//---------- FIN SENTENCIAS ----------\n";
                auxiliar += "BR $"+InfoEstatica.Estatico.display.Peek().toString()+"_SALIDA // SALIR PARA NO CUMPLOR LAS DEMAS\n";
                cad += InfoEstatica.Estatico.aplicaTabulaciones(auxiliar);
                InfoEstatica.Estatico.destabula();
                cad += "$"+InfoEstatica.Estatico.display.Peek().toString()+"_FALSO_0 // ETIQUETA DE NO CUMPLIR LA CONDICION\n";
                cad += "/**************************************************************************/\n";
                int x = 1;
                String auxiliar2 = "";
                for(NodoAST n : sinos)
                {
                    SinoSi s = (SinoSi)n;
                    s.SetEtiquetaIF(InfoEstatica.Estatico.display.Peek().toString()+"_FALSO_"+x);
                    s.setEtiquetaSalid(InfoEstatica.Estatico.display.Peek().toString()+"_SALIDA");
                    auxiliar2 += (String)s.generateByteCode(ambito);
                    x++;
                }
                cad += auxiliar2+"\n";
                String auxiliar3="";
                if(sino!=null)
                {
                    Sino s = (Sino)sino;
                    auxiliar3 = (String)s.generateByteCode(ambito);
                }
                cad += auxiliar3+"\n";
                cad += "$"+InfoEstatica.Estatico.display.Peek().toString()+"_SALIDA //SALE DEL IF..\n";
                InfoEstatica.Estatico.display.PopFromDisplay();// SACO DEL DISPLAY
                // DETENIENDO DEBUG
                if (InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE) {
                    if (InfoEstatica.Estatico.esLinea) {
                        InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                        InfoEstatica.Estatico.suspended = true;
                        InfoEstatica.Estatico.OutPutCode.setText(cad);
                        InfoEstatica.Estatico.hilo.suspend();
                    } else {
                        String key1 = super.getLinea() + "_" + super.getArchivo();
                        if (InfoEstatica.Estatico.breakPoints.containsKey(key1)) {
                            InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                            InfoEstatica.Estatico.suspended = true;
                            InfoEstatica.Estatico.OutPutCode.setText(cad);
                            InfoEstatica.Estatico.hilo.suspend();
                        }
                    }
                }
                return  cad;
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(tipo, "La sentencia 'SI' espera una expresion que retorne BOOLEAN"
                        , "Semantico", super.getLinea()
                        , super.getColumna()
                        , Boolean.FALSE
                        , super.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError(
                    "No Aplica"
                    ,"Error al traducir Si Sino: "+e.getMessage()
                    ,"Ejecucion"
                    ,super.getLinea()
                    ,super.getColumna()
                    ,Boolean.FALSE
                    ,super.getArchivo()));
        }
        InfoEstatica.Estatico.display.PopFromDisplay();// SACO DEL DISPLAY
        return "";
    }
    
    
    
}
