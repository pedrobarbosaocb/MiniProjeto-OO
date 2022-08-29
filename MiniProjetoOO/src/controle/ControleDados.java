package controle;

import modelo.*;

public class ControleDados {
	private Dados dados = new Dados();
	
	public ControleDados() {
		dados.inserirDados();
	}
	
	public Dados getDados() {
		return dados;
	}
	
	public boolean criarUsuario(String nome, String email, String niver, String senha) {
		if(senha.length() <= 6) {
			return false;
		}
		Usuario novoUsuario = new Usuario(nome, email, niver, senha);
		dados.addUsuario(novoUsuario);
		return true;
	}
	
	public boolean editarUsuario(int id, String nome, String email, String niver, String senha) {
		Usuario user = dados.getUsuarios().get(id);
		user.setNome(nome);
		user.setEmail(email);
		user.setAniversario(niver);
		user.setSenha(senha);
		return true;
	}
	
	public boolean excluirUsuario(int id) {
		try {
			dados.removerUsuario(id);
			return true;
		} catch(Exception err) {
			return false;
		}
	}
}
