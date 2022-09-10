package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.ControleDados;
import modelo.Despesa;
import modelo.Usuario;

public class PayDespesa extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ControleDados _dados;
	private static JPanel btn_panel = new JPanel();
	private static JButton save_btn = new JButton("Pagar");
	private static JButton cancel_btn = new JButton("Cancelar");

	private static JPanel panel_main = new JPanel();
	private static JPanel center_panel = new JPanel();

	private static JLabel devedor = new JLabel("Devedor");
	private static JLabel credor = new JLabel("Credor");
	private static JLabel pagar = new JLabel("Valor a pagar");

	private static JLabel txt_devedor = new JLabel("");
	private static JLabel txt_credor = new JLabel("");
	private static JTextField txt_pagar = new JTextField(20);

	public PayDespesa(Despesa despesa) {
		setModal(true);
		txt_devedor.setText(despesa.getDevedor().getNome());
		txt_credor.setText(despesa.getCredor().getNome());

		center_panel.setLayout(new GridLayout(3, 2));
		center_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		center_panel.add(devedor);
		center_panel.add(txt_devedor);
		center_panel.add(credor);
		center_panel.add(txt_credor);
		center_panel.add(pagar);
		center_panel.add(txt_pagar);

		add(center_panel, BorderLayout.CENTER);

		add(btn_panel, BorderLayout.SOUTH);
		btn_panel.setLayout(new FlowLayout());
		btn_panel.add(cancel_btn);
		btn_panel.add(save_btn);

		cancel_btn.addActionListener(this);
		save_btn.addActionListener(this);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		setBounds((int) (width / 2) - 200, (int) (height / 2) - 125, 400, 250);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == save_btn) {
			cancel_btn.doClick();
		}

		if (src == cancel_btn) {
			save_btn.removeActionListener(this);
			cancel_btn.removeActionListener(this);

			setVisible(false);
			dispose();
		}
	}
}
