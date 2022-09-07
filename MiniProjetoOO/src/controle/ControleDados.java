package controle;

import java.util.ArrayList;
import java.util.Arrays;

import modelo.*;
import modelo.Pagamento.FormaPagamento;

/**
 * Classe que realiza a intermediação entre a view e os dados no modelo com operações CRUD
 * 
 * @author Carlos Eduardo & Pedro Barbosa
 * @version 1.0
 * 
**/
public class ControleDados {
	private Dados dados = new Dados();
	private static Usuario usuarioSessao;
	
	public ControleDados() {
		dados.inserirDados();
	}
	
	public Dados getDados() {
		return dados;
	}
	
	public static Usuario getUsuarioSessao() {
		return usuarioSessao;
	}
	
	public static void setUsuarioSessao(Usuario user) {
		usuarioSessao = user;
	}
	
	public ArrayList<Usuario> getUsuarios() {
		return dados.getUsuarios();
	}
	
	public ArrayList<Amigo> getAmigos() {
		return dados.getAmigos();
	}
	
	public ArrayList<Despesa> getContas() {
		return dados.getDespesas();
	}
	
	/**
	 * Método cria um usuario e adiciona na lista de usuarios na classe @Dados
	 * @param nome  String
	 * @param email String
	 * @param niver String
	 * @param senha String
	 * @return true caso consiga adicionar o usuario, e false caso receba alguma informação errada
	 */
	public boolean criarUsuario(String nome, String email, String niver, String senha) {
		if(nome.length() < 4 || email.length() < 10 || senha.length() <= 6) {
			return false;
		}
		Usuario novoUsuario = new Usuario(nome, email, niver, senha);
		dados.addUsuario(novoUsuario);
		return true;
	}
	
	/**
	 * Método altera as informações de um usuario com os dados passados como parâmetro
	 * @param id    int
	 * @param nome  String
	 * @param email String
	 * @param niver String
	 * @param senha String
	 * @return true caso consiga alterar as informções, e false caso receba alguma informação errada
	 */
	public boolean editarUsuario(int id, String nome, String email, String niver, String senha) {
		if(id < 0 || nome.length() < 4 || email.length() < 10 || senha.length() <= 6) {
			return false;
		}
		try {
			Usuario user = dados.getUsuarioPorId(id);
			user.setNome(nome);
			user.setEmail(email);
			user.setAniversario(niver);
			user.setSenha(senha);
			return true;
		} catch(Exception err) {
			return false;
		}
		
	}
	
	/**
	 * Exclui um usuario da lista de usuarios
	 * @param id
	 * @return true caso consiga excluir o usuario, e false caso não consiga
	 */
	public boolean excluirUsuario(int id) {
		try {
			dados.removerUsuario(id);
			return true;
		} catch(Exception err) {
			return false;
		}
	}
	
	/**
	 * Método cria um amigo e adiciona na lista de amigos na classe @Dados e na lista de amigos de um usuario passado como parâmetro
	 * @param usuario  Usuario
	 * @param nome     String
	 * @param email    String
	 * @param telefone String
	 * @return true caso consiga adicionar o amigo, e false caso receba alguma informação errada
	 */
	public boolean criarAmigo(Usuario usuario, String nome, String email, String telefone) {
		if(nome.length() < 4 || email.length() < 10 || telefone.length() <= 11) {
			return false;
		}
		Amigo amigo = new Amigo(nome, email, telefone);
		amigo.addAmigoDe(usuario);
		usuario.addAmigo(amigo);
		dados.addAmigo(amigo);
		return true;
	}
	
	/**
	 * Método altera as informações de um amigo com os dados passados como parâmetro
	 * @param nome     String
	 * @param email    String
	 * @param telefone String
	 * @return true caso consiga alterar as informções, e false caso receba alguma informação errada
	 */
	public boolean editarAmigo(int id, String nome, String email, String telefone) {
		if(id < 0 || nome.length() < 4 || email.length() < 10 || telefone.length() <= 11) {
			return false;
		}
		try {
			Amigo amigo = dados.getAmigoPorId(id);
			amigo.setNome(nome);
			amigo.setEmail(email);
			amigo.setTelefone(telefone);
			return true;
		} catch(Exception err) {
			return false;
		}
	}
	
	/**
	 * Exclui um amigo da lista de amigos
	 * @param id
	 * @return true caso consiga excluir o usuario, e false caso não consiga
	 */
	public boolean excluirAmigo(int id) {
		try {
			dados.removerAmigo(id);
			return true;
		} catch(Exception err) {
			return false;
		}
	}
	
	public double casaDecimal(double n) {
		return Math.round(n*10.0)/10.0;
	}
	
	public boolean criarDespesa(String titulo, double valor, String vencimento, ArrayList<Pessoa> pessoas, 
			ArrayList<Double> valores) {
		
		if(titulo.length() < 3 || valor <= 0 || vencimento.length() < 8 || pessoas.size() <= 0 || valores.size() <= 0
				|| pessoas.size() != valores.size()) return false;
		
		double valor_pessoa = casaDecimal(valor/pessoas.size());
		
		for(int i = 0; i<pessoas.size(); i++) {
			if(valores.get(i) <= valor_pessoa) continue;
			
			for(int j = 0; j<pessoas.size(); j++) {
				if(j == i || valores.get(j) >= valor_pessoa || valores.get(i) == valor_pessoa) continue;

				double resto = casaDecimal(valores.get(i) - valor_pessoa);

				if(resto > valor_pessoa) {
					valores.set(i, resto);
					valores.set(j, casaDecimal(valor_pessoa-valores.get(j)));
					Despesa despesa = new Despesa(titulo, valores.get(j), vencimento, pessoas.get(i), pessoas.get(j));
					dados.addDespesa(despesa);
					pessoas.get(i).addCredito(despesa);
					pessoas.get(j).addDebito(despesa);
					
				} else if (resto <= valor_pessoa) {
					valores.set(i, valor_pessoa);
					double val = casaDecimal(valores.get(j)+resto);
					valores.set(j, val >= valor_pessoa ? valor_pessoa : val);
					Despesa despesa = new Despesa(titulo, resto, vencimento, pessoas.get(i), pessoas.get(j));
					dados.addDespesa(despesa);
					pessoas.get(i).addCredito(despesa);
					pessoas.get(j).addDebito(despesa);
					
				}
			}
		}
		return true;
	}
	
	public boolean criarDespesa(String titulo, double valor, String vencimento, ArrayList<Pessoa> pessoas, 
			ArrayList<Double> pagos, ArrayList<Double> valores) {
		if(titulo.length() < 3 || valor <= 0 || vencimento.length() < 8 || pessoas.size() <= 0 || pagos.size() <= 0 
				|| valores.size() <= 0 || pessoas.size() != valores.size() || pessoas.size() != pagos.size()) return false;
		
		for(int i = 0; i<pessoas.size(); i++) {
			if(pagos.get(i) <= valores.get(i)) continue;
			
			for(int j = 0; j<pessoas.size(); j++) {
				if(i == j || pagos.get(j) >= valores.get(j)) continue;
				
				double resto = casaDecimal(pagos.get(i)-valores.get(i)) + casaDecimal(pagos.get(j)-valores.get(j));
				double dif = casaDecimal(pagos.get(i)-valores.get(i)-resto);
				
				if(resto > 0) {
					pagos.set(i, casaDecimal(pagos.get(i)-dif));
					pagos.set(j, valores.get(j));
					Despesa despesa = new Despesa(titulo, dif, vencimento, pessoas.get(i), pessoas.get(j));
					dados.addDespesa(despesa);
					pessoas.get(i).addCredito(despesa);
					pessoas.get(j).addDebito(despesa);
				} else if(resto <= 0) {
					pagos.set(i, valores.get(i));
					pagos.set(j, resto == 0 ? casaDecimal(valores.get(j)-dif) : Math.abs(resto));
					Despesa despesa = new Despesa(titulo, pagos.get(j), vencimento, pessoas.get(i), pessoas.get(j));
					dados.addDespesa(despesa);
					pessoas.get(i).addCredito(despesa);
					pessoas.get(j).addDebito(despesa);
				}
			}
		}
		return true;
	}
	
	public boolean editarDespesa(int id, String titulo, double valor, String vencimento, ArrayList<Pessoa> pessoas, 
			ArrayList<Double> pagos, ArrayList<Double> valores) {
		if(id < 0 || titulo.length() < 3 || valor <= 0 || vencimento.length() < 8 || pessoas.size() <= 0 || valores.size() <= 0
				|| pessoas.size() != valores.size()) return false;
		
		excluirDespesa(id);
		
		if(pagos.size() == 0) criarDespesa(titulo, valor, vencimento, pessoas, valores);
		
		if(pagos.size() > 0) criarDespesa(titulo, valor, vencimento, pessoas, pagos, valores);
		return true;
	}
	
	public boolean excluirDespesa(int id) {
		try {
			Despesa despesa = dados.getDespesa(id);
			for(int i = 0; i < dados.getUsuarios().size(); i++) {
				Usuario usuario = dados.getUsuarios().get(i);
				usuario.getCreditos().remove(despesa);
				usuario.getDebitos().remove(despesa);
			}
			for(int i = 0; i < dados.getAmigos().size(); i++) {
				Amigo amigo = dados.getAmigos().get(i);
				amigo.getCreditos().remove(despesa);
				amigo.getDebitos().remove(despesa);
			}
			dados.getDespesas().remove(despesa);
			return true;
		} catch(Exception err) {
			return false;
		}
	}
	
	public boolean realizarPagamento(int despesaId, double valor, String forma_pagamento, String data) {
		if(!Arrays.stream(FormaPagamento.values()).anyMatch((t) -> t.name().equals(forma_pagamento))) return false;
		
		if(despesaId < 0 || valor <= 0 || data.length() <= 8) return false;
		
		Despesa despesa = dados.getDespesa(despesaId);
		if(despesa.getValor() - despesa.getTotalPago() < valor) return false;
		
		dados.getDespesa(despesaId).addPagamento(valor, FormaPagamento.valueOf(forma_pagamento), data);
		return true;
	}
}
