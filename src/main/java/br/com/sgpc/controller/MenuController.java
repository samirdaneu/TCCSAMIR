package br.com.sgpc.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.sgpc.model.Usuario;

/**
 * Controller com iterações com as telas relacionadas ao {@link MenuController}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@ManagedBean(name = "menuController")
@RequestScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = 8939415106345805141L;
	
	private Usuario usuario;
	
	public String consultarUsuarios(){
		return "/usuario/mostrarUsuarios";
	}
	
	public String consultarProdutos(){
		return "/produto/mostrarProdutos";
	}
	
	public String consultarFornecedores(){
		return "/fornecedor/mostrarFornecedores";
	}
	
	public String consultarMovimentacoes(){
		return "/movimentacao/mostrarMovimentacoes";
	}
	
	public String consultarPedidos(){
		return "/pedido/mostrarPedidos";
	}
	
	public String consultarVendas(){
		return "/relatorio/formRelatorioVendas";
	}
	
	public String cadastrarProduto(){
		return "/produto/formProduto";
	}
	
	public String cadastrarFornecedor(){
		return "/fornecedor/formFornecedor";
	}
	
	public String cadastrarUsuario(){
		return "/usuario/formUsuario";
	}
	
	public String cadastrarMovimentacao(){
		return "/movimentacao/formMovimentacao";
	}
	
	public String alterarSenha(){
		return "/usuario/formAlterarSenha";
	}
	
	public String cadastrarPedido(){
		return "/pedido/formPedido";
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
