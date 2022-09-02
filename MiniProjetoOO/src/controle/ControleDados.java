package controle;

import java.util.ArrayList;

import modelo.*;

/**
 * Classe que realiza a intermediação entre a view e os dados no modelo com operações CRUD
 * 
 * @author Carlos Eduardo & Pedro Barbosa
 * @version 1.0
 * 
**/
public class ControleDados {
	private Dados dados = new Dados();
	private static Usuario usuarioSessao;
	
	public ControleDados() {
		dados.inserirDados();
	}
	
	public Dados getDados() {
		return dados;
	}
	
	public static Usuario getUsuarioSessao() {
		return usuarioSessao;
	}
	
	public static void setUsuarioSessao(Usuario user) {
		usuarioSessao = user;
	}
	
	public ArrayList<Usuario> getUsuarios() {
		return dados.getUsuarios();
	}
	
	public ArrayList<Amigo> getAmigos() {
		return dados.getAmigos();
	}
	
	public ArrayList<Despesa> getContas() {
		return dados.getDespesas();
	}
	
	/**
	 * Método cria um usuario e adiciona na lista de usuarios na classe @Dados
	 * @param nome  String
	 * @param email String
	 * @param niver String
	 * @param senha String
	 * @return true caso consiga adicionar o usuario, e false caso receba alguma informação errada
	 */
	public boolean criarUsuario(String nome, String email, String niver, String senha) {
		if(nome.length() < 4 || email.length() < 10 || senha.length() <= 6) {
			return false;
		}
		Usuario novoUsuario = new Usuario(nome, email, niver, senha);
		dados.addUsuario(novoUsuario);
		return true;
	}
	
	/**
	 * Método altera as informações de um usuario com os dados passados como parâmetro
	 * @param id    int
	 * @param nome  String
	 * @param email String
	 * @param niver String
	 * @param senha String
	 * @return true caso consiga alterar as informções, e false caso receba alguma informação errada
	 */
	public boolean editarUsuario(int id, String nome, String email, String niver, String senha) {
		if(nome.length() < 4 || email.length() < 10 || senha.length() <= 6) {
			return false;
		}
		try {
			Usuario user = dados.getUsuarioPorId(id);
			user.setNome(nome);
			user.setEmail(email);
			user.setAniversario(niver);
			user.setSenha(senha);
			return true;
		} catch(Exception err) {
			return false;
		}
		
	}
	
	/**
	 * Exclui um usuario da lista de usuarios
	 * @param id
	 * @return true caso consiga excluir o usuario, e false caso não consiga
	 */
	public boolean excluirUsuario(int id) {
		try {
			dados.removerUsuario(id);
			return true;
		} catch(Exception err) {
			return false;
		}
	}
	
	/**
	 * Método cria um amigo e adiciona na lista de amigos na classe @Dados e na lista de amigos de um usuario passado como parâmetro
	 * @param usuario  Usuario
	 * @param nome     String
	 * @param email    String
	 * @param telefone String
	 * @return true caso consiga adicionar o amigo, e false caso receba alguma informação errada
	 */
	public boolean criarAmigo(Usuario usuario, String nome, String email, String telefone) {
		if(nome.length() < 4 || email.length() < 10 || telefone.length() <= 11) {
			return false;
		}
		Amigo amigo = new Amigo(nome, email, telefone);
		amigo.addAmigoDe(usuario);
		usuario.addAmigo(amigo);
		dados.addAmigo(amigo);
		return true;
	}
	
	/**
	 * Método altera as informações de um amigo com os dados passados como parâmetro
	 * @param nome     String
	 * @param email    String
	 * @param telefone String
	 * @return true caso consiga alterar as informções, e false caso receba alguma informação errada
	 */
	public boolean editarAmigo(int id, String nome, String email, String telefone) {
		if(nome.length() < 4 || email.length() < 10 || telefone.length() <= 11) {
			return false;
		}
		try {
			Amigo amigo = dados.getAmigoPorId(id);
			amigo.setNome(nome);
			amigo.setEmail(email);
			amigo.setTelefone(telefone);
			return true;
		} catch(Exception err) {
			return false;
		}
	}
	
	/**
	 * Exclui um amigo da lista de amigos
	 * @param id
	 * @return true caso consiga excluir o usuario, e false caso não consiga
	 */
	public boolean excluirAmigo(int id) {
		try {
			dados.removerAmigo(id);
			return true;
		} catch(Exception err) {
			return false;
		}
	}
	
//	public boolean criarDespesa(String titulo, double valor, String vencimento, Pessoa credor, Pessoa devedor) {
//		if(titulo.length() < 3 || valor <= 0 || vencimento.length() < 8) {
//			return false;
//		}
//		
//	}
}
