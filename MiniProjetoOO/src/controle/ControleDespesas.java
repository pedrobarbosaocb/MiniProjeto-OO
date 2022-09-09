package controle;

import java.util.ArrayList;

import modelo.Despesa;

/**
 * Classe que realiza algumas operações de busca na lista de Despesas
 * 
 * @author Carlos Eduardo & Pedro Barbosa
 * @version 1.0
 * 
 * @see ControleDados
**/
public class ControleDespesas {
	private ArrayList<Despesa> despesas;
	
	/**
	 * Construtor ControleDespesas
	 * 
	 * @param controleDados ControleDados
	 * **/
	public ControleDespesas(ControleDados controleDados) {
		this.despesas = controleDados.getDespesas();
	}
	
	public ArrayList<Despesa> getDespesas(){
		return despesas;
	}
	
	/**
	 * Método faz uma lista com os nomes das despesas em que este email está envolvido
	 * @param email String
	 * @return ArrayList(String)
	 */
	public ArrayList<String> getNomesDespesasEmail(String email) {
		ArrayList<String> nomes_despesas = new ArrayList<String>();
		for(Despesa despesa: getDespesasEmail(email)) {
			nomes_despesas.add(despesa.getTitulo());
		}
		return nomes_despesas;
	}
	
	/**
	 * Método percorre a lista de despesas e verifica se o id da despesa é igual ao fornecido como parâmetro
	 * 
	 * @param id int
	 * @return despesa com o id igual ao fornecido, ou null caso não encontre nenhuma despesa 
	 */
	public Despesa getDespesa(int id) {
		for(int i = 0; i < despesas.size(); i++) {
			if(despesas.get(i).getId() == id) {
				return despesas.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Método percorre a lista de despesas e verifica se o email fornecido está como credor ou devedor
	 * @param email String
	 * @return ArrayList(Despesa) lista de despesas em que este email está ou como credor ou como devedor
	 */
	public ArrayList<Despesa> getDespesasEmail(String email) {
		ArrayList<Despesa> despesas_usuarios = new ArrayList<Despesa>();
		for(Despesa despesa: despesas) {
			if(despesa.getCredor().getEmail().equals(email) || despesa.getDevedor().getEmail().equals(email)) {
				despesas_usuarios.add(despesa);
			}
		}
		return despesas_usuarios;
	}
	
	
}
