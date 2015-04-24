package sh.time.app;

public class Admin extends User {
	
	public Admin(String username, String password, String role){
		super(username, password, role);
	}
	
	public void createProject(String name){
		Project project = new Project(name); 
		TimeApp.projects.add(project);
	}
	
	public void assignProjectManager(Developer dev, Project project){
		
	}
	
	public void deleteProject(){}
	public void unassignProjectManager(){}
	
	
}
