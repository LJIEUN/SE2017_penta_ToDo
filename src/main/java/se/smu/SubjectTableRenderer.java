package se.smu;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

public class SubjectTableRenderer extends DefaultTableCellRenderer
{ 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) 
    { 
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
        if (! isSelected) 
        {
        	if(value.equals(" ")){
        		
        			cell.setBackground(Color.WHITE);
        		
        	}
        	else if (row >= 0 && value != null)
            {
        		Object previous=null;
        		String[] temp = MainFrame.findList((String)value, MainFrame.subject);
        		
        		if(row==0){
        			previous=null;
        		}
        		else{
        			previous = table.getValueAt(row - 1, column);
        		}

                cell.setBackground(getColor(cell, Integer.parseInt(temp[6])));
                for(int i=0; i<MainFrame.Todo.length;i++){		//
        			if(value.equals(MainFrame.Todo[i][0])){
        				if(MainFrame.Todo[i][7].equals("O")){
        					cell.setBackground(Color.red);
        				}
        			}
                }				//
                if (value.equals(previous))
                {
                    setText("");

                }
            }
   
        }
        return this;
    } 
    

	Color getColor(Component cell, int num){
		Color color = null;
		
		
		
		switch(num){
		
		case 0:
			return Color.LIGHT_GRAY;
		case 1:
			//return Color.green;
			return Color.LIGHT_GRAY;

		case 2:
			return Color.LIGHT_GRAY;

			//return Color.cyan;
		case 3:
			return Color.LIGHT_GRAY;

			//return Color.yellow;
		case 4:
			return Color.LIGHT_GRAY;

			//return Color.magenta;
		case 5:
			return Color.LIGHT_GRAY;

			//return Color.pink;
		case 6:
			return Color.LIGHT_GRAY;

			//return Color.orange;
		}
		
		
		return null;
	}
}

