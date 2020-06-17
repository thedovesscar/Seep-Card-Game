package com.gurmehardev.cardgame;

/**
 * This class contains all the private static fields that contain
 * the value of each card instantiated.
 * 
 * @author Gurmehar
 *
 */
public class Card {

	/**
	 * This card's full Name: !###! of !Suit!
	 */
	private final String cardName;
	
	private final boolean buildable;
	
	public final static int SPADE = 0;
	public final static int CLOVER = 1;
	public final static int HEART = 2;
	public final static int DIAMOND = 3;
	
	/**
	 * This card's suit as a Int
	 */
	private final int cardSuit;

	/**
	 * This card's Number presented as a Int
	 */
	private final int cardNumber;
	
	/**
	 * This card's Suit as string
	 */
	private final String cardSuitString;
	
	/**
	 * This card's Number presented as a String
	 */
	private final String cardNumberString;
	
	/**
	 * This card's points in a Game (mainly Seep)
	 */
	private final int cardPoints;
	
	/**
	 * This card's counter number (in the order it was instantiated)
	 */
	private final int cardCounter;
	
	public String cardImg;
	private static int counter = 0;

	/**
	 * Card default Constructor - will create a Joker card
	 */
	public Card () {
		cardCounter = 0;
		cardName = "Common Joker";
		cardSuit = 4;
		cardSuitString = "Suitless";
		cardNumberString = "Joker";
		cardPoints = 0;
		cardNumber = 0;
		cardImg = "";
		buildable = false;
	}

	/**
	 * updated from old Constructor so that now only uses 2 Param, both as Int Value
	 * @param number
	 * @param suit
	 */
	Card(int suit, int number) {
		cardSuit = suit;
		cardSuitString = getSuitString(suit);
		cardNumber = number;
		cardNumberString = getNumberString(number);
		cardPoints = getCardPoints(suit, number);
		cardName = cardNumberString + " of " + cardSuitString + "s";
		counter++;
		cardCounter = counter;
		if (number > 8) {
			buildable = true;
		}
		else 
			buildable = false;
	}
	

	private String getSuitString(int suit) {
		switch (suit) {
			case 0: 
				return "Spade";
			case 1: 
				return "Clover";
			case 2: 
				return "Heart"; 
			case 3: 
				return "Diamond";	
			default: 
				return "Not a Valid Card Suit";
		}
					
		
	}



		private String getNumberString(int number) {

			switch (number) {
				case 1:
					return "Ace";
				case 2: 
					return "Two";
				case 3: 
					return "Three";
				case 4: 
					return "Four";
				case 5: 
					return "Five";
				case 6: 
					return "Six"; 
				case 7: 
					return "Seven";
				case 8: 
					return "Eight";
				case 9: 
					return "Nine";
				case 10:
					return "Ten";
				case 11: 
					return "Jack";
				case 12: 
					return "Queen";
				case 13: 
					return "King";
				default: 
					return "Not a valid card Number"; 
			} 
		}

		private int getCardPoints ( int suit, int number){
		if (suit == Card.SPADE) {
		return number;
		} // end of if SPADE
		 else if (number == 1) {
		return 1;
		} 
		else if (suit == Card.DIAMOND && number == 10) {
		return 2;
		}
		else return 0;

		}

	/**
	 * @return the cardName
	 */
	public String getCardName() {
		return cardName;
	}

	public int getCardSuit() {
		return cardSuit;
	}
	
	/**
	 * @return the cardSuit as a String
	 */
	public String getCardSuitString() {
		return cardSuitString;
	}


	/**
	 * @return the cardNumberString
	 */
	public String getCardNumberString() {
		return cardNumberString;
	}


	/**
	 * @return the card's points for a game of Seep
	 */
	public int getCardPoints() {
		return cardPoints;
	}

	/**
	 * @return the cardValue
	 */
	public int getCardNumber() {
		return cardNumber;
	}

	public boolean isBuildable() {
		return buildable;
	}

	/**
	 * @return the cardCount
	 */
	public int getCardCounter() {
		return cardCounter;
	}
	
	public String getCardImg() {
		return cardImg;
	}
	
	public void setCardImg(String cardImg) {
		this.cardImg = cardImg;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return cardName;
	}
	
}
