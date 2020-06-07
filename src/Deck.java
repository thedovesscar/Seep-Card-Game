
/**
 * This class creates a full Deck of cards using Singleton Creation Pattern
 * to ascertain only one deck is made.
 * 
 * @author Gurmehar
 *
 */
public class Deck {

	
	private static volatile Deck instance = null;
	private Card[] deckCards;
	private int cardsUsed;
	
	
	/**
	 * Constructor for a deck of Cards that only gets called internally
	 */
	private Deck() {
		if (instance != null) {
			throw new RuntimeException("Use getInstance() method "
					+ " to create SingletonExample instance");
		}
		
		deckCards  = new Card[52];
		int cardsCreated = 0;
		
		while (cardsCreated < 52) {
			for (int i = 1; i <= 13; i++) {
				deckCards[cardsCreated] = new Card(i +" of Spades", "Spade", i + "", i, i);
				cardsCreated++;
			}
			for (int i = 1; i <= 13; i++) {
				deckCards[cardsCreated] = new Card(i +" of Hearts", "Heart", i + "", 0, i);
				cardsCreated++;
			}
			for (int i = 1; i <= 13; i++) {
				deckCards[cardsCreated] = new Card(i +" of Clovers", "Clover", i + "", 0, i);
				cardsCreated++;
			}
			for (int i = 1; i <= 13; i++) {
				deckCards[cardsCreated] = new Card(i +" of Diamonds", "Diamond", i + "", 0, i);
				cardsCreated++;
			}
		}
		
		cardsUsed = 0;
		
	}
	
	
	public static Deck getInstance() {
		if (instance == null) {
			synchronized (Deck.class) {
				if (instance == null) {
					instance = new Deck();
				}
			}
		}
		return instance;
	}

	
	
	
	
	
}
