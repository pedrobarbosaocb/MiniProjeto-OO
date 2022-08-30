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
	
	public TelaMain() {
		
		add(new PanelDespesas());
		add(new PanelHistorico());
		
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
