/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Valores;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import ErrorManager.TError;
import ObjsComun.NodoMiembro;
import Simbolos.Ambito;
import Simbolos.DppVar;
import Simbolos.Struct;

/**
 *
 * @author richard
 */
public class AccesoMiembroSimple extends NodoAST implements Expresion{
    String id1;
    String id2;
    public AccesoMiembroSimple(int linea, int columna, String Archivo, String id1, String id2) {
        super(linea, columna, Archivo);
        this.id1 = id1;
        this.id2 = id2;
    }
    String tipo = "";
    @Override
    public String getTipo(Ambito ambito) {
        if(tipo.equals(""))
        {
            generateByteCode(ambito);
        }
        return tipo;
    }

    @Override
    public Object getValor(Ambito ambito) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            if(ambito.existeVariable(id1))
            {
                DppVar simbolo = (DppVar)ambito.getSimbolo(id1); // OBTENGO EL SIMBOLO
                Ambito aux = getGlobal(ambito);
                Struct s = aux.getStruct(simbolo.getTipo()); // OBTENGO EL STRUCT QUE SE ESPECIFICO DEL TIPO
                if(s.existeMiembro(id2))
                {
                    NodoMiembro m = s.getByID(id2);
                    this.tipo = m.getTipo();
                    String cad = "// VALOR DEL MIEMBRO: "+id2+" de la estructura: "+id1+" de tipo: "+simbolo.getTipo()+"\n";
                    if(simbolo.getAmbito().equals("Global"))
                    {
                        cad += "//OBTENIENDO PUNTERO AL HEAP DESDE UNA ESTRUCTURA GLOBAL\n";
                        cad += simbolo.getPosicionRelativa()+"\n";
                        cad += "get_local $calc\n";
                    }
                    else
                    {
                        cad += "//OBTENIENDO PUNTERO AL HEAP DESDE UNA ESTRUCTURA LOCAL\n";
                        cad += "get_local 0\n";
                        cad += simbolo.getPosicionRelativa()+"\n";
                        cad += "ADD\n";
                        cad += "get_local $calc //OBTENER EL PUNTERO AL HEAP \n";
                    }
                    cad += (m.getIndiceMiembro()-1)+"\n";
                    cad += "ADD\n";
                    cad += "get_global $calc";
                    return cad;
                }
                else
                {
                    InfoEstatica.Estatico.agregarError(new TError(id2,
                        "La estructura de tipo: "+simbolo.getTipo()+" No cuenta con un miembro: "+id2,
                        "Semantico", 
                        super.getLinea(), 
                        super.getColumna(), 
                        Boolean.FALSE, 
                        super.getArchivo()));
                }
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(id1,
                        "El Simbolo especificado no existe",
                        "Semantico", 
                        super.getLinea(), 
                        super.getColumna(), 
                        Boolean.FALSE, 
                        super.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", 
                    "Error al traducir el acceso Simple a Miembro: "+e.getMessage(), 
                    "Ejecucion", 
                    super.getLinea(), 
                    super.getColumna(), 
                    Boolean.FALSE, 
                    super.getArchivo()));
        }
        return "";
    }
    
    
    private Ambito getGlobal(Ambito actual)
    {
        Ambito aux = actual;
        while(aux.getAnterior()!=null)
        {
            aux = aux.getAnterior();
        }
        return aux;
    }
}
