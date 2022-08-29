package modelo;

import java.util.ArrayList;
import java.util.UUID;

import modelo.Pagamento.FormaPagamento;

public class Dados {
	private ArrayList<Amigo> amigos = new ArrayList<Amigo>();
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ArrayList<Transacao> transacoes = new ArrayList<Transacao>();
	
	public static int gerarId() {
		UUID uuid = UUID.randomUUID();
        int id = uuid.hashCode() & 0x7FFFFFFF;
        return id;
	}
	
	public void inserirDados() {
		for(int i = 0; i < 10; i++) {
			usuarios.add(new Usuario("User"+i, "User"+i+"@gmail.com", "26/05/2000", "123456"));
		}
		
		usuarios.forEach(user -> {
			int contador = user.getId();
			Amigo amigo = new Amigo("Amigo"+contador, "Amigo"+contador+"@gmail.com", "123456789");
			amigo.addAmigoDe(user);
			amigos.add(amigo);
			user.addAmigo(amigo);
			Transacao transacao = new Transacao("transação"+contador, (contador*100)/2, "26/05/2000", user, user.getAmigos().get(0));
			transacoes.add(transacao);
			user.addCredito(transacao);
			amigo.addDebito(transacao);
		});
		
		for(int a = 0; a < 10; a++) {
			if(a % 2 == 0 ) {
				transacoes.get(a).addPagamento(100, FormaPagamento.Pix, "26/05/2000");
			} else {
				transacoes.get(a).addPagamento(transacoes.get(a).getValor(), FormaPagamento.Pix, "26/05/2000");
			}	
		}
	}
	
	public Pessoa pessoaExiste(Pessoa pessoa) {
		ArrayList<Pessoa> pessos = new ArrayList<Pessoa>();
		amigos.forEach((pess) -> {
			if(pess.getEmail() == pessoa.getEmail()) {
				pessos.add(pess);
			} else {
				pessos.add(null);
			}
		});
		return pessos.get(0);
	}
	
	public void addUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	public void removerUsuario(int id) {
		usuarios.remove(id);
	}
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public ArrayList<Amigo> getAmigos() {
		return amigos;
	}
	
	public ArrayList<Transacao> getTransacoes() {
		return transacoes;
	}
}
