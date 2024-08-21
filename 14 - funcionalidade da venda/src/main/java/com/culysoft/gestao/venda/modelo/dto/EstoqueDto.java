package com.culysoft.gestao.venda.modelo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EstoqueDto {
    private Long id;
    private String produto;
    private Integer quantidade;
    private BigDecimal preco;
    private Boolean estado;
    private LocalDateTime dataCriacao;
}
