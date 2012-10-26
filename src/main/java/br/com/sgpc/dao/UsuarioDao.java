package br.com.sgpc.dao;

import org.springframework.stereotype.Repository;

import br.com.sgpc.model.Usuario;

/**
 * Dao com iteracoes com banco de dados para a entidade {@link Usuario}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Repository
public interface UsuarioDao extends GenericDao<Usuario, Integer> {

}
