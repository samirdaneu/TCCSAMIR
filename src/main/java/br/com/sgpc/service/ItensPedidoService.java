package br.com.sgpc.service;

import java.util.List;

import br.com.sgpc.dao.GenericDao;
import br.com.sgpc.model.ItensPedido;
import br.com.sgpc.model.Pedido;

public interface ItensPedidoService extends GenericDao<ItensPedido, Integer> {
	
	List<ItensPedido> buscarPorPedido(Pedido pedido);

}
