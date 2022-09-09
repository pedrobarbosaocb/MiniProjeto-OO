package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelDespesa extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton edit = new JButton("Ver Detalhes");
	private String _titulo = "";

	public PanelDespesa(String titulo, String valor, String vencimento, String credor, String[] devedores, boolean ativa ) {
		removeAll();
		setLayout(new FlowLayout(FlowLayout.CENTER, 10,20));
		add(new JLabel(titulo));
		add(new JLabel(valor));
		add(new JLabel(credor));
		
		for(int i=0;i<devedores.length;i++) {
			add(new JLabel(devedores[i]));
		}
		add(new JLabel(vencimento));
		
		_titulo = titulo;
		
		if (ativa) {
			add(edit);
			edit.addActionListener(this);
		}
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object src = e.getSource();

		if (src == edit) {
			new EditDespesa(_titulo);
			System.out.println("edit clicado");
		}
		
	}

}
