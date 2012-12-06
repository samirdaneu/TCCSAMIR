package br.com.sgpc.service.impl;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.sgpc.service.MessageBundleService;

@Service(value = "messageBundleService" )
@Scope("prototype")
public class MessageBundleServiceImpl implements MessageBundleService {
	
	@Resource( name = "messageSource" )
	private MessageSource messageSource;
	
	public String recoveryMessage(final String chave, final Object... argumentos) {
		return messageSource.getMessage(chave, argumentos, Locale.getDefault());
	}
}
