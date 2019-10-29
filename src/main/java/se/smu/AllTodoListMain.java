package se.smu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import se.smu.MainFrame;

public class AllTodoListMain extends JPanel implements ItemListener{
	
	private String choice;
	private JTable todo_table;
	private JPanel panel;
	/**
	 * Create the panel.
	 */
	public AllTodoListMain() {
		setBackground(Color.WHITE);
		setBounds(0, 0, 584, 628);
		setLayout(null);
		
		
		
		
		panel = new JPanel();
		panel.setBounds(0, 0, 584, 82);
		panel.setBackground(Color.pink);
		add(panel);
		panel.setLayout(null);
		
		JLabel todo_label = new JLabel("5조-PENTA");
		todo_label.setFont(new Font("고딕체", Font.PLAIN, 30));
		todo_label.setHorizontalAlignment(SwingConstants.CENTER);
		todo_label.setBounds(220, 0, 160, 50);
		
		panel.add(todo_label);
		

		int a;
		int b;
		
		if(MainFrame.Todo.length==0){
			a=1;
			b=1;
		}
		else{
			a=MainFrame.Todo.length;
			b=MainFrame.Todo[0].length;
		}
		
		Object[][] column = new Object[a][b];
		String[] row = {
			"과목명", "할 일", "중요도", "마감 기한", "완료", "실제 마감일"
		};
		
		
		try {
			MainFrame.Todo=MainFrame.getTodo();
			paintToDoTable(MainFrame.Todo,column);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e){
			e.getMessage();
		}
		
		
		todo_table = new JTable();		
		todo_table.setBounds(0, 82, 583, 575);
		todo_table.setModel(new DefaultTableModel(column,row){
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		

		
		todo_table.setRowSorter(new TableRowSorter(new DefaultTableModel(column,row)));
		
		
		todo_table.getColumnModel().getColumn(2).setMaxWidth(60);
		todo_table.getColumnModel().getColumn(4).setMaxWidth(60);
		
		JScrollPane scrollPane = new JScrollPane(todo_table);
		scrollPane.setBounds(0, 82, 583, 575);
		scrollPane.setBackground(Color.WHITE);
		add(scrollPane);
		
		JComboBox sorter = new JComboBox();
		sorter.addItem("과목명"); 
		sorter.addItem("할 일"); 
		sorter.addItem("중요도"); 
		sorter.addItem("마감기한");
		sorter.addItem("실제마감일");
		sorter.addItem("완료");
		sorter.setBackground(Color.white);
		sorter.setBounds(400,50,150,30);
		sorter.addItemListener(this); 
		sorter.setVisible(true);
		panel.add(sorter);
		
		filter(MainFrame.Todo,column, row);

	}
	
	void paintToDoTable(String[][] Todo, Object[][] column){

		for(int i=0;i<Todo.length;i++){
			column[i][0] = Todo[i][0];
			column[i][1] = Todo[i][1];

			column[i][3] = Todo[i][2]+"/"+Todo[i][3]+"/"+Todo[i][4]+"  "+Todo[i][5]+":"+Todo[i][6];
			
			if(Todo[i][5].equals(null))
			column[i][4] = null;
			else
				column[i][4] = Todo[i][9]+"/"+Todo[i][10]+"/"+Todo[i][11];	
			
			
			column[i][2] = Todo[i][7];
			column[i][4] = Todo[i][8];
			if(Todo[i][8].equals("X")){
			
			}
			else{
				column[i][5] = Todo[i][9]+"/"+Todo[i][10]+"/"+Todo[i][11];
			}
		}
	}
	
	void filter(String[][] Todo, Object[][] column2,String[] row){
		JButton Filter = new JButton("숨기기");
		Filter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				AllTodoListMainHiddener Test1 = new AllTodoListMainHiddener();
				
			
				MainFrame.main_panel.removeAll();
				MainFrame.main_panel.add(Test1);
				
				Test1.revalidate();
				Test1.repaint();
				}
				catch (NullPointerException e1){
					e1.getMessage();
				}
			
			}
			
		});
		
		Filter.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		Filter.setBounds(220,50,150,30);
		Filter.setVisible(true);
		panel.add(Filter);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		choice = (String)e.getItem(); 
		
	    switch(choice)
		{
		case "과목명" :
			todo_table.getRowSorter().toggleSortOrder(0);
			break;
		case "할 일" :
			todo_table.getRowSorter().toggleSortOrder(1);
			break;
		case "중요도" :
			todo_table.getRowSorter().toggleSortOrder(2);
			break;
		case "마감기한" :
			todo_table.getRowSorter().toggleSortOrder(3);
			break;
		case "완료" :
			todo_table.getRowSorter().toggleSortOrder(4);
			break;
		case "실제마감일" :
			todo_table.getRowSorter().toggleSortOrder(5);
			break;
		}
	
	}
	
	
}


