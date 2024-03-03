package com.culysoft.gestao.venda.modelo.entidade;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EstoqueHistorico {
    private Long id;
    private String produto;
    private Integer quantidade;
    private String estado;
    private String observacao;
    private String usuarioId;
    private LocalDateTime dataCriacao;
}
