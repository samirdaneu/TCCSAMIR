package br.com.sgpc.service;

import java.io.Serializable;

import br.com.sgpc.exception.TechnicalException;
import br.com.sgpc.model.to.Cep;

public interface ConsultaCepService extends Serializable {
	
	Cep consultarCep(final String cep) throws TechnicalException;
}
