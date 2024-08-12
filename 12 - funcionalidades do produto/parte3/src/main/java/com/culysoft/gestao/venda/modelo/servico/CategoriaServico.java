
package com.culysoft.gestao.venda.modelo.servico;

import com.culysoft.gestao.venda.modelo.entidade.Categoria;
import com.culysoft.gestao.venda.modelo.repositorio.impl.CategoriaRepositorio;
import java.util.List;
import java.util.Optional;


public class CategoriaServico {
    private CategoriaRepositorio categoriaRepositorio;

    public CategoriaServico() {
        categoriaRepositorio = new CategoriaRepositorio();
    }
    
    public Optional<Categoria> buscarPeloId(Long id) {
        return categoriaRepositorio.buscarPeloId(id);
    }
    
    public List<Categoria> buscaTodos() {
        return categoriaRepositorio.buscarTodos();
    }
    
    public String salvar(Categoria categoria) {
        try {
            boolean resultado = categoriaRepositorio.salvar(categoria);
           
            if (resultado) return "Categoria Salvo com sucesso" ;
            else return "Error ao salvar";
        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate")) return categoria.getNome() + " ja existe no registo";
            
            return "error ao salvar";
        }
    }
    
    public void removerPeloId(Long id) {
        try {
            categoriaRepositorio.removerPeloId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Categoria> buscarPeloNome(String categoriaNome) {
        return categoriaRepositorio.buscaPeloNome(categoriaNome);
    }
}
 