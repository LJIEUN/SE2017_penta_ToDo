package se.smu;
import java.util.Calendar;

public class TodolistContents {
	Calendar right_now = Calendar.getInstance();
	String yearstr = right_now.get(Calendar.YEAR)+"";
	String monthstr = (right_now.get(Calendar.MONTH)+1) +"";
	String datestr = right_now.get(Calendar.DAY_OF_MONTH) +"";
	String hourstr = right_now.get(Calendar.HOUR_OF_DAY)+"";
	String minstr = right_now.get(Calendar.MINUTE)+"";
	
	String year[] = {yearstr,"2017","2018","2019","2020"};
	String month[] = {monthstr,"01","02","03","04","05","06","07","08","09","10","11","12"};
	String date[] = {datestr,"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20",
			"21","22","23","24","25","26","27","28","29","30","31"};
	String hour[] = {hourstr,"00", "01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20",
			"21","22","23"};
	String min[] = {minstr, "00", "01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20",
			"21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40",
			"41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
	
	
			

}
