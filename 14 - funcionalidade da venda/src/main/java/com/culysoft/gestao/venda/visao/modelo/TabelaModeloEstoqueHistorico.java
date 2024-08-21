
package com.culysoft.gestao.venda.visao.modelo;

import com.culysoft.gestao.venda.modelo.entidade.EstoqueHistorico;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelaModeloEstoqueHistorico extends AbstractTableModel {
    
    private final String [] colunas = {"ID", "Nome", "Quantidade", "Estado", "Motivo", "Data"};
    private List<EstoqueHistorico> estoques;
    
    public TabelaModeloEstoqueHistorico(List<EstoqueHistorico> estoques) {
        this.estoques = estoques;
    }

    @Override
    public int getRowCount() {
        return estoques.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EstoqueHistorico estoque = estoques.get(rowIndex);
        
        switch(columnIndex) {
            case 0 -> { return estoque.getId(); }
            case 1 -> { return estoque.getProduto(); }
            case 2 -> { return estoque.getQuantidade(); }
            case 3 -> { return estoque.getEstado(); }
            case 4 -> { return estoque.getObservacao(); }
            case 5 -> { return estoque.getDataCriacao(); }
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

    public List<EstoqueHistorico> getEstoques() {
        return estoques;
    }


}
