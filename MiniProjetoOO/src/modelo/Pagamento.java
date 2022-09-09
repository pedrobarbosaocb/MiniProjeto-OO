package modelo;

/**
 * Classe responsável por armazenar informações de um pagamento e suas operações
 * 
 * @author Carlos Eduardo & Pedro Barbosa
 * @version 1.0
 * 
 * @see Despesa
**/

public class Pagamento {
	
	/**
	 *Enum para forma de pagamento que só terá essas 3 opções:
	 *CARTAO, DINHEIRO, PIX
	 */
	public enum FormaPagamento{CARTAO, DINHEIRO, PIX};
	private static int nextId = 0;
	private final int id;
	private double valor;
	private FormaPagamento forma_pagamento;
	private String data;
	private Despesa despesa;
	
	/**
	 * Construtor Pagamento
	 * 
	 * @param valor           double
	 * @param forma_pagamento FormaPagamento
	 * @param data            String
	 * @param despesa         Despesa
	 * 
	 * **/
	public Pagamento(double valor, FormaPagamento forma_pagamento, String data, Despesa despesa) {
		this.id = nextId++;
		this.valor = valor;
		this.forma_pagamento = forma_pagamento;
		this.data = data;
		this.despesa = despesa;
	}

	public int getId() {
		return id;
	}

	public double getValor() {
		return valor;
	}

	public FormaPagamento getFormaPagamento() {
		return forma_pagamento;
	}

	public String getData() {
		return data;
	}

	public Despesa getTransacao() {
		return despesa;
	}
	
	public String toString() {
		return String.format("Id: %d \nValor: %.1f\nForma pagamento: %s\nData: %s\nId despesa: %d", id, valor, forma_pagamento, data, despesa.getId());
	}
}
