
package com.culysoft.gestao.venda.visao.componentes;

import com.culysoft.gestao.venda.visao.modelo.MenuModelo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class MenuItem extends javax.swing.JPanel {
    
    private boolean selecionado;
    private boolean over;

    public MenuItem(MenuModelo menuModelo) {
        initComponents();
        setOpaque(false);
        
        switch(menuModelo.getTipoMenu()) {
            case MENU -> {
                labelMenuItemIcon.setIcon(menuModelo.toIcon());
                labelMenuItemNome.setText(menuModelo.getNome());
            }
            case TITULO -> {
                labelMenuItemIcon.setText(menuModelo.getNome());
                labelMenuItemIcon.setFont(new Font("sanserif", 1, 12));
                labelMenuItemIcon.setForeground(Color.WHITE);
                labelMenuItemNome.setVisible(false);
            }
            case VAZIO -> {
               labelMenuItemNome.setText(""); 
            }
        }
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
        repaint();
    }

    public void setOver(boolean over) {
        this.over = over;
        repaint();
    }
    
    

    @Override
    protected void paintComponent(Graphics g) {
        
        if (selecionado || over) {
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (selecionado) {
                graphics2D.setColor(new Color(255, 255, 255, 80));
            } else {
                graphics2D.setColor(new Color(255, 255, 255, 80));
            }
            graphics2D.fillRoundRect(10, 0, getWidth() -20, getHeight(), 5, 5);
        }
        
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelMenuItemIcon = new javax.swing.JLabel();
        labelMenuItemNome = new javax.swing.JLabel();

        labelMenuItemNome.setForeground(new java.awt.Color(255, 255, 255));
        labelMenuItemNome.setText("MenuItem");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(labelMenuItemIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelMenuItemNome, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelMenuItemIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelMenuItemNome, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelMenuItemIcon;
    private javax.swing.JLabel labelMenuItemNome;
    // End of variables declaration//GEN-END:variables
}
