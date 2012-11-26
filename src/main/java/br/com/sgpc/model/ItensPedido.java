package br.com.sgpc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class ItensPedido implements Serializable {

	private static final long serialVersionUID = -7662403851738255916L;

	@EmbeddedId
	private ItensPedidoPK id = new ItensPedidoPK();

	private int quantidade;
	
	private BigDecimal valorUnitario;
	
	private BigDecimal valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Produto produto;
	
	public ItensPedido(){		
	}

	public ItensPedidoPK getId() {
		return id;
	}

	public void setId(ItensPedidoPK id) {
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
	
	public ItensPedido(int quantidade, BigDecimal valor, BigDecimal desconto,
			Integer pedido, Integer produto) {
		this.id = new ItensPedidoPK(pedido, produto);
		this.quantidade = quantidade;			
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	
}
