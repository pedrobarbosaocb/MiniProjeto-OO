package teste;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controle.*;
import modelo.*;

public class TestesAmigos {
	private ControleDados controleDados;
	
	@BeforeEach
	void criarAtributos() {
		controleDados = new ControleDados();
	}
	
	@Test
	void testeCriarAmigo() {
		Usuario user = new Usuario("nome", "email", "01/01/2000", "senha");
		boolean teste = controleDados.criarAmigo(user, "amigo", "amigo@gmail.com", "(61)91111-9999");
		assertTrue(teste);
		assertTrue(controleDados.getAmigos().get(0).getNome().equals("amigo"));
		assertTrue(controleDados.getAmigos().get(0).getEmail().equals("amigo@gmail.com"));
		assertTrue(controleDados.getAmigos().get(0).getTelefone().equals("(61)91111-9999"));
		assertTrue(controleDados.getAmigos().get(0).getAmigosDe().get(0) == user);
	}
	
	@Test 
	void testeCriarAmigoSemParametro() {
		Usuario user = new Usuario("nome", "email", "01/01/2000", "senha");
		boolean teste = controleDados.criarAmigo(null, "amigo", "amigo@gmail.com", "(61)91111-9999");
		assertFalse(teste);
		boolean teste2 = controleDados.criarAmigo(user, null, "amigo@gmail.com", "(61)91111-9999");
		assertFalse(teste2);
		boolean teste3 = controleDados.criarAmigo(user, "amigo", "", "(61)91111-9999");
		assertFalse(teste3);
		boolean teste4 = controleDados.criarAmigo(user, "amigo", "amigo@gmail.com", null);
		assertFalse(teste4);
	}
	
	@Test 
	void testeEditarAmigo() {
		Usuario user = new Usuario("nome", "email", "01/01/2000", "senha");
		controleDados.criarAmigo(user, "amigo", "amigo@gmail.com", "(61)91111-9999");
		Amigo amigo = controleDados.getAmigos().get(0);
		controleDados.editarAmigo(amigo.getId(), "test", "emailTeste", "telefone");
		assertTrue(amigo.getNome().equals("test"));
		assertTrue(amigo.getEmail().equals("emailTeste"));
		assertTrue(amigo.getTelefone().equals("telefone"));
	}
	
	@Test
	void testeExcluirAmigo() {
		Usuario user = new Usuario("nome", "email", "01/01/2000", "senha");
		controleDados.criarAmigo(user, "amigo", "amigo@gmail.com", "(61)91111-9999");
		Amigo amigo = controleDados.getAmigos().get(0);
		controleDados.excluirAmigo(amigo.getId());
		assertTrue(controleDados.getAmigos().size() == 0);
	}
	
	
	@Test
	void testeBuscarAmigo() {
		Usuario user = new Usuario("nome", "email", "01/01/2000", "senha");
		controleDados.criarAmigo(user, "amigo", "amigo@gmail.com", "(61)91111-9999");
		controleDados.criarAmigo(user, "amigo2", "amigo2@gmail.com", "(61)91111-9999");
		controleDados.criarAmigo(user, "amigo3", "amigo3@gmail.com", "(61)91111-9999");
		
		ArrayList<Amigo> amigos = controleDados.getAmigos();
		controleDados.excluirAmigo(amigos.get(2).getId());
		ControleAmigos controleAmigos = new ControleAmigos(controleDados);
		/**
		 * Teste com a busca por id
		 */
		for(Amigo amigo: amigos) {
			Amigo recuperado = controleAmigos.getAmigo(amigo.getId());
			assertTrue(amigo == recuperado);
		}
		/**
		 * Teste com a busca por email
		 */
		for(Amigo amigo: amigos) {
			Amigo recuperado = controleAmigos.getAmigo(amigo.getEmail());
			assertTrue(amigo == recuperado);
		}
		/**
		 * Teste com id's que não existem
		 */
		Amigo recuperado = controleAmigos.getAmigo(20);
		assertTrue(recuperado == null);
		Amigo recuperado2 = controleAmigos.getAmigo(-20);
		assertTrue(recuperado2 == null);
	}
	
	@Test
	void testeVerificarAmigoExiste() {
		Usuario user = new Usuario("nome", "email", "01/01/2000", "senha");
		controleDados.criarAmigo(user, "amigo", "amigo@gmail.com", "(61)91111-9999");
		ControleAmigos controleAmigos = new ControleAmigos(controleDados);

		assertTrue(controleAmigos.amigoExiste("amigo@gmail.com"));
		assertFalse(controleAmigos.amigoExiste("teste@gmail.com"));
	}
}
