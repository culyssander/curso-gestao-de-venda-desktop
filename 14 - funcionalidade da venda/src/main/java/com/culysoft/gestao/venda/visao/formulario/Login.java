
package com.culysoft.gestao.venda.visao.formulario;

import com.culysoft.gestao.venda.controlador.LoginControlador;
import com.culysoft.gestao.venda.visao.componentes.Botao;
import com.culysoft.gestao.venda.visao.componentes.CampoDeSenha;
import com.culysoft.gestao.venda.visao.componentes.CampoDeTexto;
import com.culysoft.gestao.venda.visao.componentes.PanelCarregar;
import com.culysoft.gestao.venda.visao.util.MensagemUtil;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;

public class Login extends javax.swing.JFrame {

    private MigLayout layout;
    private PanelCarregar panelCarregar;
    private MensagemUtil mensagemUtil;
    private LoginControlador loginControlador;
    
    private int x;
    private int y;

    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        loginControlador = new LoginControlador(this);
        
        layout = new MigLayout("fill, insets");
        panelCarregar = new PanelCarregar();
        
        background.setLayout(layout);
        background.add(panelCarregar, "pos 0 0 100% 100%");
        background.add(panelBoard1, "pos 0 0 100% 100%");
        
        mensagemUtil = new MensagemUtil(background, layout);
        evento();
        moveTelaLogin(this);
        fechaTela();
    }
    
    private void moveTelaLogin(JFrame frame) {
        panelMovimento.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = getX();
                y = getY();
            }
        });
        
        panelMovimento.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
            }
        });
    }
    
    private void fechaTela() {
        labelFechar.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                int opcao = JOptionPane.showConfirmDialog(null, "Tens certeza?", "Sair", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
        
        labelFechar.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                labelFechar.setBackground(Color.RED);
                labelFechar.setForeground(Color.WHITE);
                labelFechar.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelFechar.setOpaque(false);
                labelFechar.setForeground(Color.WHITE);
            }
         });
    }
    
    private void evento() {
        botaoLogin.addActionListener(loginControlador);
    }
    public MensagemUtil getMensagemUtil() {
        return mensagemUtil;
    }

    public PanelCarregar getPanelCarregar() {
        return panelCarregar;
    }

    public CampoDeSenha getCampoDeSenha() {
        return campoDeSenha;
    }

    public CampoDeTexto getCampoDeTextoEmail() {
        return campoDeTextoEmail;
    }

    public Botao getBotaoLogin() {
        return botaoLogin;
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBoard1 = new com.culysoft.gestao.venda.visao.componentes.PanelBoard();
        panelMovimento = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelFechar = new javax.swing.JLabel();
        campoDeTextoEmail = new com.culysoft.gestao.venda.visao.componentes.CampoDeTexto();
        campoDeSenha = new com.culysoft.gestao.venda.visao.componentes.CampoDeSenha();
        jSeparator1 = new javax.swing.JSeparator();
        botaoLogin = new com.culysoft.gestao.venda.visao.componentes.Botao();
        background = new javax.swing.JLayeredPane();

        panelBoard1.setCor1(new java.awt.Color(255, 255, 255));
        panelBoard1.setCor2(new java.awt.Color(67, 137, 162));

        panelMovimento.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-de-venda\\src\\main\\java\\com\\culysoft\\gestao\\venda\\visao\\icon\\logo 39x45.png")); // NOI18N
        jLabel1.setText("SOFT");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        labelFechar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelFechar.setForeground(new java.awt.Color(255, 255, 255));
        labelFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFechar.setText("X");

        javax.swing.GroupLayout panelMovimentoLayout = new javax.swing.GroupLayout(panelMovimento);
        panelMovimento.setLayout(panelMovimentoLayout);
        panelMovimentoLayout.setHorizontalGroup(
            panelMovimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovimentoLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(labelFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelMovimentoLayout.setVerticalGroup(
            panelMovimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovimentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelMovimentoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        campoDeTextoEmail.setCor(new java.awt.Color(255, 255, 255));
        campoDeTextoEmail.setDicas("Email");
        campoDeTextoEmail.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-de-venda\\src\\main\\java\\com\\culysoft\\gestao\\venda\\visao\\icon\\mail.png")); // NOI18N

        campoDeSenha.setCor(new java.awt.Color(255, 255, 255));
        campoDeSenha.setDicas("Senha");
        campoDeSenha.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-de-venda\\src\\main\\java\\com\\culysoft\\gestao\\venda\\visao\\icon\\pass.png")); // NOI18N

        botaoLogin.setBackground(new java.awt.Color(28, 181, 224));
        botaoLogin.setForeground(new java.awt.Color(255, 255, 255));
        botaoLogin.setIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-de-venda\\src\\main\\java\\com\\culysoft\\gestao\\venda\\visao\\icon\\login_16.png")); // NOI18N
        botaoLogin.setText("LOGIN");
        botaoLogin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout panelBoard1Layout = new javax.swing.GroupLayout(panelBoard1);
        panelBoard1.setLayout(panelBoard1Layout);
        panelBoard1Layout.setHorizontalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMovimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoard1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoDeTextoEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoDeSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
                .addGap(78, 78, 78))
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(botaoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBoard1Layout.setVerticalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addComponent(panelMovimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(campoDeTextoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoDeSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 62, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane background;
    private com.culysoft.gestao.venda.visao.componentes.Botao botaoLogin;
    private com.culysoft.gestao.venda.visao.componentes.CampoDeSenha campoDeSenha;
    private com.culysoft.gestao.venda.visao.componentes.CampoDeTexto campoDeTextoEmail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelFechar;
    private com.culysoft.gestao.venda.visao.componentes.PanelBoard panelBoard1;
    private javax.swing.JPanel panelMovimento;
    // End of variables declaration//GEN-END:variables
}
