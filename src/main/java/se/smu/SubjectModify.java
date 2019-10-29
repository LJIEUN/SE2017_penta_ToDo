package se.smu;

import java.awt.Color;
import java.awt.Font;
import java.awt.color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//JPanel
public class SubjectModify extends JPanel{
	private JTextField subject_name;
	private JTextField professor_name;
	private JComboBox day_of_subject;
	private JComboBox start_time;
	private JComboBox end_time;
	private JComboBox year;								//
	private JComboBox semester;							//
	private JComboBox subject_type;						//
	private JPanel contentPanel = new JPanel();			//+
	private String [] old_subject;					///
	
	/**
	 * Create the panel.
	 */
	public SubjectModify(String[] subject) throws IOException{
		//setAlwaysOnTop(true);							//+
		
		setBounds(0, 0, 420, 480);					//+	//
		//setModal(true);									//+
		//setLocationRelativeTo(MainFrame.main_panel);		//+
		setBackground(Color.WHITE);
		setLayout(null);
		SubjectContents contents = new SubjectContents();
		
		JPanel panel = new JPanel();
		//panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 420, 480);
		add(panel);
		panel.setLayout(null);

		old_subject = subject.clone();			///
		
		JLabel sub_add_label = new JLabel("과목수정");
		sub_add_label.setFont(new Font("바탕체", Font.PLAIN, 15));			//
		sub_add_label.setBounds(120, 0, 319, 50);						//
		panel.add(sub_add_label);									//
		
		JButton complete = new JButton("완료");
		complete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					checkTheValue();
					subjectModifying(subject);
					panel.setVisible(false);					////
					panel.removeAll();	///
					

				} catch (IOException | ErrorException e1) {
				} 
			}
		});
		complete.setBounds(120+190, 170+70*3, 60, 40);
		panel.add(complete);												//
		
		subject_name = new JTextField(subject[0]);
		subject_name.setBounds(40, 60, 320, 60);
		panel.add(subject_name);										//
		subject_name.setColumns(10);
		
		professor_name = new JTextField(subject[1]);
		professor_name.setColumns(10);
		professor_name.setBounds(40, 60+65, 320, 60);
		panel.add(professor_name);											//
		
		
		day_of_subject = new JComboBox(contents.days);
		day_of_subject.setBounds(40, 60+65*2,100, 60);
		panel.add(day_of_subject);										//
		
		start_time = new JComboBox(contents.time1);
		start_time.setBounds(40+110, 60+65*2, 100, 60);
		panel.add(start_time);											//
		
		end_time = new JComboBox(contents.time2);
		end_time.setBounds(40+100+120, 60+65*2, 100, 60);
		panel.add(end_time);											//
		
		JLabel dash = new JLabel("\u3161");
		dash.setFont(new Font("바탕체", Font.PLAIN, 13));
		dash.setBounds(40+100+110, 60+65*2, 80, 60);
		panel.add(dash);										//
		
		year = new JComboBox(contents.year);						//
		year.setBounds(40, 60+65*3, 150, 60);
		panel.add(year);									//
		
		semester = new JComboBox(contents.semester);
		semester.setBounds(60+149, 60+65*3, 150, 60);
		panel.add(semester);											//
		
		subject_type = new JComboBox(contents.subject_type);
		subject_type.setBounds(40, 60+65*4, 200, 60);
		panel.add(subject_type);												//
		
		setSize(420, 480);											//
		setVisible(true);										//
		
	}
	void checkTheValue() throws ErrorException{
		String temp[] = {subject_name.getText(),professor_name.getText(),day_of_subject.getSelectedItem().toString(),start_time.getSelectedItem().toString()};
		
		for(int i=0;i<temp.length;i++){
			if(temp[i].equals("")){
				throw new ErrorException();	
			}
		}		
	}
	void subjectModifying(String[] subject) throws IOException, ErrorException{

		String temp1[]=start_time.getSelectedItem().toString().split(":");
		String temp2[]=end_time.getSelectedItem().toString().split(":");
		
		int start_time = Integer.parseInt(temp1[0]);
		int end_time = Integer.parseInt(temp2[0]);

		int temp=checkTime(start_time, end_time);
			if(temp==1){
				if(end_time<=start_time){
					throw new ErrorException();
				}
				else{
					newSubjectList(subject);
						
					HomeMain Home = new HomeMain();
						
					MainFrame.main_panel.removeAll();
					MainFrame.main_panel.add(Home);
						
						
					Home.revalidate();
					Home.repaint();
					
				
				}
		}
	}
	int checkTime(int start_time, int end_time) throws ErrorException{
		int temp=1;
		
		HomeMain home = new HomeMain();
		int day_no=getDays(day_of_subject.getSelectedItem().toString());
		
			for(int i = start_time-8;i<end_time-8;i++){
				if(!home.column[i][day_no].equals(" ")){
					if(home.column[i][day_no].equals(old_subject[0])){
						continue;
					}
					else{
						temp=0;
						throw new ErrorException();
					}
					
				}
			}
		
		return temp;
		
	}
	
	void newSubjectList(String[] subject) throws IOException{
		
		String old_sub[][] = MainFrame.subject;
		PrintWriter pw = new PrintWriter(new FileWriter("subject_out.txt"));
		
		for(int i=0;i<old_sub.length ;i++){
			for(int j=0;j<old_sub[i].length;j++){
				if(old_sub[i][0].equals(subject[0])){
						pw.print(subject_name.getText()+"`");
						pw.print(professor_name.getText()+"`");
						pw.print(day_of_subject.getSelectedItem().toString()+"`");
						pw.print(start_time.getSelectedItem().toString()+"`");
						pw.print(end_time.getSelectedItem().toString()+"`");
				        pw.print(year.getSelectedItem().toString()+"`");
				        pw.print(semester.getSelectedItem().toString()+"`");
				        pw.print(subject_type.getSelectedItem().toString()+"`");  
						pw.print((int)(Math.random()*100)%7+"`");
						break;
				}
				else{
					pw.print(old_sub[i][j]+"`");
				}
			}
			pw.println();
		}
		
		pw.close();
		

	}

	int getDays(String day){
		
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