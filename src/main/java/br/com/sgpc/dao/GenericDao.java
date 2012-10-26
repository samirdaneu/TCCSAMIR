package br.com.sgpc.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Dao Generico com iteracoes genericas com banco de dados
 * @author Samir Daneu
 * @since 01/10/2012
 *
 * @param <T> - Tipo da classe
 * @param <ID> - Tipo do Id da entidade
 */
public interface GenericDao <T, ID extends Serializable> extends Serializable {
	
	void atualizar(T entity);
	
	void excluir(T entity);
	
	T salvar(T entity);
		
	T buscarPorID(ID id);
	
	List<T> buscarTodos();
	
	Class<T> getObjectClass();
	
	List<T> listarPesquisaPorParametros(final String query, final Map<String, Object> params);
	
	List<T> listarPesquisaPorParametros(final String query, final Map<String, Object> params, int maximo, int atual);
	
	List<T> ListarPesquisa(final String sql);
	
	T pesquisarObjetoPorParametro(final String sql, final Map<String,Object> params);
}
