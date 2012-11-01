package br.com.sgpc.controller;

import java.util.ArrayList;
import java.util.List;

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
	
	private static final String ADMINISTRADOR = "Administrador";
	private static final String VENDEDOR = "Vendedor";
	
	private Usuario usuario;
	
	private String tipoUsuarioSelecionado;
	
	private List<String> listaTiposUsuario;
	
	@Resource( name = "usuarioService" )
	private UsuarioService usuarioService;
	
	@Resource( name = "messageBundleService" )
	private MessageBundleService messageBundleService;
	
	private DataModel model;
	
	private String senhaConfirmacao;
	
	public UsuarioController(){}	
	
	@Override
	@PostConstruct
	public void inicio() {
		usuario = new Usuario();
		setListaTiposUsuario(new ArrayList<String>());
		getListaTiposUsuario().add(ADMINISTRADOR);
		getListaTiposUsuario().add(VENDEDOR);
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
				if(tipoUsuarioSelecionado.equals(ADMINISTRADOR)){
					usuario.setTipoUsuario(Usuario.TipoUsuario.ADMINISTRADOR);
				} else {
					usuario.setTipoUsuario(Usuario.TipoUsuario.VENDEDOR);
				}
				usuarioService.salvar(getUsuario());
				FacesUtil.mensagemErro(messageBundleService
						.recoveryMessage("login_senha_invalido"));
			} else {
				usuarioService.atualizar(getUsuario());
				FacesUtil.mensagemErro(messageBundleService
						.recoveryMessage("login_senha_invalido"));
			}
		} catch (Exception e) {
			FacesUtil.mensagemErro(messageBundleService
					.recoveryMessage("login_senha_invalido"));
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

	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao = senhaConfirmacao;
	}

	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}

	public void setTipoUsuarioSelecionado(String tipoUsuarioSelecionado) {
		this.tipoUsuarioSelecionado = tipoUsuarioSelecionado;
	}

	public String getTipoUsuarioSelecionado() {
		return tipoUsuarioSelecionado;
	}

	public void setListaTiposUsuario(List<String> listaTiposUsuario) {
		this.listaTiposUsuario = listaTiposUsuario;
	}

	public List<String> getListaTiposUsuario() {
		return listaTiposUsuario;
	}

}
