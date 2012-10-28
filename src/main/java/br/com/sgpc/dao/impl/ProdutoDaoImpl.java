package br.com.sgpc.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.sgpc.dao.PedidoDao;
import br.com.sgpc.dao.ProdutoDao;
import br.com.sgpc.model.Produto;

/**
 * Implentacao do {@link PedidoDao}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Repository( value = "produtoDao" )
public class ProdutoDaoImpl extends GenericDaoImpl<Produto, Integer> implements ProdutoDao {

	private static final long serialVersionUID = -4276880275402474222L;
}
