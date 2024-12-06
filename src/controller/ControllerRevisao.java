/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Automovel;
import model.DAOAutomovel;
import model.DAORevisao;
import model.Revisao;
import view.FCadRevisao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import model.Cliente;
import model.DAOCliente;
import view.FConsRevisao;
import view.RevisaoTableModel;

/**
 *
 * @author guilherme
 */
public class ControllerRevisao {

    private Revisao revisao;
    private FCadRevisao fCadRevisao;
    private FConsRevisao fConsRevisao;
    private RevisaoTableModel revisaoTB;
    private DAORevisao DAOR;
    private DAOAutomovel DAOA;
    private DAOCliente DAOC;

    public ControllerRevisao() throws ParseException {
        DAOA = new DAOAutomovel();
        revisao = null;
        fCadRevisao = new FCadRevisao(null, true);
        fConsRevisao = new FConsRevisao(null, true);
        revisaoTB = new RevisaoTableModel();
        DAOR = new DAORevisao();
        DAOC = new DAOCliente();
        inicializarComponentes();
    }

    public void inicializarComponentes() throws ParseException {
        fCadRevisao.edData.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
        fConsRevisao.tbRevisao.setModel(revisaoTB);

        fCadRevisao.btFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
        fCadRevisao.btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    inserir();
                } catch (ParseException ex) {
                    Logger.getLogger(ControllerRevisao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        fConsRevisao.btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAutomovel();
            }
        });

    }

    public void carregarAutomovel() {
        fCadRevisao.boxAutomovel.removeAllItems();
        for (Automovel a : DAOA.listar()) {
            fCadRevisao.boxAutomovel.addItem(a);

        }
    }

    public void inserir() throws ParseException {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

        Date data = formatador.parse(fCadRevisao.edData.getText());
        String servicos = fCadRevisao.edServicos.getText();
        Double km = Double.parseDouble(fCadRevisao.edKM.getText());
        Automovel a = (Automovel) fCadRevisao.boxAutomovel.getSelectedItem();

        Revisao r = new Revisao(data, km, servicos, a);
        Revisao re = DAOR.ultimaRevisao(a);

        if (DAOR.inserir(r)) {
            JOptionPane.showMessageDialog(null, "Gravado");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao gravar");
        }

        if (re != null) {
            if (r.getData().getTime() - re.getData().getTime() / 1000 / 60 / 60 / 24 > 365) {
                JOptionPane.showMessageDialog(null, "Atrasado por ano");
            }
            if (r.getKm()-re.getKm() > 10000) {
                JOptionPane.showMessageDialog(null, "Atrasado por km");

            }

        }
    }

    public void limpar() {
        revisao = null;
        fCadRevisao.edData.setText("");
        fCadRevisao.edKM.setText("");
        fCadRevisao.edServicos.setText("");
        fCadRevisao.boxAutomovel.setSelectedIndex(-1);
        fConsRevisao.edPlaca.setText("");
        revisaoTB.limpar();

    }

    public void fechar() {
        fCadRevisao.setVisible(false);
    }

    public void cadastroRevisao() {
        limpar();
        carregarAutomovel();
        //carregarCliente();
        fCadRevisao.setVisible(true);

    }

    public void consultarRevisao() {
        limpar();
        fConsRevisao.setVisible(true);
    }

    public void buscarAutomovel() {
        if (fConsRevisao.edPlaca != null) {
            revisaoTB.limpar();

            for (Revisao r : DAOR.listar(fConsRevisao.edPlaca.getText())) {
                revisaoTB.addrevisao(r);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Insira uma placa para pesquisar");
        }

    }
}

/*public void carregarCliente() {
        fCadRevisao.boxCliente.removeAllItems();
        for (Cliente c : DAOC.listar()) {m
            fCadRevisao.boxCliente.addItem(c);
            
        }
    }*/
//    public void carregarRevisao() {
//        fConsRevisao.tbRevisao.removeAll();;
//        for (Revisao r : DAOR.listar()) {
//            revisaoTB.addrevisao(r);
//
//        }
//    }
