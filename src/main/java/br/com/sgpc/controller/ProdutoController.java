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

import br.com.sgpc.model.Fornecedor;
import br.com.sgpc.model.Produto;
import br.com.sgpc.service.FornecedorService;
import br.com.sgpc.service.MessageBundleService;
import br.com.sgpc.service.ProdutoService;
import br.com.sgpc.util.FacesUtil;


@Controller("produtoController")
@Scope("session")
public class ProdutoController implements AlphaController {
	
	private static final long serialVersionUID = -6759622970651283020L;
	
	private static final String formularioProduto = "/produto/formProduto";
	
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

	@Override
	@PostConstruct
	public void inicio() {
		produto = new Produto();
		fornecedores = fornecedorService.buscarTodos();
		setModel(listarProdutos());
	}
	
	public DataModel<Produto> listarProdutos() {
		setModel(new ListDataModel<Produto>(this.produtoService.buscarTodos()));
		return getModel();
	}
	
	public void salvarProduto() {
		try {

			fornecedor = fornecedorService.buscarPorID(fornecedor.getId());

			if (produto.getId() == null) {

				produto.setFornecedor(fornecedor);
				produto.setDescricao(this.produto.getDescricao().toUpperCase());
				produto.setAtivo(true);
				produtoService.salvar(produto);
				limparCampos();
				FacesUtil.mensagemInformacao(messageBundleService
						.recoveryMessage("produto_cadastrado_sucesso"));
			} else {
				produtoService.atualizar(produto);
				limparCampos();
				FacesUtil.mensagemInformacao(messageBundleService
						.recoveryMessage("produto_atualizado_sucesso"));
			}
		} catch (Exception e){
			String string = this.produtoService.verificaCodigoDescricaoDuplicado(produto);
			if(string.equals("codigo")){
				FacesUtil.mensagemErro(messageBundleService
						.recoveryMessage("produto_codigo_duplicado"));
			} else {
				FacesUtil.mensagemErro(messageBundleService
						.recoveryMessage("produto_descricao_duplicada"));
			}
		}
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
		fornecedor = null;
        inicio();
        return formularioProduto;  
    }

	public String excluir() {
		Produto produto = getProdutoParaEditarExcluir();
		try{
			this.produtoService.excluir(produto);
		} catch (ConstraintViolationException e){
			FacesUtil.mensagemErro(messageBundleService
					.recoveryMessage("produto_relacionamento_erro"));
			if(produto.getQuantidade() > 0){
			FacesUtil.mensagemErro(messageBundleService
						.recoveryMessage("produto_relacionamento_erro"));
			}else{
				produto.setAtivo(false);
			}			
			this.produtoService.atualizar(produto);
		}		
		
		return "/produto/mostrarProdutos";
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
		return this.fornecedorService.buscarTodos();
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
