package com.culysoft.gestao.venda.modelo.repositorio;

import java.util.List;
import java.util.Optional;

public interface CrudRepositorio<T> {
    
    public boolean salvar(T t);
    public List<T> buscarTodos();
    public Optional<T> buscarPeloId(Long id);
    public boolean removerPeloId(Long id);
    
}
