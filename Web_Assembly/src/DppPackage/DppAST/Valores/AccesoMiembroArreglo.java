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
import Simbolos.DppArr;
import Simbolos.DppVar;
import Simbolos.Simbolo;
import Simbolos.Struct;
import java.util.ArrayList;

/**
 *
 * @author richard
 */
public class AccesoMiembroArreglo extends NodoAST implements Expresion{
    String id1;
    String id2;
    ArrayList<Expresion>dimensiones;
    public AccesoMiembroArreglo(int linea, int columna, String Archivo, String id1, String id2, ArrayList<Expresion>dimensiones) {
        super(linea, columna, Archivo);
        this.id1 = id1;
        this.id2 = id2;
        this.dimensiones = dimensiones;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            if(ambito.existeVariable(id1))
            {
                Simbolo s = ambito.getSimbolo(id1);
                if(s instanceof DppVar)
                {
                    DppVar v = (DppVar)s;
                    if(!esPrimitivo(v.getTipo()))
                    {
                        Ambito auxiliar = getGlobal(ambito);
                        Struct ss = auxiliar.getStruct(v.getTipo());
                        if(ss!=null)
                        {
                            if(ss.existeMiembro(id2))
                            {
                                NodoMiembro m = ss.getByID(id2);
                                String cad = "/*    TOMANDO EL VALOR DEL SIMBOLO QUE ES EL PUNTERO A ESTRUCTURA    */\n";
                                if(((DppVar) s).getAmbito().equals("Global"))
                                {
                                    cad += v.getPosicionRelativa()+"\n";
                                }
                                else
                                {
                                    cad += "get_local 0\n";
                                    cad += v.getPosicionRelativa()+"\n";
                                    cad += "ADD\n";
                                }
                                cad += "get_local $calc\n"; // OBTENGO EL PUNTERO AL INICIO DE LA ESTRUCTURA
                                cad += (m.getIndiceMiembro()-1)+"\n";
                                cad += "ADD\n";
                                cad += "get_global $calc\n";// OBTENGO EL PUNTERO DEL ARREGLO
                                cad += "// SUMANDOLE PARA OBTENER EL INICIO DEL ARREGLO\n";
                                cad += (m.getDimensiones().size()-1)+"\n";
                                cad += "ADD\n";
                                cad += "////////////////////////////////////////////////////\n";
                                ArrayList<String> coordenadas = getCoordenadas(ambito);
                                ArrayList<String> dimensiones = getDims(ambito, v, m);
                                cad += "// CALCULANDO POSICION\n";
                                cad += calcularPosicion(coordenadas, dimensiones);
                                cad += "1\nADD\n";
                                cad += "// FIN CALCULANDO POSICION\n";
                                cad += "ADD // PARA ENCONTRAR LA POSICION ABSOLUTA\n";
                                cad += "/*OBTENENIENDO EL VALOR: */\n";
                                cad += "get_global $calc\n";
                                this.tipo = m.getTipo();
                                return cad;
                            }
                            else
                            {
                                InfoEstatica.Estatico.agregarError(new TError(id2, 
                                    "El miembro: "+id2+" no es parte de la estructura: "+id1, 
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
                            InfoEstatica.Estatico.agregarError(new TError(id1, 
                                "El simbolo especificado no hace referencia a una estructura", 
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
                        InfoEstatica.Estatico.agregarError(new TError(id1, 
                            "El simbolo especificado no hace referencia a una estructura", 
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
                    InfoEstatica.Estatico.agregarError(new TError(id1, 
                            "El simbolo especificado no hace referencia a una estructura", 
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
                InfoEstatica.Estatico.agregarError(new TError(id1, 
                        "El Simbolo especificado no existe en este ambito", 
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
                    "Error al traducir el acceso a miembro Arreglo: "+e.getMessage(), 
                    "Ejecucion", 
                    super.getLinea(), 
                    super.getColumna(), 
                    Boolean.FALSE, 
                    super.getArchivo()
            ));
        }
        return "";
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
   
   private ArrayList<String> getDims(Ambito ambito, DppVar simbolo, NodoMiembro m)
   {
       ArrayList<String> dims = new ArrayList<>();
       try {
           for(int x = 0; x < this.dimensiones.size();x++)
           {
               String cad = "";
               if(simbolo.getAmbito().equals("GLobal"))
               {
                   cad += simbolo.getPosicionRelativa()+"\n";
               }
               else
               {
                   cad += "get_local 0\n";
                   cad += simbolo.getPosicionRelativa()+"\n";
                   cad += "ADD\n";
               }
               cad += "get_local $calc\n";
               cad += (m.getIndiceMiembro()-1)+"\n";
               cad += "ADD // OBTENIENDO DONDE INICIA EL ARREGLO \n";
               cad += x+"//NUMERO DE DIMENSION QUE QUIERO TOMAR\n";
               cad += "get_global $calc // OBTENGO LA DIMENSION\n";
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
   
   private Boolean esPrimitivo(String tipo) {
        switch (tipo) {
            case "ENTERO": {
                return true;
            }
            case "DECIMAL": {
                return true;
            }
            case "CARACTER": {
                return true;
            }
            case "BOOLEAN": {
                return true;
            }
            case "CADENA": {
                return true;
            }
            default: {
                return false;
            }
        }
    }
   
   private Ambito getGlobal(Ambito actual) {
        Ambito aux = actual;
        while (aux.getAnterior() != null) {
            aux = aux.getAnterior();
        }
        return aux;
    }
}
