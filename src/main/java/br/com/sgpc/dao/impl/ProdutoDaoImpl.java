package br.com.sgpc.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.sgpc.dao.ProdutoDao;
import br.com.sgpc.model.Produto;

@Repository( value = "produtoDao" )
public class ProdutoDaoImpl extends GenericDaoImpl<Produto, Integer> implements ProdutoDao {

	private static final long serialVersionUID = -4276880275402474222L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> buscarParcialPorDescricao(final String descricao) {
		final String sql = "SELECT p FROM " + Produto.class.getName() + " p WHERE p.descricao LIKE :descricao";		
		final Query query = getEntityManager().createQuery(sql);
		query.setParameter("descricao", "%" + descricao + "%");		
		return (List<Produto>) query.getResultList();
	}

	@Override
	public Produto buscarUnicoPorDescricao(String descricao) {
		final String sql = "SELECT p FROM " + Produto.class.getName() + " p WHERE p.descricao LIKE :descricao";
		final Query query = getEntityManager().createQuery(sql);
		query.setParameter("descricao", descricao);		
		return (Produto) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> buscarProdutosQuantidadeLimiteUltrapassada() {		
		final String sql = "SELECT p FROM " + Produto.class.getName() + " p WHERE p.quantidade <= p.quantidadeLimite";		
		final Query query = getEntityManager().createQuery(sql);
		return (List<Produto>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> buscarParcialPorcodigo(String codigo) {
		final String sql = "SELECT p FROM " + Produto.class.getName() + " p WHERE p.codigo LIKE :codigo";		
		final Query query = getEntityManager().createQuery(sql);
		query.setParameter("codigo", "%" + codigo + "%");		
		return (List<Produto>) query.getResultList();
	}

	@Override
	public Produto buscarUnicoPorCodigoo(String codigo) {
		final String sql = "SELECT p FROM " + Produto.class.getName() + " p WHERE p.codigo LIKE :codigo";
		final Query query = getEntityManager().createQuery(sql);
		query.setParameter("codigo", codigo);		
		return (Produto) query.getSingleResult();
	}
}
