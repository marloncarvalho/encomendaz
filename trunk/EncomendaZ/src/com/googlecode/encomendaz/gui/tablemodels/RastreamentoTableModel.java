/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.encomendaz.gui.tablemodels;

import com.googlecode.encomendaz.entidades.SituacaoRastreio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.alfredlibrary.formatadores.Data;

/**
 *
 * @author 79325645572
 */
public class RastreamentoTableModel extends AbstractTableModel {

    private List<SituacaoRastreio> rastreamentos;

    public RastreamentoTableModel() {
        rastreamentos = new ArrayList<SituacaoRastreio>();
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
        SituacaoRastreio rr = rastreamentos.get(i);
        switch (coluna) {
            case 0:
                return rr.getLocal();
            case 1:
                return Data.formatar(rr.getData(),"dd/MM/yyyy HH:mm");
            case 2:
                return rr.getAcao();
            case 3:
                return rr.getDetalhes();
            default:
                return null;
        }
    }

}
