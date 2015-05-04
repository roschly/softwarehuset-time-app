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
	

