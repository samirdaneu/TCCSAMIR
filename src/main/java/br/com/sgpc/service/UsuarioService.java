package br.com.sgpc.service;

import java.io.Serializable;

import br.com.sgpc.model.Usuario;

/**
 * Service com regras de negocio para {@link Usuario}
 * @author Guilherme Gambeti
 * @since 25/10/2012
 *
 */
public interface UsuarioService extends Serializable {
	
	/**
	 * Metodo que verifica se o usuario existe a partir do login
	 * @param login - Login do usuário
	 * @return {@link Boolean#TRUE} caso o este login exista na base, caso contrário false
	 */
	boolean verificarSeLoginExiste(final String login);
	
	/**
	 * metodo que busca um usuario pelo login
	 * @param login
	 * @return Usuario, caso exista
	 */
	Usuario procurarUsuarioPeloLogin(final String login);
}
