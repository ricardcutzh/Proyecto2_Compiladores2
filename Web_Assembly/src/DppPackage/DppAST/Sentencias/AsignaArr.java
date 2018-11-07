/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Sentencias;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import ErrorManager.TError;
import Simbolos.Ambito;
import Simbolos.DppArr;
import Simbolos.Simbolo;
import java.util.ArrayList;

/**
 *
 * @author richard
 */
public class AsignaArr extends NodoAST {

    ArrayList<Expresion> dimensiones;
    String identificador;
    Expresion valor;

    public AsignaArr(int linea, int columna, String Archivo, String identificador, ArrayList<Expresion> dimensiones, Expresion valor) {
        super(linea, columna, Archivo);
        this.identificador = identificador;
        this.dimensiones = dimensiones;
        this.valor = valor;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try {
            if (ambito.existeVariable(identificador)) {
                Simbolo s = ambito.getSimbolo(identificador);
                if (s instanceof DppArr) {
                    DppArr arr = (DppArr) s;
                    String expcode = (String)((NodoAST)valor).generateByteCode(ambito);
                    String tipoObtenido = valor.getTipo(ambito);
                    if(!tipoObtenido.equals(arr.getTipo()))
                    {
                        InfoEstatica.Estatico.agregarError(new TError(arr.getTipo()+"="+tipoObtenido, 
                                "El tipo del arreglo no coincide con el tipo Obtenido", 
                                "Semantico", super.getLinea(), super.getColumna(), 
                                Boolean.FALSE, super.getArchivo()));
                        return "";
                    }
                    if (arr.getNumDimensiones() == dimensiones.size()) {
                        String cad = "/*     OBTENIENDO EL INDICE DEL ARREGLO       */\n";
                        if(arr.getAmbito().equals("Global"))
                        {
                            cad += arr.getPosicionRelativa()+" // POSICION ABSOLITA DEL STACK\n";
                            cad += "get_local $calc\n";
                        }
                        else
                        {
                            cad += "get_local 0\n";
                            cad += arr.getPosicionRelativa()+"//POSICION EN LA STACK\n";
                            cad += "ADD\n";
                            cad += "get_local $calc // OBTENIENDO EL VALOR (PUNTERO DEL INICIO DEL ARREGLO)\n";
                        }
                        cad += "// SUMANDOLE PARA LLEGAR AL INICIO DEL ARREGLO\n";
                        cad += (arr.getNumDimensiones()-1)+"\n";
                        cad += "ADD\n";
                        ArrayList<String> dimensiones = getDimensiones(ambito, arr);
                        ArrayList<String> coordenadas = getCoordenadas(ambito);
                        cad += "// CALCULANDO POSICION\n";
                        cad += calcularPosicion(coordenadas, dimensiones);
                        cad += "1\nADD\n";
                        cad += "// FIN CALCULANDO POSICION\n";
                        cad += "ADD //SUMO PARA ENCONTRAR SU POCION ABSOLUTA\n";
                        cad += "// VALOR A  ASIGNAR: \n";
                        cad += expcode+"\n";
                        cad += "set_global $calc\n";
                        return cad;
                    } else {
                        InfoEstatica.Estatico.agregarError(new TError(identificador,
                                "Las dimensiones no coinciden al Identificador: "+identificador,
                                "Semantico",
                                super.getLinea(),
                                super.getColumna(),
                                Boolean.FALSE,
                                super.getArchivo()
                        ));
                    }
                } else {
                    InfoEstatica.Estatico.agregarError(new TError(identificador,
                            "Este Simbolo no es un Arreglo",
                            "Semantico",
                            super.getLinea(),
                            super.getColumna(),
                            Boolean.FALSE,
                            super.getArchivo()
                    ));
                }
            } else {
                InfoEstatica.Estatico.agregarError(new TError(identificador,
                        "Este simbolo no existe en este ambito",
                        "Semantico",
                        super.getLinea(),
                        super.getColumna(),
                        Boolean.FALSE,
                        super.getArchivo()
                ));
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica",
                    "Error al traducir la asignacion de arreglo: " + e.getMessage(),
                    "Ejecucion",
                    super.getLinea(),
                    super.getColumna(),
                    Boolean.FALSE,
                    super.getArchivo()
            ));
        }
        return "";
    }

    private ArrayList<String> getCoordenadas(Ambito ambito)
   {
       ArrayList<String> coordenadas = new ArrayList<>();
       try {
           for(Expresion e :this.dimensiones)
           {
               String code = (String) ((NodoAST) e).generateByteCode(ambito);
               String tipo = e.getTipo(ambito);
               if(tipo.equals("ENTERO"))
               {
                   coordenadas.add(code);
               }
               else
               {
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
       return coordenadas;
   }
    
    private String calcularPosicion(ArrayList<String> coordenadas, ArrayList<String> dimensiones)
   {
       String cad = "";
       String aux = "";
       int iteracion = 0;
       for(String i :coordenadas)
       {
           aux = calcularAuxliliar(dimensiones, iteracion, aux, i);
           iteracion++;
       }
       return aux;
   }
   
   private String calcularAuxliliar(ArrayList<String>dimensiones, int iteracion, String anterior, String coordenada)
   {
       String cad = "";
       if(iteracion == 0)
       {
           return coordenada+"\n";
       }
       else
       {
           cad = "// CALCULO....\n";
           cad += anterior+"\n"+dimensiones.get(iteracion)+"\n"+"MULT\n"+coordenada+"\nADD\n";
       }
       return cad;
   }
   
   private ArrayList<String> getDimensiones(Ambito ambito, DppArr simbolo)
   {
       ArrayList<String> dims = new ArrayList<>();
       try {
           for(int x = 0; x < simbolo.getNumDimensiones(); x++)
           {
               String cad = "";
               if(simbolo.getAmbito().equals("Global"))
               {
                   cad += "// OBTENENIENDO GLOBAL\n";
                   cad += simbolo.getPosicionRelativa()+"\n";
                   cad += "get_local $calc //PUNTERO AL INICIO DEL ARREGLO\n";
               }
               else
               {
                   cad += "// OBTENIENDO LOCAL\n";
                   cad += "get_local 0\n";
                   cad += simbolo.getPosicionRelativa()+"\n";
                   cad += "ADD\n";
                   cad += "get_local $calc\n";
               }
               cad += x+"// NUMERO DE DIMENSION QUE QUIERO TOMAR\n";
               cad += "ADD\n";
               cad += "get_global $calc //OBTENGO LA DIMENSION\n";
               dims.add(cad);
           }
       } catch (Exception e) {
       }
       return dims;
   }
}
