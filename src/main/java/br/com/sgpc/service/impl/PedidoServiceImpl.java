package br.com.sgpc.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.sgpc.dao.PedidoDao;
import br.com.sgpc.dao.impl.GenericDaoImpl;
import br.com.sgpc.model.Pedido;
import br.com.sgpc.service.PedidoService;

@Service( value = "pedidoService" )
@Scope("prototype")
public class PedidoServiceImpl extends GenericDaoImpl<Pedido, Integer>
		implements PedidoService {

	private static final long serialVersionUID = -2320677255338836868L;
	
	@Resource( name = "pedidoDao" )
	private PedidoDao pedidoDao;

	@Override
	public List<Pedido> buscarRelatorioVendas(Date dataInicio, Date dataTermino) {
		return this.pedidoDao.buscarRelatorioVendas(dataInicio, dataTermino);
	}
	
}
