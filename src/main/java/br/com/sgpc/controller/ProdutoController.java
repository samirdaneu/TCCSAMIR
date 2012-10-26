package br.com.sgpc.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.sgpc.dao.GenericDao;
import br.com.sgpc.model.Produto;
import br.com.sgpc.util.FacesUtil;


/**
 * Controller com iterações com as telas relacionadas ao {@link ProdutoController}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@ManagedBean(name = "produtoController")
@RequestScoped
public class ProdutoController implements Serializable {

	private static final long serialVersionUID = -6759622970651283020L;
	
	private Produto produto;
	
	private GenericDao<Produto, Integer> produtoDao;
	
	private DataModel model;
	
	public ProdutoController(){
		this.setProduto(new Produto());
	}
	
	public String novoProduto(){
		this.setProduto(new Produto());
		return "formProduto";
	}
	
	public DataModel listarProdutos(){
		model = new ListDataModel(this.produtoDao.buscarTodos());
		return model;
	}
	
	public String salvarProduto(){
		try {
			if (getProduto().getId() == null){
				produtoDao.salvar(getProduto());
				FacesUtil.mensagemInformacao("Produto cadastrado com sucesso!");
			} else {
				produtoDao.atualizar(getProduto());
				FacesUtil.mensagemInformacao("Produto cadastrado com sucesso!");
			}
		} catch (Exception e) {
			FacesUtil.mensagemErro("Erro ao salvar/atualizar produto");
			e.printStackTrace();
		}			
		
		return "ok";
	}
	
	public Produto getProdutoParaEditarExcluir(){
		Produto produto = (Produto) model.getRowData();
		return produto;
	}
	
	public String editar(){
		setProduto(getProdutoParaEditarExcluir());
		return "formProduto";
	}
	
	public String excluir(){
		Produto produto = getProdutoParaEditarExcluir();
		this.produtoDao.excluir(produto);
		return "mostrarProdutos";
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}	
}
