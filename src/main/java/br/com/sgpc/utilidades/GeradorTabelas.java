package br.com.sgpc.utilidades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.sgpc.model.Usuario;
import br.com.sgpc.model.Usuario.TipoUsuario;

public class GeradorTabelas {

	public static void main(String[] args) {
		
	Usuario usuario = new Usuario();
	usuario.setLogin("sdaneu");
	usuario.setSenha("samir");
	usuario.setEmail("samir.daneu@gmail.com");
	usuario.setNome("Samir Daneu");
	usuario.setAtivo(true);
	usuario.setTipoUsuario(TipoUsuario.VENDEDOR);
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("SGPC");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}