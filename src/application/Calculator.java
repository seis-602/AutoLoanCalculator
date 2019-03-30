package application;

import javafx.scene.control.TextField;

/*
 * Auto Loan Payment formula (amortization loan)
 * Use the formula A= P * ( r * (1+r)^{n} ) / ( (1+r)^{n} - 1 ).
 * A = the monthly payment.
 * P = the principal
 * r = the interest rate per month, which equals the annual interest rate divided by 12
 * n = the total number of months
 */

public class Calculator {
	
	private final int numberOfMonthsInOneYear = 12;

	public double computeMonthlyPayment(TextField carPriceInputField, TextField downPaymentInputField, TextField interestRateInputField, TextField numberOfMonthsInputField)
	{
		String carPriceString = carPriceInputField.getText().trim();
		String downPaymentString = downPaymentInputField.getText().trim();
		String interestRateString = interestRateInputField.getText().trim();
		String numberOfMonthsString = numberOfMonthsInputField.getText().trim();
		
		double carPrice = 0;
		double downPayment = 0;
		double annualInterestRate = 0;
		int numberOfMonths = 0;
				
		if (!carPriceString.isEmpty() && isNumeric(carPriceString)) {
			carPrice = Double.parseDouble(carPriceInputField.getText().trim());
		}
		
		if (!downPaymentString.isEmpty() && isNumeric(downPaymentString)) {
			downPayment = Double.parseDouble(downPaymentInputField.getText().trim());
		}
		
		if (!interestRateString.isEmpty() && isNumeric(interestRateString)) {
			annualInterestRate = Double.parseDouble(interestRateInputField.getText().trim()) / 100;
		}
		
		if (!numberOfMonthsString.isEmpty() && isNumeric(numberOfMonthsString)) {
			numberOfMonths = Integer.parseInt(numberOfMonthsInputField.getText().trim());
		}

		double paymentDue = carPrice - downPayment;
		double interestRatePerMonth = annualInterestRate / numberOfMonthsInOneYear;
		
		double monthlyPayment  = 0;
		
		if (carPrice != 0 && numberOfMonths != 0) {
			if (annualInterestRate == 0) {
				monthlyPayment = paymentDue / numberOfMonths;
			} else {
				monthlyPayment = paymentDue * ( interestRatePerMonth * Math.pow(1 + interestRatePerMonth, numberOfMonths) ) / ( Math.pow(1 + interestRatePerMonth, numberOfMonths) - 1 );	
			}
		}
		
		return monthlyPayment;
			
	}
	
	
	public double computeTotalAmountPaid(TextField carPriceInputField, TextField downPaymentInputField, TextField interestRateInputField, TextField numberOfMonthsInputField)
	{
		String numberOfMonthsString = numberOfMonthsInputField.getText().trim();
		
		double monthlyPayment = this.computeMonthlyPayment(carPriceInputField, downPaymentInputField, interestRateInputField, numberOfMonthsInputField);
		
		if (!numberOfMonthsString.isEmpty() && isNumeric(numberOfMonthsString)) {
			int numberOfMonths = Integer.parseInt(numberOfMonthsInputField.getText().trim());
			return monthlyPayment * numberOfMonths;
		} else return 0;
	}
	
	
	public double computeTotalInterestPaid(TextField carPriceInputField, TextField downPaymentInputField, TextField interestRateInputField, TextField numberOfMonthsInputField)
	{
		String carPriceString = carPriceInputField.getText().trim();
		String downPaymentString = downPaymentInputField.getText().trim();
		
		double carPrice = 0;
		double downPayment = 0;
		
		double totalAmountPaid = this.computeTotalAmountPaid(carPriceInputField, downPaymentInputField, interestRateInputField, numberOfMonthsInputField);
		
		if (!carPriceString.isEmpty() && isNumeric(carPriceString)) {
			carPrice = Double.parseDouble(carPriceInputField.getText().trim());
		}
		
		if (!downPaymentString.isEmpty() && isNumeric(downPaymentString)) {
			downPayment = Double.parseDouble(downPaymentInputField.getText().trim());
		}
		
		if (totalAmountPaid != 0) {
			return totalAmountPaid - (carPrice - downPayment);
		}
		
		return 0;
	}
	
	
	private boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
}
