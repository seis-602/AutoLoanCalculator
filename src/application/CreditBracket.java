package application;

public class CreditBracket {
	
	private String name;
	private int max_score;
	private int min_score;
	private double average_rate_new;
	private double average_rate_used;

	CreditBracket (String name, int max_score,  int min_score, double average_rate_new, double average_rate_used) {
		this.name = name;
		this.max_score = max_score;
		this.min_score = min_score;
		this.average_rate_new = average_rate_new;
		this.average_rate_used = average_rate_used;
	}
	
	private String getBracket() {
		return Integer.toString(min_score).concat(" - ").concat(Integer.toString(max_score));
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getAverageUsedCarInterestRate()
	{
		return average_rate_used;
	}
	
	public double getAverageNewCarInterestRate()
	{
		return average_rate_new;
	}
	
	@Override
	public String toString() {  //this is needed for ComboBox
		return name.concat(" (").concat(getBracket()).concat(")");
	}
	
	
}
