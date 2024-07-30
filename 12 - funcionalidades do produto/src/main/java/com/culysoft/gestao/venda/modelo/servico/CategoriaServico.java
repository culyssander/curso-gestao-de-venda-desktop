
package com.culysoft.gestao.venda.modelo.servico;

import com.culysoft.gestao.venda.modelo.entidade.Categoria;
import com.culysoft.gestao.venda.modelo.repositorio.impl.CategoriaRepositorio;
import java.util.Optional;


public class CategoriaServico {
    private CategoriaRepositorio categoriaRepositorio;

    public CategoriaServico() {
        categoriaRepositorio = new CategoriaRepositorio();
    }
    
    public Optional<Categoria> buscarPeloId(Long id) {
        return categoriaRepositorio.buscarPeloId(id);
    }
}
