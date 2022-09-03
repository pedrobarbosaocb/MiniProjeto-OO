package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controle.ControleDados;

public class TelaMain extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private static JPanel btn_panel = new JPanel();
	private static JPanel center_panel = new JPanel();
	private static JButton despesas_btn = new JButton("Despesas");
	private static JButton hist_btn = new JButton("Histórico");

	private static PanelHistorico historico = new PanelHistorico();
	private static PanelDespesas despesas = new PanelDespesas();
	
	public TelaMain() {
		setModal(true);
		
		btn_panel.add(despesas_btn);
		despesas_btn.addActionListener(this);
		btn_panel.add(hist_btn);
		hist_btn.addActionListener(this);
		
		despesas_btn.setEnabled(false);
		hist_btn.setEnabled(true);
		
		btn_panel.setLayout(new GridLayout(2,0));
		add(btn_panel, BorderLayout.WEST);
		
		despesas.setVisible(true);
		historico.setVisible(false);
		
		center_panel.add(historico, BorderLayout.CENTER);
		center_panel.add(despesas, BorderLayout.CENTER);
		add(center_panel, BorderLayout.CENTER);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		setBounds((int) (width / 2) - 604, (int) (height / 2) - 340, 1208, 680);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == despesas_btn) {
			System.out.println("showing despesa");
			historico.setVisible(false);
			despesas.setVisible(true);
			
			despesas_btn.setEnabled(false);
			hist_btn.setEnabled(true);
			
			revalidate();
			repaint();
		}
		
		if (src == hist_btn) {
			System.out.println("showing historico");
			historico.setVisible(true);
			despesas.setVisible(false);
			
			hist_btn.setEnabled(false);
			despesas_btn.setEnabled(true);
			
			revalidate();
			repaint();
		}
			
		
	}

}
