package br.com.sgpc.dao;

import java.util.List;

import br.com.sgpc.model.Produto;

/**
 * Dao com iteracoes com banco de dados para a entidade {@link Produto}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
public interface ProdutoDao extends GenericDao<Produto, Integer> {
	
	/**
	 * Busca produto pela descricao parcial
	 * @param descricao
	 * @return lista de produtos
	 */
	List<Produto> buscarParcialPorDescricao(final String descricao);
	
	Produto buscarUnicoPorDescricao(final String descricao);
	
	List<Produto> buscarProdutosQuantidadeLimiteUltrapassada();
}
