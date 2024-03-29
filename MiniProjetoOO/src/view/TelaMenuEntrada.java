package view;

import controle.*;
import modelo.Usuario;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Classe que gera a vizualização da tela de entrada do sistema
 * 
 * @author Carlos Eduardo and Pedro Barbosa
 * @version 1.0
 * 
 **/

public class TelaMenuEntrada extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static JPanel login_panel = new JPanel();
	private static JPanel btn_panel = new JPanel();

	private static JLabel titulo = new JLabel("Bem vinda(o) ao melhor auxiliador de divisão de despesas");
	private static JLabel login = new JLabel("Email");
	private static JLabel senha = new JLabel("Senha");
	private static JTextField txt_login = new JTextField();
	private static JTextField txt_senha = new JPasswordField();

	private static JButton login_btn = new JButton("Login");
	private static JButton cadastro_btn = new JButton("Criar conta");

	public static ControleDados dados = new ControleDados();

	/**
	 * Construtor TelaMenuEntrada
	 * 
	 * Gera a visualização do JFrame inicial juntamente com a tela de login que
	 * proporciona tanto o ingresso de um usuario a priori cadastrado e do registro
	 * de um novo usuario
	 * 
	 **/
	public TelaMenuEntrada() {

		setTitle("Divisor de Despesas");

		login_panel.setLayout(null);// new GridLayout(2,3));
		login_panel.setSize(10, 10);

		int x_position = 100;
		int y_position = 30;
		int login_height = 30;
		int login_width = 100;

		int[] coord = { x_position, y_position, login_width, login_height };

		login.setBounds(coord[0], coord[1], coord[2], coord[3]);
		txt_login.setBounds(coord[0] + 50, coord[1], coord[2] + 30, coord[3]);
		senha.setBounds(coord[0], coord[1] + 50, coord[2], coord[3]);
		txt_senha.setBounds(coord[0] + 50, coord[1] + 50, coord[2] + 30, coord[3]);

		login_panel.add(login);
		login_panel.add(txt_login);
		login_panel.add(senha);
		login_panel.add(txt_senha);

		titulo.setFont(new Font("Arial", Font.BOLD, 12));
		titulo.setSize(150, 30);

		/* adicionando os botões */

		login_btn.setSize(100, 30);
		cadastro_btn.setSize(100, 30);

		btn_panel.setLayout(new FlowLayout());
		btn_panel.add(login_btn);
		btn_panel.add(cadastro_btn);

		getRootPane().setDefaultButton(login_btn); // ao clicar enter o login_btn é ativado

		/* definindo posição dos paineis no jframe principal */
		add(titulo, BorderLayout.NORTH);
		add(login_panel, BorderLayout.CENTER);
		add(btn_panel, BorderLayout.SOUTH);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		setBounds((int) (width / 2) - 200, (int) (height / 2) - 125, 400, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	/**
	 * Método que dá start ao programa
	 * 
	 * @param args String(String)
	 **/
	public static void main(String[] args) {

		TelaMenuEntrada menu = new TelaMenuEntrada();

		dados.getDados().inserirDados();
		dados.criarDados();
		
		login_btn.addActionListener(menu);
		cadastro_btn.addActionListener(menu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == login_btn) {

			ControleUsuarios controleUser = new ControleUsuarios(dados);

			if (controleUser.verificarUsuario(txt_login.getText(), txt_senha.getText())) {
				Usuario user = controleUser.getUsuario(txt_login.getText());
				ControleDados.setUsuarioSessao(user);
				new TelaMain(dados);
			} else {
				JOptionPane.showMessageDialog(null,
						"O nome de usuário ou a senha estão incorretos\n"
								+ "caso não possua uma conta crie uma nova \nclicando no botão \"Criar Conta\".",
						null, JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (src == cadastro_btn) {
			new TelaCadastro(dados);
		}
	}
}
