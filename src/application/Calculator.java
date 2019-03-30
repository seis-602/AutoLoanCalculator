package application;

import javafx.scene.control.TextField;

/*
 * NOTES
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
		
		if (!carPriceString.isEmpty() && !downPaymentString.isEmpty() && !interestRateString.isEmpty() && !numberOfMonthsString.isEmpty()) {
			
			double carPrice = Double.parseDouble(carPriceInputField.getText().trim());
			double downPayment = Double.parseDouble(downPaymentInputField.getText().trim());
			double annualInterestRate = Double.parseDouble(interestRateInputField.getText().trim()) / 100;
			int numberOfMonths = Integer.parseInt(numberOfMonthsInputField.getText().trim());
			
			double paymentDue = carPrice - downPayment;
			double interestRatePerMonth = annualInterestRate / numberOfMonthsInOneYear;
			
			double monthlyPayment = paymentDue * ( interestRatePerMonth * Math.pow(1 + interestRatePerMonth, numberOfMonths) ) / ( Math.pow(1 + interestRatePerMonth, numberOfMonths) - 1 );
			
			return monthlyPayment;
			
		} else return 0;
	}
	
	
	public double computeTotalAmountPaid(TextField carPriceInputField, TextField downPaymentInputField, TextField interestRateInputField, TextField numberOfMonthsInputField)
	{
		double monthlyPayment = this.computeMonthlyPayment(carPriceInputField, downPaymentInputField, interestRateInputField, numberOfMonthsInputField);
		
		String numberOfMonthsString = numberOfMonthsInputField.getText().trim();
		
		if (!numberOfMonthsString.isEmpty()) {
			int numberOfMonths = Integer.parseInt(numberOfMonthsInputField.getText().trim());
			return monthlyPayment * numberOfMonths;
		} else return 0;
	}
	
	
	public double computeTotalInterestPaid(TextField carPriceInputField, TextField downPaymentInputField, TextField interestRateInputField, TextField numberOfMonthsInputField)
	{
		double totalAmountPaid = this.computeTotalAmountPaid(carPriceInputField, downPaymentInputField, interestRateInputField, numberOfMonthsInputField);
		
		String carPriceString = carPriceInputField.getText().trim();
		String downPaymentString = downPaymentInputField.getText().trim();
		
		if (!carPriceString.isEmpty() && !downPaymentString.isEmpty()) {

			double carPrice = Double.parseDouble(carPriceInputField.getText().trim());
			double downPayment = Double.parseDouble(downPaymentInputField.getText().trim());
			
			double paymentDue = carPrice - downPayment;
			return totalAmountPaid - paymentDue;
			
		}
		
		return 0;
	}
	
}
