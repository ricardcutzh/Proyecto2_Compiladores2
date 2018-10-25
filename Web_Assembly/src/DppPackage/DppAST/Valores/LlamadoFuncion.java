/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Valores;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import ErrorManager.TError;
import ObjsComun.Clave;
import ObjsComun.NodoParametro;
import Simbolos.Ambito;
import Simbolos.MetodoFuncion;
import java.util.ArrayList;

/**
 *
 * @author richard
 */
public class LlamadoFuncion extends NodoAST implements Expresion{
    String idFuncion;
    ArrayList<Expresion> parametros;
    /**
     * Se encargara de traducir la llamadas a funciones
     * @param linea
     * @param columna
     * @param Archivo 
     * @param id
     * @param parametros
     */
    public LlamadoFuncion(int linea, int columna, String Archivo, String id, ArrayList<Expresion>parametros) {
        super(linea, columna, Archivo);
        this.idFuncion = id;
        this.parametros = parametros;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            ArrayList<NodoParametro> nodos = getParams(ambito);
            Clave key = new Clave(idFuncion, nodos);
            Ambito aux = buscarFuncion(ambito);
            if(aux.existeFuncion(key))
            {
                MetodoFuncion m = aux.getTablaFunciones().get(key);
                resultado = m.getTipo();
                String cadena = "\n/**************************************************************************/\n";
                cadena += "// PASANDO PARAMETROS..\n";
                for(int x = 0; x < codigoParametros.size(); x++)
                {
                    if(nodos.get(x).getIdParametro().equals("llama"))
                    {
                        cadena += getCadenaParametro(x+1, codigoParametros.get(x), nodos.get(x).getTipo(), ambito.getSize()-1, "", "");
                    }
                }
                for(int x = 0; x < codigoParametros.size(); x++)
                {
                    if(!nodos.get(x).getIdParametro().equals("llama"))
                    {
                        cadena += getCadenaParametro(x+1, codigoParametros.get(x), nodos.get(x).getTipo(), ambito.getSize()-1, "", "");
                    }
                }
                cadena += "// FIN DE PASO DE PARAEMTROS\n";
                cadena += "get_local 0 // CAMBIANDO DE AMBITO\n";
                cadena += (ambito.getSize()-1)+" // SIZE DEL AMBITO PARA AVANZAR\n";
                cadena += "ADD // SUMA PARA MOVERME\n";
                cadena += "set_local 0 // ACTUALIZA EL PUNTERO\n";
                cadena += "\nCall $"+key.toString()+"// LLAMADO DE FUNCION\n";
                cadena += "get_local 0 // REGRESANDO AL AMBITO ANTERIOR\n";
                cadena += (ambito.getSize()-1)+" // SIZE DEL AMBITO PARA REGRESAR\n";
                cadena += "DIFF // RESTAR EL AMBITO\n";
                cadena += "set_local 0 // ACTUALIZA EL PUNTERO\n";
                cadena += "// OBTENIENDO EL RETORNO DE LA FUNCION Y COLOCANDOLO EN LA PILA\n";
                cadena += "get_local $ret\n";
                cadena += "/**************************************************************************/\n";
                return cadena;
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(idFuncion, "Llamado a Funcion que no existe", "Semantico"
                        , super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al traducir llamado a Funcion: "+e.getMessage()
                    , "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        return "";
    }
    
    String resultado;
    @Override
    public String getTipo(Ambito ambito) {
        return resultado;
    }

    @Override
    public Object getValor(Ambito ambito) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    ArrayList<String> codigoParametros;
    private ArrayList<NodoParametro> getParams(Ambito ambito)
    {
        codigoParametros = new ArrayList<>();
        ArrayList<NodoParametro> nodoParametros = new ArrayList<>();
        for(Expresion e : this.parametros)
        {
            NodoAST aux = (NodoAST)e;
            String auxiliar = (String)aux.generateByteCode(ambito);
            codigoParametros.add(auxiliar);// GENERA EL CODIGO DE CADA UNA DE LAS EXPRESIONES
            NodoParametro n;
            if(aux instanceof LlamadoFuncion)
            {
                n = new NodoParametro("llama", e.getTipo(ambito), Boolean.FALSE);
            }
            else
            {
                n = new NodoParametro("aux", e.getTipo(ambito), Boolean.FALSE);
            }
            
            nodoParametros.add(n);
        }
        return nodoParametros;
    }
    
    private Ambito buscarFuncion(Ambito ambito)
    {
        Ambito aux = ambito;
        while(aux.getAnterior()!=null)
        {
            aux = aux.getAnterior();
        }
        return aux;
    }
    
    private String getCadenaParametro(int indice, String expcode, String tipo, int AmbitoTam, String pre, String pos)
    {
        String cad = "";
        switch(tipo)
        {
            case "CADENA":
            {
                break;
            }
            case "ENTERO":
            {
                cad += "\n/**************************************************************************/\n";
                cad += "// PARAM: "+indice+" de Tipo Entero \n";
                cad += "get_local 0 // PUNTERO VIRTUAL\n";
                cad += AmbitoTam+"// TAMMANIO DEL AMBITO\n";
                cad += "ADD // SUMO\n";
                cad += indice +" // NUMERO DE PARAMETRO A COLOCAR\n";
                cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
                cad += expcode+"\n";
                cad += "set_local $calc // COLOCAR EN LA POSICION QUE LE TOCA\n";
                cad += "/**************************************************************************/\n";
                break;
            }
            case "DECIMAL":
            {
                cad += "\n/**************************************************************************/\n";
                cad += "// PARAM: "+indice+" de Tipo Decimal \n";
                cad += "get_local 0 // PUNTERO VIRTUAL\n";
                cad += AmbitoTam+"// TAMMANIO DEL AMBITO\n";
                cad += "ADD // SUMO\n";
                cad += indice +" // NUMERO DE PARAMETRO A COLOCAR\n";
                cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
                cad += expcode+"\n";
                cad += "set_local $calc // COLOCAR EN LA POSICION QUE LE TOCA\n";
                cad += "/**************************************************************************/\n";
                break;
            }
            case "BOOLEAN":
            {
                cad += "\n/**************************************************************************/\n";
                cad += "// PARAM: "+indice+" de Tipo Boolean \n";
                cad += "get_local 0 // PUNTERO VIRTUAL\n";
                cad += AmbitoTam+"// TAMMANIO DEL AMBITO\n";
                cad += "ADD // SUMO\n";
                cad += indice +" // NUMERO DE PARAMETRO A COLOCAR\n";
                cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
                cad += expcode+"\n";
                cad += "set_local $calc // COLOCAR EN LA POSICION QUE LE TOCA\n";
                cad += "/**************************************************************************/\n";
                break;
            }
            case "CARACTER":
            {
                cad += "\n/**************************************************************************/\n";
                cad += "// PARAM: "+indice+" de Tipo Caracter \n";
                cad += "get_local 0 // PUNTERO VIRTUAL\n";
                cad += AmbitoTam+"// TAMMANIO DEL AMBITO\n";
                cad += "ADD // SUMO\n";
                cad += indice +" // NUMERO DE PARAMETRO A COLOCAR\n";
                cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
                cad += expcode+"\n";
                cad += "set_local $calc // COLOCAR EN LA POSICION QUE LE TOCA\n";
                cad += "/**************************************************************************/\n";
                break;
            }
        }
        return cad;
    }

    
}
