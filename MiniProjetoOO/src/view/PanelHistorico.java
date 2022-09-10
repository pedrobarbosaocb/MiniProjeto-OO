package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controle.ControleDados;

public class PanelHistorico extends JPanel implements ActionListener {

	/**
	 * Classe que gera a vizualização do painel de historico de despesas
	 * 
	 * @author Carlos Eduardo & Pedro Barbosa
	 * @version 1.0
	 * 
	 * @see TelaMenuEntrada
	 **/
	private static final long serialVersionUID = 1L;
	private static JPanel despesas = new JPanel();
	private static JButton limp_historico = new JButton("Limpar Histórico");
	private static JLabel titulo = new JLabel("Histórico ---------------------------------------------------------");
	private static ControleDados _dados;

	public PanelHistorico(ControleDados dados) {
		_dados = dados;

		System.out.println("painel de historico funcionando!");

		setLayout(new BorderLayout());
		add(titulo, BorderLayout.NORTH);

		int n_despesas = 0;
		if (n_despesas == 0) {
			add(new JLabel("*Sem registros no Histórico."), BorderLayout.CENTER);
		} else {
			System.out.println("ferrou");
		}

		add(limp_historico, BorderLayout.SOUTH);
		limp_historico.addActionListener(this);

		this.setMinimumSize(new Dimension(2000, 2000));

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
