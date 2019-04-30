package application;

import java.net.URL;
import java.text.DecimalFormat;
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
	private Label monthlyPaymentResultLabel;
	@FXML
	private Label lastPaymentResultLabel;
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
		setupActions();
	}
	
	
	void setupActions() {
		setupCreditComponent();
		bindValidateInputs();
		bindFields();
	}
	
	
	public void setupCreditComponent() {
		// bind itemsComboBox with data
		creditBracketmodel.loadData();
		creditBracketComboBox.setItems(creditBracketmodel.itemsObservableList);
		
		//attach listener to creditBracketComboBox
		creditBracketComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (creditBracketComboBox.getSelectionModel().getSelectedIndex() >= 0) {
				String creditBracketDescription = newValue.getDescription();
				creditBracketDescriptionLabel.setText(creditBracketDescription);
			}
		});
	}
	
	
	public void bindValidateInputs() {
		formValidator.checkPositiveDouble2(carPriceInputField);
		formValidator.checkPositiveDouble2(downPaymentInputField);
		formValidator.checkPositiveDouble2(interestRateInputField);
		formValidator.checkMonthRange(numberOfMonthsInputField);
	}
	
	
	private void bindFields() {
    	DoubleBinding monthlyPaymentBinding = new DoubleBinding() {
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
    			
    			String carPriceString = carPriceInputField.getText().trim();
    			String downPaymentString = downPaymentInputField.getText().trim();
    			String interestRateString = interestRateInputField.getText().trim();
    			String numberOfMonthsString = numberOfMonthsInputField.getText().trim();
    			
    			if (!carPriceString.isEmpty() && !downPaymentString.isEmpty() && !interestRateString.isEmpty() && !numberOfMonthsString.isEmpty()) {
    				try {
    					double carPrice = Double.parseDouble(carPriceString);
    					double tradeInDP = Double.parseDouble(downPaymentString);
    					double principal = carPrice - tradeInDP;
    					double interestRate = Double.parseDouble(interestRateString);
    					int numberOfMonths = Integer.parseInt(numberOfMonthsString);
    				
    					if(numberOfMonths < 85) {
    						double monthlyPayment = calculator.calculateMonthlyPayment(principal, interestRate, numberOfMonths);
    						double totalAmountPaid = totalAmountPaid(monthlyPayment, numberOfMonths);
    						totalInterestPaid(principal, numberOfMonths, monthlyPayment);
    						setLastPayment(totalAmountPaid, numberOfMonths, monthlyPayment);
    						return monthlyPayment;
    					} else
    						clearFields(); 
    						return 0;
    				} catch(NumberFormatException e) {
    					e.printStackTrace();
    					clearFields();
    				}
    			} else {
    				clearFields();
    			};
    			
    			return 0;
    		}
    	};
    	
    	monthlyPaymentResultLabel.textProperty().bind(Bindings.format("$%.2f", monthlyPaymentBinding));
    }
    
    private double totalAmountPaid(Double monthlyPayment, Integer numberOfMonths) {
    	String totalAmountPaid = calculator.calculateTotalAmountPaid(monthlyPayment, numberOfMonths);
    	totalAmountPaidResultLabel.setText("$" + totalAmountPaid);
    	return Double.parseDouble(totalAmountPaid);
   }
 
    private void totalInterestPaid(Double principal, Integer numberOfMonths, Double monthlyPayment) {
    	String totalInterestPaid = calculator.calculateTotalInterestPaid(principal, numberOfMonths, monthlyPayment);
    	totalInterestPaidResultLabel.setText("$" + totalInterestPaid);
    }
    
    private void setLastPayment(Double totalAmountPaid, Integer numberOfMonths, Double monthlyPayment) {
    	String lastPayment = calculator.calculateLastPayment(totalAmountPaid, monthlyPayment, numberOfMonths);
    	DecimalFormat df = new DecimalFormat("#.00"); 
    	
    	if (!lastPayment.equals(df.format(monthlyPayment))) {
    		lastPaymentResultLabel.setText("with the last month payment of $" + lastPayment);
    	} else lastPaymentResultLabel.setText("");
    }
    
    private void clearFields() {
    	totalInterestPaidResultLabel.setText("");
    	totalAmountPaidResultLabel.setText("");
    	lastPaymentResultLabel.setText("");
    }
}
