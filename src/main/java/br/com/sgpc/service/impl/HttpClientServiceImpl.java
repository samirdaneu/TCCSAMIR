package br.com.sgpc.service.impl;

import org.apache.http.client.HttpClient;
import org.springframework.stereotype.Service;

import br.com.sgpc.service.HttpClientService;

@Service( value = "httpClientService" )
public class HttpClientServiceImpl implements HttpClientService {

	private static final long serialVersionUID = -8813057339686734869L;

	@Override
	public String requisitarUrl(final String url) {
		return null;
	}
	
	private HttpClient prepararConexao() {
		return null;
	}
}
