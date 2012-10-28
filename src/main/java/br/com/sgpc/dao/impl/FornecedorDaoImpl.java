package br.com.sgpc.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.sgpc.dao.FornecedorDao;
import br.com.sgpc.model.Fornecedor;

/**
 * Implentacao do {@link FornecedorDao}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Repository( value = "fornecedorDao" )
public class FornecedorDaoImpl extends GenericDaoImpl<Fornecedor, Integer> implements FornecedorDao {

	private static final long serialVersionUID = 2095513772858509524L;
}
