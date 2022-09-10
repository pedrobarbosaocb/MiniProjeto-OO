package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controle.ControleDados;
import controle.ControleUsuarios;
import modelo.Amigo;
import modelo.Pessoa;

/**
 * Classe que gera a vizualização da tela de registro de uma nova despesa
 * 
 * @author Carlos Eduardo and Pedro Barbosa
 * @version 1.0
 * 
 * @see TelaMenuEntrada
 **/

public class AddDespesa extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static JPanel btn_panel = new JPanel();
	private static JPanel north_panel = new JPanel();
	private static JPanel txt_panel = new JPanel();
	private static JPanel main_north_panel = new JPanel();
	private static JPanel center_panel = new JPanel();
	private static JPanel list_panel = new JPanel();
	private static JPanel east_panel = new JPanel();
	private static JPanel faltam_panel = new JPanel();

	private static JButton cancel_btn = new JButton("Cancelar");
	private static JButton gerar_btn = new JButton("Gerar Despesa");
	private static JButton add_btn = new JButton("Adicionar");
	private static JButton div_btn = new JButton("Dividir Igualmente");
	private static JButton edit_btn = new JButton("Editar valor");
	private static JButton remove_btn = new JButton("Remover amigo");
	private static JButton gerar_igual_btn = new JButton("Dividir Igualmente");

	private static JTextField txt_add_valor = new JTextField();
	private static int screen_height = 550;
	private static int screen_width = 650;

	private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
	private ArrayList<Pessoa> amigos_all = new ArrayList<Pessoa>();
	private ArrayList<Double> valores = new ArrayList<Double>();
	private static ArrayList<String> amigos;
	private static ArrayList<Amigo> amigos_id;

	private ControleDados _dados;
	private ControleUsuarios _usuario;

	private static JComboBox<String> amigos_nome = new JComboBox<String>();

	private static JList<String> valores_list = new JList<String>();
	private static JList<String> amigos_despesa = new JList<String>();
	private static DefaultListModel<String> listModel1 = new DefaultListModel<String>();
	private static DefaultListModel<String> listModel2 = new DefaultListModel<String>();

	private static JLabel titulo = new JLabel("Título da Despesa");
	private static JLabel valor_total = new JLabel("Valor Total");
	private static JLabel dt_vencimento = new JLabel("Data de Vencimento");
	private static JLabel lbl_faltam = new JLabel("Faltam");
	private static JLabel faltam = new JLabel("");
	private static JLabel lbl_valor_igual = new JLabel("Valor para cada");
	private static JLabel valor_igual = new JLabel("");

	private static JTextField txt_titulo = new JTextField();
	private static JFormattedTextField txt_valor_total = new JFormattedTextField();
	private static JFormattedTextField txt_dt_vencimento;

	private static double soma_total = 0;

	/**
	 * Construtor AddDespesa
	 * 
	 * Gera a visualização do JDialog de adição de uma nova despesa aos dados do
	 * usuario da sessao
	 * 
	 * @param dados ControleDados
	 **/
	public AddDespesa(ControleDados dados) {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		setModal(true);

		faltam.setText("0.0");

		MaskFormatter formatoData;
		try {
			formatoData = new MaskFormatter("##/##/####");
			formatoData.setPlaceholderCharacter('_');
			txt_dt_vencimento = new JFormattedTextField(formatoData);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		setTitle("Adicionando Despesa");

		_dados = dados;
		ControleUsuarios usuario = new ControleUsuarios(dados);
		_usuario = usuario;

		setLayout(new BorderLayout());

		amigos = _usuario.getNomesAmigosUsuario(ControleDados.getUsuarioSessao().getId());
		amigos_id = _usuario.getAmigosUsuario(ControleDados.getUsuarioSessao().getId());

		amigos_nome.addItem(ControleDados.getUsuarioSessao().getNome());
		amigos_all.add(ControleDados.getUsuarioSessao());

		for (int i = 0; i < amigos.size(); i++) {
			amigos_nome.addItem(amigos.get(i));
			amigos_all.add(amigos_id.get(i));
		}

		txt_add_valor.setText("00.0");
		txt_add_valor.setPreferredSize(new Dimension(50, 27));
		txt_add_valor.setBorder(new EmptyBorder(0, 10, 0, 10));
		amigos_nome.setPreferredSize(new Dimension(200, 27));

		north_panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));
		north_panel.add(amigos_nome);
		north_panel.add(txt_add_valor);
		north_panel.add(add_btn);
		north_panel.add(remove_btn);

		btn_panel.setLayout(new FlowLayout());
		btn_panel.add(cancel_btn);
		btn_panel.add(gerar_igual_btn);
		btn_panel.add(gerar_btn);

		center_panel.setBorder(new EmptyBorder(0, 10, 10, 10));
		center_panel.setLayout(new BorderLayout());
		center_panel.add(list_panel, BorderLayout.CENTER);

		list_panel.setLayout(new GridLayout(1, 2));
		list_panel.add(amigos_despesa);
		list_panel.add(valores_list);

		east_panel.setLayout(new GridLayout(4, 0));
		east_panel.setBorder(new EmptyBorder(0, 0, 10, 10));
		east_panel.add(faltam_panel);

		faltam_panel.setLayout(new BoxLayout(faltam_panel, BoxLayout.Y_AXIS));
		faltam_panel.add(lbl_faltam);
		faltam_panel.add(faltam);
		faltam_panel.add(lbl_valor_igual);
		faltam_panel.add(valor_igual);

		txt_panel.setBorder(new EmptyBorder(10, 10, 0, 10));
		txt_panel.setLayout(new GridLayout(3, 4, 10, 5));
		txt_panel.add(titulo);
		txt_panel.add(txt_titulo);
		txt_panel.add(valor_total);
		txt_panel.add(txt_valor_total);
		txt_panel.add(dt_vencimento);
		txt_panel.add(txt_dt_vencimento);

		center_panel.add(north_panel, BorderLayout.NORTH);

		main_north_panel.setLayout(new GridLayout(1, 0));
		main_north_panel.add(txt_panel);

		add(main_north_panel, BorderLayout.NORTH);
		add(center_panel, BorderLayout.CENTER);
		add(btn_panel, BorderLayout.SOUTH);
		add(east_panel, BorderLayout.EAST);

		edit_btn.addActionListener(this);
		remove_btn.addActionListener(this);
		div_btn.addActionListener(this);
		add_btn.addActionListener(this);
		cancel_btn.addActionListener(this);
		gerar_btn.addActionListener(this);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		setBounds((int) (width / 2) - screen_width / 2, (int) (height / 2) - screen_height / 2, screen_width,
				screen_height);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == edit_btn) {

		}
		if (src == remove_btn) {
			if (!amigos_despesa.isSelectionEmpty()) {
				for (int i = 0; i < pessoas.size(); i++) {
					if (pessoas.get(i).getNome().equals(amigos_despesa.getSelectedValue())) {
						pessoas.remove(i);
						i--;
					}
				}
				atualizarLista();
			}
		}
		if (src == div_btn) {
			dividirIgualmente();
		}

		if (src == cancel_btn) {
			PanelDespesas.refresh_btn.doClick();

			txt_titulo.setText("");
			txt_dt_vencimento.setText("");
			txt_valor_total.setText("");
			txt_add_valor.setText("");

			listModel1.removeAllElements();
			listModel2.removeAllElements();

			amigos_despesa.setModel(listModel1);
			valores_list.setModel(listModel2);

			txt_panel.removeAll();

			edit_btn.removeActionListener(this);
			remove_btn.removeActionListener(this);
			add_btn.removeActionListener(this);
			div_btn.removeActionListener(this);
			cancel_btn.removeActionListener(this);
			gerar_btn.removeActionListener(this);

			amigos_nome.removeAllItems();

			removeAll();
			setVisible(false);
			dispose();
		}

		if (src == gerar_igual_btn) {
			try {
				valores.clear();
				for (int i = 0; i < pessoas.size(); i++) {
					valores.add(Double.valueOf(valor_igual.getText()));
				}
				if (_dados.criarDespesa(txt_titulo.getText(), Double.parseDouble(txt_valor_total.getText()),
						txt_dt_vencimento.getText(), pessoas, valores)) {
					JOptionPane.showMessageDialog(null, "Despesa gerada com sucesso!", null,
							JOptionPane.INFORMATION_MESSAGE);
					cancel_btn.doClick();
				} else {
					JOptionPane.showMessageDialog(null, "Falha ao gerar despesa!", null,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception err) {
				JOptionPane.showMessageDialog(null, "Falha ao gerar despesa!", null, JOptionPane.INFORMATION_MESSAGE);
			}
		}

		if (src == gerar_btn) {

			try {
				if (_dados.criarDespesa(txt_titulo.getText(), Double.parseDouble(txt_valor_total.getText()),
						txt_dt_vencimento.getText(), pessoas, valores)) {
					JOptionPane.showMessageDialog(null, "Despesa gerada com sucesso!", null,
							JOptionPane.INFORMATION_MESSAGE);
					cancel_btn.doClick();
				} else {
					JOptionPane.showMessageDialog(null, "Falha ao gerar despesa!", null,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception err) {
				JOptionPane.showMessageDialog(null, "Falha ao gerar despesa!", null, JOptionPane.INFORMATION_MESSAGE);
			}

		}

		if (src == add_btn) {
			if (verificaIsNumero(txt_add_valor.getText()) && verificaIsNumero(txt_valor_total.getText())) {

				double valor_total = Double.valueOf(txt_valor_total.getText());
				double valor_add = Double.valueOf(txt_add_valor.getText());

				if (valor_total >= valor_add && (soma_total + valor_add) <= valor_total) {

					if (verificaAmigoLista(amigos_all.get(amigos_nome.getSelectedIndex()))) {

						soma_total = soma_total + Double.valueOf(txt_add_valor.getText());
						faltam.setText("" + (Double.valueOf(txt_valor_total.getText()) - soma_total));

						pessoas.add(amigos_all.get(amigos_nome.getSelectedIndex()));
						valores.add(Double.valueOf(txt_add_valor.getText()));

						atualizarLista();

						div_btn.doClick();
					}

				} else {

					JOptionPane.showMessageDialog(null, "O valor inserido excede o valor total da despesa!", null,
							JOptionPane.INFORMATION_MESSAGE);

				}
			}
		}
	}

	/**
	 * Método que divide o valor total da dispesa de forma igual entre as pessoas
	 * adicionadas
	 * 
	 */
	public void dividirIgualmente() {
		if (verificaIsNumero(txt_add_valor.getText()) && verificaIsNumero(txt_valor_total.getText())) {
			double valor_total = Double.valueOf(txt_valor_total.getText());
			valor_total = valor_total / (pessoas.size());

			valor_igual.setText("" + valor_total);

			revalidate();
			repaint();
		}
	}

	/**
	 * Método que altera cor dos campos selecionados para vermelho
	 * 
	 * @param valor String
	 * 
	 * @return true se for um numero false se a string não for um numero
	 */
	public boolean verificaIsNumero(String valor) {
		if (valor.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha os campos vazios!", null, JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else {
			try {
				Double.valueOf(valor);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Insira um número válido!", null, JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
		return true;
	}

	/**
	 * Método que verifica se a pessoa adicionada já está presente na lista
	 * 
	 * @param amigo Pessoa
	 * 
	 * @return boolean
	 */
	public boolean verificaAmigoLista(Pessoa amigo) {
		if (pessoas.contains(amigo)) {
			JOptionPane.showMessageDialog(null, "Pessoa já inserida!", null, JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Método que atualiza a Jlist que dá vizualiação aos amigos adicionados a
	 * despesa
	 * 
	 */
	public void atualizarLista() {
		listModel1.removeAllElements();
		listModel2.removeAllElements();

		for (int i = 0; i < pessoas.size(); i++) {
			listModel1.addElement(pessoas.get(i).getNome());
			listModel2.addElement("" + valores.get(i));
		}

		amigos_despesa.setModel(listModel1);
		valores_list.setModel(listModel2);

		revalidate();
		repaint();
	}
}
