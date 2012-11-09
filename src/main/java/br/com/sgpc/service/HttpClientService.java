package br.com.sgpc.service;

import java.io.Serializable;

import br.com.sgpc.exception.TechnicalException;

public interface HttpClientService extends Serializable {
	
	String requisitarUrl(final String url) throws TechnicalException;
}
