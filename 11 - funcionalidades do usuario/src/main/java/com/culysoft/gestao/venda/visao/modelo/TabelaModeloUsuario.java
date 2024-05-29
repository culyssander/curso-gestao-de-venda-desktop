
package com.culysoft.gestao.venda.visao.modelo;

import com.culysoft.gestao.venda.modelo.entidade.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TabelaModeloUsuario extends AbstractTableModel {
    
    private final String [] colunas = {"ID", "Nome", "Email", "Perfil", "Estado"};
    private List<Usuario> usuarios;
    
    public TabelaModeloUsuario(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = usuarios.get(rowIndex);
        
        switch(columnIndex) {
            case 0 -> { return usuario.getId(); }
            case 1 -> { return usuario.getNome(); }
            case 2 -> { return usuario.getEmail(); }
            case 3 -> { return usuario.getPerfil(); }
            case 4 -> { return usuario.getEstado(); }
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
}
