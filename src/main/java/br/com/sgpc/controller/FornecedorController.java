package br.com.sgpc.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.validation.ConstraintViolationException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sgpc.exception.TechnicalException;
import br.com.sgpc.model.Fornecedor;
import br.com.sgpc.model.to.Cep;
import br.com.sgpc.service.ConsultaCepService;
import br.com.sgpc.service.FornecedorService;
import br.com.sgpc.service.MessageBundleService;
import br.com.sgpc.util.FacesUtil;

@Controller(value="fornecedorController")
@Scope("request")
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

	public String limparCampos() {
		inicio();
		return "ok";
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

	public void salvarFornecedor() {
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
	
	}
	
	public Fornecedor getFornecedorParaEditarExcluir() {
		Fornecedor fornecedor = (Fornecedor) getModel().getRowData();
		return fornecedor;
	}

	public String editar() {
		setFornecedor(getFornecedorParaEditarExcluir());
		return "/fornecedor/formFornecedor";
	}

	public String excluir() {
		Fornecedor fornecedor = getFornecedorParaEditarExcluir();
		try{
			this.fornecedorService.excluir(fornecedor);
		} catch (ConstraintViolationException e){
			FacesUtil.mensagemErro(messageBundleService
					.recoveryMessage("fornecedor_relacionamento_erro"));
			fornecedor.setAtivo(false);
			this.fornecedorService.atualizar(fornecedor);
		}
		
		return consultarFornecedores();
	}
	
	public String consultarFornecedores(){
		return "/fornecedor/mostrarFornecedores";
	}
	
	public String cadastrarFornecedor(){
		return "/fornecedor/formFornecedor";
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
