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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import controle.ControleDados;
import controle.ControleUsuarios;
import modelo.Amigo;

/**
 * Classe que gera a vizualização da tela de registro de um novo amigo ao
 * sistema do usuario logado
 * 
 * @author Carlos Eduardo and Pedro Barbosa
 * @version 1.0
 * 
 * @see TelaMenuEntrada
 **/

public class EditAmigo extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static ControleDados _dados;
	private static JPanel btn_panel = new JPanel();
	private static JButton save_btn = new JButton("Salvar");
	private static JButton cancel_btn = new JButton("Cancelar");

	private static JPanel panel_main = new JPanel();

	private static JLabel nome = new JLabel("Nome");
	private static JLabel email = new JLabel("Email");
	private static JLabel telefone = new JLabel("telefone");

	private static JTextField txt_nome = new JTextField(20);
	private static JTextField txt_email = new JTextField(20);
	private static JTextField txt_telefone = new JTextField(20);

	private static Amigo amigo;

	/**
	 * Construtor EditAmigo
	 * 
	 * @param dados ControleDados
	 * @param amigo Amigo
	 **/
	public EditAmigo(ControleDados dados, Amigo amigo) {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		EditAmigo.amigo = amigo;
		_dados = dados;

		setTitle("Registrando amigo");

		setModal(true);
		setLayout(new BorderLayout());

		panel_main.setLayout(new GridLayout(4, 2));
		panel_main.setBorder(new EmptyBorder(20, 10, 20, 10));

		panel_main.add(nome);
		panel_main.add(txt_nome);
		txt_nome.setText(amigo.getNome());
		panel_main.add(email);
		panel_main.add(txt_email);
		txt_email.setText(amigo.getEmail());
		panel_main.add(telefone);
		panel_main.add(txt_telefone);
		txt_telefone.setText(amigo.getTelefone());

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
			if (_dados.criarAmigo(ControleDados.getUsuarioSessao(), txt_nome.getText(), txt_email.getText(),
					txt_telefone.getText())) {
				ControleUsuarios user = new ControleUsuarios(_dados);
				user.excluirAmigoDeUsuario(ControleDados.getUsuarioSessao().getId(), amigo.getId());
				JOptionPane.showMessageDialog(null, "Amigo atualizado com sucesso!", null,
						JOptionPane.INFORMATION_MESSAGE);

				save_btn.removeActionListener(this);
				cancel_btn.removeActionListener(this);

				setVisible(false);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Erro ao atualizar amigo!", null, JOptionPane.INFORMATION_MESSAGE);

				revalidate();
				repaint();
			}
		}

		if (src == cancel_btn) {
			panel_main.removeAll();

			save_btn.removeActionListener(this);
			cancel_btn.removeActionListener(this);

			setVisible(false);
			dispose();
		}

	}

}