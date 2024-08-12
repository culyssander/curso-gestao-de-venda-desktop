
package com.culysoft.gestao.venda.visao.componentes;

import com.culysoft.gestao.venda.visao.modelo.CartaoModelo;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class Cartao extends javax.swing.JPanel {

    private Color cor1;
    private Color cor2;
    
    public Cartao() {
        initComponents();
        setOpaque(false);
        cor1 = Color.BLACK;
        cor2 = Color.WHITE;
    }
    
    public void setData(CartaoModelo cartaoModelo) {
        labelCartaoIcon.setIcon(cartaoModelo.getIcon());
        labelCartaoTitulo.setText(cartaoModelo.getTitulo());
        labelCartaoValor.setText(cartaoModelo.getValor());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        GradientPaint gradientPaint = new GradientPaint(0, 0, cor1, 0, getHeight(), cor2);
        graphics2D.setPaint(gradientPaint);
        
        graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        
        graphics2D.setColor(new Color(255, 255, 255, 50));
        graphics2D.fillOval(getWidth() - (getHeight() / 2), 10, getHeight(), getHeight());
        graphics2D.fillOval(getWidth() - (getHeight()/2) -20, getHeight() / 2 + 20, getHeight(), getHeight());
        
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public Color getCor1() {
        return cor1;
    }

    public Color getCor2() {
        return cor2;
    }

    public void setCor1(Color cor1) {
        this.cor1 = cor1;
    }

    public void setCor2(Color cor2) {
        this.cor2 = cor2;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCartaoIcon = new javax.swing.JLabel();
        labelCartaoTitulo = new javax.swing.JLabel();
        labelCartaoValor = new javax.swing.JLabel();

        labelCartaoTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelCartaoTitulo.setForeground(new java.awt.Color(255, 255, 255));
        labelCartaoTitulo.setText("Titulo");

        labelCartaoValor.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        labelCartaoValor.setForeground(new java.awt.Color(255, 255, 255));
        labelCartaoValor.setText("Valor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCartaoTitulo)
                    .addComponent(labelCartaoValor)
                    .addComponent(labelCartaoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(170, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(labelCartaoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelCartaoTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelCartaoValor)
                .addContainerGap(29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelCartaoIcon;
    private javax.swing.JLabel labelCartaoTitulo;
    private javax.swing.JLabel labelCartaoValor;
    // End of variables declaration//GEN-END:variables
}
