/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.encomendaz.quartz;

import com.googlecode.mastercrud.exceptions.BCException;
import com.googlecode.encomendaz.gui.utils.Registro;
import com.googlecode.encomendaz.gui.utils.TratadorErros;
import com.googlecode.encomendaz.businesscontrollers.RastreadorFactory;
import java.awt.TrayIcon.MessageType;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Job.
 *
 * @author Marlon Silva Carvalho
 */
public class AtualizadorRastreiosJob implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            String s = RastreadorFactory.create().atualizarRastreiosAtivos();
            if ( s != null && !"".equals(s) ) {
                Registro.getSystemTrayIcon().displayMessage("Mensagem", "Os rastreios a seguir foram atualizados: \n" + s, MessageType.INFO);
                Registro.getRastreadorVisao().obterRastreios();
            }
        } catch (BCException ex) {
            Logger.getLogger(AtualizadorRastreiosJob.class.getName()).log(Level.SEVERE, null, ex);
            new TratadorErros("Não foi possível obter contato com os Correios.",ex);
        }
    }

}
