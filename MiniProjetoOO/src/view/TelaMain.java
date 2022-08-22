package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controle.ControleDados;

public class TelaMain extends JFrame implements ActionListener {

	private static JFrame menu_frame = new JFrame("Logado");
	private static JButton grupo = new JButton("Grupos");
	private static JButton despesa = new JButton("Despesas");
	private static JButton historico = new JButton("Histórico");
	
	public TelaMain() {
		
		grupo.setBounds(140, 100, 100, 30);
		despesa.setBounds(140, 150, 100, 30);
		historico.setBounds(140, 50, 100, 30);
		
		menu_frame.setLayout(null);
		
		menu_frame.add(grupo);
		menu_frame.add(despesa);
		menu_frame.add(historico);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		menu_frame.setBounds((int) (width / 2) - 200, (int) (height / 2) - 125, 400, 250);
		menu_frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
