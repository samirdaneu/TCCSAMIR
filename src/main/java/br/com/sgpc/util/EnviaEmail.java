package br.com.sgpc.util;

import java.net.MalformedURLException;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.sgpc.model.Produto;
import br.com.sgpc.model.Usuario;

public class EnviaEmail {
	
	public EnviaEmail(List<Usuario> administradores, Produto produto) throws EmailException, MalformedURLException {  
        enviaEmailSimples(administradores, produto);       
    }
	
	public void enviaEmailSimples(List<Usuario> administradores, Produto produto) throws EmailException {  
		
		String usuario = "samir.daneu@gmail.com";
		String senha = "xxxxxxxxx";
        
		for(Usuario destinatario : administradores){
			SimpleEmail email = new SimpleEmail();  
	        email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail  
	        email.addTo(destinatario.getEmail(), destinatario.getNome()); //destinatário  
	        email.setFrom("samir.daneu@gmail.com", "Sistema de Vendas"); // remetente  
	        email.setSubject("Produto - Código: " + produto.getCodigo() + " Descrição: " + produto.getDescricao() + 
	        		"Chegou a sua quantidade limite - Quantidade: " + produto.getQuantidade() +
	        		"Quantidade limite: " + produto.getQuantidadeLimite());  
	        email.setMsg("Aviso de produtos com quantidade no limite"); //conteudo do e-mail  
	        email.setAuthenticator(new DefaultAuthenticator(usuario, senha));  
	        email.setSmtpPort(587);  
	        email.setSSL(true);  
	        email.setTLS(true);  
	        email.send();
		}
		
             
    }  

}
