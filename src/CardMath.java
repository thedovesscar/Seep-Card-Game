import jdk.internal.dynalink.beans.StaticClass;

public class CardMath {

	private static Card tempCard;
	static Seep gameSeep = Seep.getInstance();
	static Table table = Table.getInstance();
	
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
	
	static void checkForCombo(Card c) {
		
	}

}
