package br.com.sgpc.model;

import java.io.Serializable;
import java.math.BigDecimal;
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



@Entity
@Table(name= "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = -9176352777468019515L;
	
	@Id
    @SequenceGenerator( name = "produto_id", sequenceName = "produto_seq", allocationSize = 1 )  
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "produto_id" )  
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "codigo_produto", nullable = true, unique=true, length = 10)
	private String codigo;
	
	@Column(name = "descricao_produto", nullable = false, unique=true, length = 30)
	private String descricao;
	
	@Column(name = "preco_produto", nullable = false)
	private BigDecimal preco;
	
	@Column(name = "quantidade_produto", nullable = false)
	private int quantidade;
	
	@Column(name = "quantidade_limite_produto", nullable = false)
	private int quantidadeLimite;
	
	@ManyToOne
	@JoinColumn(name = "fornecedor_id", nullable = false)
	private Fornecedor fornecedor;
	
	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
	private List<MovimentacaoProduto> movimentacaoProduto;
	
	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
	private List<ItensPedido> itens;
	
	
	public int atualizarQuantidadePorMovimentacao(int quantidadeMovimentacao, int quantidadeProduto, 
			MovimentacaoProduto.TipoMovimentacao tipoMovimentacao) {
		if (tipoMovimentacao.equals(MovimentacaoProduto.TipoMovimentacao.ENTRADA)) {
			quantidadeProduto += quantidadeMovimentacao;

		} else {
			quantidadeProduto -= quantidadeMovimentacao;
		}		
		
		return quantidadeProduto;
	}
	
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

	public void setMovimentacaoProduto(List<MovimentacaoProduto> movimentacaoProduto) {
		this.movimentacaoProduto = movimentacaoProduto;
	}

	public List<MovimentacaoProduto> getMovimentacaoProduto() {
		return movimentacaoProduto;
	}

	public void setItens(List<ItensPedido> itens) {
		this.itens = itens;
	}

	public List<ItensPedido> getItens() {
		return itens;
	}	
	
}
