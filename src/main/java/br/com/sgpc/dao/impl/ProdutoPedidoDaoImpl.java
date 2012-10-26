package br.com.sgpc.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.sgpc.dao.ProdutoPedidoDao;
import br.com.sgpc.model.ProdutoPedido;
import br.com.sgpc.model.ProdutoPedidoPK;

/**
 * Implentacao do {@link ProdutoPedidoDao}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Repository
public class ProdutoPedidoDaoImpl extends GenericDaoImpl<ProdutoPedido, ProdutoPedidoPK> implements ProdutoPedidoDao {

	private static final long serialVersionUID = 2535707997114055162L;
}
