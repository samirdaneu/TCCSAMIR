package br.com.sgpc.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.stereotype.Controller;

import br.com.sgpc.model.Fornecedor;
import br.com.sgpc.service.FornecedorService;
import br.com.sgpc.service.MessageBundleService;
import br.com.sgpc.util.FacesUtil;

/**
 * Controller com iterações com as telas relacionadas ao {@link FornecedorController}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Controller( value = "fornecedorController" )
@RequestScoped
public class FornecedorController implements AlphaController {

	private static final long serialVersionUID = 643971891099428877L;
	
	private Fornecedor fornecedor;
	
	@Resource( name = "fornecedorService" )
	private FornecedorService fornecedorService;
	
	@Resource( name = "messageBundleService" )
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
	
	public String novoFornecedor(){
		this.setFornecedor(new Fornecedor());
		return "formFornecedor";
	}
	
	public DataModel<Fornecedor> listarFornecedores() {
		setModel(new ListDataModel<Fornecedor>(this.fornecedorService.buscarTodos()));
		return getModel();
	}
	
	public String salvarFornecedor(){
		try {
			if (getFornecedor().getId() == null){
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
	
	public Fornecedor getFornecedorParaEditarExcluir(){
		Fornecedor fornecedor = (Fornecedor) getModel().getRowData();
		return fornecedor;
	}
	
	public String editar(){
		setFornecedor(getFornecedorParaEditarExcluir());
		return "formFornecedor";
	}
	
	public String excluir(){
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

}
