/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.encomendaz.businesscontrollers;

import com.googlecode.encomendaz.businesscontrollers.implementors.RastreadorImpl;

/**
 *
 * @author marlonsilvacarvalho
 */
final public class RastreadorFactory {
    private static Rastreador rastreador = new RastreadorImpl();

    public static Rastreador create() {
        return rastreador;
    }
   
}
