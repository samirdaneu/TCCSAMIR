package br.com.sgpc.service.impl;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.sgpc.service.JsonService;
import br.com.sgpc.service.MessageBundleService;

@Service( value = "jsonService" )
@Scope("prototype")
public class JsonServiceImpl implements JsonService {

	private static final long serialVersionUID = 8317856856610897879L;
	
	@Resource(name = "messageBundleService")
	private MessageBundleService messageBundleService;
	
	private JSONObject json;
	
	@Override
	public void serializerJson(final String jsonString) {
		json = (JSONObject) JSONSerializer.toJSON( jsonString );		
	}

	@Override
	public String getString(final String node) {
		
		if(json == null) {
			throw new IllegalArgumentException( messageBundleService.recoveryMessage("aviso_json_nao_serializado") );
		}
		
		return json.getString( node );
	}

	@Override
	public Integer getInteger(final String node) {
		
		if(json == null) {
			throw new IllegalArgumentException( messageBundleService.recoveryMessage("aviso_json_nao_serializado") );
		}
		
		return json.getInt( node );
	}

	@Override
	public Double getDouble(final String node) {
		
		if(json == null) {
			throw new IllegalArgumentException( messageBundleService.recoveryMessage("aviso_json_nao_serializado") );
		}
		
		return json.getDouble( node );
	}
	
	public void setMessageBundleService(MessageBundleService messageBundleService) {
		this.messageBundleService = messageBundleService;
	}
}
