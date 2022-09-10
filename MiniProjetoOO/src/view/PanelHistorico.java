package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controle.ControleDespesas;
import controle.ControleDados;
import controle.ControleUsuarios;
import modelo.Despesa;
import modelo.Pessoa;

public class PanelHistorico extends JPanel implements ActionListener {

	/**
	 * Classe que gera a vizualização da tela de todas as despesas ligadas ao
	 * usuario logado
	 * 
	 * @author Carlos Eduardo & Pedro Barbosa
	 * @version 1.0
	 * 
	 * @see TelaMenuEntrada
	 **/

	private static final long serialVersionUID = 1L;

	public static JButton refresh_btn = new JButton("Atualizar Despesas");;
	public static JButton add_despesa = new JButton("Adicionar Despesa");
	public static JButton pagar_despesa = new JButton("Pagar Despesa");

	private static JPanel despesas_list_panel = new JPanel();
	private static JPanel north_panel = new JPanel();

	private static JTextField pesquisar_despesa = new JTextField();
	private static ControleDados _dados;
	private static JList<String> despesas_list = new JList<String>();
	private static DefaultListModel<String> listModel1 = new DefaultListModel<String>();

	private static ArrayList<Pessoa> pessoas;
	private static ArrayList<Despesa> despesas;

	public PanelHistorico(ControleDados dados) {

		despesas = new ArrayList<Despesa>();

		_dados = dados;

		setLayout(new BorderLayout());

		removeAll();

		north_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		north_panel.add(refresh_btn);
		add(north_panel, BorderLayout.NORTH);
		add(pesquisar_despesa);

		despesas_list_panel.setLayout(new GridLayout(1, 0));

		AdicionarDespesas("T");

		add(despesas_list_panel, BorderLayout.CENTER);

		refresh_btn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == refresh_btn) {
			AdicionarDespesas("T"); //o filtro ainda sera implementado
		}
	}

	/**
	 * Método que remove os ActionListeners instanciados no panel a fim de evitar
	 * repeticao
	 * 
	 */
	public void removeActionListeners() {
		refresh_btn.removeActionListener(this);
	}

	/**
	 * Método que adiciona as despesas a listagem
	 * 
	 * @param filtro String
	 */
	public void AdicionarDespesas(String filtro) {
		ControleUsuarios controle_user = new ControleUsuarios(_dados);
		//ControleContas controle_conta = new ControleContas(_dados);
		//ArrayList<Despesa> debitos_list = controle_conta.getContasPorUsuario(ControleDados.getUsuarioSessao().getEmail());
		//ArrayList<Despesa> creditos_list = controle_user.getCreditosUsuario(ControleDados.getUsuarioSessao().getId());

		listModel1.removeAllElements();

		/*for (int i = 0; i < debitos_list.size(); i++) {
			listModel1.addElement(
					"--> " + debitos_list.get(i).getTitulo() + " - " + debitos_list.get(i).getTotalPago() + " - "
							+ debitos_list.get(i).getValor() + " - " + debitos_list.get(i).getCredor().getNome());
			despesas.add(debitos_list.get(i));
		}*/

		despesas_list.setModel(listModel1);

		despesas_list_panel.add(despesas_list);

		revalidate();
		repaint();
	}
}