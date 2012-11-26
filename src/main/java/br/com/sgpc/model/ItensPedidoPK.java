package br.com.sgpc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ItensPedidoPK implements Serializable {

	private static final long serialVersionUID = -1643135097218988663L;
	
	public ItensPedidoPK(){		
	}	
	
	public ItensPedidoPK(Integer pedidoID, Integer produtoID) {
		super();
		this.pedidoID = pedidoID;
		this.produtoID = produtoID;
	}
	
	@Column(name = "pedido_id")
	private Integer pedidoID;
	
	@Column(name = "produto_id")
	private Integer produtoID;
	
	public Integer getPedidoID() {
		return pedidoID;
	}

	public void setPedidoID(Integer pedidoID) {
		this.pedidoID = pedidoID;
	}

	public int getProdutoID() {
		return produtoID;
	}

	public void setProdutoID(int produtoID) {
		this.produtoID = produtoID;
	}
}
