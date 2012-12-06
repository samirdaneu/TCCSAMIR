package br.com.sgpc.dao;

import java.util.List;

import br.com.sgpc.model.Usuario;

public interface UsuarioDao extends GenericDao<Usuario, Integer> {
	
	boolean verificarSeLoginExiste(final String login);

	List<Usuario> buscarAdministradoresAtivos();
	
	Usuario procurarUsuarioPeloLogin(final String login);
	
	boolean isAdmin(Usuario usuario);
	
}
