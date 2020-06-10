import jdk.internal.dynalink.beans.StaticClass;

public class CardMath {

	static Seep gameSeep = Seep.getInstance();
	static Table table = Table.getInstance();
	
	
	/**
	 * This should be called beginning of turn to check if Seep is possible.
	 * @param card
	 * @return
	 */
	static boolean checkForSeep(Card card) {
	
		int cardNumber = card.getCardNumber();
		int tempInt = 0;
		for (int i = 0; i < table.getCardCount(); i++) {
			tempInt += table.getCard(i).getCardNumber();
		}
		int tableAVGvalue = tempInt/table.getCardCount();
		
		if (tableAVGvalue == cardNumber) return true;
		else return false;
	}
	
	
	/**
	 *  Should be called when there are 3 Stacks
	 * @param Card c 
	 */
	static void checkForCombo(Card c) {
		
	}
	
	/**
	 * Idk if it's necessary to keep this.
	 */
	static void checkForSame () {
		
	}

}
