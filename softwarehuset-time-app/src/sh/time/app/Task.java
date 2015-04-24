package sh.time.app;

public class Task {
	private static int nextTaskId = 0;
	
	private int id;
	private String name;
	private double estimatedDuration;
	
	public Task(String name){
		this.id = Task.nextTaskId;
		Task.nextTaskId++;
		
		this.name = name;
	}
	
	// method: how much time spent on this task (check activities)

}
