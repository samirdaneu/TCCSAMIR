package br.com.sgpc.model.to;

public class Cep {

	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	private String lougradouro;
	private String regiao;
	
	public Cep() { }
	
	public Cep(final String bairro, final String cep, final String cidade, final String estado, 
			   final String lougradouro, final String regiao) {
		
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.lougradouro = lougradouro;
		this.regiao = regiao;
	}
	
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getLougradouro() {
		return lougradouro;
	}

	public void setLougradouro(String lougradouro) {
		this.lougradouro = lougradouro;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
}
