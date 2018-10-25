/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Sentencias;

import Abstraccion.NodoAST;
import ErrorManager.TError;
import Simbolos.Ambito;
import Simbolos.DppVar;
import Simbolos.Simbolo;

/**
 *
 * @author richard
 */
public class SentDec extends NodoAST{
    String id;
    public SentDec(int linea, int columna, String Archivo, String id) {
        super(linea, columna, Archivo);
        this.id = id;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            Simbolo s = (Simbolo)ambito.getSimbolo(id);
            if(s!=null)
            {
                if(s instanceof DppVar)
                {
                    DppVar v = (DppVar)s;
                    String tipo = v.getTipo();
                    if(tipo.equals("ENTERO") || tipo.equals("DECIMAL"))
                    {
                        String cad = "\n// DECREMENTO: \n";
                        cad += "get_local 0 // PUNTERO\n";
                        cad += v.getPosicionRelativa()+" // POSICION RELATIVA DE LA VARIABLE\n";
                        cad += "ADD // ENCONTRAR LA POSICION REAL\n";
                        
                        // AUMENTO
                        cad += "get_local 0 // PUNTERO PARA AUMENTAR...\n";
                        cad += v.getPosicionRelativa()+" // POSICION RELATIVA..\n";
                        cad += "ADD // SUMA PARA ENCONTRAR POSICION REAL\n";
                        cad += "get_local $calc // OBTENGO EL VALOR Y L0 COLOCO EN LA PILA\n";
                        cad += "1 // COLOCLO EL AUMENTO DE 1\n";
                        cad += "DIFF // SUMO EL VALOR A LA VARIABLE\n";
                        cad += "set_local $calc // LO COLOCO DE NUEVO EN LA POSICION YA CALCULADA..\n";
                        cad += "// FIN DECREMENTO....n";
                        return cad;
                    }
                    else
                    {
                        InfoEstatica.Estatico.agregarError(new TError(
                                tipo
                                ,"La Operacion de Decremento no se pude Aplicar para el Tipo especificado"
                                ,"Semantico"
                                , super.getLinea()
                                , super.getColumna()
                                , Boolean.FALSE
                                , super.getArchivo()));
                    }
                }
                else
                {
                    InfoEstatica.Estatico.agregarError(
                            new TError(id, "No se puede aplicar la sentencia de Decremento para el Simbolo indicado"
                                     , "Semantico"
                                     , super.getLinea()
                                     , super.getColumna()
                                     , Boolean.FALSE, super.getArchivo())
                    );
                }
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(
                        id
                      , "El identificador especificado no existe en el ambito actual"
                      , "Semantico"
                      , super.getLinea()
                      , super.getColumna()
                      , Boolean.FALSE
                      , super.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica"
                    , "Error al Traducir el Decremento: "+e.getMessage()
                    , "Ejecucion"
                    , super.getLinea()
                    , super.getColumna()
                    , Boolean.FALSE
                    , super.getArchivo()));
        }
        return "";
    }
    
    
    
}
