package br.com.sgpc.service;

import java.util.List;

import br.com.sgpc.dao.GenericDao;
import br.com.sgpc.model.Produto;

public interface ProdutoService extends GenericDao<Produto, Integer> {
	
	/**
	 * Busca produto pela descricao parcial
	 * @param descricao
	 * @return lista de produtos
	 */
	List<Produto> buscarParcialPorDescricao(final String descricao);
}
