package br.com.sgpc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 5301282659589871437L;
	
	@Id
    @SequenceGenerator( name = "pedido_id", sequenceName = "pedido_seq", allocationSize = 1 )  
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "pedido_id" )  
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name="data_emissao", nullable=false)
	@Temporal(value=TemporalType.DATE)
	private Date emissao;
	
	@Column(name = "valor_total", nullable = false)
	private BigDecimal valorTotal;
	
	@Column(name = "valor_desconto", nullable = false)
	private BigDecimal valorDesconto;
	
	private BigDecimal valorRecebido;
	
	private BigDecimal valorTroco;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipoPagamento;
	
	public enum TipoPagamento {DINHEIRO, CHEQUE, DEBITO, CREDITO}
	
	@OneToMany(mappedBy = "pedido", fetch=FetchType.LAZY)
	private List<ProdutoPedido> itens = new ArrayList<ProdutoPedido>();
	
	public Pedido(){
		super();
	}
	
	public BigDecimal calcularTroco(){
		return this.valorTroco = this.valorRecebido.subtract(this.valorTotal);
	}
	
	public void realizarDesconto(){
		this.valorTotal = this.valorTotal.subtract(this.valorDesconto);
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
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


	public void setItens(List<ProdutoPedido> itens) {
		this.itens = itens;
	}


	public List<ProdutoPedido> getItens() {
		return itens;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}
}
