package sh.app;

public class Activity extends DateObject{
	private Double duration;
	private Task task;
	private Developer developer;
	
	public Activity(String startDate, String endDate, Double duration, Task task, Developer developer) throws Exception{
		super(startDate, endDate);
		this.setDuration(duration);
		this.setTask(task);
		this.setDeveloper(developer);		
	}
	
	public Double getDuration(){
		return this.duration;
	}
	public void setDuration(Double duration) throws Exception{
		
		if (duration % 0.5 != 0){
			throw new OperationNotAllowedException("Activity duration must be divisable by 0.5", "Set duration");
		}
		
		this.duration = duration;
	}
	
	public Task getTask(){
		return this.task;
	}
	public void setTask(Task task){
		this.task = task;
	}
	
	public Developer getDeveloper(){
		return this.developer;
	}
	public void setDeveloper(Developer developer){
		this.developer = developer;
	}
}
