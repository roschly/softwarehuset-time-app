package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;

import sh.app.*;

public class AdminProjectScreen extends Screen {
	private User user; 
	private Project project; 
	
	public AdminProjectScreen(User user, Project project){
		this.user = user; 
		this.project = project; 
	}

	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("=== ADMIN: Project view");
		out.println("0: Back");
		out.println("1: Assign project manager");
		out.println("2: Delete project");

	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		// TODO Auto-generated method stub
		return false;
	}

}
