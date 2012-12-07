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

import org.apache.commons.mail.EmailException;
import org.primefaces.event.RowEditEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.sgpc.model.ItensPedido;
import br.com.sgpc.model.Pedido;
import br.com.sgpc.model.Pedido.TipoPagamento;
import br.com.sgpc.model.Produto;
import br.com.sgpc.model.Usuario;
import br.com.sgpc.service.EmailService;
import br.com.sgpc.service.ItensPedidoService;
import br.com.sgpc.service.MessageBundleService;
import br.com.sgpc.service.PedidoService;
import br.com.sgpc.service.ProdutoService;
import br.com.sgpc.service.UsuarioService;
import br.com.sgpc.session.bean.LoginSession;
import br.com.sgpc.util.FacesUtil;

@Controller(value = "pedidoController")
@Scope("session")
public class PedidoController implements AlphaController {
	
	private static final long serialVersionUID = 1L;
	
	private List<Pedido> pedidosVendedor = new ArrayList<Pedido>();
	
	private String mensagemAdicionarProduto;
	
	@Resource(name = "loginSession")
	private LoginSession loginSession;
	
	@Resource(name = "emailService")
	private EmailService emailService;
	
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
	
	@Resource(name = "usuarioService")
	private UsuarioService usuarioService;
	
	@Resource( name = "produtoService" ) 
	private ProdutoService produtoService;
	
	@Resource( name = "pedidoService" ) 
	private PedidoService pedidoService;
	
	@Resource( name = "itensPedidoService" ) 
	private ItensPedidoService itensPedidoService;
	
	@Resource(name = "messageBundleService")
	private MessageBundleService messageBundleService;	
	
	private DataModel<ItensPedido> modelItensPedido;
	
	@Override
	@PostConstruct
	public void inicio() { 
		setPedido(new Pedido());
		setItens(new ArrayList<ItensPedido>());
		setProduto(new Produto());
		setItem(new ItensPedido());
		setUsuario(this.loginSession.getUsuarioLogado());
		setTotalItens(0);
		setValorTotal(new BigDecimal("0.00"));
		setValorRecebido(new BigDecimal("0.00"));
		setValorTroco(new BigDecimal("0.00"));
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
	
	private boolean isQuantidadeProdutoValido() {
		boolean resultado = false;
		Produto produto = this.produtoService
				.buscarUnicoPorDescricao(getDescricaoProduto());
		if (this.item.getQuantidade() > 0)
			resultado = true;
		this.mensagemAdicionarProduto = "Quantidade deve ser maior que zero!";
		if (produto.getQuantidade() >= this.item.getQuantidade())
			resultado = true;
		this.mensagemAdicionarProduto = "Quantidade indisponível no estoque! Quantidade disponível: "
				+ produto.getQuantidade();
		this.produto = produto;

		return resultado;
	}
	
	public void adicionarProduto(){
		if(isQuantidadeProdutoValido()){
			this.item.setProduto(getProduto());
			this.item.setPedido(getPedido());
			this.item.setValorTotal(getProduto().getPreco().multiply(new BigDecimal(this.item.getQuantidade())));
			this.totalItens = this.totalItens + item.getQuantidade();
			this.valorTotal = this.valorTotal.add(this.item.getValorTotal());
			this.itens.add(getItem());
			this.item = new ItensPedido();
			this.produto = new Produto();
			this.descricaoProduto = new String();			
		} else {
			FacesUtil.mensagemErro(this.mensagemAdicionarProduto);
		}				
	}
	
	private void salvarItens(List<ItensPedido> itens){
		for (ItensPedido item : itens) {
			ItensPedido ip = new ItensPedido(item.getQuantidade(), item.getProduto().getPreco(),
					item.getValorTotal(), this.pedido.getId(), item.getProduto().getId());			
			this.itensPedidoService.salvar(ip);
		}
	}
	
	private void atualizarProdutos(List<ItensPedido> itens) throws EmailException{
		for (ItensPedido item : itens) {
			Produto p = item.getProduto();
			p.setQuantidade((p.getQuantidade() - item.getQuantidade()));
			if (p.getQuantidade() <= p.getQuantidadeLimite()) {
				this.emailService.enviaEmailQuantidadeLimite(
						this.usuarioService.buscarAdministradoresAtivos(), p);
			}
			this.produtoService.atualizar(p);
			
		}		
	}	
	
	private boolean isCPFVazio(){
		boolean resultado = false;
		if(pedido.getCpf().isEmpty()){
			resultado = true;
		}
		return resultado;
	}
	
	private boolean cpfValido() {
		boolean resultado = false;
		if (isCPFVazio()) {
			resultado = true;
		} else if (pedido.getCpf().equals("000.000.000-00")
				|| pedido.getCpf().equals("111.111.111-11")
				|| pedido.getCpf().equals("222.222.222-22")
				|| pedido.getCpf().equals("333.333.333-33")
				|| pedido.getCpf().equals("444.444.444-44")
				|| pedido.getCpf().equals("555.555.555-55")
				|| pedido.getCpf().equals("666.666.666-66")
				|| pedido.getCpf().equals("777.777.777-77")
				|| pedido.getCpf().equals("888.888.888-88")
				|| pedido.getCpf().equals("999.999.999-99")) {
			resultado = false;
		} else {

			try {
				CPFValidator validator = new CPFValidator();
				validator.assertValid(pedido.getCpf());

			} catch (InvalidStateException e) {
				FacesUtil.mensagemErro(messageBundleService
						.recoveryMessage("cpf_invalido"));
				resultado = false;
			}
		}
		return resultado;
	}	
		
	public void salvar(){
		try {

			if(cpfValido())
			
			if (pedido.getId() == null) {

				pedido.setEmissao(new Date());
				pedido.setValorTotal(getValorTotal());
				pedido.setVendedor(getUsuario());
				pedido.setItens(getItens());
				this.pedidoService.salvar(this.pedido);
				salvarItens(getItens());
				atualizarProdutos(getItens());
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
		}
		
	}
	
	public DataModel<ItensPedido> getModelItensPedido() {
		setModelItensPedido(new ListDataModel<ItensPedido>(getItens()));
		return getModelItensPedido();
	}
	
	public void removerItem(RowEditEvent event){
		ItensPedido item = ((ItensPedido) event.getObject());
		this.totalItens -= item.getQuantidade();
		this.valorTotal = this.valorTotal.subtract(item.getValorTotal());
		itens.remove(item);
		setModelItensPedido(new ListDataModel<ItensPedido>(getItens()));
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
			
	public ItensPedido getItem() {
		return item;
	}

	public void setModelItensPedido(DataModel<ItensPedido> modelItensPedido) {
		this.modelItensPedido = modelItensPedido;
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
		if (this.valorRecebido.doubleValue() > 0) {
			return this.valorTroco = this.valorRecebido
					.subtract(this.valorTotal);
		} else {
			return this.valorTroco;
		}	
	}

	public void setMensagemAdicionarProduto(String mensagemAdicionarProduto) {
		this.mensagemAdicionarProduto = mensagemAdicionarProduto;
	}

	public String getMensagemAdicionarProduto() {
		return mensagemAdicionarProduto;
	}

}
