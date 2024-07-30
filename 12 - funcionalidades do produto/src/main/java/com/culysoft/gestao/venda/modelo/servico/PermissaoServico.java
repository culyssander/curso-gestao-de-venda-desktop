
package com.culysoft.gestao.venda.modelo.servico;

import com.culysoft.gestao.venda.modelo.entidade.Permissao;
import com.culysoft.gestao.venda.modelo.entidade.PermissaoUsuario;
import com.culysoft.gestao.venda.modelo.repositorio.impl.PermissaoRepositorioImpl;
import com.culysoft.gestao.venda.modelo.repositorio.impl.PermissaoUsuarioRepositorioImpl;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

public class PermissaoServico {
    
    private PermissaoRepositorioImpl permissaoRepositorioImpl;
    private PermissaoUsuarioRepositorioImpl permissaoUsuarioRepositorioImpl;

    public PermissaoServico() {
        permissaoRepositorioImpl = new PermissaoRepositorioImpl();
        permissaoUsuarioRepositorioImpl = new PermissaoUsuarioRepositorioImpl();
    }
    
    public void validaPermissao(Long permissaoId, Long usuarioId) {
        PermissaoUsuario permissaoUsuario = PermissaoUsuario.builder()
                .permissaoId(permissaoId)
                .usuarioId(usuarioId)
                .build();
        Optional<PermissaoUsuario> lista = buscarPermissaoUsuario(permissaoUsuario);
        if(lista.isEmpty()) {
            String mensagem = "Sem Permissao";
            JOptionPane.showMessageDialog(null, mensagem, mensagem, JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(mensagem);
        }
    }
    
    public Optional<PermissaoUsuario> buscarPermissaoUsuario(PermissaoUsuario permissaoUsuario) {
       return permissaoUsuarioRepositorioImpl.buscarPeloPermissaoIdEUsuarioId(permissaoUsuario);
    }
    
    
    public List<Permissao> buscaTodasPermissoes() {
        return permissaoRepositorioImpl.buscarTodos();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public List<PermissaoUsuario> buscarTodosPermissaoUsuario(Long usuarioId){
        return permissaoUsuarioRepositorioImpl.buscarPeloUsuarioId(usuarioId);
    }
    
    public Optional<Permissao> buscarPermissaoPeloId(Long permissaoId) {
        return permissaoRepositorioImpl.buscarPeloId(permissaoId);
    }
    
    
    public boolean salva(PermissaoUsuario permissaoUsuario) {
        System.out.println("PermissaoUsuario: " + permissaoUsuario);
        return permissaoUsuarioRepositorioImpl.salvar(permissaoUsuario);
    }
    
    public Optional<Permissao> buscarPermissaoPeloNome(String nome) {
        return permissaoRepositorioImpl.buscarPeloNome(nome);
    }
    
    public boolean remover(PermissaoUsuario permissaoUsuario) {
        return permissaoUsuarioRepositorioImpl.removerPeloId(permissaoUsuario);
    }
}
