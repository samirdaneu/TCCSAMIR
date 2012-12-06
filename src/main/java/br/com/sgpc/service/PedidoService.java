package br.com.sgpc.service;

import java.util.Date;
import java.util.List;

import br.com.sgpc.dao.GenericDao;
import br.com.sgpc.model.Pedido;

public interface PedidoService extends GenericDao<Pedido, Integer> {
	
	List<Pedido> buscarRelatorioVendas(Date dataInicio, Date dataTermino);

}
;