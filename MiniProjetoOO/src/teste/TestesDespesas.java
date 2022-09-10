package teste;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controle.*;
import modelo.*;

public class TestesDespesas {
	private ControleDados controleDados;
	private ArrayList<Pessoa> pessoas;
	private ArrayList<Double> valores;
	private ArrayList<Double> pagos;
	
	@BeforeEach
	void criarAtributos() {
		controleDados = new ControleDados();
	}
	
	public void criarDados() {
		pessoas = new ArrayList<Pessoa>();
		pessoas.add(new Usuario("user", "userEmail", "niver", "senha"));
		pessoas.add(new Amigo("amigo1", "email1", "telefone"));
		pessoas.add(new Amigo("amigo2", "email2", "telefone"));
		pessoas.add(new Amigo("amigo3", "email3", "telefone"));
		valores = new ArrayList<Double>();
		valores.add(10.0);
		valores.add(20.0);
		valores.add(40.0);
		valores.add(30.0);
		pagos = new ArrayList<Double>();
		pagos.add(25.0);
		pagos.add(0.0);
		pagos.add(25.0);
		pagos.add(50.0);
	}
	
	@Test
	void testeCriarDespesaDivisaoIgual() {
		criarDados();
		boolean teste = controleDados.criarDespesa("Titulo", 100, "12/12/2022", pessoas, valores);
		assertTrue(teste);

		for(Pessoa pessoa: pessoas) {
			if(pessoa.getNome().equals("user")) {
				pessoa.getDebitos().forEach(despesa -> {
					assertTrue(despesa.getValor() == 15);
				});
			} else if(pessoa.getNome().equals("amigo1")) {
				pessoa.getDebitos().forEach(despesa -> {
					assertTrue(despesa.getValor() == 5);
				});
			} else if(pessoa.getNome().equals("amigo2")) {
				pessoa.getCreditos().forEach(despesa -> {
					assertTrue(despesa.getValor() == 15);
				});
			} else if(pessoa.getNome().equals("amigo3")) {
				pessoa.getCreditos().forEach(despesa -> {
					assertTrue(despesa.getValor() == 5);
				});
			}
		}
	}
	
	@Test
	void testeCriarDespesaDivisaoDesigual() {
		criarDados();
		boolean teste = controleDados.criarDespesa("Titulo", 100, "12/12/2022", pessoas, pagos, valores);
		assertTrue(teste);

		for(Pessoa pessoa: pessoas) {
			if(pessoa.getNome().equals("user")) {
				pessoa.getCreditos().forEach(despesa -> {
					assertTrue(despesa.getValor() == 15);
				});
			} else if(pessoa.getNome().equals("amigo1")) {
				pessoa.getDebitos().forEach(despesa -> {
					assertTrue(despesa.getValor() == 15 || despesa.getValor() == 5);
				});
			} else if(pessoa.getNome().equals("amigo2")) {
				pessoa.getDebitos().forEach(despesa -> {
					assertTrue(despesa.getValor() == 15);
				});
			} else if(pessoa.getNome().equals("amigo3")) {
				pessoa.getCreditos().forEach(despesa -> {
					assertTrue(despesa.getValor() == 15 || despesa.getValor() == 5);
				});
			}
		}
	}
	
	@Test 
	void testeCriarDespesaSemParametro() {
		criarDados();
		boolean teste = controleDados.criarDespesa("", 100, "12/12/2022", pessoas, valores);
		assertFalse(teste);
		boolean teste2 = controleDados.criarDespesa("Titulo", 0, "12/12/2022", pessoas, valores);
		assertFalse(teste2);
		boolean teste3 = controleDados.criarDespesa("Titulo", 100, null, pessoas, valores);
		assertFalse(teste3);
		boolean teste4 = controleDados.criarDespesa("Titulo", 100, "12/12/2022", null, valores);
		assertFalse(teste4);
		pessoas.remove(0);
		boolean teste5 = controleDados.criarDespesa("Titulo", 100, "12/12/2022", pessoas, valores);
		assertFalse(teste5);
	}
	
	@Test
	void testeExcluirDespesa() {
		criarDados();
		controleDados.criarDespesa("Titulo", 100, "12/12/2022", pessoas, valores);
		Despesa despesa1 = controleDados.getDespesas().get(0);
		controleDados.excluirDespesa(despesa1.getIdConta());
		assertTrue(controleDados.getDespesas().size() == 0);
	}
	
	@Test
	void testeRealizarPagamento() {
		criarDados();
		controleDados.criarDespesa("Titulo", 100, "12/12/2022", pessoas, valores);
		Despesa despesa = controleDados.getDespesas().get(0);
		controleDados.realizarPagamento(despesa.getId(), 10, "PIX", "20/11/2022");
		assertTrue(despesa.getTotalPago() == 10);
		assertFalse(despesa.isQuitado());
	}
}
