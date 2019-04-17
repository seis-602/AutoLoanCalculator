package application;

public interface Validator {
	
	public boolean isValid();
	
	public void setValid(boolean valid);
	
	public String getErrorMessage();
	
	public void setErrorMessage(String message);
}
