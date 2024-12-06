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
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import model.Cliente;
import model.DAOCliente;

/**
 *
 * @author guilherme
 */
public class ControllerRevisao {

    private Revisao revisao;
    private FCadRevisao fCadRevisao;
    private DAORevisao DAOR;
    private DAOAutomovel DAOA;
    private DAOCliente DAOC;

    public ControllerRevisao() throws ParseException {
        DAOA = new DAOAutomovel();
        revisao = null;
        fCadRevisao = new FCadRevisao(null, true);
        DAOR = new DAORevisao();
        DAOC = new DAOCliente();
        inicializarComponentes();
    }

    public void inicializarComponentes() throws ParseException {
        fCadRevisao.edData.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));

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

    }

    public void carregarAutomovel() {
        fCadRevisao.boxAutomovel.removeAllItems();
        for (Automovel a : DAOA.listar()) {
            fCadRevisao.boxAutomovel.addItem(a);

        }
    }

    /*public void carregarCliente() {
        fCadRevisao.boxCliente.removeAllItems();
        for (Cliente c : DAOC.listar()) {m
            fCadRevisao.boxCliente.addItem(c);
            
        }
    }*/
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
            if (re.getData().getTime() - r.getData().getTime() / 1000 / 60 / 60 / 24 > 365) {
                JOptionPane.showMessageDialog(null, "Atrasado por ano");
            }
            if (re.getKm() > 10000) {
                JOptionPane.showMessageDialog(null, "Atrasado por km");

            }

        }
    }

    public void fechar() {
        fCadRevisao.setVisible(false);
    }

    public void cadastroRevisao() {
        carregarAutomovel();
        //carregarCliente();
        fCadRevisao.setVisible(true);

    }

}
