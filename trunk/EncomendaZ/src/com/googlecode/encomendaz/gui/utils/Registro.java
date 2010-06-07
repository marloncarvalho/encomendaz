/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.encomendaz.gui.utils;

import com.googlecode.encomendaz.gui.RastreadorVisao;
import java.awt.TrayIcon;

/**
 *
 * @author marlonsilvacarvalho
 */
public class Registro {
    private static TrayIcon trayIcon;
    private static RastreadorVisao rastreadorVisao;

    public static void setSystemTrayIcon(TrayIcon i) {
        trayIcon = i;
    }

    public static TrayIcon getSystemTrayIcon() {
        return trayIcon;
    }

    public static RastreadorVisao getRastreadorVisao() {
        return rastreadorVisao;
    }

    public static void setRastreadorVisao(RastreadorVisao rv) {
        rastreadorVisao = rv;
    }

}