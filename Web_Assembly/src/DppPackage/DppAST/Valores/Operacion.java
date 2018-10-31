/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Valores;

import Abstraccion.*;
import ErrorManager.TError;
import Estructuras.NodoDisplay;
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
     *
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
     *
     * @param linea
     * @param columna
     * @param archivo
     * @param op1
     * @param operador
     */
    public Operacion(int linea, int columna, String archivo, Expresion op1, InfoEstatica.Estatico.OPERADORES operador) {
        super(linea, columna, archivo);
        this.op1 = op1;
        this.operador = operador;
        this.esUnario = true;
    }

    /**
     * Traductor del ByteCode para una operacion
     *
     * @param ambito Lugar donde se encuentra la operacion
     * @return el codigo bytecode generado
     */
    @Override
    public Object generateByteCode(Ambito ambito) {
        try {
            if (esUnario) {
                String codigo = getCodigo(op1, ambito);
                switch (operador) {
                    case NOT: {
                        return TraduceNot(op1, codigo, ambito);
                    }
                    case MENOS: {
                        return TraduceNegativo(op1, codigo, ambito);
                    }
                    case INC: {
                        return TraduceAumento(op1, codigo, ambito);
                    }
                    case DEC: {
                        return TraduceDecremento(op1, codigo, ambito);
                    }
                }
            } else {
                String codigo = getCodigo(op1, ambito);
                codigo += getCodigo(op2, ambito);
                switch (operador) {
                    case MAS: {
                        return TraduceSuma(op1, op2, codigo, ambito);
                    }
                    case MENOS: {
                        return TraduceResta(op1, op2, codigo, ambito);
                    }
                    case MULT: {
                        return TraduceMultiplicacion(op1, op2, codigo, ambito);
                    }
                    case DIV: {
                        return TraduceDivision(op1, op2, codigo, ambito);
                    }
                    case MENOR: {
                        return TraduceMenorQue(op1, op2, codigo, ambito);
                    }
                    case MAYOR: {
                        return TraduceMayor(op1, op2, codigo, ambito);
                    }
                    case MENORIGUAL: {
                        return TraduceMenorIgual(op1, op2, codigo, ambito);
                    }
                    case MAYORIGUAL: {
                        return TraduceMayorIgual(op1, op2, codigo, ambito);
                    }
                    case IGUAL: {
                        return traduceIgualDiferente(op1, op2, codigo, ambito);
                    }
                    case DIFERENTE: {
                        return traduceIgualDiferente(op1, op2, codigo, ambito);
                    }
                    case AND: {
                        return TraduceAnd(op1, op2, codigo, ambito);
                    }
                    case OR: {
                        return TraduceOR(op1, op2, codigo, ambito);
                    }
                    case POT: {
                        return TraducePotencia(op1, op2, codigo, ambito);
                    }
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al Traducir Operacion: " + e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo()));
        }
        return "";
    }

    private String getCodigo(Expresion op1, Ambito ambito) {
        NodoAST aux = (NodoAST) op1;
        aux.generateByteCode(ambito);
        String tipo = op1.getTipo(ambito);
        switch (tipo) {
            case "ENTERO": {
                return "1";
            }
            case "DECIMAL": {
                return "5";
            }
            case "CADENA": {
                return "2";
            }
            case "BOOLEAN": {
                return "3";
            }
            case "CARACTER": {
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
    private Object TraduceSuma(Expresion op1, Expresion op2, String codigo, Ambito ambito) {
        try {
            // NOTA QUE FALTA LA OPERACION CON CADENAS
            String tipo1 = op1.getTipo(ambito);
            String tipo2 = op2.getTipo(ambito);
            NodoAST valor1 = (NodoAST) op1;
            String t1 = (String) valor1.generateByteCode(ambito);
            NodoAST valor2 = (NodoAST) op2;
            String t2 = (String) valor2.generateByteCode(ambito);
            switch (codigo) {

                case "11": {

                    resultado = "ENTERO";
                    return t1 + "\n" + t2 + "\n" + "ADD\n";
                }
                case "12":{
                    resultado = "CADENA";
                    String enteroCadena = enteroACadena(ambito, t1);
                    return  ConcatenandoCadenas(ambito, enteroCadena, t2);
                }
                case "13": {
                    resultado = "ENTERO";
                    return t1 + "\n" + t2 + "\n" + "ADD\n";
                }
                case "14": {
                    resultado = "ENTERO";
                    return t1 + "\n" + t2 + "\n" + "ADD\n";
                }
                case "15": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "ADD\n";
                }
                case "21":{
                    resultado = "CADENA";
                    String enteroCadena = enteroACadena(ambito, t2);
                    return ConcatenandoCadenas(ambito, t1, enteroCadena);
                }
                case "22": {
                    resultado = "CADENA";
                    return ConcatenandoCadenas(ambito, t1, t2);
                }
                case "23":
                {
                    resultado = "CADENA";
                    String enteroCadena = enteroACadena(ambito, t2);
                    return ConcatenandoCadenas(ambito, t1, enteroCadena);
                }
                case "24":
                {
                    resultado = "CADENA";
                    String caracterPointer = "";
                    caracterPointer += "// METIENDO CARACTER AL HEAP \n";
                    caracterPointer += "get_global 0 // OBTENGO EL PUNTERO DE HEAP\n";
                    caracterPointer += t2+" //CARACTER \n";
                    caracterPointer += "set_global $calc // METO EL CARACTER EN EL HEAP \n";
                    caracterPointer += "get_global 0 // METO EL PUNTERO DEL HEAP...\n";
                    caracterPointer += "get_global 0 // atualizando el puntero \n";
                    caracterPointer += "1 // NUEVO VALOR...\n";
                    caracterPointer += "ADD // SUMO\n";
                    caracterPointer += "set_global 0 // ACTUALIZO EL PUNTERO\n";
                    caracterPointer += "get_global 0 \n";
                    caracterPointer += "0\n";
                    caracterPointer += "set_global $calc \n";
                    caracterPointer += "get_global 0\n";
                    caracterPointer += "1 \n";
                    caracterPointer += "ADD\n";
                    caracterPointer += "set_global 0\n";
                    return ConcatenandoCadenas(ambito, t1, caracterPointer);
                }
                case "25": {
                    resultado = "CADENA";
                    String convertir = decimalACadena(ambito, t2);
                    return ConcatenandoCadenas(ambito, t1, convertir);
                    
                }
                case "31": {
                    resultado = "ENTERO";
                    return t1 + "\n" + t2 + "\n" + "ADD\n";
                }
                case "32":
                {
                    resultado = "CADENA";
                    String enteroCadena = enteroACadena(ambito, t1);
                    return  ConcatenandoCadenas(ambito, enteroCadena, t2);
                }
                case "33": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "ADD\n";
                }
                case "35": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "ADD\n";
                }
                case "41": {
                    resultado = "ENTERO";
                    return t1 + "\n" + t2 + "\n" + "ADD\n";
                }
                case "42":{
                    resultado = "CADENA";
                    String caracterPointer = "";
                    caracterPointer += "// METIENDO CARACTER AL HEAP \n";
                    caracterPointer += "get_global 0 // OBTENGO EL PUNTERO DE HEAP\n";
                    caracterPointer += t1+" //CARACTER \n";
                    caracterPointer += "set_global $calc // METO EL CARACTER EN EL HEAP \n";
                    caracterPointer += "get_global 0 // METO EL PUNTERO DEL HEAP...\n";
                    caracterPointer += "get_global 0 // atualizando el puntero \n";
                    caracterPointer += "1 // NUEVO VALOR...\n";
                    caracterPointer += "ADD // SUMO\n";
                    caracterPointer += "set_global 0 // ACTUALIZO EL PUNTERO\n";
                    caracterPointer += "get_global 0 \n";
                    caracterPointer += "0\n";
                    caracterPointer += "set_global $calc \n";
                    caracterPointer += "get_global 0\n";
                    caracterPointer += "1 \n";
                    caracterPointer += "ADD\n";
                    caracterPointer += "set_global 0\n";
                    return ConcatenandoCadenas(ambito, caracterPointer, t2);
                }
                case "45": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "ADD\n";
                }
                case "51": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "ADD\n";
                }
                case "52": {
                    resultado = "CADENA";
                    String convertir = decimalACadena(ambito, t1);
                    return ConcatenandoCadenas(ambito,convertir, t1);
                }
                case "53": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "ADD\n";
                }
                case "54": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "ADD\n";
                }
                case "55": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "ADD\n";
                }
                default: {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1 + "+" + tipo2, "Error La suma entre tipos indicados no es compatible", "Semantico", super.getLinea(), super.getColumna(), false, super.getArchivo()));
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al realizar la suma: " + e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo()));
        }
        return "";
    }
    
    private String enteroACadena(Ambito ambito, String t1)
    {
        try {
            String cad = "";
            cad += "\n/**************************************************************************/\n";
            cad += "// PARAM 1: ENTERO A CONVERTIR\n";
            cad += "get_local 0 // PUNTERO VIRTUAL\n";
            cad += (ambito.getSize()-1) + "// TAMMANIO DEL AMBITO\n";
            cad += "ADD // SUMO\n";
            cad += "1 // NUMERO DE PARAMETRO A COLOCAR\n";
            cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
            cad += t1 + "\n";
            //cad += "set_local $calc // COLOCAR EN LA POSICION QUE LE TOCA\n";
            cad += "/**************************************************************************/\n";
            
            cad += "/**************************************************************************/\n";
            cad += "// LLAMANDO A FUNCION QUE CONTATENA Y DEVUELVE UN PUNTERO AL STRING RESULTADO\n";
            cad += "get_local 0 // INICIO LLAMADO\n";
            cad += (ambito.getSize()-1) + " // SIZE DEL AMBITO PARA AVANZAR\n";
            cad += "ADD // SUMA PARA MOVERME\n";
            cad += "set_local 0 // ACTUALIZA EL PUNTERO\n";
            cad += "Call $_INT_TO_STRING // CONTANTENANDO STRING\n";
            cad += "get_local 0 // REGRESANDO AL AMBITO ANTERIOR\n";
            cad += (ambito.getSize()-1) + " // SIZE DEL AMBITO PARA REGRESAR\n";
            cad += "DIFF // RESTAR EL AMBITO\n";
            cad += "set_local 0 // ACTUALIZA EL PUNTERO\n";
            cad += "// OBTENGO EL RETORNO DEL PUNTERO RESULTANTE \n";
            cad += "get_local $ret //OBTENIENDO EL PUNTERO DE RETORNO EN LA PILITA\n";
            cad += "/**************************************************************************/\n";
            return cad;
            
        } catch (Exception e) {
        }
        return "";
    }
    private String decimalACadena(Ambito ambito, String t1)
    {
        try {
            String cad = "";
            cad += "\n/**************************************************************************/\n";
            cad += "// PARAM 1: ENTERO A CONVERTIR\n";
            cad += "get_local 0 // PUNTERO VIRTUAL\n";
            cad += (ambito.getSize()-1) + "// TAMMANIO DEL AMBITO\n";
            cad += "ADD // SUMO\n";
            cad += "1 // NUMERO DE PARAMETRO A COLOCAR\n";
            cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
            cad += t1 + "\n";
            
            cad += "/**************************************************************************/\n";
            cad += "// LLAMANDO A FUNCION QUE CONTATENA Y DEVUELVE UN PUNTERO AL STRING RESULTADO\n";
            cad += "get_local 0 // INICIO LLAMADO\n";
            cad += (ambito.getSize()-1) + " // SIZE DEL AMBITO PARA AVANZAR\n";
            cad += "ADD // SUMA PARA MOVERME\n";
            cad += "set_local 0 // ACTUALIZA EL PUNTERO\n";
            cad += "Call $_DECIMAL_TO_STRING // CONTANTENANDO STRING\n";
            cad += "get_local 0 // REGRESANDO AL AMBITO ANTERIOR\n";
            cad += (ambito.getSize()-1) + " // SIZE DEL AMBITO PARA REGRESAR\n";
            cad += "DIFF // RESTAR EL AMBITO\n";
            cad += "set_local 0 // ACTUALIZA EL PUNTERO\n";
            cad += "// OBTENGO EL RETORNO DEL PUNTERO RESULTANTE \n";
            cad += "get_local $ret //OBTENIENDO EL PUNTERO DE RETORNO EN LA PILITA\n";
            cad += "/**************************************************************************/\n";
            return  cad;
        } catch (Exception e) {
        }
        return "";
    }
    private String ConcatenandoCadenas(Ambito ambito, String t1, String t2) {
        try {
            String cad = "";
            cad += "\n/**************************************************************************/\n";
            cad += "// PARAM: 1 PUNTERO DE CADENA 1 \n";
            cad += "get_local 0 // PUNTERO VIRTUAL\n";
            cad += (ambito.getSize() - 1) + "// TAMMANIO DEL AMBITO\n";
            cad += "ADD // SUMO\n";
            cad += "1 // NUMERO DE PARAMETRO A COLOCAR\n";
            cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
            cad += t1 + "\n";
            //cad += "set_local $calc // COLOCAR EN LA POSICION QUE LE TOCA\n";
            cad += "/**************************************************************************/\n";

            cad += "\n/**************************************************************************/\n";
            cad += "// PARAM: 2 PUNTERO DE CADENA 2 \n";
            cad += "get_local 0 // PUNTERO VIRTUAL\n";
            cad += (ambito.getSize() - 1) + "// TAMMANIO DEL AMBITO\n";
            cad += "ADD // SUMO\n";
            cad += "2 // NUMERO DE PARAMETRO A COLOCAR\n";
            cad += "ADD // SUMA PARA ENCONTRAR SU POSICION ABSOLUTA EN EL STACK\n";
            cad += t2 + "\n";
            //cad += "set_local $calc // COLOCAR EN LA POSICION QUE LE TOCA\n";
            cad += "/**************************************************************************/\n";

            cad += "// FIN DE PASO DE PARAMETROS\n";
            cad += "get_local 0 // INICIO LLAMADO\n";
            cad += (ambito.getSize() - 1) + " // SIZE DEL AMBITO PARA AVANZAR\n";
            cad += "ADD // SUMA PARA MOVERME\n";
            cad += "set_local 0 // ACTUALIZA EL PUNTERO\n";
            cad += "Call $_CONCAT_STRING // LLAMADO DE FUNCION\n";
            cad += "get_local 0 // REGRESANDO AL AMBITO ANTERIOR\n";
            cad += (ambito.getSize() - 1) + " // SIZE DEL AMBITO PARA REGRESAR\n";
            cad += "DIFF // RESTAR EL AMBITO\n";
            cad += "set_local 0 // ACTUALIZA EL PUNTERO\n";
            cad += "// OBTENGO EL RETORNO DEL PUNTERO RESULTANTE \n";
            cad += "get_local $ret //OBTENIENDO EL PUNTERO DE RETORNO EN LA PILITA\n";
            cad += "/**************************************************************************/\n";
            return  cad;
        } catch (Exception e) {
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
    private Object TraduceResta(Expresion op1, Expresion op2, String codigo, Ambito ambito) {
        try {
            String tipo1 = op1.getTipo(ambito);
            String tipo2 = op2.getTipo(ambito);
            NodoAST valor1 = (NodoAST) op1;
            String t1 = (String) valor1.generateByteCode(ambito);
            NodoAST valor2 = (NodoAST) op2;
            String t2 = (String) valor2.generateByteCode(ambito);
            switch (codigo) {
                case "11": {
                    resultado = "ENTERO";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n";
                }
                case "13": {
                    resultado = "ENTERO";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n";
                }
                case "14": {
                    resultado = "ENTERO";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n";
                }
                case "15": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n";
                }
                case "31": {
                    resultado = "ENTERO";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n";
                }
                case "35": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n";
                }
                case "41": {
                    resultado = "ENTERO";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n";
                }
                case "45": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n";
                }
                case "51": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n";
                }
                case "53": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n";
                }
                case "54": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n";
                }
                case "55": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n";
                }
                default: {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1 + "-" + tipo2, "Error La resta entre tipos indicados no es compatible", "Semantico", super.getLinea(), super.getColumna(), false, super.getArchivo()));
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al realizar la resta: " + e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo()));
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
    private Object TraduceMultiplicacion(Expresion op1, Expresion op2, String codigo, Ambito ambito) {
        try {
            String tipo1 = op1.getTipo(ambito);
            String tipo2 = op2.getTipo(ambito);
            NodoAST valor1 = (NodoAST) op1;
            String t1 = (String) valor1.generateByteCode(ambito);
            NodoAST valor2 = (NodoAST) op2;
            String t2 = (String) valor2.generateByteCode(ambito);
            switch (codigo) {
                case "11": {
                    resultado = "ENTERO";
                    return t1 + "\n" + t2 + "\n" + "MULT\n";
                }
                case "13": {
                    resultado = "ENTERO";
                    return t1 + "\n" + t2 + "\n" + "MULT\n";
                }
                case "14": {
                    resultado = "ENTERO";
                    return t1 + "\n" + t2 + "\n" + "MULT\n";
                }
                case "15": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "MULT\n";
                }
                case "31": {
                    resultado = "ENTERO";
                    return t1 + "\n" + t2 + "\n" + "MULT\n";
                }
                case "33": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "MULT\n";
                }
                case "35": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "MULT\n";
                }
                case "41": {
                    resultado = "ENTERO";
                    return t1 + "\n" + t2 + "\n" + "MULT\n";
                }
                case "45": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "MULT\n";
                }
                case "51": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "MULT\n";
                }
                case "53": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "MULT\n";
                }
                case "54": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "MULT\n";
                }
                case "55": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "MULT\n";
                }
                default: {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1 + "*" + tipo2, "Error La Multiplicacion entre tipos indicados no es compatible", "Semantico", super.getLinea(), super.getColumna(), false, super.getArchivo()));
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejecutar la multiplicacion: " + e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo()));
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
    private Object TraduceDivision(Expresion op1, Expresion op2, String codigo, Ambito ambito) {
        try {
            String tipo1 = op1.getTipo(ambito);
            String tipo2 = op2.getTipo(ambito);
            NodoAST valor1 = (NodoAST) op1;
            String t1 = (String) valor1.generateByteCode(ambito);
            NodoAST valor2 = (NodoAST) op2;
            String t2 = (String) valor2.generateByteCode(ambito);
            switch (codigo) {
                case "11": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIV\n";
                }
                case "13": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIV\n";
                }
                case "14": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIV\n";
                }
                case "15": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIV\n";
                }
                case "31": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIV\n";
                }
                case "35": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIV\n";
                }
                case "41": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIV\n";
                }
                case "45": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIV\n";
                }
                case "51": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIV\n";
                }
                case "53": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIV\n";
                }
                case "54": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIV\n";
                }
                case "55": {
                    resultado = "DECIMAL";
                    return t1 + "\n" + t2 + "\n" + "DIV\n";
                }
                default: {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1 + "/" + tipo2, "Error La Divison entre tipos indicados no es compatible", "Semantico", super.getLinea(), super.getColumna(), false, super.getArchivo()));
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Erro al ejecutar la division: " + e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo()));
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
    private Object TraduceAumento(Expresion op1, String codigo, Ambito ambito) {
        try {
            String tipo1 = op1.getTipo(ambito);
            NodoAST valor1 = (NodoAST) op1;
            String t1 = (String) valor1.generateByteCode(ambito);
            switch (codigo) {
                case "1": {
                    resultado = "ENTERO";
                    return t1 + "\n1\nADD\n";
                }
                case "5": {
                    resultado = "DECIMAL";
                    return t1 + "\n1\nADD\n";
                }
                default: {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1 + "++", "No es posible aumentar el tipo indicado", "Semantico", super.getLinea(), super.getColumna(), false, super.getArchivo()));
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error de Traducir un aumento: " + e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo()));
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
    private Object TraduceDecremento(Expresion op1, String codigo, Ambito ambito) {
        try {
            String tipo1 = op1.getTipo(ambito);
            NodoAST valor1 = (NodoAST) op1;
            String t1 = (String) valor1.generateByteCode(ambito);
            switch (codigo) {
                case "1": {
                    resultado = "ENTERO";
                    return t1 + "\n1\nDIFF";
                }
                case "5": {
                    resultado = "DECIMAL";
                    return t1 + "\n1\nDIFF";
                }
                default: {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1 + "--", "No es posible decrementar el tipo indicado", "Semantico", super.getLinea(), super.getColumna(), false, super.getArchivo()));
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al Traducir el Decremento: " + e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo()));
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
    private Object TraduceNegativo(Expresion op1, String codigo, Ambito ambito) {
        try {
            String tipo1 = op1.getTipo(ambito);
            NodoAST valor1 = (NodoAST) op1;
            String t1 = (String) valor1.generateByteCode(ambito);
            switch (codigo) {
                case "1": {
                    resultado = "ENTERO";
                    return t1 + "\n-1\nMULT\n";
                }
                case "2": {
                    resultado = "DECIMAL";
                    return t1 + "\n-1\nMULT\n";
                }
                default: {
                    InfoEstatica.Estatico.agregarError(new TError("-" + tipo1, "No es posible aplicar Negativo al tipo indicado", "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo()));
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al traducir el Negativo: " + e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo()));
        }
        return "";
    }

    //**************************************************************************************
    //*                                      MENOR                                         *
    //**************************************************************************************
    //*               |    ENTERO    |   CADENA   |    BOOLEAN   |   CARACTER  |   DECIMAL *
    //*-------------------------------------------------------------------------------------
    //*    ENTERO     |      11      |    pp      |      13      |     14      |     15    *
    //*------------------------------------------------------------------------------------*
    //*    CADENA     |      00      |    00      |      00      |     00      |     00    *
    //*------------------------------------------------------------------------------------*
    //*    BOOLEAN    |      31      |    PP      |      00      |     34      |     35    *
    //*------------------------------------------------------------------------------------*
    //*    CARACTER   |      41      |    00      |      43      |     44      |     45    *
    //*------------------------------------------------------------------------------------*
    //*    DECIMAL    |      51      |    00      |      53      |     54      |     55    *
    //*------------------------------------------------------------------------------------*
    private Object TraduceMenorQue(Expresion op1, Expresion op2, String codigo, Ambito ambito) {
        try {
            String tipo1 = op1.getTipo(ambito);
            String tipo2 = op2.getTipo(ambito);
            NodoAST valor1 = (NodoAST) op1;
            String t1 = (String) valor1.generateByteCode(ambito);
            NodoAST valor2 = (NodoAST) op2;
            String t2 = (String) valor2.generateByteCode(ambito);
            switch (codigo) {
                case "11": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LT\n";
                }
                case "13": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LT\n";
                }
                case "14": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LT\n";
                }
                case "15": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LT\n";
                }
                case "22": {
                    resultado = "BOOLEAN";
                    return CadenaComparacion(ambito, t1, t2);
                }
                case "31": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LT\n";
                }
                case "34": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LT\n";
                }
                case "35": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LT\n";
                }
                case "41": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LT\n";
                }
                case "43": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LT\n";
                }
                case "44": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LT\n";
                }
                case "45": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LT\n";
                }
                case "51": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LT\n";
                }
                case "53": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LT\n";
                }
                case "54": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LT\n";
                }
                case "55": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LT\n";
                }
                default: {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1 + "<" + tipo2, "No es posible aplicar la operacion: Menor que", "Semantico",
                            super.getLinea(), super.getColumna(), false, super.getArchivo()));
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al traducir Menor que: " + e.getMessage(),
                     "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo()));
        }
        return "";
    }

    //**************************************************************************************
    //*                                      MAYOR                                         *
    //**************************************************************************************
    //*               |    ENTERO    |   CADENA   |    BOOLEAN   |   CARACTER  |   DECIMAL *
    //*-------------------------------------------------------------------------------------
    //*    ENTERO     |      11      |    pp      |      13      |     14      |     15    *
    //*------------------------------------------------------------------------------------*
    //*    CADENA     |      00      |    00      |      00      |     00      |     00    *
    //*------------------------------------------------------------------------------------*
    //*    BOOLEAN    |      31      |    PP      |      00      |     34      |     35    *
    //*------------------------------------------------------------------------------------*
    //*    CARACTER   |      41      |    00      |      43      |     44      |     45    *
    //*------------------------------------------------------------------------------------*
    //*    DECIMAL    |      51      |    00      |      53      |     54      |     55    *
    //*------------------------------------------------------------------------------------*
    private Object TraduceMayor(Expresion op1, Expresion op2, String codigo, Ambito ambito) {
        try {
            String tipo1 = op1.getTipo(ambito);
            String tipo2 = op2.getTipo(ambito);
            NodoAST valor1 = (NodoAST) op1;
            String t1 = (String) valor1.generateByteCode(ambito);
            NodoAST valor2 = (NodoAST) op2;
            String t2 = (String) valor2.generateByteCode(ambito);
            switch (codigo) {
                case "11": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GT\n";
                }
                case "13": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GT\n";
                }
                case "14": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GT\n";
                }
                case "15": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GT\n";
                }
                case "22": {
                    resultado = "BOOLEAN";
                    return CadenaComparacion(ambito, t1, t2);
                }
                case "31": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GT\n";
                }
                case "34": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GT\n";
                }
                case "35": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GT\n";
                }
                case "41": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GT\n";
                }
                case "43": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GT\n";
                }
                case "44": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GT\n";
                }
                case "45": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GT\n";
                }
                case "51": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GT\n";
                }
                case "53": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GT\n";
                }
                case "54": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GT\n";
                }
                case "55": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GT\n";
                }
                default: {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1 + ">" + tipo2, "No es posible aplicar la operacion: Mayor que", "Semantico",
                            super.getLinea(), super.getColumna(), false, super.getArchivo()));
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al traducir Mayor que: " + e.getMessage(),
                     "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo()));
        }
        return "";
    }

    //**************************************************************************************
    //*                                      MAYOR IGUAL                                   *
    //**************************************************************************************
    //*               |    ENTERO    |   CADENA   |    BOOLEAN   |   CARACTER  |   DECIMAL *
    //*-------------------------------------------------------------------------------------
    //*    ENTERO     |      11      |    pp      |      13      |     14      |     15    *
    //*------------------------------------------------------------------------------------*
    //*    CADENA     |      00      |    00      |      00      |     00      |     00    *
    //*------------------------------------------------------------------------------------*
    //*    BOOLEAN    |      31      |    PP      |      00      |     34      |     35    *
    //*------------------------------------------------------------------------------------*
    //*    CARACTER   |      41      |    00      |      43      |     44      |     45    *
    //*------------------------------------------------------------------------------------*
    //*    DECIMAL    |      51      |    00      |      53      |     54      |     55    *
    //*------------------------------------------------------------------------------------*
    private Object TraduceMayorIgual(Expresion op1, Expresion op2, String codigo, Ambito ambito) {
        try {
            String tipo1 = op1.getTipo(ambito);
            String tipo2 = op2.getTipo(ambito);
            NodoAST valor1 = (NodoAST) op1;
            String t1 = (String) valor1.generateByteCode(ambito);
            NodoAST valor2 = (NodoAST) op2;
            String t2 = (String) valor2.generateByteCode(ambito);
            switch (codigo) {
                case "11": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GTE\n";
                }
                case "13": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GTE\n";
                }
                case "14": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GTE\n";
                }
                case "15": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GTE\n";
                }
                case "22":{
                    resultado = "BOOLEAN";
                    return CadenaComparacion(ambito, t1, t2);
                }
                case "31": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GTE\n";
                }
                case "34": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GTE\n";
                }
                case "35": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GTE\n";
                }
                case "41": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GTE\n";
                }
                case "43": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GTE\n";
                }
                case "44": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GTE\n";
                }
                case "45": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GTE\n";
                }
                case "51": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GTE\n";
                }
                case "53": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GTE\n";
                }
                case "54": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GTE\n";
                }
                case "55": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "GTE\n";
                }
                default: {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1 + ">=" + tipo2, "No es posible aplicar la operacion: Mayor Igual que", "Semantico",
                            super.getLinea(), super.getColumna(), false, super.getArchivo()));
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al traducir Mayor Igual que: " + e.getMessage(),
                     "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo()));
        }
        return "";
    }

    //**************************************************************************************
    //*                                      MAYOR                                         *
    //**************************************************************************************
    //*               |    ENTERO    |   CADENA   |    BOOLEAN   |   CARACTER  |   DECIMAL *
    //*-------------------------------------------------------------------------------------
    //*    ENTERO     |      11      |    pp      |      13      |     14      |     15    *
    //*------------------------------------------------------------------------------------*
    //*    CADENA     |      00      |    00      |      00      |     00      |     00    *
    //*------------------------------------------------------------------------------------*
    //*    BOOLEAN    |      31      |    PP      |      00      |     34      |     35    *
    //*------------------------------------------------------------------------------------*
    //*    CARACTER   |      41      |    00      |      43      |     44      |     45    *
    //*------------------------------------------------------------------------------------*
    //*    DECIMAL    |      51      |    00      |      53      |     54      |     55    *
    //*------------------------------------------------------------------------------------*
    private Object TraduceMenorIgual(Expresion op1, Expresion op2, String codigo, Ambito ambito) {
        try {
            String tipo1 = op1.getTipo(ambito);
            String tipo2 = op2.getTipo(ambito);
            NodoAST valor1 = (NodoAST) op1;
            String t1 = (String) valor1.generateByteCode(ambito);
            NodoAST valor2 = (NodoAST) op2;
            String t2 = (String) valor2.generateByteCode(ambito);
            switch (codigo) {
                case "11": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LTE\n";
                }
                case "13": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LTE\n";
                }
                case "14": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LTE\n";
                }
                case "15": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LTE\n";
                }
                case "31": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LTE\n";
                }
                case "34": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LTE\n";
                }
                case "35": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LTE\n";
                }
                case "41": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LTE\n";
                }
                case "43": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LTE\n";
                }
                case "44": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LTE\n";
                }
                case "45": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LTE\n";
                }
                case "51": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LTE\n";
                }
                case "53": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LTE\n";
                }
                case "54": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LTE\n";
                }
                case "55": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "LTE\n";
                }
                default: {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1 + "<=" + tipo2, "No es posible aplicar la operacion: Menor Igual que", "Semantico",
                            super.getLinea(), super.getColumna(), false, super.getArchivo()));
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al traducir Menor Igual que: " + e.getMessage(),
                     "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo()));
        }
        return "";
    }

    //**************************************************************************************
    //*                                      IGUAL                                         *
    //**************************************************************************************
    //*               |    ENTERO    |   CADENA   |    BOOLEAN   |   CARACTER  |   DECIMAL *
    //*-------------------------------------------------------------------------------------
    //*    ENTERO     |      11      |    pp      |      13      |     14      |     15    *
    //*------------------------------------------------------------------------------------*
    //*    CADENA     |      00      |    PP      |      00      |     00      |     00    *
    //*------------------------------------------------------------------------------------*
    //*    BOOLEAN    |      31      |    PP      |      00      |     34      |     35    *
    //*------------------------------------------------------------------------------------*
    //*    CARACTER   |      41      |    PP      |      43      |     44      |     45    *
    //*------------------------------------------------------------------------------------*
    //*    DECIMAL    |      51      |    PP      |      53      |     54      |     55    *
    //*------------------------------------------------------------------------------------*
    private Object traduceIgualDiferente(Expresion op1, Expresion op2, String codigo, Ambito ambito) {
        try {
            String tipo1 = op1.getTipo(ambito);
            String tipo2 = op2.getTipo(ambito);
            NodoAST valor1 = (NodoAST) op1;
            String t1 = (String) valor1.generateByteCode(ambito);
            NodoAST valor2 = (NodoAST) op2;
            String t2 = (String) valor2.generateByteCode(ambito);
            switch (codigo) {
                case "11": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n" + "EQZ" + (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"\nEQZ":"");
                }
                case "13": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n" + "EQZ" + (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"\nEQZ":"");
                }
                case "14": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n" + "EQZ" + (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"\nEQZ":"");
                }
                case "15": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n" + "EQZ" + (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"\nEQZ":"");
                }
                case "22": {
                    resultado = "BOOLEAN";
                    return  CadenaIgualDiferente(ambito, t1, t2);
                }
                case "31": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n" + "EQZ" + (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"\nEQZ":"");
                }
                case "33": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n" + "EQZ" + (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"\nEQZ":"");
                }
                case "34": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n" + "EQZ" + (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"\nEQZ":"");
                }
                case "35": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n" + "EQZ" + (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"\nEQZ":"");
                }
                case "41": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n" + "EQZ" + (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"\nEQZ":"");
                }
                case "43": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n" + "EQZ" + (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"\nEQZ":"");
                }
                case "44": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n" + "EQZ" + (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"\nEQZ":"");
                }
                case "45": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n" + "EQZ" + (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"\nEQZ":"");
                }
                case "51": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n" + "EQZ" + (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"\nEQZ":"");
                }
                case "53": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n" + "EQZ" + (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"\nEQZ":"");
                }
                case "54": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n" + "EQZ" + (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"\nEQZ":"");
                }
                case "55": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\n" + "DIFF\n" + "EQZ" + (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"\nEQZ":"");
                }
                default: {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1 + "==" + tipo2 + " | " + tipo1 + "!=" + tipo2, "No es posible aplicar la operacion: Igual o Diferente", "Semantico",
                            super.getLinea(), super.getColumna(), false, super.getArchivo()));
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al traducir la igualdad o Diferencia: " + e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(),
                    false, super.getArchivo()));
        }
        return "";
    }
    
    private String CadenaIgualDiferente(Ambito ambito, String t1, String t2)
    {
        try {
            String cad = "";
            cad += "// LLAMANDO A FUNCION AUXLIAR QUE RETORNA RESULTADO\n";
            cad += "get_local 0 \n";
            cad += (ambito.getSize() - 1)+"\n";
            cad += "ADD\n";
            cad += "1\n";
            cad += "ADD\n";
            cad += "// EXPRESION 1: PUNTERO 1\n";
            cad += t1+"\n";
            cad += "get_local 0\n";
            cad += (ambito.getSize()-1)+"\n";
            cad += "ADD\n";
            cad += "2\n";
            cad += "ADD\n";
            cad += "// EXPRESION 2: PUNTERO 2\n";
            cad += t2+"\n";
            cad += "// LLMANDO A FUNCION: \n";
            cad += "get_local 0\n";
            cad += (ambito.getSize() - 1)+"\n";
            cad += "ADD\n";
            cad += "set_local 0\n";
            cad += "Call $_STRING_EQUAL_DIFF\n";
            cad += "get_local 0\n";
            cad += (ambito.getSize() - 1)+"\n";
            cad += "DIFF\n";
            cad += "set_local 0\n";
            cad += "get_local $ret // obteniendo el resultado\n";
            cad += (this.operador == InfoEstatica.Estatico.OPERADORES.DIFERENTE?"EQZ\n":"");
            return  cad;
        } catch (Exception e) {
        }
        return "";
    }
    
    private String CadenaComparacion(Ambito ambito, String t1, String t2)
    {
        try {
            String cad = "";
            cad += "// LLAMANDO A FUNCION AUXLIAR QUE RETORNA RESULTADO\n";
            cad += "get_local 0 \n";
            cad += (ambito.getSize() - 1)+"\n";
            cad += "ADD\n";
            cad += "1\n";
            cad += "ADD\n";
            cad += "// EXPRESION 1: PUNTERO 1\n";
            cad += t1+"\n";
            cad += "get_local 0\n";
            cad += (ambito.getSize()-1)+"\n";
            cad += "ADD\n";
            cad += "2\n";
            cad += "ADD\n";
            cad += "// EXPRESION 2: PUNTERO 2\n";
            cad += t2+"\n";
            cad += "// LLMANDO A FUNCION: \n";
            cad += "get_local 0\n";
            cad += (ambito.getSize() - 1)+"\n";
            cad += "ADD\n";
            cad += "set_local 0\n";
            switch(this.operador)
            {
                case MAYOR:
                {
                    cad += "Call $_STRING_MAYOR\n";
                    break;
                }
                case MAYORIGUAL:
                {
                    cad += "Call $_STRING_MAYOR_EQUAL\n";
                    break;
                }
                case MENOR:
                {
                    cad += "Call $_STRING_MENOR\n";
                    break;
                }
                case MENORIGUAL:
                {
                    cad += "Call $_STRING_MENOR_EQUAL\n";
                    break;
                }
            }
            cad += "get_local 0\n";
            cad += (ambito.getSize() - 1)+"\n";
            cad += "DIFF\n";
            cad += "set_local 0\n";
            cad += "get_local $ret // obteniendo el resultado\n";
            return  cad;
        } catch (Exception e) {
        }
        return "";
    }

    private Object TraduceNot(Expresion op1, String codigo, Ambito ambito) {
        try {
            String tipo1 = op1.getTipo(ambito);
            NodoAST valor1 = (NodoAST) op1;
            String t1 = (String) valor1.generateByteCode(ambito);
            switch (codigo) {
                case "3": {
                    resultado = "BOOLEAN";
                    String cad = t1 + "\nEQZ\n";
                    return cad;
                }
                default: {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1, "No es posible aplicar la operacion: NOT", "Semantico",
                            super.getLinea(), super.getColumna(), false, super.getArchivo()));
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica",
                     "Error al traducir el NOT: " + e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(),
                     false, super.getArchivo()));
        }
        return "";
    }

    private Object TraduceAnd(Expresion op1, Expresion op2, String codigo, Ambito ambito) {
        try {
            String tipo1 = op1.getTipo(ambito);
            String tipo2 = op2.getTipo(ambito);
            NodoAST valor1 = (NodoAST) op1;
            String t1 = (String) valor1.generateByteCode(ambito);
            NodoAST valor2 = (NodoAST) op2;
            String t2 = (String) valor2.generateByteCode(ambito);
            switch (codigo) {
                case "33": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\nMULT\n";
                }
                default: {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1 + "&&" + tipo2, "No es posible aplicar la operacion: AND", "Semantico",
                            super.getLinea(), super.getColumna(), false, super.getArchivo()));
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica",
                     "Error al traducir el AND: " + e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(),
                     false, super.getArchivo()));
        }
        return "";
    }

    private Object TraduceOR(Expresion op1, Expresion op2, String codigo, Ambito ambito) {
        try {
            String tipo1 = op1.getTipo(ambito);
            String tipo2 = op2.getTipo(ambito);
            NodoAST valor1 = (NodoAST) op1;
            String t1 = (String) valor1.generateByteCode(ambito);
            NodoAST valor2 = (NodoAST) op2;
            String t2 = (String) valor2.generateByteCode(ambito);
            switch (codigo) {
                case "33": {
                    resultado = "BOOLEAN";
                    return t1 + "\n" + t2 + "\nADD\n";
                }
                default: {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1 + "||" + tipo2, "No es posible aplicar la operacion: OR", "Semantico",
                            super.getLinea(), super.getColumna(), false, super.getArchivo()));
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica",
                     "Error al traducir el OR: " + e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(),
                     false, super.getArchivo()));
        }
        return "";
    }

    //**************************************************************************************
    //*                                      IGUAL                                         *
    //**************************************************************************************
    //*               |    ENTERO    |   CADENA   |    BOOLEAN   |   CARACTER  |   DECIMAL *
    //*-------------------------------------------------------------------------------------
    //*    ENTERO     |      11      |    pp      |      13      |     14      |     15    *
    //*------------------------------------------------------------------------------------*
    //*    CADENA     |      00      |    PP      |      00      |     00      |     00    *
    //*------------------------------------------------------------------------------------*
    //*    BOOLEAN    |      31      |    PP      |      00      |     34      |     35    *
    //*------------------------------------------------------------------------------------*
    //*    CARACTER   |      41      |    PP      |      43      |     44      |     45    *
    //*------------------------------------------------------------------------------------*
    //*    DECIMAL    |      51      |    PP      |      53      |     54      |     55    *
    //*------------------------------------------------------------------------------------*
    private Object TraducePotencia(Expresion op1, Expresion op2, String codigo, Ambito ambito) {
        try {
            String tipo1 = op1.getTipo(ambito);
            String tipo2 = op2.getTipo(ambito);
            NodoAST valor1 = (NodoAST) op1;
            String t1 = (String) valor1.generateByteCode(ambito);
            NodoAST valor2 = (NodoAST) op2;
            String t2 = (String) valor2.generateByteCode(ambito);
            switch (codigo) {
                case "11": {
                    resultado = "ENTERO";
                    String cad = "// PASANDO PARAMETROS A POTENCIA\n";
                    cad += "// PRIMER PARAMETRO\n";
                    cad += "get_local 0 // PUNTERO DE STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // sumando...\n";
                    cad += "1 // PARAMETRO BASE...\n";
                    cad += "ADD // POSICION ABSOULTA...\n";
                    cad += "// BASE.....\n";
                    cad += t1 + "\n";
                    cad += "// FIN BASE \n";
                    //cad += "set_local $calc\n";
                    cad += "// SEGUNDO PARAMETRO\n";
                    cad += "get_local 0 // PUNTERO STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // SUMA....\n";
                    cad += "2 // PARAMETRO POTENCIADOR\n";
                    cad += "ADD // POSICION ABSOLUTA DE LA FUNCION\n";
                    cad += "// POTENCIA\n";
                    cad += t2 + "\n";
                    //cad += "set_local $calc\n";
                    cad += "// FIN POTENCIA\n";
                    cad += "get_local 0 // PUNTERO STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // CAMBIANDO DEL AMBITO\n";
                    cad += "set_local 0 // ACTUALIZANDO EL PUNTERO\n";
                    cad += "Call $_POW // LLAMADA DE POTENCIA\n";
                    cad += "get_local 0 // PUNTERO DE STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "DIFF // RESTA\n";
                    cad += "set_local 0 // ACTUALIZANDO EL PUNTERO\n";
                    cad += "get_local $ret// obteniendo el retorno\n";
                    return cad;

                }
                case "13": {
                    resultado = "ENTERO";
                    String cad = "// PASANDO PARAMETROS A POTENCIA\n";
                    cad += "// PRIMER PARAMETRO\n";
                    cad += "get_local 0 // PUNTERO DE STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // sumando...\n";
                    cad += "1 // PARAMETRO BASE...\n";
                    cad += "ADD // POSICION ABSOULTA...\n";
                    cad += "// BASE.....\n";
                    cad += t1 + "\n";
                    cad += "// FIN BASE \n";
                   // cad += "set_local $calc\n";
                    cad += "// SEGUNDO PARAMETRO\n";
                    cad += "get_local 0 // PUNTERO STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // SUMA....\n";
                    cad += "2 // PARAMETRO POTENCIADOR\n";
                    cad += "ADD // POSICION ABSOLUTA DE LA FUNCION\n";
                    cad += "// POTENCIA\n";
                    cad += t2 + "\n";
                    //cad += "set_local $calc\n";
                    cad += "// FIN POTENCIA\n";
                    cad += "get_local 0 // PUNTERO STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // CAMBIANDO DEL AMBITO\n";
                    cad += "set_local 0 // ACTUALIZANDO EL PUNTERO\n";
                    cad += "Call $_POW // LLAMADA DE POTENCIA\n";
                    cad += "get_local 0 // PUNTERO DE STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "DIFF // RESTA\n";
                    cad += "set_local 0 // ACTUALIZANDO EL PUNTERO\n";
                    cad += "get_local $ret// obteniendo el retorno\n";
                    return cad;
                }
                case "14": {
                    resultado = "ENTERO";
                    String cad = "// PASANDO PARAMETROS A POTENCIA\n";
                    cad += "// PRIMER PARAMETRO\n";
                    cad += "get_local 0 // PUNTERO DE STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // sumando...\n";
                    cad += "1 // PARAMETRO BASE...\n";
                    cad += "ADD // POSICION ABSOULTA...\n";
                    cad += "// BASE.....\n";
                    cad += t1 + "\n";
                    cad += "// FIN BASE \n";
                   // cad += "set_local $calc\n";
                    cad += "// SEGUNDO PARAMETRO\n";
                    cad += "get_local 0 // PUNTERO STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // SUMA....\n";
                    cad += "2 // PARAMETRO POTENCIADOR\n";
                    cad += "ADD // POSICION ABSOLUTA DE LA FUNCION\n";
                    cad += "// POTENCIA\n";
                    cad += t2 + "\n";
                    //cad += "set_local $calc\n";
                    cad += "// FIN POTENCIA\n";
                    cad += "get_local 0 // PUNTERO STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // CAMBIANDO DEL AMBITO\n";
                    cad += "set_local 0 // ACTUALIZANDO EL PUNTERO\n";
                    cad += "Call $_POW // LLAMADA DE POTENCIA\n";
                    cad += "get_local 0 // PUNTERO DE STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "DIFF // RESTA\n";
                    cad += "set_local 0 // ACTUALIZANDO EL PUNTERO\n";
                    cad += "get_local $ret// obteniendo el retorno\n";
                    return cad;
                }
                case "51": {
                    resultado = "DECIMAL";
                    String cad = "// PASANDO PARAMETROS A POTENCIA\n";
                    cad += "// PRIMER PARAMETRO\n";
                    cad += "get_local 0 // PUNTERO DE STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // sumando...\n";
                    cad += "1 // PARAMETRO BASE...\n";
                    cad += "ADD // POSICION ABSOULTA...\n";
                    cad += "// BASE.....\n";
                    cad += t1 + "\n";
                    cad += "// FIN BASE \n";
                    //cad += "set_local $calc\n";
                    cad += "// SEGUNDO PARAMETRO\n";
                    cad += "get_local 0 // PUNTERO STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // SUMA....\n";
                    cad += "2 // PARAMETRO POTENCIADOR\n";
                    cad += "ADD // POSICION ABSOLUTA DE LA FUNCION\n";
                    cad += "// POTENCIA\n";
                    cad += t2 + "\n";
                    //cad += "set_local $calc\n";
                    cad += "// FIN POTENCIA\n";
                    cad += "get_local 0 // PUNTERO STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // CAMBIANDO DEL AMBITO\n";
                    cad += "set_local 0 // ACTUALIZANDO EL PUNTERO\n";
                    cad += "Call $_POW // LLAMADA DE POTENCIA\n";
                    cad += "get_local 0 // PUNTERO DE STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "DIFF // RESTA\n";
                    cad += "set_local 0 // ACTUALIZANDO EL PUNTERO\n";
                    cad += "get_local $ret// obteniendo el retorno\n";
                    return cad;
                }
                case "53": {
                    resultado = "DECIMAL";
                    String cad = "// PASANDO PARAMETROS A POTENCIA\n";
                    cad += "// PRIMER PARAMETRO\n";
                    cad += "get_local 0 // PUNTERO DE STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // sumando...\n";
                    cad += "1 // PARAMETRO BASE...\n";
                    cad += "ADD // POSICION ABSOULTA...\n";
                    cad += "// BASE.....\n";
                    cad += t1 + "\n";
                    cad += "// FIN BASE \n";
                    //cad += "set_local $calc\n";
                    cad += "// SEGUNDO PARAMETRO\n";
                    cad += "get_local 0 // PUNTERO STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // SUMA....\n";
                    cad += "2 // PARAMETRO POTENCIADOR\n";
                    cad += "ADD // POSICION ABSOLUTA DE LA FUNCION\n";
                    cad += "// POTENCIA\n";
                    cad += t2 + "\n";
                    //cad += "set_local $calc\n";
                    cad += "// FIN POTENCIA\n";
                    cad += "get_local 0 // PUNTERO STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // CAMBIANDO DEL AMBITO\n";
                    cad += "set_local 0 // ACTUALIZANDO EL PUNTERO\n";
                    cad += "Call $_POW // LLAMADA DE POTENCIA\n";
                    cad += "get_local 0 // PUNTERO DE STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "DIFF // RESTA\n";
                    cad += "set_local 0 // ACTUALIZANDO EL PUNTERO\n";
                    cad += "get_local $ret// obteniendo el retorno\n";
                    return cad;
                }
                case "54": {
                    resultado = "DECIMAL";
                    String cad = "// PASANDO PARAMETROS A POTENCIA\n";
                    cad += "// PRIMER PARAMETRO\n";
                    cad += "get_local 0 // PUNTERO DE STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // sumando...\n";
                    cad += "1 // PARAMETRO BASE...\n";
                    cad += "ADD // POSICION ABSOULTA...\n";
                    cad += "// BASE.....\n";
                    cad += t1 + "\n";
                    cad += "// FIN BASE \n";
                    //cad += "set_local $calc\n";
                    cad += "// SEGUNDO PARAMETRO\n";
                    cad += "get_local 0 // PUNTERO STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // SUMA....\n";
                    cad += "2 // PARAMETRO POTENCIADOR\n";
                    cad += "ADD // POSICION ABSOLUTA DE LA FUNCION\n";
                    cad += "// POTENCIA\n";
                    cad += t2 + "\n";
                    //cad += "set_local $calc\n";
                    cad += "// FIN POTENCIA\n";
                    cad += "get_local 0 // PUNTERO STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "ADD // CAMBIANDO DEL AMBITO\n";
                    cad += "set_local 0 // ACTUALIZANDO EL PUNTERO\n";
                    cad += "Call $_POW // LLAMADA DE POTENCIA\n";
                    cad += "get_local 0 // PUNTERO DE STACK\n";
                    cad += (ambito.getSize() - 1) + "// TAMANIO DEL AMBITO\n";
                    cad += "DIFF // RESTA\n";
                    cad += "set_local 0 // ACTUALIZANDO EL PUNTERO\n";
                    cad += "get_local $ret// obteniendo el retorno\n";
                    return cad;
                }
                default: {
                    InfoEstatica.Estatico.agregarError(new TError(tipo1 + "Potencia: " + tipo2, "La operacion Potencia no se puede aplicar para los tipos indicados",
                             "Semantico", super.getLinea(), super.getColumna(), false, super.getArchivo()));
                }
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError(
                    "No Aplica", "Error al traducir Potencia: " + e.getMessage(), "Ejecucion",
                     super.getLinea(), super.getColumna(), false, super.getArchivo()
            ));
        }
        return "";
    }
}
