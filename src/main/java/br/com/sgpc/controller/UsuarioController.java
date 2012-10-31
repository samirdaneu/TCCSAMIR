package br.com.sgpc.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.stereotype.Controller;

import br.com.sgpc.model.Usuario;
import br.com.sgpc.service.MessageBundleService;
import br.com.sgpc.service.UsuarioService;
import br.com.sgpc.util.FacesUtil;

/**
 * Controller com iterações com as telas relacionadas ao {@link UsuarioController}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Controller( value = "usuarioController" )
@RequestScoped
public class UsuarioController implements AlphaController {

	private static final long serialVersionUID = -2558847076842976054L;
	
	private Usuario usuario;
	
	@Resource( name = "usuarioService" )
	private UsuarioService usuarioService;
	
	@Resource( name = "messageBundleService" )
	private MessageBundleService messageBundleService;
	
	private DataModel model;
	
	public UsuarioController(){}	
	
	@Override
	@PostConstruct
	public void inicio() {
		usuario = new Usuario();		
	}
	
	public String novoUsuario(){
		this.setUsuario(new Usuario());
		return "formUsuario";
	}
	
	public DataModel listarUsuarios(){
		model = new ListDataModel(this.usuarioService.buscarTodos());
		return model;
	}
	
	public String salvarUsuario(){
		try {
			if (getUsuario().getId() == null){
				usuario.setAtivo(true);
				usuarioService.salvar(getUsuario());
				FacesUtil.mensagemInformacao("Usu�rio cadastrado com sucesso!");
			} else {
				usuarioService.atualizar(getUsuario());
				FacesUtil.mensagemInformacao("Usu�rio cadastrado com sucesso!");
			}
		} catch (Exception e) {
			FacesUtil.mensagemErro("Erro ao salvar/atualizar usu�rio");
			e.printStackTrace();
		}			
		
		return "ok";
	}
	
	public Usuario getUsuarioParaEditarExcluir(){
		Usuario usuario = (Usuario) model.getRowData();
		return usuario;
	}
	
	public String editar(){
		setUsuario(getUsuarioParaEditarExcluir());
		return "formUsuario";
	}
	
	public String excluir(){
		Usuario usuario = getUsuarioParaEditarExcluir();
		this.usuarioService.excluir(usuario);
		return "mostrarUsuarios";
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}	
}
