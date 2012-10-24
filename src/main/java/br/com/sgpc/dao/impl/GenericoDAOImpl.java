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

import br.com.sgpc.dao.GenericoDAO;

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class GenericoDAOImpl<T, ID extends Serializable> implements
		GenericoDAO<T, ID> {
	
	private EntityManager entityManager;

	private final Class<T> objectClass;

	@SuppressWarnings("unchecked")
	public GenericoDAOImpl() {
		this.objectClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void atualizar(T object) {
		getEntityManager().merge(object);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(T object) {
		object = getEntityManager().merge(object);
		getEntityManager().remove(object);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public T salvar(T object) {
		getEntityManager().clear();
		getEntityManager().persist(object);
		return object;
	}

	@Override
	public T buscarPorID(ID id) {
		return (T) getEntityManager().find(objectClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> buscarTodos() {
		String sql = "select obj from " + this.objectClass.getSimpleName()
				+ "obj";
		Query q = getEntityManager().createQuery(sql);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> listarPesquisaPorParametros(String query,
			Map<String, Object> params) {

		Query sql = getEntityManager().createQuery(query);

		for (String chave : params.keySet()) {
			sql.setParameter(chave, params.get(chave));
		}

		return sql.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> listarPesquisaPorParametros(String query,
			Map<String, Object> params, int maximo, int atual) {

		Query sql = getEntityManager().createQuery(query).setMaxResults(maximo)
				.setFirstResult(atual);

		for (String chave : params.keySet()) {
			sql.setParameter(chave, params.get(chave));
		}

		return sql.getResultList();
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected EntityManager getEntityManager() {
			 if(entityManager == null)
				 throw new IllegalStateException("Erro");
			 return entityManager;
	}

	@Override
	public Class<T> getObjectClass() {
		return this.objectClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> ListarPesquisa(String sql) {
		Query query = getEntityManager().createQuery(sql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T pesquisarObjetoPorParametro(String sql, Map<String, Object> params) {
		Query query = getEntityManager().createQuery(sql);

		for (String chave : params.keySet()) {
			query.setParameter(chave, params.get(chave));
		}

		try {
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
