package br.com.sgpc.model;

import java.io.Serializable;
import java.util.Set;

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

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 7590225802018243309L;

	@Id
    @SequenceGenerator( name = "cliente_id", sequenceName = "cliente_seq", allocationSize = 1 )  
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "cliente_id" )  
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "nome_cliente", nullable = false, length = 30)
	private String nome;
	
	@Email(message="E-mail inválido!")
	@Column(name = "email_cliente", nullable = true, length = 30)
	private String email;
	
	@Column(name = "telefone_contato", nullable = true)
	private String telefone;
	
	@Column(name="cpf", nullable=true, length = 11)
	private String cpf;
	
	@Column(name = "sexo", nullable = false, length = 1, columnDefinition="char(1)")
	@Enumerated(EnumType.STRING)    
    private Sexo sexo;
	
	public enum Sexo {
	     M("Masculino"),
	     F("Feminino");
	 
	     private String descricao;
	 
	     private Sexo(String descricao) {
	          this.descricao = descricao;
	     }
	 
	     public String getDescricao() {
	           return descricao;
	     }
	 }

	@OneToMany(mappedBy = "cliente", fetch=FetchType.LAZY)
	private Set<Pedido> pedidos;

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefone() {
		return telefone;
	}
}
