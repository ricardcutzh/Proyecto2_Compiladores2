/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.NavegorWeb.Figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase que dibuja un cuadrado en un panel
 *
 * @author richard
 */
public class Quadrate implements Figura {

    int posx, posy, alto, ancho;
    String color;

    /**
     * Constructor del manejador de cuadrado
     *
     * @param posx
     * @param posy
     * @param alto
     * @param ancho
     * @param color
     */
    public Quadrate(int posx, int posy, int alto, int ancho, String color) {
        this.posx = posx;
        this.posy = posy;
        this.alto = alto;
        this.ancho = ancho;
        this.color = color;
    }

    @Override
    public void Dibujar(Graphics g) {
        try {
            Graphics2D g2d = (Graphics2D) g;

            Color real = Color.BLACK;
            try {
                real = Color.decode(color);
            } catch (Exception e) {
            }

            Rectangle2D.Double r = new Rectangle2D.Double(posx, posy, ancho, alto);
            g2d.setColor(real);
            g2d.fill(r);
        } catch (Exception e) {
        }

    }

}
