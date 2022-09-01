package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controle.ControleDados;

public class TelaCadastro extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
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
	private static JFormattedTextField txt_dt_nascimento;
	private static JTextField txt_senha = new JPasswordField(200);
	private static JTextField txt_confirm_senha = new JPasswordField(200);
	private static boolean has_txt_dt_nascimento = false;

	public TelaCadastro() {
		setModal(true);
		info_panel.removeAll();
		info_panel.setLayout(new GridLayout(6, 2));
		info_panel.add(nome);
		info_panel.add(txt_nome);
		info_panel.add(email);
		info_panel.add(txt_email);
		info_panel.add(telefone);
		info_panel.add(txt_telefone);
		info_panel.add(dt_nascimento);

		try {
			MaskFormatter formatoData = new MaskFormatter("##/##/####");
			formatoData.setPlaceholder("_");
			txt_dt_nascimento = new JFormattedTextField(formatoData);
			info_panel.add(txt_dt_nascimento);

		} catch (ParseException err) {
			System.err.println("Erro na formatação: " + err.getMessage());
			System.exit(-1);
		}

		info_panel.add(senha);
		info_panel.add(txt_senha);
		info_panel.add(confirm_senha);
		info_panel.add(txt_confirm_senha);

		btn_panel.setLayout(new FlowLayout());
		btn_panel.add(cancel_btn);
		cancel_btn.addActionListener(this);
		btn_panel.add(save_btn);
		save_btn.addActionListener(this);

		setLayout(new BorderLayout());
		add(info_panel, BorderLayout.CENTER);
		add(btn_panel, BorderLayout.SOUTH);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

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

		if (src == save_btn) {

			System.out.println(txt_nome.getText() + "\n" + txt_email.getText() + "\n" + txt_telefone.getText() + "\n"
					+ txt_dt_nascimento.getText() + "\n" + txt_senha.getText() + "\n" + txt_confirm_senha.getText());
		}
	}
}
