package sh.time.app;

import java.util.HashMap;
import java.util.Date;

public class ProjectActivity extends Activity {

	private HashMap<Task, Double> taskList = new HashMap<Task, Double>();
	
	public ProjectActivity(Date date, Developer dev, HashMap<Task, Double> taskList){
		super(date, dev);
		this.taskList = taskList;
	}
	
}
