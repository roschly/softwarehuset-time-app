package sh.app;

public class Activity extends DateObject{
	private Double duration;
	private Task task;
	private Developer developer;
	
	public Activity(String startDate, String endDate, Double duration, Task task, Developer developer) throws Exception{
		super(startDate, endDate);
		
	}
	
	public Double getDuration(){
		return this.duration;
	}
	public void setDuration(Double time){
		
		
		
		this.duration = time;
	}
}
