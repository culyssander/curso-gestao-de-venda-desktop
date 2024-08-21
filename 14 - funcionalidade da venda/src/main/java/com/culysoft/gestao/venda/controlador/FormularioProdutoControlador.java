
package com.culysoft.gestao.venda.controlador;

import com.culysoft.gestao.venda.modelo.dto.ProdutoDto;
import com.culysoft.gestao.venda.modelo.entidade.Categoria;
import com.culysoft.gestao.venda.modelo.entidade.Produto;
import com.culysoft.gestao.venda.modelo.servico.CategoriaServico;
import com.culysoft.gestao.venda.modelo.servico.PermissaoServico;
import com.culysoft.gestao.venda.modelo.servico.ProdutoServico;
import com.culysoft.gestao.venda.visao.componentes.Mensagem;
import com.culysoft.gestao.venda.visao.formulario.FormularioProduto;
import com.culysoft.gestao.venda.visao.modelo.TabelaModeloCategoria;
import com.culysoft.gestao.venda.visao.modelo.TabelaModeloProduto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.swing.JOptionPane;


public class FormularioProdutoControlador implements ActionListener, MouseListener {
    
    private FormularioProduto formularioProduto;
    private ProdutoServico produtoServico;
    private TabelaModeloProduto tabelaModeloProduto;
    
    private CategoriaServico categoriaServico;
    private TabelaModeloCategoria tabelaModeloCategoria;
    private Long categoriaId = null;
    private Long produtoId = null;
    
    private PermissaoServico permissaoServico;
    
    private final long PERMISSAO_ID_PARA_SALVAR_PRODUTO = 7;
    private final long PERMISSAO_ID_PARA_REMOVER_PRODUTO = 8;
    
//    private final long PERMISSAO_ID_BUSCAR_TODOS = 3;
//    private final long PERMISSAO_ID_REMOVE = 4;
//    private final long PERMISSAO_ID_PARA_SALVAR_PERMISSAO = 16;

    public FormularioProdutoControlador(FormularioProduto formularioProduto) {
        this.formularioProduto = formularioProduto;
        
        produtoServico = new ProdutoServico();
        categoriaServico = new CategoriaServico();
        permissaoServico = new PermissaoServico();
        
        actualizaTabelaProduto();
        actualizaTabelaCategoria();
    }
    
    private void actualizaTabelaProduto() {
        tabelaModeloProduto = new TabelaModeloProduto(produtoServico.buscaTodos());
        formularioProduto.getTabelaProduto().setModel(tabelaModeloProduto);
    }
    
    public void actualizaTabelaCategoria() {
        tabelaModeloCategoria = new TabelaModeloCategoria(categoriaServico.buscaTodos());
        formularioProduto.getTela().getProdutoCategoria().getTabelaCategoria().setModel(tabelaModeloCategoria);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String acao = e.getActionCommand().toLowerCase();
        
        switch(acao) {
            case "adicionar" -> { mostrarTelaDeCadastroProdutoCategoria(); break;}
            case "actualizar" -> {mostrarTelaDeCadastroProdutoParaActualiza(); break;}
            case "remover" -> {removerProduto();  break;}
        }
        
        salvarCategoria();
        salvarProduto();
    }
    
    private void mostrarTelaDeCadastroProdutoCategoria() {
        permissaoServico.validaPermissao(PERMISSAO_ID_PARA_SALVAR_PRODUTO, formularioProduto.getUsuarioId());
        formularioProduto.getDashboard().setForm(formularioProduto.getTela());
        limpaCampoProduto();
        limpaCampoCategoria();
    }
    
    private void mostrarTelaDeCadastroProdutoParaActualiza() {
        permissaoServico.validaPermissao(PERMISSAO_ID_PARA_SALVAR_PRODUTO, formularioProduto.getUsuarioId());
        selecionaProdutoNaTabela();
        formularioProduto.getDashboard().setForm(formularioProduto.getTela());
    }
    
    private void selecionaProdutoNaTabela() {
        if (produtoId == null) {
            String mensagem = "Seleciona um produto na tablea";
            JOptionPane.showMessageDialog(null, mensagem, "Produto nao selecionado", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(mensagem);
        }
    }
    
    private void removerProduto() {
        permissaoServico.validaPermissao(PERMISSAO_ID_PARA_REMOVER_PRODUTO, formularioProduto.getUsuarioId());
        selecionaProdutoNaTabela();
        produtoServico.remover(produtoId);
        actualizaTabelaProduto();
        limpaCampoProduto();
   }
    
    
    private void salvarCategoria() {
        formularioProduto.getTela().getProdutoCategoria().getBotaoCategoria().addActionListener(e -> {
            String nome = formularioProduto.getTela().getProdutoCategoria().getTextoNomeCategoria().getText();
            String descricao = formularioProduto.getTela().getProdutoCategoria().getTextoDescricaoCategoria().getText();
            
            // Validacao
            validacaoDeCampoVazio(nome);
            
            Categoria categoria = Categoria.builder()
                    .id(categoriaId)
                    .nome(nome)
                    .descricao(descricao)
                    .build();
            
            String mensagem = categoriaServico.salvar(categoria);
            
            if (mensagem.startsWith("Categoria")) {
                formularioProduto.getTela().getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.SUCESSO, mensagem);
                actualizaTabelaCategoria();
                limpaCampoCategoria();
                formularioProduto.getTela().getProdutoCategoria().preencherComboBoxCategoria();
            }
            else formularioProduto.getTela().getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, mensagem);
        });
    }
    
    public void limpaCampoCategoria() {
        categoriaId = null;
        formularioProduto.getTela().getProdutoCategoria().getTextoNomeCategoria().setText("");
        formularioProduto.getTela().getProdutoCategoria().getTextoDescricaoCategoria().setText("");
        actualizaTabelaCategoria();
    }
    
    private void validacaoDeCampoVazio(String texto) {
        if (texto.isBlank()) {
            String mensagem = "Campo obrigatorio";
            formularioProduto.getTela().getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, mensagem);
            throw new RuntimeException(mensagem);
        }
    }

    public TabelaModeloCategoria getTabelaModeloCategoria() {
        return tabelaModeloCategoria;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    
    private void salvarProduto() {
        // Valida permissao
        permissaoServico.validaPermissao(PERMISSAO_ID_PARA_SALVAR_PRODUTO, formularioProduto.getUsuarioId());
        
        formularioProduto.getTela().getProdutoCategoria().getBotaoProduto().addActionListener(e -> {
            String nome = formularioProduto.getTela().getProdutoCategoria().getTextoNomeProduto().getText();
            String descricao = formularioProduto.getTela().getProdutoCategoria().getTextoDescricaoProduto().getText();
            String precoTemp = formularioProduto.getTela().getProdutoCategoria().getTextoPrecoProduto().getText();
            
            validacaoDeCampoVazio(nome);
            validacaoDeCampoVazio(precoTemp);
            validaComboCategoriaProduto();
            
            BigDecimal preco = null;
            
            try {
                preco = BigDecimal.valueOf(Double.parseDouble(precoTemp));
            } catch (NumberFormatException err) {
                mensagemDeErroProduto("Erro no preco");
            }
            
            Produto produto = Produto.builder()
                    .id(produtoId)
                    .nome(nome)
                    .descricao(descricao)
                    .preco(preco)
                    .categoriaId(categoriaId)
                    .dataCriacao(LocalDateTime.now())
                    .usuarioId(formularioProduto.getUsuarioId())
                    .build();
            
            String mensagem = produtoServico.salvar(produto);
            
            if (mensagem.startsWith("Produto")) {
                formularioProduto.getTela().getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.SUCESSO, mensagem);
                actualizaTabelaProduto();
                limpaCampoProduto();
            }
            else formularioProduto.getTela().getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, mensagem);
        });
    }
    
    private void validaComboCategoriaProduto() {
        if (formularioProduto.getTela().getProdutoCategoria().getComboBoxCategoriaProduto().getSelectedIndex() == 0) {
            mensagemDeErroProduto("Seleciona categoria");
        } 
        String categoriaNome = formularioProduto.getTela().getProdutoCategoria().getComboBoxCategoriaProduto().getSelectedItem().toString();
        Optional<Categoria> categoriaTemp = categoriaServico.buscarPeloNome(categoriaNome);
        
        if (categoriaTemp.isPresent()) categoriaId = categoriaTemp.get().getId();
        else {
            mensagemDeErroProduto("Categoria inexistente");
        }
    }
    
    private void mensagemDeErroProduto(String mensagem) {
        formularioProduto.getTela().getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, mensagem);
        throw new RuntimeException(mensagem);
    }
    
    
    public void limpaCampoProduto() {
        formularioProduto.getTela().getProdutoCategoria().getTextoNomeProduto().setText("");
        formularioProduto.getTela().getProdutoCategoria().getTextoDescricaoProduto().setText("");
        formularioProduto.getTela().getProdutoCategoria().getTextoPrecoProduto().setText("");
        formularioProduto.getTela().getProdutoCategoria().getComboBoxCategoriaProduto().setSelectedIndex(0);
    }
    
    private void preencherCampoProduto(ProdutoDto produto) {
        formularioProduto.getTela().getProdutoCategoria().getTextoNomeProduto().setText(produto.getNome());
        formularioProduto.getTela().getProdutoCategoria().getTextoDescricaoProduto().setText(produto.getDescricao());
        formularioProduto.getTela().getProdutoCategoria().getTextoPrecoProduto().setText(produto.getPreco().toString());
        formularioProduto.getTela().getProdutoCategoria().getComboBoxCategoriaProduto().setSelectedItem(produto.getCategoria());
        produtoId = produto.getId();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int linha = formularioProduto.getTabelaProduto().getSelectedRow();
        ProdutoDto produtoTemp = tabelaModeloProduto.getProdutos().get(linha);
        preencherCampoProduto(produtoTemp);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
