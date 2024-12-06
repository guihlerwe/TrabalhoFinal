/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Revisao;

/**
 *
 * @author guilherme
 */
public class RevisaoTableModel extends AbstractTableModel {

    private List<Revisao> revisao = new ArrayList<Revisao>();

    @Override
    public int getRowCount() {
        return revisao.size();
    }

    @Override
    public int getColumnCount() {
        return 4;

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 -> {
                return "Data";
            }
            case 1 -> {
                return "KM";
            }
            case 2->{
                return "Servicos Realizaods";
            }
            case 3 ->{
                return "Automovel";
            }
           
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return revisao.get(rowIndex).getData();
            }
            case 1 -> {
                return revisao.get(rowIndex).getKm();
            }
            case 2 ->{
                return revisao.get(rowIndex).getServicosrealizados();
            }
            case 3->{
                return revisao.get(rowIndex).getIdautomovel();
            }
        }
        return 0;
    }

    public void addrevisao(Revisao r) {
        revisao.add(r);
        fireTableRowsInserted(revisao.size() - 1, revisao.size() - 1);
    }

    public void excluir(int indice) {
        revisao.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public void limpar() {
        revisao.clear();
    }

    public Revisao pegarRevisao(int indice) {
        return revisao.get(indice);
    }

}
