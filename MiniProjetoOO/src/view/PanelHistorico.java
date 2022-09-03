package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelHistorico extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel despesas = new JPanel();
	private static JButton limp_historico = new JButton("Limpar Histórico");
	
	public PanelHistorico() {
		System.out.println("painel de historico funcionando!");
		setLayout(new BorderLayout());
		
		removeAll();
		int n_despesas = 8;
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", false ));
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", false ));
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", false ));
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", false ));
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", false ));
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", false ));
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", false ));
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", false ));
		despesas.add(new PanelDespesa("Coquinha Gelada", "11,00", "01/09/2023", "Kadu", "Pedro", false ));
		despesas.setLayout(new GridLayout(n_despesas,0));
		despesas.setBorder(BorderFactory.createLineBorder(Color.black));
		add(despesas, BorderLayout.CENTER);
		add(limp_historico, BorderLayout.SOUTH);
		limp_historico.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == limp_historico) {
			despesas.removeAll();
			
			revalidate();
			repaint();
		}
		
	}

}
