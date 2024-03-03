package com.culysoft.gestao.venda.modelo.repositorio.impl;

import com.culysoft.gestao.venda.modelo.entidade.Categoria;

public class CategoriaRepositorio extends CrudRepositorioImpl {

    public CategoriaRepositorio() {
        super(Categoria.class);
    }
    
    
    
    public static void main(String[] args) {
        Categoria categoria = Categoria.builder()
                .id(2L)
                .nome("Livro")
                .descricao("Conhecimento e poder")
                .build();
        
        CategoriaRepositorio repositorio = new CategoriaRepositorio();
        
        System.out.println("RESULTADO: " + repositorio.buscarTodos());
    }
}
