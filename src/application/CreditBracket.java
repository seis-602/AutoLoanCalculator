package application;

public class CreditBracket {
	
	private String name;
	private int maxScore;
	private int minScore;
	private double averageRateNew;
	private double averageRateUsed;

	CreditBracket (String name, int maxScore, int minScore, double averageRateNew, double averageRateUsed) {
		this.name = name;
		this.maxScore = maxScore;
		this.minScore = minScore;
		this.averageRateNew = averageRateNew;
		this.averageRateUsed = averageRateUsed;
	}
	
	private String getBracket() {
		return Integer.toString(minScore).concat(" - ").concat(Integer.toString(maxScore));
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getAverageUsedCarInterestRate()
	{
		return averageRateUsed;
	}
	
	public double getAverageNewCarInterestRate()
	{
		return averageRateNew;
	}
	
	@Override
	public String toString() {  //this is needed for ComboBox
		return name.concat(" (").concat(getBracket()).concat(")");
	}
	
	
}
