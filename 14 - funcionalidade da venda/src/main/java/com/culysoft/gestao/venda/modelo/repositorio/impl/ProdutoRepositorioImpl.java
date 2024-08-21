
package com.culysoft.gestao.venda.modelo.repositorio.impl;

import com.culysoft.gestao.venda.modelo.conexao.ConexaoMySQL;
import com.culysoft.gestao.venda.modelo.entidade.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProdutoRepositorioImpl extends CrudRepositorioImpl<Produto> {

    public ProdutoRepositorioImpl() {
        super(Produto.class);
    }

    public Optional<Produto> buscarPeloNome(String nome) {
        try {
            String SQL  = String.format("SELECT * FROM produto WHERE nome = ?");
            System.out.println("SQL " + SQL);
            
            PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(SQL);
            ps.setString(1, nome);
            
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()) {
                return Optional.ofNullable(getT(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return Optional.empty();
    }
    
    
    public List<Produto> buscaTodosPelaCategoria(String nome) {
        List<Produto> produtos = new ArrayList<>();
        try {
            String SQL  = String.format("SELECT p.* FROM produto p, categoria c WHERE p.categoriaId = c.id AND c.nome = ?");
            
            PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(SQL);
            ps.setString(1, nome);
            
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                produtos.add(getT(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return produtos;
    }
    
}
