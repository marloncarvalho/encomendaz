/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.encomendaz.quartz;

import com.googlecode.encomendaz.RastreadorApp;
import com.googlecode.encomendaz.gui.utils.Configuracao;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author 79325645572
 */
public class InicializadorQuartz {
    private static SchedulerFactory sf = new StdSchedulerFactory();
    private static Scheduler sched;

    public static void inicializar() {
        try {
            sched = sf.getScheduler();
            JobDetail job = new JobDetail("job1", "group1", AtualizadorRastreiosJob.class);
            SimpleTrigger trigger = new SimpleTrigger("myTrigger",
                    null,
                    new Date(),
                    null,
                    SimpleTrigger.REPEAT_INDEFINITELY,
                    (Configuracao.get().getTempoAtualizacao() * 60L) * 1000L);
            sched.scheduleJob(job, trigger);
            sched.start();
        } catch (SchedulerException ex) {
            Logger.getLogger(RastreadorApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void reiniciar() {
        parar();
        try {
            sched = sf.getScheduler();
            JobDetail job = new JobDetail("job1", "group1", AtualizadorRastreiosJob.class);
            SimpleTrigger trigger = new SimpleTrigger("myTrigger",
                    null,
                    new Date(),
                    null,
                    SimpleTrigger.REPEAT_INDEFINITELY,
                    (Configuracao.get().getTempoAtualizacao() * 60L) * 1000L);
            sched.scheduleJob(job, trigger);
            sched.start();
        } catch (SchedulerException ex) {
            Logger.getLogger(InicializadorQuartz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void parar() {
        try {
            sched.shutdown(true);
        } catch (SchedulerException ex) {
            Logger.getLogger(InicializadorQuartz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}