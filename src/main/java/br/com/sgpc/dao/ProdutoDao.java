package br.com.sgpc.dao;

import java.util.List;

import br.com.sgpc.model.Produto;

public interface ProdutoDao extends GenericDao<Produto, Integer> {

	List<Produto> buscarParcialPorDescricao(final String descricao);
	
	Produto buscarUnicoPorDescricao(final String descricao);
	
	List<Produto> buscarParcialPorcodigo(final String codigo);
	
	Produto buscarUnicoPorCodigoo(final String codigo);
	
	List<Produto> buscarProdutosQuantidadeLimiteUltrapassada();
	
	String verificaCodigoDescricaoDuplicado(Produto produto);
}
