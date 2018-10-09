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
 * Clase que se encargara de pintar un punto (Circulo) en el panel
 * @author richard
 */
public class Punto implements Figura{
    int posx,posy;
    String color;
    int diametro;
    /**
     * Constructor del objeto tipo Punto 
     * @param posX Coordenada x
     * @param posY Coordenada y
     * @param color Color que se le desea aplicar
     * @param diametro Diametro de la figura
     */
    public Punto(int posX, int posY, String color, int diametro)
    {
        this.posx = posX;
        this.posy = posY;
        this.color = color;
        this.diametro = diametro;
    }
    /**
     * Metodo que dibuja el punto
     * @param g Grafico que se dibujara
     */
    @Override
    public void Dibujar(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Ellipse2D.Double punto = new Ellipse2D.Double(posx, posy, diametro, diametro);
        Color real;
        try {
            real = Color.decode(color);
        } catch (Exception e) {
            real = Color.BLACK;
        }
        g2d.setColor(real);
        g2d.fill(punto);
    }
    
}
