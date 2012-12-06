package br.com.sgpc.session.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import br.com.sgpc.model.Usuario;

@Component( value = "loginSession" )
public class LoginSession implements Serializable{

	private static final long serialVersionUID = -2680275619040922680L;
	
	private Usuario usuarioLogado;

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
