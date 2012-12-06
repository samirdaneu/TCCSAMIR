package br.com.sgpc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.sgpc.dao.UsuarioDao;
import br.com.sgpc.dao.impl.GenericDaoImpl;
import br.com.sgpc.model.Usuario;
import br.com.sgpc.service.UsuarioService;

@Service( value = "usuarioService" )
@Scope("prototype")
public class UsuarioServiceImpl extends GenericDaoImpl<Usuario, Integer>
		implements UsuarioService {

	private static final long serialVersionUID = 2453278820347973699L;
	
	@Resource( name = "usuarioDao" )
	private UsuarioDao usuarioDao;
	
	@Override
	public boolean verificarSeLoginExiste(final String login) {
		return usuarioDao.verificarSeLoginExiste(login);		
	}
	
	@Override
	public Usuario procurarUsuarioPeloLogin(final String login) {
		return usuarioDao.procurarUsuarioPeloLogin(login);		
	}

	@Override
	public List<Usuario> buscarAdministradoresAtivos() {
		return usuarioDao.buscarAdministradoresAtivos();
	}

	@Override
	public String geraSenhaAleatoria() {
		String seq = "abcdefghijklmnopqqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
		int len =
			(int) Math.round(Math.random() * 8);
		char senha[] = new char[len];
		for (int i = 0; i < len; i++) {
			senha[i] = seq.charAt((int) (Math.random() * (seq.length() - 1)));
		}
		return String.valueOf(senha);
	}

	@Override
	public boolean isAdmin(Usuario usuario) {
		return this.usuarioDao.isAdmin(usuario);
	}

}
