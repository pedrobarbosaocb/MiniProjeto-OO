package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelDevedor extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton remove = new JButton("-");
	
	public PanelDevedor(String[] amigos) {
		setLayout(new FlowLayout());
		add(new JLabel("Devedor"));
		add(new JComboBox<String>(amigos));
		add(remove);
		remove.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if (src == remove) {
			setVisible(false);
			
		}
		
	}

}
