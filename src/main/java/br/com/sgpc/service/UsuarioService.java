package br.com.sgpc.service;

import java.util.List;

import br.com.sgpc.dao.GenericDao;
import br.com.sgpc.model.Usuario;

public interface UsuarioService extends GenericDao<Usuario, Integer> {
	
	boolean verificarSeLoginExiste(final String login);
	
	Usuario procurarUsuarioPeloLogin(final String login);
	
	List<Usuario> buscarAdministradoresAtivos();
	
	String geraSenhaAleatoria();
	
	boolean isAdmin(Usuario usuario);
	
}
