package br.com.sgpc.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.com.sgpc.dao.GenericDao;
import br.com.sgpc.model.Pedido;
import br.com.sgpc.util.FacesUtil;


/**
 * Controller com iterações com as telas relacionadas ao {@link PedidoController}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@ManagedBean(name = "pedidoController")
@RequestScoped
public class PedidoController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Pedido pedido;
	
	private GenericDao<Pedido, Integer> pedidoDAO;
	
	private DataModel model;
	
	public PedidoController() {
		this.setPedido(new Pedido());
	}
	
	public String novoPedido(){
		this.setPedido(new Pedido());
		return "formPedido";
	}
	
	public DataModel listarPedidos(){
		model = new ListDataModel(this.pedidoDAO.buscarTodos());
		return model;
	}
	
	public String salvarPedido(){
		try {
			if (getPedido().getId() == null){
				pedidoDAO.salvar(getPedido());
				FacesUtil.mensagemInformacao("Pedido cadastrado com sucesso!");
			} else {
				pedidoDAO.atualizar(getPedido());
				FacesUtil.mensagemInformacao("Pedido cadastrado com sucesso!");
			}
		} catch (Exception e) {
			FacesUtil.mensagemErro("Erro ao salvar/atualizar pedido");
			e.printStackTrace();
		}			
		
		return "ok";
	}
	
	public Pedido getPedidoParaEditarExcluir(){
		Pedido pedido = (Pedido) model.getRowData();
		return pedido;
	}
	
	public String editar(){
		setPedido(getPedidoParaEditarExcluir());
		return "formPedido";
	}
	
	public String excluir(){
		Pedido pedido = getPedidoParaEditarExcluir();
		this.pedidoDAO.excluir(pedido);
		return "mostrarPedidos";
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

}
