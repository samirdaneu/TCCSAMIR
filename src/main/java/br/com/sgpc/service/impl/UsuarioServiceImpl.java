package br.com.sgpc.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.sgpc.dao.impl.GenericDaoImpl;
import br.com.sgpc.model.Usuario;
import br.com.sgpc.service.LoginService;
import br.com.sgpc.service.UsuarioService;

/**
 * Implementacao de {@link LoginService}
 * @author Guilherme Gambeti
 * @since 25/10/2012
 *
 */
@Service( value = "usuarioService" )
public class UsuarioServiceImpl extends GenericDaoImpl<Usuario, Integer>
		implements UsuarioService {

	private static final long serialVersionUID = 2453278820347973699L;
	
	@Override
	public boolean verificarSeLoginExiste(final String login) {
		
		boolean existe = procurarUsuarioPeloLogin( login ) != null;
		return existe;
	}
	
	@Override
	public Usuario procurarUsuarioPeloLogin(final String login) {
		
		if( login == null ) {
			throw new IllegalArgumentException(); // TODO: colocar bundle pelo eclipse na app
		}
		
		final String query = "select u from Usuario u where u.login = :login ";
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("login", login);		
		
		return pesquisarObjetoPorParametro(query, params);
	}

}
