/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.NavegorWeb.Figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * Clase que maneja el dibujo de un ovalo
 * @author richard
 */
public class Oval implements Figura{
    int posx,posy,alto,ancho;
    String color;
    /**
     * Constructor del manejador del ovalo
     * @param posx
     * @param posy
     * @param color
     * @param alto
     * @param ancho 
     */
    public Oval(int posx, int posy, String color, int alto, int ancho)
    {
        this.posx = posx;
        this.posy = posy;
        this.color = color;
        this.alto = alto;
        this.ancho = ancho;
    }
    
    @Override
    public void Dibujar(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Color real;
        try {
            real = Color.decode(color);
        } catch (Exception e) {
            real = Color.BLACK;
        }
        Ellipse2D.Double ovalo = new Ellipse2D.Double(posx, posy, ancho, alto);
        g2d.setColor(real);
        g2d.fill(ovalo);
    }
    
}
