package br.com.sgpc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "fornecedor")
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = -7863048949631947013L;
	
	@Id
    @SequenceGenerator( name = "fornecedor_id", sequenceName = "fornecedor_seq", allocationSize = 1 )  
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "fornecedor_id" )  
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "razao_social", nullable = false, length = 30)
	private String razaoSocial;
	
	@Column(name = "cnpj", nullable = true, length = 14)
	private String cnpj;
	
	@Column(name = "telefone", nullable = true, length = 14)
	private String telefone;
	
	@Column(name = "endereco", nullable = true, length = 40)
	private String endereco;
	
	@Column(name = "cep", nullable = true, length = 8)
	private String cep;
	
	@Column(name = "bairro", nullable = true, length = 30)
	private String bairro;
	
	@Column(name = "cidade", nullable = true, length = 30)
	private String cidade;
	
	@Column(name = "uf", nullable = true, length = 2)
	private String uf;
	
	@Email(message="E-mail inv�lido!")
	@Column(name = "email", nullable = true, length = 20)
	private String email;
	
	@Column(name = "site", nullable = true, length = 30)
	private String site;
	
	@Column(name = "nome_contato", nullable = true, length = 30)
	private String nomeContato;
	
	@OneToMany(mappedBy="fornecedor", fetch=FetchType.LAZY)
	private List<Produto> produtos;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCidade() {
		return cidade;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getUf() {
		return uf;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
}
