package view;

import java.awt.BorderLayout;
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
	private static JButton refresh_despesa = new JButton("Recarregar");
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
		add(refresh_despesa, BorderLayout.EAST);
		
		add_despesa.addActionListener(this);
		refresh_despesa.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == add_despesa) {
			new AddDespesa(_dados);
		}
		
		if (src == refresh_despesa) {
			AdicionarDespesas("T");
		}
	}

	public void AdicionarDespesas(String filtro) {
		ControleUsuarios controle_user = new ControleUsuarios(_dados);
		ArrayList<Despesa> debitos_list = controle_user.getDebitosUsuario(ControleDados.getUsuarioSessao().getId());
		ArrayList<Despesa> creditos_list = controle_user.getCreditosUsuario(ControleDados.getUsuarioSessao().getId());
		
		System.out.println(controle_user.getCreditosUsuario(ControleDados.getUsuarioSessao().getId()));
		System.out.println(controle_user.getDebitosUsuario(ControleDados.getUsuarioSessao().getId()));
		listModel1.removeAllElements();
		
		for (int i = 0; i < debitos_list.size(); i++) {
				listModel1.addElement( "DEBITO " + debitos_list.get(i).getTitulo() + " - " 
										+ debitos_list.get(i).getTotalPago() + " - "
										+ debitos_list.get(i).getValor() + " - "
										+ debitos_list.get(i).getCredor().getNome());
		}
		
		for (int i = 0; i < creditos_list.size(); i++) {
			listModel1.addElement( "CREDITO " + creditos_list.get(i).getTitulo() + " - " 
									+ creditos_list.get(i).getTotalPago() + " - "
									+ creditos_list.get(i).getValor() + " - "
									+ creditos_list.get(i).getDevedor().getNome());
	}
		
		despesas.setModel(listModel1);		
		
		despesas_panel.add(despesas);
		
		revalidate();
		repaint();
	}
}