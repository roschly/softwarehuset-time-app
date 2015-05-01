package sh.app;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateObject {
	private Date startDate;
	private Date endDate;
	
	// TODO: Is throwing an exception here enough, or should it have a try/catch??
	public DateObject(String startDate, String endDate) throws Exception{
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	// Jose: inserted OperationNotAllowedException, when parse error.
	// inserted format.setLenient(false) --> only YYYY-ww format accepted (longer - only first part read e.g 2014-01-01 = 2014-01)
	
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
	
	// Jose: inserted OperationNotAllowedException, when parse error.
	// inserted format.setLenient(false) --> only YYYY-ww format accepted (longer - only first part read e.g 2014-01-01 = 2014-01
	//  Swithced dates in if-statement
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
