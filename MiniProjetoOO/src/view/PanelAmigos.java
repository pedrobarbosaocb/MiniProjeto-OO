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
import modelo.Amigo;

/**
 * Classe que gera a vizualização da tela dos presentes na conta do usuario
 * logado
 * 
 * @author Carlos Eduardo and Pedro Barbosa
 * @version 1.0
 * 
 * @see TelaMenuEntrada
 **/

public class PanelAmigos extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static JButton refresh_btn = new JButton("Atualizar");
	public static JButton add_amigo = new JButton("Adicionar Novo Amigo");
	public static JButton edit_amigo = new JButton("Editar Amigo");
	public static JButton remove_amigo = new JButton("Remover Amigo");
	public static JButton search_btn = new JButton("Buscar");
	public static JTextField search = new JTextField(20);
	private static JPanel search_panel = new JPanel();

	private static JPanel amigos_list_panel = new JPanel();
	private static JPanel north_panel = new JPanel();

	private static ControleDados _dados;
	private static JList<String> amigos_list = new JList<String>();
	private static DefaultListModel<String> listModel1 = new DefaultListModel<String>();

	private static ArrayList<Amigo> amigos;

	/**
	 * Construtor PanelAmigos
	 * 
	 * Gera a visualização do painel que possui e gerencia os amigos do usuario da
	 * sessao
	 * 
	 * @param dados ControleDados
	 **/
	public PanelAmigos(ControleDados dados) {

		amigos = new ArrayList<Amigo>();

		_dados = dados;

		setLayout(new BorderLayout());

		removeAll();

		north_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		search_panel.add(search);
		search_panel.add(search_btn);
		search_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		search_panel.setBorder(new EmptyBorder(0, 0, 0, 10));
		search.setPreferredSize(new Dimension(57, 27));
		north_panel.add(search_panel);
		north_panel.add(edit_amigo);
		north_panel.add(remove_amigo);
		add(north_panel, BorderLayout.NORTH);

		amigos_list_panel.setLayout(new GridLayout(1, 0));

		AdicionarAmigos("T");

		add(amigos_list_panel, BorderLayout.CENTER);
		add(add_amigo, BorderLayout.SOUTH);

		search_btn.addActionListener(this);
		remove_amigo.addActionListener(this);
		add_amigo.addActionListener(this);
		refresh_btn.addActionListener(this);
		edit_amigo.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == add_amigo) {
			new AddAmigo(_dados);
			AdicionarAmigos("");
		}

		if (src == refresh_btn) {
			AdicionarAmigos("");
		}

		if (src == edit_amigo) {
			if (!amigos_list.isSelectionEmpty()) {
				new EditAmigo(_dados, amigos.get(amigos_list.getSelectedIndex()));
				AdicionarAmigos("");
			} else {
				JOptionPane.showMessageDialog(null, "Selecione o amigo que você deseja remover!", null,
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (src == search_btn) {
			AdicionarAmigos(search.getText());
		}

		if (src == remove_amigo) {
			if (!amigos_list.isSelectionEmpty()) {
				ControleUsuarios user = new ControleUsuarios(_dados);
				for (Amigo amigo: amigos) {
					if (user.excluirAmigoDeUsuario(ControleDados.getUsuarioSessao().getId(),
							amigos.get(amigos_list.getSelectedIndex()).getId())) {
							amigos.remove(amigo);
							break;
					}
				}
				AdicionarAmigos("");

			} else {
				JOptionPane.showMessageDialog(null, "Selecione o amigo que você deseja editar!", null,
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
		add_amigo.removeActionListener(this);
		refresh_btn.removeActionListener(this);
		edit_amigo.removeActionListener(this);
	}

	/**
	 * Método que adiciona as despesas a listagem
	 * 
	 * @param filtro String
	 */
	public void AdicionarAmigos(String filtro) {
		ControleUsuarios controle_user = new ControleUsuarios(_dados);
		ArrayList<Amigo> amigos_list_got = controle_user.getAmigosUsuario(ControleDados.getUsuarioSessao().getId());

		amigos.clear();

		listModel1.removeAllElements();

		for (int i = 0; i < amigos_list_got.size(); i++) {
			if (amigos_list_got.get(i).getNome().toUpperCase().contains(filtro.toUpperCase())) {
				listModel1.addElement(amigos_list_got.get(i).getNome());
				amigos.add(amigos_list_got.get(i));
			}
		}

		if (amigos_list_got.size() == 0) {
			edit_amigo.setEnabled(false);
			remove_amigo.setEnabled(false);
		} else {
			edit_amigo.setEnabled(true);
			remove_amigo.setEnabled(true);
		}

		amigos_list.setModel(listModel1);

		amigos_list_panel.add(amigos_list);

		revalidate();
		repaint();
	}
}
