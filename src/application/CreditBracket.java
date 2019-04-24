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
	
	public String getDescription() {
		String newCarInterestRateString = String.format("%.2f", this.averageRateNew).concat("%");
		String usedCarInterestRateString = String.format("%.2f", this.averageRateUsed).concat("%");
		
		String description = "Based on your score, the average rate is "
								.concat(newCarInterestRateString)
								.concat(" (new) or ")
								.concat(usedCarInterestRateString)
								.concat("  (used).");
		
		if (this.name.toLowerCase().equals("deep subprime")) {
			description = description.concat(" Consider buying an inexpensive used car and refinancing in 6-12 months.");
		}
		
		return description;
	}
	
	
}
