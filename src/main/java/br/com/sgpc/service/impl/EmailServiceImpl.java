package br.com.sgpc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.sgpc.model.Produto;
import br.com.sgpc.model.Usuario;
import br.com.sgpc.service.EmailService;
import br.com.sgpc.service.UsuarioService;

@Service( value = "emailService" )
@Scope("prototype")
public class EmailServiceImpl implements EmailService {

	private static final long serialVersionUID = 4356547273883961020L;
	
	@Resource(name = "usuarioService")
	private UsuarioService usuarioService;
	
	String emailSGV = "sgv.vendas@gmail.com";
	String senhaSGV = "vendassgv";
	
	@Override
	public void enviaEmailQuantidadeLimite(List<Usuario> administradores,
			Produto produto) throws EmailException {
		
		for(Usuario destinatario : administradores){
			SimpleEmail email = new SimpleEmail();  
	        email.setHostName("smtp.gmail.com");  
	        email.addTo(destinatario.getEmail(), destinatario.getNome());  
	        email.setFrom(emailSGV, "Sistema de Vendas");  
	        email.setMsg("Produto - Código: " + produto.getCodigo() + "\n" +
	        		"Descrição: " + produto.getDescricao() + "\n" +
	        		"Fornecedor: " + produto.getFornecedor().getRazaoSocial() + "\n" + 
	        		"Quantidade: " + produto.getQuantidade() + "\n" +
	        		"Quantidade limite: " + produto.getQuantidadeLimite());  
	        email.setSubject("Quantidade Limite - " + produto.getDescricao());  
	        email.setAuthenticator(new DefaultAuthenticator(emailSGV, senhaSGV));  
	        email.setSmtpPort(587);  
	        email.setSSL(true);  
	        email.setTLS(true);  
	        email.send();
		}
	}

	@Override
	public void enviaEmailNovaSenha(Usuario usuario) throws EmailException {
		
		String novaSenha = this.usuarioService.geraSenhaAleatoria();		
		SimpleEmail email = new SimpleEmail();  
        email.setHostName("smtp.gmail.com");  
        email.addTo(usuario.getEmail(), usuario.getNome());  
        email.setFrom("samir.daneu@gmail.com", "Sistema de Vendas");  
        email.setSubject(usuario.getNome() + ", sua nova senha para o login " + usuario.getLogin() + " é " + novaSenha);  
        email.setMsg("Alteração de senha");  
        email.setAuthenticator(new DefaultAuthenticator(emailSGV, senhaSGV));  
        email.setSmtpPort(587);  
        email.setSSL(true);  
        email.setTLS(true);  
        email.send();
		
	}

}
