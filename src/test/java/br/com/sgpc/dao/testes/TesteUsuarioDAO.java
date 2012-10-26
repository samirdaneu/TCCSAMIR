package br.com.sgpc.dao.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sgpc.dao.UsuarioDao;
import br.com.sgpc.model.Usuario;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:**/applicationContext*.xml"})
public class TesteUsuarioDAO {

	private UsuarioDao usuarioDAO;
	private Integer id = 1;

	private Usuario getUsuario(){
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setEmail("samir.daneu@gmail.com");
		usuario.setLogin("samdaneu");
		usuario.setSenha("samdaneu");
		usuario.setNome("Samir Campos Daneu");
		return usuario;
	}
	
	@Test @Ignore
	public void testaSalvar(){
		Usuario usuario = null;
		usuario = usuarioDAO.salvar(getUsuario());
		assertNotNull(usuario);
		assertEquals(id, usuario.getId());
		assertEquals("Samir Campos Daneu", usuario.getNome());
		assertEquals("samir.daneu@gmail.com", usuario.getEmail());				
	}
	
	@Test @Ignore
	public void testaAtualizar(){
		Usuario usuario = usuarioDAO.buscarPorID(id);
		usuario.setNome("Samir Daneu");
		usuarioDAO.atualizar(usuario);
		assertNotNull(usuario);
	}
}
