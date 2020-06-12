import javax.swing.JOptionPane;


public class CardMath {

	static Seep gameSeep = Seep.getInstance();
	static Table table = Table.getInstance();
	static Card handCard = new Card();
	static Card tableCard = new Card();
	public CardMath() {
		
	}
	
	/**
	 * This method should check generically if seep is possible
	 * and should return the value that will allow the seep to occur
	 * then method to check if user has the card value to commit the seep
	 * @return
	 */
	static int checkForSeep() {
		return 0; //TODO
	}
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
	static boolean checkForSame (int cardNum) {
		
		for (int ii = 0; ii < table.getCardCount(); ii++) {
			if (table.getCard(ii).getCardNumber() == cardNum) 
				return true;
		}
		return false;
	}
	
	static Card getSameCard(Card card) {
		Card chosenCard = card;
		for (int ii = 0; ii < table.getCardCount(); ii++) {
			if (table.getCard(ii).getCardNumber() == card.getCardNumber()) {
				chosenCard = table.getCard(ii);
				return chosenCard;
			}
		}
		
		return chosenCard;
	}
	
	
	static Card findAskingCard(Hand hand) {
		Card chosenCard;
		Card tempCard = new Card();
		for (int i = 3; i >= 0; i--) {
			Card c = hand.getCard(i);
			if (c.getCardNumber() >= 9) {
				if (c.getCardSuit() != Card.SPADE) {
					chosenCard = c;
					JOptionPane.showMessageDialog(null, "Player is asking for " + chosenCard.getCardNumberString());
					return chosenCard;
				}
				else  {
					 tempCard = c;
				}
			}
		} //end of for searching for Card to ask.
		JOptionPane.showMessageDialog(null, "Player is asking for " + tempCard.getCardNumberString());
		return tempCard;
	}
	
	static boolean isAskingCardonTable(Card card) {
		for (int i = 0; i < table.getCardCount(); i++) {
			if (card.getCardNumber() == table.getCard(i).getCardNumber()) {
				return true;
			}
		}
		return false;
	}

	static boolean checkTableforCard(Hand hand) {
		for (int ii = 0; ii < table.getCardCount(); ii++) {
			for (int jj = 0; jj < hand.getCardCount(); jj++) {
				if (table.getCard(ii).getCardNumber() == hand.getCard(jj).getCardNumber()) {
					handCard = hand.getCard(jj);
					tableCard = table.getCard(ii);
					return true;
				}
					
			}
		}
		return false;
	}
	/**
	 * This is similar to the regular checkForSpadeVersion(hand, card)
	 * excpet this should be called only when it is the asking players turn;
	 * @param hand
	 * @param card
	 * @param asking
	 * @return
	 */
	
	/**
	 * This is simple test for which card to throw down
	 * will throw lowest card that isnt a spade
	 * and then throw lowest card if all spades
	 * @param hand
	 * @return
	 */
	static Card throwDownCard(Hand hand) {
		
		for(int ii = 0; ii < hand.getCardCount(); ii++) {
			if ( hand.getCard(ii).getCardSuit() != Card.SPADE)
				return hand.getCard(ii);
		}
		
		return hand.getCard(0);
	}
	
	
 	static Card checkForSpadeVersion(Hand hand, Card card, boolean asking) {
		Card cardFound = card;
		int cardNum = card.getCardNumber();
		
		for (int i = 0; i < 4; i++) {
			if (hand.getCard(i).getCardNumber() == cardNum) {
				if(hand.getCard(i).getCardSuit() == Card.SPADE) {
					cardFound = hand.getCard(i);
				}
			}
		}
		return cardFound;
	}
	
	/**
	 * This Method is to be called when Card is found on Table 
	 * and player is about to pick it up. 
	 * THis checks player hand to see if they havec the spade 
	 * to pic up the points
	 * @param hand
	 * @param card
	 * @return
	 */
	static Card checkForSpadeVersion(Hand hand, Card card) {
		Card cardFound = card;
		int cardNum = card.getCardNumber();
		
		int handSize = hand.getCardCount();
		
		for (int i = 0; i < handSize; i++) {
			if (hand.getCard(i).getCardNumber() == cardNum) {
				if(hand.getCard(i).getCardSuit() == Card.SPADE) {
					cardFound = hand.getCard(i);
				}
			}
		}
		
		
		return cardFound;
	}
	
}
