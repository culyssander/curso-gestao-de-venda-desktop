
package com.culysoft.gestao.venda.visao.modelo;

import com.culysoft.gestao.venda.modelo.dto.ProdutoDto;
import com.culysoft.gestao.venda.modelo.entidade.Categoria;
import com.culysoft.gestao.venda.modelo.entidade.Produto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelaModeloCategoria extends AbstractTableModel {
    
    private final String [] colunas = {"ID", "Nome", "Descrição", ""};
    private List<Categoria> categorias;
    
    public TabelaModeloCategoria(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public int getRowCount() {
        return categorias.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Categoria categoria = categorias.get(rowIndex);
        
        switch(columnIndex) {
            case 0 -> { return categoria.getId(); }
            case 1 -> { return categoria.getNome(); }
            case 2 -> { return categoria.getDescricao(); }
            case 3 -> { return "REMOVER"; }
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

    public List<Categoria> getCategorias() {
        return categorias;
    }

}
