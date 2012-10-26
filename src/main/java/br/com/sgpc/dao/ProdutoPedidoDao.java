package br.com.sgpc.dao;

import org.springframework.stereotype.Repository;

import br.com.sgpc.model.ProdutoPedido;
import br.com.sgpc.model.ProdutoPedidoPK;

/**
 * Dao com iteracoes com banco de dados para a entidade {@link ProdutoPedido}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Repository
public interface ProdutoPedidoDao extends GenericDao<ProdutoPedido, ProdutoPedidoPK> {

}
