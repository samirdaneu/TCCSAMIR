package br.com.sgpc.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.sgpc.dao.ProdutoPedidoDao;
import br.com.sgpc.model.ItensPedido;
import br.com.sgpc.model.ItensPedidoPK;

/**
 * Implentacao do {@link ProdutoPedidoDao}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Repository( value = "produtoPedidoDao" )
public class ProdutoPedidoDaoImpl extends GenericDaoImpl<ItensPedido, ItensPedidoPK> implements ProdutoPedidoDao {

	private static final long serialVersionUID = 2535707997114055162L;
}
