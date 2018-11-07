/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Valores;

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
public class AccesoArreglo extends NodoAST implements Expresion{
    ArrayList<Expresion> coordenada;
    String id;
    public AccesoArreglo(int linea, int columna, String Archivo, String id, ArrayList<Expresion> coordenada) {
        super(linea, columna, Archivo);
        this.id = id;
        this.coordenada = coordenada;
        
    }
    String tipo;
    @Override
    public String getTipo(Ambito ambito) {
        return this.tipo;
    }

    @Override
    public Object getValor(Ambito ambito) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            if(ambito.existeVariable(id))
            {
                Simbolo s = ambito.getSimbolo(id);
                if(s instanceof DppArr)
                {
                    DppArr arr = (DppArr)s;
                    if(arr.getNumDimensiones() == this.coordenada.size())
                    {
                        String cad = "/*     OBTENIENDO VALOR DE ARREGLO         */\n";
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
                        cad += "get_global $calc // OBTENGO EL VALOR DEL ARREGLO\n";
                        this.tipo = arr.getTipo();
                        return cad;
                    }
                    else
                    {
                        InfoEstatica.Estatico.agregarError(new TError(
                                id+" tiene dimensiones: "+arr.getNumDimensiones()+" | se encontraron: "+coordenada.size(), 
                                "El Arreglo especificado no coinciden las dimensiones", 
                                "Semantico", 
                                super.getLinea(), 
                                super.getColumna(), 
                                Boolean.FALSE, 
                                super.getArchivo()
                        ));
                    }
                }
                else
                {
                    InfoEstatica.Estatico.agregarError(new TError(id, 
                            "El simbolo indicado no es un arreglo: "+id, "Semantico", 
                            super.getLinea(), 
                            super.getColumna(), 
                            Boolean.FALSE, 
                            super.getArchivo()
                    ));
                }
            }
            else
            {
                InfoEstatica.Estatico.agregarError(new TError(
                        id, 
                        "No Existe el Simbolo: "+id+" en este ambito", 
                        "Semantico", 
                        super.getLinea(), 
                        super.getColumna(), 
                        Boolean.FALSE, 
                        super.getArchivo()
                ));
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError(
                    "No Aplica", 
                    "Error al traducir el acceso al arreglo: "+e.getMessage(), 
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
           for(Expresion e :this.coordenada)
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
}
