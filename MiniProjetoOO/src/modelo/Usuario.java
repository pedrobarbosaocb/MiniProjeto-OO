package modelo;

import java.util.ArrayList;

/**
 * Classe responsável por armazenar informações de um usuario e suas operações, extende a classe @Pessoa
 * 
 * @author Carlos Eduardo & Pedro Barbosa
 * @version 1.0
 * 
 * @see Pessoa
 * @see Amigo
**/
public class Usuario extends Pessoa {
	
	private static int nextId = 0;
	private final int id;
	private String aniversario;
	private String senha;
	private ArrayList<Amigo> amigos;
	
	/**
	 * Construtor Despesa
	 * 
	 * @param nome  String
	 * @param email String
	 * @param niver String
	 * @param senha String
	 * **/
	public Usuario(String nome, String email, String niver, String senha) {
		super(nome, email);
		this.id = nextId++;
		this.setAniversario(niver);
		this.setSenha(senha);
		this.amigos = new ArrayList<Amigo>();
	}
	
	public int getId() {
		return id;
	}

	public String getAniversario() {
		return aniversario;
	}

	public void setAniversario(String aniversario) {
		this.aniversario = aniversario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ArrayList<Amigo> getAmigos() {
		return amigos;
	}
	
	public void setAmigos(ArrayList<Amigo> amigos) {
		this.amigos = amigos;
	}
	public void addAmigo(Amigo amigo) {
		this.amigos.add(amigo);
	}

}
