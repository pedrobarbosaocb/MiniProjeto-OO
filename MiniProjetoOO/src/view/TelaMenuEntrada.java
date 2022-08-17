package view;

import controle.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaMenuEntrada implements ActionListener  {

	private static JFrame menu_frame = new JFrame("Acesso");
	private static JLabel titulo = new JLabel("Olá, faça seu login ou crie uma conta");
	private static JButton login_btn = new JButton("Login");
	private static JButton cadastro_btn = new JButton("Cadastro");
	public static ControleDados dados = new ControleDados();
	
	public TelaMenuEntrada() {
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setSize(150, 30);
		login_btn.setSize(100, 30);
		cadastro_btn.setSize(100, 30);
		
		menu_frame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
		
		menu_frame.add(titulo);
		menu_frame.add(login_btn);
		menu_frame.add(cadastro_btn);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		menu_frame.setBounds((int) (width/2)-200, (int) (height/2)-125, 400, 250);
		menu_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu_frame.setVisible(true);
	}

	public static void main(String[] args) {
		TelaMenuEntrada menu = new TelaMenuEntrada();
		
		login_btn.addActionListener(menu);
		cadastro_btn.addActionListener(menu);
	}

	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src == login_btn) {
			//new TelaPessoa().mostrarDados(dados, 1);
			System.out.println("login");
			//menu_frame.setVisible(false);
		}
			
		
		if(src == cadastro_btn) {
			JOptionPane.showMessageDialog(null, 
					"Ainda precisam ser implementadas as funcionalidades\n"
					+ "relacionadas a curso e a matrícula", null, 
					JOptionPane.INFORMATION_MESSAGE);
			//menu_frame.setVisible(false);
		}
	}
}
