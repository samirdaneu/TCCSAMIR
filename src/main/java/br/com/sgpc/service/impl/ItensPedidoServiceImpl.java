package br.com.sgpc.service.impl;

import org.springframework.stereotype.Service;

import br.com.sgpc.dao.impl.GenericDaoImpl;
import br.com.sgpc.model.ItensPedido;
import br.com.sgpc.service.ItensPedidoService;

@Service( value = "itensPedidoService" )
public class ItensPedidoServiceImpl extends GenericDaoImpl<ItensPedido, Integer>
implements ItensPedidoService {

	private static final long serialVersionUID = 3999446314365226657L;

}


