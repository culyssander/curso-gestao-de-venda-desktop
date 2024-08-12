
package com.culysoft.gestao.venda.visao.formulario.produto;

import com.culysoft.gestao.venda.visao.formulario.FormularioProduto;
import com.culysoft.gestao.venda.visao.util.MensagemUtil;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Tela extends javax.swing.JPanel {

    private Capa capa;
    private ProdutoCategoria produtoCategoria;
    private MigLayout layout;
    private boolean estaEmCategoria;
    
    private double tamanhoCapa = 30;
    private double tamanhoProdutoCategoria = 67;
    private double tamanhoAdicional = 35;
    private DecimalFormat decimalFormat = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MensagemUtil mensagemUtil;
    
    public Tela(FormularioProduto formularioProduto) {
        initComponents();
        capa = new Capa(formularioProduto);
        produtoCategoria = new ProdutoCategoria(formularioProduto);
        inicializacao();
        mensagemUtil = new MensagemUtil(background, layout);
    }

    public MensagemUtil getMensagemUtil() {
        return mensagemUtil;
    }
    
    public ProdutoCategoria getProdutoCategoria() {
        return produtoCategoria;
    }
    
    private void inicializacao() {
        layout = new MigLayout("fill, insets");
        
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                
                System.out.println("FRACTION " + fraction);
                double fractionCapa;
                double fractionProdutoCategoria;
                double tamanho = tamanhoAdicional;
//                double tamanhoProdutoCategoria = this.tamanhoProdutoCategoria;
                
                if (fraction <= 0.5f) {
                    tamanho += fraction * tamanhoAdicional;
                } else {
                    tamanho += tamanho - fraction * tamanhoAdicional;
                }
                
                if (estaEmCategoria) {
                    fractionCapa = 1f - fraction;
                    fractionProdutoCategoria = fraction;
                    
                    
                    if (fraction >= 0.5) {
                        capa.produtoDireita( fractionCapa * 100);
                    } else {
                        capa.categoriaDireita(fractionProdutoCategoria * 100);
                    }
                } else {
                    fractionCapa = fraction;
                    fractionProdutoCategoria = 1f - fraction;
                    
                    if (fraction <= 0.5f) {
                        capa.produtoEsquerda(fraction * 100);
                    } else {
                        capa.categoriaEsquerda((1f-fraction) * 100);
                    }
                }

                if (fraction >= 0.5f)
                    produtoCategoria.mostrarProduto(estaEmCategoria);
                
                fractionCapa = Double.parseDouble(decimalFormat.format(fractionCapa));
                fractionProdutoCategoria = Double.parseDouble(decimalFormat.format(fractionProdutoCategoria));
                
                layout.setComponentConstraints(capa, "width " + tamanho + "%, pos " + fractionCapa + "al 0 n 100%" );
                layout.setComponentConstraints(produtoCategoria, "width " + tamanhoProdutoCategoria + "%, pos " + fractionProdutoCategoria + "al 0 n 100%");
                background.revalidate();
            }

            @Override
            public void end() {
                estaEmCategoria = !estaEmCategoria;
            }
        };
        
        Animator animator = new Animator(800, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
        
        
        background.setLayout(layout);
        background.add(capa, "width " + tamanhoCapa + "%, pos 0al 0 n 100%");
        background.add(produtoCategoria, "width " + tamanhoProdutoCategoria + "%, pos 1al 0 n 100%");
        
        capa.evento(e -> {
            if (!animator.isRunning())
                animator.start();
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JLayeredPane();

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane background;
    // End of variables declaration//GEN-END:variables
}
