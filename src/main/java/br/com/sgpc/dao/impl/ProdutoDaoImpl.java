package br.com.sgpc.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.sgpc.dao.PedidoDao;
import br.com.sgpc.dao.ProdutoDao;
import br.com.sgpc.model.Produto;

/**
 * Implentacao do {@link PedidoDao}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Repository( value = "produtoDao" )
public class ProdutoDaoImpl extends GenericDaoImpl<Produto, Integer> implements ProdutoDao {

	private static final long serialVersionUID = -4276880275402474222L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> buscarParcialPorDescricao(final String descricao) {
		
		final String sql = "SELECT p FROM PRODUTO p WHERE p.descricao LIKE :descricao";
		
		final Query query = getEntityManager().createQuery( sql );
		query.setParameter("descricao", "%" + descricao + "%");
		
		return (List<Produto>) query.getResultList();
	}
}
