package br.com.sgpc.service;

import java.io.Serializable;

public interface HttpClientService extends Serializable {
	
	/**
	 * Metodo que monta uma requisicao por "get" a uma determinada URL
	 * @param url
	 * @return
	 */
	String requisitarUrl(final String url);
}
