package controle;

import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

import modelo.*;

public class Teste {
	private static LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
	private static LinkedList<Pessoa> pessoas = new LinkedList<Pessoa>();
	
	public static int gerarId() {
		UUID uuid = UUID.randomUUID();
        int id = uuid.hashCode() & 0x7FFFFFFF;
        return id;
	}
	
	public static void AdicionarUsuario(String nome, String email, Date niver, String senha) {
	};

	public static void main(String[] args) {
		AdicionarUsuario("Carlos", "cadu@gmail.com", new Date(), "123456");
		System.out.println(usuarios.get(0).getId());
	}

}
