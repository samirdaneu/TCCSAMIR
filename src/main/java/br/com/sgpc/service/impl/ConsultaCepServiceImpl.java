package br.com.sgpc.service.impl;

import org.springframework.stereotype.Service;

import br.com.sgpc.model.to.Cep;
import br.com.sgpc.service.ConsultaCepService;

@Service( value = "consultaCepService" )
public class ConsultaCepServiceImpl implements ConsultaCepService {

	private static final long serialVersionUID = 7133279405790296529L;

	@Override
	public Cep consultarCep(final String cep) {
		return null;
	}
}
