package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;

import sh.app.*;

public class PMOverviewScreen extends Screen {
	
	User user;
	
	public PMOverviewScreen(User user) {
		this.user = user;
	}
		
	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("=== PM: Overview");
		out.println("0: Back");
		out.println("1: Show projects");
		out.println("2: Select project");
	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		
		switch (input){
		case "0":
			timeAppUI.setScreen(new LoginScreen());;
		case "1":;
		case "2":;
		}
		
		return false;
	}

}
