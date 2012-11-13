package br.com.sgpc.dao;

import java.util.List;

import br.com.sgpc.model.Usuario;

/**
 * Dao com iteracoes com banco de dados para a entidade {@link Usuario}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
public interface UsuarioDao extends GenericDao<Usuario, Integer> {
	
	boolean verificarSeLoginExiste(final String login);

	List<Usuario> buscarAdministradoresAtivos();
	
	Usuario procurarUsuarioPeloLogin(final String login);
	
}
