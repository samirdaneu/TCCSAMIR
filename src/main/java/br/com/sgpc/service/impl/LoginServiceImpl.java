package br.com.sgpc.service.impl;

import org.springframework.stereotype.Service;

import br.com.sgpc.service.LoginService;

/**
 * Implementacao de {@link LoginService}
 * @author Guilherme Gambeti
 * @since 25/10/2012
 *
 */
@Service( value = "loginService" )
public class LoginServiceImpl implements LoginService {

	private static final long serialVersionUID = 4910026451945303018L;

	@Override
	public boolean senhasIguais(String senha, String senhaBase) {
		
		if(senha == null || senhaBase == null) {
			throw new IllegalArgumentException(); // TODO: colocar bundle
		}
		
		return senha.equals( senhaBase );
	}
}
