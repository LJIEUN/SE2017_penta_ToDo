package se.smu;


import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SubjectDeletePopup extends JDialog {

	private final JPanel contentPanel = new JPanel();
	/**
	 * Create the dialog.
	 */
	public SubjectDeletePopup(int choice, String[] list) {
		setAlwaysOnTop(true);
		setBounds(100, 100, 319, 190);
		setModal(true);
		setLocationRelativeTo(MainFrame.main_panel);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel message = new JLabel("삭제완료");
		message.setBackground(Color.WHITE);
		message.setFont(new Font("바탕체", Font.PLAIN, 20));
		message.setBounds(100, 24, 178, 47);
		contentPanel.add(message);
		
//		JButton cancel = new JButton("");
//		cancel.setIcon(new ImageIcon(Del_Popup.class.getResource("/img/cancel.PNG")));
////		cancel.setBackground(Color.WHITE);
	//	cancel.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				dispose();
				
//			}
//		});
//		cancel.setBorder(null);
//		cancel.setBounds(5, 94, 147, 47);
//		contentPanel.add(cancel);
		
		JButton confirm = new JButton("");
		confirm.setIcon(new ImageIcon(SubjectDeletePopup.class.getResource("/icon/ok.PNG")));
		confirm.setBackground(Color.WHITE);
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch(choice){
				case 0:
					try {
						SubjectDelete del = new SubjectDelete(list);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 1:
					try {
						TodoListDelete del = new TodoListDelete(list);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
				dispose();
			}
		});
		confirm.setBorder(null);
		confirm.setBounds(74, 94, 152, 47);
		contentPanel.add(confirm);
		
	}
}
