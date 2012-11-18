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

@Controller(value = "usuarioController")
@RequestScoped
public class UsuarioController implements AlphaController {

	private static final long serialVersionUID = -2558847076842976054L;

	private static final String ADMINISTRADOR = "Administrador";
	private static final String VENDEDOR = "Vendedor";

	private Usuario usuario;

	private String tipoUsuarioSelecionado;

	private List<String> listaTiposUsuario;

	@Resource(name = "usuarioService")
	private UsuarioService usuarioService;

	@Resource(name = "messageBundleService")
	private MessageBundleService messageBundleService;

	private DataModel<Usuario> model;

	private String senhaConfirmacao;

	public UsuarioController() {
	}

	@Override
	@PostConstruct
	public void inicio() {
		usuario = new Usuario();
		setModel(listarUsuarios());
		setListaTiposUsuario(new ArrayList<String>());
		getListaTiposUsuario().add(ADMINISTRADOR);
		getListaTiposUsuario().add(VENDEDOR);
	}

	public String novoUsuario() {
		this.setUsuario(new Usuario());
		return "formUsuario";
	}

	public DataModel<Usuario> listarUsuarios() {
		setModel(new ListDataModel<Usuario>(this.usuarioService.buscarTodos()));
		return getModel();
	}

	private boolean validaLogin(Usuario usuario) {
		boolean existe = false;
		if (usuarioService.verificarSeLoginExiste(usuario.getLogin())) {
			existe = true;
		}
		return existe;
	}

	private boolean validaSenhasIguais(Usuario usuario) {
		boolean iguais = false;
		if (usuario.getSenha().equals(senhaConfirmacao)) {
			iguais = true;
		}
		return iguais;
	}

	public String salvarUsuario() {

		String destino = "";

		if (getUsuario().getId() == null && validaLogin(getUsuario())) {
			FacesUtil.mensagemErro(messageBundleService
					.recoveryMessage("usuario_login_existente"));

		} else if (!validaSenhasIguais(getUsuario())) {
			FacesUtil.mensagemErro(messageBundleService
					.recoveryMessage("usuario_senhas_diferentes"));

		} else {

			try {
				if (getUsuario().getId() == null) {
					usuario.setAtivo(true);

					if (tipoUsuarioSelecionado.equals(ADMINISTRADOR)) {
						usuario.setTipoUsuario(Usuario.TipoUsuario.ADMINISTRADOR);
					} else {
						usuario.setTipoUsuario(Usuario.TipoUsuario.VENDEDOR);
					}

					usuarioService.salvar(getUsuario());
					FacesUtil.mensagemInformacao(messageBundleService
							.recoveryMessage("usuario_cadastro_sucesso"));
				} else {
					usuarioService.atualizar(getUsuario());
					FacesUtil.mensagemInformacao(messageBundleService
							.recoveryMessage("usuario_atualizado_sucesso"));
				}

				destino = "usuario/formUsuario";

			} catch (Exception e) {
				FacesUtil.mensagemErro(messageBundleService
						.recoveryMessage("usuario_salvar_atualizar_erro"));
				e.printStackTrace();
			}

		}
		
		return destino;
	}
	
	public String limparCampos() {
	    inicio();
        return "sucesso";  
    }

	public Usuario getUsuarioParaEditarDesativar() {
		Usuario usuario = (Usuario) getModel().getRowData();
		return usuario;
	}

	public String editar() {
		setUsuario(getUsuarioParaEditarDesativar());
		return "formUsuario";
	}

	public String desativar() {
		Usuario usuario = getUsuarioParaEditarDesativar();
		usuario.setAtivo(false);
		this.usuarioService.atualizar(usuario);
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

	public void setModel(DataModel<Usuario> model) {
		this.model = model;
	}

	public DataModel<Usuario> getModel() {
		return model;
	}

}
