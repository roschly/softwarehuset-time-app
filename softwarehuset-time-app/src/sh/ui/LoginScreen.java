package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//remove after date test
import java.util.Date;

import sh.app.*; 

public class LoginScreen extends Screen {

	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("=== LOGIN");
		out.println("Enter username:");
		SimpleDateFormat format = new SimpleDateFormat("YYYY-ww");
		format.setLenient(false);
		Date startDate;
		Date endDate; 
		try{
			startDate = format.parse("2014-02"); 
			endDate = format.parse("2014-04");
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(startDate);
			int startWeek = cal.get(Calendar.WEEK_OF_YEAR); 
			cal.setTime(endDate);
			int endWeek = cal.get(Calendar.WEEK_OF_YEAR);
			
			int diff = endWeek-startWeek;
			//out.println(diff); 
			//out.println(startDate); 
			//out.println(endDate); 
			cal.setTime(startDate);
			for (int i = 0; i <= diff; i++) {
				cal.add(Calendar.WEEK_OF_YEAR, i);
				out.println(cal.getTime());
			}
			
			out.println(); 
		} catch (Exception e) {
			
		}
		
		
		
		/*int diff = endWeek-startWeek; 
		
		for (int i = startWeek; i <= endWeek; i++) {
			out.println()
		}
		
		out.println(startDate.);*/
	}

	@Override
	public boolean processInput(String input, PrintWriter out){
		
		User user = timeAppUI.getTimeApp().getUserByName(input); 

		if ( user != null ){
			timeAppUI.setScreen( new OverviewScreen(user) );
		}
		else {
			out.print("User doesn't exist!");
			timeAppUI.setScreen( new LoginScreen() );
		}
		return false;
	}

}
	

