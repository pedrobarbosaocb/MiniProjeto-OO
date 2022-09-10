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
import javax.swing.border.EmptyBorder;

import controle.ControleDados;
import controle.ControleUsuarios;
import modelo.Usuario;

public class TelaMain extends JDialog implements ActionListener {

	/**
	 * Classe que gera a vizualização da tela principal
	 * 
	 * @author Carlos Eduardo & Pedro Barbosa
	 * @version 1.0
	 * 
	 * @see TelaMain
	 **/

	private static final long serialVersionUID = 1L;

	private static JLabel user = new JLabel();

	private static JPanel user_panel = new JPanel();
	private static JPanel btn_panel = new JPanel();
	private static JPanel user_btn_panel = new JPanel();
	private static JPanel center_panel = new JPanel();

	private static JButton despesas_btn = new JButton("Despesas");
	private static JButton hist_btn = new JButton("Histórico");
	private static JButton logout_btn = new JButton("Logout");
	private static JButton perfil_btn = new JButton("Editar Perfil");
	private static JButton add_amigo_btn = new JButton("Adicionar Amigo");

	private static ControleDados _dados;

	private static PanelHistorico historico;
	private static PanelDespesas despesas;

	public TelaMain(ControleDados dados) {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		setTitle("Divisor de Despesas");

		_dados = dados;

		center_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		historico = new PanelHistorico(_dados);
		despesas = new PanelDespesas(_dados);

		setModal(true);

		user.setText("Usuário logado: " + ControleDados.getUsuarioSessao().getNome());
		user_panel.setBorder(new EmptyBorder(10, 10, 0, 10));
		user_panel.setLayout(new BorderLayout());
		user_btn_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		user_panel.add(user, BorderLayout.WEST);
		user_btn_panel.add(perfil_btn);
		user_btn_panel.add(logout_btn);
		user_panel.add(user_btn_panel, BorderLayout.CENTER);

		btn_panel.setBorder(new EmptyBorder(10, 10, 10, 0));
		btn_panel.add(despesas_btn);
		btn_panel.add(hist_btn);
		btn_panel.add(add_amigo_btn);

		despesas_btn.setEnabled(false);
		hist_btn.setEnabled(true);

		btn_panel.setLayout(new GridLayout(3, 0));

		center_panel.setLayout(new GridLayout());
		center_panel.add(despesas);

		add_amigo_btn.addActionListener(this);
		logout_btn.addActionListener(this);
		despesas_btn.addActionListener(this);
		hist_btn.addActionListener(this);
		perfil_btn.addActionListener(this);

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
			center_panel.remove(historico);
			center_panel.add(despesas);

			despesas_btn.setEnabled(false);
			hist_btn.setEnabled(true);

			revalidate();
			repaint();
		}

		if (src == hist_btn) {
			System.out.println("showing historico");
			center_panel.remove(despesas);
			center_panel.add(historico);
			hist_btn.setEnabled(false);
			despesas_btn.setEnabled(true);

			revalidate();
			repaint();
		}

		if (src == logout_btn) {
			despesas.removeActionListeners();

			add_amigo_btn.removeActionListener(this);
			perfil_btn.removeActionListener(this);
			logout_btn.removeActionListener(this);
			despesas_btn.removeActionListener(this);
			hist_btn.removeActionListener(this);

			center_panel.removeAll();

			setVisible(false);
			dispose();
		}

		if (src == perfil_btn) {
			new EditPerfil(_dados);
		}

		if (src == add_amigo_btn) {
			new AddAmigo(_dados);
		}
	}
}
