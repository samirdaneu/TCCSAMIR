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
@ManagedBean(name = "menu")
@RequestScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = 8939415106345805141L;
	
	private Usuario usuario;

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
