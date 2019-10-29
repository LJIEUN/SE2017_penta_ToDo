package se.smu;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class SubjectDelete {

	public SubjectDelete(String [] subject) throws IOException{
		
		deleteSubject(subject);
		deleteRelatedTodo(subject);			
		repaint();
		
	}
	
	void deleteSubject(String[] subject) throws IOException{
		
		String [][] old_subject = MainFrame.subject;
		PrintWriter pw = new PrintWriter(new FileWriter("subject_out.txt"));
		
		for(int i=0;i<old_subject.length;i++){
				if(old_subject[i][0].equals(subject[0])){
					continue;
				}
				else{
					for(int j=0;j<old_subject[i].length;j++){
						pw.print(old_subject[i][j]+"`");
					}
					pw.println();
				}
			}
		pw.close();
	}
	
	void deleteRelatedTodo(String[] subject) throws IOException{
		
		String [][] old_todo = MainFrame.Todo;
		PrintWriter pw = new PrintWriter(new FileWriter("Todo_out.txt"));
		
		for(int i=0;i<old_todo.length;i++){
				if(old_todo[i][0].equals(subject[0])){
					continue;
				}
				else{
					for(int j=0;j<old_todo[i].length;j++){
						pw.print(old_todo[i][j]+"`");
					}
					pw.println();
				}
			}
		pw.close();
		MainFrame.Todo=MainFrame.getTodo();
	}
	
	void repaint(){
		HomeMain Home = new HomeMain();
		
		MainFrame.main_panel.removeAll();
		MainFrame.main_panel.add(Home);
			
			
		Home.revalidate();
		Home.repaint();
	}
}