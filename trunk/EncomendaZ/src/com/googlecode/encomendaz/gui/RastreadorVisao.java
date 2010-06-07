/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RastreadorVisao.java
 *
 * Created on 03/06/2010, 19:05:59
 */
package com.googlecode.encomendaz.gui;

import com.googlecode.encomendaz.gui.utils.TratadorErros;
import com.googlecode.encomendaz.gui.utils.Registro;
import com.googlecode.encomendaz.gui.cellrenderers.CellRenderer;
import com.googlecode.mastercrud.exceptions.BCException;
import com.googlecode.mastercrud.persistence.Criterias;
import com.googlecode.encomendaz.businesscontrollers.RastreadorFactory;
import com.googlecode.encomendaz.entidades.Rastreio;
import com.googlecode.encomendaz.gui.tablemodels.RastreioTableModel;
import com.googlecode.encomendaz.gui.utils.Configuracao;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import org.alfredlibrary.validadores.Numeros;

/**
 *
 * @author marlonsilvacarvalho
 */
public class RastreadorVisao extends javax.swing.JFrame {

    private JPopupMenu popupMenu = new JPopupMenu();
    int selectedRow = -1;

    /** Creates new form RastreadorVisao */
    public RastreadorVisao() {
        initComponents();
        
        this.txtTempo.setText(String.valueOf(Configuracao.get().getTempoAtualizacao()));
        this.setResizable(false);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/com/googlecode/encomendaz/resources/tray-ok.png")));

        JMenuItem menuItem = new JMenuItem("Remover");
        menuItem.addActionListener(new ActionAdapter(this));
        popupMenu.add(menuItem);

        menuItem = new JMenuItem("Detalhes");
        menuItem.addActionListener(new ActionAdapter(this));
        popupMenu.add(menuItem);

        MouseListener popupListener = new PopupListener();
        tblDados.addMouseListener(popupListener);

        try {
            tblDados.setModel(new RastreioTableModel(RastreadorFactory.create().obterRastreiosAtivos()));
        } catch (BCException ex) {
            Logger.getLogger(RastreadorVisao.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblDados.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tblDados.setColumnSelectionAllowed(true);
        tblDados.setRowSelectionAllowed(true);
        tblDados.setSelectionBackground(Color.BLUE);
        tblDados.setShowHorizontalLines(true);
        tblDados.setShowVerticalLines(false);
        tblDados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblDados.setCellSelectionEnabled(true);

        TableCellRenderer cr = new CellRenderer();
        tblDados.setDefaultRenderer(Object.class,cr);
        tblDados.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblDados.getColumnModel().getColumn(0).setMaxWidth(60);
        tblDados.getColumnModel().getColumn(0).setMinWidth(60);
        tblDados.getColumnModel().getColumn(0).setCellRenderer(cr);
        tblDados.getColumnModel().getColumn(1).setCellRenderer(cr);
        tblDados.getColumnModel().getColumn(2).setCellRenderer(cr);
        tblDados.getColumnModel().getColumn(3).setCellRenderer(cr);
        tblDados.setRowHeight(24);
        final TableCellRenderer defRenderer = tblDados.getTableHeader().getDefaultRenderer();
        tblDados.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                Component c = defRenderer.getTableCellRendererComponent(
                        table,
                        value,
                        isSelected,
                        hasFocus,
                        row,
                        column);

                if (c instanceof DefaultTableCellRenderer) {
                    DefaultTableCellRenderer dtcr = (DefaultTableCellRenderer) c;
                    dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                }
                return c;
            }
        });
    }

    public void exibirDetalhes() {
        RastreioTableModel rtm = (RastreioTableModel) tblDados.getModel();
        Rastreio r = rtm.obterRastreio(selectedRow);
        DetalhesRastreioVisao drv = new DetalhesRastreioVisao(r.getSituacoes());
        drv.setSize(550,305);
        drv.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        painelRastreamentos = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cmbFiltros = new javax.swing.JComboBox();
        btnNovo = new javax.swing.JButton();
        txtCodigo = new javax.swing.JTextField();
        scrollTblDados = new javax.swing.JScrollPane();
        tblDados = new javax.swing.JTable();
        painelConfig = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTempo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnAplicar = new javax.swing.JButton();
        painelDoacoes = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        painelSobre = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(com.googlecode.encomendaz.RastreadorApp.class).getContext().getResourceMap(RastreadorVisao.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowDeiconified(java.awt.event.WindowEvent evt) {
                formWindowDeiconified(evt);
            }
            public void windowIconified(java.awt.event.WindowEvent evt) {
                formWindowIconified(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbedPane.setName("tabbedPane"); // NOI18N

        painelRastreamentos.setName("painelRastreamentos"); // NOI18N
        painelRastreamentos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel1.border.titleFont"), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 100));
        jPanel1.setName("jPanel1"); // NOI18N

        cmbFiltros.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Filtrar por Situação", "Entregues", "Não Entregues", "Todos" }));
        cmbFiltros.setName("cmbFiltros"); // NOI18N
        cmbFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFiltrosActionPerformed(evt);
            }
        });

        btnNovo.setText(resourceMap.getString("btnNovo.text")); // NOI18N
        btnNovo.setActionCommand(resourceMap.getString("btnNovo.actionCommand")); // NOI18N
        btnNovo.setName("btnNovo"); // NOI18N
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        txtCodigo.setFont(resourceMap.getFont("txtCodigo.font")); // NOI18N
        txtCodigo.setText(resourceMap.getString("txtCodigo.text")); // NOI18N
        txtCodigo.setName("txtCodigo"); // NOI18N
        txtCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCodigoMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(cmbFiltros, 0, 292, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtCodigo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 296, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnNovo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 106, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cmbFiltros, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(txtCodigo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnNovo))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        painelRastreamentos.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 70));

        scrollTblDados.setName("scrollTblDados"); // NOI18N

        tblDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDados.setName("tblDados"); // NOI18N
        scrollTblDados.setViewportView(tblDados);

        painelRastreamentos.add(scrollTblDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 729, 270));

        tabbedPane.addTab(resourceMap.getString("painelRastreamentos.TabConstraints.tabTitle"), painelRastreamentos); // NOI18N

        painelConfig.setName("painelConfig"); // NOI18N
        painelConfig.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel3.border.title"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        txtTempo.setText(resourceMap.getString("txtTempo.text")); // NOI18N
        txtTempo.setName("txtTempo"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        btnAplicar.setText(resourceMap.getString("btnAplicar.text")); // NOI18N
        btnAplicar.setName("btnAplicar"); // NOI18N
        btnAplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplicarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel4)
                .add(6, 6, 6)
                .add(txtTempo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 64, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 249, Short.MAX_VALUE)
                .add(btnAplicar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(txtTempo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5)
                    .add(btnAplicar))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        painelConfig.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 700, 70));

        tabbedPane.addTab(resourceMap.getString("painelConfig.TabConstraints.tabTitle"), painelConfig); // NOI18N

        painelDoacoes.setBackground(resourceMap.getColor("painelDoacoes.background")); // NOI18N
        painelDoacoes.setName("painelDoacoes"); // NOI18N
        painelDoacoes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(resourceMap.getIcon("jLabel2.icon")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        painelDoacoes.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 330, -1));

        jLabel3.setIcon(resourceMap.getIcon("jLabel3.icon")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        painelDoacoes.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, -1, -1));

        tabbedPane.addTab(resourceMap.getString("painelDoacoes.TabConstraints.tabTitle"), painelDoacoes); // NOI18N

        painelSobre.setBackground(resourceMap.getColor("painelSobre.background")); // NOI18N
        painelSobre.setName("painelSobre"); // NOI18N
        painelSobre.setLayout(null);

        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        painelSobre.add(jLabel1);
        jLabel1.setBounds(100, 30, 540, 300);

        tabbedPane.addTab(resourceMap.getString("painelSobre.TabConstraints.tabTitle"), painelSobre); // NOI18N

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(resourceMap.getIcon("jLabel6.icon")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, -1));

        tabbedPane.addTab(resourceMap.getString("jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        getContentPane().add(tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 752, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCodigoMouseClicked
        if ("Informe um novo código".equals(txtCodigo.getText())) {
            txtCodigo.setText("");
        }
}//GEN-LAST:event_txtCodigoMouseClicked

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        String value = txtCodigo.getText();
        try {
            RastreadorFactory.create().atualizarRastreios(value);
            this.obterRastreios();
            txtCodigo.setText("Informe um novo código");
        } catch (BCException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            new TratadorErros(ex.getMessage(), ex);
        }
}//GEN-LAST:event_btnNovoActionPerformed

    private void cmbFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFiltrosActionPerformed
        this.obterRastreios();
}//GEN-LAST:event_cmbFiltrosActionPerformed

    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified
        this.setVisible(false);
        Registro.getSystemTrayIcon().getPopupMenu().getItem(0).setEnabled(true);
    }//GEN-LAST:event_formWindowIconified

    private void formWindowDeiconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeiconified
        this.setVisible(true);
        Registro.getSystemTrayIcon().getPopupMenu().getItem(0).setEnabled(false);
    }//GEN-LAST:event_formWindowDeiconified

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.setVisible(false);
        Registro.getSystemTrayIcon().getPopupMenu().getItem(0).setEnabled(true);
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
        Registro.getSystemTrayIcon().getPopupMenu().getItem(0).setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Registro.getSystemTrayIcon().getPopupMenu().getItem(0).setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void btnAplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplicarActionPerformed
        if (Numeros.isFloat(txtTempo.getText())) {
            Configuracao.get().setTempoAtualizacao(Long.valueOf(txtTempo.getText()));
        }
    }//GEN-LAST:event_btnAplicarActionPerformed

    public void remover(ActionEvent e) {
        RastreioTableModel rtm = (RastreioTableModel) tblDados.getModel();
        Rastreio r = rtm.obterRastreio(selectedRow);
        try {
            RastreadorFactory.create().delete(r);
            this.obterRastreios();
        } catch (BCException ex) {
            Logger.getLogger(RastreadorVisao.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblDados.repaint();
    }

    public void obterRastreios() {
        String s = cmbFiltros.getModel().getSelectedItem().toString();
        if ( "Entregues".equals(s) ) {
            try {
                RastreioTableModel rtm = (RastreioTableModel) tblDados.getModel();
                rtm.adicionar(RastreadorFactory.create().obterRastreiosInativos());
                return;
            } catch (BCException ex) {
                Logger.getLogger(RastreadorVisao.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        } else if ( "Não Entregues".equals(s) ) {
            try {
                RastreioTableModel rtm = (RastreioTableModel) tblDados.getModel();
                rtm.adicionar(RastreadorFactory.create().obterRastreiosAtivos());
                return;
            } catch (BCException ex) {
                Logger.getLogger(RastreadorVisao.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        } else {
            try {
                RastreioTableModel rtm = (RastreioTableModel) tblDados.getModel();
                rtm.adicionar(RastreadorFactory.create().list(new Criterias()).getResult());
            } catch (BCException ex) {
                Logger.getLogger(RastreadorVisao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tblDados.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAplicar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JComboBox cmbFiltros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel painelConfig;
    private javax.swing.JPanel painelDoacoes;
    private javax.swing.JPanel painelRastreamentos;
    private javax.swing.JPanel painelSobre;
    private javax.swing.JScrollPane scrollTblDados;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable tblDados;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtTempo;
    // End of variables declaration//GEN-END:variables

    class PopupListener implements MouseListener {

        @Override
        public void mousePressed(MouseEvent e) {
            showPopup(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            showPopup(e);
        }

        private void showPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                selectedRow = tblDados.rowAtPoint(e.getPoint());
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    class ActionAdapter implements ActionListener {

        RastreadorVisao adapter;

        ActionAdapter(RastreadorVisao adapter) {
            this.adapter = adapter;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem item = (JMenuItem) e.getSource();
            if ("Remover".equals(item.getText())) {
                int resp = JOptionPane.showConfirmDialog(RastreadorVisao.this, "Confirma a exclusão?");
                if ( resp == 0 ) {
                    adapter.remover(e);
                }
            }
            if ("Detalhes".equals(item.getText())) {
                adapter.exibirDetalhes();
            }
        }
    }
}



