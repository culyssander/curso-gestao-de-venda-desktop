
package com.culysoft.gestao.venda.visao.componentes;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class Tabela extends JTable {

    public Tabela() {
        setShowHorizontalLines(true);
        setRowHeight(30);
        setGridColor(new Color(230,230,230));
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TabelaCabelho cabelho = new TabelaCabelho(value + "");
                return cabelho;
            }
            
        });
        
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (value != null) {
                    if (value == "REMOVER") return botao(String.valueOf(value));
                }
                
                if (isSelected){
                    component.setForeground(new Color(13, 113, 182));
                } else {
                    component.setForeground(new Color(102, 102, 102));
                }
                
                component.setBackground(Color.WHITE);
                return component;
            }
            
        });
    }
    
    
    public JButton botao(String texto) {
        BotaoContorno botao = new BotaoContorno();
        botao.setText(texto);
        botao.setForeground(new Color(204, 0, 0));
        botao.setBackground(new Color(204, 0, 0));
        return botao;
    }
    
}
