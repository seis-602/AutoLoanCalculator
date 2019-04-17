package application;

public class InputValidator implements Validator {
	
	private boolean valid;
	private String errorMessage;
	
	public InputValidator(boolean valid, String errorMessage) {
		this.valid = valid;
		this.errorMessage = errorMessage;
	}
	
	public InputValidator(boolean valid) {
		this.valid = valid;
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public boolean isValid() {
		return this.valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
