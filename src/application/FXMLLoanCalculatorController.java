package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLLoanCalculatorController implements Initializable {
	
	private CreditBracketModel creditBracketmodel = new CreditBracketModel();
	private Calculator calculator = new Calculator();
	private FormValidator formValidator = new FormValidator();
	
	
	@FXML
	private ComboBox<CreditBracket> creditBracketComboBox;
	@FXML
	private Label creditBracketDescriptionLabel;
	
	
	@FXML
	private TextField carPriceInputField;
	@FXML
	private TextField downPaymentInputField;
	@FXML
	private TextField interestRateInputField;
	@FXML
	private TextField numberOfMonthsInputField;
	
	
	@FXML
	private Label monthlyPaymentErrorLabel;
	@FXML
	private Label downPaymentErrorLabel;
	@FXML
	private Label interestRateErrorLabel;
	@FXML
	private Label numberOfMonthsErrorLabel;
	
	
	@FXML
	private Label monthlyPaymentResultLabel;
	@FXML
	private Label totalAmountPaidResultLabel;
	@FXML
	private Label totalInterestPaidResultLabel;
	
	
	@FXML
    private URL location;
    @FXML
    private ResourceBundle resources;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Application Window Opened");
		setupActions();
	}
	
	
	void setupActions() {
		
		setupCreditBracket();
		
		bindValidateInputs();
		
		bindMonthlyPayments();
		bindTotalAmountPaid();
		bindTotalInterestPaid();
	}
	
	public void setupCreditBracket() {
		// bind itemsComboBox with data
		creditBracketmodel.loadData();
		creditBracketComboBox.setItems(creditBracketmodel.itemsObservableList);
		
		//attach a listener to creditBracketComboBox
		creditBracketComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			
			if (creditBracketComboBox.getSelectionModel().getSelectedIndex() >= 0) {
				String creditBracketDescription = getCreditBracketDescription(newValue);
				creditBracketDescriptionLabel.setText(creditBracketDescription);
			}
			
		});
	}
	
	private void bindValidateInputs() {
		StringBinding carPriceErrorMessage = new StringBinding() { 
			{
				super.bind(carPriceInputField.textProperty());
			}
			
			@Override
			protected String computeValue() {
				InputValidator inputValidator = formValidator.validPositiveNumber(carPriceInputField);
				
				if(!inputValidator.isValid()) {
					return inputValidator.getErrorMessage();
				} else return "";
			}
		};
		
		StringBinding downPaymentErrorMessage = new StringBinding() { 
			{
				super.bind(downPaymentInputField.textProperty());
			}
			
			@Override
			protected String computeValue() {
				InputValidator inputValidator = formValidator.validPositiveNumber(downPaymentInputField);
				
				if(!inputValidator.isValid()) {
					return inputValidator.getErrorMessage();
				} else return "";
			}
		};
		
		StringBinding interestRateErrorMessage = new StringBinding() { 
			{
				super.bind(interestRateInputField.textProperty());
			}
			
			@Override
			protected String computeValue() {
				InputValidator inputValidator = formValidator.validPositiveNumber(interestRateInputField);
				
				if(!inputValidator.isValid()) {
					return inputValidator.getErrorMessage();
				} else return "";
			}
		};
		
		StringBinding numberOfMonthsErrorMessage = new StringBinding() { 
			{
				super.bind(numberOfMonthsInputField.textProperty());
			}
			
			@Override
			protected String computeValue() {
				InputValidator inputValidator = formValidator.validNumberOfMonths(numberOfMonthsInputField);
				
				if(!inputValidator.isValid()) {
					return inputValidator.getErrorMessage();
				} else return "";
			}
		};
		
		monthlyPaymentErrorLabel.textProperty().bind(carPriceErrorMessage);
		downPaymentErrorLabel.textProperty().bind(downPaymentErrorMessage);
		interestRateErrorLabel.textProperty().bind(interestRateErrorMessage);
		numberOfMonthsErrorLabel.textProperty().bind(numberOfMonthsErrorMessage);
	}
	
	
	private void bindMonthlyPayments() {
		
		//create monthlyPayment as low level DoubleBinding 
		DoubleBinding monthlyPayment = new DoubleBinding() { 
			{
				super.bind(
						carPriceInputField.textProperty(), 
						downPaymentInputField.textProperty(), 
						interestRateInputField.textProperty(),
						numberOfMonthsInputField.textProperty()
					);
			}
			
			@Override
			protected double computeValue() {
				return calculator.computeMonthlyPayment(carPriceInputField, downPaymentInputField, interestRateInputField, numberOfMonthsInputField);
			}
		};
		
		monthlyPaymentResultLabel.textProperty().bind(Bindings.format("$%.2f", monthlyPayment));
	}
	
	private void bindTotalAmountPaid() {
		
		//create totalAmountPaid as low level DoubleBinding 
		DoubleBinding totalAmountPaid = new DoubleBinding() { 
			{
				super.bind(
						carPriceInputField.textProperty(), 
						downPaymentInputField.textProperty(), 
						interestRateInputField.textProperty(),
						numberOfMonthsInputField.textProperty()
					);
			}
			
			@Override
			protected double computeValue() {
				return calculator.computeTotalAmountPaid(carPriceInputField, downPaymentInputField, interestRateInputField, numberOfMonthsInputField);
			}
		};
		
		totalAmountPaidResultLabel.textProperty().bind(Bindings.format("$%.2f", totalAmountPaid));
	}
	
	private void bindTotalInterestPaid() {
		
		//create totalAmountPaid as low level DoubleBinding 
		DoubleBinding totalInterestPaid = new DoubleBinding() { 
			{
				super.bind(
						carPriceInputField.textProperty(), 
						downPaymentInputField.textProperty(), 
						interestRateInputField.textProperty(),
						numberOfMonthsInputField.textProperty()
					);
			}
			
			@Override
			protected double computeValue() {
				return calculator.computeTotalInterestPaid(carPriceInputField, downPaymentInputField, interestRateInputField, numberOfMonthsInputField);
			}
		};
		
		totalInterestPaidResultLabel.textProperty().bind(Bindings.format("$%.2f", totalInterestPaid));
	}
	
	private String getCreditBracketDescription(CreditBracket creditBracket) {
		
		String newCarInterestRateString = String.format("%.2f", creditBracket.getAverageNewCarInterestRate()).concat("%");
		String usedCarInterestRateString = String.format("%.2f", creditBracket.getAverageUsedCarInterestRate()).concat("%");
		
		String description = "Based on your score, the average rate is "
								.concat(newCarInterestRateString)
								.concat(" (new) or ")
								.concat(usedCarInterestRateString)
								.concat("  (used).");
		
		if(creditBracket.getName().toLowerCase().equals("deep subprime")) {
			description = description.concat(" Consider buying an inexpensive used car and refinancing in 6-12 months.");
		}
		
		return description;
	}
}
