
public class Card {

	private String cardName;
	private String cardSuit;
	private String cardNumberString;
	private int cardPoints;
	private int cardValue;
	private int cardCount;
	private static int count = 0;


	Card () {
		cardCount = 0;
		cardName = "Common Joker";
		cardSuit = "Suitless";
		cardNumberString = "Joker";
		cardPoints = 0;
		cardValue = 0;
	}
	
	Card(String nameGiven, String suitGiven, String numberStringGiven, int pointsGiven, int valueGiven)   {
		count++; 
		cardCount = count;
		cardName = nameGiven;
		cardSuit = suitGiven;
		cardNumberString = numberStringGiven;
		cardPoints = pointsGiven;
		cardValue = valueGiven;
	}

	/**
	 * @return the cardName
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * @param cardName to set Name of card
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	/**
	 * @return the cardSuit
	 */
	public String getCardSuit() {
		return cardSuit;
	}

	/**
	 * @param cardSuit to set the Suit of card
	 */
	public void setCardSuit(String cardSuit) {
		this.cardSuit = cardSuit;
	}

	/**
	 * @return the cardNumberString
	 */
	public String getCardNumberString() {
		return cardNumberString;
	}

	/**
	 * @param cardNumberString to set the Literal card number
	 */
	public void setCardNumberString(String cardNumberString) {
		this.cardNumberString = cardNumberString;
	}

	/**
	 * @return the cardPoints
	 */
	public int getCardPoints() {
		return cardPoints;
	}

	/**
	 * @param cardPoints to set the Points for the given card
	 */
	public void setCardPoints(int cardPoints) {
		this.cardPoints = cardPoints;
	}

	/**
	 * @return the cardValue
	 */
	public int getCardValue() {
		return cardValue;
	}

	/**
	 * @param cardValue to set numeric Value
	 */
	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}

	/**
	 * @return the cardCount
	 */
	public int getCardCount() {
		return cardCount;
	}

	/**
	 * @param cardCount to set the Counter for the created Card object
	 */
	public void setCardCount(int cardCount) {
		this.cardCount = cardCount;
	}
	

}
