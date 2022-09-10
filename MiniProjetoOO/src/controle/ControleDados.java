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
 * @see Dados
**/
public class ControleDados {
	private Dados dados = new Dados();
	private static Usuario usuarioSessao;
	
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
	
	public ArrayList<Despesa> getDespesas() {
		return dados.getDespesas();
	}
	
	/**
	 * Método verifica se algum dos objetos da lista é null, "" ou 0
	 * @param objeto Object[]
	 * @return true caso um objeto passado seja null, "" ou 0, e false caso contrário
	 */
	public boolean verificarObjetos(Object[] objeto) {
		for(Object obj: objeto) {
			if(obj == null) return true;
			else if(obj.equals("")) return true;
			else if(obj.equals(0)) return true;
		}
		return false;
	}
	
	/**
	 * Método cria um usuario e adiciona na lista de usuarios na classe @Dados
	 * @param nome  String
	 * @param email String
	 * @param niver String
	 * @param senha String
	 * @return true caso consiga adicionar o usuario, e false caso receba alguma informação errada
	 * @see {@link Dados#addUsuario(int)}
	 */
	public boolean criarUsuario(String nome, String email, String niver, String senha) {
		Object[] params = {nome, email, niver, senha};
		if(verificarObjetos(params)) {
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
	 * @see {@link Dados#getUsuario(int)}
	 */
	public boolean editarUsuario(int id, String nome, String email, String niver, String senha) {
		Object[] params = {nome, email, niver, senha};
		if(verificarObjetos(params) || id < 0) {
			return false;
		}
		Usuario user = dados.getUsuario(id);
		if(user == null) return false;
		
		user.setNome(nome);
		user.setEmail(email);
		user.setAniversario(niver);
		user.setSenha(senha);
		return true;
	}
	
	/**
	 * Exclui um usuario da lista de usuarios
	 * @param id
	 * @return true caso consiga excluir o usuario, e false caso não consiga
	 * @see {@link Dados#removerUsuario(int)}
	 */
	public boolean excluirUsuario(int id) {
		if(id < 0) return false;
		return dados.removerUsuario(id);
	}
	
	/**
	 * Método cria um amigo e adiciona na lista de amigos na classe @Dados e na lista de amigos de um usuario passado como parâmetro
	 * @param usuario  Usuario
	 * @param nome     String
	 * @param email    String
	 * @param telefone String
	 * @return true caso consiga adicionar o amigo, e false caso receba alguma informação errada
	 * @see {@link Amigo#addAmigoDe(Usuario)}
	 * @see {@link Usuario#addAmigo(Amigo)}
	 * @see {@link Dados#addAmigo(Amigo)}
	 */
	public boolean criarAmigo(Usuario usuario, String nome, String email, String telefone) {
		Object[] params = {usuario, nome, email, telefone};
		if(verificarObjetos(params)) {
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
	 * @see {@link Dados#getAmigo(int)}
	 */
	public boolean editarAmigo(int id, String nome, String email, String telefone) {
		Object[] params = {nome, email, telefone};
		if(verificarObjetos(params)) {
			return false;
		}
		
		Amigo amigo = dados.getAmigo(id);
		if(amigo == null) return false;
		amigo.setNome(nome);
		amigo.setEmail(email);
		amigo.setTelefone(telefone);
		return true;
	}
	
	/**
	 * Exclui um amigo da lista de amigos
	 * @param id
	 * @return true caso consiga excluir o usuario, e false caso não consiga
	 * @see {@link Dados#removerAmigo(int)}
	 */
	public boolean excluirAmigo(int id) {
		if(id < 0) return false;
		return dados.removerAmigo(id);
		
	}
	
	/**
	 * Método transforma um double de x casas decimais em apenas uma casa decimal
	 * @param n double
	 * @return double fornecido com apenas uma casa decimal
	 */
	public double casaDecimal(double n) {
		return Math.round(n*10.0)/10.0;
	}
	
	/**
	 * Método responsável por criar as despesas entre cada usuario/amigo, recebe o valor total da despesa, a lista de pessoas envolvidas
	 * e a lista de valores pagos pelas pessoas, divide a despesa igualmente para todos levando em conta o que cada um pagou.
	 * @param titulo     String
	 * @param valor      double
	 * @param vencimento String
	 * @param pessoas    ArrayList(Pessoa)
	 * @param valores    ArrayList(Double)
	 * @return true caso consiga criar as despesas, e false caso haja algum erro
	 */
	public boolean criarDespesa(String titulo, double valor, String vencimento, ArrayList<Pessoa> pessoas, 
			ArrayList<Double> valores) {
		
		Object[] params = {titulo, valor, vencimento, pessoas, valores};
		if(verificarObjetos(params) || valor <= 0 || pessoas.size() != valores.size()) return false;
		
		double valor_pessoa = casaDecimal(valor/pessoas.size());
		
		for(int i = 0; i<pessoas.size(); i++) {
			if(valores.get(i) <= valor_pessoa) continue;
			
			for(int j = 0; j<pessoas.size(); j++) {
				if(j == i || valores.get(j) >= valor_pessoa || valores.get(i) == valor_pessoa) continue;

				double resto = casaDecimal(valores.get(i) - valor_pessoa);

				if(resto > valor_pessoa) {
					double val = casaDecimal(valor_pessoa-valores.get(j));
					valores.set(i, valores.get(i)-val);
					valores.set(j, val+valores.get(j) >= valor_pessoa ? valor_pessoa : val);
					Despesa despesa = new Despesa(titulo, val, vencimento, pessoas.get(i), pessoas.get(j));
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
	
	/**
	 * Sobrecarga do método {@link #criarDespesa}, recebe o valor total da despesa, a lista de pessoas envolvidas,
	 * lista de valores pagos pelas pessoas e a lista de valores que devem ser pagos por cada um. Ou seja, não divide
	 * igualmente como a {@link #criarDespesa}
	 * @param titulo     String
	 * @param valor      double
	 * @param vencimento String
	 * @param pessoas    ArrayList(Pessoa)
	 * @param pagos      ArrayList(Double)
	 * @param valores    ArrayList(Double)
	 * @return true caso consiga criar as despesas, e false caso haja algum erro
	 */
	public boolean criarDespesa(String titulo, double valor, String vencimento, ArrayList<Pessoa> pessoas, 
			ArrayList<Double> pagos, ArrayList<Double> valores) {
		
		Object[] params = {titulo, valor, vencimento, pessoas, valores, pagos};
		if(verificarObjetos(params) || valor <= 0 || pessoas.size() != valores.size()|| pessoas.size() != pagos.size()) return false;
		
		for(int i = 0; i<pessoas.size(); i++) {
			if(pagos.get(i) <= valores.get(i)) continue;
			
			for(int j = 0; j<pessoas.size(); j++) {
				if(i == j || pagos.get(j) >= valores.get(j) || pagos.get(i) == valores.get(i)) continue;
				
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
					double val = dif+resto;
					pagos.set(j, resto == 0 ? valores.get(j) : val);
					Despesa despesa = new Despesa(titulo, val, vencimento, pessoas.get(i), pessoas.get(j));
					dados.addDespesa(despesa);
					pessoas.get(i).addCredito(despesa);
					pessoas.get(j).addDebito(despesa);
				}
			}
		}
		return true;
	}
	
	/**
	 * Método para editar as informações de uma despesa, exclui a antiga e cria uma nova com parâmetros recebidos.
	 * @param id         int
	 * @param titulo     String
	 * @param valor      double
	 * @param vencimento String
	 * @param pessoas    ArrayList(Pessoa)
	 * @param pagos      ArrayList(Double)
	 * @param valores    ArrayList(Double)
	 * @return true caso consiga, false caso não
	 */
	public boolean editarDespesa(int id, String titulo, double valor, String vencimento, ArrayList<Pessoa> pessoas, 
			ArrayList<Double> pagos, ArrayList<Double> valores) {
		Object[] params = {titulo, valor, vencimento, pessoas, valores};
		if(verificarObjetos(params) || valor < 0 || pessoas.size() != valores.size()|| pessoas.size() != pagos.size()) return false;
		
		excluirDespesa(id);
		
		boolean criada = false;
		if(pagos.size() == 0) criada = criarDespesa(titulo, valor, vencimento, pessoas, valores);
		
		if(pagos.size() > 0) criada = criarDespesa(titulo, valor, vencimento, pessoas, pagos, valores);
		return criada;
	}
	
	/**
	 * Método responsável por excluir uma despesa, exclui a despesa das listas de creditos e debitos de cada usuario/amigo
	 * e depois da lista de despesas.
	 * @param id int
	 * @return true caso consiga excluir, e false caso não
	 */
	public boolean excluirDespesa(int id) {
		Despesa despesa = dados.getDespesa(id);
		if(despesa != null) {
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
		} else return false;
	}
	
	/**
	 * Método responsável por criar um pagamento e adicioná-lo na lista de pagamentos da despesa.
	 * @param despesaId       int
	 * @param valor           double
	 * @param forma_pagamento {@link FormaPagamento}
	 * @param data            String
	 * @return true caso consiga criar o pagamento, false caso tenha alguma informação errada, faltante ou o valor seja maior
	 * do que o valor faltante para pagar a despesa
	 */
	public boolean realizarPagamento(int despesaId, double valor, String forma_pagamento, String data) {
		if(!Arrays.stream(FormaPagamento.values()).anyMatch((t) -> t.name().equals(forma_pagamento))) return false;
		
		if(despesaId < 0 || valor <= 0 || data.length() <= 8) return false;
		
		Despesa despesa = dados.getDespesa(despesaId);
		if(despesa.getValor() - despesa.getTotalPago() < valor) return false;
		
		dados.getDespesa(despesaId).addPagamento(valor, FormaPagamento.valueOf(forma_pagamento), data);
		return true;
	}
}
