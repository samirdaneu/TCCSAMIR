package br.com.sgpc.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.sgpc.dao.PedidoDao;
import br.com.sgpc.model.Pedido;

@Repository( value = "pedidoDao" )
public class PedidoDaoImpl extends GenericDaoImpl<Pedido, Integer> implements PedidoDao {

	private static final long serialVersionUID = 5297987709197264289L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> buscarRelatorioVendas(Date dataInicio, Date dataTermino) {
		final String sql = "SELECT p FROM " + Pedido.class.getName() + " p WHERE p.emissao between ? and ?";		
		final Query query = getEntityManager().createQuery(sql);
		query.setParameter(1, dataInicio);
		query.setParameter(2, dataTermino);
		return (List<Pedido>) query.getResultList();
	}
}
