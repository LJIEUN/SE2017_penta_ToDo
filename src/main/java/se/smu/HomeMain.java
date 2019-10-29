package se.smu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
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

public class HomeMain extends JPanel{
	
	private JTable table;
	Object[][] column = {
			
			{new Integer(8), " ", " ", " ", " ", " "},
			{new Integer(9), " ", " ", " ", " ", " "},
			{new Integer(10), " ", " ", " ", " ", " "},
			{new Integer(11), " ", " ", " ", " ", " "},
			{new Integer(12), " ", " ", " ", " ", " "},
			{new Integer(13), " ", " ", " ", " ", " "},
			{new Integer(14), " ", " ", " ", " ", " "},
			{new Integer(15), " ", " ", " ", " ", " "},
			{new Integer(16), " ", " ", " ", " ", " "},
			{new Integer(17), " ", " ", " ", " ", " "},
			{new Integer(18), " ", " ", " ", " ", " "},
			{new Integer(19), " ", " ", " ", " ", " "},
			{new Integer(20), " ", " ", " ", " ", " "},
			{new Integer(21), " ", " ", " ", " ", " "},
			{new Integer(22), " ", " ", " ", " ", " "},
		};
	
	/**
	 * Create the panel.
	 */
	public HomeMain() {
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 584, 56);
		panel.setBackground(Color.pink);
		add(panel);
		panel.setLayout(null);
		
		JLabel todo_label = new JLabel("5조-PENTA");
		todo_label.setFont(new Font("고딕체", Font.PLAIN, 30));
		todo_label.setHorizontalAlignment(SwingConstants.CENTER);
		todo_label.setBounds(220, 0, 160, 50);
		panel.add(todo_label);
		
		//HomeMain 
		setBackground(Color.pink);
		setBounds(0, 0, 584, 630);
		setLayout(null);

			String[] row = {
				" ", "월", "화", "수", "목", "금"
			};
		
			
			
		try {
				MainFrame.subject = MainFrame.getSubject();
				paintTable(MainFrame.subject,column);
				
			
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
			} catch (NullPointerException e){
				e.getMessage();
			}
			
		
		
		table = new JTable(column,row);
		table.setFont(new Font("바탕체", Font.PLAIN, 12));
		table.setModel(new DefaultTableModel(column,row) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class,  String.class,  String.class,  String.class,  String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		
		
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setName("TimeTable");
		table.setColumnSelectionAllowed(true);
		
		SubjectTableRenderer renderer = new SubjectTableRenderer();
		
		table.setDefaultRenderer(String.class, renderer);
		
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		table.setRowHeight(50);
		
		
		table.setBounds(0,0, 584, 630);
		
		JScrollPane jscrollpane = new JScrollPane(table);
		jscrollpane.setBounds(0, 60, 584, 570);
		add(jscrollpane);
		

		//여기서부터 
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) throws NullPointerException, ArrayIndexOutOfBoundsException{
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				String value = (String) table.getValueAt(row, col);
				if(value.equals(" ")){
					
				}
				else
				{
				if(e.getModifiers() == MouseEvent.BUTTON3_MASK){				//+++//
					SubjectOption subject_option = new SubjectOption(MainFrame.findList(value, MainFrame.subject));				///+++//
				}
				}
			}
		});
		
	}										

	void paintTable(String[][] subject, Object[][] column){
		
		
		
		for(int i = 0;i<subject.length;i++){
			
			String temp1[] = subject[i][3].split(":");
			String temp2[] = subject[i][4].split(":");
			int start_time = Integer.parseInt(temp1[0]);
			int end_time =  Integer.parseInt(temp2[0]);
			
			int days = getDay(subject[i][2]);
			
			column[start_time-8][days]=subject[i][0];
			for(int j = start_time-7;j<end_time-8;j++){
				column[j][days]=subject[i][0];
				
			}
		}
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
