package br.com.sgpc.service.impl;

import org.springframework.stereotype.Service;

import br.com.sgpc.dao.impl.GenericDaoImpl;
import br.com.sgpc.model.Pedido;
import br.com.sgpc.service.PedidoService;

@Service( value = "pedidoService" )
public class PedidoServiceImpl extends GenericDaoImpl<Pedido, Integer>
		implements PedidoService {

	private static final long serialVersionUID = -2320677255338836868L;
	
}
