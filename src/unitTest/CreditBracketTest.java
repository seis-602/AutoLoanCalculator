package unitTest;

import application.CreditBracket;
import application.CreditBracketModel;
import javafx.collections.ObservableList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CreditBracketTest {

	@Test
	void testLoadItem() {
		CreditBracketModel creditBracketmodel = new CreditBracketModel();
		creditBracketmodel.loadData();
		
		ObservableList<CreditBracket> items = creditBracketmodel.getObservableList();
		
		assertTrue(items.get(0).getName().equals("Super prime"));
	}
	
	@Test
	void testListSize() {
		CreditBracketModel creditBracketmodel = new CreditBracketModel();
		creditBracketmodel.loadData();
		
		ObservableList<CreditBracket> items = creditBracketmodel.getObservableList();
		
		assertEquals(items.size(), 5);
	}
	
}
