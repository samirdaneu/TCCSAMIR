package br.com.sgpc.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.sgpc.dao.ClienteDao;
import br.com.sgpc.model.Cliente;


/**
 * Implentacao do {@link ClienteDao}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Repository
public class ClienteDaoImpl extends GenericDaoImpl<Cliente, Integer> implements ClienteDao {

	private static final long serialVersionUID = 938100030575902274L;
}
