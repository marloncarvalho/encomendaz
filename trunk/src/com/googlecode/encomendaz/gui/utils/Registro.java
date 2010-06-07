/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.encomendaz.gui.utils;

import java.awt.TrayIcon;

/**
 *
 * @author marlonsilvacarvalho
 */
public class Registro {
    private static TrayIcon trayIcon;

    public static void setSystemTrayIcon(TrayIcon i) {
        trayIcon = i;
    }

    public static TrayIcon getSystemTrayIcon() {
        return trayIcon;
    }

}