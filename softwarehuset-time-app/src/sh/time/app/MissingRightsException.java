package sh.time.app;

public class MissingRightsException extends Exception {

	private String operation;
	
	public MissingRightsException(String errorMsg, String operation) {
		super(errorMsg); 
		this.operation = operation; 
	}
	
	public Object getOperation() {
		return this.operation; 
	}
	
	
}