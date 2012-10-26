package br.com.sgpc.dao;

import org.springframework.stereotype.Repository;

import br.com.sgpc.model.Cliente;

/**
 * Dao com iteracoes com banco de dados para a entidade {@link Cliente}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Repository
public interface ClienteDao extends GenericDao<Cliente, Integer> {
	
}
