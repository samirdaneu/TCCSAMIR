package br.com.sgpc.service;

import java.io.Serializable;

public interface LoginService extends Serializable {
	
	boolean senhasIguais(final String senha, final String senhaBase);
}

