package modelo;

import java.util.ArrayList;

/**
 * Classe armazena informações de um amigo de usuario e que extende a classe @Pessoa
 * 
 * @author Carlos Eduardo and Pedro Barbosa
 * @version 1.0
 * 
 * @see Pessoa
 * @see Usuario
 * **/

public class Amigo extends Pessoa {
	
	private static int nextId = 0;
	private final int id;
	private String telefone;
	private ArrayList<Usuario> amigosDe;
	
	/**
	 * Construtor Amigo
	 * 
	 * @param nome     String
	 * @param email    String
	 * @param telefone String
	 * **/
	public Amigo(String nome, String email, String telefone) {
		super(nome, email);
		this.id = nextId++;
		this.setTelefone(telefone);
		this.amigosDe = new ArrayList<Usuario>();
	}
	
	public int getId() {
		return id;
	}
	
	public String getTelefone() {
		return this.telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public ArrayList<Usuario> getAmigosDe() {
		return this.amigosDe;
	}
	
	/**
	 * Método para adicionar um usuario na lista de amigos
	 * 
	 * @param user Usuario
	 */
	public void addAmigoDe(Usuario user) {
		this.amigosDe.add(user);
	}
	
	public String toString() {
		return String.format("Id: %d \nNome: %s\nEmail: %s\nTelefone: %s\n", id, getNome(), getEmail(), telefone);
	}
}
