
package com.culysoft.gestao.venda.modelo.servico;

import com.culysoft.gestao.venda.modelo.dto.EstoqueDto;
import com.culysoft.gestao.venda.modelo.entidade.Estoque;
import com.culysoft.gestao.venda.modelo.entidade.Produto;
import com.culysoft.gestao.venda.modelo.repositorio.impl.EstoqueRepositorioImpl;
import com.culysoft.gestao.venda.modelo.repositorio.impl.ProdutoRepositorioImpl;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;


public class EstoqueServico {
    
    private EstoqueRepositorioImpl estoqueRepositorio;
    private ProdutoRepositorioImpl produtoRepositorioImpl;

    public EstoqueServico() {
        estoqueRepositorio = new EstoqueRepositorioImpl();
        produtoRepositorioImpl = new ProdutoRepositorioImpl();
    }
    
    public List<EstoqueDto> buscaTodos() {
        List<Estoque> lista = estoqueRepositorio.buscarTodos();
        
        return lista.stream().map(e -> {
            Optional<Produto> produto = produtoRepositorioImpl.buscarPeloId(e.getProdutoId());
            
            return EstoqueDto.builder()
                    .id(e.getId())
                    .produto(produto.get().getNome())
                    .preco(produto.get().getPreco())
                    .quantidade(e.getQuantidade())
                    .estado(e.getEstado())
                    .dataCriacao(e.getDataCriacao())
                    .build();
        }).toList();
    }
    
    
    public Optional<Estoque> buscarPeloProdutoId(Long produtoid) {
        return estoqueRepositorio.buscarPeloProdutoId(produtoid);
    }
    
    public Estoque buscaPeloId(Long id) {
        Optional<Estoque> estoque = estoqueRepositorio.buscarPeloId(id);
        
        if (estoque.isEmpty()) {
            throw new RuntimeException("Estoque nao encontrado");
        }
        
        return estoque.get();
    }
    
    public String salvar(Estoque estoque) {
        boolean resultado = estoqueRepositorio.salvar(estoque);
        
        if (resultado) {
            return "Estoque registrado com sucesso";
        }
        return "Erro ao registrar estoque";
    }
    
    public void remover(Long id) {
        boolean resultado = estoqueRepositorio.removerPeloId(id);
        
        if (resultado)
            JOptionPane.showMessageDialog(null, "Estoque removido com sucesso");
        else 
            JOptionPane.showMessageDialog(null, "Erro ao remove");
    }
}
