package br.com.sgpc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sgpc.model.ItensPedido;
import br.com.sgpc.model.Pedido;
import br.com.sgpc.model.Pedido.TipoPagamento;
import br.com.sgpc.model.Produto;
import br.com.sgpc.model.Usuario;
import br.com.sgpc.service.ItensPedidoService;
import br.com.sgpc.service.MessageBundleService;
import br.com.sgpc.service.PedidoService;
import br.com.sgpc.service.ProdutoService;
import br.com.sgpc.service.UsuarioService;
import br.com.sgpc.session.bean.LoginSession;
import br.com.sgpc.util.FacesUtil;

@Controller(value = "pedidoController")
@Scope("request")
public class PedidoController implements AlphaController {
	
	private static final long serialVersionUID = 1L;
	
	private List<Pedido> pedidosVendedor = new ArrayList<Pedido>();
	
	@Resource(name = "loginSession")
	private LoginSession loginSession;
	
	private int totalItens;
	
	private Usuario usuario;
	
	private Produto produto;
	
	private List<Produto> produtos;
	
	private ItensPedido item;
	
	private List<ItensPedido> itens;
	
	private BigDecimal valorTotal;
	
	private BigDecimal valorRecebido;
	
	private BigDecimal valorTroco;
	
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
		this.pedido.setTipoPagamento(TipoPagamento.CHEQUE);
		setItens(new ArrayList<ItensPedido>());
		setProduto(new Produto());
		setItem(new ItensPedido());
		setUsuario(this.loginSession.getUsuarioLogado());
		setTotalItens(0);
		setValorTotal(new BigDecimal("0.00"));
		setValorRecebido(null);
		setValorTroco(null);
		setModelItensPedido(new ListDataModel<ItensPedido>(getItens()));
	}
	
	public String limparCampos() {
		inicio();
        return "sucesso";  
    }
	
	public SelectItem[] getTiposPagamento() {
		SelectItem[] items = new SelectItem[TipoPagamento.values().length];
		int i = 0;
		for(TipoPagamento t: TipoPagamento.values()) {
			items[i++] = new SelectItem(t, t.getNome());
		}
		return items;
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
		this.pedido.setTipoPagamento(TipoPagamento.DINHEIRO);
		this.item.setProduto(getProduto());
		this.item.setPedido(getPedido());
		this.item.setValorTotal(getProduto().getPreco().multiply(new BigDecimal(this.item.getQuantidade())));
		setTotalItens(this.totalItens + item.getQuantidade());
		setValorTotal(this.valorTotal.add(this.item.getValorTotal()));
		this.itens.add(getItem());
		//this.produtoService.atualizar(getProduto().);
		setItem(new ItensPedido());
		setProduto(new Produto());
		setDescricaoProduto(null);
		//this.itensPedidoService.salvar(this.item);
	}
	
	public DataModel<Pedido> listarPedidos() {
		setModelPedido(new ListDataModel<Pedido>(
				this.pedidoService.buscarTodos()));
		return getModelPedido();
	}
	
	public String salvar(){
		try {

			if (pedido.getId() == null) {

				pedido.setEmissao(new Date());
				pedido.setItens(getItens());
				pedido.setValorTotal(getValorTotal());
				pedido.setVendedor(getUsuario());
				this.pedidoService.salvar(this.pedido);
				FacesUtil.mensagemInformacao(messageBundleService
						.recoveryMessage("pedido_finalizado_sucesso"));
			} else {

				this.pedidoService.atualizar(this.pedido);
				FacesUtil.mensagemInformacao(messageBundleService
						.recoveryMessage("pedido_atualizado_sucesso"));
			}
		} catch (Exception e) {
			FacesUtil.mensagemErro(messageBundleService
					.recoveryMessage("pedido_finalizado_erro"));
			e.printStackTrace();
		}
		return null;
	}
	
	public DataModel<ItensPedido> getModelItensPedido() {
		setModelItensPedido(new ListDataModel<ItensPedido>(getItens()));
		return getModelItensPedido();
	}
	
	public void editarItem(RowEditEvent event){
		FacesUtil.mensagemInformacao(messageBundleService
				.recoveryMessage("item_alterado_lista"));
		
	}
	
	public void removerItem(RowEditEvent event){
		ItensPedido item = ((ItensPedido) event.getObject());
		itens.remove(item);
		FacesUtil.mensagemInformacao(messageBundleService
				.recoveryMessage("item_removido_lista"));
	}
	
	public String excluir(){
		return null;
	}
	
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
			
	public String salvarPedido(){
		return "ok";
	}
	
	public ItensPedido getItem() {
		return item;
	}

	public void setModelItensPedido(DataModel<ItensPedido> modelItensPedido) {
		this.modelItensPedido = modelItensPedido;
	}

	public void setModelPedido(DataModel<Pedido> modelPedido) {
		this.modelPedido = modelPedido;
	}

	public DataModel<Pedido> getModelPedido() {
		return modelPedido;
	}
	
	public void setTotalItens(int totalItens) {
		this.totalItens = totalItens;
	}

	public int getTotalItens() {
		return totalItens;
	}

	public void setPedidosVendedor(List<Pedido> pedidosVendedor) {
		this.pedidosVendedor = pedidosVendedor;
	}

	public List<Pedido> getPedidosVendedor() {
		return pedidosVendedor;
	}

	public void setLoginSession(LoginSession loginSession) {
		this.loginSession = loginSession;
	}

	public LoginSession getLoginSession() {
		return loginSession;
	}

	public void setValorRecebido(BigDecimal valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public BigDecimal getValorRecebido() {
		return valorRecebido;
	}

	public void setValorTroco(BigDecimal valorTroco) {
		this.valorTroco = valorTroco;
	}

	public BigDecimal getValorTroco() {
		return valorTroco;
	}

}
