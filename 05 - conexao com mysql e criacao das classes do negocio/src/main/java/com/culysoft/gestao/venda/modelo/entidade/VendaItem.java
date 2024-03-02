package com.culysoft.gestao.venda.modelo.entidade;

import java.math.BigDecimal;


public class VendaItem {
    private Long vendaId;
    private Long produtoId;
    private Integer quantidade;
    private BigDecimal desconto;
    private BigDecimal preco;
    private BigDecimal total;
}
