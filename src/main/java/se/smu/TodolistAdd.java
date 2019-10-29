package se.smu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;


public class TodolistAdd extends JPanel{
   private String subject_name;
   private JTextField todo_name;
   private JComboBox deadline_year;
   private JComboBox deadline_month;
   private JComboBox deadline_date;
   private JComboBox deadline_hour;
   private JComboBox deadline_min;
   private JCheckBox importance;
   private int importance2 = 0;  
   public String finish_year = null;
   public String finish_month = null;
   public String finish_date = null;
   
   public TodolistAdd(String [] subject) throws IOException {
      //setBackground(Color.WHITE);
      setBounds(0,0,420,480);
      setLayout(null);
      EtchedBorder eborder;
      
      eborder=new EtchedBorder(EtchedBorder.LOWERED);
            
      JPanel panel = new JPanel();
      //panel.setBackground(Color.WHITE);
      panel.setBounds(0, 0, 420, 480);
      add(panel);
      panel.setLayout(null);
      
      JLabel todo_add = new JLabel("To do list 등록");
      todo_add.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
      todo_add.setBounds(120, 0, 200, 30);
      panel.add(todo_add);
      
      TodolistContents todolist_contents = new TodolistContents();
      
      JButton confirm = new JButton("확인");
      confirm.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            try{
               hide();
               checkTheValue();
      
               addTodolist();
               MainFrame.Todo = MainFrame.getTodo();
               
               TodolistList todolist_list = new TodolistList(subject);
               SubjectOption.option_panel.removeAll();
               SubjectOption.option_panel.add(todolist_list);
               
               todolist_list.revalidate();
               todolist_list.repaint();               
            }
            catch(IOException | ErrorException e1){
               
            }
         }
      });
      confirm.setBounds(300, 390, 60, 35);
      confirm.setBackground(Color.DARK_GRAY);
      confirm.setForeground(Color.WHITE);
      panel.add(confirm);
      
      subject_name = subject[0];
      
      JLabel todo_name_label = new JLabel("* 할 일");
      todo_name_label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
      todo_name_label.setBorder(null);
      todo_name_label.setBounds(35, 40, 60, 60);
      panel.add(todo_name_label);
      
      todo_name = new JTextField();
      todo_name.setBounds(35, 90, 340, 60);
      panel.add(todo_name);
      todo_name.setColumns(10);
      
      JLabel deadline_label = new JLabel("* 마감기한");
      deadline_label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
      deadline_label.setBorder(null);
      deadline_label.setBounds(35, 145, 103, 50);
      panel.add(deadline_label);
      
      deadline_year = new JComboBox(todolist_contents.year);
      deadline_year.setBounds(35, 190, 100, 60);
      panel.add(deadline_year);
      
      deadline_month = new JComboBox(todolist_contents.month);
      deadline_month.setBounds(155, 190, 100, 60);
      panel.add(deadline_month);

      deadline_date = new JComboBox(todolist_contents.date);
      deadline_date.setBounds(280, 190, 100, 60);
      panel.add(deadline_date);
      
      deadline_hour = new JComboBox(todolist_contents.hour);
      deadline_hour.setBounds(155, 270, 100, 60);
      panel.add(deadline_hour);
      
      deadline_min = new JComboBox(todolist_contents.min);
      deadline_min.setBounds(280, 270, 100, 60);
      panel.add(deadline_min);
      
      
      
      
      JLabel importance_label = new JLabel("* 중요여부");
      importance_label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
      importance_label.setBorder(null);
      importance_label.setBounds(35, 330, 103, 60);
      panel.add(importance_label);
      
      importance = new JCheckBox();
      importance.setBounds(140, 352, 20, 20);
      panel.add(importance);
      /*
      if(importance.isSelected()){
    	  importance2 = 1;  
      }
      */
   }
   void checkTheValue() throws ErrorException{
      String temp[] = {todo_name.getText(), deadline_year.getSelectedItem().toString(), deadline_month.getSelectedItem().toString(),
            deadline_date.getSelectedItem().toString(), deadline_hour.getSelectedItem().toString(), deadline_min.getSelectedItem().toString()};
      String temp1 = deadline_month.getSelectedItem().toString();
      String temp2 = deadline_date.getSelectedItem().toString();
      
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

   void saveTodolist() throws IOException{
      File f = new File("Todo_out.txt");
      
      if(!f.isFile()){
         f.createNewFile();
      }
      PrintWriter print_writer = new PrintWriter(new FileWriter("Todo_out.txt", true));
      
      print_writer.print(subject_name+"`");//0
      print_writer.print(todo_name.getText()+"`");//1
      print_writer.print(deadline_year.getSelectedItem().toString()+"`");//2
      print_writer.print(deadline_month.getSelectedItem().toString()+"`");//3
      print_writer.print(deadline_date.getSelectedItem().toString()+"`");//4
      print_writer.print(deadline_hour.getSelectedItem().toString()+"`");//5
      print_writer.print(deadline_min.getSelectedItem().toString()+"`");//6
      if(importance.isSelected()){
      print_writer.print("O"+"`");//7
      }
      else{
      print_writer.print("X"+"`");  
      }
    	  
      print_writer.print("X`");//8
      print_writer.print(finish_year+"`");//9
      print_writer.print(finish_month+"`");//10
      print_writer.print(finish_date+"`");//11
      print_writer.println();
      
      print_writer.close();
   }
   
   void saveTodolist(int a) throws IOException{
       File f = new File("Todo_out.txt");

          // 파일 존재 여부 판단
          if (!f.isFile()) {
             f.createNewFile();
          }
          
         PrintWriter print_writer = new PrintWriter(new FileWriter("Todo_out.txt", true));
         
          print_writer.print(subject_name+"`");//0
          print_writer.print(todo_name.getText()+"`");//1
          print_writer.print(deadline_year.getSelectedItem().toString()+"`");//2
          print_writer.print(deadline_month.getSelectedItem().toString()+"`");//3
          print_writer.print(deadline_date.getSelectedItem().toString()+"`");//4
          print_writer.print(deadline_hour.getSelectedItem().toString()+"`");//5
          print_writer.print(deadline_min.getSelectedItem().toString()+"`");//6
          print_writer.print(a+"`");//7
          print_writer.print("X`");//8
          print_writer.print(finish_year+"`");//9
          print_writer.print(finish_month+"`");//10
          print_writer.print(finish_date+"`");//11
          print_writer.println();
         
          print_writer.close();
          
   }
   
   void addTodolist() throws IOException, ErrorException{
   


     // if(importance.isSelected()){

      //   saveTodolist(1);
    //  }
    //  else 
         saveTodolist();
      

      
   }
}