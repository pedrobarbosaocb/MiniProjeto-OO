package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import modelo.Pagamento.FormaPagamento;

public class Dados {
	private ArrayList<Amigo> amigos = new ArrayList<Amigo>();
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ArrayList<Grupo> grupos = new ArrayList<Grupo>();
	private ArrayList<Transacao> transacoes = new ArrayList<Transacao>();
	
	public static int gerarId() {
		UUID uuid = UUID.randomUUID();
        int id = uuid.hashCode() & 0x7FFFFFFF;
        return id;
	}
	
	public void inserirDados() {
		for(int i = 0; i < 10; i++) {
			usuarios.add(new Usuario("User"+i, "User"+i+"@gmail.com", new Date(), "123456"));
		}
		
		usuarios.forEach(user -> {
			int contador = Integer.parseInt(String.valueOf(String.valueOf(user.getId()).charAt(0)));
			Amigo amigo = new Amigo("Amigo"+contador, "Amigo"+contador+"@gmail.com", "123456789");
			amigo.addAmigoDe(user);
			amigos.add(amigo);
			user.addAmigo(amigo);
			Transacao transacao = new Transacao("transação"+contador, (contador*100)/2, new Date(393939409), user, user.getAmigos().get(0));
			transacoes.add(transacao);
			user.addCredito(transacao);
			amigo.addDebito(transacao);
		});
		
		for(int a = 0; a < 10; a++) {
			if(a % 2 == 0 ) {
				transacoes.get(a).addPagamento(100, FormaPagamento.Pix, new Date());
			} else {
				transacoes.get(a).addPagamento(transacoes.get(a).getValor(), FormaPagamento.Pix, new Date());
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
	
	public void adicionarUser(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	public void editarUser(int id, Usuario usuario) {
		for(int i = 0; i<usuarios.size(); i++) {
			Usuario user = usuarios.get(i);
			if(user.getId() == id) {
				int index = usuarios.indexOf(user);
				usuarios.set(index, usuario);
			};
		};
	}
	
	public static void main(String[] args) {
		Dados d = new Dados();
		d.inserirDados();
		d.usuarios.forEach(user -> {
			System.out.println(user.getAmigos().get(0).getEmail() + user.getId());
		});
		d.transacoes.forEach(tra -> {
			System.out.println("Valor:"+tra.getValor() + "Aberto:"+tra.isQuitado());
		});
	}
}
