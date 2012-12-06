package br.com.sgpc.service;

import java.util.List;

import br.com.sgpc.dao.GenericDao;
import br.com.sgpc.model.Produto;

public interface ProdutoService extends GenericDao<Produto, Integer> {
	
	List<Produto> buscarParcialPorDescricao(final String descricao);
	
	List<Produto> buscarParcialPorCodigo(final String codigo);
	
	Produto buscarUnicoPorCodigo(final String codigo);
	
	Produto buscarUnicoPorDescricao(final String descricao);
	
	List<Produto> buscarProdutosQuantidadeLimiteUltrapassada();
	
	String verificaCodigoDescricaoDuplicado(Produto produto);
}
