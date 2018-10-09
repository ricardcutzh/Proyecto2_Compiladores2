/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TextEditorPanel;

import DppPackage.DppAnalizador;
import DracoScriptPackage.DracoAnalizador;
import GUI.NavegorWeb.Interfaz.Navegador;
import InfoEstatica.Estatico;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.*;


/**
 * Clase que se encarga de generar el editor de texto correspodiente para cada
 * uno de los archivos que se abren
 * @author richard
 */
public class WebAssEditor extends javax.swing.JPanel {
    
    public RSyntaxTextArea areaEdicion;
    String pathArchivo;
    String fileExtension = "";
    String fileName;
    String pathProyecto ="";
    /**
     * Construye un nuevo Panel editor de Texto, Usando la libreria: Rsyntax Highlighter
     * @param pathArchivo ruta del archivo
     * @param pathProyecto Ruta donde se encuentra alojado el proyecto
     */
    
    public WebAssEditor(String pathArchivo, String pathProyecto) {
        this.pathArchivo = pathArchivo;
        initComponents();
        this.pathProyecto = pathProyecto;
        String arr[] = pathArchivo.split("/");
        
        this.fileName  = arr[arr.length - 1];
        try{
            String aux[] = this.fileName.split("\\.");
            this.fileExtension = aux[1];
        }
        catch(Exception e)
        {
            
        }
        
        
        this.setLayout(new BorderLayout());
        RSyntaxTextArea t = new RSyntaxTextArea();
        this.areaEdicion = t;
        colocaColorSyntaxis(t, fileExtension);
        
        
        
        RTextScrollPane sp = new RTextScrollPane(t);
        this.add(sp);
        
        ReadFile();
    }
    
    public void ReadFile()
    {
        try {
            FileReader file = new FileReader(this.pathArchivo);
            BufferedReader reader = new BufferedReader(file);
            String text = "";
            String line = reader.readLine();
            while(line != null)
            {
                text += line+"\n";
                line = reader.readLine();
            }
            this.areaEdicion.setText(text);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error: "+e.getMessage(), "Error Al Abrir el archivo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Metodo que devuelve el path del archivo para realizar su lectura
     * @return String, pathArchivo
     */
    public String getPathFile()
    {
        return this.pathArchivo;
    }
    
    /**
     * Metodo que retorna el nombre del archivo
     * @return String, Nombre del archivo
     */
    public String getFileName()
    {
        return this.fileName;
    }
    
    /**
     * Metodo encargado de colocarle la coloracion de sintaxis correspondiente
     * @param editor Editor al que se le colocara la coloraicon de la sintaxis
     * @param ext Extension que dependiendo de la extension se le colocara
     */
    private void colocaColorSyntaxis(RSyntaxTextArea editor, String ext)
    {
        AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory)TokenMakerFactory.getDefaultInstance();
        switch(ext.toLowerCase())
        {
            case "ds":
            {
                atmf.putMapping("text/ds", "GUI.TextEditorPanel.SyntaxDracoScript.DracoScriptSyntax");
                editor.setSyntaxEditingStyle("text/ds");
                try {
                    Theme theme = Theme.load(getClass().getResourceAsStream("/GUI/TextEditorPanel/SyntaxDracoScript/DracoTema.xml"));
                    theme.apply(editor);
                } catch (Exception e) {
                }
                break;
            }
            case "dasm":
            {
                atmf.putMapping("text/dasm", "GUI.TextEditorPanel.SyntaxDASM.DasmSyntax");
                editor.setSyntaxEditingStyle("text/dasm");
                try {
                    Theme theme = Theme.load(getClass().getResourceAsStream("/GUI/TextEditorPanel/SyntaxDASM/DasmTheme.xml"));
                    theme.apply(editor);
                } catch (Exception e) {
                }
                break;
            }
            case "dpp":
            {
                atmf.putMapping("text/dpp", "GUI.TextEditorPanel.SyntaxDpp.DppSyntax");
                editor.setSyntaxEditingStyle("text/dpp");
                try {
                    Theme theme = Theme.load(getClass().getResourceAsStream("/GUI/TextEditorPanel/SyntaxDpp/miTema.xml"));
                    theme.apply(editor);
                } catch (Exception e) {
                }
                break;
            }
        }
    }
    
    public Boolean saveFile()
    {
        File f = new File(this.pathArchivo);
        try {
            String text = this.areaEdicion.getText();
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            writer.write(text);
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar archivo: "+this.pathArchivo+" | "+e.getMessage());
        }
        return true;
    }
    
    public Object EjecutarAnalisis()
    {
        switch(this.fileExtension)
        {
            case "ds":
            {
                DracoScriptPackage.DracoAnalizador analizador = new DracoAnalizador(this.areaEdicion.getText(), this.fileName, this.pathProyecto);
                Runnable r = analizador;
                Thread t = new Thread(r);
                Estatico.hilo = t;
                Estatico.punteroText = this.areaEdicion;
                Estatico.navegador = new Navegador();
                Estatico.navegador.setVisible(true);
                
                t.start();
                break;
            }
            case "dasm":
            {
                /*NO HACE NADA*/
                break;
            }
            case "dpp":
            {
                DppPackage.DppAnalizador analizador = new DppAnalizador(this.areaEdicion.getText(), this.fileName, pathProyecto);
                Runnable r = analizador;
                Thread t = new Thread(r);
                Estatico.hilo = t;
                Estatico.punteroText = this.areaEdicion;
                /*AQUI GENERA EL CODIGO*/
                t.start();
                break;
            }
        }
        return null;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
