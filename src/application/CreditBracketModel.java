package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CreditBracketModel {
	
	ObservableList<CreditBracket> itemsObservableList = FXCollections.observableArrayList();
	
	void loadData() {
		try (BufferedReader br = new BufferedReader(new FileReader("data/CreditBracketMaster.csv"))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				
				CreditBracket bracket = new CreditBracket(
							values[0],
							Integer.parseInt(values[1]),
							Integer.parseInt(values[2]),
							Double.parseDouble(values[3]), 
							Double.parseDouble(values[4])
						);
				
				itemsObservableList.add(bracket);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
