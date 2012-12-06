package br.com.sgpc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 839746810219584165L;

	@Id
    @SequenceGenerator( name = "usuario_id", sequenceName = "usuario_seq", allocationSize = 1 )  
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "usuario_id" )  
    @Column( name = "id", unique = true, nullable = false )  
	private Integer id;

	@Column(name = "login", nullable = false, length = 8, unique = true)
	private String login;

	@Column(name = "senha", nullable = false, length = 8)
	private String senha;
	
	@Column(name = "nome", nullable = false, length = 50)
	private String nome;
	
	@Column(name = "email_usuario", nullable = false, length = 40)
	private String email;

	@Column(name = "ativo", nullable = false)
	private boolean ativo;

	@Column(name = "tipo_usuario")
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;
	
	@OneToMany(mappedBy = "vendedor", fetch = FetchType.LAZY)
	private List<Pedido> pedidos;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<MovimentacaoProduto> movimentacoesProdutos;
	
	public enum TipoUsuario {VENDEDOR, ADMINISTRADOR}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setMovimentacoesProdutos(List<MovimentacaoProduto> movimentacoesProdutos) {
		this.movimentacoesProdutos = movimentacoesProdutos;
	}

	public List<MovimentacaoProduto> getMovimentacoesProdutos() {
		return movimentacoesProdutos;
	}
	
}