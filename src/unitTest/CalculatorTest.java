package unitTest;

import application.Calculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CalculatorTest {
	
	private Calculator calculator = new Calculator();

	@Test
	void testCalculateMonthlyPayment() {
		double monthlyPayment = calculator.calculateMonthlyPayment(500.00, 0.00, 5);
		assertEquals(monthlyPayment, 100);
	}
	
	@Test
	void testCalculateTotalInterestPaid() {
		String interestPaid = calculator.calculateTotalInterestPaid(1000.00, 2, 550.00);
		assertTrue(interestPaid.equals("100.00"));
	}
	
	@Test
	void testCalculateTotalAmountPaid() {
		String amountPaid = calculator.calculateTotalAmountPaid(500.00, 2);
		assertTrue(amountPaid.equals("1000.00"));
	}
	
	@Test
	void testCalculateLastPayment() {
		String amountPaid = calculator.calculateLastPayment(100.00, 33.33, 3);
		assertTrue(amountPaid.equals("33.34"));
	}

}
