package br.com.sgpc.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.sgpc.dao.ItensPedidoDao;
import br.com.sgpc.model.ItensPedido;

@Repository(value = "itensPedidoDao")
public class ItensPedidoDaoImpl extends GenericDaoImpl<ItensPedido, Integer> implements ItensPedidoDao {

	private static final long serialVersionUID = 745711965211323738L;

}

