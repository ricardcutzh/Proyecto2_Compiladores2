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
 * Clase que manejara la traduccion de un valor primitivo
 * @author richard
 */
public class ValorPrimitivo extends NodoAST implements Expresion{
    
    Object valor;
    /**
     * Constructor de la clase de los metodos primitivos
     * @param linea linea donde se encuentra
     * @param columna columna donde se encuentra
     * @param Archivo el archivo donde se ubica
     * @param valor el valor que se va a guardar
     */
    public ValorPrimitivo(int linea, int columna, String Archivo, Object valor) {
        super(linea, columna, Archivo);
        this.valor = valor;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            String tipo = getTipo(ambito);
            switch(tipo)
            {
                case "ENTERO":// TAMANO DE 4 BYTES
                {
                    return valor.toString(); // DEVUELVO EL VALOR ENTERO DEL VALOR
                }
                case "DECIMAL":// TAMANO DE 8 BYTES
                {
                    return valor.toString(); // DEVUELVO EL VALOR DECIMAL DEL VALOR
                }
                case "CADENA":
                {
                    
                    return getStringDeclaration((String)this.valor);
                }
                case "BOOLEAN":// TAMANO DE 1 BYTE
                {
                    Boolean aux = (Boolean)this.valor;
                    if(aux) {return "1";}
                    return "0";
                }
                case "CARACTER":// TAMANO DE 1 BYTE
                {
                    Character aux = (Character)this.valor;
                    return String.valueOf((int)aux);// DEVUELVO EL VALOR NUMERICO DEL CHARACTER
                }
            }
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al ejecutar: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo()));
        }
        return "";
    }

    @Override
    public String getTipo(Ambito ambito) {
        TypeManage manejador = new TypeManage(this.valor);
        return manejador.evaluaValor();
    }

    @Override
    public Object getValor(Ambito ambito) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private String getStringDeclaration(String cadena)
    {
        try 
        {
            cadena = cadena.replace("\"", "");
            char arr [] = cadena.toCharArray();
            String cad = "// ASIGNANDO CADENA.....\n";
            cad += "get_global 0 // METIENDO EL PUNTERO DEL INICIO DE LA CADENA DEL HEAP EN EL FONDO DEL STACK\n";
            for(char c : arr)
            {
                cad += "get_global 0 // PUNTERO HEAP\n";
                cad += String.valueOf((int)c)+"// '"+c+"' INSERTANDO EL VALOR DEL CARACTER \n";
                cad += "set_global $calc // COLOCO EL CARACTER EN LA POSICION QUE LE TECOA...\n";
                cad += "get_global 0 // ACTUALIZANDO PUNTERO..\n";
                cad += "1 // AUMENTANDO 1\n";
                cad += "ADD //SUMO\n";
                cad += "set_global 0 // AUMENTANDO EL PUNTERO EN UNO\n";
            }
            cad += "get_global 0 // PUNTERO HEAP\n";
            cad += "0 // CARACTER NULO\n";
            cad += "set_global $calc // COLOCANDO CHAR NULO\n";
            cad += "get_global 0 // ACTUALIZANDO PUNTERO..\n";
            cad += "1 // AUMENTANDO 1\n";
            cad += "ADD // SUMANDO\n";
            cad += "set_global 0 // AUMENTANDO EL PUNTERO EN UNO\n";
            return cad;
        } catch (Exception e) 
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica"
                    , "Error al traducir la declaracion de una cadena"
                    , "Ejecucion", super.getLinea(), super.getColumna()
                    , Boolean.FALSE, super.getArchivo()));
        }
        return "";
    }
    
}
