/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Automovel;
import model.Cliente;
import model.DAOAutomovel;
import model.DAOCliente;
import view.AutomovelTableModel;
import view.FCadAutomovel;
import view.FConsAutomovel;

/**
 *
 * @author guilherme
 */
public class ControllerAutomovel {
    
    private Automovel automovel;
    private Cliente cliente;
    private FCadAutomovel fCadAutomovel;
    private FConsAutomovel fConsAutomovel;
    private DAOAutomovel DAOA;
    private DAOCliente DAOC;
    private AutomovelTableModel automovelTB;
    
    public ControllerAutomovel() {
        fCadAutomovel = new FCadAutomovel(null, true);
        automovel = null;
        DAOA = new DAOAutomovel();
        cliente = new Cliente();
        DAOC = new DAOCliente();
        fConsAutomovel = new FConsAutomovel(null, true);
        automovelTB = new AutomovelTableModel();
        inicializarComponenetes();
        
    }
    
    public void inicializarComponenetes() {
        fConsAutomovel.tbAutomoveis.setModel(automovelTB);
        fCadAutomovel.btFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fecharTela();
            }
        });
        fCadAutomovel.btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    inserir();
                } catch (ParseException ex) {
                    Logger.getLogger(ControllerAutomovel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        fConsAutomovel.btFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fecharTela();
            }
        });
        fConsAutomovel.btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
        fConsAutomovel.btEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        
    }
    
    public void cadastroAutmovel() {
        fCadAutomovel.boxCliente.removeAllItems();
        fCadAutomovel.checkMercosul.setSelected(false);
        
        carregarCliente();
        fCadAutomovel.setVisible(true);
    }
    
    public void consultarAutomoveis() {
        carregarAutomovel();
        fConsAutomovel.setVisible(true);
    }
    
    public void carregarCliente() {
        fCadAutomovel.boxCliente.removeAllItems();
        for (Cliente c : DAOC.listar()) {
            fCadAutomovel.boxCliente.addItem(c);
            
        }
    }
    
    public void fecharTela() {
        limpar();
        fCadAutomovel.setVisible(false);
        fConsAutomovel.setVisible(false);
    }
    
    public void carregarAutomovel() {
        automovelTB.limpar();
        for (Automovel a : DAOA.listar()) {
            automovelTB.addAutomovel(a);
        }
    }

    /* public void abrirTela(){
        fCosA
    }*/
    public void limpar() {
        automovel = null;
        fCadAutomovel.edAno.setText("");
        fCadAutomovel.edMarca.setText("");
        fCadAutomovel.checkMercosul.setSelected(false);
        fCadAutomovel.edPlaca.setText("");
        fCadAutomovel.edModelo.setText("");
        fCadAutomovel.boxCliente.setSelectedIndex(-1);
        
    }
    
    public void inserir() throws ParseException {
        if (automovel == null) {
            int ano = Integer.parseInt(fCadAutomovel.edAno.getText());
            String marca = fCadAutomovel.edMarca.getText();
            String modelo = fCadAutomovel.edModelo.getText();
            Cliente cliente = (Cliente) fCadAutomovel.boxCliente.getSelectedItem();
            String placa = fCadAutomovel.edPlaca.getText();
            
            Automovel automovel = new Automovel(placa, marca, modelo, 0, cliente);
            
            if (DAOA.inserir(automovel)) {
                JOptionPane.showMessageDialog(null, "Gravado");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao gravar");
            }
            
        } else {
            int ano = Integer.parseInt(fCadAutomovel.edAno.getText());
            automovel.setAno(ano);
            String marca = fCadAutomovel.edMarca.getText();
            automovel.setMarca(marca);
            String modelo = fCadAutomovel.edModelo.getText();
            automovel.setModelo(modelo);
            Cliente cliente = (Cliente) fCadAutomovel.boxCliente.getSelectedItem();
            automovel.setIdcliente(cliente);
            String placa = fCadAutomovel.edPlaca.getText();
            automovel.setPlaca(placa);
            
            Automovel automovel = new Automovel(placa, marca, modelo, 0, cliente);
            
            if (DAOA.inserir(automovel)) {
                JOptionPane.showMessageDialog(null, "Editado");
                limpar();
                fCadAutomovel.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao editar");
                
            }
            
        }
        
    }
    
    public void excluir() {
        
        int linhaSelecionada = fConsAutomovel.tbAutomoveis.getSelectedRow();
        if (linhaSelecionada >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Confirmar a exclusão?", "Confirmação", 0) == 0) {
                Automovel a = automovelTB.pegarAutomovel(linhaSelecionada);
                if (DAOA.excluir(a)) {
                    automovelTB.excluir(linhaSelecionada);
                    JOptionPane.showMessageDialog(null, "Excluido");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um registro");
            }
            
        }
        
    }
    
    public void editar() {
        int linhaSelecionada = fConsAutomovel.tbAutomoveis.getSelectedRow();
        if (linhaSelecionada >= 0) {
            carregarCliente();
            automovel = automovelTB.pegarAutomovel(linhaSelecionada);
            fCadAutomovel.edPlaca.setText(automovel.getPlaca());
            fCadAutomovel.edMarca.setText(automovel.getMarca());
            fCadAutomovel.edModelo.setText(automovel.getModelo());
            fCadAutomovel.edAno.setText(Integer.toString(automovel.getAno()));
            fCadAutomovel.boxCliente.setSelectedIndex(pegarIndiceCliente(automovel.getIdcliente()));
            
            fConsAutomovel.setVisible(false);
            fCadAutomovel.setVisible(true);
            fConsAutomovel.setVisible(true);
            
        }
    }
    
    public int pegarIndiceCliente(Cliente c) {
        int i = 0;
        for (Cliente cliente : DAOC.listar()) {
            if (c.getNome().equals(cliente.getNome())) {
                return i;
            }
            i++;
        }
        return 0;
    }
}
