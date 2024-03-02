
package com.culysoft.gestao.venda.modelo.entidade;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Estoque {
    private Long id;
    private Long produtoId;
    private Integer quantidade;
    private Boolean estado;
    private Long usuarioId;
    private LocalDateTime dataCriacao;
}
