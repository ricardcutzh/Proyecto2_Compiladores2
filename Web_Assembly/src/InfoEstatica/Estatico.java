/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoEstatica;
import java.util.ArrayList;
import ErrorManager.TError;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 * Clase que sera de apoyo para La informacion Estatica
 * @author richard
 */
public class Estatico {
    
    /**
     * elemento estatico que solo almacena los errores
     */
    public static ArrayList<TError> errores;
    
    public static JTextArea console;
    public static JTable tablaError;
    public static JTable tablaSimbolos;
    
    public static void setUp(JTextArea consola, JTable tablaErrores, JTable tablaSimbolo)
    {
        errores = new ArrayList<>();
        console = consola;
        console.setText(">> Web Assembly | Compiladores 2 | 201503476");
        tablaError = tablaErrores;
        tablaSimbolos = tablaSimbolo;
        resetTablas();
    }
    
    public enum OPERADORES
    {
        MAS,
        MENOS,
        MULT,
        DIV,
        POT,
        MOD,
        INC,
        DEC,
        IGUAL,
        DIFERENTE,
        MAYOR,
        MENOR,
        MAYORIGUAL,
        MENORIGUAL,
        AND,
        OR,
        NOT
    }
    
    /**
     * METODO QUE PERMITE ACTUALIZAR LA TABLA DE ERRORES
     * @param error el error que se desea ver en la tabla
     */
    public static void agregarError(TError error)
    {
        errores.add(error);
        String matriz [][] = new String[errores.size()][6];
        for (int i = 0; i < errores.size(); i++) {
            matriz[i][0] = errores.get(i).getTipo();
            matriz[i][1] = errores.get(i).getLexema();
            matriz[i][2] = errores.get(i).getMensaje();
            matriz[i][3] = String.valueOf(errores.get(i).getLinea()+1);
            matriz[i][4] = String.valueOf(errores.get(i).getColumna());
            matriz[i][5] = errores.get(i).getArchivo();
        }
        
        /*ACTUALIZANDO LA TABLA AL COLOCAR ERRORES*/
        tablaError.setModel(new javax.swing.table.DefaultTableModel(
            matriz,
            new String [] {
                "Tipo de Error", "Lexema", "Mensaje", "Linea", "Columna", "Archivo Origen"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    public static void resetTablas()
    {
        tablaError.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{
                
            },
            new String [] {
                "Tipo de Error", "Lexema", "Mensaje", "Linea", "Columna", "Archivo Origen"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    public static void ImprimeEnConsola(String mensaje)
    {
        console.append("\n>> "+mensaje);
    }
}
