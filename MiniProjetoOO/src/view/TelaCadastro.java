package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controle.ControleDados;

public class TelaCadastro extends JFrame implements ActionListener {
	
	private static JFrame cadastro_frame = new JFrame("Cadastro");
	private static JPanel btn_panel = new JPanel();
	private static JButton cancel_btn = new JButton("Cancelar");
	private static JButton save_btn = new JButton("Salvar");
	private static JPanel info_panel = new JPanel();
	private static JLabel nome = new JLabel("Nome:");
	private static JLabel email = new JLabel("Email:");
	private static JLabel telefone = new JLabel("Telefone:");
	private static JLabel dt_nascimento = new JLabel("Data de Nascimento:");
	private static JLabel senha = new JLabel("Senha:");
	private static JLabel confirm_senha = new JLabel("Confirmar Senha:");
	private static JTextField txt_nome = new JTextField(200);
	private static JTextField txt_email = new JTextField(200);
	private static JTextField txt_telefone = new JTextField(200);
	private static JTextField txt_dt_nascimento = new JTextField(200);
	private static JTextField txt_senha = new JTextField(200);
	private static JTextField txt_confirm_senha = new JTextField(200);
	
	
	public TelaCadastro() {
		
		info_panel.setLayout(new GridLayout(6,2));
		info_panel.add(nome);
		info_panel.add(txt_nome);
		info_panel.add(email);
		info_panel.add(txt_email);
		info_panel.add(telefone);
		info_panel.add(txt_telefone);
		info_panel.add(dt_nascimento);
		info_panel.add(txt_dt_nascimento);
		info_panel.add(senha);
		info_panel.add(txt_senha);
		info_panel.add(confirm_senha);
		info_panel.add(txt_confirm_senha);
		
		btn_panel.setLayout(new FlowLayout());
		btn_panel.add(cancel_btn);
		btn_panel.add(save_btn);
		cancel_btn.addActionListener(this);
		
		cadastro_frame.setLayout(new BorderLayout());
		cadastro_frame.add(info_panel, BorderLayout.CENTER);
		cadastro_frame.add(btn_panel, BorderLayout.SOUTH);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		cadastro_frame.setBounds((int) (width / 2) - 200, (int) (height / 2) - 125, 400, 250);
		cadastro_frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == cancel_btn) {
			cadastro_frame.setVisible(false);
			cadastro_frame.dispose();
		}
		
		if (src == cancel_btn) {
			cadastro_frame.setVisible(false);
			cadastro_frame.dispose();
		}
		
		
	}

}
