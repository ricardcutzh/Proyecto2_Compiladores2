/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Sentencias;

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
public class AsignacionMiembroArreglo extends NodoAST {

    String id1;
    String id2;
    ArrayList<Expresion> dimensiones;
    Expresion e;

    public AsignacionMiembroArreglo(int linea, int columna, String Archivo, String id1, String id2, ArrayList<Expresion> dimensiones, Expresion e) {
        super(linea, columna, Archivo);
        this.id1 = id1;
        this.id2 = id2;
        this.dimensiones = dimensiones;
        this.e = e;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try {
            if (ambito.existeVariable(id1)) {
                Simbolo s = ambito.getSimbolo(id1);
                if (s instanceof DppVar) {
                    DppVar v = (DppVar) s;
                    if (!esPrimitivo(v.getTipo())) {
                        Ambito auxiliar = getGlobal(ambito);
                        Struct es = auxiliar.getStruct(v.getTipo());
                        if(es!=null)
                        {
                            if(es.existeMiembro(id2))
                            {
                                NodoMiembro m = es.getByID(id2);
                                String expCode = (String) ((NodoAST) e).generateByteCode(ambito);// GENERO EL CODIGO RELACIONADO A LA EXPRESION
                                String tipoObtenido = e.getTipo(ambito);
                                if(tipoObtenido.equals(m.getTipo()))
                                {
                                    String cad = "/*ASIGNANDO A UN MIEMBRO QUE ES ARREGLO\n*/\n";
                                    if(v.getAmbito().equals("Global"))
                                    {
                                        cad += v.getPosicionRelativa()+"// POSICION ABSOLUTA EN EL STACK\n";
                                    }
                                    else
                                    {
                                        cad += "get_local 0 // UTILIZANDO EL PUNTERO DEL STACK\n";
                                        cad += v.getPosicionRelativa()+" // POSICION DEL LA ESTRUCTURA\n";
                                        cad += "ADD\n";
                                    }
                                    cad += "get_local $calc // OBTENGO EL PUNTERO DE LA ESTRUCTURA \n";
                                    cad += (m.getIndiceMiembro()-1)+"\n";
                                    cad += "ADD // ENCONTRANDO LA POSICION DEL MIEMBRO QUE NECESITO\n";
                                    cad += "get_global $calc // ENCONTRANDO EL PUNTERO DONDE ESTA EL ARREGLO\n";
                                    cad += "// ENCONTRANDO LA POSICION DONDE INICIA EL ARREGLO\n";
                                    cad += (this.dimensiones.size() - 1)+"\n";
                                    cad += "ADD\n";
                                    cad += "////////////////////////////////////////////////////\n";
                                    ArrayList<String> coordenadas = getCoordenadas(ambito);
                                    ArrayList<String> dimensiones = getDims(ambito, v, m);
                                    cad += "// CALCULANDO POSICION\n";
                                    cad += calcularPosicion(coordenadas, dimensiones);
                                    cad += "1\nADD\n";
                                    cad += "// FIN CALCULANDO POSICION\n";
                                    cad += "ADD // PARA ENCONTRAR LA POSICION ABSOLUTA\n";
                                    cad += "// VALOR ASIGNAR\n";
                                    cad += expCode+"\n";
                                    cad += "set_global $calc\n";
                                    return cad;
                                }
                                else
                                {
                                    InfoEstatica.Estatico.agregarError(new TError(m.getTipo()+"="+tipoObtenido, 
                                            "Tipos no se pueden asignar", 
                                            "Semantico", 
                                            super.getLinea(), super.getColumna(), 
                                            Boolean.FALSE, 
                                            super.getArchivo()));
                                }
                            }
                            else
                            {
                                InfoEstatica.Estatico.agregarError(new TError(id1+" de tipo: "+v.getTipo(), 
                                    "No tiene definido un miembro: "+id2, 
                                    "Semantico", 
                                    super.getLinea(), 
                                    super.getColumna(), 
                                    Boolean.FALSE, 
                                    super.getArchivo()));
                            }
                        }
                        else
                        {
                            InfoEstatica.Estatico.agregarError(new TError(id1+" de tipo: "+v.getTipo(), 
                                    "No es una estructura que exista o este definida", 
                                    "Semantico", 
                                    super.getLinea(), 
                                    super.getColumna(), 
                                    Boolean.FALSE, 
                                    super.getArchivo()));
                        }
                    } else {
                        InfoEstatica.Estatico.agregarError(new TError(
                                "Simbolo: " + id1,
                                "El simbolo especificado no es una estructura ",
                                "Ejecucion",
                                super.getLinea(),
                                super.getColumna(),
                                Boolean.FALSE, super.getArchivo()));
                    }
                } else {
                    InfoEstatica.Estatico.agregarError(new TError(
                            "Simbolo: " + id1,
                            "El simbolo especificado no es una estructura ",
                            "Ejecucion",
                            super.getLinea(),
                            super.getColumna(),
                            Boolean.FALSE, super.getArchivo()));
                }
            } else {
                InfoEstatica.Estatico.agregarError(new TError(
                        id1,
                        "El Simbolo indicado no existe en este ambito: " + id1,
                        "Semantico",
                        super.getLinea(),
                        super.getColumna(),
                        Boolean.FALSE, super.getArchivo()));
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError(
                    "No Aplica",
                    "Error al traducir asignacion de miembro arreglo: " + e.getMessage(),
                    "Ejecucion",
                    super.getLinea(),
                    super.getColumna(),
                    Boolean.FALSE, super.getArchivo()));
        }
        return "";
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
}
