package br.com.sgpc.service;

import java.io.Serializable;

public interface JsonService extends Serializable {
	
	/**
	 * Metodo para transformar uma String em Json
	 * @param jsonString
	 */
	void serializerJson(final String jsonString);
	
	/**
	 * Pega um valor string no json
	 * @return
	 */
	String getString(final String node);
	
	/**
	 * Pega um valor int no json
	 * @return
	 */
	Integer getInteger(final String node);
	
	/**
	 * Pega um valor double no json
	 * @return
	 */
	Double getDouble(final String node);
}
