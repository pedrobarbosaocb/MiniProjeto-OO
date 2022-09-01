package controle;

import java.util.ArrayList;

import modelo.Amigo;
import modelo.Despesa;
import modelo.Usuario;

public class ControleUsuarios {
	private ArrayList<Usuario> usuarios;
	
	public ControleUsuarios(ControleDados controleDados) {
		this.usuarios = controleDados.getUsuarios();
	}
	
	public ArrayList<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public Usuario getUsuarioPorId(int id) {
		Usuario usuario = usuarios.get(0);
		for(int i = 0; i<usuarios.size(); i++) {
			if(usuarios.get(i).getId() == id) {
				usuario = usuarios.get(i);
			}
		}
		return usuario;
	}
	
	public Usuario getUsuarioPorEmail(String email) {
		Usuario usuario = usuarios.get(0);
		for(int i = 0; i<usuarios.size(); i++) {
			if(usuarios.get(i).getEmail() == email) {
				usuario = usuarios.get(i);
			}
		}
		return usuario;
	}
	
	public ArrayList<String> getNomesUsuarios() {
		ArrayList<String> nomesUsuarios = new ArrayList<String>();
		for(int i = 0; i<usuarios.size(); i++) {
			nomesUsuarios.add(usuarios.get(i).getNome());
		}
		return nomesUsuarios;
	}
	
	public ArrayList<Amigo> getAmigosUsuario(int id) {
		return usuarios.get(id).getAmigos();
	}
	
	public ArrayList<String> getNomesAmigosUsuario(int id) {
		ArrayList<String> nomesAmigos = new ArrayList<String>();
		ArrayList<Amigo> amigosUsuario = usuarios.get(id).getAmigos();
		for(int i = 0; i<amigosUsuario.size(); i++) {
			nomesAmigos.add(amigosUsuario.get(i).getNome());
		}
		return nomesAmigos;
	}
	
	public ArrayList<Despesa> getDebitosUsuario(int id) {
		return usuarios.get(id).getDebitos();
	}
	
	public ArrayList<Despesa> getCreditosUsuario(int id) {
		return usuarios.get(id).getCreditos();
	}
	
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
	
	public boolean usuarioExiste(String email) {
		for(int i = 0; i<usuarios.size(); i++) {
			if(usuarios.get(i).getEmail() == email) {
				return true;
			}
		}
		return false;
	}
	
	//public boolean excluirAmigoDeUsuario(int idUsuario, int idAmigo) 
}
