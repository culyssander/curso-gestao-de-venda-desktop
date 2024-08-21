
package com.culysoft.gestao.venda.modelo.repositorio.impl;

import com.culysoft.gestao.venda.modelo.conexao.ConexaoMySQL;
import com.culysoft.gestao.venda.modelo.dto.VendaDto;
import com.culysoft.gestao.venda.modelo.entidade.Venda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VendaRepositorioImpl extends CrudRepositorioImpl<Venda>{

    public VendaRepositorioImpl() {
        super(Venda.class);
    }
    
    public List<VendaDto> encontrarTodosPersonalizado() {
        String SQL = "SELECT v.*, u.nome, c.cpf FROM venda v, cliente c, usuario u "
                + "WHERE v.clienteid = c.id AND v.usuarioId = u.id ORDER BY v.id";
        List<VendaDto> lista = new ArrayList<>();
        try {
            ResultSet resultSet = ConexaoMySQL.obterConexao()
                    .prepareStatement(SQL)
                    .executeQuery();
            
            while(resultSet.next()) {
                lista.add(getVendaResponseDto(resultSet));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
      
    private VendaDto getVendaResponseDto(ResultSet resultSet) throws SQLException {
        return VendaDto.builder()
                .id(resultSet.getLong("id"))
                .totalVenda(resultSet.getBigDecimal("totalVenda"))
                .troco(resultSet.getBigDecimal("troco"))
                .desconto(resultSet.getBigDecimal("desconto"))
                .cliente(resultSet.getString("cpf"))
                .dataCriacao(resultSet.getObject("dataCriacao", LocalDateTime.class))
                .ultimaActualizacao(resultSet.getObject("ultimaActualizacao", LocalDateTime.class))
                .usuario(resultSet.getString("nome"))
                .valorPago(resultSet.getBigDecimal("valorPago"))
                .vendaItemDtos(null)
                .build();
    }
}
