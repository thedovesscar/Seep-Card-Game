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
	
	
	public final static int SPADE = 0;
	public final static int CLOVER = 1;
	public final static int HEART = 2;
	public final static int DIAMOND = 3;
	
	private final int cardSuit;
	/**
	 * This card's Suit
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
	 * This card's Number presented as a Int
	 */
	private final int cardValue;
	
	/**
	 * This card's counter number (in the order it was instantiated)
	 */
	private final int cardCounter;
	
	public String cardImg;
	private static int counter = 0;

	/**
	 * Card default Constructor - will create a Joker card
	 */
	Card () {
		cardCounter = 0;
		cardName = "Common Joker";
		cardSuit = 4;
		cardSuitString = "Suitless";
		cardNumberString = "Joker";
		cardPoints = 0;
		cardValue = 0;
		cardImg = "";
	}
	/**
	 * Construct a Card Object
	 * 
	 * @param nameGiven "Ace of Spades"
	 * @param suitGiven	"Spades"
	 * @param numberStringGiven "Ace"
	 * @param pointsGiven "1"
	 * @param valueGiven "1"
	 */
	Card(String nameGiven, int suitGiven, String suitStringGiven, String numberStringGiven, int pointsGiven, int valueGiven)   {
		counter++; 
		cardCounter = counter;
		cardName = nameGiven;
		cardSuit = suitGiven;
		cardSuitString = suitStringGiven;
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
	public int getCardValue() {
		return cardValue;
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
		return ""+ cardName + " --- Card Points in Seep: "+ cardPoints;
	}
	
}
