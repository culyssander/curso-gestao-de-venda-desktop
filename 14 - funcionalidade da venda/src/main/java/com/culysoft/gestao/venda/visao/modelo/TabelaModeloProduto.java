
package com.culysoft.gestao.venda.visao.modelo;

import com.culysoft.gestao.venda.modelo.dto.ProdutoDto;
import com.culysoft.gestao.venda.modelo.entidade.Produto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelaModeloProduto extends AbstractTableModel {
    
    private final String [] colunas = {"ID", "Nome", "Descrição", "Preço", "Categoria"};
    private List<ProdutoDto> produtos;
    
    public TabelaModeloProduto(List<ProdutoDto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProdutoDto produto = produtos.get(rowIndex);
        
        switch(columnIndex) {
            case 0 -> { return produto.getId(); }
            case 1 -> { return produto.getNome(); }
            case 2 -> { return produto.getDescricao(); }
            case 3 -> { return produto.getPreco(); }
            case 4 -> { return produto.getCategoria(); }
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

    public List<ProdutoDto> getProdutos() {
        return produtos;
    }

}
