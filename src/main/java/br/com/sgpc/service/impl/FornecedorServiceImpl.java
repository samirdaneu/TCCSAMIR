package br.com.sgpc.service.impl;

import org.springframework.stereotype.Service;

import br.com.sgpc.dao.impl.GenericDaoImpl;
import br.com.sgpc.model.Fornecedor;
import br.com.sgpc.service.FornecedorService;

@Service( value = "fornecedorService" )
public class FornecedorServiceImpl extends GenericDaoImpl<Fornecedor, Integer>
		implements FornecedorService {

}
