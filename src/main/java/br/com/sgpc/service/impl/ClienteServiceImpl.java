package br.com.sgpc.service.impl;

import org.springframework.stereotype.Service;

import br.com.sgpc.dao.impl.GenericDaoImpl;
import br.com.sgpc.model.Cliente;
import br.com.sgpc.service.ClienteService;

@Service( value = "clienteService" )
public class ClienteServiceImpl extends GenericDaoImpl<Cliente, Integer>
		implements ClienteService {

}
