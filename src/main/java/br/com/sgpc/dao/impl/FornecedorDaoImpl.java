package br.com.sgpc.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.sgpc.dao.FornecedorDao;
import br.com.sgpc.model.Fornecedor;

@Repository( value = "fornecedorDao" )
public class FornecedorDaoImpl extends GenericDaoImpl<Fornecedor, Integer> implements FornecedorDao {

	private static final long serialVersionUID = 2095513772858509524L;
}
