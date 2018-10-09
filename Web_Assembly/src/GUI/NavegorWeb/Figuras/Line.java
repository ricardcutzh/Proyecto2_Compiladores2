/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.NavegorWeb.Figuras;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Manejador de la figura Linea que se dibuja en el panel
 * @author richard
 */
public class Line implements Figura{
    
    int x1,y1,x2,y2;
    String color;
    int grosor;
    /**
     * Contructor de la linea
     * @param x1 coordenada inicio x
     * @param y1 coordenada inicio y
     * @param x2 coordenada final x
     * @param y2 coordenana final y
     * @param grosor grosor de la linea
     * @param color  color que se desea aplicar
     */
    public Line(int x1, int y1, int x2, int y2, int grosor, String color)
    {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.grosor = grosor;
        this.color = color;
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
        g2d.setStroke(new BasicStroke(this.grosor));
        g2d.drawLine(x1, y1, x2, y2);
    }
    
}
