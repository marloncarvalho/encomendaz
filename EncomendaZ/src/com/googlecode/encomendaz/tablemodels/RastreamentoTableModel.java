/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.encomendaz.tablemodels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.alfredlibrary.utilitarios.correios.RegistroRastreamento;

/**
 *
 * @author 79325645572
 */
public class RastreamentoTableModel extends AbstractTableModel {

    private List<RegistroRastreamento> rastreamentos;

    public RastreamentoTableModel() {
        rastreamentos = new ArrayList<RegistroRastreamento>();
    }

    public RastreamentoTableModel(List list) {
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
                return "Local";
            case 1:
                return "Data";
            case 2:
                return "Ação";
            case 3:
                return "Detalhe";
            default:
                return ""; // isso nunca deve ocorrer, pois temos só 3 colunas
        }
    }

    @Override
    public int getColumnCount() {
        return 4;
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
        RegistroRastreamento rr = rastreamentos.get(i);
        switch (coluna) {
            case 0:
                return rr.getLocal();
            case 1:
                return rr.getDataHora().toString();
            case 2:
                return rr.getAcao();
            case 3:
                return rr.getDetalhe();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        RegistroRastreamento rr = rastreamentos.get(linha);
        switch (coluna) {
            case 0:
                rr.setLocal(valor.toString());
                break;
            case 1:
                rr.setDataHora(new Date());
                break;
            case 2:
                rr.setAcao(valor.toString());
                break;
            case 3:
                rr.setDetalhe(valor.toString());
        }
        fireTableDataChanged();
    }
}
