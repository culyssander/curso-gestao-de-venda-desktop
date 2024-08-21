
package com.culysoft.gestao.venda.modelo.servico;

import com.culysoft.gestao.venda.modelo.dto.VendaDto;
import com.culysoft.gestao.venda.modelo.repositorio.impl.VendaItemRepositorioImpl;
import com.culysoft.gestao.venda.modelo.repositorio.impl.VendaRepositorioImpl;
import java.util.List;

public class VendaServico {
    
    private VendaRepositorioImpl vendaRepositorioImpl;
    private VendaItemRepositorioImpl vendaItemRepositorioImpl;

    public VendaServico() {
        vendaRepositorioImpl = new VendaRepositorioImpl();
        vendaItemRepositorioImpl = new VendaItemRepositorioImpl();
    }
    
    public List<VendaDto> buscarTodos() {
        return vendaRepositorioImpl.encontrarTodosPersonalizado();
    }
    
}
