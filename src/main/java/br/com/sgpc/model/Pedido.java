package br.com.sgpc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 5301282659589871437L;
	
	@Id
    @SequenceGenerator( name = "pedido_id", sequenceName = "pedido_seq", allocationSize = 1 )  
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "pedido_id" )  
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name="data_emissao", nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date emissao;
	
	@Column(name = "valor_total", nullable = false)
	private BigDecimal valorTotal;
	
	@Column(name = "valor_desconto", nullable = false)
	private BigDecimal valorDesconto;
	
	@Column(name = "cpf", nullable = true, length = 11)
	private String cpf;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario vendedor;
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
	private List<ProdutoPedido> itens = new ArrayList<ProdutoPedido>();
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
	private List<FormaPagamento> formasPagamento;
	
	@Transient
	private BigDecimal valorRecebido;
	
	@Transient
	private BigDecimal valorTroco;
	
	public BigDecimal calcularTroco() {
		return valorTroco = valorRecebido.subtract( valorTotal );
	}
	
	public void realizarDesconto() {
		valorTotal = valorTotal.subtract( valorDesconto );
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEmissao() {
		return emissao;
	}

	public void setEmissao(Date emissao) {
		this.emissao = emissao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public List<ProdutoPedido> getItens() {
		return itens;
	}

	public void setItens(List<ProdutoPedido> itens) {
		this.itens = itens;
	}

	public List<FormaPagamento> getFormasPagamento() {
		return formasPagamento;
	}

	public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
		this.formasPagamento = formasPagamento;
	}

	public BigDecimal getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(BigDecimal valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public BigDecimal getValorTroco() {
		return valorTroco;
	}

	public void setValorTroco(BigDecimal valorTroco) {
		this.valorTroco = valorTroco;
	}
}
