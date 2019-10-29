package se.smu;

import javax.swing.JDialog;


public class ErrorException extends Exception {
	
	public ErrorException(){
		ErrorPopup error = new ErrorPopup();
		error.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		error.setVisible(true);
	}

}
