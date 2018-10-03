/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_assembly;

/**
 * Clase que maneja el inicio de la Aplicacion de Web Assembly
 * @author richard
 */
import GUI.IDE;
import javax.swing.UIManager;
import DracoScriptPackage.Analizador.*;
import java.io.FileReader;
import java.util.ArrayList;

public class Web_Assembly {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
           UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        } catch (Exception e) {
            System.err.println("Error al colocar el look And feel");
        }
        IDE ide = new IDE();
        ide.setVisible(true);
        
        
    }
    
    
    public static void pruebas()
    {

    }
    
}
