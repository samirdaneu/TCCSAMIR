package br.com.sgpc.service;

import java.io.Serializable;

/**
 * Service com regras de negocio relacionadas a login
 * @author Guilherme Gambeti
 * @since 25/10/2012
 *
 */
public interface LoginService extends Serializable {
	
	/**
	 * Metodo que conter a regra de comparacao de senha // TODO: Talvez um criptografia, num futuro
	 * @param senha
	 * @param senhaBase
	 * @return
	 */
	boolean senhasIguais(final String senha, final String senhaBase);
}

