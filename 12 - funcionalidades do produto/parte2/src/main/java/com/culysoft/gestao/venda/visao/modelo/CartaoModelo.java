
package com.culysoft.gestao.venda.visao.modelo;

import javax.swing.Icon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartaoModelo {
    
    private Icon icon;
    private String titulo;
    private String valor;
}
