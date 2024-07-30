
package com.culysoft.gestao.venda.visao.util;

import com.culysoft.gestao.venda.visao.componentes.Mensagem;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MensagemUtil {
    
    private final JLayeredPane background;
    private final MigLayout layout;

    public MensagemUtil(JLayeredPane background, MigLayout layout) {
        this.background = background;
        this.layout = layout;
    }
    
    public void mostrarMensagem(Mensagem.TipoMensagem tipo, String mensagem) {
        Mensagem ms = new Mensagem();
        
        ms.mostrarMensagem(tipo, mensagem);
        
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isMostra()) {
                    background.add(ms, "pos 0.al 20", 0);
                    ms.setVisible(true);
                    background.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                
                if (ms.isMostra()) {
                    f = 20 * (1f - fraction);
                } else {
                    f = 20 * fraction;
                }
                
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 20));
                background.repaint();
                background.revalidate();
            }

            @Override
            public void end() {
                if (ms.isMostra()) {
                    background.remove(ms);
                    background.repaint();
                    background.revalidate();
                } else {
                    ms.setMostra(true);
                }
            }            
          
        };
        
        
        Animator animator = new Animator(300, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
        animator.start();
        
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }).start();
    }
    
}
