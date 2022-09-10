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
			Despesa despesa = new Despesa("Despesa"+contador, (contador*100)/2, "26/05/2000", user, user.getAmigos().get(0));
			despesas.add(despesa);
			user.addCredito(despesa);
			amigo.addDebito(despesa);
		});
		
		for(int a = 0; a < 10; a++) {
			if(a % 2 == 0 ) {
				despesas.get(a).addPagamento(100, FormaPagamento.PIX, "26/05/2000");
			} else {
				despesas.get(a).addPagamento(despesas.get(a).getValor(), FormaPagamento.PIX, "26/05/2000");
			}	
		}
	}
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
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
	 * Método percorre a lista de usuarios e verifica se o id do usuario é igual ao fornecido como parâmetro
	 * 
	 * @param id int
	 * @return usuario com o id igual ao fornecido, ou null caso não encontre nenhum
	 */
	public Usuario getUsuario(int id) {
		for(Usuario user: usuarios) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 *Método percorre a lista de usuarios e ao encontrar algum com o id igual ao passado como parâmetro exclui tal usuario
	 *
	 * @param id int
	 * **/
	public boolean removerUsuario(int id) {
		for(int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).getId() == id) {
				usuarios.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Método para excluir amigo da lista de amigos de um usuario
	 * 
	 * @param idUsuario int
	 * @param idAmigo   int
	 */
	public void removerAmigoUsuario(int idUsuario, int idAmigo) {
		Usuario user = getUsuario(idUsuario);
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
	 * Método percorre a lista de amigos e verifica se o id do amigo é igual ao fornecido como parâmetro
	 * 
	 * @param id int
	 * @return Amigo com o id igual ao fornecido, ou null caso não encontre nenhum amigo 
	 */
	public Amigo getAmigo(int id) {
		for(int i = 0; i < amigos.size(); i++) {
			if(amigos.get(i).getId() == id) {
				return amigos.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Método percorre a lista de amigos e ao encontrar algum com o id igual ao passado como parâmetro exclui tal amigo
	 * 
	 * @param id int
	 * **/
	public boolean removerAmigo(int id) {
		for(int i = 0; i < amigos.size(); i++) {
			if(amigos.get(i).getId() == id) {
				amigos.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Despesa> getDespesas() {
		return despesas;
	}
	
	/**
	 * Método para adicionar despesa na lista de despesas
	 * 
	 * @param despesa Despesa
	 * **/
	public void addDespesa(Despesa despesa) {
		despesas.add(despesa);
	}
	
	/**
	 * Método percorre a lista de despesas e verifica se o id é igual ao fornecido como parâmetro
	 * 
	 * @param id int
	 * @return despesa com o id igual ao fornecido
	 */
	public Despesa getDespesa(int id) {
		for(Despesa despesa: getDespesas()) {
			if(despesa.getId() == id) {
				return despesa;
			}
		}
		return null;
	}
}
