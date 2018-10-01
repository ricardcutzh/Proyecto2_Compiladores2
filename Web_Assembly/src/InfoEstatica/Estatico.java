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
import javax.swing.table.DefaultTableModel;

/**
 * Clase que sera de apoyo para La informacion Estatica
 *
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

    public static void setUp(JTextArea consola, JTable tablaErrores, JTable tablaSimbolo) {
        errores = new ArrayList<>();
        console = consola;
        console.setText(">> Web Assembly | Compiladores 2 | 201503476");
        tablaError = tablaErrores;
        tablaSimbolos = tablaSimbolo;
        resetTablas();
    }

    public enum OPERADORES {
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
     *
     * @param error el error que se desea ver en la tabla
     */
    public static void agregarError(TError error) {
        try {
            errores.add(error);
            DefaultTableModel model = (DefaultTableModel)tablaError.getModel();
            Object rowData[] = new Object[6];
            rowData[0] = error.getTipo();
            rowData[1] = error.getLexema();
            rowData[2] = error.getMensaje();
            rowData[3] = error.getLinea();
            rowData[4] = error.getColumna();
            rowData[5] = error.getArchivo();
            model.addRow(rowData);
        } catch (Exception e) {
        }

    }

    public static void resetTablas() {
        tablaError.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Tipo de Error", "Lexema", "Mensaje", "Linea", "Columna", "Archivo Origen"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    public static void ImprimeEnConsola(String mensaje) {
        console.append("\n>> " + mensaje);
    }
    
    public static void ActualizarTablaDeErrores()
    {
        try {
            DefaultTableModel model = (DefaultTableModel)tablaError.getModel();
            Object rowData[] = new Object[6];
            for(TError e : errores)
            {
                rowData[0] = e.getTipo();
                rowData[1] = e.getLexema();
                rowData[2] = e.getMensaje();
                rowData[3] = e.getLinea();
                rowData[4] = e.getColumna();
                rowData[5] = e.getArchivo();
                model.addRow(rowData);
            }
        } catch (Exception e) {
            
        }
    }
}
