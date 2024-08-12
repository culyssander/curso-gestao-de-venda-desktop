
package com.culysoft.gestao.venda.visao.componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class CampoDeSenha extends JPasswordField {
    
    private String dicas;
    private Icon prefixoIcon;
    private Color cor;

    public CampoDeSenha() {
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.decode("#1D1B86"));
        setFont(new Font("sansserif", 0, 13));
        setSelectionColor(new Color(75, 175, 152));
        this.cor = new Color(28, 181, 223, 80);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(cor);
        graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        paintIcon(g);
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        
        if (getText().length() == 0) {
            int altura = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            g.setColor(new Color(200, 200, 200));
            g.drawString(dicas, ins.left, altura / 2 + fm.getAscent() / 2 - 2);
        }
    }
    
    private void paintIcon(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        
        if (prefixoIcon != null) {
            Image prefixo = ((ImageIcon) prefixoIcon).getImage();
            
            int altura = (getHeight() - prefixoIcon.getIconHeight()) /2;
            
            graphics2D.drawImage(prefixo, 10, altura, this);
        }
    }
    
    private void inicializarBordar() {
        int esquerda = 10;
        
        if (prefixoIcon != null) {
            esquerda = prefixoIcon.getIconWidth() + 15;
        }
        
        setBorder(BorderFactory.createEmptyBorder(10, esquerda, 10, 10));
    }

    public String getDicas() {
        return dicas;
    }

    public void setDicas(String dicas) {
        this.dicas = dicas;
    }

    public Icon getPrefixoIcon() {
        return prefixoIcon;
    }

    public void setPrefixoIcon(Icon prefixoIcon) {
        this.prefixoIcon = prefixoIcon;
        inicializarBordar();
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }    
    
}
