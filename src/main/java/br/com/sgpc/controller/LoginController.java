package br.com.sgpc.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sgpc.model.Usuario;
import br.com.sgpc.service.LoginService;
import br.com.sgpc.service.MessageBundleService;
import br.com.sgpc.service.UsuarioService;
import br.com.sgpc.session.bean.LoginSession;
import br.com.sgpc.util.FacesUtil;

@Controller( value = "loginController" )
@Scope("request")
public class LoginController implements AlphaController {

	private static final long serialVersionUID = 3204266186679032413L;
	
	@Resource( name = "usuarioService" )
	private UsuarioService usuarioService;
	
	@Resource( name = "loginService" )
	private LoginService loginService;
	
	@Resource( name = "messageBundleService" )
	private MessageBundleService messageBundleService;
	
	@Resource(name = "loginSession")
	private LoginSession loginSession;
	
	private Usuario usuario;
	
	@Override
	@PostConstruct
	public void inicio() {
		usuario = new Usuario();
	}
	
	public String logar() {
		
		boolean existe = usuarioService.verificarSeLoginExiste( usuario.getLogin() );			
		if(existe) {			
			final Usuario usuarioBase = usuarioService.procurarUsuarioPeloLogin( usuario.getLogin() );
			if(!usuarioBase.isAtivo())
				FacesUtil.mensagemErro( messageBundleService.recoveryMessage("login_usuario_inativo") );
			
			if( loginService.senhasIguais(usuario.getSenha(), usuarioBase.getSenha()) ) {
				loginSession.setUsuarioLogado( usuarioBase );
				return "pedido/formPedido";
			}
		}
		
		FacesUtil.mensagemErro( messageBundleService.recoveryMessage("login_senha_invalido") );
		return null;
	}
	
	public String limparCampos() {
		inicio();
        return "sucesso";  
    }
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public void setMessageBundleService(MessageBundleService messageBundleService) {
		this.messageBundleService = messageBundleService;
	}
}
