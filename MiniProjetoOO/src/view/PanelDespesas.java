package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelDespesas extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JLabel saudacao = new JLabel("descricao");
	private static JLabel saudacao1 = new JLabel("amigo1");
	private static JLabel saudacao2 = new JLabel("exemplo");
	private static JLabel saudacao3 = new JLabel("exemplo");
	private static JPanel despesas = new JPanel();
	private static JPanel despesa = new JPanel();
	private static JButton add_despesa = new JButton("Adicionar Despesa");

	public PanelDespesas() {
		setLayout(new BorderLayout());

		removeAll();
		int n_despesas = 8;
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", true ));
		despesas.add(new PanelDespesa("Suco Verde", "11,00", "01/09/2023", "Kadu", "Pedro", true ));
		despesas.add(new PanelDespesa("Pão de Queijo vegano", "11,00", "01/09/2023", "Kadu", "Pedro", true ));
		despesas.add(new PanelDespesa("Guardanapo tres coracoes", "11,00", "01/09/2023", "Kadu", "Pedro", true ));
		despesas.add(new PanelDespesa("teste", "11,00", "01/09/2023", "Kadu", "Pedro", true ));
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", true ));
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", true ));
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", true ));
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", true ));
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", true ));
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", true ));
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", true ));
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", true ));
		despesas.setLayout(new GridLayout(n_despesas,0));
		add(despesas, BorderLayout.CENTER);
		add(add_despesa, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}