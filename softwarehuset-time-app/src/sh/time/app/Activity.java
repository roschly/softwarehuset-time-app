package sh.time.app;

public class Activity {
	private static int nextActivityId = 0;
	
	private int id;
	
	public Activity(){
		this.id = Activity.nextActivityId;
		Activity.nextActivityId++;
	}

}
