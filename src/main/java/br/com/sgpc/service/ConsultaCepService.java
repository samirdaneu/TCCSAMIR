package br.com.sgpc.service;

import java.io.Serializable;

import br.com.sgpc.exception.TechnicalException;
import br.com.sgpc.model.to.Cep;

public interface ConsultaCepService extends Serializable {
	
	/**
	 * Metodo que efetua a consulta do cep em um serviço de cep
	 * @param cep
	 * @return
	 */
	Cep consultarCep(final String cep) throws TechnicalException;
}
