/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

/**
 *
 * @author guilherme
 */
public class FTelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FTelaPrincipal
     */
    public FTelaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMuenu = new javax.swing.JMenu();
        miCadCliente = new javax.swing.JMenuItem();
        miCadAutomovel = new javax.swing.JMenuItem();
        miCadRevisao = new javax.swing.JMenuItem();
        menuu = new javax.swing.JMenu();
        miConsCliente = new javax.swing.JMenuItem();
        miConsAutomovel = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Automobile");

        jMuenu.setText("Cadastrar");

        miCadCliente.setText("Cliente");
        jMuenu.add(miCadCliente);

        miCadAutomovel.setText("Automovel");
        miCadAutomovel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCadAutomovelActionPerformed(evt);
            }
        });
        jMuenu.add(miCadAutomovel);

        miCadRevisao.setText("Revisao");
        jMuenu.add(miCadRevisao);

        jMenuBar1.add(jMuenu);

        menuu.setText("Consultar");

        miConsCliente.setText("Cliente");
        menuu.add(miConsCliente);

        miConsAutomovel.setText("Automovel");
        menuu.add(miConsAutomovel);

        jMenuBar1.add(menuu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void miCadAutomovelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCadAutomovelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miCadAutomovelActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMuenu;
    private javax.swing.JMenu menuu;
    public javax.swing.JMenuItem miCadAutomovel;
    public javax.swing.JMenuItem miCadCliente;
    public javax.swing.JMenuItem miCadRevisao;
    public javax.swing.JMenuItem miConsAutomovel;
    public javax.swing.JMenuItem miConsCliente;
    // End of variables declaration//GEN-END:variables
}
