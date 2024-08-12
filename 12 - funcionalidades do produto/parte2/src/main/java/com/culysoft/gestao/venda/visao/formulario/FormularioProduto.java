
package com.culysoft.gestao.venda.visao.formulario;

import com.culysoft.gestao.venda.controlador.FormularioProdutoControlador;
import com.culysoft.gestao.venda.visao.formulario.produto.Tela;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;


public class FormularioProduto extends javax.swing.JPanel {

    private FormularioProdutoControlador formularioProdutoControlador;
    private Dashboard dashboard;
    private Tela tela;
    private Long usuarioId;
    
    public FormularioProduto(Long usuarioId, Dashboard dashboard) {
        initComponents();
        
        setOpaque(false);
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        
        this.usuarioId = usuarioId;
        this.dashboard = dashboard;
        
        formularioProdutoControlador = new FormularioProdutoControlador(this);
        tela = new Tela();
        
        eventoDosBotoes();
        eventoDoMouse();
    }

    public Tela getTela() {
        return tela;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }
    
    public Long getUsuarioId() {
        return usuarioId;
    }
    
    private void eventoDosBotoes() {
        botaoActualizar.addActionListener(formularioProdutoControlador);
        botaoAdicionar.addActionListener(formularioProdutoControlador);
        botaoImprimir.addActionListener(formularioProdutoControlador);
        botaoRemover.addActionListener(formularioProdutoControlador);
    }
    
    private void eventoDoMouse() {
//        tabelaUsuarios.addMouseListener(formularioProdutoControlador);
    }
    
    public JTable getTabelaProduto() {
        return tabelaProduto;
    }
    
    public JButton getBotaoAdicionar() {
        return botaoAdicionar;
    }
    
    public JButton getBotaoActualizar() {
        return botaoActualizar;
    }
    
    public JButton getBotaoRemover() {
        return botaoRemover;
    }
   
    public JButton getBotaImprimir() {
        return botaoImprimir;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botaoAdicionar = new javax.swing.JButton();
        botaoActualizar = new javax.swing.JButton();
        botaoRemover = new javax.swing.JButton();
        botaoImprimir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProduto = new com.culysoft.gestao.venda.visao.componentes.Tabela();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("> Produto");

        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        botaoAdicionar.setBackground(new java.awt.Color(0, 0, 70));
        botaoAdicionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        botaoAdicionar.setText("Adicionar");
        botaoAdicionar.setName("adicionar"); // NOI18N
        jPanel1.add(botaoAdicionar);

        botaoActualizar.setBackground(new java.awt.Color(0, 0, 70));
        botaoActualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoActualizar.setForeground(new java.awt.Color(255, 255, 255));
        botaoActualizar.setText("Actualizar");
        botaoActualizar.setName("actualizar"); // NOI18N
        jPanel1.add(botaoActualizar);

        botaoRemover.setBackground(new java.awt.Color(0, 0, 70));
        botaoRemover.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoRemover.setForeground(new java.awt.Color(255, 255, 255));
        botaoRemover.setText("Remover");
        botaoRemover.setName("Remover"); // NOI18N
        jPanel1.add(botaoRemover);

        botaoImprimir.setBackground(new java.awt.Color(0, 0, 70));
        botaoImprimir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoImprimir.setForeground(new java.awt.Color(255, 255, 255));
        botaoImprimir.setText("Imprimir");
        botaoImprimir.setName("imprimir"); // NOI18N
        jPanel1.add(botaoImprimir);

        tabelaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaProduto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoActualizar;
    private javax.swing.JButton botaoAdicionar;
    private javax.swing.JButton botaoImprimir;
    private javax.swing.JButton botaoRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.culysoft.gestao.venda.visao.componentes.Tabela tabelaProduto;
    // End of variables declaration//GEN-END:variables
}
