package br.com.sgpc.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.sgpc.dao.UsuarioDao;
import br.com.sgpc.model.Usuario;

/**
 * Implentacao do {@link UsuarioDao}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Repository
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Integer> implements UsuarioDao {

	private static final long serialVersionUID = 4240090695801913199L;
}
