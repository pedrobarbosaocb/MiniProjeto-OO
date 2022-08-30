package view;

import controle.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaMenuEntrada extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static JFrame menu_frame = new JFrame("Acesso");
	private static JPanel login_panel = new JPanel();
	private static JPanel btn_panel = new JPanel();
	private static JLabel titulo = new JLabel("Olá, faça seu login ou crie uma conta");
	private static JLabel login = new JLabel("Usuário");
	private static JLabel senha = new JLabel("Senha");
	private static JButton login_btn = new JButton("Login");
	private static JButton cadastro_btn = new JButton("Cadastro");
	public static ControleDados dados = new ControleDados();

	public TelaMenuEntrada() {
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setSize(150, 30);
		login_btn.setSize(100, 30);
		cadastro_btn.setSize(100, 30);

		//menu_frame.setLayout(null/*new FlowLayout(FlowLayout.CENTER, 50, 50)*/);
		btn_panel.setLayout(new CardLayout(40, 30));
		
		btn_panel.setLayout(new FlowLayout());
		btn_panel.add(login_btn);
		btn_panel.add(cadastro_btn);
		
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
			new TelaMain();
			menu_frame.setVisible(false);
			ControleUsuarios controleUser = new ControleUsuarios(dados);
			System.out.println(controleUser.verificarUsuario("kdu@gmail.com", "minhasenhaforte"));
		}

		if (src == cadastro_btn) {
			new TelaCadastro();
			menu_frame.setVisible(false);
			/*JOptionPane.showMessageDialog(null,
					"Ainda precisam ser implementadas as funcionalidades\n" + "relacionadas a curso e a matrícula",
					null, JOptionPane.INFORMATION_MESSAGE);*/
			// menu_frame.setVisible(false);
		}
	}
}
