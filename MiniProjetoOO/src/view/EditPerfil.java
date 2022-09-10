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
import modelo.Usuario;

/**
 * Classe que gera a vizualização da tela de edicao do perfil do usuario logado
 * 
 * @author Carlos Eduardo & Pedro Barbosa
 * @version 1.0
 * 
 * @see TelaMenuEntrada
 **/

public class EditPerfil extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static ControleDados _dados;
	private static JPanel btn_panel = new JPanel();
	private static JButton save_btn = new JButton("Salvar");
	private static JButton cancel_btn = new JButton("Cancelar");

	private static JPanel panel_main = new JPanel();

	private static JLabel nome = new JLabel("Nome");
	private static JLabel email = new JLabel("Email");
	private static JLabel dt_nascimento = new JLabel("Data de Nascimento");
	private static JLabel senha = new JLabel("Senha");

	private static JTextField txt_nome = new JTextField(20);
	private static JTextField txt_email = new JTextField(20);
	private static JTextField txt_dt_nascimento = new JTextField(20);
	private static JTextField txt_senha = new JTextField(20);

	/**
	 * Construtor EditPerfil
	 * 
	 * Gera a visualização do JDialog de edição dos parametros do usuario da sessao
	 * 
	 * @param dados ControleDados
	 **/
	public EditPerfil(ControleDados dados) {
		_dados = dados;

		Usuario current_user = ControleDados.getUsuarioSessao();

		txt_nome.setText(current_user.getNome());
		txt_email.setText(current_user.getEmail());
		txt_dt_nascimento.setText(current_user.getAniversario());
		txt_senha.setText(current_user.getSenha());

		setModal(true);
		setLayout(new BorderLayout());

		panel_main.setLayout(new GridLayout(4, 2));
		panel_main.setBorder(new EmptyBorder(20, 10, 20, 10));

		panel_main.add(nome);
		panel_main.add(txt_nome);
		panel_main.add(email);
		panel_main.add(txt_email);
		panel_main.add(dt_nascimento);
		panel_main.add(txt_dt_nascimento);
		panel_main.add(senha);
		panel_main.add(txt_senha);

		add(panel_main, BorderLayout.CENTER);

		btn_panel.setLayout(new FlowLayout());
		btn_panel.add(cancel_btn);
		btn_panel.add(save_btn);

		add(btn_panel, BorderLayout.SOUTH);

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
			if (_dados.editarUsuario(ControleDados.getUsuarioSessao().getId(), txt_nome.getText(), txt_email.getText(),
					txt_dt_nascimento.getText(), txt_senha.getText())) {
				JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!", null,
						JOptionPane.INFORMATION_MESSAGE);

				save_btn.removeActionListener(this);
				cancel_btn.removeActionListener(this);

				setVisible(false);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Erro ao atualizar usuario!", null,
						JOptionPane.INFORMATION_MESSAGE);

				revalidate();
				repaint();
			}
		}

		if (src == cancel_btn) {
			save_btn.removeActionListener(this);
			cancel_btn.removeActionListener(this);

			setVisible(false);
			dispose();
		}

	}

}
