package br.com.sgpc.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.sgpc.dao.MovimentacaoProdutoDao;
import br.com.sgpc.model.MovimentacaoProduto;

@Repository( value = "movimentacaoProdutoDao" )
public class MovimentacaoDaoImpl  extends GenericDaoImpl<MovimentacaoProduto, Integer> implements MovimentacaoProdutoDao {

	private static final long serialVersionUID = 152352266901832745L;

}
