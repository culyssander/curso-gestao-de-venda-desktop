
package com.culysoft.gestao.venda.modelo.repositorio.impl;

import com.culysoft.gestao.venda.modelo.conexao.ConexaoMySQL;
import com.culysoft.gestao.venda.modelo.entidade.PermissaoUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PermissaoUsuarioRepositorioImpl extends CrudRepositorioImpl<PermissaoUsuario>{

    public PermissaoUsuarioRepositorioImpl() {
        super(PermissaoUsuario.class);
    }
    
    public List<PermissaoUsuario> buscarPeloUsuarioId(Long usuarioId) {
        List<PermissaoUsuario> lista = new ArrayList<>();
        try {
            String sql = "select pu.* from usuario u, permissao p, permissaousuario pu\n" +
                        "WHERE pu.permissaoId = p.id  AND pu.usuarioId = ?";
            
            PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(sql);
            
            ps.setLong(1, usuarioId);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                lista.add(getT(rs));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
    
    public  Optional<PermissaoUsuario>  buscarPeloPermissaoIdEUsuarioId(PermissaoUsuario permissaoUsuario) {
        try {
            String sql = "select pu.* from usuario u, permissao p, permissaousuario pu\n" +
                        "WHERE pu.permissaoId = ?  AND pu.usuarioId = ?";
            
            PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(sql);
            
            ps.setLong(1, permissaoUsuario.getPermissaoId());
            ps.setLong(2, permissaoUsuario.getUsuarioId());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                return Optional.ofNullable(getT(rs));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return Optional.empty();
    }
    
    public boolean salvar(PermissaoUsuario permissaoUsuario) {
        try {
            String SQL = String.format("INSERT INTO permissaousuario (permissaoId, usuarioId) VALUES(?, ?)");

            PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(SQL);

            ps.setLong(1, permissaoUsuario.getPermissaoId());
            ps.setLong(2, permissaoUsuario.getUsuarioId());

            int resultado = ps.executeUpdate();

            return resultado == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean removerPeloId(PermissaoUsuario permissaoUsuario) {
        try {
            String SQL = String.format("DELETE FROM permissaousuario WHERE permissaoId = ? and "
                    + "usuarioId = ?");

            PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(SQL);

            ps.setLong(1, permissaoUsuario.getPermissaoId());
            ps.setLong(2, permissaoUsuario.getUsuarioId());

            int resultado = ps.executeUpdate();

            return resultado == 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    
}
