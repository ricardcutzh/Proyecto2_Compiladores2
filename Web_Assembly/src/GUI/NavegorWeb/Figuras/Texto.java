/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.NavegorWeb.Figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author richard
 */
public class Texto implements Figura{
    int posx, posy;
    String color, cadena;
    /**
     * Constructor de la figura que pinta un texto en el Panel
     * @param posx
     * @param posy
     * @param color
     * @param cadena 
     */
    public Texto(int posx, int posy, String color, String cadena)
    {
        this.posx = posx;
        this.posy = posy;
        this.color = color;
        this.cadena = cadena;
    }
    
    @Override
    public void Dibujar(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Color real = Color.BLACK;
        try {
            real = Color.decode(color);
        } catch (Exception e) {
        }
        g2d.setColor(real);
        g2d.drawString(cadena, posx, posy);
    }
    
}
