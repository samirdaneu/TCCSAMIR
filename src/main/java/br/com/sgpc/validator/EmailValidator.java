package br.com.sgpc.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValidator implements Validator {
	
	private Pattern pattern;
	private Matcher matcher;
 
	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	private boolean validarEmail(final String hex) {
		 
		matcher = pattern.matcher(hex);
		return matcher.matches();
 
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		
		String email = value.toString();
		
		if(!email.isEmpty() && !validarEmail(value.toString())){
						
			FacesMessage message = new FacesMessage();
			
			message.setSeverity(FacesMessage.SEVERITY_ERROR);			
			message.setSummary("Email inválido!");			
			throw new ValidatorException(message);
		}	
			
	}
		
}


