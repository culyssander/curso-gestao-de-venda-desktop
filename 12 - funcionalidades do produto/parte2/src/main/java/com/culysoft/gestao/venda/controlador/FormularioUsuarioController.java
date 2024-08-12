package com.culysoft.gestao.venda.controlador;

import com.culysoft.gestao.venda.modelo.entidade.Permissao;
import com.culysoft.gestao.venda.modelo.entidade.PermissaoUsuario;
import com.culysoft.gestao.venda.modelo.entidade.Usuario;
import com.culysoft.gestao.venda.modelo.servico.PermissaoServico;
import com.culysoft.gestao.venda.modelo.servico.UsuarioServico;
import com.culysoft.gestao.venda.visao.formulario.FormularioUsuario;
import com.culysoft.gestao.venda.visao.modelo.TabelaModeloUsuario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FormularioUsuarioController implements ActionListener, MouseListener {
    
    private FormularioUsuario formularioUsuario;
    private UsuarioServico usuarioServico;
    private PermissaoServico permissaoServico;
    private TabelaModeloUsuario tabelaModeloUsuario;
    private List<Usuario> usuarios;
    private String urlFoto;
    private Long actualizaUsuario;
    private Long usuarioLogado;
    private Usuario usuario;
    
    private final long PERMISSAO_ID_PARA_SALVAR_USUARIO = 1;
    private final long PERMISSAO_ID_PARA_SOMENTE_TEU = 2;
    private final long PERMISSAO_ID_BUSCAR_TODOS = 3;
    private final long PERMISSAO_ID_REMOVE = 4;
    private final long PERMISSAO_ID_PARA_SALVAR_PERMISSAO = 16;
    

    public FormularioUsuarioController(FormularioUsuario formularioUsuario) {
        this.formularioUsuario = formularioUsuario;
        usuarioServico = new UsuarioServico();
        permissaoServico = new PermissaoServico();
        this.usuarioLogado = formularioUsuario.getUsuarioId();
        actualizarTabela();
    }
    
    private void actualizarTabela() {
        
        // se o usuario logado tem permissao de todos - 
        
        
        usuarios = usuarioServico.buscaTodos();
        tabelaModeloUsuario = new TabelaModeloUsuario(usuarios);
        formularioUsuario.getTabelaUsuarios().setModel(tabelaModeloUsuario);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String acao = e.getActionCommand().toLowerCase();
        System.out.println(acao);
        switch(acao) {
            case "adicionar" -> { mostrarTela("ADICIONAR USUÁRIO"); }
            case "actualizar" -> { actualizaTela("ACTUALIZAR USUÁRIO"); }
            case "salvar" -> { salvarUsuario(); }
            case "permissao" -> { permissao(); }
            case "salvarpermissao" -> { salvarPermissao(); }
            case "seleciona" -> { escolherArquivo(); }
            case "remover" -> { remover(); }
        }
    }
    
    private void mostrarTela(String titulo) {
        permissaoServico.validaPermissao(PERMISSAO_ID_PARA_SALVAR_USUARIO, usuarioLogado);
        limpaCampoUsuario();
        limpaMensagem();
        formularioUsuario.getDialogUsuario().pack();
        formularioUsuario.getDialogUsuario().setLocationRelativeTo(null);
        formularioUsuario.getDialogUsuario().setVisible(true);
        formularioUsuario.getLabelTitulo().setText(titulo);
    }
    
    private void actualizaTela(String titulo) {
        permissaoServico.validaPermissao(PERMISSAO_ID_PARA_SALVAR_USUARIO, usuarioLogado);
        if (usuario == null){
            String mensagem = "Deves seleciona o usuario na tabela";
            JOptionPane.showMessageDialog(null, mensagem, "Seleciona a tabela", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(mensagem);
        }
            
        limpaMensagem();
        formularioUsuario.getDialogUsuario().pack();
        formularioUsuario.getDialogUsuario().setLocationRelativeTo(null);
        formularioUsuario.getDialogUsuario().setVisible(true);
        formularioUsuario.getLabelTitulo().setText(titulo);
        preencherFormularioDeCadastro(usuario);
    }
    
    private void salvarUsuario() {
        String nome = formularioUsuario.getTxtNome().getText();
        String email = formularioUsuario.getTxtEmail().getText();
        String senha = formularioUsuario.getTxtSenha().getText();
        String perfil = formularioUsuario.getComboPerfil().getSelectedItem().toString();
        System.out.println("SENHA:" + senha);
        validacaoTexto(nome);
        validacaoTexto(email);
        validacaoTexto(senha);
        validacaoPerfil();
        
        try {
            Usuario usuario = Usuario.builder()
                    .id(actualizaUsuario)
                    .nome(nome)
                    .email(email)
                    .senha(senha)
                    .perfil(perfil)
                    .foto(urlFoto)
                    .estado(formularioUsuario.getRadioAtivo().isSelected())
                    .dataCriacao(LocalDateTime.now())
                    .build();
            
            boolean resultado = usuarioServico.salvar(usuario);
            
            if (resultado) {
                mensagem(true, "Usuário salvo com sucesso!!!");
                limpaCampoUsuario();
            }
            else
                mensagem(false, "Erro ao salvar usuário");
        } catch (Exception e) {
            System.out.println(e);
            mensagem(false, e.getMessage());
        }
    }
    
    private void validacaoTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            String mensagem = "Todos campos são obrigatorio";
            mensagem(false, mensagem);
            throw new RuntimeException(mensagem);
        }
    }
    
    private void validacaoPerfil() {
        if (formularioUsuario.getComboPerfil().getSelectedIndex() < 1) {
            String mensagem = "É Obrigatorio seleciona o perfil";
            mensagem(false, mensagem);
            throw new RuntimeException(mensagem);
        }
    }
    
    private void mensagem(boolean bool, String mensagem) {
        if (bool) 
            formularioUsuario.getLabelMensagem().setBackground(Color.decode("#45B649"));
        else
            formularioUsuario.getLabelMensagem().setBackground(Color.decode("#93291E"));
        
        formularioUsuario.getLabelMensagem().setOpaque(true);
        formularioUsuario.getLabelMensagem().setText(mensagem);
    }
    
    private void limpaMensagem() {
        formularioUsuario.getLabelMensagem().setBackground(Color.WHITE);
        formularioUsuario.getLabelMensagem().setOpaque(false);
    }

    private void escolherArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        int retorno = fileChooser.showDialog(null, "Seleciona");
        
        if (retorno == JFileChooser.APPROVE_OPTION) {
            File file  = fileChooser.getSelectedFile().getAbsoluteFile();
            urlFoto = file.getAbsolutePath();
            formularioUsuario.getTxtFoto().setText(urlFoto);
        }
    }
    
    private void limpaCampoUsuario() {
       formularioUsuario.getTxtNome().setText("");
       formularioUsuario.getTxtEmail().setText("");
       formularioUsuario.getTxtSenha().setText("");
       formularioUsuario.getTxtFoto().setText("");
       formularioUsuario.getComboPerfil().setSelectedIndex(0);
       formularioUsuario.getRadioAtivo().setSelected(true);
       urlFoto = "";
       actualizaUsuario = null;
       actualizarTabela();
    }
    
    private void preencherFormularioDeCadastro(Usuario usuario) {
       actualizaUsuario = usuario.getId();
       formularioUsuario.getTxtNome().setText(usuario.getNome());
       formularioUsuario.getTxtEmail().setText(usuario.getEmail());
       formularioUsuario.getTxtSenha().setText("");
       formularioUsuario.getTxtFoto().setText(usuario.getFoto());
       
       if (usuario.getPerfil().equalsIgnoreCase("admin"))
            formularioUsuario.getComboPerfil().setSelectedIndex(1);
       else
           formularioUsuario.getComboPerfil().setSelectedIndex(2);
       
       if (usuario.getEstado())
           formularioUsuario.getRadioAtivo().setSelected(true);
       else
           formularioUsuario.getRadioDesativo().setSelected(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {    }

    @Override
    public void mouseReleased(MouseEvent e) {  
        int linha = formularioUsuario.getTabelaUsuarios().getSelectedRow();
        usuario = usuarios.get(linha);
        actualizaUsuario = usuario.getId();
//        preencherFormularioDeCadastro(usuario);
    }

    @Override
    public void mouseEntered(MouseEvent e) {    }

    @Override
    public void mouseExited(MouseEvent e) {    }
    
    
    public void remover() {
        permissaoServico.validaPermissao(PERMISSAO_ID_REMOVE, usuarioLogado);
        if (usuario == null){
            String mensagem = "Deves seleciona o usuario na tabela";
            JOptionPane.showMessageDialog(null, mensagem, "Seleciona a tabela", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(mensagem);
        }
        
        int confirma = JOptionPane.showConfirmDialog(null, "Tens certeza que desejas remove?\n\n"
                + "Nome: " + usuario.getNome(), "Remover usuario", JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            boolean resultado = usuarioServico.remover(usuario.getId());
            if (resultado) {
                JOptionPane.showMessageDialog(null, "Usuario removido com Sucesso!");
                actualizarTabela();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao remover usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void permissao() {
        permissaoServico.validaPermissao(PERMISSAO_ID_PARA_SALVAR_PERMISSAO, usuarioLogado);
        if (actualizaUsuario == null) {
            String mensagem = "Tens que seleciona um usuario na tabela";
            JOptionPane.showMessageDialog(null, mensagem);
            throw new RuntimeException(mensagem);
        }
        limpaPermissoes();
        preencherPermissao();
        
        formularioUsuario.getDialogPermissao().pack();
        formularioUsuario.getDialogPermissao().setLocationRelativeTo(null);
        formularioUsuario.getDialogPermissao().setVisible(true);
        
    }
    
    private void limpaPermissoes() {
        List<JCheckBox> lista = formularioUsuario.listaChecks();
        
        for(JCheckBox checkBox : lista) {
            checkBox.setSelected(false);
        }
    }



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    



private void preencherPermissao() {
        List<JCheckBox> lista = formularioUsuario.listaChecks();

        Map<String, JCheckBox> map = new HashMap<>();

        for (JCheckBox checkBox : lista) {
            map.put(checkBox.getName(), checkBox);
        }

        permissaoServico.buscarTodosPermissaoUsuario(actualizaUsuario)
                .stream()
                .forEach(p -> {
                    Optional<Permissao> permissaoOp = permissaoServico.buscarPermissaoPeloId(p.getPermissaoId());

                    if (permissaoOp.isPresent()) {
                        Permissao permissao = permissaoOp.get();
                        JCheckBox checkBox = map.get(permissao.getNome());

                        if (checkBox != null) {
                            checkBox.setToolTipText(permissao.getDescricao());
                            checkBox.setSelected(true);
                        }
                    }

                });
    }

private void salvarPermissao() {
        Long usuarioId = actualizaUsuario;
        List<JCheckBox> checkBoxs = formularioUsuario.listaChecks();

        List<PermissaoUsuario> adicionaPermissao = new ArrayList<>();
        List<PermissaoUsuario> removePermissao = new ArrayList<>();

        for (JCheckBox checkBox : checkBoxs) {
            String nome = checkBox.getName();

            Optional<Permissao> permissoes = permissaoServico.buscarPermissaoPeloNome(nome);
            System.out.println(permissoes);
            if (permissoes.isPresent()) {
                Permissao permissao = permissoes.get();
                PermissaoUsuario permissaoUsuario = PermissaoUsuario.builder()
                        .permissaoId(permissao.getId())
                        .usuarioId(usuarioId)
                        .build();

                if (checkBox.isSelected()) {
                    adicionaPermissao.add(permissaoUsuario);
                } else {
                    removePermissao.add(permissaoUsuario);
                }
            }
        }
                adicionaPermissao.stream()
                .filter(pu -> permissaoServico.buscarPermissaoUsuario(pu).isEmpty())
                .forEach(pu -> permissaoServico.salva(pu));

        removePermissao.stream()
                .filter(pu -> permissaoServico.buscarPermissaoUsuario(pu).isPresent())
                .forEach(pu -> permissaoServico.remover(pu));

        JOptionPane.showMessageDialog(null, "Permissão alterado com sucesso!");
    }

}
