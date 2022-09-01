package modelo;

import java.util.List;
import java.util.ArrayList;

public class Grupo {
	private final int id;
	private String nome;
	private List<Usuario> usuarios;

	public Grupo(int id, String nome, ArrayList<Usuario> usuarios) {
		this.id = id;
		this.setNome(nome);
		this.usuarios = usuarios;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

}
