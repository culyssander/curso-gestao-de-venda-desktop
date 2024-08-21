
package com.culysoft.gestao.venda.visao.formulario;

import com.culysoft.gestao.venda.controlador.FormularioEstoqueController;
import com.culysoft.gestao.venda.visao.componentes.Botao;
import com.culysoft.gestao.venda.visao.componentes.CampoDeTexto;
import com.culysoft.gestao.venda.visao.componentes.Tabela;
import com.culysoft.gestao.venda.visao.util.MensagemUtil;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import net.miginfocom.swing.MigLayout;


public class FormularioEstoque extends javax.swing.JPanel {

    private FormularioEstoqueController formularioEstoqueController;
    private Long usuarioId;
    private MensagemUtil mensagem;
    private MigLayout layout;
    private FormularioPrincipal formularioPrincipal;
    
    public FormularioEstoque(Long usuarioId, FormularioPrincipal formularioPrincipal) {
        initComponents();
        
        setOpaque(false);
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        dialogEstoque.setResizable(false);
        
        this.usuarioId = usuarioId;
        this.formularioPrincipal = formularioPrincipal;
        
        formularioEstoqueController = new FormularioEstoqueController(this);
        
        layout = new MigLayout("fill, insets");
        background.setLayout(layout);
        background.add(panelBoard1);
        mensagem = new MensagemUtil(background, layout);
        
        eventoDosBotoes();
        eventoDoMouse();
        eventoDoTeclado();
    }

    public MensagemUtil getMensagem() {
        return mensagem;
    }

    public FormularioPrincipal getFormularioPrincipal() {
        return formularioPrincipal;
    }
    

    public Long getUsuarioId() {
        return usuarioId;
    }
    
    private void eventoDosBotoes() {
        botaoActualizar.addActionListener(formularioEstoqueController);
        botaoAdicionar.addActionListener(formularioEstoqueController);
        botaoImprimir.addActionListener(formularioEstoqueController);
        botaoRemover.addActionListener(formularioEstoqueController);
        botaoSalva.addActionListener(formularioEstoqueController);
    }
    
    private void eventoDoTeclado() {
        textoNomeOuId.addKeyListener(formularioEstoqueController);
    }
    
    private void eventoDoMouse() {
        tabelaEstoque.addMouseListener(formularioEstoqueController);
    }

    public Tabela getTabelaEstoque() {
        return tabelaEstoque;
    }

    public JDialog getDialogEstoque() {
        return dialogEstoque;
    }

    public CampoDeTexto getTextoNomeOuId() {
        return textoNomeOuId;
    }

    public CampoDeTexto getTextoObservacao() {
        return textoObservacao;
    }

    public CampoDeTexto getTextoQuantidade() {
        return textoQuantidade;
    }

    public JRadioButton getRadioActivo() {
        return radioActivo;
    }

    public JRadioButton getRadioDesactiva() {
        return radioDesactiva;
    }

    public Botao getBotaoSalva() {
        return botaoSalva;
    }

    public JLabel getLabelTextoDoProduto() {
        return labelTextoDoProduto;
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogEstoque = new javax.swing.JDialog();
        background = new javax.swing.JLayeredPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        panelBoard1 = new com.culysoft.gestao.venda.visao.componentes.PanelBoard();
        jLabel2 = new javax.swing.JLabel();
        textoNomeOuId = new com.culysoft.gestao.venda.visao.componentes.CampoDeTexto();
        textoQuantidade = new com.culysoft.gestao.venda.visao.componentes.CampoDeTexto();
        textoObservacao = new com.culysoft.gestao.venda.visao.componentes.CampoDeTexto();
        labelTextoDoProduto = new javax.swing.JLabel();
        botaoSalva = new com.culysoft.gestao.venda.visao.componentes.Botao();
        radioActivo = new javax.swing.JRadioButton();
        radioDesactiva = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botaoAdicionar = new javax.swing.JButton();
        botaoActualizar = new javax.swing.JButton();
        botaoRemover = new javax.swing.JButton();
        botaoImprimir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaEstoque = new com.culysoft.gestao.venda.visao.componentes.Tabela();

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 365, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout dialogEstoqueLayout = new javax.swing.GroupLayout(dialogEstoque.getContentPane());
        dialogEstoque.getContentPane().setLayout(dialogEstoqueLayout);
        dialogEstoqueLayout.setHorizontalGroup(
            dialogEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );
        dialogEstoqueLayout.setVerticalGroup(
            dialogEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );

        panelBoard1.setCor1(new java.awt.Color(255, 255, 255));
        panelBoard1.setCor2(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(28, 181, 223));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Registro de Estoque");
        jLabel2.setToolTipText("");

        textoNomeOuId.setToolTipText("");
        textoNomeOuId.setDicas("Nome ou Id do Produto");
        textoNomeOuId.setDragEnabled(true);
        textoNomeOuId.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-de-venda\\src\\main\\java\\com\\culysoft\\gestao\\venda\\visao\\icon\\produto1.png")); // NOI18N

        textoQuantidade.setToolTipText("");
        textoQuantidade.setDicas("Quantidade");
        textoQuantidade.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-de-venda\\src\\main\\java\\com\\culysoft\\gestao\\venda\\visao\\icon\\quantidade.png")); // NOI18N

        textoObservacao.setDicas("Observacao");
        textoObservacao.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-de-venda\\src\\main\\java\\com\\culysoft\\gestao\\venda\\visao\\icon\\observacao.png")); // NOI18N

        botaoSalva.setBackground(new java.awt.Color(28, 181, 223));
        botaoSalva.setForeground(new java.awt.Color(255, 255, 255));
        botaoSalva.setText("Salvar");
        botaoSalva.setActionCommand("salvar");
        botaoSalva.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        buttonGroup1.add(radioActivo);
        radioActivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        radioActivo.setForeground(new java.awt.Color(28, 181, 223));
        radioActivo.setSelected(true);
        radioActivo.setText("Activa");

        buttonGroup1.add(radioDesactiva);
        radioDesactiva.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        radioDesactiva.setForeground(new java.awt.Color(28, 181, 223));
        radioDesactiva.setText("Desactiva");

        javax.swing.GroupLayout panelBoard1Layout = new javax.swing.GroupLayout(panelBoard1);
        panelBoard1.setLayout(panelBoard1Layout);
        panelBoard1Layout.setHorizontalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBoard1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelBoard1Layout.createSequentialGroup()
                                .addComponent(radioActivo)
                                .addGap(31, 31, 31)
                                .addComponent(radioDesactiva))
                            .addComponent(textoNomeOuId, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                            .addComponent(textoQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoObservacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelTextoDoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelBoard1Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(botaoSalva, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        panelBoard1Layout.setVerticalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(textoNomeOuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textoObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioActivo)
                    .addComponent(radioDesactiva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTextoDoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoSalva, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 51, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("> Estoque");

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

        tabelaEstoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaEstoque);

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
    private javax.swing.JLayeredPane background;
    private javax.swing.JButton botaoActualizar;
    private javax.swing.JButton botaoAdicionar;
    private javax.swing.JButton botaoImprimir;
    private javax.swing.JButton botaoRemover;
    private com.culysoft.gestao.venda.visao.componentes.Botao botaoSalva;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JDialog dialogEstoque;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTextoDoProduto;
    private com.culysoft.gestao.venda.visao.componentes.PanelBoard panelBoard1;
    private javax.swing.JRadioButton radioActivo;
    private javax.swing.JRadioButton radioDesactiva;
    private com.culysoft.gestao.venda.visao.componentes.Tabela tabelaEstoque;
    private com.culysoft.gestao.venda.visao.componentes.CampoDeTexto textoNomeOuId;
    private com.culysoft.gestao.venda.visao.componentes.CampoDeTexto textoObservacao;
    private com.culysoft.gestao.venda.visao.componentes.CampoDeTexto textoQuantidade;
    // End of variables declaration//GEN-END:variables
}
