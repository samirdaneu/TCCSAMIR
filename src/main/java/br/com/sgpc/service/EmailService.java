package br.com.sgpc.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.mail.EmailException;

import br.com.sgpc.model.Produto;
import br.com.sgpc.model.Usuario;

public interface EmailService extends Serializable {
	
	public void enviaEmailQuantiddeLimite(List<Usuario> administradores, Produto produto) throws EmailException;
	
	public void enviaEmailNovaSenha(Usuario usuario) throws EmailException;

}
