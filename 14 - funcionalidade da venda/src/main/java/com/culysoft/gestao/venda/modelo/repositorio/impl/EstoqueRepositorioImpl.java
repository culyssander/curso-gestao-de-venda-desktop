
package com.culysoft.gestao.venda.modelo.repositorio.impl;

import com.culysoft.gestao.venda.modelo.conexao.ConexaoMySQL;
import com.culysoft.gestao.venda.modelo.entidade.Estoque;
import com.culysoft.gestao.venda.modelo.entidade.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class EstoqueRepositorioImpl extends CrudRepositorioImpl<Estoque>{

    public EstoqueRepositorioImpl() {
        super(Estoque.class);
    }
    
   // select campos from estoque, produto e.produtoId = p.id
    
    public Optional<Estoque> buscarPeloProdutoId(Long produtoId) {
        try {
            String SQL  = String.format("SELECT * FROM estoque WHERE produtoid = ?");
            System.out.println("SQL " + SQL);
            
            PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(SQL);
            ps.setLong(1, produtoId);
            
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()) {
                return Optional.ofNullable(getT(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return Optional.empty();
    }
}
