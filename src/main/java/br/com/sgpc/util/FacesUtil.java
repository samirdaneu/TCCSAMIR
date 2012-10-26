package br.com.sgpc.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class FacesUtil {
	
	/**
	 * Metodo interno utilitário para criacao de {@link FacesMessage}
	 * @param mensagem
	 * @param severity
	 */
	private static void mensagem(final String mensagem, final Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, mensagem, null));		
	}
	
	/**
	 * Metodo para obter uma {@link FacesMessage#SEVERITY_INFO}
	 * @param mensagem
	 */
	public static void mensagemInformacao(String mensagem){
		mensagem(mensagem, FacesMessage.SEVERITY_INFO);
	}
	
	/**
	 * Metodo para obter uma {@link FacesMessage#SEVERITY_ERROR}
	 * @param mensagem
	 */
	public static void mensagemErro(String mensagem){
		mensagem(mensagem, FacesMessage.SEVERITY_ERROR);
	}
}
