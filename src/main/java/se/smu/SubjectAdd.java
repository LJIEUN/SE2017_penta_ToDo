package se.smu;


import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;

//import TodoList.MainFrame;
//import TodoList.Error_Exception;
//import TodoList.Home.Subject_Contents;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;


public class SubjectAdd extends JDialog {
	private JTextField subject_name;
	private JTextField professor_name;
	private JComboBox day_of_subject;
	private JComboBox start_time;
	private JComboBox end_time;
	private JComboBox year;								//
	private JComboBox semester;							//
	private JComboBox subject_type;						//
	private JPanel contentPanel = new JPanel();			//+

	/**
	 * Create the panel.
	 */
	public SubjectAdd(){
		setAlwaysOnTop(true);							//+
		setBounds(100, 100, 319, 400);					//+	//
		setModal(true);									//+
		setLocationRelativeTo(MainFrame.main_panel);		//+
		setBackground(Color.WHITE);
		setBounds(100,100, 319, 400);
		setLayout(null);
		SubjectContents contents = new SubjectContents();				

		
		JLabel sub_add_label = new JLabel("과목등록");
		sub_add_label.setFont(new Font("바탕체", Font.PLAIN, 15));			//
		sub_add_label.setBounds(100, 100, 319, 50);						//
		add(sub_add_label);									//
		
		JButton complete = new JButton("완료");
		complete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					checkTheValue();
					subjectAdding();
				} catch (IOException | ErrorException e1) {
				} 
				dispose();										///
			}
		});
		complete.setBounds(120+219, 170+65*4, 100, 60);
		add(complete);												//
		
		subject_name = new JTextField("과목명");
		subject_name.setBounds(120, 170, 279, 60);
		add(subject_name);										//
		subject_name.setColumns(10);
		

		
		professor_name = new JTextField("담당교수명");
		professor_name.setColumns(10);
		professor_name.setBounds(120, 170+65, 279, 60);
		add(professor_name);											//
		
		
		day_of_subject = new JComboBox(contents.days);
		day_of_subject.setBounds(120, 170+65*2,80, 60);
		add(day_of_subject);	
		
		start_time = new JComboBox(contents.time1);
		start_time.setBounds(120+99, 170+65*2, 80, 60);
		add(start_time);											//
		
		end_time = new JComboBox(contents.time2);
		end_time.setBounds(120+99+95, 170+65*2, 80, 60);
		add(end_time);											//
		
		JLabel dash = new JLabel("\u3161");
		dash.setFont(new Font("바탕", Font.PLAIN, 13));
		dash.setBounds(120+99+40, 170+65*2, 80, 60);
		add(dash);										//
		
		year = new JComboBox(contents.year);						//
		year.setBounds(120, 170+65*3, 130, 60);
		add(year);									//
		
		semester = new JComboBox(contents.semester);
		semester.setBounds(120+149, 170+65*3, 130, 60);
		add(semester);											//
		
		subject_type = new JComboBox(contents.subject_type);
		subject_type.setBounds(120, 170+65*4, 200, 60);
		add(subject_type);												//


		setSize(500, 700);			
		setVisible(true);										//
		
	}
	

	void checkTheValue() throws ErrorException{
		String temp[] = {subject_name.getText(),professor_name.getText(),day_of_subject.getSelectedItem().toString(),start_time.getSelectedItem().toString()};

		for(int i=0;i<temp.length;i++){
			if(temp[i].equals("")){
				throw new ErrorException();
			}
			if(subject_name.getText().equals("과목명")){
				throw new ErrorException();
			}
			if(professor_name.getText().equals("담당교수명")){
				throw new ErrorException();
			}
		}

	}
	
	void subjectAdding() throws IOException, ErrorException{
		
		
		String temp1[]=start_time.getSelectedItem().toString().split(":");
		String temp2[]=end_time.getSelectedItem().toString().split(":");
		
		int start_time_int = Integer.parseInt(temp1[0]);
		int end_time_int = Integer.parseInt(temp2[0]);
		
		int temp=checkTime(start_time_int, end_time_int);

		if(temp==1){
			if(end_time_int<=start_time_int){
				throw new ErrorException();
			}
			else{
	
				saveSubject();
			
				HomeMain Home = new HomeMain();
					
				MainFrame.main_panel.removeAll();
				MainFrame.main_panel.add(Home);
					
					
				Home.revalidate();
				Home.repaint();
			}
		}
	}

	void saveSubject() throws IOException{
		
		
		PrintWriter pw = new PrintWriter(new FileWriter("subject_out.txt", true));
		
        pw.print(subject_name.getText()+"`");
        pw.print(professor_name.getText()+"`");
        pw.print(day_of_subject.getSelectedItem().toString()+"`");
        pw.print(start_time.getSelectedItem().toString()+"`");
        pw.print(end_time.getSelectedItem().toString()+"`");
        pw.print(year.getSelectedItem().toString()+"`");
        pw.print(semester.getSelectedItem().toString()+"`");
        pw.print(subject_type.getSelectedItem().toString()+"`");
        pw.print((int)(Math.random()*100)%7+"`");    
        pw.println();
        pw.close();
	}
	
	int checkTime(int start_time, int end_time) throws ErrorException{
		int temp=1;
		
		HomeMain home = new HomeMain();
		int day_no=getDay(day_of_subject.getSelectedItem().toString());

		
			for(int i = start_time-8;i<end_time-8;i++){
				if(!home.column[i][day_no].equals(" ")){
					temp=0;
					throw new ErrorException();
				}
			}
		
		return temp;
		
	}
	
int getDay(String day){
		
		int day_no=0;
		
		switch(day){
		
		case "월" :
			day_no=1;
			break;
		case "화" :
			day_no=2;
			break;
		case "수" :
			day_no=3;
			break;
		case "목" :
			day_no=4;
			break;
		case "금" :
			day_no=5;
			break;
		}
		return day_no;
	}
	
}


