package br.com.sgpc.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.sgpc.dao.PedidoDao;
import br.com.sgpc.model.Pedido;

/**
 * Implentacao do {@link PedidoDao}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Repository
public class PedidoDaoImpl extends GenericDaoImpl<Pedido, Integer> implements PedidoDao {

	private static final long serialVersionUID = 5297987709197264289L;
}
