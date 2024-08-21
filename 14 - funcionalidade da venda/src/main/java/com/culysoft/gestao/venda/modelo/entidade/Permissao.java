package com.culysoft.gestao.venda.modelo.entidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permissao {
    private Long id;
    private String nome;
    private String descricao;
}
