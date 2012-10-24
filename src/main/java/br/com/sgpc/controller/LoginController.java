package br.com.sgpc.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.sgpc.dao.GenericoDAO;
import br.com.sgpc.model.Usuario;

@ManagedBean(name = "login")
@RequestScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 2864294559845602900L;
	
	@Resource
	private GenericoDAO<Usuario, Integer> usuarioDAO;
	
	private Usuario usuario;
	
	private String resultado;
	
	public LoginController() {
		this.setUsuario(new Usuario());
	}	
	
	private Usuario verificaLogin(String login){
		String query = "select u from Usuario u where u.login = :login ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("login", login);		
		return this.usuarioDAO.pesquisarObjetoPorParametro(query, params);
	}
	
	public String logar(){
		Usuario u = verificaLogin(usuario.getLogin());
		
		if(u == null){
			this.setResultado("Usuário/Senha inválido");
		} else {
			if(u != null && u.getSenha().equals(usuario.getSenha())){
				return "logon_success";
			} else {
				this.setResultado("Usuário/Senha inválido");
			}
		}
		
		return "";	
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}
}
