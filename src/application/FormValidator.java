package application;

import javafx.scene.control.TextField;

public class FormValidator {
	
	public InputValidator validCarPrice(TextField input) {
		return checkForValidPositiveNumber(input);
	}
	
	public InputValidator validDownPayment(TextField input) {
		return checkForValidPositiveNumber(input);
	}
	
	public InputValidator validInterestRate(TextField input) {
		return checkForValidPositiveNumber(input);
	}
	
	public InputValidator validNumberOfMonths(TextField input) {
		InputValidator validator = checkForValidPositiveNumber(input);
		
		if (validator.isValid() && !input.getText().trim().isEmpty()) {
			Double number = Double.parseDouble(input.getText().trim());
			if(number < 1 || number > 85) {
				return new InputValidator(false, "Please enter a time period between 1 and 84 months");
			} else return new InputValidator(true);
		
		} else return validator;
	}
	
	
	private InputValidator checkForValidPositiveNumber(TextField input) {
		
		boolean valid = true;
		String message = "";
		
		String str = input.getText().trim();
		
		if (!str.isEmpty() && !Calculator.isNumeric(str)) {
			valid = false;
			message = "Please enter a valid number.";
		}
		
		return new InputValidator(valid, message);
	}
}
