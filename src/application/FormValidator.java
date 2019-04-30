package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class FormValidator {
	
	public void checkPositiveDouble2(TextField input) {
		
		// add listener on input
		input.textProperty().addListener(new ChangeListener<String>() {
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    	
		    	if (!newValue.matches("\\d*\\.?\\d{0,2}")) {
		    		
		    		// replace with only digits and decimal points
		    		String str = newValue.replaceAll("[^0-9.]", "");
		    		
		    		// split by first decimal point
		    		String[] parts = str.split("\\.", 2);
		    		
		    		// if has decimal value
		    		if (parts.length > 1) {
		    			
		    			String decimals = parts[1].replaceAll("\\.", "");
		    			
		    			// if decimals is more then 2 digits long
		    			if (parts[1].replaceAll("\\.", "").length() >= 2) {
		    				decimals = decimals.substring(0, 2);
		    			}
		    			
 		    			// format string with one decimal point and 2 digit decimals
		    			str = parts[0] + "." + decimals;
		    		}

		    		input.setText(str);
    			}
		    }
		});
	}
	
	public void checkMonthRange(TextField input) {
		
		// add listener
		// Credit: https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
		input.textProperty().addListener(new ChangeListener<String>() {
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        
		    	String str = newValue;
		    	
		    	// if not only numeric values
		    	if (!str.matches("\\d*")) {
		    		str = newValue.replaceAll("[^\\d]", "");
		        }
		    	
		    	// restrict to 1 - 84 months
		    	if (!str.isEmpty()) {
		    		if (Integer.parseInt(str) < 1) {
		        		str = "1";
		        	} else if (Integer.parseInt(str) > 84) {
		        		str = "84";
		        	}
		    	}
	        	
	        	input.setText(str);
		    }
		});
	}
}
