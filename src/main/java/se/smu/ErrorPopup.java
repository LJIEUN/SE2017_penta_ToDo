package se.smu;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErrorPopup extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public ErrorPopup() {
		setAlwaysOnTop(true);
		contentPanel.setBackground(Color.WHITE);
		setBounds(100, 100, 319, 190);
		setModal(true);
		setLocationRelativeTo(MainFrame.main_panel);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	/*		{
			JLabel error_label = new JLabel("");
			error_label.setFont(new Font("바탕체", Font.PLAIN, 30));
			error_label.setBounds(146, 20, 92, 28);
			contentPanel.add(error_label);
		}
	*/	
		{
			JLabel caution_image = new JLabel("");
			caution_image.setBackground(Color.WHITE);
			caution_image.setIcon(new ImageIcon(ErrorPopup.class.getResource("/icon/error.PNG")));
			caution_image.setBounds(45, 20, 200, 50);
			contentPanel.add(caution_image);
		}
		
		JButton confirm = new JButton("");
		confirm.setIcon(new ImageIcon(DDayPopup.class.getResource("/icon/ok.PNG")));
		
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		confirm.setBorder(null);
		confirm.setBounds(75, 81, 150, 60);
		contentPanel.add(confirm);
	}
}