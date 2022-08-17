package controle;

import modelo.*;

public class ControleDados {
	private Dados dados = new Dados();
	
	public ControleDados() {
		dados.inserirDados();;
	}
	
	public Dados getDados() {
		return dados;
	}
}
