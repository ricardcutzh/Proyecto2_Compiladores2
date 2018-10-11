/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Declaraciones;
import java.util.ArrayList;
import Abstraccion.*;
import ErrorManager.TError;
import Simbolos.Ambito;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;
/**
 * Clase que iniciara la traduccion de el D++ 
 * @author richard
 */
public class DppASTTree extends NodoAST{
    ArrayList<NodoAST> nodos;
    Boolean huboMain;
    String PathEscritura;
    /**
     * Constructor del nodo principal que iniciara la traduuccion
     * @param linea
     * @param columna
     * @param Archivo 
     */
    public DppASTTree(int linea, int columna, String Archivo) {
        super(linea, columna, Archivo);
        nodos = new ArrayList<>();
        this.huboMain = false;
    }
    
    /**
     * Metodo para anadir nodo a la lista para ejecutarlos
     * @param nodo nodo que se va anadir
     */
    public void addNodo(NodoAST nodo)
    {
        this.nodos.add(nodo);
    }
    
    /**
     * Metodo auxiliar para colocar el path de escritura del archivo de salida DASM
     * @param path 
     */
    public void setPathEscritura(String path)
    {
        this.PathEscritura = path;
    }
    
    /**
     * Metodo sobreescrito para poder generar la traduccion
     * @param ambito Ambito donde se inicia la traduccion
     * @return Objeto de la traduccion
     */
    @Override
    public Object generateByteCode(Ambito ambito) {
        try 
        {
            String salida = super.getArchivo().replace(".dpp", ".dasm");
            File f = new File(this.PathEscritura+"/"+salida);
            FileWriter fw = new FileWriter(f);
            for(NodoAST n : nodos)
            {
                String s = (String)n.generateByteCode(ambito);
                fw.write(s);
            }
            fw.close();
            JOptionPane.showMessageDialog(null,"Archivo: "+salida+" Generado");
        } catch (Exception e)
        {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al iniciar la traudccion: "+e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, ambito.getArchivo()));
        }
        return "";
    }
    
    
}
