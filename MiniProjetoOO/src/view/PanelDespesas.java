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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.ControleDados;
import controle.ControleUsuarios;
import modelo.Despesa;

/**
 * Classe que gera a vizualização da tela das despesas presentes na conta do
 * usuario logado
 * 
 * @author Carlos Eduardo & Pedro Barbosa
 * @version 1.0
 * 
 * @see TelaMenuEntrada
 **/

public class PanelDespesas extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static JButton refresh_btn = new JButton("Atualizar");;
	public static JButton add_despesa = new JButton("Nova Despesa");
	public static JButton remove_despesa = new JButton("Remover Despesa");
	public static JButton pagar_despesa = new JButton("Realizar Pagamento");
	public static JTextField search = new JTextField(20);
	public static JButton search_btn = new JButton("Buscar");
	private static JPanel search_panel = new JPanel();

	private static JPanel despesas_list_panel = new JPanel();
	private static JPanel north_panel = new JPanel();

	private static JTextField pesquisar_despesa = new JTextField(20);
	private static ControleDados _dados;
	private static JList<String> despesas_list = new JList<String>();
	private static DefaultListModel<String> listModel1 = new DefaultListModel<String>();

	private static ArrayList<Despesa> despesas;

	/**
	 * Construtor PanelDespesas
	 * 
	 * Gera a visualização do painel que contém e gerencia as despesas presentes nos
	 * dados do usuario da sessao
	 * 
	 * @param dados ControleDados
	 **/
	public PanelDespesas(ControleDados dados) {

		despesas = new ArrayList<Despesa>();

		_dados = dados;

		setLayout(new BorderLayout());

		removeAll();

		search_panel.add(search);
		search_panel.add(search_btn);
		search_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		search_panel.setBorder(new EmptyBorder(0, 0, 0, 10));
		search.setPreferredSize(new Dimension(57, 27));
		north_panel.add(search_panel);

		north_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		north_panel.add(pagar_despesa);
		north_panel.add(remove_despesa);
		add(north_panel, BorderLayout.NORTH);
		add(pesquisar_despesa);

		despesas_list_panel.setLayout(new GridLayout(1, 0));

		AdicionarDespesas("");

		add(despesas_list_panel, BorderLayout.CENTER);
		add(add_despesa, BorderLayout.SOUTH);

		search_btn.addActionListener(this);
		add_despesa.addActionListener(this);
		remove_despesa.addActionListener(this);
		refresh_btn.addActionListener(this);
		pagar_despesa.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == add_despesa) {
			new AddDespesa(_dados);
		}

		if (src == search_btn) {
			AdicionarDespesas(search.getText());
		}

		if (src == refresh_btn) {
			AdicionarDespesas("");
		}

		if (src == remove_despesa) {
			if (!despesas_list.isSelectionEmpty()) {
				_dados.excluirDespesa(despesas.get(despesas_list.getSelectedIndex()).getIdConta());
				AdicionarDespesas("");
			} else {
				JOptionPane.showMessageDialog(null, "Selecione a despesa que voce deseja remover!", null,
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (src == pagar_despesa) {
			if (!despesas_list.isSelectionEmpty()) {
				new PayDespesa(_dados, despesas.get(despesas_list.getSelectedIndex()));
			} else {
				JOptionPane.showMessageDialog(null, "Selecione a despesa que voce deseja pagar!", null,
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * Método que remove os ActionListeners instanciados no panel a fim de evitar
	 * repeticao
	 * 
	 */
	public void removeActionListeners() {
		search_btn.removeActionListener(this);
		add_despesa.removeActionListener(this);
		remove_despesa.removeActionListener(this);
		refresh_btn.removeActionListener(this);
		pagar_despesa.removeActionListener(this);
	}

	/**
	 * Método que adiciona as despesas com relação ao filtro na listagem
	 * 
	 * @param filtro String
	 */
	public void AdicionarDespesas(String filtro) {
		ControleUsuarios controle_user = new ControleUsuarios(_dados);
		ArrayList<Despesa> debitos_list = controle_user.getDebitosUsuario(ControleDados.getUsuarioSessao().getId());
		ArrayList<Despesa> creditos_list = controle_user.getCreditosUsuario(ControleDados.getUsuarioSessao().getId());

		listModel1.removeAllElements();

		for (int i = 0; i < debitos_list.size(); i++) {
			if (!debitos_list.get(i).isQuitado()) {
				if (debitos_list.get(i).getTitulo().toUpperCase().contains(filtro.toUpperCase())) {
					listModel1.addElement("DEBITO --> " + debitos_list.get(i).getTitulo() + " - Você deve R$"
							+ String.format("%.2f", debitos_list.get(i).getValor() - debitos_list.get(i).getTotalPago())
							+ " a " + debitos_list.get(i).getCredor().getNome());
					despesas.add(debitos_list.get(i));
				}
			}
		}

		for (int i = 0; i < creditos_list.size(); i++) {
			if (!creditos_list.get(i).isQuitado()) {
				if (creditos_list.get(i).getTitulo().toUpperCase().contains(filtro.toUpperCase())) {
					listModel1.addElement("CREDITO --> " + creditos_list.get(i).getTitulo() + " - "
							+ creditos_list.get(i).getDevedor().getNome() + " deve R$"
							+ String.format("%.2f",
									creditos_list.get(i).getValor() - creditos_list.get(i).getTotalPago())
							+ " a você. ");
					despesas.add(creditos_list.get(i));
				}
			}
		}

		if (creditos_list.size() == 0 && debitos_list.size() == 0) {
			pagar_despesa.setEnabled(false);
			remove_despesa.setEnabled(false);
		} else {
			pagar_despesa.setEnabled(true);
			remove_despesa.setEnabled(true);
		}

		despesas_list.setModel(listModel1);

		despesas_list_panel.add(despesas_list);

		revalidate();
		repaint();
	}
}