package application;

import java.text.DecimalFormat;

public class Calculator {
	
	private Double monthlyPayment;
	private DecimalFormat df = new DecimalFormat("#0.00");
	
	public Double calculateMonthlyPayment(Double principal, Double interestRate,  Integer numberOfMonths) {
		
		Double monthlyRate;
		
		//Calculate monthly payment
		if(interestRate != 0 && numberOfMonths !=0) {
			interestRate /= 100;
			monthlyRate = interestRate / 12;
			monthlyPayment = (principal * monthlyRate)/((1-Math.pow(1+monthlyRate, -numberOfMonths)));
		} else if(interestRate == 0 && numberOfMonths != 0) {
			monthlyPayment = principal / numberOfMonths;
		} else monthlyPayment = 0.0;
		
		return monthlyPayment;
	}
	
	public String calculateTotalInterestPaid(Double principal, Integer numberOfMonths, Double monthlyPayment) {
		
		Double TIP;
		
		if (numberOfMonths >= 1) {
			TIP = (monthlyPayment *= numberOfMonths) - principal;
		} else TIP = 0.0;
		
		String totalInterestPaid = df.format(TIP);
		
		return totalInterestPaid;
	}
	
	public String calculateTotalAmountPaid(Double monthlyPayment, Integer numberOfMonths) {
		
		//Need to add remainder to total amount paid?
		Double TAP = monthlyPayment *= numberOfMonths;
		String totalAmountPaid = df.format(TAP);
		
		return totalAmountPaid;
	}
	
	public String calculateLastPayment(Double totalAmountPaid, Double monthlyPayment, Integer numberOfMonths) {
		String monthlyPaymentString = df.format(monthlyPayment);
		monthlyPayment = Double.parseDouble(monthlyPaymentString);
		
		Double remainder = totalAmountPaid - (monthlyPayment * numberOfMonths);
		monthlyPayment += remainder;
		String lastPayment = df.format(monthlyPayment);
		
		return lastPayment;
	}
	
	// positive numeric only
	public static boolean isNumeric(String str) {
		try {
			Double number = Double.parseDouble(str);
			return number >= 0;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
}
