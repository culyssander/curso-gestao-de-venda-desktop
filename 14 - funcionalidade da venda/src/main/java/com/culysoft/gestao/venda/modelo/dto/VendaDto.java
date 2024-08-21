
package com.culysoft.gestao.venda.modelo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VendaDto {
    private Long id;
    private BigDecimal totalVenda;
    private BigDecimal valorPago;
    private BigDecimal troco;
    private BigDecimal desconto;
    private String cliente;
    private String usuario;
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimaActualizacao;
    private List<VendaItemDto> vendaItemDtos;
}
