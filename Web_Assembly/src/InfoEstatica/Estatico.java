/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoEstatica;
import java.util.ArrayList;
import ErrorManager.TError;
import javax.swing.JTextArea;

/**
 * Clase que sera de apoyo para La informacion Estatica
 * @author richard
 */
public class Estatico {
    
    /**
     * elemento estatico que solo almacena los errores
     */
    public static ArrayList<TError> errores;
    
    public static JTextArea console;
    
    public static void setUp(JTextArea consola)
    {
        errores = new ArrayList<>();
        console = consola;
        console.setText(">> Web Assembly | Compiladores 2 | 201503476");
    }
}
