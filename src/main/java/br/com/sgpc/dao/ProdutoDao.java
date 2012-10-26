package br.com.sgpc.dao;

import org.springframework.stereotype.Repository;

import br.com.sgpc.model.Produto;

/**
 * Dao com iteracoes com banco de dados para a entidade {@link Produto}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Repository
public interface ProdutoDao extends GenericDao<Produto, Integer> {

}
