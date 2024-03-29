/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Sentencias;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import DppPackage.DppAST.Valores.LlamadoFuncion;
import ErrorManager.TError;
import ObjsComun.Clave;
import ObjsComun.NodoParametro;
import Simbolos.Ambito;
import java.util.ArrayList;

/**
 *
 * @author richard
 */
public class LlamadoMetodo extends NodoAST {

    String idMetodo;
    ArrayList<Expresion> parametros;

    /**
     * Nodo del arbol que traduce el llamado a un metodo (NO SE HARA REFERENCIA
     * AL RETURN)
     *
     * @param linea
     * @param columna
     * @param Archivo
     * @param idMetodo
     * @param parametros
     */
    public LlamadoMetodo(int linea, int columna, String Archivo, String idMetodo, ArrayList<Expresion> parametros) {
        super(linea, columna, Archivo);
        this.idMetodo = idMetodo;
        this.parametros = parametros;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try {
            ArrayList<NodoParametro> nodos = getParams(ambito);
            Clave key = new Clave(idMetodo, nodos);
            Ambito aux = buscarFuncion(ambito);
            if (aux.existeFuncion(key)) {
                String cadena = "\n/**************************************************************************/\n";
                cadena += "// PASANDO PARAMETROS\n";
                for (int x = 0; x < codigoParametros.size(); x++) {
                    if (nodos.get(x).getIdParametro().equals("llama")) {
                        cadena += getCadenaParametro(x + 1, codigoParametros.get(x), nodos.get(x).getTipo(), ambito.getSize() - 1);

                    } else {
                        cadena += getCadenaParametro(x + 1, codigoParametros.get(x), nodos.get(x).getTipo(), ambito.getSize() - 1);
                    }
                }
                cadena += "// FIN DE PASO DE PARAMETROS\n";
                cadena += "get_local 0 // INICIO LLAMADO\n";
                cadena += (ambito.getSize() - 1) + " // SIZE DEL AMBITO PARA AVANZAR\n";
                cadena += "ADD // SUMA PARA MOVERME\n";
                cadena += "set_local 0 // ACTUALIZA EL PUNTERO\n";
                cadena += "\nCall $" + key.toString() + "// LLAMADO DE FUNCION\n";
                cadena += "get_local 0 // REGRESANDO AL AMBITO ANTERIOR\n";
                cadena += (ambito.getSize() - 1) + " // SIZE DEL AMBITO PARA REGRESAR\n";
                cadena += "DIFF // RESTAR EL AMBITO\n";
                cadena += "set_local 0 // ACTUALIZA EL PUNTERO\n";
                cadena += "/**************************************************************************/\n";
                // DETENIENDO DEBUG
                if (InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE) {
                    if (InfoEstatica.Estatico.esLinea) {
                        InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                        InfoEstatica.Estatico.suspended = true;
                        InfoEstatica.Estatico.OutPutCode.setText(cadena);
                        InfoEstatica.Estatico.hilo.suspend();
                    } else {
                        String key1 = super.getLinea() + "_" + super.getArchivo();
                        if (InfoEstatica.Estatico.breakPoints.containsKey(key1)) {
                            InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                            InfoEstatica.Estatico.suspended = true;
                            InfoEstatica.Estatico.OutPutCode.setText(cadena);
                            InfoEstatica.Estatico.hilo.suspend();
                        }
                    }
                }
                return cadena;
            } else {
                InfoEstatica.Estatico.agregarError(new TError(idMetodo, "Llamado a metodo que no existe", "Semantico",
                         super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al traducir llamado a Metodo: " + e.getMessage(),
                     "Ejecucion", super.getLinea(), super.getColumna(), Boolean.FALSE, super.getArchivo()));
        }
        return "";
    }

    ArrayList<String> codigoParametros;

    private ArrayList<NodoParametro> getParams(Ambito ambito) {
        codigoParametros = new ArrayList<>();
        ArrayList<NodoParametro> nodoParametros = new ArrayList<>();
        for (Expresion e : this.parametros) {
            NodoAST aux = (NodoAST) e;
            String auxiliar = (String) aux.generateByteCode(ambito);
            codigoParametros.add(auxiliar);// GENERA EL CODIGO DE CADA UNA DE LAS EXPRESIONES
            NodoParametro n;
            if (aux instanceof LlamadoFuncion) {
                n = new NodoParametro("llama", e.getTipo(ambito), Boolean.FALSE);
            } else {
                if(InfoEstatica.Estatico.valorArreglo)
                {
                    n = new NodoParametro("aux", e.getTipo(ambito), Boolean.TRUE, InfoEstatica.Estatico.dims);
                    InfoEstatica.Estatico.dims = 0;
                    InfoEstatica.Estatico.valorArreglo = false;
                }
                else
                {
                    n = new NodoParametro("aux", e.getTipo(ambito), Boolean.FALSE);
                }
            }
            nodoParametros.add(n);
        }
        return nodoParametros;
    }

    private Ambito buscarFuncion(Ambito ambito) {
        Ambito aux = ambito;
        while (aux.getAnterior() != null) {
            aux = aux.getAnterior();
        }
        return aux;
    }

    private String getCadenaParametro(int indice, String expcode, String tipo, int AmbitoTam) {
        String cad = "";
        switch (tipo) {
            case "CADENA": {
                cad += "\n/**************************************************************************/\n";
                cad += "// PARAM: " + indice + " | PUNTERO A CADENA EN HEAP \n";
                cad += "get_local 0 // PUNTERO VIRTUAL\n";
                cad += AmbitoTam + "// TAMMANIO DEL AMBITO\n";
                cad += "ADD // SUMO\n";
                cad += indice + " // NUMERO DE PARAMETRO A COLOCAR\n";
                cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
                cad += expcode + "\n";
                cad += "/**************************************************************************/\n";
                break;
            }
            case "ENTERO": {
                cad += "\n/**************************************************************************/\n";
                cad += "// PARAM: " + indice + " de Tipo Entero \n";
                cad += "get_local 0 // PUNTERO VIRTUAL\n";
                cad += AmbitoTam + "// TAMMANIO DEL AMBITO\n";
                cad += "ADD // SUMO\n";
                cad += indice + " // NUMERO DE PARAMETRO A COLOCAR\n";
                cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
                cad += expcode + "\n";
                //cad += "set_local $calc // COLOCAR EN LA POSICION QUE LE TOCA\n";
                cad += "/**************************************************************************/\n";
                break;
            }
            case "DECIMAL": {
                cad += "\n/**************************************************************************/\n";
                cad += "// PARAM: " + indice + " de Tipo Decimal \n";
                cad += "get_local 0 // PUNTERO VIRTUAL\n";
                cad += AmbitoTam + "// TAMMANIO DEL AMBITO\n";
                cad += "ADD // SUMO\n";
                cad += indice + " // NUMERO DE PARAMETRO A COLOCAR\n";
                cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
                cad += expcode + "\n";
                //cad += "set_local $calc // COLOCAR EN LA POSICION QUE LE TOCA\n";
                cad += "/**************************************************************************/\n";
                break;
            }
            case "BOOLEAN": {
                cad += "\n/**************************************************************************/\n";
                cad += "// PARAM: " + indice + " de Tipo Boolean \n";
                cad += "get_local 0 // PUNTERO VIRTUAL\n";
                cad += AmbitoTam + "// TAMMANIO DEL AMBITO\n";
                cad += "ADD // SUMO\n";
                cad += indice + " // NUMERO DE PARAMETRO A COLOCAR\n";
                cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
                cad += expcode + "\n";
                //cad += "set_local $calc // COLOCAR EN LA POSICION QUE LE TOCA\n";
                cad += "/**************************************************************************/\n";
                break;
            }
            case "CARACTER": {
                cad += "\n/**************************************************************************/\n";
                cad += "// PARAM: " + indice + " de Tipo Caracter \n";
                cad += "get_local 0 // PUNTERO VIRTUAL\n";
                cad += AmbitoTam + "// TAMMANIO DEL AMBITO\n";
                cad += "ADD // SUMO\n";
                cad += indice + " // NUMERO DE PARAMETRO A COLOCAR\n";
                cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
                cad += expcode + "\n";
                //cad += "set_local $calc // COLOCAR EN LA POSICION QUE LE TOCA\n";
                cad += "/**************************************************************************/\n";
                break;
            }
            default: {
                cad += "\n/**************************************************************************/\n";
                cad += "// PARAM: " + indice + " de Tipo: " + tipo + "\n";
                cad += "get_local 0 // PUNTERO VIRTUAL\n";
                cad += AmbitoTam + "// TAMMANIO DEL AMBITO\n";
                cad += "ADD // SUMO\n";
                cad += indice + " // NUMERO DE PARAMETRO A COLOCAR\n";
                cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
                cad += expcode + "\n";
                //cad += "set_local $calc // COLOCAR EN LA POSICION QUE LE TOCA\n";
                cad += "/**************************************************************************/\n";
            }
        }
        return cad;
    }

}
