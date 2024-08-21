/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.culysoft.gestao.venda.controlador;

import com.culysoft.gestao.venda.modelo.dto.VendaDto;
import com.culysoft.gestao.venda.modelo.entidade.Estoque;
import com.culysoft.gestao.venda.modelo.entidade.Produto;
import com.culysoft.gestao.venda.modelo.entidade.Usuario;
import com.culysoft.gestao.venda.modelo.servico.CategoriaServico;
import com.culysoft.gestao.venda.modelo.servico.EstoqueServico;
import com.culysoft.gestao.venda.modelo.servico.ProdutoServico;
import com.culysoft.gestao.venda.modelo.servico.UsuarioServico;
import com.culysoft.gestao.venda.modelo.servico.VendaServico;
import com.culysoft.gestao.venda.visao.formulario.FormularioVenda;
import com.culysoft.gestao.venda.visao.modelo.TabelaModeloVenda;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author qculissander
 */
public class FormularioVendaController implements ActionListener {
    
    private FormularioVenda formularioVenda;
    
    private VendaServico vendaServico;
    private UsuarioServico usuarioServico;
    private CategoriaServico categoriaServico;
    private ProdutoServico produtoServico;
    private EstoqueServico estoqueServico;
    
    private TabelaModeloVenda tabelaModeloVenda;
    
    private Optional<Produto> produto;
    private Optional<Estoque> estoque;

    public FormularioVendaController(FormularioVenda formularioVenda) {
        this.formularioVenda = formularioVenda;
        
        this.vendaServico = new VendaServico();
        usuarioServico = new UsuarioServico();
        categoriaServico = new CategoriaServico();
        produtoServico = new ProdutoServico();
        estoqueServico = new EstoqueServico();
        
        actualizarTabelaVenda();
        preencherComboBoxCategoria();
    }
    
    private void actualizarTabelaVenda() {
        // se o usuario tem permissao de admim
        Optional<Usuario> usuario = usuarioServico.buscarPeloId(formularioVenda.getUsuarioId());
        List<VendaDto> lista = new ArrayList<>();
        
        System.out.println("USUARIO: " + usuario);
        if (usuario.get().getPerfil().equalsIgnoreCase("ADMIN")) {
            System.out.println("Lista:" + lista);
            lista = vendaServico.buscarTodos();
        } else {
            
        }
       
        tabelaModeloVenda = new TabelaModeloVenda(lista);
        formularioVenda.getTabelaVenda().setModel(tabelaModeloVenda);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String acao = e.getActionCommand().toLowerCase();
        
        System.out.println(acao);
        switch(acao) {
            case "adicionar" -> { mostrarTelaDeVenda(); break; }
            case "categoria" -> { alterarValorDaCategoria(); break; }
            case "produto" -> { alterarPanelDosDetalhesDoProduto(); break; }
        }
    }
    
    private void mostrarTelaDeVenda() {
        formularioVenda.getDialogVenda().pack();
        formularioVenda.getDialogVenda().setLocationRelativeTo(null);
        formularioVenda.getDialogVenda().setVisible(true);
    }
    
    private void preencherComboBoxCategoria() {
        formularioVenda.getComboBoxCategoria().removeAllItems();
        
        
        formularioVenda.getComboBoxCategoria().addItem("Seleciona a categoria");
        formularioVenda.getComboBoxProduto().addItem("Seleciona o Produto");
        
        categoriaServico.buscaTodos()
                .forEach(c -> formularioVenda.getComboBoxCategoria().addItem(c.getNome()));
    }
    
    private void alterarValorDaCategoria() {
        limpaComboBoxProduto();
        if (formularioVenda.getComboBoxCategoria().getSelectedIndex() > 0) {
            String nomeDoProduto = formularioVenda.getComboBoxCategoria().getSelectedItem().toString();
            List<Produto> produtos = produtoServico.buscaTodosPelaCategoria(nomeDoProduto);
            produtos.forEach(p -> {
                formularioVenda.getComboBoxProduto().addItem(p.getNome());
                formularioVenda.getComboBoxProduto().setName(p.getId().toString());
            });
        }
    }
    
    private void limpaComboBoxProduto() {
        formularioVenda.getComboBoxProduto().removeAllItems();
        formularioVenda.getComboBoxProduto().addItem("Seleciona o Produto");
    }
    
    private void alterarPanelDosDetalhesDoProduto() {
        System.out.println("PRODUTODDDDD");
        if (formularioVenda.getComboBoxProduto().getSelectedIndex() > 0 ) {
            estoque = estoqueServico.buscarPeloProdutoId(Long.valueOf(formularioVenda.getComboBoxProduto().getName()));
            mostrarPanelDosDetalhesDoProduto();
        }
    }
    
    private void mostrarPanelDosDetalhesDoProduto() {
        if (estoque.isPresent()) {
            produto = produtoServico.buscaPeloId(Long.valueOf(formularioVenda.getComboBoxProduto().getName()));
            formularioVenda.getLabelNomeDoProduto().setText(produto.get().getNome());
            formularioVenda.getLabelEstoqueQuantidade().setText(estoque.get().getQuantidade().toString());
            formularioVenda.getLabelPrecoProduto().setText(estoque.get().getProdutoId().toString());
        }
    }
}
