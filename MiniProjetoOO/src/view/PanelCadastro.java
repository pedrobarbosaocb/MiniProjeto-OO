package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelCadastro extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JButton despesas = new JButton("Despesas");
	private static JButton historico = new JButton("Histórico");
	private static JButton meu_perfil = new JButton("Meu Perfil");
	
	public PanelCadastro(){
		setLayout(new GridLayout(3, 0));
		
		add(despesas);
		add(historico);
		add(meu_perfil);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
