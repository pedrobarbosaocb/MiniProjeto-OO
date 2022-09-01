package view;

import controle.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaMenuEntrada extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static JLabel titulo = new JLabel("Olá, faça seu login ou crie uma conta");

	private static JPanel login_panel = new JPanel();
	private static JPanel btn_panel = new JPanel();

	private static JLabel login = new JLabel("Usuário");
	private static JLabel senha = new JLabel("Senha");
	private static JTextField txt_login = new JTextField();
	// private static JPasswordField txt_senha = new JPasswordField();
	private static JTextField txt_senha = new JPasswordField();

	private static JButton login_btn = new JButton("Login");
	private static JButton cadastro_btn = new JButton("Criar conta");

	public static ControleDados dados = new ControleDados();

	public TelaMenuEntrada() {

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

		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setSize(150, 30);

		/* adicionando os botões */

		login_btn.setSize(100, 30);
		cadastro_btn.setSize(100, 30);

		btn_panel.setLayout(new FlowLayout());
		btn_panel.add(login_btn);
		btn_panel.add(cadastro_btn);

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

	public static void main(String[] args) {

		TelaMenuEntrada menu = new TelaMenuEntrada();

		dados.getDados().inserirDados();
		dados.criarUsuario("carlos", "kdu@gmail.com", "26/05/2003", "minhasenhaforte");

		login_btn.addActionListener(menu);
		cadastro_btn.addActionListener(menu);

	}

	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == login_btn) {
			
			
			//setVisible(false);
			ControleUsuarios controleUser = new ControleUsuarios(dados);
			
			// por algum motivo quando verifica usando txt_login.getText() e txt_senha.getText() da false
			if(controleUser.verificarUsuario("kdu@gmail.com", "minhasenhaforte")) {
				new TelaMain();
			} else {
				JOptionPane.showMessageDialog(null,
						"O nome de usuário ou a senha estão incorretos\n" + "caso não possua uma conta crie uma nova clicando no botão Cadastro",
						null, JOptionPane.INFORMATION_MESSAGE);
			}
			
		}

		if (src == cadastro_btn) {
			new TelaCadastro();
			//setVisible(false);
			
			/*
			JOptionPane.showMessageDialog(null,
					"Ainda precisam ser implementadas as funcionalidades\n" + "relacionadas a curso e a matrícula",
					null, JOptionPane.INFORMATION_MESSAGE);
			menu_frame.setVisible(false);
			*/
		}
	}
}
