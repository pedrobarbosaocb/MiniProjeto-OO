package modelo;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Usuario extends Pessoa {
	private Date aniversario;
	private String senha;
	private List<Amigo> amigos;
	private List<Grupo> grupos;
	
	public Usuario(String nome, String email, Date niver, String senha) {
		super(nome, email);
		this.setAniversario(niver);
		this.setSenha(senha);
		this.amigos = new ArrayList<Amigo>();
		this.grupos = new ArrayList<Grupo>();
	}

	public Date getAniversario() {
		return aniversario;
	}

	public void setAniversario(Date aniversario) {
		this.aniversario = aniversario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Amigo> getAmigos() {
		return amigos;
	}
	
	public void addAmigo(Amigo amigo) {
		this.amigos.add(amigo);
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

}
