package sh.app;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateObject {
	private Date startDate;
	private Date endDate;
	
	public DateObject(String startDate, String endDate) throws Exception{
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	public void setStartDate(String startDate) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("YYYY-ww");
		format.setLenient(false);
		
		try {
			Date strToDate = format.parse(startDate);
			this.startDate = strToDate;
		} catch (Exception e){
			throw new OperationNotAllowedException("Date must have the format YYYY-ww", "Set startdate");	
		}
	}
	
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("YYYY-ww");
		format.setLenient(false);
		Date strToDate; 
		
		try {
			strToDate = format.parse(endDate); 			
		} catch (Exception e){
			throw new OperationNotAllowedException("Date must have the format YYYY-ww", "Set enddate");	
		}
		
		if ( strToDate.before(this.getStartDate()) ){
			throw new OperationNotAllowedException("End date must be after start date", "Set enddate");
		} else {
			this.endDate = strToDate;
		}
	}
}
