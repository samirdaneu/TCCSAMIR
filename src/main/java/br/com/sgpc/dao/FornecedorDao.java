package br.com.sgpc.dao;

import org.springframework.stereotype.Repository;

import br.com.sgpc.model.Fornecedor;

/**
 * Dao com iteracoes com banco de dados para a entidade {@link Fornecedor}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Repository
public interface FornecedorDao extends GenericDao<Fornecedor, Integer> {

}
