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
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controle.ControleDados;
import modelo.Usuario;

public class TelaMain extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private static JLabel user = new JLabel();
	private static JPanel user_panel = new JPanel();
	private static JPanel btn_panel = new JPanel();
	private static JPanel center_panel = new JPanel();
	private static JButton despesas_btn = new JButton("Despesas");
	private static JButton hist_btn = new JButton("Histórico");
	private static JButton logout_btn = new JButton("Logout");
	private static JButton perfil_btn = new JButton("Editar Perfil");

	private static PanelHistorico historico = new PanelHistorico();
	private static PanelDespesas despesas = new PanelDespesas();
	
	public TelaMain(ControleDados dados) {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		setModal(true);
		
		user.setText("Usuário logado: " + dados.getUsuarioSessao().getNome());
		user_panel.setLayout(new BorderLayout());
		user_panel.add(user, BorderLayout.CENTER);
		user_panel.add(logout_btn, BorderLayout.EAST);
		user_panel.add(perfil_btn, BorderLayout.WEST);
		
		btn_panel.add(despesas_btn);
		btn_panel.add(hist_btn);
		
		despesas_btn.setEnabled(false);
		hist_btn.setEnabled(true);
		
		btn_panel.setLayout(new GridLayout(2,0));
		
		despesas.setVisible(true);
		historico.setVisible(false);
		
		center_panel.add(historico, BorderLayout.CENTER);
		center_panel.add(despesas, BorderLayout.CENTER);
		
		logout_btn.addActionListener(this);
		despesas_btn.addActionListener(this);
		hist_btn.addActionListener(this);
		
		add(user_panel, BorderLayout.NORTH);
		add(center_panel, BorderLayout.CENTER);
		add(btn_panel, BorderLayout.WEST);
		
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
		
		if (src == logout_btn) {
			
			logout_btn.removeActionListener(this);
			despesas_btn.removeActionListener(this);
			hist_btn.removeActionListener(this);
			
			System.out.println("teste");
			setVisible(false);
			dispose();
		}
			
		
	}

}
