
package com.culysoft.gestao.venda.modelo.repositorio.impl;

import com.culysoft.gestao.venda.modelo.conexao.ConexaoMySQL;
import com.culysoft.gestao.venda.modelo.entidade.Permissao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class PermissaoRepositorioImpl extends CrudRepositorioImpl {

    public PermissaoRepositorioImpl() {
        super(Permissao.class);
    }
    
    
    
    public Optional<Permissao> buscarPeloNome(String nome) {
        System.out.println("Nome: " + nome);
        try {
            String sql = "select * from permissao where nome = ?";
            
            PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(sql);
            
            ps.setString(1, nome);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                System.out.println("Resultset: " + rs.getString(1));
                return Optional.ofNullable((Permissao)getT(rs));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }
}
