package br.com.sgpc.service;

import java.io.Serializable;

public interface MessageBundleService extends Serializable {
	
	/**
	 * Metodo para buscar as mensagens no bundle
	 * @param chave
	 * @param argumentos
	 * @return Mensagem encontrada
	 */
	public String recoveryMessage(final String chave, final Object... argumentos);
}
