/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.NavegorWeb.Interfaz;

import java.awt.Color;
import javax.swing.JFrame;

/**
 * Clase que maneja el frame del navegador
 * @author richard
 */
public class Navegador extends JFrame{
    PanelDibujo panel;
    /**
     * Constructor que maneja el Frame del Navegador
     */
    public Navegador()
    {
        setSize(500, 500);
        setResizable(false);
        this.panel = new PanelDibujo();
        this.add(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBackground(Color.WHITE);
    }
    
    /**
     * Getter del Panel en donde se dibuja
     * @return referencia al Panel de Dibujo
     */
    public PanelDibujo getPanel() {
        return panel;
    }
    
    
}
