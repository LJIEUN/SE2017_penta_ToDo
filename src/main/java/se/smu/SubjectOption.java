package se.smu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


import java.awt.Window.*;

public class SubjectOption extends JPanel{
	public static JPanel option_panel = new JPanel();
    public SubjectOption(String[] subject) {
        final JFrame frame = new JFrame("Popup Menu Demo");
        frame.setLocationRelativeTo(MainFrame.main_panel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 319, 190);
        frame.setSize(420, 481);
        frame.setVisible(true);
    	frame.getContentPane().add(option_panel, BorderLayout.CENTER);

    	
        // build poup menu
         JPopupMenu popup = new JPopupMenu();		//원래 앞에 final
        // New project menu item
        JMenuItem menuItem = new JMenuItem("수정");
        menuItem.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
            	SubjectModify subject_modify;
            	try{
            		subject_modify = new SubjectModify(subject);
            		SubjectOption.option_panel.removeAll();
            		SubjectOption.option_panel.add(subject_modify);
            		subject_modify.repaint();
            		//frame.setVisible(false);
            	}
            	catch(IOException ex){
            		ex.printStackTrace();
            	}
            }
            
        });
        popup.add(menuItem);
        // New File menu item
        menuItem = new JMenuItem("삭제");
        menuItem.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frame, "삭제 선택!");
            	//여기서부터 내가고친부

            	try{
            		SubjectDeletePopup dialog = new SubjectDeletePopup(0, subject);
            		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            		dialog.setVisible(true);
					SubjectDelete subject_del = new SubjectDelete(subject);
            	}catch(IOException e1){
            		e1.printStackTrace();
            	}
            	//dispose();
            	//여기까지
            }
        });
        
        popup.add(menuItem);
        menuItem = new JMenuItem("To do list");
        menuItem.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
            	TodolistList todolist_list;
            	try{
            		todolist_list = new TodolistList(subject);
            		SubjectOption.option_panel.removeAll();
            		SubjectOption.option_panel.add(todolist_list);
            		todolist_list.repaint();
            	}
            	catch(IOException ex){
            		ex.printStackTrace();
            	}
            }
        });
        popup.add(menuItem);
        
        
        
        
 
        // add mouse listener
        frame.addMouseListener(new MouseAdapter() {
 
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }
 
            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
                
            }
 
            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popup.show(e.getComponent(),
                            e.getX(), e.getY());
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(420, 480);
        frame.setVisible(true);
    }
}
