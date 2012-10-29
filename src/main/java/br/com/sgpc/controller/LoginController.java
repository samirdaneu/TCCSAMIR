package br.com.sgpc.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Controller;

import br.com.sgpc.model.Usuario;
import br.com.sgpc.service.LoginService;
import br.com.sgpc.service.UsuarioService;

/**
 * Controller com iterações com as telas relacionadas ao {@link LoginController}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Controller( value = "login" )
@ViewScoped
public class LoginController implements AlphaController {

	private static final long serialVersionUID = 3204266186679032413L;
	
	@Resource( name = "usuarioService" )
	private UsuarioService usuarioService;
	
	@Resource( name = "loginService" )
	private LoginService loginService;
	
	private Usuario usuario;
	private String resultado;
	
	public LoginController() { }
	
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
			if( loginService.senhasIguais(usuario.getSenha(), usuarioBase.getSenha()) ) {
				return "logon_success";
			}
		} 
		resultado = "Login ou senha inválido(a)";
		return "";	
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
