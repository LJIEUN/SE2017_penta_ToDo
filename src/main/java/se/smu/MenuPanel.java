package se.smu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument.Content;
import se.smu.MainFrame;

public class MenuPanel extends JPanel{
	/**
	 * Create the panel.
	 * @return 
	 */

	
	public MenuPanel() {

		JPanel menu_panel = new JPanel();
		menu_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		menu_panel.setBackground(Color.pink);				/**/
		menu_panel.setBounds(0, 0, 600, 200);
		MainFrame.contentPane.add(menu_panel);
		menu_panel.setLayout(null);
		
		
		JButton add_button = new JButton("");
		JButton home_button = new JButton("");
		JButton list_button = new JButton("");
		
		add_button.setBorderPainted(false);
		home_button.setBorderPainted(false);
		list_button.setBorderPainted(false);

		add_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				SubjectAdd addSub = new SubjectAdd();
				
			}
		});
		
		add_button.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/add.PNG")));					
		add_button.setBackground(Color.pink);															
		add_button.setBounds(0, 0, 200, 80);
		menu_panel.add(add_button);
		
		
		
		home_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				HomeMain homemain = new HomeMain();
				
				add_button.setBounds(0, 0, 200, 80);
				home_button.setBounds(200, 0, 200, 80);
				list_button.setBounds(400, 0, 200, 80);
				add_button.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/add.PNG")));				/**/
				home_button.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/home.PNG")));
				list_button.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/list.PNG")));				/**/
				
				MainFrame.contentPane.remove(menu_panel);
				MainFrame.main_panel.removeAll();
				MainFrame.contentPane.add(menu_panel);
				MainFrame.main_panel.add(homemain);
				
				
				homemain.revalidate();
				homemain.repaint();

			}
		});
		
		home_button.setBackground(Color.pink);
		home_button.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/home.PNG")));
		home_button.setBounds(200, 0, 200, 80);
		menu_panel.add(home_button);
		


		list_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AllTodoListMain Test1 = new AllTodoListMain();
				
				add_button.setBounds(0, 0, 200, 80);
				home_button.setBounds(200, 0, 200, 80);
				list_button.setBounds(400, 0, 200, 80);
				add_button.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/add.PNG")));					/**/
				home_button.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/home.PNG")));
				list_button.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/list.PNG")));				/**/
				
				MainFrame.contentPane.remove(menu_panel);
				MainFrame.main_panel.removeAll();
				MainFrame.contentPane.add(menu_panel);
				MainFrame.main_panel.add(Test1);
				
				Test1.revalidate();
				Test1.repaint();
				
			}
		});
		/*
		add_button.setIcon(new ImageIcon(MainFrame.class.getResource("/img/plus.PNG")));					
		add_button.setBackground(Color.lightGray);															
		add_button.setBounds(160, 0, 160, 100);
		menu_panel.add(add_button);
		*/

		list_button.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/list.PNG")));					/**/
		list_button.setBackground(Color.pink);
		list_button.setBounds(390, 0, 200, 80);
		menu_panel.add(list_button);
		
		menu_panel.revalidate();
		menu_panel.repaint();
		
	}
	
}


