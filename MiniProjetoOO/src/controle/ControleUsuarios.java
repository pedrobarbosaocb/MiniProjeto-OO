package controle;

import java.util.ArrayList;

import modelo.Usuario;

public class ControleUsuarios {
	private ArrayList<Usuario> usuarios;
	
	public ControleUsuarios(ControleDados controleDados) {
		this.usuarios = controleDados.getDados().getUsuarios();
	}
	
	public ArrayList<Usuario> getUsuarios(){
		return usuarios;
	}
}
