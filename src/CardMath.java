import javax.swing.JOptionPane;

import jdk.internal.dynalink.beans.StaticClass;

public class CardMath {

	static Seep gameSeep = Seep.getInstance();
	static Table table = Table.getInstance();
	
	public CardMath() {
		
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
