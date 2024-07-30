
package com.culysoft.gestao.venda.controlador;

import com.culysoft.gestao.venda.modelo.servico.ProdutoServico;
import com.culysoft.gestao.venda.visao.formulario.FormularioProduto;
import com.culysoft.gestao.venda.visao.modelo.TabelaModeloProduto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FormularioProdutoControlador implements ActionListener {
    
    private FormularioProduto formularioProduto;
    private ProdutoServico produtoServico;
    private TabelaModeloProduto tabelaModeloProduto;

    public FormularioProdutoControlador(FormularioProduto formularioProduto) {
        this.formularioProduto = formularioProduto;
        
        produtoServico = new ProdutoServico();
        actualizaTabelaProduto();
    }
    
    private void actualizaTabelaProduto() {
        tabelaModeloProduto = new TabelaModeloProduto(produtoServico.buscaTodos());
        formularioProduto.getTabelaProduto().setModel(tabelaModeloProduto);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String acao = e.getActionCommand().toLowerCase();
        
        switch(acao) {
            case "adicionar" -> { mostrarTelaDeCadastroProdutoCategoria(); }
        }
    }
    
    private void mostrarTelaDeCadastroProdutoCategoria() {
        formularioProduto.getDashboard().setForm(formularioProduto.getTela());
    }
    
    
}
