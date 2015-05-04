package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;

import sh.app.Activity;
import sh.app.Project;
import sh.app.Task;
import sh.app.User;

public class ActivityScreen extends Screen {

	private User user;
	private Project project;
	private Task task;
	private Activity activity;
		
	public ActivityScreen(User user, Project project, Task task, Activity activity) {
		this.user = user;
		this.project = project;
		this.task = task;
		this.activity = activity;
	}

	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("=== ACTIVITY VIEW: ID " + this.activity.getId());
		out.println("0: Back");
		out.println("1 <hours>: Change duration"); // this.activity.setDuration(<input>)
		//out.println("2: Delete activity");

		
	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		
		String[] cmdInputs = input.split(" ");
		String cmdNumber = cmdInputs[0];
		
		switch (cmdNumber){
		case "0":
			timeAppUI.setScreen(new TaskScreen(this.user, this.project, this.task));
			break;
		case "1":
			// Change duration
			if (cmdInputs.length != 2){
				out.print("ERROR: Incorrect number of arguments");
				timeAppUI.setScreen( new ActivityScreen(this.user, this.project, this.task, this.activity) );
				break;
			}
			else {
				String duration = cmdInputs[1];
				
				try {
					this.activity.changeDuration( Double.parseDouble(duration), this.user, this.project );
					out.println("New duration: " + duration + " hours, for activity with ID: " + this.activity.getId());
					timeAppUI.setScreen( new ActivityScreen(this.user, this.project, this.task, this.activity) );
				} catch(Exception e){
					out.println(e.getMessage());
				}
			}
			break;			
		default:
			out.print("ERROR: wrong input");
			break;
		}
		return false;
	}

}
