package com.culysoft.gestao.venda.modelo.entidade;

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
public class Venda {
    private Long id;
    private BigDecimal totalVenda;
    private BigDecimal valarPago;
    private BigDecimal desconto;
    private BigDecimal troco;
    private Long clienteId;
    private Long usuarioId;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
}
