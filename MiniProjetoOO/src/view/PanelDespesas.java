package view;

import java.awt.BorderLayout;
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

import controle.ControleDados;
import controle.ControleUsuarios;
import modelo.Amigo;
import modelo.Despesa;
import modelo.Pessoa;

public class PanelDespesas extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static JLabel titulo = new JLabel("Despesas");
	private static JPanel despesas_panel = new JPanel();
	private static JButton add_despesa = new JButton("Adicionar Despesa");
	private static JTextField pesquisar_despesa = new JTextField();
	private static ControleDados _dados;
	private static JList<String> despesas = new JList<String>();
	private DefaultListModel<String> listModel1 = new DefaultListModel<String>();
	private static JLabel vazio = new JLabel("Uau que vazio...");
	private ArrayList<Pessoa> pessoas;
	
	public PanelDespesas(ControleDados dados) {

		_dados = dados;

		setLayout(new BorderLayout());

		removeAll();

		add(titulo, BorderLayout.NORTH);
		add(pesquisar_despesa);
		
		despesas_panel.setLayout(new GridLayout(1,0));

		AdicionarDespesas("T");
		

		add(despesas_panel, BorderLayout.CENTER);
		add(add_despesa, BorderLayout.SOUTH);
		add_despesa.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == add_despesa) {
			new AddDespesa(_dados);
		}
	}

	public void AdicionarDespesas(String filtro) {
		ControleUsuarios controle_user = new ControleUsuarios(_dados);
		ArrayList<Despesa> despesas_list = controle_user.getDebitosUsuario(ControleDados.getUsuarioSessao().getId());
		
		despesas_list.addAll(controle_user.getCreditosUsuario(ControleDados.getUsuarioSessao().getId()));
		
		listModel1.addElement("Teste");
		for (int i = 0; i < despesas_list.size() - 1; i++) {
				listModel1.addElement(despesas_list.get(i).getTitulo());
		}
		
		despesas.setModel(listModel1);
		despesas_panel.add(despesas);
	}
}