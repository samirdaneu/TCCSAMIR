package br.com.sgpc.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.sgpc.model.Fornecedor;

public class GeradorTabelas {

	public static void main(String[] args) {
		
	/*Usuario usuario = new Usuario();
	usuario.setLogin("sdaneu");
	usuario.setSenha("samir");
	usuario.setEmail("samir.daneu@gmail.com");
	usuario.setNome("Samir Daneu");
	usuario.setAtivo(true);
	usuario.setTipoUsuario(TipoUsuario.VENDEDOR);*/
		
		Fornecedor fornecedor = new Fornecedor();
		
		fornecedor.setRazaoSocial("Teste 2");
				
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("SGPC");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(fornecedor);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}