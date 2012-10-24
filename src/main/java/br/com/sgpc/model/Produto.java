package br.com.sgpc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name= "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = -9176352777468019515L;
	
	@Id
    @SequenceGenerator( name = "produto_id", sequenceName = "produto_seq", allocationSize = 1 )  
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "produto_id" )  
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "codigo_produto", nullable = true, length = 10)
	private String codigo;
	
	@Column(name = "descricao_produto", nullable = false, length = 30)
	private String descricao;
	
	@Column(name = "preco_produto", nullable = false)
	private BigDecimal preco;
	
	@Column(name = "valor_desconto_produto", nullable = false)
	private BigDecimal valorDesconto;
	
	@Column(name = "quantidade_produto", nullable = false)
	private int quantidade;
	
	@Column(name = "quantidade_limite_produto", nullable = false)
	private int quantidadeLimite;
	
	@ManyToOne
	@JoinColumn(name = "fornecedor_id", nullable = false)
	private Fornecedor fornecedor;
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidadeLimite() {
		return quantidadeLimite;
	}

	public void setQuantidadeLimite(int quantidadeLimite) {
		this.quantidadeLimite = quantidadeLimite;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

}
