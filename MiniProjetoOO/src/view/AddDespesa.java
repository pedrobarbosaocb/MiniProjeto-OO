package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class AddDespesa extends JDialog implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JPanel btn_panel = new JPanel();
	private static JPanel amigos_panel = new JPanel();
	private static JPanel main_panel = new JPanel();
	private static JButton cancel_btn = new JButton("Cancelar");
	private static JButton save_btn = new JButton("Salvar");
	private static JButton add_amigo = new JButton("+");
	
	private static JButton divisao_por_igual = new JButton("Igual");
	private static JLabel valor = new JLabel("Valor");
	private static String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
	private static int screen_height = 250;
	private static int test = 0;
	private static PanelDevedor devedor = new PanelDevedor(petStrings);

	public AddDespesa() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		setModal(true);
		
		screen_height = 250 + 10*test;
		main_panel.setLayout(new BoxLayout(main_panel, 1));
		main_panel.add(divisao_por_igual);
		main_panel.add(valor);
		amigos_panel.add(devedor);
		amigos_panel.setLayout(new FlowLayout());
		main_panel.add(amigos_panel);
		add(main_panel, BorderLayout.CENTER);
		
		add(btn_panel, BorderLayout.SOUTH);
		btn_panel.setLayout(new FlowLayout());
		btn_panel.add(cancel_btn);
		btn_panel.add(save_btn);
		btn_panel.add(add_amigo);
		
		add_amigo.addActionListener(this);
		cancel_btn.addActionListener(this);
		save_btn.addActionListener(this);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		//pack();
		
		setBounds((int) (width / 2) - 200, (int) (height / 2) - screen_height/2, 400, screen_height);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == add_amigo) {
			test+=1;
			amigos_panel.add(new PanelDevedor(petStrings));
			screen_height = 250 + 20*test;
			System.out.println("teste");
			
			revalidate();
			repaint();
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width = screenSize.getWidth();
			double height = screenSize.getHeight();
			setBounds((int) (width / 2) - 200, (int) (height / 2) - screen_height/2, 400, screen_height);
		}
		
		if (src == cancel_btn) {
			add_amigo.removeActionListener(this);
			cancel_btn.removeActionListener(this);
			save_btn.removeActionListener(this);
			
			System.out.println("test2");
			removeAll();
			setVisible(false);
			dispose();
		}
		
	}

}
