package br.com.sgpc.dao;

import org.springframework.stereotype.Repository;

import br.com.sgpc.model.Pedido;

/**
 * Dao com iteracoes com banco de dados para a entidade {@link Pedido}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Repository
public interface PedidoDao extends GenericDao<Pedido, Integer> {

}
