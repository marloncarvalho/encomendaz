/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.encomendaz.gui.utils;

import javax.swing.JOptionPane;

/**
 *
 * @author marlonsilvacarvalho
 */
public class TratadorErros {
    private String mensagem;
    private Exception e;

    public TratadorErros(String mensagem, Exception e) {
        this.mensagem = mensagem;
        this.e = e;
        this.exibir();
    }

    private void exibir() {
        JOptionPane.showMessageDialog(null, mensagem);
    }

}