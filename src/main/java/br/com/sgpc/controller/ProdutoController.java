package br.com.sgpc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.stereotype.Controller;

import br.com.sgpc.model.Fornecedor;
import br.com.sgpc.model.Produto;
import br.com.sgpc.service.FornecedorService;
import br.com.sgpc.service.MessageBundleService;
import br.com.sgpc.service.ProdutoService;
import br.com.sgpc.util.FacesUtil;

/**
 * Controller com itera��es com as telas relacionadas ao
 * {@link ProdutoController}
 * 
 * @author Samir Daneu
 * @since 01/10/2012
 * 
 */
@Controller(value = "produtoController")
@RequestScoped
public class ProdutoController implements AlphaController {

	private static final long serialVersionUID = -6759622970651283020L;

	private Produto produto;

	private List<Fornecedor> fornecedores;

	private Fornecedor fornecedor;

	@Resource(name = "produtoService")
	private ProdutoService produtoService;

	@Resource(name = "fornecedorService")
	private FornecedorService fornecedorService;

	@Resource(name = "messageBundleService")
	private MessageBundleService messageBundleService;

	private DataModel<Produto> model;

	public ProdutoController() {
	}

	@Override
	@PostConstruct
	public void inicio() {
		produto = new Produto();
		fornecedores = fornecedorService.buscarTodos();		
		setModel(listarProdutos());
	}

	public String novoProduto() {
		this.setProduto(new Produto());
		return "produto/fomProduto";
	}

	public DataModel<Produto> listarProdutos() {
		setModel(new ListDataModel<Produto>(this.produtoService.buscarTodos()));
		return getModel();
	}

	public String salvarProduto() {
		try {

			fornecedor = fornecedorService.buscarPorID(fornecedor.getId());

			if (produto.getId() == null) {

				produto.setFornecedor(fornecedor);
				produtoService.salvar(produto);
				FacesUtil.mensagemInformacao(messageBundleService
						.recoveryMessage("produto_cadastrado_sucesso"));
			} else {

				produtoService.atualizar(produto);
				FacesUtil.mensagemInformacao(messageBundleService
						.recoveryMessage("produto_atualizado_sucesso"));
			}
		} catch (Exception e) {
			FacesUtil.mensagemErro(messageBundleService
					.recoveryMessage("produto_salvar_atualizar_erro"));
			e.printStackTrace();
		}

		return "ok";
	}

	public Produto getProdutoParaEditarExcluir() {
		Produto produto = getModel().getRowData();
		return produto;
	}

	public String editar() {
		setProduto(getProdutoParaEditarExcluir());
		return "/produto/formEditar";
	}
	
	public String limparCampos() {
		this.fornecedores = new ArrayList<Fornecedor>();
        inicio();
        return "sucesso";  
    }

	public String desativar() {
		Produto produto = getProdutoParaEditarExcluir();
		//this.produtoService.excluir(produto);
		return "mostrarProdutos";
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public List<Fornecedor> getFornecedores() {
		return this.fornecedores;
	}

	public void setMessageBundleService(
			MessageBundleService messageBundleService) {
		this.messageBundleService = messageBundleService;
	}

	public void setModel(DataModel<Produto> model) {
		this.model = model;
	}

	public DataModel<Produto> getModel() {
		return model;
	}

}
