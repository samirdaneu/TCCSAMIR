package br.com.sgpc.service.impl;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.sgpc.exception.TechnicalException;
import br.com.sgpc.service.HttpClientService;

@Service( value = "httpClientService" )
@Scope("prototype")
public class HttpClientServiceImpl implements HttpClientService {

	private static final long serialVersionUID = 4975730915478065254L;

	@Override
	public String requisitarUrl(final String url) throws TechnicalException {
		
		String resultado = null;
		
		try {
			
			final HttpClient client = new DefaultHttpClient();
			final HttpGet get = new HttpGet( url );
			final HttpResponse response = client.execute( get );
			
			resultado = IOUtils.toString( response.getEntity().getContent() );
			
		} catch (ClientProtocolException e) {
			throw new TechnicalException(e);
			
		} catch (IOException e) {
			throw new TechnicalException(e);
		}
		
		return resultado;
	}
}
