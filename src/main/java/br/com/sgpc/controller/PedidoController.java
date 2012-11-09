package br.com.sgpc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Controller;

import br.com.sgpc.model.Pedido;
import br.com.sgpc.model.Produto;
import br.com.sgpc.model.Usuario;
import br.com.sgpc.service.ProdutoService;


/**
 * Controller com iterações com as telas relacionadas ao {@link PedidoController}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Controller(value = "pedidoController")
@RequestScoped
public class PedidoController implements AlphaController {
	
	private static final long serialVersionUID = 1L;
	
	private Usuario vendedor;
	private Pedido pedidoVigente;
	
	private String descricaoProduto;
	
	@Resource( name = "produtoService" ) 
	private ProdutoService produtoService;
	
	@Override
	@PostConstruct
	public void inicio() { 
		pedidoVigente = new Pedido();
	}
	
	/**
	 * Metodo do autocomplete para buscar o produto no evento onKeyPress
	 * @param query
	 * @return Lista pesquisada no banco
	 */
	public List<String> buscarProdutos(final String query) {
		final List<Produto> produtos = produtoService.buscarParcialPorDescricao( query );
		final List<String> descricoes = new ArrayList<String>();
		
		for(Produto produto : produtos) {
			descricoes.add( produto.getDescricao() );
		}
		
		return descricoes;
	}
	
	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public Pedido getPedidoVigente() {
		return pedidoVigente;
	}

	public void setPedidoVigente(Pedido pedidoVigente) {
		this.pedidoVigente = pedidoVigente;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	/*public DataModel listarPedidos(){
		model = new ListDataModel(this.pedidoDAO.buscarTodos());
		return model;
	}*/

}
