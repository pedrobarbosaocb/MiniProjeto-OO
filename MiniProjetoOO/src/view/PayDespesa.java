package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controle.ControleDados;
import modelo.Despesa;
import modelo.Pagamento.FormaPagamento;

/**
 * Classe que gera a vizualização da tela de pagamento de uma despesa
 * 
 * @author Carlos Eduardo and Pedro Barbosa
 * @version 1.0
 * 
 * @see TelaMenuEntrada
 **/

public class PayDespesa extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static ControleDados _dados;
	private static JPanel btn_panel = new JPanel();
	private static JButton save_btn = new JButton("Pagar");
	private static JButton cancel_btn = new JButton("Cancelar");

	private static JPanel center_panel = new JPanel();

	private static JLabel devedor = new JLabel("Devedor");
	private static JLabel credor = new JLabel("Credor");
	private static JLabel pagar = new JLabel("Valor a pagar");
	private static JLabel lbl_forma_pagto = new JLabel("Forma de Pagamento");
	private static JLabel data = new JLabel("Data do Pagamento");
	private static JLabel lbl_valor_restante = new JLabel("Valor Restante");
	private static JLabel valor_restante = new JLabel("");

	private static JLabel txt_devedor = new JLabel("");
	private static JFormattedTextField txt_data;
	private static JLabel txt_credor = new JLabel("");
	private static JTextField txt_pagar = new JTextField(20);

	private static JComboBox<String> forma_pagto_cbb = new JComboBox<String>();
	private static Despesa despesa;
	private static FormaPagamento forma_pagto = FormaPagamento.PIX;

	/**
	 * Construtor PayDespesa
	 * 
	 * Gera a visualização do JDialog de pagamento das despesas, o qual gera o
	 * pagamento da despesa inserida como parametro
	 * 
	 * @param dados   ControleDados
	 * @param despesa Despesa
	 **/
	public PayDespesa(ControleDados dados, Despesa despesa) {
		_dados = dados;

		PayDespesa.despesa = despesa;

		setModal(true);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		MaskFormatter formatoData;
		try {
			formatoData = new MaskFormatter("##/##/####");
			formatoData.setPlaceholderCharacter('_');
			txt_data = new JFormattedTextField(formatoData);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		txt_devedor.setText(despesa.getDevedor().getNome());
		txt_credor.setText(despesa.getCredor().getNome());

		forma_pagto_cbb.addItem("Pix");
		forma_pagto_cbb.addItem("Dinheiro");
		forma_pagto_cbb.addItem("Cartão");
		valor_restante.setText("" + (despesa.getValor() - despesa.getTotalPago()));
		txt_pagar.setText("" + (despesa.getValor() - despesa.getTotalPago()));

		center_panel.setLayout(new GridLayout(6, 2, 10, 5));
		center_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		center_panel.add(devedor);
		center_panel.add(txt_devedor);
		center_panel.add(credor);
		center_panel.add(txt_credor);
		center_panel.add(lbl_valor_restante);
		center_panel.add(valor_restante);
		center_panel.add(data);
		center_panel.add(txt_data);
		center_panel.add(pagar);
		center_panel.add(txt_pagar);
		center_panel.add(lbl_forma_pagto);
		center_panel.add(forma_pagto_cbb);

		add(center_panel, BorderLayout.CENTER);

		add(btn_panel, BorderLayout.SOUTH);
		btn_panel.setLayout(new FlowLayout());
		btn_panel.add(cancel_btn);
		btn_panel.add(save_btn);

		cancel_btn.addActionListener(this);
		save_btn.addActionListener(this);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		setBounds((int) (width / 2) - 200, (int) (height / 2) - 125, 400, 250);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == save_btn) {

			switch (forma_pagto_cbb.getSelectedIndex()) {
			case 0:
				forma_pagto = FormaPagamento.PIX;
				break;
			case 1:
				forma_pagto = FormaPagamento.DINHEIRO;
				break;
			case 2:
				forma_pagto = FormaPagamento.CARTAO;
				break;
			}

			if (_dados.realizarPagamento(despesa.getId(), Double.valueOf(txt_pagar.getText()), forma_pagto.toString(),
					txt_data.getText())) {
				JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso!", null,
						JOptionPane.INFORMATION_MESSAGE);
				cancel_btn.doClick();
			} else {
				JOptionPane.showMessageDialog(null, "Falha ao realizar pagamento!", null,
						JOptionPane.INFORMATION_MESSAGE);
			}

		}

		if (src == cancel_btn) {
			PanelDespesas.refresh_btn.doClick();

			center_panel.removeAll();

			save_btn.removeActionListener(this);
			cancel_btn.removeActionListener(this);
			forma_pagto_cbb.removeAllItems();

			setVisible(false);
			dispose();
		}
	}
}
