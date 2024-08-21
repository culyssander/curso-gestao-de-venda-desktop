
package com.culysoft.gestao.venda.controlador;

import com.culysoft.gestao.venda.modelo.dto.EstoqueDto;
import com.culysoft.gestao.venda.modelo.entidade.Estoque;
import com.culysoft.gestao.venda.modelo.entidade.EstoqueHistorico;
import com.culysoft.gestao.venda.modelo.entidade.Produto;
import com.culysoft.gestao.venda.modelo.repositorio.impl.EstoqueHistoricoRepositorioImpl;
import com.culysoft.gestao.venda.modelo.repositorio.impl.ProdutoRepositorioImpl;
import com.culysoft.gestao.venda.modelo.servico.EstoqueServico;
import com.culysoft.gestao.venda.visao.componentes.Mensagem;
import com.culysoft.gestao.venda.visao.formulario.FormularioEstoque;
import com.culysoft.gestao.venda.visao.modelo.TabelaModeloEstoque;
import com.culysoft.gestao.venda.visao.modelo.TabelaModeloEstoqueHistorico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.swing.JOptionPane;


public class FormularioEstoqueController implements ActionListener, KeyListener, MouseListener {
    
    private FormularioEstoque formularioEstoque;
    private EstoqueServico estoqueServico;
    private TabelaModeloEstoque tabelaModeloEstoque;
    private Optional<Produto> produto;
    private ProdutoRepositorioImpl produtoRepositorioImpl;
    private Long estoqueId;
    private EstoqueDto estoqueDto;
    
    private EstoqueHistoricoRepositorioImpl estoqueHistoricoRepositorioImpl;
    private TabelaModeloEstoqueHistorico tabelaModeloEstoqueHistorico;

    public FormularioEstoqueController(FormularioEstoque formularioEstoque) {
        this.formularioEstoque = formularioEstoque;
        
        estoqueServico = new EstoqueServico();
        produtoRepositorioImpl = new ProdutoRepositorioImpl();
        estoqueHistoricoRepositorioImpl = new EstoqueHistoricoRepositorioImpl();
        actualizaTabelaEstoque();
        actualizaTabelaEstoqueHistorico();
    }
    
    private void actualizaTabelaEstoque() {
        tabelaModeloEstoque = new TabelaModeloEstoque(estoqueServico.buscaTodos());
        formularioEstoque.getTabelaEstoque().setModel(tabelaModeloEstoque);
    }
    
    private void actualizaTabelaEstoqueHistorico() {
        tabelaModeloEstoqueHistorico = new TabelaModeloEstoqueHistorico(estoqueHistoricoRepositorioImpl.buscarTodos());
        formularioEstoque.getFormularioPrincipal().getTabelaDoFormularioPrincipal().setModel(tabelaModeloEstoqueHistorico);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String acao = e.getActionCommand().toLowerCase();
        
        switch(acao) {
            case "adicionar" -> {mostrarTelaAdicionar(); limpaCampos(); break;}
            case "actualizar" -> {mostrarTelaParaActualizar(); }
            case "salvar" -> { salvar(); break; }
            case "remover" -> { remover(); break; }
        }
    }
    
    private void mostrarTelaAdicionar() {
        formularioEstoque.getDialogEstoque().pack();        
        formularioEstoque.getDialogEstoque().setLocationRelativeTo(null);
        formularioEstoque.getDialogEstoque().setVisible(true);
    }
    
    private void mostrarTelaParaActualizar() {
        selecionaATabela();
        preencherValoresNoFormulario();
        mostrarTelaAdicionar();
    }

    private void preencherValoresNoFormulario() {
        formularioEstoque.getTextoNomeOuId().setText(estoqueDto.getProduto());
        formularioEstoque.getTextoQuantidade().setText(estoqueDto.getQuantidade().toString());
//        formularioEstoque.getTextoObservacao().setText(estoqueDto.get);
        formularioEstoque.getLabelTextoDoProduto().setText(estoqueDto.getProduto());
        formularioEstoque.getRadioActivo().setSelected(estoqueDto.getEstado());
        produto = produtoRepositorioImpl.buscarPeloNome(estoqueDto.getProduto());
    }
    
    private void limpaCampos() {
        formularioEstoque.getTextoNomeOuId().setText("");
        formularioEstoque.getTextoQuantidade().setText("");
        formularioEstoque.getTextoObservacao().setText("");
        formularioEstoque.getLabelTextoDoProduto().setText("");
        formularioEstoque.getRadioActivo().setSelected(true);
        estoqueId = null;
        produto = Optional.empty();
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (!formularioEstoque.getTextoNomeOuId().getText().isBlank()) {
            String texto = formularioEstoque.getTextoNomeOuId().getText();
            try {
                produto = produtoRepositorioImpl.buscarPeloId(Long.valueOf(texto));
            } catch (Exception ee) {
                produto = produtoRepositorioImpl.buscarPeloNome(texto);
            }
            
            if (produto.isPresent()) {
                formularioEstoque.getLabelTextoDoProduto().setText(produto.get().getNome());
            } else {
                formularioEstoque.getLabelTextoDoProduto().setText("");
            }
        }
    }
    
    private void validacaoDeCampoVazio(String texto) {
        if (texto.isBlank()) {
            mensagemDeErro("Campos obrigatorios");
        }
    }
    
    private void salvar() {
        String nomeOuIdProduto = formularioEstoque.getTextoNomeOuId().getText();
        String quantidadeString = formularioEstoque.getTextoQuantidade().getText();
        String descricao = formularioEstoque.getTextoObservacao().getText();

        validacaoDeCampoVazio(nomeOuIdProduto);
        validacaoDeCampoVazio(quantidadeString);
        
        if (produto.isEmpty()) {
            mensagemDeErro("Produto nao encontrado");
        }
        
        Estoque estoque;
        
        try {
            Integer quantidade = Integer.valueOf(quantidadeString);
            Optional<Estoque> estoqueOptional = estoqueServico.buscarPeloProdutoId(produto.get().getId());
            
            if (estoqueId == null) {
               estoque = new Estoque();
               if (estoqueOptional.isPresent())
                   estoque.setId(estoqueOptional.get().getId());
               
               quantidade = estoqueOptional.isPresent() ? quantidade + estoqueOptional.get().getQuantidade() : quantidade;
               
            } else {
                estoque = estoqueServico.buscaPeloId(estoqueId);
                
                int confirmar = JOptionPane.showConfirmDialog(null, "Tens certeza que desejas alterar o estoque?\n"
                        + "A quantidade do estoque será modificada com o novo valor", "Alterar estoque", JOptionPane.YES_NO_OPTION);
                
                if (!(confirmar == JOptionPane.YES_OPTION)) {
                    throw new RuntimeException();
                } 
            }
            
            estoque.setQuantidade(quantidade);
            estoque.setProdutoId(produto.get().getId());
            estoque.setUsuarioId(formularioEstoque.getUsuarioId());
            estoque.setDataCriacao(LocalDateTime.now());
            estoque.setEstado(formularioEstoque.getRadioActivo().isSelected());
            
            String mensagem = estoqueServico.salvar(estoque);
            
            if (mensagem.startsWith("Estoque")){
                formularioEstoque.getMensagem().mostrarMensagem(Mensagem.TipoMensagem.SUCESSO, mensagem);
                estoqueHistoricoRepositorioImpl.salvar(EstoqueHistorico.builder()
                        .estado("ENTRADA")
                        .dataCriacao(LocalDateTime.now())
                        .observacao(estoqueId == null ? "ADICIONAR" + descricao : "ALTERAR " + descricao)
                        .produto(produto.get().getNome())
                        .quantidade(quantidade)
                        .usuarioId(formularioEstoque.getUsuarioId().toString())
                        .build());
                actualizaTabelaEstoque();
                actualizaTabelaEstoqueHistorico();
                limpaCampos();
            } else {
                mensagemDeErro(mensagem);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            mensagemDeErro(e.getMessage());
        }
    }
     
    private void mensagemDeErro(String mensagem) {
        formularioEstoque.getMensagem().mostrarMensagem(Mensagem.TipoMensagem.ERRO, mensagem);
        throw new RuntimeException(mensagem);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int linha = formularioEstoque.getTabelaEstoque().getSelectedRow();
        
        estoqueId = tabelaModeloEstoque.getEstoques().get(linha).getId();
        estoqueDto = tabelaModeloEstoque.getEstoques().get(linha);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public void remover() {
        selecionaATabela();
        estoqueServico.remover(estoqueId);
        salvarHistorico("REMOVER", "SAIDA");
        actualizaTabelaEstoque();
        actualizaTabelaEstoqueHistorico();
        limpaCampos();
    }
    
    private void salvarHistorico(String observacao, String estado) {
        EstoqueHistorico estoqueHistorico =  EstoqueHistorico.builder()
                .estado(estado)
                .dataCriacao(LocalDateTime.now())
                .observacao(observacao)
                .produto(estoqueDto.getProduto())
                .quantidade(estoqueDto.getQuantidade())
                .usuarioId(formularioEstoque.getUsuarioId().toString())
                .build();
        
        estoqueHistoricoRepositorioImpl.salvar(estoqueHistorico);
    }
    
    private void selecionaATabela() {
        if (estoqueId == null) {
            JOptionPane.showMessageDialog(null, "Seleciona a tabela", "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException();
        }
    }
}
