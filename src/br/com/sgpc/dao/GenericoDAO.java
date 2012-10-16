package br.com.sgpc.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericoDAO <T, ID extends Serializable>{
	
	public void atualizar(T object);
	
	public void excluir(T object);
	
	public T salvar(T object);
		
	public T buscarPorID(ID id);
	
	public List<T> buscarTodos();
	
	public Class<T> getObjectClass();
	
	public List<T> listarPesquisaPorParametros(String query,
			Map<String, Object> params);
	
	public List<T> listarPesquisaPorParametros(String query,
			Map<String, Object> params, int maximo, int atual);
	
	public List<T> ListarPesquisa(String sql);
	
	public T pesquisarObjetoPorParametro(String sql, Map<String,Object> params);
}
