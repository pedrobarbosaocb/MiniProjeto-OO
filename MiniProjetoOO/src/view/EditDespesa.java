package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controle.ControleDados;

public class EditDespesa extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JButton cancel_btn = new JButton("Cancelar");
	private static JButton save_btn = new JButton("Salvar");
	private static JPanel btn_panel = new JPanel();
	private static ControleDados _dados;
	private static int _id_despesa;
	
	public EditDespesa(ControleDados dados, int id_despesa) {
		_id_despesa = id_despesa;
		_dados = dados;
		setModal(true);
		add(new JLabel("titulo"));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		btn_panel.setLayout(new FlowLayout());
		btn_panel.add(cancel_btn);
		btn_panel.add(save_btn);
		
		add(btn_panel, BorderLayout.SOUTH);		

		setBounds((int) (width / 2) - 200, (int) (height / 2) - 125, 400, 250);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if (src == cancel_btn) {
			setVisible(false);
			dispose();
		}
	}
}
