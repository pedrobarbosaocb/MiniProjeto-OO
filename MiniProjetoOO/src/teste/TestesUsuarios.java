package teste;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controle.*;
import modelo.*;

public class TestesUsuarios {
	private ControleDados controleDados;
	
	@BeforeEach
	void criarAtributos() {
		controleDados = new ControleDados();
	}
	
	@Test
	void testeCriarUsuario() {
		boolean teste = controleDados.criarUsuario("nome", "email", "01/01/2000", "senha");
		assertTrue(teste);
		assertTrue(controleDados.getUsuarios().get(0).getNome().equals("nome"));
		assertTrue(controleDados.getUsuarios().get(0).getEmail().equals("email"));
		assertTrue(controleDados.getUsuarios().get(0).getAniversario().equals("01/01/2000"));
		assertTrue(controleDados.getUsuarios().get(0).getSenha().equals("senha"));
	}
	
	@Test 
	void testeCriarUsuarioSemParametro() {
		boolean teste = controleDados.criarUsuario(null, "email", "01/01/2000", "senha");
		assertFalse(teste);
		boolean teste2 = controleDados.criarUsuario("nome", null, "01/01/2000", "senha");
		assertFalse(teste2);
		boolean teste3 = controleDados.criarUsuario("nome", "email", "", "senha");
		assertFalse(teste3);
		boolean teste4 = controleDados.criarUsuario("nome", "email", "01/01/2000", "");
		assertFalse(teste4);
	}
	
	@Test 
	void testeEditarUsuario() {
		controleDados.criarUsuario("nome", "email", "01/01/2000", "senha");
		Usuario user = controleDados.getUsuarios().get(0);
		controleDados.editarUsuario(user.getId(), "teste", "emailTeste", "02/02/1999", "teste");
		assertTrue(user.getNome().equals("teste"));
		assertTrue(user.getEmail().equals("emailTeste"));
		assertTrue(user.getAniversario().equals("02/02/1999"));
		assertTrue(user.getSenha().equals("teste"));
	}
	
	@Test
	void testeExcluirUsuario() {
		controleDados.criarUsuario("nome", "email", "01/01/2000", "senha");
		Usuario user = controleDados.getUsuarios().get(0);
		controleDados.excluirUsuario(user.getId());
		assertTrue(controleDados.getAmigos().size() == 0);
	}
	
	
	@Test
	void testeBuscarUsuario() {
		controleDados.criarUsuario("nome", "email", "01/01/2000", "senha");
		controleDados.criarUsuario("nome2", "email2", "01/01/2000", "senha2");
		controleDados.criarUsuario("nome3", "email3", "01/01/2000", "senha3");
		
		ArrayList<Usuario> usuarios = controleDados.getUsuarios();
		controleDados.excluirUsuario(usuarios.get(2).getId());
		ControleUsuarios controleUsuarios = new ControleUsuarios(controleDados);
		/**
		 * Teste com a busca por id
		 */
		for(Usuario usuario: usuarios) {
			Usuario recuperado = controleUsuarios.getUsuario(usuario.getId());
			assertTrue(usuario == recuperado);
		}
		/**
		 * Teste com a busca por email
		 */
		for(Usuario usuario: usuarios) {
			Usuario recuperado = controleUsuarios.getUsuario(usuario.getEmail());
			assertTrue(usuario == recuperado);
		}
		/**
		 * Teste com id's que não existem
		 */
		Usuario recuperado = controleUsuarios.getUsuario(20);
		assertTrue(recuperado == null);
		Usuario recuperado2 = controleUsuarios.getUsuario(-20);
		assertTrue(recuperado2 == null);
	}
	
	@Test
	void testeVerificarUsuarioExiste() {
		controleDados.criarUsuario("nome", "email", "01/01/2000", "senha");
		ControleUsuarios controleUsuarios = new ControleUsuarios(controleDados);

		assertTrue(controleUsuarios.usuarioExiste("email"));
		assertFalse(controleUsuarios.usuarioExiste("teste"));
	}
	
	@Test
	void testeVerificarLoginUsuario() {
		controleDados.criarUsuario("nome", "email", "01/01/2000", "senha");
		ControleUsuarios controleUsuarios = new ControleUsuarios(controleDados);
		
		assertTrue(controleUsuarios.verificarUsuario("email", "senha"));
		assertFalse(controleUsuarios.verificarUsuario("email1", "senha"));
		assertFalse(controleUsuarios.verificarUsuario("email", "senha1"));
		assertFalse(controleUsuarios.verificarUsuario(null, "senha"));
		assertFalse(controleUsuarios.verificarUsuario("email", ""));
	}
	
	@Test 
	void testeExcluirAmigoUsuario() {
		controleDados.criarUsuario("nome", "email", "01/01/2000", "senha");
		ControleUsuarios controleUsuarios = new ControleUsuarios(controleDados);
		
		Usuario user = controleUsuarios.getUsuarios().get(0);
		controleDados.criarAmigo(user,  "amigo1", "email1", "telefone1");
		controleDados.criarAmigo(user,  "amigo2", "email2", "telefone2");
		controleDados.criarAmigo(user,  "amigo3", "email3", "telefone3");
		
		controleUsuarios.excluirAmigoDeUsuario(user.getId(), 0);
		assertTrue(user.getAmigos().size() == 2);
		Amigo amigo = user.getAmigos().get(0);
		assertTrue(amigo.getEmail().equals("email2"));
		Amigo amigo1 = user.getAmigos().get(1);
		assertTrue(amigo1.getEmail().equals("email3"));
	}
}
