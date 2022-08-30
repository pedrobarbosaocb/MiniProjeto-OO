package modelo;

import java.util.ArrayList;

import modelo.Pagamento.FormaPagamento;

/**
 * Classe funciona como um banco de dados para armazenas as informações as listas das principais
 * clases da aplicação
 * 
 * @author Carlos Eduardo & Pedro Barbosa
 * @version 1.0
**/

public class Dados {
	
	private ArrayList<Amigo> amigos = new ArrayList<Amigo>();
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ArrayList<Despesa> despesas = new ArrayList<Despesa>();
	
	/**
	 * Método para inserir dados aleatorios nas listas que são atributos da classe
	 * **/
	public void inserirDados() {
		for(int i = 0; i < 10; i++) {
			usuarios.add(new Usuario("User"+i, "User"+i+"@gmail.com", "26/05/2000", "123456"));
		}
		
		usuarios.forEach(user -> {
			int contador = user.getId();
			Amigo amigo = new Amigo("Amigo"+contador, "Amigo"+contador+"@gmail.com", "123456789");
			amigo.addAmigoDe(user);
			amigos.add(amigo);
			user.addAmigo(amigo);
			Despesa despesa = new Despesa("transação"+contador, (contador*100)/2, "26/05/2000", user, user.getAmigos().get(0));
			despesas.add(despesa);
			user.addCredito(despesa);
			amigo.addDebito(despesa);
		});
		
		for(int a = 0; a < 10; a++) {
			if(a % 2 == 0 ) {
				despesas.get(a).addPagamento(100, FormaPagamento.Pix, "26/05/2000");
			} else {
				despesas.get(a).addPagamento(despesas.get(a).getValor(), FormaPagamento.Pix, "26/05/2000");
			}	
		}
	}
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	
	/**
	 * Método percorre a lista de usuarios e verifica se o id do usuario é igual ao fornecido como parâmetro
	 * 
	 * @param id int
	 * @return usuario com o id igual ao fornecido
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
	 * Método para adicionar usuario na lista de usuarios
	 * 
	 * @param usuario Usuario
	 * **/
	public void addUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	/**
	 *Método percorre a lista de usuarios e ao encontrar algum com o id igual ao passado como parâmetro exclui tal usuario
	 *
	 * @param id int
	 * **/
	public void removerUsuario(int id) {
		for(int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).getId() == id) {
				usuarios.remove(i);
			}
		}
	}
	
	/**
	 * Método para excluir amigo da lista de amigos de um usuario
	 * 
	 * @param idUsuario int
	 * @param idAmigo   int
	 */
	public void removerAmigoUsuario(int idUsuario, int idAmigo) {
		Usuario user = getUsuarioPorId(idUsuario);
		ArrayList<Amigo> amigos = user.getAmigos();
		for(int i = 0; i < amigos.size(); i++) {
			if(amigos.get(i).getId() == idAmigo) {
				amigos.remove(i);
			}
		}
		user.setAmigos(amigos);
	}
	
	public ArrayList<Amigo> getAmigos() {
		return amigos;
	}
	
	/**
	 * Método para adicionar amigo na lista de amigos
	 * 
	 * @param amigo Amigo
	 * **/
	public void addAmigo(Amigo amigo) {
		amigos.add(amigo);
	}
	
	/**
	 * Método percorre a lista de amigos e ao encontrar algum com o id igual ao passado como parâmetro exclui tal amigo
	 * 
	 * @param id int
	 * **/
	public void removerAmigo(int id) {
		amigos.remove(id);
	}
	
	public ArrayList<Despesa> getDespesas() {
		return despesas;
	}
}
