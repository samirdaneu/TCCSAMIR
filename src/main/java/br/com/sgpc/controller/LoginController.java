package br.com.sgpc.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Controller;

import br.com.sgpc.model.Usuario;
import br.com.sgpc.service.LoginService;
import br.com.sgpc.service.MessageBundleService;
import br.com.sgpc.service.UsuarioService;
import br.com.sgpc.util.FacesUtil;

/**
 * Controller com iterações com as telas relacionadas ao {@link LoginController}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Controller( value = "loginController" )
@SessionScoped
public class LoginController implements AlphaController {

	private static final long serialVersionUID = 3204266186679032413L;
	
	@Resource( name = "usuarioService" )
	private UsuarioService usuarioService;
	
	@Resource( name = "loginService" )
	private LoginService loginService;
	
	@Resource( name = "messageBundleService" )
	private MessageBundleService messageBundleService;
	
	private Usuario usuario;
	
	@Override
	@PostConstruct
	public void inicio() {
		usuario = new Usuario();
	}
	
	/**
	 * Metodo que valida se o usuario tem acesso ao sistema
	 * @return
	 */
	public String logar() {
		
		boolean existe = usuarioService.verificarSeLoginExiste( usuario.getLogin() );			
		if(existe) {			
			final Usuario usuarioBase = usuarioService.procurarUsuarioPeloLogin( usuario.getLogin() );
			if(!usuarioBase.isAtivo())
				FacesUtil.mensagemErro( messageBundleService.recoveryMessage("login_usuario_inativo") );
			
			if( loginService.senhasIguais(usuario.getSenha(), usuarioBase.getSenha()) ) {
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
