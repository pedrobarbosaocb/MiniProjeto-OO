package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelDespesas extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JLabel titulo = new JLabel("Despesas");
	private static JPanel despesas = new JPanel();
	private static JButton add_despesa = new JButton("Adicionar Despesa");
	private static JTextField pesquisar_despesa = new JTextField();

	public PanelDespesas() {
		setLayout(new BorderLayout());
		
		removeAll();
		
		add(titulo, BorderLayout.NORTH);
		add(pesquisar_despesa);
		String[] devedores = {"Pedro", "Paulo", "Paula"};
		int n_despesas = 8;
		for (int i=0; i<n_despesas; i++) {
			despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu",  devedores , true ));
		}		
		despesas.setLayout(new GridLayout(n_despesas,0));
		add(despesas, BorderLayout.CENTER);
		add(add_despesa, BorderLayout.SOUTH);
		add_despesa.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == add_despesa) {
			new AddDespesa();
		}
		
	}

}