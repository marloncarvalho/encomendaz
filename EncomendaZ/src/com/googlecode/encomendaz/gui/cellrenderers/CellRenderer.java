/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.encomendaz.gui.cellrenderers;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author marlonsilvacarvalho
 */
public class CellRenderer extends DefaultTableCellRenderer {

    public CellRenderer() {
        this.setHorizontalAlignment(CENTER);
    }

    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel lbl = new JLabel();
        lbl.setHorizontalAlignment(CENTER);
        if ( column == 0 ) {
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/com/googlecode/encomendaz/resources/" + value.toString()));
            lbl.setIcon(icon);
        } else {
            lbl.setText((String) value);
        }
        return lbl;
    }
}
