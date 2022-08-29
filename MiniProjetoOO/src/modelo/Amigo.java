package modelo;

import java.util.List;
import java.util.ArrayList;

public class Amigo extends Pessoa {
	
	private static int nextId = -1;
	private final int id;
	private String telefone;
	private List<Usuario> amigosDe;
	
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
	
	public List<Usuario> getAmigosDe() {
		return this.amigosDe;
	}
	
	public void addAmigoDe(Usuario user) {
		this.amigosDe.add(user);
	}
}
