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

public class DDayPopup extends JDialog {
	private final JPanel contentPanel = new JPanel();
	
	public DDayPopup(String[] todo){
		setAlwaysOnTop(true);
		setBounds(100,100,319,240);
		setModal(true);
		setLocationRelativeTo(MainFrame.main_panel);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel message = new JLabel("");
		message.setBackground(Color.WHITE);
		message.setBounds(40,30,190,80);
		message.setIcon(new ImageIcon(DDayPopup.class.getResource("/icon/alarm.PNG")));
		contentPanel.add(message);
		
		JLabel todo_name = new JLabel(todo[1]);
		todo_name.setBounds(55,0,152,47);
		todo_name.setFont(new Font("고딕", Font.PLAIN, 20));
		contentPanel.add(todo_name);
		
		JButton confirm = new JButton("");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		confirm.setBounds(80,120,150,60);
		contentPanel.add(confirm);
		confirm.setActionCommand("확인");
		getRootPane().setDefaultButton(confirm);
		confirm.setIcon(new ImageIcon(DDayPopup.class.getResource("/icon/ok.PNG")));
		confirm.setBackground(Color.WHITE);
		confirm.setBorder(null);
		
		
	}
}
