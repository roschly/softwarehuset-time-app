package sh.time.app;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;


abstract class Activity {
	private static int nextActivityId = 0;
	
	private int id;
	private Date date;
	private Developer developer;
	
	public Activity(Date date, Developer dev){
		this.id = Activity.nextActivityId;
		Activity.nextActivityId++;
		
		this.date = date;
		this.developer = dev;
	}
		
}
