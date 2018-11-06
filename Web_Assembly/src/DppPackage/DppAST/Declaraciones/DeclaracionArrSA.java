/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DppPackage.DppAST.Declaraciones;

import Abstraccion.Expresion;
import Abstraccion.NodoAST;
import ErrorManager.TError;
import Simbolos.Ambito;
import Simbolos.DppArr;
import Simbolos.SimboloTabla;
import java.util.ArrayList;

/**
 *
 * @author richard
 */
public class DeclaracionArrSA extends NodoAST {

    ArrayList<Expresion> dimensiones;
    String identificador;
    String tipo;

    public DeclaracionArrSA(int linea, int columna, String Archivo, String id, String tipo, ArrayList<Expresion> dimensiones) {
        super(linea, columna, Archivo);
        this.dimensiones = dimensiones;
        this.identificador = id;
        this.tipo = tipo;
    }

    @Override
    public Object generateByteCode(Ambito ambito) {
        try {
            String cad = "";
            if (!ambito.existeVariable(identificador)) {
                DppArr simbolo = new DppArr(identificador, Boolean.TRUE, tipo, ambito.getSize(), dimensiones.size(), 1, ambito.getIdAmbito());
                ambito.addDppSimbol(identificador, simbolo);
                cad += "// GUARDANDO EL PUNTERO\n";
                if (ambito.getIdAmbito().equals("Global")) {
                    cad += simbolo.getPosicionRelativa() + " // POSICION ABSOLUTA EN EL STACK\n";
                    cad += "get_global 0\n";
                    cad += "set_local $calc\n";
                } else {
                    cad += "get_local 0 // TOMANDO EL PUNTERO\n";
                    cad += simbolo.getPosicionRelativa() + " // POSICION RELATIVA\n";
                    cad += "ADD\n";
                    cad += "get_global 0\n";
                    cad += "set_local $calc\n";
                }
                cad += "// FIN DE GUARDAR EL PUNTERO DEL INICIO DEL ARREGLO\n";
                cad += "/*            ARREGLO: GUARDANDO DIMENSIONES EN HEAP             */\n";
                ArrayList<String> codes = getExpresionCads(ambito);
                int x = 1;
                for (String c : codes) {
                    cad += "// DIMENSION: " + x + "\n";
                    cad += "get_global 0\n";
                    cad += "// VALOR DIMENSION\n";
                    cad += c + "\n";
                    cad += "set_global $calc // DIMENSION GUARDADA EN HEAP\n";
                    cad += "// AUMENTANDO PUNTERO:\n";
                    cad += "get_global 0\n";
                    cad += "1\n";
                    cad += "ADD\n";
                    cad += "set_global 0\n";
                    cad += "// FIN DIMENSION: " + x + "\n";
                    x++;
                }
                cad += "/*         ARREGLO: FIN GUARDADO DIMENSIONES       */\n";
                cad += "/*         LLENANDO EL ESPACIO DEL HEAP PARA EL ARREGLO */\n";
                cad += "// PARAMETRO 1 NUMERO DE ELEMENTOS A LLENAR \n";
                cad += "get_local 0\n";
                cad += (ambito.getSize() - 1) + "\n";
                cad += "ADD\n";
                cad += "1\n";
                cad += "ADD\n";
                cad += "////////////////////////////////////////////////////////////\n";
                cad += "/*         CALCULANDO EL NUMERO DE ELEMENTOS A LLENAR  */\n";
                cad += "// PARAMETRO 1: PUNTERO DEL ARREGLO\n";
                cad += "get_local 0\n";
                cad += (ambito.getSize() - 1) + "\n";
                cad += "ADD\n";
                cad += 1 + "\n";
                cad += "ADD\n";
                cad += "get_local 0\n";
                cad += simbolo.getPosicionRelativa() + "\n";
                cad += "ADD\n";
                cad += "get_local $calc\n";
                cad += "//  PARAMETRO 2: NUMERO DE DIMENSIONES\n";
                cad += "get_local 0\n";
                cad += (ambito.getSize() - 1) + "\n";
                cad += "ADD\n";
                cad += 2 + "\n";
                cad += "ADD\n";
                cad += dimensiones.size() + " // DIMENSIONES\n";
                cad += "// LLAMANDO A LA FUNCION PARA CALCULAR\n";
                cad += "get_local 0\n";
                cad += (ambito.getSize() - 1) + "\n";
                cad += "ADD\n";
                cad += "set_local 0\n";
                cad += "Call $_CALCULA_CANTIDAD\n";
                cad += "get_local 0\n";
                cad += (ambito.getSize() - 1) + "\n";
                cad += "DIFF\n";
                cad += "set_local 0\n";
                cad += "/////////////////////////////////////////////////////////////\n";
                cad += "// OBTENIENDO EL RETURN\n";
                cad += "get_local $ret\n";
                cad += "/////////////////////////////////////////////////////////////\n";
                cad += "// LLAMANDO A LA FUNCION DE LLENADO\n";
                cad += "get_local 0\n";
                cad += (ambito.getSize() - 1) + "\n";
                cad += "ADD\n";
                cad += "set_local 0\n";
                cad += "Call $_LLENAR_ARR\n";
                cad += "get_local 0\n";
                cad += (ambito.getSize() - 1) + "\n";
                cad += "DIFF\n";
                cad += "set_local 0\n";
                cad += "//////////////////////////////////////////////////////////////\n";
                InfoEstatica.Estatico.AgregarTablaSimbolos(new SimboloTabla(identificador, Boolean.TRUE, tipo, ambito.getIdAmbito(), super.getLinea(),
                        super.getColumna(), 1, "Variable | Arreglo"));
                // AQUI COLOCO EL DEBUGGUER QUE SE DETENGA
                if (InfoEstatica.Estatico.mod == InfoEstatica.Estatico.MODALIDAD.DEBUGG_MODE) {
                    if (InfoEstatica.Estatico.esLinea) {
                        InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                        InfoEstatica.Estatico.suspended = true;
                        InfoEstatica.Estatico.OutPutCode.setText(cad);
                        InfoEstatica.Estatico.hilo.suspend();
                    } else {
                        String key = super.getLinea() + "_" + super.getArchivo();
                        if (InfoEstatica.Estatico.breakPoints.containsKey(key)) {
                            InfoEstatica.Estatico.MarcarLineaArchivo(super.getArchivo(), super.getLinea());
                            InfoEstatica.Estatico.suspended = true;
                            InfoEstatica.Estatico.OutPutCode.setText(cad);
                            InfoEstatica.Estatico.hilo.suspend();
                        }
                    }
                }
                return cad;
            } else {
                InfoEstatica.Estatico.agregarError(new TError(identificador,
                        "Ya existe una definicion de: " + identificador + " en este Ambito",
                        "Semantico",
                        super.getLinea(),
                        super.getColumna(),
                        Boolean.FALSE,
                        super.getArchivo()
                ));
            }
            return cad;
        } catch (Exception e) {
            InfoEstatica.Estatico.agregarError(new TError("No Aplica",
                    "Error al Traducir Arreglo sin Asignacion: " + e.getMessage(),
                    "Ejecucion",
                    super.getLinea(),
                    super.getColumna(),
                    Boolean.FALSE,
                    super.getArchivo()
            ));
        }
        return "";
    }

    private ArrayList<String> getExpresionCads(Ambito ambito) {
        ArrayList<String> expresiones = new ArrayList<>();
        try {
            for (Expresion e : this.dimensiones) {
                String code = (String) ((NodoAST) e).generateByteCode(ambito);
                String tipo = e.getTipo(ambito);
                if (tipo.equals("ENTERO")) {
                    expresiones.add(code);
                } else {
                    InfoEstatica.Estatico.agregarError(new TError(tipo,
                            "La definicion de Dimensiones de un Arreglo debe ser tipo Entero",
                            "Semantico",
                            super.getLinea(),
                            super.getColumna(),
                            Boolean.FALSE,
                            super.getArchivo()
                    ));
                }
            }
        } catch (Exception e) {
        }
        return expresiones;
    }

}
