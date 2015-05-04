package sh.app;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Activity {
	private static Integer nextActivityId = 0;
	
	private Double duration;
	private User developer;
	private Date date;
	private Integer id;
	

	
	public Activity(String date, Double duration, User developer, Task task) throws Exception{
		if ( !task.getDevelopers().contains(developer) ) {
			throw new OperationNotAllowedException("Must be assigned to task to create activity", "Create activity");
		} else {
			
			this.id = Activity.nextActivityId;
			Activity.nextActivityId++;
			
			this.setDuration(duration);
			this.setDeveloper(developer);
			this.setDate(date);
			
			task.addActivity(this);
		}
	}
	
	public Double getDuration(){
		return this.duration;
	}
	
	public void setDuration(Double duration) throws Exception{
		
			if (duration % 0.5 != 0 && duration != 0){
				throw new OperationNotAllowedException("Activity duration must be divisable by 0.5 AND not 0", "Set duration");
			}		
		this.duration = duration;
	}
	
	public void changeDuration(Double duration, User user, Project project) throws Exception{
		
		if( ! (this.developer.equals(user) || project.getProjectmanager().equals(user)) ) {
			throw new OperationNotAllowedException("Must be projectmanager to edit another developers activity", "Edit activity"); 
		} else { 
			if (duration % 0.5 != 0 && duration != 0){
				throw new OperationNotAllowedException("Activity duration must be divisable by 0.5 AND not 0", "Set duration");
			}
		}
		
		this.duration = duration;
	}
	
	public User getDeveloper(){
		return this.developer;
	}
	public void setDeveloper(User developer){
		this.developer = developer;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	// Jose: inserted OperationNotAllowedException, when parse error.
	public void setDate(String date) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		
		try {
			Date strToDate = format.parse(date);
			this.date = strToDate;
		} catch (Exception e){
			throw new OperationNotAllowedException("Date must have the format yyyy-MM-dd", "Set date");	
		}
	}
	public Integer getId(){
		return this.id;
	} 
	
}
