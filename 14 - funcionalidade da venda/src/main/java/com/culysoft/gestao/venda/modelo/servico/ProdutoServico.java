
package com.culysoft.gestao.venda.modelo.servico;

import com.culysoft.gestao.venda.modelo.dto.ProdutoDto;
import com.culysoft.gestao.venda.modelo.entidade.Categoria;
import com.culysoft.gestao.venda.modelo.entidade.Produto;
import com.culysoft.gestao.venda.modelo.repositorio.impl.ProdutoRepositorioImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProdutoServico {
    
    private ProdutoRepositorioImpl produtoRepositorioImpl;
    private CategoriaServico categoriaServico;

    public ProdutoServico() {
        produtoRepositorioImpl = new ProdutoRepositorioImpl();
        categoriaServico = new CategoriaServico();
    }
    
    public List<ProdutoDto> buscaTodos() {
        List<ProdutoDto> produtosDto =  new ArrayList<>();
        try {
            produtosDto = produtoRepositorioImpl.buscarTodos()
                    .stream()
                    .map(p -> {
                        Optional<Categoria> categoria = categoriaServico.buscarPeloId(p.getCategoriaId());
                        
                        return ProdutoDto.builder()
                                .id(p.getId())
                                .nome(p.getNome())
                                .descricao(p.getDescricao())
                                .preco(p.getPreco())
                                .categoria(categoria.get().getNome())
                                .build();
                    })
                    .toList();
        } catch (Exception e) {
            System.out.println(e);
        }
        return produtosDto;
    }

    public String salvar(Produto produto) {
        boolean resultado = produtoRepositorioImpl.salvar(produto);
        
        if (resultado) return "Produto salvo com sucesso";
        
        return "erro ao salvar produto";
    }

    public void remover(Long produtoId) {
        produtoRepositorioImpl.removerPeloId(produtoId);
    }
    
    public List<Produto> buscaTodosPelaCategoria(String nome) {
        return produtoRepositorioImpl.buscaTodosPelaCategoria(nome);
    }
    
    public Optional<Produto> buscaPeloId(Long id) {
        return produtoRepositorioImpl.buscarPeloId(id);
    }
}
