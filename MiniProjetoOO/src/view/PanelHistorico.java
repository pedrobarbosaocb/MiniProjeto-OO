package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private static JLabel titulo = new JLabel("Histórico");
	private String[] devedores = { "---" };
	
	public PanelHistorico() {
		System.out.println("painel de historico funcionando!");
		setLayout(new BorderLayout());
		removeAll();
		add(titulo, BorderLayout.NORTH);
		int n_despesas = 8;
		despesas.add(new PanelDespesa("OPa tudo bem", "---", "---", "----", devedores, false ));
		despesas.add(new PanelDespesa("eae ff", "---", "---", "----", devedores, false ));
		despesas.add(new PanelDespesa("testando", "---", "---", "----", devedores, false ));
		despesas.setLayout(new GridLayout(n_despesas,0));
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
			despesas.add(new PanelDespesa("---", "---", "---", "----", devedores, false ));
		}
	}
}
