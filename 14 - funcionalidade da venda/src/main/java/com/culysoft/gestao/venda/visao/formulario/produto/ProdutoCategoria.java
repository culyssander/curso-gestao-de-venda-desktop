
package com.culysoft.gestao.venda.visao.formulario.produto;

import com.culysoft.gestao.venda.controlador.FormularioProdutoControlador;
import com.culysoft.gestao.venda.modelo.entidade.Categoria;
import com.culysoft.gestao.venda.modelo.servico.CategoriaServico;
import com.culysoft.gestao.venda.visao.componentes.BarraDeRolar;
import com.culysoft.gestao.venda.visao.componentes.Botao;
import com.culysoft.gestao.venda.visao.componentes.CampoDeTexto;
import com.culysoft.gestao.venda.visao.componentes.ComboBox;
import com.culysoft.gestao.venda.visao.componentes.Mensagem;
import com.culysoft.gestao.venda.visao.componentes.Tabela;
import com.culysoft.gestao.venda.visao.formulario.FormularioProduto;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;

public class ProdutoCategoria extends javax.swing.JPanel {
    
    private CampoDeTexto textoNomeCategoria;
    private CampoDeTexto textoDescricaoCategoria;
    private JButton botaoResetCategoria;
    private Botao botaoCategoria;
    
    private CampoDeTexto textoNomeProduto;
    private CampoDeTexto textoDescricaoProduto;
    private CampoDeTexto textoPrecoProduto;
    private ComboBox comboBoxCategoriaProduto;
    private Botao botaoProduto;
    
    String caminho = "\\src\\main\\java\\com\\culysoft\\gestao\\venda\\visao\\icon\\";
    
//    private FormularioProdutoControlador formularioProdutoControlador;
    private FormularioProduto formularioProduto;
    private CategoriaServico categoriaServico;
    
    public ProdutoCategoria(FormularioProduto formularioProduto) {
        initComponents();
        
        produto.setVisible(true);
        categoria.setVisible(false);
        
        formularioProduto();
        formularioCategoria();
        this.formularioProduto = formularioProduto;
        
        categoriaServico = new CategoriaServico();
        preencherComboBoxCategoria();
        
        eventoNaTabela();
        limpa();
    }
    
    public void preencherComboBoxCategoria() {
        comboBoxCategoriaProduto.removeAllItems();
        comboBoxCategoriaProduto.addItem("Seleciona a categoria");
        
        categoriaServico.buscaTodos()
                .forEach(c -> comboBoxCategoriaProduto.addItem(c.getNome()));
    }
    
    private void eventoNaTabela() {
        tabelaCategoria.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               int linha = tabelaCategoria.getSelectedRow();
               int coluna = tabelaCategoria.getSelectedColumn();
                Categoria  categoria = formularioProduto.getFormularioProdutoControlador().getTabelaModeloCategoria().getCategorias().get(linha);
                textoNomeCategoria.setText(categoria.getNome());
                textoDescricaoCategoria.setText(categoria.getDescricao());
                formularioProduto.getFormularioProdutoControlador().setCategoriaId(categoria.getId());
                
                
                System.out.println("Coluna: " + coluna);
                
                if (coluna == 3) {
                   int confirma = JOptionPane.showConfirmDialog(null, "Tens certeza que desejas remover\n" 
                           + categoria.getNome(), "Remover categoria", JOptionPane.YES_NO_OPTION);
                   
                   if (confirma == JOptionPane.YES_OPTION) {
                       categoriaServico.removerPeloId(categoria.getId());
                       formularioProduto.getTela().getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.SUCESSO, "Removido com Sucesso.");
                       formularioProduto.getFormularioProdutoControlador().actualizaTabelaCategoria();
                       formularioProduto.getFormularioProdutoControlador().limpaCampoCategoria();
                       preencherComboBoxCategoria();
                   }
                }
            }
            
        });
    }
    
    private void limpa() {
        botaoResetCategoria.addActionListener(e -> {
            formularioProduto.getFormularioProdutoControlador().limpaCampoCategoria();
        });
    }
    private void formularioProduto() {
        produto.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]25[]push"));
        
        JLabel label = new JLabel("Registro de Produto");
        label.setForeground(Color.decode("#1cb5e0"));
        label.setFont(new Font("sansserif", 1, 20));
        produto.add(label);
        
        textoNomeProduto = new CampoDeTexto();
        textoNomeProduto.setDicas("Nome");
        textoNomeProduto.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir")+ caminho + "produto1.png"));
        produto.add(textoNomeProduto, "w 60%");
        
        textoDescricaoProduto = new CampoDeTexto();
        textoDescricaoProduto.setDicas("Descricao");
        textoDescricaoProduto.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir")+ caminho + "descricao.png"));
        produto.add(textoDescricaoProduto, "w 60%");
        
        textoPrecoProduto = new CampoDeTexto();
        textoPrecoProduto.setDicas("Preco");
        textoPrecoProduto.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir")+ caminho + "preco.png"));
        produto.add(textoPrecoProduto, "w 60%");
        
        
        comboBoxCategoriaProduto = new ComboBox();
        comboBoxCategoriaProduto.addItem("Seleciona a categoria");
        produto.add(comboBoxCategoriaProduto, "w 60%, h 35");
        
        botaoProduto = new Botao();
        botaoProduto.setBackground(new Color(28, 181, 224));
        botaoProduto.setForeground(Color.WHITE);
        botaoProduto.setFont(new Font("sansserif", 5, 14));
        botaoProduto.setActionCommand("salvarproduto");
        botaoProduto.setText("Salvar");
        produto.add(botaoProduto, "w 40%, h 40");
    }
    
    private void formularioCategoria() {
        categoria.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]25[]push"));
        
        JLabel label = new JLabel("Registro de Categoria");
        label.setForeground(Color.decode("#1cb5e0"));
        label.setFont(new Font("sansserif", 1, 20));
        categoria.add(label);
        
        textoNomeCategoria = new CampoDeTexto();
        textoNomeCategoria.setDicas("Nome");
        textoNomeCategoria.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir")+ caminho + "produto1.png"));
        categoria.add(textoNomeCategoria, "w 60%");
        
        textoDescricaoCategoria = new CampoDeTexto();
        textoDescricaoCategoria.setDicas("Descricao");
        textoDescricaoCategoria.setPrefixoIcon(new ImageIcon(System.getProperty("user.dir")+ caminho + "descricao.png"));
        categoria.add(textoDescricaoCategoria, "w 60%");
        
        botaoResetCategoria = new JButton("Limpa");
        botaoResetCategoria.setContentAreaFilled(false);
        botaoResetCategoria.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoResetCategoria.setForeground(Color.decode("#1cb5e0"));
        categoria.add(botaoResetCategoria);
        
        botaoCategoria = new Botao();
        botaoCategoria.setBackground(new Color(28, 181, 224));
        botaoCategoria.setForeground(Color.WHITE);
        botaoCategoria.setFont(new Font("sansserif", 5, 14));
        botaoCategoria.setActionCommand("salvarcategoria");
        botaoCategoria.setText("Salvar");
        categoria.add(botaoCategoria, "w 40%, h 40");
        
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        jScrollPane1.setVerticalScrollBar(new BarraDeRolar());
        categoria.add(jScrollPane1, "w 80%, h 40%");
        
    }
    
    public void mostrarProduto(boolean mostrar) {
        if (mostrar) {
            produto.setVisible(true);
            categoria.setVisible(false);
        } else {
            produto.setVisible(false);
            categoria.setVisible(true);
        }
    }
    

    public CampoDeTexto getTextoNomeCategoria() {
        return textoNomeCategoria;
    }

    public CampoDeTexto getTextoDescricaoCategoria() {
        return textoDescricaoCategoria;
    }

    public JButton getBotaoResetCategoria() {
        return botaoResetCategoria;
    }

    public Botao getBotaoCategoria() {
        return botaoCategoria;
    }

    public CampoDeTexto getTextoNomeProduto() {
        return textoNomeProduto;
    }

    public CampoDeTexto getTextoDescricaoProduto() {
        return textoDescricaoProduto;
    }

    public CampoDeTexto getTextoPrecoProduto() {
        return textoPrecoProduto;
    }
    
    public ComboBox getComboBoxCategoriaProduto() {
        return comboBoxCategoriaProduto;
    }

    public Botao getBotaoProduto() {
        return botaoProduto;
    }

    public Tabela getTabelaCategoria() {
        return tabelaCategoria;
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JLayeredPane();
        produto = new javax.swing.JPanel();
        categoria = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCategoria = new com.culysoft.gestao.venda.visao.componentes.Tabela();

        background.setLayout(new java.awt.CardLayout());

        produto.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout produtoLayout = new javax.swing.GroupLayout(produto);
        produto.setLayout(produtoLayout);
        produtoLayout.setHorizontalGroup(
            produtoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        produtoLayout.setVerticalGroup(
            produtoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        background.add(produto, "card3");

        categoria.setBackground(new java.awt.Color(255, 255, 255));

        tabelaCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaCategoria);

        javax.swing.GroupLayout categoriaLayout = new javax.swing.GroupLayout(categoria);
        categoria.setLayout(categoriaLayout);
        categoriaLayout.setHorizontalGroup(
            categoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        categoriaLayout.setVerticalGroup(
            categoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoriaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
        );

        background.add(categoria, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane background;
    private javax.swing.JPanel categoria;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel produto;
    private com.culysoft.gestao.venda.visao.componentes.Tabela tabelaCategoria;
    // End of variables declaration//GEN-END:variables
}
