package controle;

import java.util.ArrayList;

import modelo.Amigo;
import modelo.Despesa;
import modelo.Usuario;

/**
 * Classe que realiza algumas operações de busca na lista de usuarios
 * 
 * @author Carlos Eduardo & Pedro Barbosa
 * @version 1.0
 * 
 * @see ControleDados
**/
public class ControleUsuarios {
	private ArrayList<Usuario> usuarios;
	
	/**
	 * Construtor ControleUsuarios
	 * 
	 * @param controleDados ControleDados
	 * **/
	public ControleUsuarios(ControleDados controleDados) {
		this.usuarios = controleDados.getUsuarios();
	}
	
	public ArrayList<Usuario> getUsuarios(){
		return usuarios;
	}
	
	/**
	 * Método percorre a lista de usuarios e verifica se o id é igual ao fornecido como parâmetro
	 * 
	 * @param id int
	 * @return Usuario com o id igual ao fornecido
	 */
	public Usuario getUsuarioPorId(int id) {
		Usuario usuario = usuarios.get(0);
		for(int i = 0; i<usuarios.size(); i++) {
			if(usuarios.get(i).getId() == id) {
				usuario = usuarios.get(i);
			}
		}
		return usuario;
	}
	
	/**
	 * Método percorre a lista de usuarios e verifica se o email é igual ao fornecido como parâmetro
	 * 
	 * @param email String
	 * @return Usuario com o email igual ao fornecido
	 */
	public Usuario getUsuarioPorEmail(String email) {
		Usuario usuario = usuarios.get(0);
		for(int i = 0; i<usuarios.size(); i++) {
			if(usuarios.get(i).getEmail().equals(email)) {
				usuario = usuarios.get(i);
			}
		}
		return usuario;
	}
	
	/**
	 * Método percorre a lista de usuarios e armazena o nome de cada um
	 * 
	 * @return Lista com nomes de todos os usuarios
	 */
	public ArrayList<String> getNomesUsuarios() {
		ArrayList<String> nomesUsuarios = new ArrayList<String>();
		for(int i = 0; i<usuarios.size(); i++) {
			nomesUsuarios.add(usuarios.get(i).getNome());
		}
		return nomesUsuarios;
	}
	
	/**
	 * @param id int
	 * @return Lista de amigos de um usuario
	 */
	public ArrayList<Amigo> getAmigosUsuario(int id) {
		return getUsuarioPorId(id).getAmigos();
	}
	
	/**
	 * @param id int
	 * @return Lista com os nomes dos amigos do usuario com o id passado como parâmetro
	 */
	public ArrayList<String> getNomesAmigosUsuario(int id) {
		ArrayList<String> nomesAmigos = new ArrayList<String>();
		ArrayList<Amigo> amigosUsuario = getAmigosUsuario(id);
		for(int i = 0; i<amigosUsuario.size(); i++) {
			nomesAmigos.add(amigosUsuario.get(i).getNome());
		}
		return nomesAmigos;
	}
	
	public ArrayList<Despesa> getDebitosUsuario(int id) {
		return getUsuarioPorId(id).getDebitos();
	}
	
	public ArrayList<Despesa> getCreditosUsuario(int id) {
		return getUsuarioPorId(id).getCreditos();
	}
	
	/**
	 * Método verifica se email está na lista de usuarios e então verifica se a senha está correta
	 * 
	 * @param email String
	 * @param senha String
	 * @return true caso informações estejam corretas, false caso não
	 */
	public boolean verificarUsuario(String email, String senha) {
		boolean verificado = false;
		for(int i = 0; i<usuarios.size(); i++) {
			Usuario user = usuarios.get(i);
			if(user.getEmail().equals(email)) {
				if(user.getSenha().equals(senha)) {
					verificado = true;
				}
			}
		}
		return verificado;
	}
	
	/**
	 * Método verifica se algum usuario possui o email passado como parâmetro
	 * @param email String
	 * @return true caso ache um usuario com este email, false caso não
	 */
	public boolean usuarioExiste(String email) {
		for(int i = 0; i<usuarios.size(); i++) {
			if(usuarios.get(i).getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
	
	//public boolean excluirAmigoDeUsuario(int idUsuario, int idAmigo) 
}
