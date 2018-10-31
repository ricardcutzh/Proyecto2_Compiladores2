/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simbolos;

import Abstraccion.NodoAST;
import ObjsComun.NodoMiembro;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author richard
 */
public class Struct extends Simbolo {

    HashMap<String, NodoMiembro> miembros;

    public Struct(String idSimbolo, Boolean esVector, String tipo, HashMap<String, NodoMiembro> miembros) {
        super(idSimbolo, esVector, tipo);
        this.miembros = miembros;
    }

    public NodoMiembro getByIndex(int index) {
        for (Map.Entry<String, NodoMiembro> n : this.miembros.entrySet()) {
            if (n.getValue().getIndiceMiembro() == index) {
                return (NodoMiembro) n.getValue();
            }
        }
        return null;
    }

    public String declaracionStruct(Ambito ambito) {
        try {
            if (ambito.getIdAmbito().equals("Global")) {
                String cad = "// DECLARAION DE ESTRUCTURA: " + idSimbolo + "\n";
                cad += (ambito.getSize()) + "// POSICION ABSOLUTA EN EL STACK\n";
                cad += "ADD // POSICION DONDE GUARDARE EL PUNTERO AL INICIO DE LA ESTRUCTURA\n";
                cad += "get_global 0 // PUNTERO AL INICIO DE LA ESTRUCTURA\n";
                cad += "set_local $calc // GUARDO EL PUNTERO DE LA ESTRUCTURA\n";
                for (int x = 1; x <= miembros.size(); x++) {
                    cad += ((NodoAST) getByIndex(x)).generateByteCode(ambito);
                }
                cad += "// ACTUALIZANDO PUNTERO DEL HEAP\n";
                cad += "get_global 0\n";
                cad += miembros.size() + "\n";
                cad += "ADD // sumo\n";
                cad += "set_global 0\n";
                return cad;

            } else {
                String cad = "// DECLARAION DE ESTRUCTURA: " + idSimbolo + "\n";
                cad += "get_local 0 // PUNTERO DEL STACK\n";
                cad += (ambito.getSize()) + "\n";
                cad += "ADD // POSICION DONDE GUARDARE EL PUNTERO AL INICIO DE LA ESTRUCTURA\n";
                cad += "get_global 0 // PUNTERO AL INICIO DE LA ESTRUCTURA\n";
                cad += "set_local $calc // GUARDO EL PUNTERO DE LA ESTRUCTURA\n";
                for (int x = 1; x <= miembros.size(); x++) {
                    cad += ((NodoAST) getByIndex(x)).generateByteCode(ambito);
                }
                cad += "// ACTUALIZANDO PUNTERO DEL HEAP\n";
                cad += "get_global 0\n";
                cad += miembros.size() + "\n";
                cad += "ADD // sumo\n";
                cad += "set_global 0\n";
                return cad;
            }
        } catch (Exception e) {
        }
        return "";
    }

}
