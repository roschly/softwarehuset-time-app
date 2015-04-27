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
	
	public void setEndDate(String endDate) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("YYYY-ww");
		
		try {
			Date strToDate = format.parse(endDate);
			
			if (strToDate.before( this.getStartDate() )){
				throw new OperationNotAllowedException("End date must be after start date", "Set end date");
			}
			
			this.endDate = strToDate;
		} catch (Exception e){
			// TODO: Parse exception			
		}
	}

}
