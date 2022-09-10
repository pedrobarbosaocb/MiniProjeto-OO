package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controle.ControleDados;

/**
 * Classe que gera a vizualização da tela de cadastro
 * 
 * @author Carlos Eduardo & Pedro Barbosa
 * @version 1.0
 * 
 * @see TelaCadastro
 **/

public class TelaCadastro extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static JPanel btn_panel = new JPanel();
	private static JPanel info_panel = new JPanel();

	private static JButton cancel_btn = new JButton("Cancelar");
	private static JButton save_btn = new JButton("Salvar");

	private static JLabel nome = new JLabel("Nome:");
	private static JLabel email = new JLabel("Email:");
	private static JLabel dt_nascimento = new JLabel("Data de Nascimento:");
	private static JLabel senha = new JLabel("Senha:");
	private static JLabel confirm_senha = new JLabel("Confirmar Senha:");

	private static JTextField txt_nome = new JTextField(200);
	private static JTextField txt_email = new JTextField(200);
	private static JFormattedTextField txt_dt_nascimento;
	private static JTextField txt_senha = new JPasswordField(200);
	private static JTextField txt_confirm_senha = new JPasswordField(200);

	private static ControleDados _dados;

	/**
	 * Construtor TelaCadastro
	 * 
	 * @param dados ControleDados
	 **/
	public TelaCadastro(ControleDados dados) {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		setTitle("Novo Cadastro");

		EsvaziarCampos();
		_dados = dados;
		setModal(true);

		info_panel.removeAll();
		info_panel.setLayout(new GridLayout(6, 2));
		info_panel.add(nome);
		info_panel.add(txt_nome);
		info_panel.add(email);
		info_panel.add(txt_email);
		info_panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // padding

		try {

			info_panel.add(dt_nascimento);

			MaskFormatter formatoData = new MaskFormatter("##/##/####");
			formatoData.setPlaceholderCharacter('_');
			txt_dt_nascimento = new JFormattedTextField(formatoData);
			info_panel.add(txt_dt_nascimento);
		} catch (ParseException err) {
			System.err.println("Erro na formatação: " + err.getMessage());
		}

		info_panel.add(senha);
		info_panel.add(txt_senha);
		info_panel.add(confirm_senha);
		info_panel.add(txt_confirm_senha);

		btn_panel.setLayout(new FlowLayout());
		btn_panel.add(cancel_btn);
		btn_panel.add(save_btn);

		cancel_btn.addActionListener(this);
		save_btn.addActionListener(this);

		setLayout(new BorderLayout());
		add(info_panel, BorderLayout.CENTER);
		add(btn_panel, BorderLayout.SOUTH);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		setBounds((int) (width / 2) - 200, (int) (height / 2) - 125, 400, 250);
		setVisible(true);

		getRootPane().setDefaultButton(save_btn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == cancel_btn) {
			setVisible(false);
			dispose();
		}

		if (src == save_btn) {

			if (!txt_senha.getText().equals(txt_confirm_senha.getText())) {
				JLabel[] campos = { senha, confirm_senha };
				CamposVermelhos(campos);
				JOptionPane.showMessageDialog(null, "Os campos \"senha\" e \"confirmar senha\" estão diferentes!", null,
						JOptionPane.INFORMATION_MESSAGE);
			} else if (_dados.criarUsuario(txt_nome.getText(), txt_email.getText(), txt_dt_nascimento.getText(),
					txt_senha.getText())) {
				JOptionPane.showMessageDialog(null, "Usuário criado com sucesso!", null,
						JOptionPane.INFORMATION_MESSAGE);

				cancel_btn.removeActionListener(this);
				save_btn.removeActionListener(this);

				EsvaziarCampos();

				setVisible(false);
				dispose();
			} else {
				if (txt_nome.getText().isEmpty() || txt_email.getText().isEmpty()
						|| txt_dt_nascimento.getText().isEmpty() || txt_senha.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Verifique se todos os dados estão corretos!\nO nome", null,
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else {
			cancel_btn.removeActionListener(this);
			save_btn.removeActionListener(this);
		}
	}

	/**
	 * Método que altera cor dos campos selecionados para vermelho
	 * 
	 * @param labels JLabels[]
	 */
	public void CamposVermelhos(JLabel[] labels) {
		for (int i = 0; i < labels.length; i++) {
			labels[i].setForeground(new Color(255, 0, 0));
		}
	}

	/**
	 * Método que esvazia os campos de texto
	 * 
	 */
	public void EsvaziarCampos() {
		txt_nome.setText(null);
		txt_email.setText(null);
		txt_senha.setText(null);
		txt_confirm_senha.setText(null);
	}
}
