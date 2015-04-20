package sh.time.app;

public class Task {
	private static int nextTaskId = 0;
	
	private int id;
	private String name;
	
	public Task(String name){
		this.id = Task.nextTaskId;
		Task.nextTaskId++;
		
		this.name = name;
	}

}
