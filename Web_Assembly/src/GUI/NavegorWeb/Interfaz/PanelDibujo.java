/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.NavegorWeb.Interfaz;

import GUI.NavegorWeb.Figuras.Figura;
import GUI.NavegorWeb.Figuras.Line;
import GUI.NavegorWeb.Figuras.Oval;
import GUI.NavegorWeb.Figuras.Punto;
import GUI.NavegorWeb.Figuras.Quadrate;
import GUI.NavegorWeb.Figuras.Texto;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author richard
 */
public class PanelDibujo extends JPanel{
    
    ArrayList<Figura> figuras;
    /**
     * Constructor del Panel de Figura, Inicializa la lista de figuras que se pintaran
     */
    public PanelDibujo()
    {
        this.figuras = new ArrayList<>();
        setBackground(Color.WHITE);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(Figura f:this.figuras)
        {
            f.Dibujar(g);
        }
    }
    
    /**
     * Metodo que agrega un Punto al Panel
     * @param posx coordenada x
     * @param posy coordenada y
     * @param color color que se colocara la figura
     * @param diametro  diametro del punto
     */
    public void addPunto(int posx, int posy, String color, int diametro)
    {
        this.figuras.add(new Punto(posx, posy, color, diametro));
        this.repaint();
    }
    
    /**
     * Metodo que agrega un cuadrado al Panel
     * @param posx coordenada x
     * @param posy coordenada y
     * @param color color
     * @param ancho dato de ancho
     * @param alto  dato de alto
     */
    public void addCuadrado(int posx, int posy, String color, int ancho, int alto)
    {
        this.figuras.add(new Quadrate(posx, posy, alto, ancho, color));
        this.repaint();
    }
    
    /**
     * Metodo que agrega un Ovalo al Panel 
     * @param posx coordenada x
     * @param posy coordenada y
     * @param color color
     * @param ancho dato de ancho
     * @param alto  dato de alto
     */
    public void addOval(int posx, int posy, String color, int ancho, int alto)
    {
        this.figuras.add(new Oval(posx, posy, color, alto, ancho));
        this.repaint();
    }
    
    /**
     * Metodo que agrega una Cadena al Panel
     * @param posx coordenada x
     * @param posy coordenada y
     * @param color cadena de color
     * @param cadena  cadena a pintar
     */
    public void addString(int posx, int posy, String color, String cadena)
    {
        this.figuras.add(new Texto(posx, posy, color, cadena));
        this.repaint();
    }
    
    /**
     * Metodo que agrega una linea al Panel
     * @param posx1 posicion x inicial
     * @param posy1 posicion y inicial
     * @param posx2 posicion x final
     * @param posy2 posicion y final
     * @param color cadena de color
     * @param grosor entero que representa el grosor
     */
    public void addLinea(int posx1, int posy1, int posx2, int posy2, String color, int grosor)
    {
        this.figuras.add(new Line(posx1, posy1, posx2, posy2, grosor, color));
        this.repaint();
    }
}
