package br.com.sgpc.dao;

import java.util.Date;
import java.util.List;

import br.com.sgpc.model.Pedido;

public interface PedidoDao extends GenericDao<Pedido, Integer> {
	
	List<Pedido> buscarRelatorioVendas(Date dataInicio, Date dataTermino);

}
