/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Automovel;

/**
 *
 * @author guilherme
 */
public class AutomovelTableModel extends AbstractTableModel {

    private List<Automovel> automovel = new ArrayList<Automovel>();

    @Override
    public int getRowCount() {
        return automovel.size();
    }

    @Override
    public int getColumnCount() {
        return 5;

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 -> {
                return "Placa";
            }
            case 1 -> {
                return "Marca";
            }
            case 2->{
                return "Modelo";
            }
            case 3 ->{
                return "Ano";
            }
            case 4 ->{
                return "Cliente";
            }
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return automovel.get(rowIndex).getPlaca();
            }
            case 1 -> {
                return automovel.get(rowIndex).getMarca();
            }
            case 2 ->{
                return automovel.get(rowIndex).getModelo();
            }
            case 3->{
                return automovel.get(rowIndex).getAno();
            }
            case 4->{
                return automovel.get(rowIndex).getIdcliente();
            }
        }
        return 0;
    }

    public void addAutomovel(Automovel a) {
        automovel.add(a);
        fireTableRowsInserted(automovel.size() - 1, automovel.size() - 1);
    }

    public void excluir(int indice) {
        automovel.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public void limpar() {
        automovel.clear();
    }

    public Automovel pegarAutomovel(int indice) {
        return automovel.get(indice);
    }

}
