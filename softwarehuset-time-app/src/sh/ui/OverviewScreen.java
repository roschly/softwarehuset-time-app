package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import sh.app.Project;
import sh.app.User;

public class OverviewScreen extends Screen {

	private User user;
	
	public OverviewScreen(User user){
		this.user = user;
	}
	
	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("=== OVERVIEW");
		out.println("0: back");
		out.println("1: show projects");
		out.println("2 <projectname>: select project");
		out.println("3 <unique projectname> <startDate> <endDate>: create project");
		// Delete project

	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		
		// TODO: error handle
		String[] cmdInputs = input.split(" ");
		String cmdNumber = cmdInputs[0];
		
		
		
		switch (cmdNumber){
		case "0":
			timeAppUI.setScreen(new LoginScreen());
			break;
		case "1":
			// Show projects
			ArrayList<Project> projects = timeAppUI.getTimeApp().getProjects();
			ArrayList<String> projectNames = new ArrayList<String>();
			
			for (Project project : projects){
				projectNames.add(project.getName());
			}
			out.println("List of projects");
			Screen.displayList( projectNames );
			break;
		case "2":
			// Select project
			if (cmdInputs.length < 2){
				out.println("ERROR: Too few arguments");
				timeAppUI.setScreen( new OverviewScreen(this.user) );
				break;
			}
			else {
				String cmdArgument = cmdInputs[1];
				Project project = timeAppUI.getTimeApp().getProjectByName(cmdArgument);
				timeAppUI.setScreen( new ProjectScreen(this.user, project) );
				break;
			}
		case "3":
			// Create project
			break;
		default:
			break;
		}
		return false;
	}

}
