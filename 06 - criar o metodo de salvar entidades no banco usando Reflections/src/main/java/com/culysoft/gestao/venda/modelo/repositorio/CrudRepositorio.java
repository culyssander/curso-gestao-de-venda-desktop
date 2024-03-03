package com.culysoft.gestao.venda.modelo.repositorio;

public interface CrudRepositorio<T> {
    
    public boolean salvar(T t);
    
}
