package br.com.sgpc.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.sgpc.dao.GenericoDAO;
import br.com.sgpc.model.Fornecedor;
import br.com.sgpc.utilidades.FacesUtilidades;


@ManagedBean(name = "fornecedorController")
@RequestScoped
public class FornecedorController implements Serializable {

	private static final long serialVersionUID = 643971891099428877L;
	
	private Fornecedor fornecedor;
	
	private GenericoDAO<Fornecedor, Integer> fornecedorDAO;
	
	private DataModel model;

	public FornecedorController() {
		this.setFornecedor(new Fornecedor());
	}
	
	public String novoFornecedor(){
		this.setFornecedor(new Fornecedor());
		return "formFornecedor";
	}
	
	public DataModel listarFornecedors(){
		model = new ListDataModel(this.fornecedorDAO.buscarTodos());
		return model;
	}
	
	public String salvarFornecedor(){
		try {
			if (getFornecedor().getId() == null){
				fornecedorDAO.salvar(getFornecedor());
				FacesUtilidades.mensagemInformação("Fornecedor cadastrado com sucesso!");
			} else {
				fornecedorDAO.atualizar(getFornecedor());
				FacesUtilidades.mensagemInformação("Fornecedor cadastrado com sucesso!");
			}
		} catch (Exception e) {
			FacesUtilidades.mensagemErro("Erro ao salvar/atualizar fornecedor");
			e.printStackTrace();
		}			
		
		return "ok";
	}
	
	public Fornecedor getFornecedorParaEditarExcluir(){
		Fornecedor fornecedor = (Fornecedor) model.getRowData();
		return fornecedor;
	}
	
	public String editar(){
		setFornecedor(getFornecedorParaEditarExcluir());
		return "formFornecedor";
	}
	
	public String excluir(){
		Fornecedor fornecedor = getFornecedorParaEditarExcluir();
		this.fornecedorDAO.excluir(fornecedor);
		return "mostrarFornecedores";
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

}
