package br.com.sgpc.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.stereotype.Controller;

import br.com.sgpc.exception.TechnicalException;
import br.com.sgpc.model.Fornecedor;
import br.com.sgpc.model.to.Cep;
import br.com.sgpc.service.ConsultaCepService;
import br.com.sgpc.service.FornecedorService;
import br.com.sgpc.service.MessageBundleService;
import br.com.sgpc.util.FacesUtil;

/**
 * Controller com itera��es com as telas relacionadas ao
 * {@link FornecedorController}
 * 
 * @author Samir Daneu
 * @since 01/10/2012
 * 
 */
@Controller(value = "fornecedorController")
@RequestScoped
public class FornecedorController implements AlphaController {

	private static final long serialVersionUID = 643971891099428877L;

	private Fornecedor fornecedor;

	private Cep cep;

	@Resource(name = "consultaCepService")
	private ConsultaCepService consultaCepService;

	@Resource(name = "fornecedorService")
	private FornecedorService fornecedorService;

	@Resource(name = "messageBundleService")
	private MessageBundleService messageBundleService;

	private DataModel<Fornecedor> model;

	@Override
	@PostConstruct
	public void inicio() {
		fornecedor = new Fornecedor();
		setModel(listarFornecedores());
	}

	public FornecedorController() {
		this.setFornecedor(new Fornecedor());
	}

	public String novoFornecedor() {
		this.setFornecedor(new Fornecedor());
		return "formFornecedor";
	}

	public DataModel<Fornecedor> listarFornecedores() {
		setModel(new ListDataModel<Fornecedor>(
				this.fornecedorService.buscarTodos()));
		return getModel();
	}

	public void encontraCEP() {
		try {
			setCep(consultaCepService.consultarCep(getFornecedor().getCep()));
		} catch (TechnicalException e) {
			FacesUtil.mensagemErro(messageBundleService
					.recoveryMessage("fornecedor_cep_erro"));
			e.printStackTrace();
		}

		if (this.cep != null) {
			this.fornecedor.setLogradouro(this.cep.getLougradouro());
			this.fornecedor.setBairro(this.cep.getBairro());
			this.fornecedor.setCidade(this.cep.getCidade());
			this.fornecedor.setEstado(this.cep.getEstado());
		} else {
			FacesUtil.mensagemErro(messageBundleService
					.recoveryMessage("fornecedor_cep_erro"));
		}

	}

	public String salvarFornecedor() {
		try {
			if (getFornecedor().getId() == null) {
				fornecedorService.salvar(getFornecedor());
				FacesUtil.mensagemInformacao(messageBundleService
						.recoveryMessage("fornecedor_cadastro_sucesso"));
			} else {
				fornecedorService.atualizar(getFornecedor());
				FacesUtil.mensagemInformacao(messageBundleService
						.recoveryMessage("fornecedor_atualizado_sucesso"));
			}
		} catch (Exception e) {
			FacesUtil.mensagemErro(messageBundleService
					.recoveryMessage("fornecedor_salvar_atualizar_erro"));
			e.printStackTrace();
		}

		return "ok";
	}

	public String limparCampos() {
		inicio();
		return "sucesso";
	}

	public Fornecedor getFornecedorParaEditarExcluir() {
		Fornecedor fornecedor = (Fornecedor) getModel().getRowData();
		return fornecedor;
	}

	public String editar() {
		setFornecedor(getFornecedorParaEditarExcluir());
		return "formFornecedor";
	}

	public String excluir() {
		Fornecedor fornecedor = getFornecedorParaEditarExcluir();
		this.fornecedorService.excluir(fornecedor);
		return "mostrarFornecedores";
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setModel(DataModel<Fornecedor> model) {
		this.model = model;
	}

	public DataModel<Fornecedor> getModel() {
		return model;
	}

	public void setConsultaCepService(ConsultaCepService consultaCepService) {
		this.consultaCepService = consultaCepService;
	}

	public ConsultaCepService getConsultaCepService() {
		return consultaCepService;
	}

	public void setCep(Cep cep) {
		this.cep = cep;
	}

	public Cep getCep() {
		return cep;
	}

}
