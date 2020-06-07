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
	
	/**
	 * This card's Suit
	 */
	private final String cardSuit;
	
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
	private static int counter = 0;

	/**
	 * Card default Constructor - will create a Joker card
	 */
	Card () {
		cardCounter = 0;
		cardName = "Common Joker";
		cardSuit = "Suitless";
		cardNumberString = "Joker";
		cardPoints = 0;
		cardValue = 0;
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
	Card(String nameGiven, String suitGiven, String numberStringGiven, int pointsGiven, int valueGiven)   {
		counter++; 
		cardCounter = counter;
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
	 * @return the cardSuit
	 */
	public String getCardSuit() {
		return cardSuit;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Card Name: "+ cardName + "--- Card Points in Seep: "+ cardPoints;
	}

}
