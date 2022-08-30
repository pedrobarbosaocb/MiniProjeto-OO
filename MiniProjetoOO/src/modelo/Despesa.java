package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.Pagamento.FormaPagamento;

/**
 * Classe responsável por armazenar informações de uma despesa e suas operações
 * 
 * @author Carlos Eduardo & Pedro Barbosa
 * @version 1.0
 * 
 * @see Pagamento
**/

public class Despesa {

	private static int nextId = -1;
	private final int id;
	private String titulo;
	private double valor;
	private String vencimento;
	private Pessoa credor;
	private Pessoa devedor;
	private List<Pagamento> pagamentos;

	/**
	 * Construtor Despesa
	 * 
	 * @param titulo     String
	 * @param valor      double
	 * @param vencimento String
	 * @param credor     Pessoa
	 * @param devedor    Pessoa
	 * 
	 * **/
	public Despesa(String titulo, double valor, String vencimento, Pessoa credor, Pessoa devedor) {
		this.id = nextId++;
		this.titulo = titulo;
		this.valor = valor;
		this.vencimento = vencimento;
		this.credor = credor;
		this.devedor = devedor;
		this.pagamentos = new ArrayList<Pagamento>();
	}

	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	public Pessoa getCredor() {
		return credor;
	}

	public Pessoa getDevedor() {
		return devedor;
	}
	
	/** 
	 * Método adiciona um pagamento na lista de pagamentos da despesa
	 * 
	 * @param valor            double
	 * @param forma_pagamento  FormaPagamento
	 * @param data             String
	 */
	public void addPagamento(double valor, FormaPagamento forma_pagamento, String data) {
		pagamentos.add(new Pagamento(valor, forma_pagamento, data, this));
	}
	
	/**
	 * Método percorre a lista de pagamentos e soma seus valores para ter o total que já foi pago dessa despesa
	 * 
	 * @return retorna o valor que já foi pago da despesa
	 */
	public double getTotalPago() {
		double ret = 0;
		for(int i = 0; i<pagamentos.size(); i++) {
			ret += pagamentos.get(i).getValor();
		}
		return ret;
	}
	
	/**
	 * Método verifica se o valor pago já é igual ao valor da despesa
	 * 
	 * @return true se já foi pago ou false se ainda falta alguma quantia para ser pago
	 */
	public boolean isQuitado() {
		return this.getTotalPago() >= this.valor;
	}

}
