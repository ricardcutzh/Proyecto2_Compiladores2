/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjsComun;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import ErrorManager.TError;
import Simbolos.Ambito;
import java.util.ArrayList;

/**
 *
 * @author richard
 */
public class NodoMiembro extends NodoAST{
    String tipo;
    String identificador;
    int indiceMiembro;
    boolean esArreglo;
    
    public NodoMiembro(int linea, int columna, String Archivo, String tipo, String identificador, int indiceMiembro) {
        super(linea, columna, Archivo);
        this.tipo = tipo;
        this.identificador = identificador;
        this.indiceMiembro = indiceMiembro;
    }
    ArrayList<Expresion> dimensiones;
    public NodoMiembro(int linea, int columna, String Archivo, String tipo, String identificador, int indiceMiembre, ArrayList<Expresion>dimensiones)
    {
        super(linea, columna, Archivo);
        this.tipo = tipo;
        this.identificador = identificador;
        this.indiceMiembro = indiceMiembre;
        this.esArreglo = true;
        this.dimensiones = dimensiones;
    }

    public String getTipo() {
        return tipo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public int getIndiceMiembro() {
        return indiceMiembro;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            if(esPrimitivo())
            {
                if(!esArreglo)
                {
                    String cad = "// MIEMBRO: "+identificador+" | INDEX: "+indiceMiembro+"\n";
                    cad += "get_global 0 // METO EL PUNTERO ACTUAL DEL HEAP\n";
                    cad += (indiceMiembro-1)+" // INDICE DEL MIEMBRO\n";
                    cad += "ADD // POSICION ABSOULTA EN EL HEAP\n";
                    cad += "0 // VALOR POR DEFECTO\n";
                    cad += "set_global $calc // LO COLOCO EN LA POSICION ABSOLUTA\n";
                    return cad;
                }
                else
                {
                    // ESTO SI FUESE ARREGLO
                    String cad = "// MIEMBRO: "+identificador+" | INDEX: "+indiceMiembro+"\n";
                    cad += "// ESTOY DECLARANDO UN ARREGLO EN STRUCT\n";
                    cad += "get_global 0 // METO EL PUNTERO ACTUAL DEL HEAP\n";
                    cad += (indiceMiembro-1)+" // INDICE DEL MIEMBRO\n";
                    cad += "ADD // POSICION ABSOULTA EN EL HEAP\n";
                    cad += "0 // VALOR POR DEFECTO\n";
                    cad += "set_global $calc // LO COLOCO EN LA POSICION ABSOLUTA\n";
                    return cad;
                }
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(
                        tipo, 
                        "Miembros de una estructura no pueden ser de tipo: "+tipo,
                        "Semantico", 
                        super.getLinea(), 
                        super.getColumna(), 
                        false, 
                        super.getArchivo()
                ));
            }
            
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError(
                    "Miembro: "+identificador
                    , "Error al traducir el miembro: "+e.getMessage()
                    , "Ejecucion"
                    , super.getLinea()
                    , super.getColumna()
                    , Boolean.FALSE
                    , super.getArchivo()));
        }
        return "";
    }

    public boolean isEsArreglo() {
        return esArreglo;
    }
    
    private Boolean esPrimitivo()
    {
        switch(tipo)
        {
            case "CADENA":
            {
                return true;
            }
            case "ENTERO":
            {
                return  true;
            }
            case "DECIMAL":
            {
                return true;
            }
            case "BOOLEAN":
            {
                return true;
            }
            case "CARACTER":
            {
                return true;
            }
            default:{
                return false;
            }
        }
    }
    
    public String generaCodigoDeArreglo(Ambito ambito)
    {
        try {
            if(esArreglo)
            {
                String cad = "";
                cad += "// GUARDANDO EL PUNTERO\n";
                if(ambito.getIdAmbito().equals("Global"))
                {
                    cad += (ambito.getSize()) + "// POSICION ABSOLUTA EN EL STACK\n";
                    cad += "get_local $calc // OBTENGO EL PUNTERO DE LA ESTRUCTURA\n";
                    cad += (indiceMiembro-1)+" // INDICE DEL MIEMBRO\n";
                    cad += "ADD\n"; //ENCUENTRO LA POSICION DONDE TENGO QUE METER EL PUNTERO DEL HEAP AHORA
                    cad += "get_global 0 // METO EL PUNTERO DEL HEAP\n";
                    cad += "set_global $calc\n";
                }
                else
                {
                    cad += "get_local 0 // PUNTERO DEL STACK\n";
                    cad += (ambito.getSize()) + "\n";
                    cad += "ADD // POSICION DONDE GUARDARE EL PUNTERO AL INICIO DE LA ESTRUCTURA\n";
                    cad += "get_local $calc\n";
                    cad += (indiceMiembro-1)+"//INDICE DONDE DEBO COLOCAR EL ARREGLO\n";
                    cad += "ADD\n";
                    cad += "get_global 0 // METO EL PUNTERO DEL HEAP\n";
                    cad += "set_global $calc\n";
                }
                cad += "// FIN DE GUARDAR EL PUNTERO DEL INICIO DEL ARREGLO\n";
                cad += "/*            ARREGLO: GUARDANDO DIMENSIONES EN HEAP             */\n";
                ArrayList<String> codes = getExpresionCads(ambito);
                int x = 1;
                for (String c : codes) {
                    cad += "// DIMENSION: " + x + "\n";
                    cad += "get_global 0\n";
                    cad += "// VALOR DIMENSION\n";
                    cad += c + "\n";
                    cad += "set_global $calc // DIMENSION GUARDADA EN HEAP\n";
                    cad += "// AUMENTANDO PUNTERO:\n";
                    cad += "get_global 0\n";
                    cad += "1\n";
                    cad += "ADD\n";
                    cad += "set_global 0\n";
                    cad += "// FIN DIMENSION: " + x + "\n";
                    x++;
                }
                cad += "/*         ARREGLO: FIN GUARDADO DIMENSIONES       */\n";
                cad += "/*         LLENANDO EL ESPACIO DEL HEAP PARA EL ARREGLO */\n";
                cad += "// PARAMETRO 1 NUMERO DE ELEMENTOS A LLENAR \n";
                cad += "get_local 0\n";
                cad += (ambito.getSize()) + "\n";
                cad += "ADD\n";
                cad += "1\n";
                cad += "ADD\n";
                cad += "////////////////////////////////////////////////////////////\n";
                cad += "/*         CALCULANDO EL NUMERO DE ELEMENTOS A LLENAR  */\n";
                cad += "// PARAMETRO 1: PUNTERO DEL ARREGLO\n";
                cad += "get_local 0\n";
                cad += (ambito.getSize()) + "\n";
                cad += "ADD\n";
                cad += 1 + "\n";
                cad += "ADD\n";
                cad += "get_local 0\n";
                cad += (ambito.getSize()) + "\n";
                cad += "ADD\n";
                cad += "get_local $calc\n";
                cad += (indiceMiembro - 1)+" //PUNTERO A ARRAY\n";
                cad += "ADD\n";
                cad += "get_global $calc\n";
                cad += "//  PARAMETRO 2: NUMERO DE DIMENSIONES\n";
                cad += "get_local 0\n";
                cad += (ambito.getSize()) + "\n";
                cad += "ADD\n";
                cad += 2 + "\n";
                cad += "ADD\n";
                cad += dimensiones.size() + " // DIMENSIONES\n";
                cad += "// LLAMANDO A LA FUNCION PARA CALCULAR\n";
                cad += "get_local 0\n";
                cad += (ambito.getSize()) + "\n";
                cad += "ADD\n";
                cad += "set_local 0\n";
                cad += "Call $_CALCULA_CANTIDAD\n";
                cad += "get_local 0\n";
                cad += (ambito.getSize()) + "\n";
                cad += "DIFF\n";
                cad += "set_local 0\n";
                cad += "/////////////////////////////////////////////////////////////\n";
                cad += "// OBTENIENDO EL RETURN\n";
                cad += "get_local $ret\n";
                cad += "/////////////////////////////////////////////////////////////\n";
                cad += "// LLAMANDO A LA FUNCION DE LLENADO\n";
                cad += "get_local 0\n";
                cad += (ambito.getSize()) + "\n";
                cad += "ADD\n";
                cad += "set_local 0\n";
                cad += "Call $_LLENAR_ARR\n";
                cad += "get_local 0\n";
                cad += (ambito.getSize()) + "\n";
                cad += "DIFF\n";
                cad += "set_local 0\n";
                cad += "//////////////////////////////////////////////////////////////\n";
                return cad;
            }
            else
            {
                return "";
            }
        } catch (Exception e) {
        }
        return "";
    }
    
    private ArrayList<String> getExpresionCads(Ambito ambito) {
        ArrayList<String> expresiones = new ArrayList<>();
        try {
            for (Expresion e : this.dimensiones) {
                String code = (String) ((NodoAST) e).generateByteCode(ambito);
                String tipo = e.getTipo(ambito);
                if (tipo.equals("ENTERO")) {
                    expresiones.add(code);
                } else {
                    InfoEstatica.Estatico.agregarError(new TError(tipo,
                            "La definicion de Dimensiones de un Arreglo debe ser tipo Entero",
                            "Semantico",
                            super.getLinea(),
                            super.getColumna(),
                            Boolean.FALSE,
                            super.getArchivo()
                    ));
                }
            }
        } catch (Exception e) {
        }
        return expresiones;
    }
}
