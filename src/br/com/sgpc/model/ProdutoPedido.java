package br.com.sgpc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto_pedido")
public class ProdutoPedido implements Serializable {

	private static final long serialVersionUID = -7662403851738255916L;

	@EmbeddedId
	private ProdutoPedidoPK id = new ProdutoPedidoPK();

	private int quantidade;
	
	private BigDecimal preco;
	
	private BigDecimal desconto;

	public ProdutoPedidoPK getId() {
		return id;
	}

	public void setId(ProdutoPedidoPK id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@ManyToOne
	@JoinColumn(name = "pedido_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Produto produto;
	
	public ProdutoPedido(){		
	}
	
	public ProdutoPedido(int quantidade, BigDecimal valor, BigDecimal desconto,
			Integer pedido, Integer produto) {
		this.id = new ProdutoPedidoPK(pedido, produto);
		this.quantidade = quantidade;
		this.preco = valor;
		this.desconto = desconto;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}
}
