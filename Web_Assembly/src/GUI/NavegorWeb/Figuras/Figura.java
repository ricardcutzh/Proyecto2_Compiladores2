/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.NavegorWeb.Figuras;

import java.awt.Graphics;

/**
 * Interfaz que van a utilizar las demas clases que son figuras 
 * para poder implementar el metodo dibujar que tendra cada figura
 * @author richard
 */
public interface Figura {
    /**
     * Metodo que se implementara para dibujar la figura correspondiente
     * @param g Objeto de tipo Graphics al cual se dibujara
     */
    void Dibujar(Graphics g);
}
