package br.com.sgpc.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.sgpc.exception.TechnicalException;
import br.com.sgpc.model.to.Cep;
import br.com.sgpc.service.ConsultaCepService;
import br.com.sgpc.service.HttpClientService;
import br.com.sgpc.service.JsonService;
import br.com.sgpc.service.MessageBundleService;

@Service( value = "consultaCepService" )
@Scope("prototype")
public class ConsultaCepServiceImpl implements ConsultaCepService {

	private static final long serialVersionUID = 7133279405790296529L;
	
	@Resource(name = "messageBundleService")
	private MessageBundleService messageBundleService;
	
	@Resource(name = "httpClientService")
	private HttpClientService httpClientService;
	
	@Resource(name = "jsonService")
	private JsonService jsonService;
	
	private final static Integer CODIGO_RETORNO_SUCESSO = 1;
	
	@Override
	public Cep consultarCep(final String cep) throws TechnicalException {
		
		final String token = messageBundleService.recoveryMessage( "token_servico_cep" );
		final String estiloResposta = messageBundleService.recoveryMessage( "estilo_resposta_servico_cep" );
		final String url = messageBundleService.recoveryMessage("url_servico_cep", token, cep, estiloResposta);
		
		final String resultadoConsulta = httpClientService.requisitarUrl( url );
		
		if(StringUtils.isBlank( resultadoConsulta )) {
			throw new TechnicalException( messageBundleService.recoveryMessage("retorno_consulta_cep_vazio") );
		}
		
		return transformJsonIn( resultadoConsulta );
	}
	
	/**
	 * Metodo para transformar o retorno json em {@link Cep}
	 * @param json
	 * @return
	 * @throws TechnicalException
	 */
	private Cep transformJsonIn(final String json) throws TechnicalException {
		
		jsonService.serializerJson( json );
		
		final Integer codigoResposta = jsonService.getInteger( "responseCode" );
		if(codigoResposta != CODIGO_RETORNO_SUCESSO) {
			throw new TechnicalException( messageBundleService.recoveryMessage("codigo_retorno_erro", codigoResposta) );
		}
		
		return new Cep(jsonService.getString( "bairro" ), jsonService.getString( "cep" ), jsonService.getString( "cidade" ),
				       jsonService.getString( "estado" ), jsonService.getString( "logradouro" ),
				       jsonService.getString( "regiao" ));
	}
	
	public void setMessageBundleService(MessageBundleService messageBundleService) {
		this.messageBundleService = messageBundleService;
	}

	public void setHttpClientService(HttpClientService httpClientService) {
		this.httpClientService = httpClientService;
	}

	public void setJsonService(JsonService jsonService) {
		this.jsonService = jsonService;
	}
}
