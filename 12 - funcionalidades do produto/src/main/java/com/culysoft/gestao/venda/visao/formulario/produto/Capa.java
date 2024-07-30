
package com.culysoft.gestao.venda.visao.formulario.produto;

import com.culysoft.gestao.venda.visao.componentes.BotaoContorno;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;


public class Capa extends javax.swing.JPanel {

    private JLabel volta;
    private JLabel titulo;
    private JLabel descricao;
    private BotaoContorno botaoContorno;
    private boolean estaEmCategoria;
    private ActionListener actionListener;
    private DecimalFormat decimalFormat = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MigLayout layout;

    public Capa() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");
        setLayout(layout);
        inicializacao();
    }
    
    private void inicializacao() {
        String caminho = "\\src\\main\\java\\com\\culysoft\\gestao\\venda\\visao\\icon\\esquerda.png";
        
        volta = new JLabel();
        volta.setOpaque(false);
        volta.setIcon(new ImageIcon(System.getProperty("user.dir") + caminho));
        volta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        volta.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("CLICOU PRA VOLTAR NA TELA DE LISTAGEM DE PRODUTO");
            }
        });
        
        add(volta, "w 40%");
        
        titulo = new JLabel("Categoria");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("sansserif", 1, 30));
        add(titulo);
        
        descricao = new JLabel("Todas as informações");
        descricao.setForeground(Color.WHITE);
        add(descricao);
        
        botaoContorno = new BotaoContorno();
        botaoContorno.setText("CATEGORIA");
        botaoContorno.setBackground(Color.WHITE);
        botaoContorno.setForeground(Color.WHITE);
        botaoContorno.addActionListener(e -> actionListener.actionPerformed(e));
        add(botaoContorno, "w 50%, h 30");
    }
    
    
    private void categoria(boolean bool) {
        if (estaEmCategoria != bool) {
            if (bool) {
                titulo.setText("Produto");
                descricao.setText("Cadastro e alteracao de Produto");
                botaoContorno.setText("PRODUTO");
            } else {
                titulo.setText("Categoria");
                descricao.setText("Todas as informações");
                botaoContorno.setText("CATEGORIA");
            }
            
            this.estaEmCategoria = bool;
        }
    }
    
    public void produtoEsquerda(double v) {
        v = Double.parseDouble(decimalFormat.format(v));
        categoria(false);
        layout.setComponentConstraints(titulo, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(descricao, "pad 0 -" + v + "% 0 0");
        
    }
    
    public void produtoDireita(double v) {
        v = Double.parseDouble(decimalFormat.format(v));
        categoria(false);
        layout.setComponentConstraints(titulo, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(descricao, "pad 0 -" + v + "% 0 0");
    }
    
    public void categoriaEsquerda(double v) {
        v = Double.parseDouble(decimalFormat.format(v));
        categoria(true);
        layout.setComponentConstraints(titulo, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(descricao, "pad 0 " + v + "% 0 " + v + "%");
    }
    
    public void categoriaDireita(double v) {
        v = Double.parseDouble(decimalFormat.format(v));
        categoria(true);
        layout.setComponentConstraints(titulo, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(descricao, "pad 0 " + v + "% 0 " + v + "%");
    }

 
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        GradientPaint gradientPaint = new GradientPaint(0, 0, Color.decode("#1CB5E0"), 0, getHeight(), Color.decode("#000046"));
        graphics2D.setPaint(gradientPaint);
        
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    public void evento(ActionListener e) {
        this.actionListener = e;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
