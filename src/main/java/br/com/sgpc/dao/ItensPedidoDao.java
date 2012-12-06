package br.com.sgpc.dao;

import java.util.List;

import br.com.sgpc.model.ItensPedido;
import br.com.sgpc.model.Pedido;

public interface ItensPedidoDao extends GenericDao<ItensPedido, Integer> {
	
	List<ItensPedido> buscarPorPedido(Pedido pedido);

}
