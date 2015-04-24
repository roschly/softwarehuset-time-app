package sh.time.app;

public class Developer extends User {
	
	public Developer(String username, String password, String role){
		super(username, password, role);
	}
	
	// TODO: Add optional Duration parameter ??
	public void createTask(Project project, String taskName) throws MissingRightsException{
		
		// check project has projectmanager AND this developer is that projectmanager
		if ( project.getProjectmanager() != null && project.getProjectmanager() == this ){
			Task task = new Task(taskName);
			project.addTask(task);
		}
		else {
			throw new MissingRightsException("Create task operation not allowed if not project manager", "Create task");
		}
	}
	
	public void timeRegistration(){}
	
}
