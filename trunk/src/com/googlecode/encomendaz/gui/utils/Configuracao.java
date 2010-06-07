/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.encomendaz.gui.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.alfredlibrary.validadores.Numeros;

/**
 *
 * @author marlonsilvacarvalho
 */
final public class Configuracao {

    private String propertiesFile = "rastreador.properties";
    private Properties props = new Properties();
    private File file;
    private static Configuracao instance = new Configuracao();

    private Configuracao() {
        this.loadFile();
    }

    public static Configuracao get() {
        return instance;
    }

    private void loadFile() {
        file = new File(propertiesFile);

        if (!file.exists()) {
            FileInputStream in;
            try {
                file.createNewFile();
                in = new FileInputStream(file);
                props.load(in);
            } catch (IOException ex) {
                Logger.getLogger(Configuracao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public long getTempoAtualizacao() {
        String tempo = props.getProperty("tempoatualizacao");
        if ( tempo == null || "".equals(tempo) ) {
            setTempoAtualizacao(60L);
            return 60L;
        } else {
            if ( Numeros.isLong(tempo) ) {
                return Long.valueOf(tempo);
            } else {
                setTempoAtualizacao(60L);
                return 60;
            }
        }
    }

    public void setTempoAtualizacao(long segundos) {
        props.setProperty("tempoatualizacao", String.valueOf(segundos));
        try {
            FileOutputStream out = new FileOutputStream(file);
            props.store(out,"");
        } catch (IOException ex) {
            Logger.getLogger(Configuracao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
