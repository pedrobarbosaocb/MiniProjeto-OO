package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelo.Pessoa;

public class PanelDespesa extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel edit_panel = new JPanel();
	private static JPanel info_panel = new JPanel();
	private JButton edit = new JButton("Editar");
	private String _titulo = "";

	public PanelDespesa(String titulo, String valor, String vencimento, String credor, String devedor, boolean ativa ) {
		removeAll();
		setLayout(new FlowLayout(FlowLayout.CENTER, 10,20));
		add(new JLabel(titulo));
		add(new JLabel(valor));
		add(new JLabel(credor));
		add(new JLabel(devedor));
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
