package br.com.sgpc.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.sgpc.dao.UsuarioDao;
import br.com.sgpc.model.Usuario;

/**
 * Implentacao do {@link UsuarioDao}
 * 
 * @author Samir Daneu
 * @since 01/10/2012
 * 
 */
@Repository(value = "usuarioDao")
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Integer> implements
		UsuarioDao {

	private static final long serialVersionUID = 4240090695801913199L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> buscarAdministradoresAtivos() {
		final String sql = "SELECT u FROM " + Usuario.class.getName()
				+ " u WHERE u.tipoUsuario = ? and u.ativo = ?";

		final Query query = getEntityManager().createQuery(sql);
		query.setParameter(1, Usuario.TipoUsuario.ADMINISTRADOR);
		query.setParameter(2, true);

		return (List<Usuario>) query.getResultList();
	}

	@Override
	public boolean verificarSeLoginExiste(String login) {
		boolean existe = procurarUsuarioPeloLogin( login ) != null;
		return existe;
	}

	@Override
	public Usuario procurarUsuarioPeloLogin(String login) {
		if( login == null ) {
			throw new IllegalArgumentException();
		}
		
		final String query = "select u from Usuario u where u.login = :login ";
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("login", login);		
		
		return pesquisarObjetoPorParametro(query, params);
	}
}
