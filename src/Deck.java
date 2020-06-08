
/**
 * This class creates a full Deck of cards using Singleton Creation Pattern
 * to ascertain only one deck is made.
 * 
 * @author Gurmehar
 *
 */
public class Deck {

	
	private static volatile Deck instance = null;
	private Card[] deck;
	private int cardsUsed;
	
	
	/**
	 * Constructor for a deck of Cards that only gets called internally
	 */
	private Deck() {
		if (instance != null) {
			throw new RuntimeException("Use getInstance() method "
					+ " to create SingletonExample instance");
		}
		
		deck  = new Card[52];
		int cardsCreated = 0;
		
		while (cardsCreated < 52) {
			for (int i = 1; i <= 13; i++) {
				deck[cardsCreated] = new Card(i +" of Spades", 0, "Spade", i + "", i, i);
				cardsCreated++;
			}
			for (int i = 1; i <= 13; i++) {
				deck[cardsCreated] = new Card(i +" of Hearts", 1,  "Heart", i + "", 0, i);
				cardsCreated++;
			}
			for (int i = 1; i <= 13; i++) {
				deck[cardsCreated] = new Card(i +" of Clovers", 2, "Clover", i + "", 0, i);
				cardsCreated++;
			}
			for (int i = 1; i <= 13; i++) {
				deck[cardsCreated] = new Card(i +" of Diamonds", 3, "Diamond", i + "", 0, i);
				cardsCreated++;
			}
		}
		deck[0].setCardImg("img/AS.png");
		deck[1].setCardImg("img/2S.png");
		deck[2].setCardImg("img/3S.png");
		deck[3].setCardImg("img/4S.png");
		deck[4].setCardImg("img/5S.png");
		deck[5].setCardImg("img/6S.png");
		deck[6].setCardImg("img/7S.png");
		deck[7].setCardImg("img/8S.png");
		deck[8].setCardImg("img/9S.png");
		deck[9].setCardImg("img/10S.png");
		deck[10].setCardImg("img/JS.png");
		deck[11].setCardImg("img/QS.png");
		deck[12].setCardImg("img/KS.png");
		
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

	/**
     * Put all the used cards back into the deck (if any), and
     * shuffle the deck into a random order.
     */
	public void shuffle() {
        for ( int i = deck.length-1; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            Card temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
        cardsUsed = 0;
    }
	
	/**
     * As cards are dealt from the deck, the number of cards left
     * decreases.  This function returns the number of cards that
     * are still left in the deck.  The return value would be
     * 52 or 54 (depending on whether the deck includes Jokers)
     * when the deck is first created or after the deck has been
     * shuffled.  It decreases by 1 each time the dealCard() method
     * is called.
     */
    public int cardsLeft() {
        return deck.length - cardsUsed;
    }
    
    /**
     * Removes the next card from the deck and return it.  It is illegal
     * to call this method if there are no more cards in the deck.  You can
     * check the number of cards remaining by calling the cardsLeft() function.
     * @return the card which is removed from the deck.
     * @throws IllegalStateException if there are no cards left in the deck
     */
    public Card dealCard() {
        if (cardsUsed == deck.length)
            throw new IllegalStateException("No cards are left in the deck.");
        cardsUsed++;
        return deck[cardsUsed - 1];
        // Programming note:  Cards are not literally removed from the array
        // that represents the deck.  We just keep track of how many cards
        // have been used.
    }

	
}
