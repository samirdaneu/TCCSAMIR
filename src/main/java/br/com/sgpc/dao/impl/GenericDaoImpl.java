
package br.com.sgpc.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.sgpc.dao.GenericDao;

/**
 * Implentacao do {@link GenericDao}
 * @author Samir Daneu
 * @since 01/10/2012
 *
 */
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {
	
	private static final long serialVersionUID = -635827887836665583L;
	
	@PersistenceContext
	private EntityManager entityManager;

	public GenericDaoImpl() { }
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void atualizar(T entity) {
		getEntityManager().merge(entity);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(T entity) {
		entity = getEntityManager().merge(entity);
		getEntityManager().remove(entity);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public T salvar(T entity) {
		getEntityManager().clear();
		getEntityManager().persist(entity);
		getEntityManager().flush();
		return entity;
	}

	@Override
	public T buscarPorID(ID id) {
		return (T) entityManager.find(getObjectClass(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> buscarTodos() {
		String sql = "select obj from " + getObjectClass().getSimpleName() + " obj";
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> listarPesquisaPorParametros(final String query, final Map<String, Object> params) {

		Query sql = getEntityManager().createQuery( query );

		for (String chave : params.keySet()) {
			sql.setParameter(chave, params.get(chave));
		}

		return sql.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<T> getObjectClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> ListarPesquisa(final String sql) {
		Query query = getEntityManager().createQuery(sql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T pesquisarObjetoPorParametro(final String sql, final Map<String, Object> params) {
		final Query query = entityManager.createQuery( sql );

		for (String chave : params.keySet()) {
			query.setParameter(chave, params.get(chave));
		}

		try {
			return (T) query.getSingleResult();
		} catch (NoResultException ignored) {
			return null;
		}
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/**
	 * Metodo que retorna uma erro caso estejamos sem entityManager
	 * @return
	 */
	protected EntityManager getEntityManager() {
		 if(entityManager == null)
			 throw new IllegalStateException("Erro! não encontramos o entityManager");
		 return entityManager;
	}
}