package modelo;

import java.util.ArrayList;

/**
 * Classe abstrata para que seus atributos e métodos possam ser utilizados por outras clases
 * 
 * @author Carlos Eduardo & Pedro Barbosa
 * @version 1.0
 * 
 * @see Amigo
 * @see Usuario
**/
public abstract class Pessoa {
	
	private String nome;
	private String email;
	private ArrayList<Despesa> debitos;
	private ArrayList<Despesa> creditos;
	
	/**
	 * Construtor Pessoa
	 * 
	 * @param nome  String
	 * @param email String
	 */
	public Pessoa(String nome, String email) {
		this.setNome(nome);
		this.setEmail(email);
		this.debitos = new ArrayList<Despesa>();
		this.creditos = new ArrayList<Despesa>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public ArrayList<Despesa> getDebitos(){
		return debitos;
	}
	
	/**
	 * Método adiciona uma despesa na lista de debitos da pessoa
	 * 
	 * @param despesa Despesa
	 */
	public void addDebito(Despesa despesa) {
		this.debitos.add(despesa);
	}
	
	public ArrayList<Despesa> getCreditos(){
		return creditos;
	}
	
	/**
	 * Método adiciona uma despesa na lista de creditos da pessoa
	 * 
	 * @param despesa Despesa
	 */
	public void addCredito(Despesa despesa) {
		this.creditos.add(despesa);
	}
}
