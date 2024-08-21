/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.culysoft.gestao.venda.visao.formulario;

import com.culysoft.gestao.venda.visao.componentes.Tabela;
import com.culysoft.gestao.venda.visao.modelo.CartaoModelo;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author qculissander
 */
public class FormularioPrincipal extends javax.swing.JPanel {

    /**
     * Creates new form FormularioPrincipal
     */
    public FormularioPrincipal() {
        initComponents();
        inicializarCartao();
        jScrollPane1.getViewport().setBackground(Color.WHITE);
    }
    
    private void inicializarCartao() {
        CartaoModelo cartaoModelo = new CartaoModelo(new ImageIcon(getCaminho() + "produto.png"), "Produto", "Total 7");
        CartaoModelo cartaoModelo1 = new CartaoModelo(new ImageIcon(getCaminho() + "venda.png"), "Venda", "Total 7");
        CartaoModelo cartaoModelo2 = new CartaoModelo(new ImageIcon(getCaminho() + "stock.png"), "Estoque", "Total 7");
        
        cartao4.setData(cartaoModelo);
        cartao5.setData(cartaoModelo1);
        cartao6.setData(cartaoModelo2);
    }

    public Tabela getTabelaDoFormularioPrincipal() {
        return tabelaDoFormularioPrincipal;
    }
    
    private String getCaminho() {
        return System.getProperty("user.dir") + "\\src\\main\\java\\com\\culysoft\\gestao\\venda\\visao\\icon\\";
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cartao4 = new com.culysoft.gestao.venda.visao.componentes.Cartao();
        cartao5 = new com.culysoft.gestao.venda.visao.componentes.Cartao();
        cartao6 = new com.culysoft.gestao.venda.visao.componentes.Cartao();
        panelBoard1 = new com.culysoft.gestao.venda.visao.componentes.PanelBoard();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaDoFormularioPrincipal = new com.culysoft.gestao.venda.visao.componentes.Tabela();

        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        cartao4.setCor1(new java.awt.Color(0, 180, 219));
        cartao4.setCor2(new java.awt.Color(0, 0, 0));
        jPanel1.add(cartao4);

        cartao5.setCor1(new java.awt.Color(48, 43, 99));
        cartao5.setCor2(new java.awt.Color(0, 0, 0));
        jPanel1.add(cartao5);

        cartao6.setCor1(new java.awt.Color(176, 106, 179));
        cartao6.setCor2(new java.awt.Color(0, 0, 0));
        jPanel1.add(cartao6);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(130, 130, 130));
        jLabel1.setText("Historico do estoque");

        tabelaDoFormularioPrincipal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Quantidade", "Estado", "Motivo", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaDoFormularioPrincipal);

        javax.swing.GroupLayout panelBoard1Layout = new javax.swing.GroupLayout(panelBoard1);
        panelBoard1.setLayout(panelBoard1Layout);
        panelBoard1Layout.setHorizontalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBoard1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBoard1Layout.setVerticalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelBoard1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBoard1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.culysoft.gestao.venda.visao.componentes.Cartao cartao4;
    private com.culysoft.gestao.venda.visao.componentes.Cartao cartao5;
    private com.culysoft.gestao.venda.visao.componentes.Cartao cartao6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.culysoft.gestao.venda.visao.componentes.PanelBoard panelBoard1;
    private com.culysoft.gestao.venda.visao.componentes.Tabela tabelaDoFormularioPrincipal;
    // End of variables declaration//GEN-END:variables
}
