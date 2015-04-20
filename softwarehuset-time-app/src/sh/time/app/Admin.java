package sh.time.app;

public class Admin extends User {
	
	
	public void createProject(String name){
		Project project = new Project(name); 
		TimeApp.projects.add(project);
	}
	
	public void assignProjectManager(Developer dev, Project project){
		
	}
	
	public void deleteProject(){}
	public void unassignProjectManager(){}
	
	
}
