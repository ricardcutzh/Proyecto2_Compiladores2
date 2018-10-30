/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoEstatica;

import java.util.ArrayList;
import ErrorManager.TError;
import Estructuras.Display;
import GUI.NavegorWeb.Interfaz.Navegador;
import GUI.TextEditorPanel.WebAssEditor;
import ObjsComun.BreakPointNode;
import ObjsComun.NodoIDValor;
import java.awt.Color;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
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
    
    public static String proyectPath;
    ////////////////////////////////////////////////////////////////////////////
    public static String tipoFuncion;
    ////////////////////////////////////////////////////////////////////////////
    public static HashMap<String, BreakPointNode> breakPoints = new HashMap<>();
    ////////////////////////////////////////////////////////////////////////////
    public static Thread hilo;
    public static Boolean esLinea;
    public static RSyntaxTextArea punteroText;
    public static Boolean suspended;
    ////////////////////////////////////////////////////////////////////////////
    public static Stack<Boolean> pilaCiclos;
    ////////////////////////////////////////////////////////////////////////////
    public static Navegador navegador;
    ////////////////////////////////////////////////////////////////////////////
    public static JTabbedPane Pestanas;
    public static ArrayList<Character> tabs;
    ////////////////////////////////////////////////////////////////////////////
    public static JTable pilitaTable;
    public static JTable StackTable;
    public static JTable HeapTable;
    ////////////////////////////////////////////////////////////////////////////
    public static Display display;
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
        tabs = new ArrayList<>();
        display = new Display();
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
            //MarcarError(error.getLinea());
            MarcarErrorArchivo(error.getArchivo(), error.getLinea());
        } catch (Exception e) {
        }

    }

    public static void AgregarTablaSimbolos(Simbolos.SimboloTabla sim) {
        try {
            DefaultTableModel model = (DefaultTableModel) tablaSimbolos.getModel();
            Object rowData[] = new Object[8];
            rowData[0] = sim.getAmbito();
            rowData[1] = sim.getIdSimbolo();
            rowData[2] = sim.getTipo();
            rowData[3] = sim.getLinea() + 1;
            rowData[4] = sim.getColumna();
            rowData[5] = sim.getTamanio();
            rowData[6] = sim.getEsVector() ? "Si" : "No";
            rowData[7] = sim.getRol();
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
        tablaSimbolos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Ambito", "Nombre", "Tipo", "Linea", "Columna", "Tamano", "Es Arreglo", "Rol"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    public static void ImprimeEnConsola(String mensaje) {
        console.append("\n>> " + mensaje);
    }
    
    public static void imprimeCaracter(Character caracter)
    {
        console.append(caracter.toString());
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
                //MarcarError(e.getLinea());
                MarcarErrorArchivo(e.getArchivo(), e.getLinea());
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
                punteroText.setCaretPosition(inicio);// MUEVE EL CURSOR EN EL DEBUGGER
                h.addHighlight(inicio, fin, new DefaultHighlighter.DefaultHighlightPainter(Color.LIGHT_GRAY));

            } catch (BadLocationException ex) {
                Logger.getLogger(Estatico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void MarcarError(int linea) {
        if (punteroText != null) {
            Highlighter h = punteroText.getHighlighter();
            try {
                int inicio = punteroText.getLineStartOffset(linea);
                int fin = punteroText.getLineEndOffset(linea);
                h.addHighlight(inicio, fin, new DefaultHighlighter.DefaultHighlightPainter(Color.decode("#e60000")));
            } catch (Exception e) {

            }
        }
    }

    public static void quitarMarcas() {
        if (punteroText != null) {
            Highlighter h = punteroText.getHighlighter();
            h.removeAllHighlights();
        }
    }

    public static void MarcarErrorArchivo(String archivo, int linea) {
        if (Pestanas.getTabCount() > 0) {
            for (int x = 0; x < Pestanas.getTabCount(); x++) {
                Object o = Pestanas.getComponentAt(x);
                if (o instanceof WebAssEditor) {
                    if (((WebAssEditor) o).getFileName().equals(archivo)) {
                        RSyntaxTextArea aux = ((WebAssEditor) o).areaEdicion;
                        RSyntaxTextArea temp = punteroText;
                        punteroText = aux;
                        MarcarError(linea);
                        punteroText = temp;
                        return;
                    }
                }
            }
        }
    }

    public static void MarcarLineaArchivo(String archivo, int linea) {
        if (Pestanas.getTabCount() > 0) {
            for (int x = 0; x < Pestanas.getTabCount(); x++) {
                Object o = Pestanas.getComponentAt(x);
                if (o instanceof WebAssEditor) {
                    if (((WebAssEditor) o).getFileName().equals(archivo)) {
                        RSyntaxTextArea aux = ((WebAssEditor) o).areaEdicion;
                        RSyntaxTextArea temp = punteroText;
                        punteroText = aux;
                        Pestanas.setSelectedIndex(x);
                        MarcaLinea(linea);
                        punteroText = temp;
                        return;
                    }
                }
            }
        }
    }

    public static void desmarcarTodo() {
        if (Pestanas.getTabCount() > 0) {
            for (int x = 0; x < Pestanas.getTabCount(); x++) {
                Object o = Pestanas.getComponentAt(x);
                if (o instanceof WebAssEditor) {
                    RSyntaxTextArea aux = ((WebAssEditor) o).areaEdicion;
                    RSyntaxTextArea temp = punteroText;
                    punteroText = aux;
                    quitarMarcas();
                    punteroText = temp;
                }
            }
        }
    }

    public static void tabula() {
        tabs.add('r');
    }

    public static void destabula() {
        if (!tabs.isEmpty()) {
            tabs.remove(0);
        }
    }

    public static String aplicaTabulaciones(String original) {
        String aux = "";
        for (short x = 0; x < tabs.size(); x++) {
            aux += "\t";
        }
        String salida = "";
        String lineas[] = original.split("\n");
        for (String l : lineas) {
            if(!l.equals("") || !l.equals(" "))
            {
                salida += aux + l +"\n";
            }
        }
        return salida;
    }
    
    public static void ActualizaPilita(Estructuras.Pilita pilita)
    {
        try 
        {
            DefaultTableModel model = (DefaultTableModel)pilitaTable.getModel();
            if(model.getRowCount()>0)
            {
                model.setRowCount(0);
            }
            pilita.graficarPilita(model);
        } catch (Exception e) 
        {
            
        }
    }
    
    public static void ActualizaStack(Estructuras.StackAmbito stack)
    {
        try 
        {
            DefaultTableModel model = (DefaultTableModel)StackTable.getModel();
            if(model.getRowCount()>0)
            {
                model.setRowCount(0);
            }
            stack.graficarStack(model);
        } catch (Exception e) 
        {
            
        }
    }
    
    public static void ActualizaHeap(Estructuras.Global.DasmHeap heap)
    {
        try {
            DefaultTableModel model = (DefaultTableModel)HeapTable.getModel();
            if(model.getRowCount()>0)
            {
                model.setRowCount(0);
            }
            heap.graficarHeap(model);
        } catch (Exception e) {
        }
    }
}
