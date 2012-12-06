package br.com.sgpc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.sgpc.dao.ItensPedidoDao;
import br.com.sgpc.dao.impl.GenericDaoImpl;
import br.com.sgpc.model.ItensPedido;
import br.com.sgpc.model.Pedido;
import br.com.sgpc.service.ItensPedidoService;

@Service( value = "itensPedidoService" )
@Scope("prototype")
public class ItensPedidoServiceImpl extends GenericDaoImpl<ItensPedido, Integer>
implements ItensPedidoService {

	private static final long serialVersionUID = 3999446314365226657L;
	
	@Resource( name = "itensPedidoDao" )
	private ItensPedidoDao itensPedidoDao;

	@Override
	public List<ItensPedido> buscarPorPedido(Pedido pedido) {
		return this.itensPedidoDao.buscarPorPedido(pedido);
	}

}


