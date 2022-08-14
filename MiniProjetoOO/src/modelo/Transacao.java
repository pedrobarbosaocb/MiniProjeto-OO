package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Pagamento.FormaPagamento;

public class Transacao {
	private static int nextId = 0;
	private final int id;
	private String titulo;
	private double valor;
	private Date vencimento;
	private Pessoa credor;
	private Pessoa devedor;
	private List<Pagamento> pagamentos;

	public Transacao(String titulo, double valor, Date vencimento, Pessoa credor, Pessoa devedor) {
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

	public void setValor(double valor) throws Exception {
		if(this.isQuitado()) {
			throw new Exception("Já foi quitado!!");
		}
		this.valor = valor;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Pessoa getCredor() {
		return credor;
	}

	public Pessoa getDevedor() {
		return devedor;
	}
	
	public void addPagamento(double valor, FormaPagamento forma_pagamento, Date data) {
		pagamentos.add(new Pagamento(valor, forma_pagamento, data, this));
	}
	
	public double getTotalPago() {
		double ret = 0;
		for(int i = 0; i<pagamentos.size(); i++) {
			ret += pagamentos.get(i).getValor();
		}
		return ret;
	}
	
	public boolean isQuitado() {
		return this.getTotalPago() >= this.valor;
	}

}
