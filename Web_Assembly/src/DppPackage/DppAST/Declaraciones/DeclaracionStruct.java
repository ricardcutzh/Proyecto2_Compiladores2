/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Declaraciones;

import Abstraccion.NodoAST;
import ErrorManager.TError;
import ObjsComun.NodoMiembro;
import Simbolos.Ambito;
import Simbolos.Struct;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author richard
 */
public class DeclaracionStruct extends NodoAST{
    ArrayList<NodoMiembro> miembros;
    String identificador;
    public DeclaracionStruct(int linea, int columna, String Archivo, ArrayList<NodoMiembro> miembros, String identificador) {
        super(linea, columna, Archivo);
        this.miembros = miembros;
        this.identificador = identificador;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            Ambito aux = getGlobal(ambito);
            if(!aux.existeStruct(identificador))
            {
                Struct s = new Struct(identificador, Boolean.FALSE, identificador, getMiembros());
                aux.AddStruct(identificador, s);
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(
                        identificador
                        , "Ya existe una definicion del Struct: "+identificador
                        , "Semantico"
                        , super.getLinea()
                        , super.getColumna()
                        , Boolean.FALSE
                        , super.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError(
                    identificador
                    , "Error al declara Estructura: "+e.getMessage()
                    , "Ejecucion"
                    , super.getLinea()
                    , super.getColumna()
                    , Boolean.FALSE
                    , super.getArchivo()));
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
    private HashMap<String, NodoMiembro> getMiembros()
    {
        HashMap<String, NodoMiembro> m = new HashMap<>();
        for(NodoMiembro n : this.miembros)
        {
            if(!m.containsKey(n.getIdentificador()))
            {
                m.put(n.getIdentificador(), n);
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(
                        n.getIdentificador()
                        , "Ya existe un miembro Definido con el Id: "+n.getIdentificador() + " Como parte de la Estructura: "+identificador 
                        , "Semantico"
                        , ((NodoAST)n).getLinea(), ((NodoAST)n).getColumna()
                        , Boolean.FALSE, ((NodoAST)n).getArchivo() ));
            }
        }
        return m;
    }
    
}
