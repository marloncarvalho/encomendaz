/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.encomendaz.tablemodels;

import com.googlecode.encomendaz.entidades.Rastreio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.alfredlibrary.formatadores.Data;
import org.alfredlibrary.utilitarios.correios.RegistroRastreamento;

/**
 *
 * @author 79325645572
 */
public class RastreioTableModel extends AbstractTableModel {

    private List<Rastreio> rastreamentos;

    public RastreioTableModel() {
        rastreamentos = new ArrayList<Rastreio>();
    }

    public RastreioTableModel(List list) {
        this();
        rastreamentos.addAll(list);
    }

    @Override
    public Class<?> getColumnClass(int coluna) {
        return String.class;
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Código";
            case 1:
                return "Data Ultima Situação";
            case 2:
                return "Ação";
            default:
                return ""; 
        }
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return this.rastreamentos.size();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int i, int coluna) {
        Rastreio r = rastreamentos.get(i);
        switch (coluna) {
            case 0:
                return r.getCodigo();
            case 1:
                return Data.formatar(r.getUltimaSituacao().getData(), "dd/MM/yyyy");
            case 2:
                return r.getUltimaSituacao().getAcao();
            default:
                return null;
        }
    }

}
