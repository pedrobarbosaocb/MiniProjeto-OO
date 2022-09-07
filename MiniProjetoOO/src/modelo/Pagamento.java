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
}
