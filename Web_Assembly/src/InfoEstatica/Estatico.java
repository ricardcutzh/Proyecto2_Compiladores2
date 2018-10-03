/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoEstatica;

import java.util.ArrayList;
import ErrorManager.TError;
import ObjsComun.BreakPointNode;
import ObjsComun.NodoIDValor;
import java.awt.Color;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

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
    public static JTable tablaBreaks;

    public static MODALIDAD mod;

    public static HashMap<String, BreakPointNode> breakPoints = new HashMap<>();
    ////////////////////////////////////////////////////////////////////////////
    public static Thread hilo;
    public static Boolean esLinea;
    public static RSyntaxTextArea punteroText;
    public static Boolean suspended;
    ////////////////////////////////////////////////////////////////////////////
    public static Stack<Boolean> pilaCiclos;
    ////////////////////////////////////////////////////////////////////////////
    public static void setUp(JTextArea consola, JTable tablaErrores, JTable tablaSimbolo) {
        errores = new ArrayList<>();
        console = consola;
        console.setText(">> Web Assembly | Compiladores 2 | 201503476");
        tablaError = tablaErrores;
        tablaSimbolos = tablaSimbolo;
        hilo = new Thread();
        esLinea = false;
        suspended = false;
        pilaCiclos = new Stack<>();
        pilaCiclos.push(false);
        resetTablas();
    }

    public static void colocaTablaBreaks(JTable Tabla) {
        tablaBreaks = Tabla;
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

    public enum MODALIDAD {
        DEBUGG_MODE,
        RUN_MODE
    }

    /**
     * METODO QUE PERMITE ACTUALIZAR LA TABLA DE ERRORES
     *
     * @param error el error que se desea ver en la tabla
     */
    public static void agregarError(TError error) {
        try {
            errores.add(error);
            DefaultTableModel model = (DefaultTableModel) tablaError.getModel();
            Object rowData[] = new Object[6];
            rowData[0] = error.getTipo();
            rowData[1] = error.getLexema();
            rowData[2] = error.getMensaje();
            rowData[3] = error.getLinea() + 1;
            rowData[4] = error.getColumna();
            rowData[5] = error.getArchivo();
            model.addRow(rowData);
        } catch (Exception e) {
        }

    }

    public static void agregarBreakPoint(String archivo, int linea) {
        try {
            DefaultTableModel model = (DefaultTableModel) tablaBreaks.getModel();
            Object rowData[] = new Object[2];
            rowData[0] = linea + 1;
            rowData[1] = archivo;
            model.addRow(rowData);
        } catch (Exception e) {
        }
    }

    public static void resetBreaks() {
        try {
            tablaBreaks.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Numero de Linea", "Archivo de Origen"
                    }
            ) {
                boolean[] canEdit = new boolean[]{
                    false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
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

    public static void ActualizarTablaDeErrores() {
        try {
            DefaultTableModel model = (DefaultTableModel) tablaError.getModel();
            Object rowData[] = new Object[6];
            for (TError e : errores) {
                rowData[0] = e.getTipo();
                rowData[1] = e.getLexema();
                rowData[2] = e.getMensaje();
                rowData[3] = e.getLinea() + 1;
                rowData[4] = e.getColumna();
                rowData[5] = e.getArchivo();
                model.addRow(rowData);
            }
        } catch (Exception e) {

        }
    }

    public static void MarcaLinea(int linea) {
        if (punteroText != null) {
            Highlighter h = punteroText.getHighlighter();
            h.removeAllHighlights();
            try {
                int inicio = punteroText.getLineStartOffset(linea);
                int fin = punteroText.getLineEndOffset(linea);
                h.addHighlight(inicio, fin, new DefaultHighlighter.DefaultHighlightPainter(Color.LIGHT_GRAY));

            } catch (BadLocationException ex) {
                Logger.getLogger(Estatico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void quitarMarcas() {
        if (punteroText != null) {
            Highlighter h = punteroText.getHighlighter();
            h.removeAllHighlights();
        }
    }
}
