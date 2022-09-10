package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import controle.ControleDados;
import controle.ControleDespesas;
import modelo.Despesa;

/**
 * Classe que gera a vizualização da tela de todas as despesas ligadas ao
 * usuario logado
 * 
 * @author Carlos Eduardo and Pedro Barbosa
 * @version 1.0
 * 
 * @see TelaMenuEntrada
 **/

public class PanelHistorico extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static JButton refresh_btn = new JButton("Atualizar Despesas");;
	public static JButton add_despesa = new JButton("Adicionar Despesa");
	public static JButton pagar_despesa = new JButton("Pagar Despesa");

	private static JPanel despesas_list_panel = new JPanel();
	private static JPanel north_panel = new JPanel();

	private static ControleDados _dados;
	private static JList<String> despesas_list = new JList<String>();
	private static DefaultListModel<String> listModel1 = new DefaultListModel<String>();

	private static ArrayList<Despesa> despesas;

	/**
	 * Construtor PanelHistorico
	 * 
	 * Gera a visualização do painel de Historico, o qual possui todos os registros
	 * de despesas já quitados
	 * 
	 * @param dados ControleDados
	 **/
	public PanelHistorico(ControleDados dados) {

		despesas = new ArrayList<Despesa>();

		_dados = dados;

		setLayout(new BorderLayout());

		removeAll();

		north_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		add(north_panel, BorderLayout.NORTH);

		despesas_list_panel.setLayout(new GridLayout(1, 0));

		AdicionarDespesas("");

		add(despesas_list_panel, BorderLayout.CENTER);

		refresh_btn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == refresh_btn) {
			AdicionarDespesas("T"); // o filtro ainda sera implementado
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
		ControleDespesas controle_despesas = new ControleDespesas(_dados);

		ArrayList<Despesa> despesas_all_list = controle_despesas
				.getDespesasEmail(ControleDados.getUsuarioSessao().getEmail());

		listModel1.removeAllElements();

		for (int i = 0; i < despesas_all_list.size(); i++) {
			if (despesas_all_list.get(i).isQuitado()) {
				listModel1.addElement(" --> " + despesas_all_list.get(i).getTitulo() + " - "
						+ despesas_all_list.get(i).getDevedor().getNome() + " pagou R$ "
						+ String.format("%.2f", despesas_all_list.get(i).getTotalPago()) + " a "
						+ despesas_all_list.get(i).getCredor().getNome());
				despesas.add(despesas_all_list.get(i));
			}
		}

		despesas_list.setModel(listModel1);

		despesas_list_panel.add(despesas_list);

		revalidate();
		repaint();
	}
}