/*
 * RastreadorApp.java
 */
package com.googlecode.encomendaz;

import com.googlecode.encomendaz.gui.RastreadorVisao;
import com.googlecode.encomendaz.gui.utils.Configuracao;
import com.googlecode.encomendaz.gui.utils.Registro;
import com.googlecode.encomendaz.quartz.AtualizadorRastreiosJob;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class RastreadorApp extends SingleFrameApplication {
    private SchedulerFactory sf = new StdSchedulerFactory();
    private Scheduler sched;
    private TrayIcon trayIcon;
    private RastreadorVisao rastreadorVisao;

    @Override
    protected void startup() {
        this.inicializarTray();
        this.inicializarQuartz();
        rastreadorVisao = new RastreadorVisao();
        rastreadorVisao.setSize(732, 392);
        rastreadorVisao.setVisible(true);
    }

    public void inicializarTray() {
        if (SystemTray.isSupported()) {
            final SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/com/googlecode/encomendaz/resources/tray-ok.png"));
            setTrayIcon(new TrayIcon(image));
            Registro.setSystemTrayIcon(getTrayIcon());
            PopupMenu popup = new PopupMenu();
            MenuItem restoreItem = new MenuItem("Restaurar");
            restoreItem.setActionCommand("Restore");
            restoreItem.setEnabled(false);
            MenuItem defaultItem = new MenuItem("Sair");
            defaultItem.setActionCommand("Exit");
            popup.add(restoreItem);
            popup.addSeparator();
            popup.add(defaultItem);
            popup.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    String acmd = ae.getActionCommand();
                    if (acmd.equals("Exit")) {
                        try {
                            sched.shutdown(true);
                            rastreadorVisao.setVisible(false);
                            rastreadorVisao.dispose();
                            RastreadorApp.getInstance().exit();
                        } catch (SchedulerException ex) {
                            Logger.getLogger(RastreadorApp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (acmd.equals("Restore")) {
                        rastreadorVisao.setVisible(true);
                        rastreadorVisao.setExtendedState(javax.swing.JFrame.NORMAL);
                    }
                }
            });
            getTrayIcon().setPopupMenu(popup);
            try {
                tray.add(getTrayIcon());
            } catch (AWTException ex) {
                Logger.getLogger(RastreadorApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void inicializarQuartz() {
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

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of RastreadorApp
     */
    public static RastreadorApp getApplication() {
        return Application.getInstance(RastreadorApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(RastreadorApp.class, args);
    }

    /**
     * @return the trayIcon
     */
    public TrayIcon getTrayIcon() {
        return trayIcon;
    }

    /**
     * @param trayIcon the trayIcon to set
     */
    public void setTrayIcon(TrayIcon trayIcon) {
        this.trayIcon = trayIcon;
    }
}
