/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Declaraciones;

import java.util.ArrayList;
import Abstraccion.*;
import DppPackage.DppAST.Importaciones.Importacion;
import ErrorManager.TError;
import ObjsComun.Clave;
import Simbolos.Ambito;
import Simbolos.MetodoFuncion;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 * Clase que iniciara la traduccion de el D++
 *
 * @author richard
 */
public class DppASTTree extends NodoAST {

    public ArrayList<NodoAST> nodos;
    Boolean huboMain;
    String PathEscritura;

    /**
     * Constructor del nodo principal que iniciara la traduuccion
     *
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
     *
     * @param nodo nodo que se va anadir
     */
    public void addNodo(NodoAST nodo) {
        this.nodos.add(nodo);
    }

    /**
     * Metodo auxiliar para colocar el path de escritura del archivo de salida
     * DASM
     *
     * @param path
     */
    public void setPathEscritura(String path) {
        this.PathEscritura = path;
    }

    /**
     * Metodo sobreescrito para poder generar la traduccion
     *
     * @param ambito Ambito donde se inicia la traduccion
     * @return Objeto de la traduccion
     */
    @Override
    public Object generateByteCode(Ambito ambito) {// EL AMBITO QUE VIENE ES EL GLOBAL
        try {
            // LO PRIMERO ES PODER RECOPILAR TODA LA INFORMACION: FUNCIONES, VARIABLES GLOBALES, METODOS PARA LUEGO HACER EL CAMBIO
            DeclaracionMain Main = null;
            for (NodoAST n : nodos) // AQUI YA NO DEBERIAN DE HABER NODOS IMPORTACIONES YA DEVERIA IR LIMPIO 
            {
                if (n instanceof DeclaracionFuncion || n instanceof DeclaracionMetodo) {
                    n.generateByteCode(ambito);// SOLO LAS VA ALMECENAR LAS FUNCIONES
                } else if (n instanceof DeclaracionMain) {
                    if (!huboMain) {
                        Main = (DeclaracionMain) n;
                        huboMain = true;
                    } else {
                        InfoEstatica.Estatico.agregarError(new TError("Metodo Principal redefinido ", "Multiple definicion del metodo principal", "Semantico", n.getLinea(), n.getColumna(), false, n.getArchivo()));
                    }
                }
            }
            //////////////////////////////////////////////////////////////////
            // ESCRIBIENDO LAS VARIABLES GLOBALES
            String vars = variablesGlobales(ambito);
            ///////////////////////////////////////////////////////////////////
            String salida = super.getArchivo().replace(".dpp", ".dasm");
            File f = new File(PathEscritura + "/" + salida);
            FileWriter fw = new FileWriter(f);
            fw.write(vars);
            //////////////////////////////////////////////////////////////////
            // ESCRIBE EL METODO MAIN
            EscribeElMain(ambito, fw, Main);
            //////////////////////////////////////////////////////////////////
            HashMap<Clave, MetodoFuncion> funcs = ambito.getTablaFunciones();
            //////////////////////////////////////////////////////////////////
            // ESCRIBE LOS DEMAS METODOS
            EscribeMetodos(ambito, fw, funcs);
            //////////////////////////////////////////////////////////////////
            fw.close();
            if (InfoEstatica.Estatico.errores.size() > 0) {
                JOptionPane.showMessageDialog(null, "Existen errores |  SALIDA NO PRODUCIDA!", "Errores Semanticos", JOptionPane.ERROR_MESSAGE);
                f.delete();
            } else {
                JOptionPane.showMessageDialog(null, "Codigo producido en: " + salida, "Compilacion terminada", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica", "Error al iniciar la traudccion: " + e.getMessage(), "Ejecucion", super.getLinea(), super.getColumna(), false, super.getArchivo()));
        }
        return "";
    }

    private String variablesGlobales(Ambito ambito) {
        String vars = "/******************************************************************************************/\n";
        vars += "//         VARIABLES GLOBALES ";
        vars += "\n/******************************************************************************************/\n";
        ///////////////////////////////////////////////////////////////////
        // AQUI FALTA LA DECLARACION DE VARIABLES PRIMERO EN EL AMBITO 
        // GLOBAL
        for (NodoAST n : nodos) {
            if (n instanceof DeclaracionVar) {
                vars += n.generateByteCode(ambito);
            }
        }
        if(huboMain)
        {
            vars += "\nCall $principal\n";
        }
        vars += "\n/******************************************************************************************/\n";

        return vars;
    }

    private void EscribeElMain(Ambito ambito, FileWriter fw, DeclaracionMain Main) {
        try {
            String division = "\n/******************************************************************************************/\n";
            division += "//      SEGMENTO DE CODIGO  \n";
            division += "/******************************************************************************************/\n";
            fw.write(division);
            if (huboMain) {
                String m = (String) Main.generateByteCode(ambito);
                fw.write(m);
            }
        } catch (Exception e) {
        }

    }
    
    private void EscribeMetodos(Ambito ambito, FileWriter fw, HashMap<Clave, MetodoFuncion> funcs)
    {
        try {
            String cad = "";
            for(Map.Entry<Clave, MetodoFuncion> entry: funcs.entrySet())
            {
                MetodoFuncion aux = entry.getValue();
                Clave c = entry.getKey();
                InfoEstatica.Estatico.tipoFuncion = aux.getTipo(); // SETEO EL TIPO DE LA FUNCION QUE SE ESTA TRADUCIENDO
                cad = "Function $"+entry.getKey().toString()+"\n";
                InfoEstatica.Estatico.tabula();
                cad += InfoEstatica.Estatico.aplicaTabulaciones((String)aux.generateByteCode(new Ambito("Local | "+c.getIdFuncion(), ambito, ambito.getArchivo())));
                cad += "\n\t$e_retornar\n";
                InfoEstatica.Estatico.destabula();
                cad += "\nEnd\n";
                InfoEstatica.Estatico.tipoFuncion = "";
                fw.write(cad);
            }
        } catch (Exception e) {
        }
    }

}
