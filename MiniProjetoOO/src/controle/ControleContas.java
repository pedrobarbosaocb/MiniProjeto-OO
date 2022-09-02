package controle;

import java.util.ArrayList;

import modelo.Despesa;

public class ControleContas {
	private ArrayList<Despesa> despesas;
	
	public ControleContas(ControleDados controleDados) {
		this.despesas = controleDados.getContas();
	}
	
	public ArrayList<Despesa> getContas(){
		return despesas;
	}
	
	public Despesa getContasPorId(int id) {
		Despesa despesa = despesas.get(0);
		for(int i = 0; i<despesas.size(); i++) {
			if(despesas.get(i).getId() == id) {
				despesa = despesas.get(i);
			}
		}
		return despesa;
	}
	
	public ArrayList<Despesa> getContasPorUsuario(String email) {
		ArrayList<Despesa> contas_usuarios = new ArrayList<Despesa>();
		for(int i = 0; i<despesas.size(); i++) {
			Despesa despesa = despesas.get(i);
			if(despesa.getCredor().getEmail().equals(email) || despesa.getDevedor().getEmail().equals(email)) {
				contas_usuarios.add(despesa);
			}
		}
		return contas_usuarios;
	}
	
	
}
