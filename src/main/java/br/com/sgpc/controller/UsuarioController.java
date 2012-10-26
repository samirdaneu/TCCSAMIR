package br.com.sgpc.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.sgpc.dao.GenericDao;
import br.com.sgpc.model.Usuario;
import br.com.sgpc.util.FacesUtil;

/**
 * Controller com iterações com as telas relacionadas ao {@link UsuarioController}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@ManagedBean(name = "usuarioController")
@RequestScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = -2558847076842976054L;
	
	private Usuario usuario;
	
	private GenericDao<Usuario, Integer> usuarioDAO;
	
	private DataModel model;
	
	public UsuarioController(){
		this.setUsuario(new Usuario());
	}
	
	public String novoUsuario(){
		this.setUsuario(new Usuario());
		return "formUsuario";
	}
	
	public DataModel listarUsuarios(){
		model = new ListDataModel(this.usuarioDAO.buscarTodos());
		return model;
	}
	
	public String salvarUsuario(){
		try {
			if (getUsuario().getId() == null){
				usuarioDAO.salvar(getUsuario());
				FacesUtil.mensagemInformacao("Usu�rio cadastrado com sucesso!");
			} else {
				usuarioDAO.atualizar(getUsuario());
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
		this.usuarioDAO.excluir(usuario);
		return "mostrarUsuarios";
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
