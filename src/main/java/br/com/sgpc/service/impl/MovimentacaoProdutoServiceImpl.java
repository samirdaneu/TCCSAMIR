package br.com.sgpc.service.impl;

import org.springframework.stereotype.Service;

import br.com.sgpc.dao.impl.GenericDaoImpl;
import br.com.sgpc.model.MovimentacaoProduto;
import br.com.sgpc.service.MovimentacaoProdutoService;

@Service( value = "movimentacaoProdutoService" )
public class MovimentacaoProdutoServiceImpl extends GenericDaoImpl<MovimentacaoProduto, Integer>
implements MovimentacaoProdutoService {

	private static final long serialVersionUID = -7329298188334446973L;

}
