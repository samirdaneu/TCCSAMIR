package br.com.sgpc.util;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.sgpc.model.Fornecedor;
import br.com.sgpc.model.Produto;
import br.com.sgpc.model.Usuario;
import br.com.sgpc.model.Usuario.TipoUsuario;

public class GeradorTabelas {

	public static void main(String[] args) {
		
	/*Usuario usuario = new Usuario();
	usuario.setLogin("sdaneu");
	usuario.setSenha("samir");
	usuario.setEmail("samir.daneu@gmail.com");
	usuario.setNome("Samir Daneu");
	usuario.setAtivo(true);
	usuario.setTipoUsuario(TipoUsuario.VENDEDOR);
		
	
	
		Fornecedor fornecedor = new Fornecedor();
		
		fornecedor.setRazaoSocial("Teste 4");
				
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("SGPC");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(fornecedor);
		
		Produto produto = new Produto();
		produto.setDescricao("Ouei");
		produto.setFornecedor(fornecedor);
		produto.getFornecedor().setRazaoSocial("Samir");
		produto.setPreco(new BigDecimal("2.50"));
		produto.setQuantidade(2);
		
		
		em.persist(produto);
		em.getTransaction().commit();
		em.close();
		emf.close();*/
	}
}