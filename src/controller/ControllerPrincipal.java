/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import view.FTelaPrincipal;

/**
 *
 * @author guilherme
 */
public class ControllerPrincipal {

    private FTelaPrincipal fPrincipal;
    private ControllerCliente cliente;
    private ControllerAutomovel automovel;
    private ControllerRevisao revisao;

    public ControllerPrincipal() throws ParseException {
        fPrincipal = new FTelaPrincipal();
        cliente = new ControllerCliente();
        automovel = new ControllerAutomovel();
        revisao = new ControllerRevisao();
        inicializarComponentes();
    }

    public void executar() {
        fPrincipal.setVisible(true);
    }

    public void inicializarComponentes() {

        fPrincipal.miCadCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaCadastroCliente();
            }
        });
        fPrincipal.miCadAutomovel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaCadastroAutomovel();
            }
        });
        fPrincipal.miCadRevisao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaCadastroRevisao();
            }
        });
        /*fPrincipal.miConsAutomovel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaConsultaAutomovel();
            }
        });*/
        fPrincipal.miConsCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaConsultaCliente();
            }
        });
        fPrincipal.miConsAutomovel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaConsultaAutomovel();
            }
        });

    }

    public void abrirTelaCadastroCliente() {
        cliente.cadastroCliente();
    }

    public void abrirTelaCadastroAutomovel() {
        automovel.cadastroAutmovel();
    }

    public void abrirTelaCadastroRevisao() {
        revisao.cadastroRevisao();
    }

    public void abrirTelaConsultaAutomovel() {
        automovel.consultarAutomoveis();
    }

    public void abrirTelaConsultaCliente() {
        cliente.consultarCliente();
    }

}
