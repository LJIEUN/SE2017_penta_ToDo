package se.smu;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainTitle extends JPanel {
	MainTitle(){
		JLabel main_label = new JLabel("5조-PENTA");
		main_label.setFont(new Font("고딕체", Font.PLAIN, 30));
		main_label.setHorizontalAlignment(SwingConstants.CENTER);
		main_label.setBounds(220, 0, 160, 50);
		main_label.setVisible(true);
	}
}
