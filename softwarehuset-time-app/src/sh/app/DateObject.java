package sh.app;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateObject {
	private Date startDate;
	private Date endDate;
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	public void setStartDate(String startDate) throws Exception{
		
		SimpleDateFormat format = new SimpleDateFormat("YYYY-ww");
		try {
			Date strToDate = format.parse(startDate);
			this.startDate = strToDate;
		} catch (Exception e){
			// TODO: Parse exception	
		}
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		SimpleDateFormat format = new SimpleDateFormat("YYYY-ww");
		
		try {
			Date strToDate = format.parse(endDate);
			
			if (strToDate.before( this.getStartDate() )){
				// Throw exception
			}
			
			this.endDate = strToDate;
		} catch (Exception e){
			// TODO: Parse exception			
		}
	}

}
