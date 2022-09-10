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

/**
 * Classe que gera a vizualização da tela principal
 * 
 * @author Carlos Eduardo and Pedro Barbosa
 * @version 1.0
 * 
 * @see TelaMain
 **/

public class TelaMain extends JDialog implements ActionListener {

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
	private static JButton amigos_btn = new JButton("Amigos");

	private static ControleDados _dados;

	public static PanelHistorico historico;
	public static PanelDespesas despesas;
	public static PanelAmigos amigos;

	/**
	 * Construtor TelaMain
	 * 
	 * Gera a visualização da tela seguinte ao login do sistema, apresentando as
	 * principais telas e funcionalidades do sistema, com base nos dados do atual
	 * usuario da sessao.
	 * 
	 * @param dados ControleDados
	 **/
	public TelaMain(ControleDados dados) {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		setTitle("Divisor de Despesas");

		_dados = dados;

		center_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		historico = new PanelHistorico(_dados);
		despesas = new PanelDespesas(_dados);
		amigos = new PanelAmigos(_dados);

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
		btn_panel.add(amigos_btn);

		despesas_btn.setEnabled(false);
		hist_btn.setEnabled(true);

		btn_panel.setLayout(new GridLayout(3, 0));

		center_panel.setLayout(new GridLayout());
		center_panel.add(despesas);
		center_panel.add(historico);
		center_panel.add(amigos);

		amigos_btn.addActionListener(this);
		logout_btn.addActionListener(this);
		despesas_btn.addActionListener(this);
		hist_btn.addActionListener(this);
		perfil_btn.addActionListener(this);

		add(user_panel, BorderLayout.NORTH);
		add(center_panel, BorderLayout.CENTER);
		add(btn_panel, BorderLayout.WEST);

		mostrarPainel(0);

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
			mostrarPainel(0);
		}

		if (src == hist_btn) {
			mostrarPainel(1);

			historico.AdicionarDespesas("");

		}

		if (src == logout_btn) {
			historico.removeActionListeners();
			despesas.removeActionListeners();

			amigos_btn.removeActionListener(this);
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
			logout_btn.doClick();
			new TelaMain(_dados);
		}

		if (src == amigos_btn) {
			mostrarPainel(2);
			amigos.AdicionarAmigos("");
		}
	}

	/**
	 * Método no qual tira a visualização dos paineis do centro do JDialog e
	 * retornando a visualização do painel indicado pelo int painel
	 * 
	 * @param painel int
	 **/
	public void mostrarPainel(int painel) {
		center_panel.remove(despesas);
		center_panel.remove(historico);
		center_panel.remove(amigos);

		hist_btn.setEnabled(false);
		despesas_btn.setEnabled(false);
		amigos_btn.setEnabled(false);

		switch (painel) {
		case 0:
			center_panel.add(despesas);

			hist_btn.setEnabled(true);
			despesas_btn.setEnabled(false);
			amigos_btn.setEnabled(true);
			break;
		case 1:
			center_panel.add(historico);

			hist_btn.setEnabled(false);
			despesas_btn.setEnabled(true);
			amigos_btn.setEnabled(true);
			break;
		case 2:
			center_panel.add(amigos);

			hist_btn.setEnabled(true);
			despesas_btn.setEnabled(true);
			amigos_btn.setEnabled(false);
			break;
		}

		revalidate();
		repaint();

	}
}
