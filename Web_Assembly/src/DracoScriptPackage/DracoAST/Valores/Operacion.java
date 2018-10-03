/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DracoScriptPackage.DracoAST.Valores;
import Abstraccion.*;
import ErrorManager.TError;
import InfoEstatica.Estatico;
import ObjsComun.Nulo;
import Simbolos.Ambito;
/**
 * Clase que maneja las expresiones que tienen Operaciones
 * @author richard
 */
public class Operacion extends NodoAST implements Expresion {
    
    Expresion op1;
    Expresion op2;
    Estatico.OPERADORES operador;
    Boolean esUnario;
    /**
     * Constructor para las operaciones Binarias
     * @param linea  linea donde se encuentra al Expresion
     * @param columna columna donde se encuentra la expresion
     * @param Archivo Archivo donde se esta realizando
     * @param op1 Expresion izquierda
     * @param op2 Expresion derecha
     * @param operador Operador que va a decir que tipo de operacion se realizara
     */
    public Operacion(int linea, int columna, String Archivo, Expresion op1, Expresion op2, Estatico.OPERADORES operador) {
        super(linea, columna, Archivo);
        esUnario = false;
        this.op1 = op1;
        this.op2 = op2;
        this.operador = operador;
    }
    
    /**
     * Constructor para los operaciones Unarias
     * @param linea linea donde se encuentra al Expresion
     * @param columna columna donde se encuentra la expresion
     * @param Archivo Archivo donde se esta realizando
     * @param op1 unica expresion
     * @param operador OPerador Unario
     */
    public Operacion(int linea, int columna, String Archivo, Expresion op1, Estatico.OPERADORES operador)
    {
        super(linea, columna, Archivo);
        esUnario = true;
        this.op1 = op1;
        this.operador = operador;
    }
    
    //****************************************************
    //*                 CODIGO DE TIPOS                  *
    //****************************************************
    //* BOOLEAN        |          1                      *
    //* CADENA         |          2                      *
    //* NUMERICO       |          3                      *
    //* CARACTER       |          4                      *
    //****************************************************
    
    /**
     * Metodo privado que me ayudara en la contruccion de las operaciones aritmeticas
     * @param op  operador que necesito saber su tipo
     * @param ambito Ambito en el que se encuentra
     * @return Cadena con el codigo de la operacion a efectuar
     */
    private String getCodigo(Expresion op, Ambito ambito)
    {
        Object val = op.getValor(ambito);
        String tipo = op.getTipo(ambito);
        switch(tipo)
        {
            case "NUMERICO":
            {
                return "3";
            }
            case "BOOLEAN":
            {
                return "1";
            }
            case "CADENA":
            {
                return "2";
            }
            case "CARACTER":
            {
                return "4";
            }
        }
        return"";
    }
    
    Object valorAux = null;
    @Override
    public String getTipo(Ambito ambito) {
        if(this.valorAux == null)
        {
            valorAux = getValor(ambito);
        }
        ManejadorTipo m = new ManejadorTipo(valorAux);
        return m.evaluaValor();
    }

    @Override
    public Object getValor(Ambito ambito) {
        try {
            if(esUnario)
            {
                String codigo = getCodigo(op1, ambito);
                switch(operador)
                {
                    case INC:
                    {
                        this.valorAux = HazAumento(op1, codigo, ambito);
                        return this.valorAux;
                    }
                    case DEC:
                    {
                        this.valorAux = HazDecremento(op1, codigo, ambito);
                        return this.valorAux;
                    }
                    case NOT:
                    {
                        this.valorAux = HazNot(op1, codigo, ambito);
                        return valorAux;
                    }
                    case MENOS:
                    {
                        this.valorAux = HazMenos(op1, codigo, ambito);
                        return this.valorAux;
                    }
                }
            }
            else
            {
                String codigo1 = getCodigo(op1, ambito);
                codigo1 += getCodigo(op2, ambito);
                switch(operador)
                {
                    case MAS:
                    {
                        this.valorAux = HazSuma(op1, op2, codigo1, ambito);
                        return valorAux;
                    }
                    case MENOS:
                    {
                        this.valorAux = HazResta(op1, op2, codigo1, ambito);
                        return valorAux;
                    }
                    case MULT:
                    {
                        this.valorAux = HazMultiplicacion(op1, op2, codigo1, ambito);
                        return valorAux;
                    }
                    case DIV:
                    {
                        this.valorAux = HazDivision(op1, op2, codigo1, ambito);
                        return valorAux;
                    }
                    case POT:
                    {
                        this.valorAux = HazPotencia(op1, op2, codigo1, ambito);
                        return valorAux;
                    }
                    case MOD:
                    {
                        this.valorAux = HazModular(op1, op2, codigo1, ambito);
                        return this.valorAux;
                    }
                    case IGUAL:
                    {
                        this.valorAux = HazIgual(op1, op2, codigo1, ambito);
                        return this.valorAux;
                    }
                    case DIFERENTE:
                    {
                        this.valorAux = HazDiferente(op1, op2, codigo1, ambito);
                        return this.valorAux;
                    }
                    case MAYOR:
                    {
                        this.valorAux = HazMayor(op1, op2, codigo1, ambito);
                        return this.valorAux;
                    }
                    case MENOR:
                    {
                        this.valorAux = HazMenor(op1, op2, codigo1, ambito);
                        return this.valorAux;
                    }
                    case MAYORIGUAL:
                    {
                        this.valorAux = MayorIgual(op1, op2, codigo1, ambito);
                        return this.valorAux;
                    }
                    case MENORIGUAL:
                    {
                        this.valorAux = MenorIgual(op1, op2, codigo1, ambito);
                        return this.valorAux;
                    }
                    case AND:
                    {
                        this.valorAux = HazAnd(op1, op2, codigo1, ambito);
                        return this.valorAux;
                    }
                    case OR:
                    {
                        this.valorAux = HazOr(op1, op2, codigo1, ambito);
                        return this.valorAux;
                    }
                }
            }
        } catch (Exception e) {
            TError error = new TError(e.getMessage(), e.getMessage(), "Ejecucion", super.getLinea(),super.getColumna(), false, ambito.getArchivo());
            InfoEstatica.Estatico.agregarError(error);
        }
        return new Nulo();
    }
    
    
    //**********************************************************************************************
    //*                                    SUMA                                                    *
    //**********************************************************************************************
    //*                 | BOOLEAN        |     CADENA     |       NUMERICO   |       CARACTER      *
    //**********************************************************************************************
    //*   BOOLEAN       |      11        |      12        |          13      |        14           *
    //---------------------------------------------------------------------------------------------*
    //*   CADENA        |      21        |      22        |          23      |        24           *
    //---------------------------------------------------------------------------------------------*
    //    NUMERICO      |      31        |      32        |          33      |        34           *
    //---------------------------------------------------------------------------------------------*
    //*   CARACTER      |      41        |      42        |          43      |        44           *
    //---------------------------------------------------------------------------------------------*
    
    /**
     * Metodo privado que se encarga de manejar la suma
     * @param op1 operando1
     * @param op2 operando2
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object HazSuma(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        
        String tipo1 = op1.getTipo(ambito);
        String tipo2 = op2.getTipo(ambito);
        try 
        {
            Object val1 = op1.getValor(ambito);
            Object val2 = op2.getValor(ambito);
            switch(codigo)
            {
                case "11":
                {
                    Boolean valor1 = (Boolean)val1;
                    Boolean valor2 = (Boolean)val2;
                    this.valorAux = valor1 || valor2;
                    return valorAux;
                }
                case "12":
                {
                    Boolean valor1 = (Boolean)val1;
                    String valor2 = (String)val2;
                    this.valorAux = String.valueOf(valor1) + valor2;
                    return valorAux;
                }
                case "13":
                {
                    Boolean valor1 = (Boolean)val1;
                    Double valor2 = Double.parseDouble(val2.toString());
                    Double aux = 0.0;
                    if(valor1){aux = 1.0;}
                    valorAux = aux + valor2;
                    return valorAux;
                }
                case "14":
                {
                    Boolean valor1 = (Boolean)val1;
                    Character valor2 = (Character)val2;
                    Double aux = 0.0;
                    if(valor1){aux = 1.0;}
                    Double aux2 = Double.valueOf(Character.getNumericValue(valor2));
                    valorAux = aux + aux2;
                    return valorAux;
                }
                case "21":
                {
                    String valor1 = (String)val1;
                    Boolean valor2 = (Boolean)val2;
                    valorAux = valor1 + String.valueOf(valor2);
                    return valorAux;
                }
                case "22":
                {
                    String valor1 = (String)val1;
                    String valor2 = (String)val2;
                    valorAux = valor1 + valor2;
                    return valorAux;
                }
                case "23":
                {
                    String valor1 = (String)val1;
                    Double valor2 = Double.parseDouble(val2.toString());
                    valorAux = valor1 + String.valueOf(valor2);
                    return valorAux;
                }
                case "24":
                {
                    String valor1 = (String)val1;
                    Character valor2 = (Character)val2;
                    valorAux = valor1 + String.valueOf(valor2);
                    return valorAux;
                }
                case "31":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Boolean valor2 = (Boolean)val2;
                    Double aux = 0.0;
                    if(valor2){aux = 1.0;}
                    valorAux = valor1 + aux;
                    return valorAux;
                }
                case "32":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    String valor2 = (String)val2;
                    valorAux = String.valueOf(valor1) + valor2;
                    return valorAux;
                }
                case "33":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Double valor2 = Double.parseDouble(val2.toString());
                    valorAux = valor1 + valor2;
                    return valorAux;
                }
                case "34":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Character valor2 = (Character)val2;
                    Double aux = Double.valueOf(Character.getNumericValue(valor2));
                    valorAux = valor1 + aux;
                    return valorAux;
                }
                case "41":
                {
                    Character valor1 = (Character)val1;
                    Boolean valor2 = (Boolean)val2;
                    Double aux1 = 0.0;
                    if(valor2){aux1 = 1.0;}
                    Double aux2 = Double.valueOf(Character.getNumericValue(valor1));
                    valorAux = aux2 + aux1;
                    return valorAux;
                }
                case "42":
                {
                    Character valor1 = (Character)val1;
                    String valor2 = (String)val2;
                    valorAux = String.valueOf(valor1) + valor2;
                    return valorAux;
                }
                case "43":
                {
                    Character valor1 = (Character)val1;
                    Double valor2 = Double.parseDouble(val2.toString());
                    Double aux = Double.valueOf(Character.getNumericValue(valor1));
                    valorAux = aux + valor2;
                    return valorAux;
                }
                case "44":
                {
                    Character valor1 = (Character)val1;
                    Character valor2 = (Character)val2;
                    valorAux = String.valueOf(valor1) + String.valueOf(valor2);
                    return valorAux;
                }
                default:
                {
                    TError error = new TError(tipo1+"+"+tipo2, "No se puede realizar la suma entre tipo: "+tipo1+" y "+tipo2, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                    Estatico.agregarError(error);
                }
            }
        } catch (Exception e) 
        {
            TError error = new TError(tipo1+"+"+tipo2, "Error al Sumar: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        this.valorAux = 0;
        return 0;
    }
    
    //**********************************************************************************************
    //*                                    RESTA                                                   *
    //**********************************************************************************************
    //*                 | BOOLEAN        |     CADENA     |       NUMERICO   |       CARACTER      *
    //**********************************************************************************************
    //*   BOOLEAN       |      00        |      00        |          13      |        14           *
    //---------------------------------------------------------------------------------------------*
    //*   CADENA        |      00        |      00        |          00      |        00           *
    //---------------------------------------------------------------------------------------------*
    //    NUMERICO      |      31        |      00        |          33      |        34           *
    //---------------------------------------------------------------------------------------------*
    //*   CARACTER      |      41        |      00        |          43      |        00           *
    //---------------------------------------------------------------------------------------------*
    /**
     * Metodo privado que se encarga de manejar la resta
     * @param op1 operando1
     * @param op2 operando2
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object HazResta(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        String tipo2 = op2.getTipo(ambito);
        try
        {
            Object val1 = op1.getValor(ambito);
            Object val2 = op2.getValor(ambito);
            switch(codigo)
            {
                case "13":
                {
                    Boolean valor1 = (Boolean)val1;
                    Double valor2 = Double.parseDouble(val2.toString());
                    Double aux = 0.0;
                    if(valor1){aux = 1.0;}
                    valorAux = aux - valor2;
                    return valorAux;
                }
                case "14":
                {
                    Boolean valor1 = (Boolean)val1;
                    Character valor2 = (Character)val2;
                    Double aux = 0.0;
                    if(valor1){aux = 1.0;}
                    Double aux2 = Double.valueOf(Character.getNumericValue(valor2));
                    valorAux = aux - aux2;
                    return valorAux;
                }
                case "31":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Boolean valor2 = (Boolean)val2;
                    Double aux = 0.0;
                    if(valor2){aux = 1.0;}
                    valorAux = valor1 - aux;
                    return valorAux;
                }
                case "33":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Double valor2 = Double.parseDouble(val2.toString());
                    valorAux = valor1 - valor2;
                    return valorAux;
                }
                case "34":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Character valor2 = (Character)val2;
                    Double aux2 = Double.valueOf(Character.getNumericValue(valor2));
                    valorAux = valor1 - aux2;
                    return valorAux;
                }
                case "41":
                {
                    Character valor1 = (Character)val1;
                    Boolean valor2 = (Boolean)val2;
                    Double aux1 = Double.valueOf(Character.getNumericValue(valor1));
                    Double aux2 = 0.0;
                    if(valor2){aux2 = 1.0;}
                    valorAux = aux1 - aux2;
                    return valorAux;
                }
                case "43":
                {
                    Character valor1 = (Character)val1;
                    Double aux1 = Double.valueOf(Character.getNumericValue(valor1));
                    Double valor2 = Double.parseDouble(val2.toString());
                    valorAux = aux1 - valor2;
                    return  valorAux;
                }
                default:
                {
                    TError error = new TError(tipo1+"-"+tipo2, "No se puede realizar la Resta entre tipo: "+tipo1+" y "+tipo2, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                    Estatico.agregarError(error);
                }
            }
        }
        catch(Exception e)
        {
            TError error = new TError(tipo1+"-"+tipo2, "Error al Restar: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        this.valorAux = 0;
        return 0;
    }
    
    //**********************************************************************************************
    //*                                    Multiplicacion                                          *
    //**********************************************************************************************
    //*                 | BOOLEAN        |     CADENA     |       NUMERICO   |       CARACTER      *
    //**********************************************************************************************
    //*   BOOLEAN       |      11        |      00        |          13      |        14           *
    //---------------------------------------------------------------------------------------------*
    //*   CADENA        |      00        |      00        |          00      |        00           *
    //---------------------------------------------------------------------------------------------*
    //    NUMERICO      |      31        |      00        |          33      |        34           *
    //---------------------------------------------------------------------------------------------*
    //*   CARACTER      |      41        |      00        |          43      |        00           *
    //---------------------------------------------------------------------------------------------*
    /**
     * Metodo privado que se encarga de manejar la multiplicacion
     * @param op1 operando1
     * @param op2 operando2
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object HazMultiplicacion(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        String tipo2 = op2.getTipo(ambito);
        try 
        {
            Object val1 = op1.getValor(ambito);
            Object val2 = op2.getValor(ambito);
            switch(codigo)
            {
                case "11":
                {
                    Boolean valor1 = (Boolean)val1;
                    Boolean valor2 = (Boolean)val2;
                    return valor1 && valor2;
                }
                case "13":
                {
                    Boolean valor1 = (Boolean)val1;
                    Double valor2 = Double.parseDouble(val2.toString());
                    Double aux1 = 0.0;
                    if(valor1){aux1 = 1.0;}
                    return aux1 * valor2;
                }
                case "14":
                {
                    Boolean valor1 = (Boolean)val1;
                    Double aux1 = 0.0;
                    if(valor1){aux1 = 1.0;}
                    Character valor2 = (Character)val2;
                    Double aux2 = Double.valueOf(Character.getNumericValue(valor2));
                    return aux1 * aux2;
                }
                case "31":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Boolean valor2 = (Boolean)val2;
                    Double aux2 = 0.0;
                    if(valor2){aux2 = 1.0;}
                    return valor1 * aux2;
                }
                case "33":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Double valor2 = Double.parseDouble(val2.toString());
                    return valor1 * valor2;
                }
                case "34":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Character valor2 = (Character)val2;
                    Double aux2 = Double.valueOf(Character.getNumericValue(valor2));
                    return valor1 * aux2;
                }
                case "41":
                {
                    Character valor1 = (Character)val1;
                    Double aux1 = Double.valueOf(Character.getNumericValue(valor1));
                    Boolean valor2 = (Boolean)val2;
                    Double aux2 = 0.0;
                    if(valor2){aux2 = 1.0;}
                    return aux1 * aux2;
                }
                case "43":
                {
                    Character valor1 = (Character)val1;
                    Double aux1 = Double.valueOf(Character.getNumericValue(valor1));
                    Double valor2 = Double.parseDouble(val2.toString());
                    return aux1 * valor2;
                }
                default:
                {
                    TError error = new TError(tipo1+"*"+tipo2, "No se puede realizar la multiplicacion entre tipo: "+tipo1+" y "+tipo2, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                    Estatico.agregarError(error);
                }
            }
        } catch (Exception e) 
        {
            TError error = new TError(tipo1+"*"+tipo2, "Error al Multiplcar: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        this.valorAux = 0;
        return 0;
    }
    
    //**********************************************************************************************
    //*                                    Division                                                *
    //**********************************************************************************************
    //*                 | BOOLEAN        |     CADENA     |       NUMERICO   |       CARACTER      *
    //**********************************************************************************************
    //*   BOOLEAN       |      00        |      00        |          13      |        14           *
    //---------------------------------------------------------------------------------------------*
    //*   CADENA        |      00        |      00        |          00      |        00           *
    //---------------------------------------------------------------------------------------------*
    //    NUMERICO      |      31        |      00        |          33      |        34           *
    //---------------------------------------------------------------------------------------------*
    //*   CARACTER      |      41        |      00        |          43      |        00           *
    //---------------------------------------------------------------------------------------------*
    /**
     * Metodo privado que maneja la division
     * @param op1 operando1
     * @param op2 operando2
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object HazDivision(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        String tipo2 = op2.getTipo(ambito);
        try 
        {
            Object val1 = op1.getValor(ambito);
            Object val2 = op2.getValor(ambito);
            switch(codigo)
            {
                case "13":
                {
                    Double valor2 = Double.parseDouble(val2.toString());
                    Boolean valor1 = (Boolean)val1;
                    Double aux2 = 0.0;
                    if(valor1){aux2 = 1.0;}
                    try {
                        this.valorAux = aux2 / valor2;
                        return valorAux;
                    } catch (Exception e) {
                        TError error = new TError(aux2.toString()+"/"+valor2.toString(), "Error Division entre 0 no Es posible! ", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                        Estatico.agregarError(error);
                        return 0.0;
                    }
                }
                case "14":
                {
                    Boolean valor1 = (Boolean)val1;
                    Double aux1 = 0.0;
                    if(valor1){aux1 = 1.0;}
                    Character valor2 = (Character)val2;
                    Double aux2 = Double.valueOf(Character.getNumericValue(valor2));
                    try {
                        this.valorAux = aux1 / aux2;
                        return this.valorAux;
                    } catch (Exception e) {
                        TError error = new TError(aux1.toString()+"/"+aux2.toString(), "Error Division entre 0 no Es posible! ", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                        Estatico.agregarError(error);
                        return 0.0;
                    }
                }
                case "31":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Boolean valor2 = (Boolean)val2;
                    Double aux1 = 0.0;
                    if(valor2){aux1 = 1.0;}
                    try {
                        this.valorAux = valor1 / aux1;
                        return valorAux;
                    } catch (Exception e) {
                        TError error = new TError(valor1.toString()+"/"+aux1.toString(), "Error Division entre 0 no Es posible! ", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                        Estatico.agregarError(error);
                        return 0.0;
                    }
                }
                case "33":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Double valor2 = Double.parseDouble(val2.toString());
                    try {
                        this.valorAux = valor1 / valor2;
                        return valorAux;
                    } catch (Exception e) {
                        TError error = new TError(valor1.toString()+"/"+valor2.toString(), "Error Division entre 0 no Es posible! ", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                        Estatico.agregarError(error);
                        return 0.0;
                    }
                }
                case "34":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Character valor2 = (Character)val2;
                    Double aux2 = Double.valueOf(Character.getNumericValue(valor2));
                    try {
                        this.valorAux = valor1 / aux2;
                        return this.valorAux;
                    } catch (Exception e) {
                        TError error = new TError(valor1.toString()+"/"+aux2.toString(), "Error Division entre 0 no Es posible! ", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                        Estatico.agregarError(error);
                        return 0.0;
                    }
                }
                case "41":
                {
                    Character valor1 = (Character)val1;
                    Double aux1 = Double.valueOf(Character.getNumericValue(valor1));
                    Boolean valor2 = (Boolean)val2;
                    Double aux2 = 0.0;
                    if(valor2){aux2 = 1.0;}
                    try {
                        this.valorAux = aux1 / aux2;
                        return this.valorAux;
                    } catch (Exception e) {
                        TError error = new TError(aux1.toString()+"/"+aux2.toString(), "Error Division entre 0 no Es posible! ", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                        Estatico.agregarError(error);
                        return 0.0;
                    }
                }
                case "43":
                {
                    Character valor1 = (Character)val1;
                    Double aux1 = Double.valueOf(Character.getNumericValue(valor1));
                    Double valor2 = Double.parseDouble(val2.toString());
                    try {
                        this.valorAux = aux1 / valor2;
                        return valorAux;
                    } catch (Exception e) {
                        TError error = new TError(valor1.toString()+"/"+valor2.toString(), "Error Division entre 0 no Es posible! ", "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                        Estatico.agregarError(error);
                        return 0.0;
                    }
                }
                default:
                {
                    TError error = new TError(tipo1+"/"+tipo2, "No se puede realizar la Division entre tipo: "+tipo1+" y "+tipo2, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                    Estatico.agregarError(error);
                }
            }
        } catch (Exception e) 
        {
            TError error = new TError(tipo1+"/"+tipo2, "Error al Dividir: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        this.valorAux = 0;
        return valorAux;
    }
    
    
    //**********************************************************************************************
    //*                                    Potencia                                                *
    //**********************************************************************************************
    //*                 | BOOLEAN        |     CADENA     |       NUMERICO   |       CARACTER      *
    //**********************************************************************************************
    //*   BOOLEAN       |      00        |      00        |          13      |        14           *
    //---------------------------------------------------------------------------------------------*
    //*   CADENA        |      00        |      00        |          00      |        00           *
    //---------------------------------------------------------------------------------------------*
    //    NUMERICO      |      31        |      00        |          33      |        34           *
    //---------------------------------------------------------------------------------------------*
    //*   CARACTER      |      41        |      00        |          43      |        00           *
    //---------------------------------------------------------------------------------------------*
    /**
     * Metodo privado que maneja la potencia
     * @param op1 operando1
     * @param op2 operando2
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object HazPotencia(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        String tipo2 = op2.getTipo(ambito);
        try {
            Object val1 = op1.getValor(ambito);
            Object val2 = op2.getValor(ambito);
            switch(codigo)
            {
                case "13":
                {
                    Boolean valor1 = (Boolean)val1;
                    Double aux1 = 0.0;
                    if(valor1){aux1 = 1.0;}
                    Double valor2 = Double.parseDouble(val2.toString());
                    return Math.pow(aux1, valor2);
                }
                case "14":
                {
                    Boolean valor1 = (Boolean)val1;
                    Double aux1 = 0.0;
                    if(valor1){aux1 = 1.0;}
                    Character valor2 = (Character)val2;
                    Double aux2 = Double.valueOf(Character.getNumericValue(valor2));
                    return Math.pow(aux1, aux2);
                }
                case "31":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Boolean valor2 = (Boolean)val2;
                    Double aux2 = 0.0;
                    if(valor2){aux2 = 1.0;}
                    return Math.pow(valor1, aux2);
                }
                case "33":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Double valor2 = Double.parseDouble(val2.toString());
                    return Math.pow(valor1, valor2);
                }
                case "34":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Character valor2 = (Character)val2;
                    Double aux2 = Double.valueOf(Character.getNumericValue(valor2));
                    return Math.pow(valor1, aux2);
                }
                case "41":
                {
                    Character valor1 = (Character)val1;
                    Double aux1 = Double.valueOf(Character.getNumericValue(valor1));
                    Boolean valor2 = (Boolean)val2;
                    Double aux2 = 0.0;
                    if(valor2){aux2 = 1.0;}
                    return Math.pow(aux1, aux2);
                }
                case "43":
                {
                    Character valor1 = (Character)val1;
                    Double aux1 = Double.valueOf(Character.getNumericValue(valor1));
                    Double valor2 = Double.parseDouble(val2.toString());
                    return Math.pow(aux1, valor2);
                }
                default:
                {
                    TError error = new TError(tipo1+"^"+tipo2, "No se puede realizar la Potencia entre tipo: "+tipo1+" y "+tipo2, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                    Estatico.agregarError(error);
                }
            }
        } catch (Exception e) {
            TError error = new TError(tipo1+"^"+tipo2, "Error al ejecutar Potencia: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        this.valorAux = 0.0;
        return valorAux;
    }

    //**********************************************************************************************
    //*                                    MOD                                                     *
    //**********************************************************************************************
    //*                 | BOOLEAN        |     CADENA     |       NUMERICO   |       CARACTER      *
    //**********************************************************************************************
    //*   BOOLEAN       |      00        |      00        |          13      |        14           *
    //---------------------------------------------------------------------------------------------*
    //*   CADENA        |      00        |      00        |          00      |        00           *
    //---------------------------------------------------------------------------------------------*
    //    NUMERICO      |      31        |      00        |          33      |        34           *
    //---------------------------------------------------------------------------------------------*
    //*   CARACTER      |      41        |      00        |          43      |        00           *
    //---------------------------------------------------------------------------------------------*
    /**
     * Metodo privado que se encarga de Modular
     * @param op1 operando1
     * @param op2 operando2
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object HazModular(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        String tipo2 = op2.getTipo(ambito);
        try {
            Object val1 = op1.getValor(ambito);
            Object val2 = op2.getValor(ambito);
            switch(codigo)
            {
                case "13":
                {
                    Boolean valor1 = (Boolean)val1;
                    Double aux1 = 0.0;
                    if(valor1){aux1 = 1.0;}
                    Double valor2 = Double.parseDouble(val2.toString());
                    return aux1 % valor2;
                }
                case "14":
                {
                    Boolean valor1 = (Boolean)val1;
                    Double aux1 = 0.0;
                    if(valor1){aux1 = 1.0;}
                    Character valor2 = (Character)val2;
                    Double aux2 = Double.valueOf(Character.getNumericValue(valor2));
                    return aux1 % aux2;
                }
                case "31":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Boolean valor2 = (Boolean)val2;
                    Double aux2 = 0.0;
                    if(valor2){aux2 = 1.0;}
                    return valor1 % aux2;
                }
                case "33":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Double valor2 = Double.parseDouble(val2.toString());
                    return valor1 % valor2;
                }
                case "34":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Character valor2 = (Character)val2;
                    Double aux2 = Double.valueOf(Character.getNumericValue(valor2));
                    return valor1 % aux2;
                }
                case "41":
                {
                    Character valor1 = (Character)val1;
                    Double aux1 = Double.valueOf(Character.getNumericValue(valor1));
                    Boolean valor2 = (Boolean)val2;
                    Double aux2 = 0.0;
                    if(valor2){aux2 = 1.0;}
                    return aux1 % aux2;
                }
                case "43":
                {
                    Character valor1 = (Character)val1;
                    Double aux1 = Double.valueOf(Character.getNumericValue(valor1));
                    Double valor2 = Double.parseDouble(val2.toString());
                    return aux1 % valor2;
                }
                default:
                {
                    TError error = new TError(tipo1+"%"+tipo2, "No se puede realizar la Modular entre tipo: "+tipo1+" y "+tipo2, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                    Estatico.agregarError(error);
                }
            }
        } catch (Exception e) {
            TError error = new TError(tipo1+"%"+tipo2, "Error al ejecutar Modular: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        this.valorAux = 0.0;
        return  valorAux;
    }
    
    //**********************************************************************************************
    //*                                    IGUALDAD                                                *
    //**********************************************************************************************
    //*                 | BOOLEAN        |     CADENA     |       NUMERICO   |       CARACTER      *
    //**********************************************************************************************
    //*   BOOLEAN       |      00        |      00        |          13      |        14           *
    //---------------------------------------------------------------------------------------------*
    //*   CADENA        |      00        |      00        |          00      |        00           *
    //---------------------------------------------------------------------------------------------*
    //    NUMERICO      |      31        |      00        |          33      |        34           *
    //---------------------------------------------------------------------------------------------*
    //*   CARACTER      |      41        |      00        |          43      |        00           *
    //---------------------------------------------------------------------------------------------*
    /**
     * 
     * Metodo que se encarga de realizar la comparacion de igualdad
     * @param op1 operando1
     * @param op2 operando2
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object HazIgual(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        String tipo2 = op2.getTipo(ambito);
        try 
        {
            Object val1 = op1.getValor(ambito);
            Object val2 = op2.getValor(ambito);
            switch(codigo)
            {
                case "11":
                {
                    Boolean valor1 = (Boolean)val1;
                    Boolean valor2 = (Boolean)val2;
                    return valor1.equals(valor2);
                }
                case "12":
                {
                    Boolean valor1 = (Boolean)val1;
                    String valor2 = (String)val2;
                    return String.valueOf(valor1).equals(valor2);
                }
                case "13":
                {
                    Boolean valor1 = (Boolean)val1;
                    Double aux1 = 0.0;
                    if(valor1){aux1 = 1.0;}
                    Double valor2 = Double.parseDouble(val2.toString());
                    return aux1.equals(valor2);
                }
                case "14":
                {
                    Boolean valor1 = (Boolean)val1;
                    Double aux1 = 0.0;
                    if(valor1){aux1 = 1.0;}
                    Character valor2 = (Character)val2;
                    Double aux2 = Double.valueOf(Character.getNumericValue(valor2));
                    return aux1.equals(aux2);
                }
                case "21":
                {
                    String valor1 = (String)val1;
                    Boolean valor2 = (Boolean)val2;
                    return valor1.equals(String.valueOf(valor2));
                }
                case "22":
                {
                    String valor1 = (String)val1;
                    String valor2 = (String)val2;
                    return valor1.equals(valor2);
                }
                case "23":
                {
                    String valor1 = (String)val1;
                    return  valor1.equals(val2.toString());
                }
                case "24":
                {
                    return val1.toString().equals(val2.toString());
                }
                case "31":
                {
                    Double valor1 = (Double)val1;
                    Boolean valor2 = (Boolean)val2;
                    Double aux2 = 0.0;
                    if(valor2){aux2 = 1.0;}
                    return valor1.equals(aux2);
                }
                case "32":
                {
                    return String.valueOf(val1).equals(val2.toString());
                }
                case "33":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Double valor2 = Double.parseDouble(val2.toString());
                    return valor1.equals(valor2);
                }
                case "34":
                {
                    Double valor1 = (Double)val1;
                    Character valor2 = (Character)val2;
                    Double aux2 = Double.valueOf(Character.getNumericValue(valor2));
                    return valor1.equals(aux2);
                }
                case "41":
                {
                    Character valor1 = (Character)val1;
                    Double aux1 = Double.valueOf(Character.getNumericValue(valor1));
                    Boolean valor2 = (Boolean)val2;
                    Double aux2 = 0.0;
                    if(valor2){aux2 = 1.0;}
                    return aux1.equals(aux2);
                }
                case "42":
                {
                    return val1.toString().equals(val2.toString());
                }
                case "43":
                {
                    Character valor1 = (Character)val1;
                    Double aux1 = Double.valueOf(Character.getNumericValue(valor1));
                    Double valor2 = Double.parseDouble(val2.toString());
                    return aux1.equals(valor2);
                }
                case "44":
                {
                    return val1.toString().equals(val2.toString());
                }
            }
        } catch (Exception e) 
        {
            TError error = new TError(tipo1+"=="+tipo2, "Error al ejecutar Igualdad: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        return false;
    }

    /**
     * Metodo que se encarga de realizar la comparacion de Diferente
     * @param op1 operando1
     * @param op2 operando2
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object HazDiferente(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        String tipo2 = op2.getTipo(ambito);
        try {
            Boolean res = (Boolean)HazIgual(op1, op2, codigo, ambito);
            return !res;
        } catch (Exception e) {
            TError error = new TError(tipo1+"!="+tipo2, "Error al ejecutar Diferente: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        return false;
    }
    
    //**********************************************************************************************
    //*                                    IGUALDAD                                                *
    //**********************************************************************************************
    //*                 | BOOLEAN        |     CADENA     |       NUMERICO   |       CARACTER      *
    //**********************************************************************************************
    //*   BOOLEAN       |      00        |      00        |          00      |        00           *
    //---------------------------------------------------------------------------------------------*
    //*   CADENA        |      00        |      00        |          00      |        00           *
    //---------------------------------------------------------------------------------------------*
    //    NUMERICO      |      31        |      00        |          33      |        34           *
    //---------------------------------------------------------------------------------------------*
    //*   CARACTER      |      41        |      00        |          43      |        00           *
    //---------------------------------------------------------------------------------------------*
    /**
     * 
     * Metodo que se encarga de realizar la comparacion Mayor
     * @param op1 operando1
     * @param op2 operando2
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object HazMayor(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        String tipo2 = op2.getTipo(ambito);
        try {
            Object val1 = op1.getValor(ambito);
            Object val2 = op2.getValor(ambito);
            switch(codigo)
            {
                
                case "22":
                {
                    String valor1 = (String)val1;
                    String valor2 = (String)val2;
                    if(valor1.compareTo(valor2)>0)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                case "23":
                {
                    String valor1 = (String)val1;
                    String valor2 = val2.toString();
                    if(valor1.compareTo(valor2)>0)
                    {
                        return true;
                    }
                    return false;
                }
                case "24":
                {
                    String valor1 = (String)val1;
                    Character valor2 = (Character)val2;
                    if(valor1.compareTo(valor2.toString())>0)
                    {
                        return true;
                    }
                    return false;
                }
                case "32":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    String valor2 = (String)val2;
                    if(String.valueOf(valor1).compareTo(valor2)>0)
                    {
                        return true;
                    }
                    return false;
                }
                case "33":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Double valor2 = Double.parseDouble(val2.toString());
                    if(valor1 > valor2)
                    {
                        return true;
                    }
                    return false;
                }
                case "34":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Character valor2 = (Character)val2;
                    Double aux = Double.valueOf(Character.getNumericValue(valor2));
                    if(valor1>aux)
                    {
                        return true;
                    }
                    return false;
                }
                case "42":
                {
                    Character valor1 = (Character)val1;
                    String valor2 = (String)val2;
                    if(valor1.toString().compareTo(valor2)>0)
                    {
                        return true;
                    }
                    return false;
                }
                case "43":
                {
                    Character valor1 = (Character)val1;
                    Double valor2 = Double.parseDouble(val2.toString());
                    Double aux1 = Double.valueOf(Character.getNumericValue(valor1));
                    if(aux1 > valor2)
                    {
                        return true;
                    }
                    return false;
                }
                case "44":
                {
                    Character valor1 = (Character)val1;
                    Character valor2 = (Character)val2;
                    if(valor1.compareTo(valor2)>0)
                    {
                        return true;
                    }
                    return false;
                }
                default:
                {
                    TError error = new TError(tipo1+">"+tipo2, "No se puede realizar la Mayor entre tipo: "+tipo1+" y "+tipo2, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                    Estatico.agregarError(error);
                }
            }
        } catch (Exception e) {
            TError error = new TError(tipo1+">"+tipo2, "Error al ejecutar Mayor: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        return false;
    }
    /**
     * 
     * Metodo que se encarga de realizar la comparacion Menor
     * @param op1 operando1
     * @param op2 operando2
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object HazMenor(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        String tipo2 = op2.getTipo(ambito);
        try {
            Object val1 = op1.getValor(ambito);
            Object val2 = op2.getValor(ambito);
            switch(codigo)
            {
                case "22":
                {
                    String valor1 = (String)val1;
                    String valor2 = (String)val2;
                    if(valor1.compareTo(valor2)<0)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                case "23":
                {
                    String valor1 = (String)val1;
                    String valor2 = val2.toString();
                    if(valor1.compareTo(valor2)<0)
                    {
                        return true;
                    }
                    return false;
                }
                case "24":
                {
                    String valor1 = (String)val1;
                    Character valor2 = (Character)val2;
                    if(valor1.compareTo(valor2.toString())<0)
                    {
                        return true;
                    }
                    return false;
                }
                case "32":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    String valor2 = (String)val2;
                    if(String.valueOf(valor1).compareTo(valor2)<0)
                    {
                        return true;
                    }
                    return false;
                }
                case "33":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Double valor2 = Double.parseDouble(val2.toString());
                    if(valor1 < valor2)
                    {
                        return true;
                    }
                    return false;
                }
                case "34":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    Character valor2 = (Character)val2;
                    Double aux = Double.valueOf(Character.getNumericValue(valor2));
                    if(valor1<aux)
                    {
                        return true;
                    }
                    return false;
                }
                case "42":
                {
                    Character valor1 = (Character)val1;
                    String valor2 = (String)val2;
                    if(valor1.toString().compareTo(valor2)<0)
                    {
                        return true;
                    }
                    return false;
                }
                case "43":
                {
                    Character valor1 = (Character)val1;
                    Double valor2 = Double.parseDouble(val2.toString());
                    Double aux1 = Double.valueOf(Character.getNumericValue(valor1));
                    if(aux1 < valor2)
                    {
                        return true;
                    }
                    return false;
                }
                case "44":
                {
                    Character valor1 = (Character)val1;
                    Character valor2 = (Character)val2;
                    if(valor1.compareTo(valor2)<0)
                    {
                        return true;
                    }
                    return false;
                }
                default:
                {
                    TError error = new TError(tipo1+"<"+tipo2, "No se puede realizar la Menor entre tipo: "+tipo1+" y "+tipo2, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                    Estatico.agregarError(error);
                }
            }
        } catch (Exception e) {
            TError error = new TError(tipo1+"<"+tipo2, "Error al ejecutar Menor: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        return false;
    }
    
    /**
     * 
     * Metodo que se encarga de realizar la comparacion Mayor Igual
     * @param op1 operando1
     * @param op2 operando2
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object MayorIgual(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        String tipo2 = op2.getTipo(ambito);
        try 
        {
            Boolean esMayor = (Boolean)HazMayor(op1, op2, codigo, ambito);
            Boolean esIgual = (Boolean)HazIgual(op1, op2, codigo, ambito);
            return esMayor || esIgual;
        } catch (Exception e) 
        {
            TError error = new TError(tipo1+">="+tipo2, "Error al ejecutar Mayor igual: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        return false;
    }
    /**
     * 
     * Metodo que se encarga de realizar la comparacion Menor Igual
     * @param op1 operando1
     * @param op2 operando2
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object MenorIgual(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        String tipo2 = op2.getTipo(ambito);
        try {
            Boolean esMenor = (Boolean)HazMenor(op1, op2, codigo, ambito);
            Boolean esIguak = (Boolean)HazIgual(op1, op2, codigo, ambito);
            return esMenor || esIguak;
        } catch (Exception e) {
            TError error = new TError(tipo1+"<="+tipo2, "Error al ejecutar Menor igual: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        return false;
    }
    
    /**
     * 
     * Metodo que se encarga de realizar la operacion AND
     * @param op1 operando1
     * @param op2 operando2
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object HazAnd(Expresion op1,Expresion op2, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        String tipo2 = op2.getTipo(ambito);
        try {
            Object val1 = op1.getValor(ambito);
            Object val2 = op2.getValor(ambito);
            switch(codigo)
            {
                case "11":
                {
                    Boolean valor1 = (Boolean)val1;
                    Boolean valor2 = (Boolean)val2;
                    return valor1 && valor2;
                }
                default:
                {
                    TError error = new TError(tipo1+"&&"+tipo2, "No se puede realizar Operacion AND entre tipo: "+tipo1+" y "+tipo2, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                    Estatico.agregarError(error);
                }
            }
        } catch (Exception e) {
            TError error = new TError(tipo1+"&&"+tipo2, "Error al ejecutar AND: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        return false;
    }
    /**
     * 
     * Metodo que se encarga de realizar la operacion OR
     * @param op1 operando1
     * @param op2 operando2
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object HazOr(Expresion op1, Expresion op2, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        String tipo2 = op2.getTipo(ambito);
        try {
            Object val1 = op1.getValor(ambito);
            Object val2 = op2.getValor(ambito);
            switch(codigo)
            {
                case "11":
                {
                    Boolean valor1 = (Boolean)val1;
                    Boolean valor2 = (Boolean)val2;
                    return valor1 || valor2;
                }
                default:
                {
                    TError error = new TError(tipo1+"||"+tipo2, "No se puede realizar Operacion OR entre tipo: "+tipo1+" y "+tipo2, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                    Estatico.agregarError(error);
                }
            }
        } catch (Exception e) {
            TError error = new TError(tipo1+"||"+tipo2, "Error al ejecutar OR: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        return false;
    }
    /**
     * 
     * Metodo que se encarga de realizar la operacion NOT
     * @param op1 operando1
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object HazNot(Expresion op1, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        try {
            Object val1 = op1.getValor(ambito);
            switch(codigo)
            {
                case "1":
                {
                    Boolean valor1 = (Boolean)val1;
                    return !valor1;
                }
                default:
                {
                    TError error = new TError("!"+tipo1, "No se puede realizar Operacion NOT entre tipo: "+tipo1, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                    Estatico.agregarError(error);
                }
            }
        } catch (Exception e) {
            TError error = new TError("!"+tipo1, "Error al ejecutar NOT: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        return false;
    }
    /**
     * 
     * Metodo que se encarga de realizar la operacion Aumento
     * @param op1 operando1
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object HazAumento(Expresion op1, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        try {
            Object val1 = op1.getValor(ambito);
            switch(codigo)
            {
                case "3":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    return valor1 + 1.0;
                }
                default:
                {
                     TError error = new TError(tipo1+"++", "No se puede realizar Operacion Incremento entre tipo: "+tipo1, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                    Estatico.agregarError(error);
                }
            }
        } catch (Exception e) {
            TError error = new TError(tipo1+"++", "Error al ejecutar Incremento: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        return 0.0;
    }
    /**
     * 
     * Metodo que se encarga de realizar la operacion Decremento
     * @param op1 operando1
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object HazDecremento(Expresion op1, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        try {
            Object val1 = op1.getValor(ambito);
            switch(codigo)
            {
                case "3":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    return valor1 - 1.0;
                }
                default:
                {
                    TError error = new TError(tipo1+"--", "No se puede realizar Operacion Decremento entre tipo: "+tipo1, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                    Estatico.agregarError(error);
                }
            }
        } catch (Exception e) {
            TError error = new TError(tipo1+"--", "Error al ejecutar Decremento: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        return 0.0;
    }
    /**
     * 
     * Metodo que se encarga de realizar la operacion Negativo
     * @param op1 operando1
     * @param codigo codigo previamente buscado
     * @param ambito Ambito donde se encuentra
     * @return el resultado de la operacion binaria
     */
    private Object HazMenos(Expresion op1, String codigo, Ambito ambito)
    {
        String tipo1 = op1.getTipo(ambito);
        try {
            Object val1 = op1.getValor(ambito);
            switch(codigo)
            {
                case "3":
                {
                    Double valor1 = Double.parseDouble(val1.toString());
                    return valor1*-1;
                }
                default:
                {
                    TError error = new TError("-"+tipo1, "No se puede realizar Operacion Negativo entre tipo: "+tipo1, "Semantico", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
                    Estatico.agregarError(error);
                }
            }
        } catch (Exception e) {
            TError error = new TError("-"+tipo1, "Error al ejecutar Negativo: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo());
            Estatico.agregarError(error);
        }
        return 0.0;
    }
}
