
package com.culysoft.gestao.venda.controlador;

import com.culysoft.gestao.venda.modelo.entidade.Usuario;
import com.culysoft.gestao.venda.modelo.servico.UsuarioServico;
import com.culysoft.gestao.venda.visao.componentes.Mensagem;
import com.culysoft.gestao.venda.visao.formulario.Dashboard;
import com.culysoft.gestao.venda.visao.formulario.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class LoginControlador implements ActionListener {
    
    private Login login;
    private UsuarioServico usuarioServico;

    public LoginControlador(Login login) {
        this.login = login;
        usuarioServico = new UsuarioServico();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String acao = e.getActionCommand().toLowerCase();
        
        switch(acao) {
            case "login" -> login();
        }
    }
    
    private void login() {
        validaCampo();
        String email = login.getCampoDeTextoEmail().getText();
        String senha = String.valueOf(login.getCampoDeSenha().getPassword());
        
        String mensagm = usuarioServico.login(email, senha);
        
        if (mensagm.startsWith("Email e Senha")) {
            login.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.SUCESSO, mensagm);
            login.getPanelCarregar().setVisible(true);
            Optional<Object> usuario = usuarioServico.buscarPeloEmail(email);
            
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                limpaCampo();
                login.setVisible(false);
                new Dashboard((Usuario) usuario.get()).setVisible(true);
                } catch (Exception e) {
                }
            }).start();
            
        } else {
            login.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, mensagm);
        }
    }
    
    private void validaCampo() {
        String email = login.getCampoDeTextoEmail().getText();
        String senha = String.valueOf(login.getCampoDeSenha().getPassword());
        
        if (email.isBlank() || senha.isBlank()) {
            String mensagem = "Email e Senha sao obrigatorio";
            login.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, mensagem);
            throw new RuntimeException(mensagem);
        }
    }
    
    private void limpaCampo() {
        login.getCampoDeTextoEmail().setText("");
        login.getCampoDeSenha().setText("");
    }
}
