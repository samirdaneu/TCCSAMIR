package br.com.sgpc.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.sgpc.dao.ItensPedidoDao;
import br.com.sgpc.model.ItensPedido;
import br.com.sgpc.model.Pedido;

@Repository(value = "itensPedidoDao")
public class ItensPedidoDaoImpl extends GenericDaoImpl<ItensPedido, Integer> implements ItensPedidoDao {

	private static final long serialVersionUID = 745711965211323738L;

	@SuppressWarnings("unchecked")
	@Override
	public List<ItensPedido> buscarPorPedido(Pedido pedido) {
		final String sql = "SELECT ip FROM " + ItensPedido.class.getName() + " ip WHERE ip.pedido = :pedido";
		final Query query = getEntityManager().createQuery(sql);
		query.setParameter("pedido", pedido.getId());		
		return (List<ItensPedido>) query.getResultList();		
	}
	

}

