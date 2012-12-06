package br.com.sgpc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.validation.ConstraintViolationException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sgpc.model.Usuario;
import br.com.sgpc.service.MessageBundleService;
import br.com.sgpc.service.UsuarioService;
import br.com.sgpc.util.FacesUtil;

@Controller(value = "usuarioController")
@Scope("request")
public class UsuarioController implements AlphaController {

	private static final long serialVersionUID = -2558847076842976054L;
	
	private String novaSenha;
	
	private String novaSenhaConfirmacao;
	
	private String login;

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

	@Override
	@PostConstruct
	public void inicio() {
		usuario = new Usuario();
		setModel(listarUsuarios());
		setListaTiposUsuario(new ArrayList<String>());
		getListaTiposUsuario().add(ADMINISTRADOR);
		getListaTiposUsuario().add(VENDEDOR);
	}
	
	public String limparCampos() {
	    inicio();
        return "ok";  
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
		
	private boolean validaSenhasIguaisTroca(String senha) {
		boolean iguais = false;
		if (this.novaSenha.equals(senhaConfirmacao)) {
			iguais = true;
		}
		return iguais;
	}
	
	public void alterarSenha(String login) {
		try {
			if (!validaSenhasIguaisTroca(getNovaSenha())) {
				FacesUtil.mensagemErro(messageBundleService
						.recoveryMessage("usuario_senhas_diferentes"));
			} else {
				this.usuario = this.usuarioService.procurarUsuarioPeloLogin(login);
				this.usuario.setSenha(getNovaSenha());
				this.usuarioService.atualizar(this.usuario);
			}
		} catch (Exception e) {
			FacesUtil.mensagemErro(messageBundleService
					.recoveryMessage("usuario_senhas_diferentes"));
		}
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

	public Usuario getUsuarioParaEditarExcluir() {
		Usuario usuario = (Usuario) getModel().getRowData();
		return usuario;
	}

	public String editar() {
		setUsuario(getUsuarioParaEditarExcluir());
		return "/usuario/formEditar";
	}
	
	public String excluir() {
		Usuario usuario = getUsuarioParaEditarExcluir();
		try{
			this.usuarioService.excluir(usuario);
		} catch (ConstraintViolationException e){
			FacesUtil.mensagemErro(messageBundleService
					.recoveryMessage("fornecedor_relacionamento_erro"));
			usuario.setAtivo(false);
			this.usuarioService.atualizar(usuario);
		}
		
		return "/usuario/mostrarUsuarios";
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

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenhaConfirmacao(String novaSenhaConfirmacao) {
		this.novaSenhaConfirmacao = novaSenhaConfirmacao;
	}

	public String getNovaSenhaConfirmacao() {
		return novaSenhaConfirmacao;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

}
