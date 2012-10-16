package br.com.sgpc.utilidades;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtilidades {
	
	private static void mensagem(String mensagem, FacesMessage.Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, mensagem, null));		
	}
	
	public static void mensagemInformação(String mensagem){
		mensagem(mensagem, FacesMessage.SEVERITY_INFO);
	}
	
	public static void mensagemErro(String mensagem){
		mensagem(mensagem, FacesMessage.SEVERITY_ERROR);
	}

	

}
