
package com.culysoft.gestao.venda.visao.formulario;

import com.culysoft.gestao.venda.controlador.FormularioUsuarioController;
import com.culysoft.gestao.venda.controlador.FormularioVendaController;
import com.culysoft.gestao.venda.visao.componentes.Botao;
import com.culysoft.gestao.venda.visao.componentes.CampoDeTexto;
import com.culysoft.gestao.venda.visao.componentes.ComboBox;
import com.culysoft.gestao.venda.visao.componentes.Tabela;
import com.culysoft.gestao.venda.visao.util.MensagemUtil;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;


public class FormularioVenda extends javax.swing.JPanel {

    private FormularioVendaController formularioVendaController;
    private Long usuarioId;
    private MigLayout layout;
    private MensagemUtil mensagemUtil;
    private boolean mostrar = true;
    
    public FormularioVenda(Long usuarioId) {
        initComponents();
        
        setOpaque(false);
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        dialogVenda.setResizable(false);
        
        this.usuarioId = usuarioId;
        
        layout = new MigLayout("fill, insets");
        background.setLayout(layout);
        background.add(panelBoard1, "pos 0 0 100% 100%");
        inicializacaoDoPanelCiclo();
        
        formularioVendaController = new FormularioVendaController(this);
        
        eventoDosBotoes();
        eventoDoMouse();
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
    
    private void eventoDosBotoes() {
        botaoActualizar.addActionListener(formularioVendaController);
        botaoAdicionar.addActionListener(formularioVendaController);
        botaoImprimir.addActionListener(formularioVendaController);
        botaoPermissao.addActionListener(formularioVendaController);
        botaoRemover.addActionListener(formularioVendaController);
        
        comboBoxCategoria.addActionListener(formularioVendaController);        
        comboBoxProduto.addActionListener(formularioVendaController);

    }
    
    private void inicializacaoDoPanelCiclo() {
        panelCiclo1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        panelCiclo1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mostrar = !mostrar;
                mostrarCarrinho();
            }
            
            
            @Override
            public void mouseExited(MouseEvent e) {
                panelCiclo1.setBackground(Color.decode("#000046"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panelCiclo1.setBackground(Color.decode("#1CB5E0"));
            }
            
        });
    }
    
    private void eventoDoMouse() {
//        tabelaVenda.addMouseListener(formularioUsuarioController);
    }
    
    public JDialog getDialogUsuario() {
        return dialogVenda;
    }

    public JTable getTabelaVenda() {
        return tabelaVenda;
    }

    public Tabela getTabelaCarrinho() {
        return tabelaCarrinho;
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
    
    public JButton getBotaoPermissao() {
        return botaoPermissao;
    }
    
    public JButton getBotaImprimir() {
        return botaoImprimir;
    }

    public JDialog getDialogVenda() {
        return dialogVenda;
    }

    public CampoDeTexto getTextoBuscarProdutoPeloID() {
        return textoBuscarProdutoPeloID;
    }

    public CampoDeTexto getTextoCPF() {
        return textoCPF;
    }

    public CampoDeTexto getTextoDesconto() {
        return textoDesconto;
    }

    public CampoDeTexto getTextoQuantidade() {
        return textoQuantidade;
    }

    public CampoDeTexto getTextoValorPago() {
        return textoValorPago;
    }

    public ComboBox getComboBoxCategoria() {
        return comboBoxCategoria;
    }

    public ComboBox getComboBoxProduto() {
        return comboBoxProduto;
    }

    public JLabel getLabelDesconto() {
        return labelDesconto;
    }

    public JLabel getLabelEstoqueQuantidade() {
        return labelEstoqueQuantidade;
    }

    public JLabel getLabelNomeDoProduto() {
        return labelNomeDoProduto;
    }

    public JLabel getLabelPrecoProduto() {
        return labelPrecoProduto;
    }

    public JLabel getLabelQuantidadeNoCarrinho() {
        return labelQuantidadeNoCarrinho;
    }

    public JLabel getLabelTotalVenda() {
        return labelTotalVenda;
    }

    public JLabel getLabelTroco() {
        return labelTroco;
    }

    public JLabel getLabelValorPago() {
        return labelValorPago;
    }

    public Botao getBotaoAdicionarCarrinho() {
        return botaoAdicionarCarrinho;
    }

    public Botao getBotaoCarrinhoLimpar() {
        return botaoCarrinhoLimpar;
    }

    public Botao getBotaoCarrinhoRemover() {
        return botaoCarrinhoRemover;
    }

    public Botao getBotaoLimpar() {
        return botaoLimpar;
    }

    public JButton getBotaoPesquisar() {
        return botaoPesquisar;
    }

    public Botao getBotaoVenda() {
        return botaoVenda;
    }
    
    public void mostrarCarrinho() {
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!mostrar) {
                    background.add(panelBoard2, "pos 0.5al 240", 0); // adicionar no primeiro indice
                    panelBoard2.setVisible(true);
                    background.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                
                if (mostrar) {
                    f = 240 * (1f - fraction);
                } else {
                    f = 240 * fraction;
                }

                layout.setComponentConstraints(panelBoard2, "pos 0.5al " + (int) (f - 240));
                background.repaint();
                background.revalidate();
            }

            @Override
            public void end() {
                if (mostrar) {
                    background.remove(panelBoard2);
                    background.repaint();
                    background.revalidate();
                } else {
                    mostrar = false;
                }
            }
        };
        
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogVenda = new javax.swing.JDialog();
        background = new javax.swing.JLayeredPane();
        panelBoard1 = new com.culysoft.gestao.venda.visao.componentes.PanelBoard();
        jLabel4 = new javax.swing.JLabel();
        panelCiclo1 = new com.culysoft.gestao.venda.visao.componentes.PanelCiclo();
        labelQuantidadeNoCarrinho = new javax.swing.JLabel();
        textoBuscarProdutoPeloID = new com.culysoft.gestao.venda.visao.componentes.CampoDeTexto();
        textoQuantidade = new com.culysoft.gestao.venda.visao.componentes.CampoDeTexto();
        comboBoxCategoria = new com.culysoft.gestao.venda.visao.componentes.ComboBox();
        comboBoxProduto = new com.culysoft.gestao.venda.visao.componentes.ComboBox();
        jLabel6 = new javax.swing.JLabel();
        textoDesconto = new com.culysoft.gestao.venda.visao.componentes.CampoDeTexto();
        textoValorPago = new com.culysoft.gestao.venda.visao.componentes.CampoDeTexto();
        textoCPF = new com.culysoft.gestao.venda.visao.componentes.CampoDeTexto();
        jPanel2 = new javax.swing.JPanel();
        botaoAdicionarCarrinho = new com.culysoft.gestao.venda.visao.componentes.Botao();
        botaoVenda = new com.culysoft.gestao.venda.visao.componentes.Botao();
        botaoLimpar = new com.culysoft.gestao.venda.visao.componentes.Botao();
        jPanel3 = new javax.swing.JPanel();
        labelNomeDoProduto = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        labelEstoqueQuantidade = new javax.swing.JLabel();
        labelPrecoProduto = new javax.swing.JLabel();
        labelTotalVenda = new javax.swing.JLabel();
        labelValorPago = new javax.swing.JLabel();
        labelDesconto = new javax.swing.JLabel();
        labelTroco = new javax.swing.JLabel();
        panelBoard2 = new com.culysoft.gestao.venda.visao.componentes.PanelBoard();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaCarrinho = new com.culysoft.gestao.venda.visao.componentes.Tabela();
        jPanel4 = new javax.swing.JPanel();
        botaoCarrinhoRemover = new com.culysoft.gestao.venda.visao.componentes.Botao();
        botaoCarrinhoLimpar = new com.culysoft.gestao.venda.visao.componentes.Botao();
        comboBox1 = new com.culysoft.gestao.venda.visao.componentes.ComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botaoAdicionar = new javax.swing.JButton();
        botaoActualizar = new javax.swing.JButton();
        botaoRemover = new javax.swing.JButton();
        botaoPermissao = new javax.swing.JButton();
        botaoImprimir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVenda = new com.culysoft.gestao.venda.visao.componentes.Tabela();
        jLabel2 = new javax.swing.JLabel();
        dataInicial = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        dataFinal = new com.toedter.calendar.JDateChooser();
        botaoPesquisar = new javax.swing.JButton();

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 491, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout dialogVendaLayout = new javax.swing.GroupLayout(dialogVenda.getContentPane());
        dialogVenda.getContentPane().setLayout(dialogVendaLayout);
        dialogVendaLayout.setHorizontalGroup(
            dialogVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );
        dialogVendaLayout.setVerticalGroup(
            dialogVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );

        panelBoard1.setBackground(new java.awt.Color(255, 255, 255));
        panelBoard1.setCor1(new java.awt.Color(255, 255, 255));
        panelBoard1.setCor2(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(28, 181, 224));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Registro da venda");

        panelCiclo1.setBackground(new java.awt.Color(0, 0, 70));

        labelQuantidadeNoCarrinho.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelQuantidadeNoCarrinho.setForeground(new java.awt.Color(255, 255, 255));
        labelQuantidadeNoCarrinho.setIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-de-venda\\src\\main\\java\\com\\culysoft\\gestao\\venda\\visao\\icon\\carrinho.png")); // NOI18N
        labelQuantidadeNoCarrinho.setText("0");

        javax.swing.GroupLayout panelCiclo1Layout = new javax.swing.GroupLayout(panelCiclo1);
        panelCiclo1.setLayout(panelCiclo1Layout);
        panelCiclo1Layout.setHorizontalGroup(
            panelCiclo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCiclo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelQuantidadeNoCarrinho)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCiclo1Layout.setVerticalGroup(
            panelCiclo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCiclo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelQuantidadeNoCarrinho, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
        );

        textoBuscarProdutoPeloID.setToolTipText("");
        textoBuscarProdutoPeloID.setDicas("Buscar Produto pelo ID");
        textoBuscarProdutoPeloID.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-de-venda\\src\\main\\java\\com\\culysoft\\gestao\\venda\\visao\\icon\\produto1.png")); // NOI18N

        textoQuantidade.setDicas("Quantidade");
        textoQuantidade.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-de-venda\\src\\main\\java\\com\\culysoft\\gestao\\venda\\visao\\icon\\quantidade.png")); // NOI18N

        comboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleciona a categoria" }));
        comboBoxCategoria.setActionCommand("categoria");
        comboBoxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxCategoriaActionPerformed(evt);
            }
        });

        comboBoxProduto.setActionCommand("produto");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Detalhes da venda");

        textoDesconto.setDicas("Desconto");
        textoDesconto.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-de-venda\\src\\main\\java\\com\\culysoft\\gestao\\venda\\visao\\icon\\desconto.png")); // NOI18N

        textoValorPago.setDicas("Valor Pago");

        textoCPF.setDicas("CPF / CNPJ");
        textoCPF.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-de-venda\\src\\main\\java\\com\\culysoft\\gestao\\venda\\visao\\icon\\id.png")); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        botaoAdicionarCarrinho.setBackground(new java.awt.Color(28, 181, 223));
        botaoAdicionarCarrinho.setForeground(new java.awt.Color(255, 255, 255));
        botaoAdicionarCarrinho.setText("Adicionar");
        botaoAdicionarCarrinho.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel2.add(botaoAdicionarCarrinho);

        botaoVenda.setBackground(new java.awt.Color(28, 181, 223));
        botaoVenda.setForeground(new java.awt.Color(255, 255, 255));
        botaoVenda.setText("Vender");
        botaoVenda.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel2.add(botaoVenda);

        botaoLimpar.setBackground(new java.awt.Color(28, 181, 223));
        botaoLimpar.setForeground(new java.awt.Color(255, 255, 255));
        botaoLimpar.setText("Limpar");
        botaoLimpar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel2.add(botaoLimpar);

        jPanel3.setBackground(new java.awt.Color(0, 0, 70));

        labelNomeDoProduto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNomeDoProduto.setForeground(new java.awt.Color(255, 255, 255));
        labelNomeDoProduto.setText("Nome do Produto:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Qtd do estoque:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Preço:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Total da venda:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Valor Pago:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Desconto:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Troco:");

        labelEstoqueQuantidade.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEstoqueQuantidade.setForeground(new java.awt.Color(255, 255, 255));
        labelEstoqueQuantidade.setText("0");

        labelPrecoProduto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelPrecoProduto.setForeground(new java.awt.Color(255, 255, 255));
        labelPrecoProduto.setText("0.00");

        labelTotalVenda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelTotalVenda.setForeground(new java.awt.Color(255, 255, 255));
        labelTotalVenda.setText("0.00");

        labelValorPago.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelValorPago.setForeground(new java.awt.Color(255, 255, 255));
        labelValorPago.setText("0.00");

        labelDesconto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelDesconto.setForeground(new java.awt.Color(255, 255, 255));
        labelDesconto.setText("0.00");

        labelTroco.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelTroco.setForeground(new java.awt.Color(255, 255, 255));
        labelTroco.setText("0.00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(labelEstoqueQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(labelNomeDoProduto)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(labelPrecoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelTotalVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(labelDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelTroco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelValorPago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(6, 6, 6))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNomeDoProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(labelEstoqueQuantidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPrecoProduto)
                    .addComponent(jLabel9))
                .addGap(53, 53, 53)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(labelTotalVenda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelValorPago)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDesconto)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTroco)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBoard1Layout = new javax.swing.GroupLayout(panelBoard1);
        panelBoard1.setLayout(panelBoard1Layout);
        panelBoard1Layout.setHorizontalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBoard1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelCiclo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBoard1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelBoard1Layout.createSequentialGroup()
                                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6)
                                    .addComponent(textoBuscarProdutoPeloID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textoQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoard1Layout.createSequentialGroup()
                                        .addComponent(comboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboBoxProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(textoDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textoValorPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textoCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        panelBoard1Layout.setVerticalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCiclo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBoard1Layout.createSequentialGroup()
                        .addComponent(textoBuscarProdutoPeloID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(textoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textoValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(28, 181, 224));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Checkout");

        tabelaCarrinho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane2.setViewportView(tabelaCarrinho);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        botaoCarrinhoRemover.setText("Remover");
        jPanel4.add(botaoCarrinhoRemover);

        botaoCarrinhoLimpar.setText("Limpar");
        botaoCarrinhoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCarrinhoLimparActionPerformed(evt);
            }
        });
        jPanel4.add(botaoCarrinhoLimpar);

        javax.swing.GroupLayout panelBoard2Layout = new javax.swing.GroupLayout(panelBoard2);
        panelBoard2.setLayout(panelBoard2Layout);
        panelBoard2Layout.setHorizontalGroup(
            panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard2Layout.createSequentialGroup()
                .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBoard2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBoard2Layout.setVerticalGroup(
            panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("> Venda");

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

        botaoPermissao.setBackground(new java.awt.Color(0, 0, 70));
        botaoPermissao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoPermissao.setForeground(new java.awt.Color(255, 255, 255));
        botaoPermissao.setText("Detalhes");
        botaoPermissao.setName("permissao"); // NOI18N
        jPanel1.add(botaoPermissao);

        botaoImprimir.setBackground(new java.awt.Color(0, 0, 70));
        botaoImprimir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoImprimir.setForeground(new java.awt.Color(255, 255, 255));
        botaoImprimir.setText("Imprimir");
        botaoImprimir.setName("imprimir"); // NOI18N
        jPanel1.add(botaoImprimir);

        tabelaVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaVenda);

        jLabel2.setText("Data Inicial:");

        jLabel3.setText("Data Final:");

        botaoPesquisar.setBackground(new java.awt.Color(0, 0, 70));
        botaoPesquisar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoPesquisar.setForeground(new java.awt.Color(255, 255, 255));
        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.setName("permissao"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(dataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoPesquisar)
                    .addComponent(dataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCarrinhoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCarrinhoLimparActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoCarrinhoLimparActionPerformed

    private void comboBoxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxCategoriaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane background;
    private javax.swing.JButton botaoActualizar;
    private javax.swing.JButton botaoAdicionar;
    private com.culysoft.gestao.venda.visao.componentes.Botao botaoAdicionarCarrinho;
    private com.culysoft.gestao.venda.visao.componentes.Botao botaoCarrinhoLimpar;
    private com.culysoft.gestao.venda.visao.componentes.Botao botaoCarrinhoRemover;
    private javax.swing.JButton botaoImprimir;
    private com.culysoft.gestao.venda.visao.componentes.Botao botaoLimpar;
    private javax.swing.JButton botaoPermissao;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton botaoRemover;
    private com.culysoft.gestao.venda.visao.componentes.Botao botaoVenda;
    private com.culysoft.gestao.venda.visao.componentes.ComboBox comboBox1;
    private com.culysoft.gestao.venda.visao.componentes.ComboBox comboBoxCategoria;
    private com.culysoft.gestao.venda.visao.componentes.ComboBox comboBoxProduto;
    private com.toedter.calendar.JDateChooser dataFinal;
    private com.toedter.calendar.JDateChooser dataInicial;
    private javax.swing.JDialog dialogVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelDesconto;
    private javax.swing.JLabel labelEstoqueQuantidade;
    private javax.swing.JLabel labelNomeDoProduto;
    private javax.swing.JLabel labelPrecoProduto;
    private javax.swing.JLabel labelQuantidadeNoCarrinho;
    private javax.swing.JLabel labelTotalVenda;
    private javax.swing.JLabel labelTroco;
    private javax.swing.JLabel labelValorPago;
    private com.culysoft.gestao.venda.visao.componentes.PanelBoard panelBoard1;
    private com.culysoft.gestao.venda.visao.componentes.PanelBoard panelBoard2;
    private com.culysoft.gestao.venda.visao.componentes.PanelCiclo panelCiclo1;
    private com.culysoft.gestao.venda.visao.componentes.Tabela tabelaCarrinho;
    private com.culysoft.gestao.venda.visao.componentes.Tabela tabelaVenda;
    private com.culysoft.gestao.venda.visao.componentes.CampoDeTexto textoBuscarProdutoPeloID;
    private com.culysoft.gestao.venda.visao.componentes.CampoDeTexto textoCPF;
    private com.culysoft.gestao.venda.visao.componentes.CampoDeTexto textoDesconto;
    private com.culysoft.gestao.venda.visao.componentes.CampoDeTexto textoQuantidade;
    private com.culysoft.gestao.venda.visao.componentes.CampoDeTexto textoValorPago;
    // End of variables declaration//GEN-END:variables
}
