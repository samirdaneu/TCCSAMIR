package br.com.sgpc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.event.RowEditEvent;
import org.springframework.stereotype.Controller;

import br.com.sgpc.model.ItensPedido;
import br.com.sgpc.model.Pedido;
import br.com.sgpc.model.Produto;
import br.com.sgpc.model.Usuario;
import br.com.sgpc.service.ItensPedidoService;
import br.com.sgpc.service.MessageBundleService;
import br.com.sgpc.service.PedidoService;
import br.com.sgpc.service.ProdutoService;
import br.com.sgpc.service.UsuarioService;

@Controller(value = "pedidoController")
@RequestScoped
public class PedidoController implements AlphaController {
	
	private static final long serialVersionUID = 1L;
	
	private static final String DINHEIRO = "Dinheiro";	

	private static final String CHEQUE = "Cheque";
	
	private static final String DEBITO = "Débito";
	
	private static final String CREDITO = "Crédito";
	
	private List<String> listaTipoPagamento;	
	
	private Usuario usuario;
	
	private Produto produto;
	
	private List<Produto> produtos;
	
	private ItensPedido item;
	
	private List<ItensPedido> itens;
	
	private int quantidade;
	
	private BigDecimal valorTotal;
	
	private Pedido pedido;
	
	private String descricaoProduto;
	
	@Resource( name = "produtoService" ) 
	private ProdutoService produtoService;
	
	@Resource( name = "pedidoService" ) 
	private PedidoService pedidoService;
	
	@Resource( name = "usuarioService" ) 
	private UsuarioService usuarioService;
	
	@Resource( name = "itensPedidoService" ) 
	private ItensPedidoService itensPedidoService;
	
	@Resource(name = "messageBundleService")
	private MessageBundleService messageBundleService;
	
	private DataModel<Pedido> modelPedido;
	
	private DataModel<ItensPedido> modelItensPedido;
	
	@Override
	@PostConstruct
	public void inicio() { 
		setPedido(new Pedido());
		setItens(new ArrayList<ItensPedido>());
		setProduto(new Produto());
		setItem(new ItensPedido());
		//setModelItensPedido(listarItensPedidos());
		setListaTipoPagamento(new ArrayList<String>());
		getListaTipoPagamento().add(DINHEIRO);
		getListaTipoPagamento().add(CHEQUE);
		getListaTipoPagamento().add(DEBITO);
		getListaTipoPagamento().add(CREDITO);
		setUsuario(new Usuario());
	}
	
	public DataModel<Pedido> listarPedidos() {
		setModelPedido(new ListDataModel<Pedido>(
				this.pedidoService.buscarTodos()));
		return getModelPedido();
	}
	
	
	
	/*public DataModel<ItensPedido> listarItensPedidos() {
		setUsuario(usuarioService.buscarPorID(1));
		setProduto(produtoService.buscarPorID(1));
		pedido.setEmissao(new Date());		
		movimentacaoProduto.setUsuario(getUsuario());
		setModelItensPedido(new ListDataModel<ItensPedido>(
				this.itensPedidoService.buscarTodos()));
		return getModelItensPedido();
	}*/
	
	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	public PedidoService getPedidoService() {
		return pedidoService;
	}

	public void setMessageBundleService(MessageBundleService messageBundleService) {
		this.messageBundleService = messageBundleService;
	}

	public MessageBundleService getMessageBundleService() {
		return messageBundleService;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}    

	public void editarIten(RowEditEvent event){
		//Pedido pedido = (Pedido) event.getObject();		
	}
	
	public void excluirIten(RowEditEvent event){
		//Pedido pedido = (Pedido) event.getObject();		
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setItens(List<ItensPedido> itens) {
		this.itens = itens;
	}

	public List<ItensPedido> getItens() {
		return itens;		
	}

	public void setItensPedidoService(ItensPedidoService itensPedidoService) {
		this.itensPedidoService = itensPedidoService;
	}

	public ItensPedidoService getItensPedidoService() {
		return itensPedidoService;
	}

	public void setItem(ItensPedido item) {
		this.item = item;
	}
	
	public List<String> buscarProdutos(final String query) {
		final List<Produto> produtos = produtoService
				.buscarParcialPorDescricao(query);
		final List<String> descricoes = new ArrayList<String>();

		for (Produto produto : produtos) {
			descricoes.add(produto.getDescricao());
		}

		return descricoes;
	}
	
	public void adicionarProduto(){
		setProduto(this.produtoService.buscarUnicoPorDescricao(getDescricaoProduto()));
		this.item.setProduto(getProduto());
		this.item.setPedido(getPedido());
		this.item.setQuantidade(getQuantidade());
		this.itens.add(getItem());		
		//this.itensPedidoService.salvar(this.item);
	}
		
	public String salvarPedido(){
		return "ok";
	}
	
	public String limparCampos() {
		inicio();
        return "sucesso";  
    }

	public ItensPedido getItem() {
		return item;
	}

	public void setModelItensPedido(DataModel<ItensPedido> modelItensPedido) {
		this.modelItensPedido = modelItensPedido;
	}

	public DataModel<ItensPedido> getModelItensPedido() {
		return modelItensPedido;
	}

	public void setModelPedido(DataModel<Pedido> modelPedido) {
		this.modelPedido = modelPedido;
	}

	public DataModel<Pedido> getModelPedido() {
		return modelPedido;
	}
	
	public static String getDinheiro() {
		return DINHEIRO;
	}

	public static String getCheque() {
		return CHEQUE;
	}

	public static String getDebito() {
		return DEBITO;
	}

	public static String getCredito() {
		return CREDITO;
	}

	public void setListaTipoPagamento(List<String> listaTipoPagamento) {
		this.listaTipoPagamento = listaTipoPagamento;
	}

	public List<String> getListaTipoPagamento() {
		return listaTipoPagamento;
	}

}
