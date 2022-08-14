package modelo;

import java.util.Date;

public class Pagamento {
	
	public enum FormaPagamento{Cartao, Dinheiro, Pix};
	private static int nextId = 0;
	private final int id;
	private double valor;
	private FormaPagamento forma_pagamento;
	private Date data;
	private Transacao transacao;
	
	public Pagamento(double valor, FormaPagamento forma_pagamento, Date data, Transacao transacao) {
		this.id = nextId++;
		this.valor = valor;
		this.forma_pagamento = forma_pagamento;
		this.data = data;
		this.transacao = transacao;
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

	public Date getData() {
		return data;
	}

	public Transacao getTransacao() {
		return transacao;
	}
}
