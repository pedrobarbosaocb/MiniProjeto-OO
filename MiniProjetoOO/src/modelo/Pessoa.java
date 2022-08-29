package modelo;

import java.util.List;
import java.util.ArrayList;

public abstract class Pessoa {
	
	private String nome;
	private String email;
	private List<Transacao> debitos;
	private List<Transacao> creditos;
	
	protected Pessoa(String nome, String email) {
		this.setNome(nome);
		this.setEmail(email);
		this.debitos = new ArrayList<Transacao>();
		this.creditos = new ArrayList<Transacao>();
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
	
	public List<Transacao> getDebitos(){
		return debitos;
	}
	
	public void addDebito(Transacao transacao) {
		this.debitos.add(transacao);
	}
	
	public List<Transacao> getCreditos(){
		return creditos;
	}
	
	public void addCredito(Transacao transacao) {
		this.creditos.add(transacao);
	}
}
