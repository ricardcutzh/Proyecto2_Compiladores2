/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_assembly;

/**
 *
 * @author richard
 */
import GUI.IDE;
import javax.swing.UIManager;


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
    
}
