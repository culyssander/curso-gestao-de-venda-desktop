
package com.culysoft.gestao.venda.visao.componentes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Botao extends JButton {
    private Animator animator;
    private int tamanhoDoAlvo;
    private float tamanhoDaAnimator;
    private Point ponto;
    private float alfa;
    private Color corDeEfeito = new Color(255, 255, 255);

    public Botao() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(5, 0, 5, 0));
        setBackground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tamanhoDoAlvo = Math.max(getWidth(), getHeight() * 2);
                
                tamanhoDaAnimator = 0;
                ponto = e.getPoint();
                
                alfa = 0.5f;
                
                if (animator.isRunning()) {
                    animator.stop();
                }
                
                animator.start();
            }
            
        });
        
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (fraction > 0.5f) {
                    alfa = 1 - fraction;
                }
                
                tamanhoDaAnimator = fraction * tamanhoDoAlvo;
                repaint();
            }
            
        };
        
        animator = new Animator(700, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        int comprimento = getWidth();
        int altura = getHeight();
        
        BufferedImage img = new BufferedImage(comprimento, altura, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D graphics2D = img.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(getBackground());
        graphics2D.fillRoundRect(0, 0, comprimento, altura, altura, altura);
        
        if (ponto != null) {
            graphics2D.setColor(corDeEfeito);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alfa));
            graphics2D.fillOval((int) (ponto.x - tamanhoDaAnimator / 2), (int) (ponto.y - tamanhoDaAnimator / 2), 
                    (int) tamanhoDaAnimator, (int) tamanhoDaAnimator);
        }
        
        graphics2D.dispose();
        g.drawImage(img, 0, 0, null);
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
    
    
}
