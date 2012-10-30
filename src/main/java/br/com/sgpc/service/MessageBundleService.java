package br.com.sgpc.service;

public interface MessageBundleService {
	
	/**
	 * Metodo para buscar as mensagens no bundle
	 * @param chave
	 * @param argumentos
	 * @return Mensagem encontrada
	 */
	public String recoveryMessage(final String chave, final Object... argumentos);
}
