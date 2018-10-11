/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Valores;
import Abstraccion.*;
import ErrorManager.TError;
import Simbolos.Ambito;
/**
 *
 * @author richard
 */
public class Operacion extends NodoAST implements Expresion {
    Expresion op1, op2;
    InfoEstatica.Estatico.OPERADORES operador;
    Boolean esUnario;
    /**
     * Constructor para operaciones binarias
     * @param linea
     * @param columna
     * @param Archivo
     * @param op1
     * @param op2
     * @param operador
     */
    public Operacion(int linea, int columna, String Archivo, Expresion op1, Expresion op2, InfoEstatica.Estatico.OPERADORES operador) {
        super(linea, columna, Archivo);
        this.op1 = op1;
        this.op2 = op2;
        this.operador = operador;
        this.esUnario = false;
    }
    /**
     * Constructor para operaciones no binarias
     * @param linea
     * @param columna
     * @param archivo
     * @param op1
     * @param operador 
     */
    public Operacion(int linea, int columna, String archivo, Expresion op1, InfoEstatica.Estatico.OPERADORES operador)
    {
        super(linea, columna, archivo);
        this.op1 = op1;
        this.operador = operador;
        this.esUnario = true;
    }
    
    /**
     * Traductor del ByteCode para una operacion
     * @param ambito Lugar donde se encuentra la operacion
     * @return  el codigo bytecode generado
     */
    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            if(esUnario)
            {
                String codigo = getCodigo(op1, ambito);
                switch(operador)
                {
                    case NOT:
                    {
                        break;
                    }
                    case MENOS:
                    {
                        return TraduceNegativo(op1, codigo, ambito);
                    }
                    case INC:
                    {
                        return TraduceAumento(op1, codigo, ambito);
                    }
                    case DEC:
                    {
                        return TraduceDecremento(op1, codigo, ambito);
                    }
                }
            }
            else
            {
                String codigo =  getCodigo(op1, ambito);
                codigo += getCodigo(op2, ambito);
                switch(operador)
                {
                    case MAS:
                    {
                        return TraduceSuma(op1, op2, codigo, ambito);
                    }
                    case MENOS:
                    {
                        return TraduceResta(op1, op2, codigo, ambito);
                    }
                    case MULT:
                    {
                        return TraduceMultiplicacion(op1, op2, codigo, ambito);
                    }
                    case DIV:
                    {
                        return TraduceDivision(op1, op2, codigo, ambito);
                    }
                }
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al Traducir Operacion: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
        }
        return "";
    }
    
    private String getCodigo(Expresion op1, Ambito ambito)
    {
        NodoAST aux = (NodoAST)op1;
        aux.generateByteCode(ambito);
        String tipo = op1.getTipo(ambito);
        switch(tipo)
        {
            case "ENTERO":
            {
                return "1";
            }
            case "DECIMAL":
            {
                return "5";
            }
            case "CADENA":
            {
                return "2";
            }
            case "BOOLEAN":
            {
                return "3";
            }
            case "CARACTER":
            {
                return "4";
            }
        }
        return "0";
    }
    
    String resultado = "";
    @Override
    public String getTipo(Ambito ambito) {
        return resultado;
    }

    @Override
    public Object getValor(Ambito ambito) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //**************************************************************************************
    //*                                      SUMA                                          *
    //**************************************************************************************
    //*               |    ENTERO    |   CADENA   |    BOOLEAN   |   CARACTER  |   DECIMAL *
    //*-------------------------------------------------------------------------------------
    //*    ENTERO     |      11      |    12      |      13      |     14      |     15    *
    //*------------------------------------------------------------------------------------*
    //*    CADENA     |      21      |    22      |      23      |     24      |     25    *
    //*------------------------------------------------------------------------------------*
    //*    BOOLEAN    |      31      |    32      |      33      |     00      |     35    *
    //*------------------------------------------------------------------------------------*
    //*    CARACTER   |      41      |    42      |      00      |     00      |     45    *
    //*------------------------------------------------------------------------------------*
    //*    DECIMAL    |      51      |    52      |      53      |     54      |     55    *
    //*------------------------------------------------------------------------------------*
    private Object TraduceSuma(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        try 
        {
            // NOTA QUE FALTA LA OPERACION CON CADENAS
            String tipo1 = op1.getTipo(ambito);
            String tipo2 = op2.getTipo(ambito);
            NodoAST valor1 = (NodoAST)op1;
            String t1 = (String)valor1.generateByteCode(ambito);
            NodoAST valor2 = (NodoAST)op2;
            String t2 = (String)valor2.generateByteCode(ambito);
            switch(codigo)
            {
                
                case "11":
                {
                    
                    resultado = "ENTERO";
                    return t1+"\n"+t2+"\n"+"ADD\n";
                }
                case "13":
                {
                    resultado = "ENTERO";
                    return t1+"\n"+t2+"\n"+"ADD\n";
                }
                case "14":
                {
                    resultado = "ENTERO";
                    return t1+"\n"+t2+"\n"+"ADD\n";
                }
                case "15":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"ADD\n";
                }
                case "31":
                {
                    resultado = "ENTERO";
                    return t1+"\n"+t2+"\n"+"ADD\n";
                }
                case "33":
                {
                    resultado = "BOOLEAN";
                    return t1+"\n"+t2+"\n"+"ADD\n";
                }
                case "35":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"ADD\n";
                }
                case "41":
                {
                    resultado = "ENTERO";
                    return t1+"\n"+t2+"\n"+"ADD\n";
                }
                case "45":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"ADD\n";
                }
                case "51":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"ADD\n";
                }
                case "53":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"ADD\n";
                }
                case "54":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"ADD\n";
                }
                case "55":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"ADD\n";
                }
                default:
                {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1+"+"+tipo2, "Error La suma entre tipos indicados no es compatible", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
                }
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al realizar la suma: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
        }
        return "";
    }
    
    //**************************************************************************************
    //*                                      RESTA                                         *
    //**************************************************************************************
    //*               |    ENTERO    |   CADENA   |    BOOLEAN   |   CARACTER  |   DECIMAL *
    //*-------------------------------------------------------------------------------------
    //*    ENTERO     |      11      |    00      |      13      |     14      |     15    *
    //*------------------------------------------------------------------------------------*
    //*    CADENA     |      00      |    00      |      00      |     00      |     00    *
    //*------------------------------------------------------------------------------------*
    //*    BOOLEAN    |      31      |    00      |      00      |     00      |     35    *
    //*------------------------------------------------------------------------------------*
    //*    CARACTER   |      41      |    00      |      00      |     00      |     45    *
    //*------------------------------------------------------------------------------------*
    //*    DECIMAL    |      51      |    00      |      53      |     54      |     55    *
    //*------------------------------------------------------------------------------------*
    private Object TraduceResta(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        try 
        {
            String tipo1 = op1.getTipo(ambito);
            String tipo2 = op2.getTipo(ambito);
            NodoAST valor1 = (NodoAST)op1;
            String t1 = (String)valor1.generateByteCode(ambito);
            NodoAST valor2 = (NodoAST)op2;
            String t2 = (String)valor2.generateByteCode(ambito);
            switch(codigo)
            {
                case "11":
                {
                    resultado = "ENTERO";
                    return t1+"\n"+t2+"\n"+"DIFF\n";
                }
                case "13":
                {
                    resultado = "ENTERO";
                    return t1+"\n"+t2+"\n"+"DIFF\n";
                }
                case "14":
                {
                    resultado = "ENTERO";
                    return t1+"\n"+t2+"\n"+"DIFF\n";
                }
                case "15":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIFF\n";
                }
                case "31":
                {
                    resultado = "ENTERO";
                    return t1+"\n"+t2+"\n"+"DIFF\n";
                }
                case "35":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIFF\n";
                }
                case "41":
                {
                    resultado = "ENTERO";
                    return t1+"\n"+t2+"\n"+"DIFF\n";
                }
                case "45":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIFF\n";
                }
                case "51":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIFF\n";
                }
                case "53":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIFF\n";
                }
                case "54":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIFF\n";
                }
                case "55":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIFF\n";
                }
                default:
                {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1+"-"+tipo2, "Error La resta entre tipos indicados no es compatible", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
                }
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al realizar la resta: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
        }
        return "";
    }

    //**************************************************************************************
    //*                                      MULT                                          *
    //**************************************************************************************
    //*               |    ENTERO    |   CADENA   |    BOOLEAN   |   CARACTER  |   DECIMAL *
    //*-------------------------------------------------------------------------------------
    //*    ENTERO     |      11      |    00      |      13      |     14      |     15    *
    //*------------------------------------------------------------------------------------*
    //*    CADENA     |      00      |    00      |      00      |     00      |     00    *
    //*------------------------------------------------------------------------------------*
    //*    BOOLEAN    |      31      |    00      |      33      |     00      |     35    *
    //*------------------------------------------------------------------------------------*
    //*    CARACTER   |      41      |    00      |      00      |     00      |     45    *
    //*------------------------------------------------------------------------------------*
    //*    DECIMAL    |      51      |    00      |      53      |     54      |     55    *
    //*------------------------------------------------------------------------------------*
    private Object TraduceMultiplicacion(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        try 
        {
            String tipo1 = op1.getTipo(ambito);
            String tipo2 = op2.getTipo(ambito);
            NodoAST valor1 = (NodoAST)op1;
            String t1 = (String)valor1.generateByteCode(ambito);
            NodoAST valor2 = (NodoAST)op2;
            String t2 = (String)valor2.generateByteCode(ambito);
            switch(codigo)
            {
                case "11":
                {
                    resultado = "ENTERO";
                    return t1+"\n"+t2+"\n"+"MULT\n";
                }
                case "13":
                {
                    resultado = "ENTERO";
                    return t1+"\n"+t2+"\n"+"MULT\n";
                }
                case "14":
                {
                    resultado = "ENTERO";
                    return t1+"\n"+t2+"\n"+"MULT\n";
                }
                case "15":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"MULT\n";
                }
                case "31":
                {
                    resultado = "ENTERO";
                    return t1+"\n"+t2+"\n"+"MULT\n";
                }
                case "33":
                {
                    resultado = "BOOLEAN";
                    return t1+"\n"+t2+"\n"+"MULT\n";
                }
                case "35":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"MULT\n";
                }
                case "41":
                {
                    resultado = "ENTERO";
                    return t1+"\n"+t2+"\n"+"MULT\n";
                }
                case "45":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"MULT\n";
                }
                case "51":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"MULT\n";
                }
                case "53":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"MULT\n";
                }
                case "54":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"MULT\n";
                }
                case "55":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"MULT\n";
                }
                default:
                {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1+"*"+tipo2, "Error La Multiplicacion entre tipos indicados no es compatible", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
                }
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejecutar la multiplicacion: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
        }
        return "";
    }
    
    //**************************************************************************************
    //*                                      DIV                                           *
    //**************************************************************************************
    //*               |    ENTERO    |   CADENA   |    BOOLEAN   |   CARACTER  |   DECIMAL *
    //*-------------------------------------------------------------------------------------
    //*    ENTERO     |      11      |    00      |      13      |     14      |     15    *
    //*------------------------------------------------------------------------------------*
    //*    CADENA     |      00      |    00      |      00      |     00      |     00    *
    //*------------------------------------------------------------------------------------*
    //*    BOOLEAN    |      31      |    00      |      00      |     00      |     35    *
    //*------------------------------------------------------------------------------------*
    //*    CARACTER   |      41      |    00      |      00      |     00      |     45    *
    //*------------------------------------------------------------------------------------*
    //*    DECIMAL    |      51      |    00      |      53      |     54      |     55    *
    //*------------------------------------------------------------------------------------*
    private Object TraduceDivision(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        try 
        {
            String tipo1 = op1.getTipo(ambito);
            String tipo2 = op2.getTipo(ambito);
            NodoAST valor1 = (NodoAST)op1;
            String t1 = (String)valor1.generateByteCode(ambito);
            NodoAST valor2 = (NodoAST)op2;
            String t2 = (String)valor2.generateByteCode(ambito);
            switch(codigo)
            {
                case "11":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIV\n";
                }
                case "13":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIV\n";
                }
                case "14":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIV\n";
                }
                case "15":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIV\n";
                }
                case "31":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIV\n";
                }
                case "35":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIV\n";
                }
                case "41":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIV\n";
                }
                case "45":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIV\n";
                }
                case "51":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIV\n";
                }
                case "53":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIV\n";
                }
                case "54":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIV\n";
                }
                case "55":
                {
                    resultado = "DECIMAL";
                    return t1+"\n"+t2+"\n"+"DIV\n";
                }
                default:
                {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1+"/"+tipo2, "Error La Divison entre tipos indicados no es compatible", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
                }
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Erro al ejecutar la division: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
        }
        return "";
    }

    //*****************************************
    //*                AUMENTO                *
    //*****************************************
    //*  ENTERO   |       1                   *
    //*****************************************
    //*  DECIMAL  |       5                   *
    //*****************************************
    private Object TraduceAumento(Expresion op1, String codigo, Ambito ambito)
    {
        try 
        {
            String tipo1 = op1.getTipo(ambito);
            NodoAST valor1 = (NodoAST)op1;
            String t1 = (String)valor1.generateByteCode(ambito);
            switch(codigo)
            {
                case "1":
                {
                    resultado = "ENTERO";
                    return t1+"\n1\nADD\n";
                }
                case "5":
                {
                    resultado = "DECIMAL";
                    return t1+"\n1\nADD\n";
                }
                default:
                {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1+"++", "No es posible aumentar el tipo indicado", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
                }
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error de Traducir un aumento: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
        }
        return "";
    }
    
    //*****************************************
    //*                DECREMENTO             *
    //*****************************************
    //*  ENTERO   |       1                   *
    //*****************************************
    //*  DECIMAL  |       5                   *
    //*****************************************
    private Object TraduceDecremento(Expresion op1, String codigo, Ambito ambito)
    {
        try 
        {
            String tipo1 = op1.getTipo(ambito);
            NodoAST valor1 = (NodoAST)op1;
            String t1 = (String)valor1.generateByteCode(ambito);
            switch(codigo)
            {
                case "1":
                {
                    resultado = "ENTERO";
                    return t1+"\n1\nDIFF";
                }
                case "5":
                {
                    resultado = "DECIMAL";
                    return t1+"\n1\nDIFF";
                }
                default:
                {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1+"--", "No es posible decrementar el tipo indicado", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
                }
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al Traducir el Decremento: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
        }
        return "";
    }
    
    //*****************************************
    //*                NEGATIVO               *
    //*****************************************
    //*  ENTERO   |       1                   *
    //*****************************************
    //*  DECIMAL  |       5                   *
    //*****************************************
    private Object TraduceNegativo(Expresion op1, String codigo, Ambito ambito)
    {
        try 
        {
            String tipo1 = op1.getTipo(ambito);
            NodoAST valor1 = (NodoAST)op1;
            String t1 = (String)valor1.generateByteCode(ambito);
            switch(codigo)
            {
                case "1":
                {
                    resultado = "ENTERO";
                    return t1+"\n-1\nMULT\n";
                }
                case "2":
                {
                    resultado = "DECIMAL";
                    return t1+"\n-1\nMULT\n";
                }
                default:
                {
                    InfoEstatica.Estatico.agregarError(new TError("-"+tipo1, "No es posible aplicar Negativo al tipo indicado", "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
                }
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al traducir el Negativo: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
        }
        return "";
    }
} 
