/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstraccion;

import Estructuras.IP;
import Simbolos.EntornoDasm;

/**
 *
 * @author richard
 */
public interface SentenciaDasm {
    
    /**
     * Metodo que van a implementar para poder realizar operaciones con las instrucciones
     * que se ejecutan en DASM
     * @param entorno
     * @param instrucctionPointer
     * @return Dependiendo de la instruccion este va retornar algun valor
     */
    Object Ejecuta(EntornoDasm entorno, IP instrucctionPointer);
}
