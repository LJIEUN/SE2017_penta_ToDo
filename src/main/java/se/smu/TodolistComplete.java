package se.smu;


import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TodolistComplete extends JDialog {

   private final JPanel contentPanel = new JPanel();
      private JComboBox finish_year=null;
      private JComboBox finish_month=null;
      private JComboBox finish_date=null;
      private String deadline_min;
      private String deadline_hour;
      private String deadline_date;
      private String deadline_month;
      private String deadline_year;
      private String todo_name;
      private String subject_name;
      private String importance;
   /**
    * Create the dialog.
    */
   public TodolistComplete(String [] subject, String [] todo) throws IOException {

      subject_name=todo[0];
      todo_name=todo[1];
      deadline_year=todo[2];
      deadline_month=todo[3];
      deadline_date=todo[4];
      deadline_hour=todo[5];
      deadline_min=todo[6];
      importance=todo[7];
      
      
      setAlwaysOnTop(true);
      setBounds(100, 100, 350, 190);
      setModal(true);
      setLocationRelativeTo(MainFrame.main_panel);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBackground(Color.WHITE);
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(null);
      
      JLabel message = new JLabel("실제 마감일을 설정 해주세요");
      message.setBackground(Color.WHITE);
      message.setFont(new Font("바탕체", Font.PLAIN, 10));
      message.setBounds(100, 10, 178, 30);
      contentPanel.add(message);
      
      TodolistContents todolist_contents = new TodolistContents();
      
      finish_year = new JComboBox(todolist_contents.year);
      finish_year.setBounds(8, 40,103,50);
      contentPanel.add(finish_year);

      
      finish_month = new JComboBox(todolist_contents.month);
      finish_month.setBounds(118, 40,100,50);
      contentPanel.add(finish_month);
      
      finish_date = new JComboBox(todolist_contents.date);
      finish_date.setBounds(228, 40,100,50);
      contentPanel.add(finish_date);
      
      
      JButton cancel = new JButton("");
      cancel.setIcon(new ImageIcon(TodolistComplete.class.getResource("/icon/cancel.PNG")));
      cancel.setBackground(Color.WHITE);
      cancel.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            dispose();
            
         }
      });
      cancel.setBorder(null);
      cancel.setBounds(168, 100, 147, 47);
      contentPanel.add(cancel);
      
      JButton confirm = new JButton("");
      confirm.setIcon(new ImageIcon(TodolistComplete.class.getResource("/icon/ok.PNG")));
      confirm.setBackground(Color.WHITE);
      confirm.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               dispose();
               checkTheValue();
               completeTodolist(todo);
               
                     TodolistList todolist_list = new TodolistList(subject);
                     SubjectOption.option_panel.removeAll();
                     SubjectOption.option_panel.add(todolist_list);
                     
            } catch (ErrorException | IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
            
         }
      });
      confirm.setBorder(null);
      confirm.setBounds(18, 100, 152, 47);
      contentPanel.add(confirm);
   }
   void checkTheValue() throws ErrorException{
         String temp[] = {todo_name, deadline_year, deadline_month,
         deadline_date, deadline_hour, deadline_min};
         String temp1 = finish_month.getSelectedItem().toString();
         String temp2 = finish_date.getSelectedItem().toString();
         
         if(temp1.equals("02")&&temp2.equals("31")||temp1.equals("02")&&temp2.equals("30")||
        		 temp1.equals("04")&&temp2.equals("31")||temp1.equals("06")&&temp2.equals("31")||
        		 temp1.equals("08")&&temp2.equals("31")||temp1.equals("09")&&temp2.equals("31")||
        		 temp1.equals("11")&&temp2.equals("31")){
            throw new ErrorException();
         }      
         for(int i=0; i<temp.length; i++){
            if(temp[i].equals("")){
            throw new ErrorException();
          }
      }
   }
   
   void completeTodolist(String[] todo) throws ErrorException, IOException{
      String old_todo[][] = MainFrame.Todo;
      PrintWriter print_writer = new PrintWriter(new FileWriter("Todo_out.txt"));
      for(int i=0; i<old_todo.length; i++){
         for(int j=0; j<old_todo[i].length; j++){
            if(old_todo[i][1].equals(todo[1])){
               print_writer.print(todo[0]+"`");
               print_writer.print(todo[1]+"`");
               print_writer.print(todo[2]+"`");//2
               print_writer.print(todo[3]+"`");//3
               print_writer.print(todo[4]+"`");//4
               print_writer.print(todo[5]+"`");//5
               print_writer.print(todo[6]+"`");//6
               print_writer.print(todo[7]+"`");//7
                 print_writer.print("O`");//8
               print_writer.print(finish_year.getSelectedItem().toString()+"`");//9
               print_writer.print(finish_month.getSelectedItem().toString()+"`");//10
               print_writer.print(finish_date.getSelectedItem().toString()+"`");//11
               break;
        
            }
            else{
               print_writer.print(old_todo[i][j]+"`");
            }
         }
         print_writer.println();
         }
         print_writer.close();
      
         MainFrame.Todo = MainFrame.getTodo();      
      }
}