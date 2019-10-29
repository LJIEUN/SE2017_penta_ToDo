package se.smu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import se.smu.MainFrame;


public class TodolistList extends JPanel{
   private JTable todolist_table;
   
   TodolistList(String [] subject) throws IOException{
      setBackground(Color.WHITE);
      setBounds(0, 0, 420, 480);
      setLayout(null);

      EtchedBorder eborder;
      eborder = new EtchedBorder(EtchedBorder.LOWERED);
      
      JPanel panel = new JPanel();
      panel.setBackground(Color.WHITE);
      panel.setBounds(0, 0, 420, 480);
      add(panel);
      panel.setLayout(null);
      
      
      JLabel todo_label = new JLabel("To do list");
      todo_label.setFont(new Font("고딕체", Font.PLAIN, 30));
      todo_label.setHorizontalAlignment(SwingConstants.CENTER);
      todo_label.setBounds(120, 0, 159, 56);
      panel.add(todo_label);
      
      Object[][] column =   {
    		    {"1"," ",null,null},
                {"2"," ",null,null},
                {"3"," ",null,null},
                {"4"," ",null,null},
                {"5"," ",null,null},
                {"6"," ",null,null},
                {"7"," ",null,null},
                {"8"," ",null,null},
                {"9"," ",null,null},
                {"10"," ",null,null},
                {"11"," ",null,null},
                {"12"," ",null,null},
                {"13"," ",null,null},
                {"14"," ",null,null},
                {"15"," ",null,null},
                {"16"," ",null,null}
             };

   
      String[] row = {
    	"", "할 일", "마감 기한" , "중요도"
      };
      
   
   
      todolist_table= new JTable();      
      
      
      getTodoName(column, subject[0]);
      
      todolist_table.setModel(new DefaultTableModel(column,row) {
         Class[] columnTypes = new Class[] {
            String.class
         };
         /*
         public Class getColumnClass(int columnIndex) {
            return columnTypes[columnIndex];
         }
         */
         boolean[] columnEditables = new boolean[] {
            false , false , false , false
         };
         public boolean isCellEditable(int row, int column) {
            return columnEditables[column];
         }
      });
      todolist_table.setBounds(0, 50, 418, 233);
      panel.add(todolist_table);
      
      
      
      JScrollPane scrollPane = new JScrollPane(todolist_table);
      scrollPane.setBounds(0, 50, 418, 233);
      scrollPane.setBackground(Color.WHITE);
      panel.add(scrollPane);
      
      todolist_table.setRowHeight(42);
      todolist_table.getColumnModel().getColumn(0).setMaxWidth(40);
      todolist_table.getColumnModel().getColumn(3).setMaxWidth(50);
      
      
      
      
      //등록
      JButton add = new JButton("");
      add.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            
            TodolistAdd add;
            try {
               add = new TodolistAdd(subject);
               //MainFrame.main_panel.removeAll();
               //MainFrame.main_panel.add(add);
               SubjectOption.option_panel.removeAll();
               SubjectOption.option_panel.add(add);
               add.repaint();
               
               //add.revalidate();
               add.repaint();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         
         }
      });
      add.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/todo_add.PNG")));
      add.setBorder(null);
      add.setBackground(Color.WHITE);
      add.setBounds(5, 341, 96, 48);
      panel.add(add);
      
      
      
      //수정
      JButton edit = new JButton("");
      edit.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            
            int row = todolist_table.getSelectedRow();
            int col = todolist_table.getSelectedColumn();
            String value = (String) todolist_table.getValueAt(row, col);
            
            if(value.equals(" ")){
               
            }
            else{
               TodolistModify modi;
               try{
                  modi = new TodolistModify(subject, MainFrame.findList(value, MainFrame.Todo));
                  
                  SubjectOption.option_panel.removeAll();
                  SubjectOption.option_panel.add(modi);
                  modi.repaint(); 
               }
               catch(IOException e1){
                  e1.printStackTrace();
               }
            }
               
         }
      });
      edit.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/todo_edit.PNG")));
      edit.setBorder(null);
      edit.setBackground(Color.WHITE);
      edit.setBounds(104, 341, 96, 48);
      panel.add(edit);
      
      
      //삭제
      JButton del = new JButton("");
      del.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              int row = todolist_table.getSelectedRow();
              int col = todolist_table.getSelectedColumn();
              String value = (String) todolist_table.getValueAt(row, col);
              TodoListDelete tododel;
              try {
             	TodoDeletePopup deldialog = new TodoDeletePopup();
         		deldialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         		deldialog.setVisible(true);
 				tododel = new TodoListDelete(MainFrame.findList(value, MainFrame.Todo));
 		           
 		  
 	             TodolistList todolist_list = new TodolistList(subject);
 	             SubjectOption.option_panel.removeAll();
 	             SubjectOption.option_panel.add(todolist_list);
 	            
 	             todolist_list.revalidate();
 	             todolist_list.repaint();  
 	        
 	         
   
 				tododel.repaint();//
 				
 			} catch (IOException e1) {
 				// TODO Auto-generated catch block
 				e1.printStackTrace();
 			}
             

             
          }
       });
      del.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/todo_del.PNG")));
      del.setBorder(null);
      del.setBackground(Color.WHITE);
      del.setBounds(203, 341, 96, 48);
      panel.add(del);
      
      
      //완료
      JButton done = new JButton("");
      done.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
             int row = todolist_table.getSelectedRow();
             int col = todolist_table.getSelectedColumn();
             String value = (String) todolist_table.getValueAt(row, col);
             
             if(value.equals(" ")){
                
             }
             else{
                
                try{
                	TodolistComplete comp = new TodolistComplete(subject, MainFrame.findList(value, MainFrame.Todo));
                	comp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                	comp.setLocation(250, 350);
                	comp.setVisible(true);           
                }
                catch(IOException e1){
                   e1.printStackTrace();
                }
             }          
         }
      });
      done.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/todo_done.PNG")));
      done.setBorder(null);
      done.setBackground(Color.WHITE);
      done.setBounds(302, 341, 96, 48);
      panel.add(done);
      
      
   }


   void getTodoName(Object [][] column, String subject_name){
      
      
      //System.out.println("진입");
      int temp=0;
      for(int i=0;i<MainFrame.Todo.length;i++){
         

          if(MainFrame.Todo[i][0].equals(subject_name)&&MainFrame.Todo[i][8].equals("X"))
          {
             column[temp][1]=MainFrame.Todo[i][1];
             column[temp][2]=MainFrame.Todo[i][2]+"/"+MainFrame.Todo[i][3]+"/"+MainFrame.Todo[i][4]+"   "+MainFrame.Todo[i][5]+":"+MainFrame.Todo[i][6];
             column[temp][3]=MainFrame.Todo[i][7];
             temp++;

         }
      }
      
   }
   

}