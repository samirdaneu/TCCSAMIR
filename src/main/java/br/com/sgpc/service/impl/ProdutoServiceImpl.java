package br.com.sgpc.service.impl;

import org.springframework.stereotype.Service;

import br.com.sgpc.dao.impl.GenericDaoImpl;
import br.com.sgpc.model.Produto;
import br.com.sgpc.service.ProdutoService;

@Service( value = "produtoService" )
public class ProdutoServiceImpl extends GenericDaoImpl<Produto, Integer>
		implements ProdutoService {

}
