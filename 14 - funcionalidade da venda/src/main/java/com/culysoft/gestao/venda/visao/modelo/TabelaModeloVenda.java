
package com.culysoft.gestao.venda.visao.modelo;

import com.culysoft.gestao.venda.modelo.dto.VendaDto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelaModeloVenda extends AbstractTableModel {
    
    private final String [] colunas = {"ID", "Total", "Valor Pago", "Troco", "Desconto", "Cliente", "Usuario", "Data"};
    private List<VendaDto> vendas;
    
    public TabelaModeloVenda(List<VendaDto> vendas) {
        this.vendas = vendas;
    }

    @Override
    public int getRowCount() {
        return vendas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VendaDto venda = vendas.get(rowIndex);
        
        switch(columnIndex) {
            case 0 -> {return venda.getId();}
            case 1 -> {return venda.getTotalVenda();}
            case 2 -> {return venda.getValorPago();}
            case 3 -> {return venda.getTroco(); }
            case 4 -> {return venda.getDesconto(); }
            case 5 -> {return venda.getCliente(); }
            case 6 -> {return venda.getUsuario(); }
            case 7 -> {return venda.getDataCriacao(); }
        }
        
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public List<VendaDto> getVendas() {
        return vendas;
    }

    
}
