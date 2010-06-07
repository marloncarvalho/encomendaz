/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.encomendaz.gui.tablemodels;

import com.googlecode.encomendaz.entidades.Rastreio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.alfredlibrary.formatadores.Data;

/**
 *
 * @author 79325645572
 */
public class RastreioTableModel extends AbstractTableModel {

    private List<Rastreio> rastreamentos;

    public RastreioTableModel() {
        rastreamentos = new ArrayList<Rastreio>();

    }

    public void adicionar(Rastreio rastreio) {
        rastreamentos.add(rastreio);
        fireTableRowsInserted(rastreamentos.size() - 1, rastreamentos.size() - 1);
    }

    public Rastreio obterRastreio(int row) {
        return rastreamentos.get(row);
    }

    public void adicionar(List<Rastreio> lista) {
        rastreamentos.clear();
        int i = lista.size();
        rastreamentos.addAll(lista);
        fireTableRowsInserted(i, i + lista.size());
    }

    public RastreioTableModel(List list) {
        this();
        rastreamentos.addAll(list);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Situação";
            case 1:
                return "Código";
            case 2:
                return "Data Última Situação";
            case 3:
                return "Ação";
            default:
                return "";
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
        Rastreio r = rastreamentos.get(i);
        switch (coluna) {
            case 0:
                if (r.getUltimaSituacao() != null && r.getUltimaSituacao().getAcao() != null
                        && r.getUltimaSituacao().getAcao().equals("Entregue")) {
                    return "ok.png";
                } else {
                    return "nok.png";
                }
            case 1:
                return r.getCodigo();
            case 2:
                if ( r.getUltimaSituacao() != null )
                    return Data.formatar(r.getUltimaSituacao().getData(), "dd/MM/yyyy");
                return "";
            case 3:
                if ( r.getUltimaSituacao() != null )
                    return r.getUltimaSituacao().getAcao();
                return "";
            default:
                return null;
        }
    }
}
