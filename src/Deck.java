
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
				deck[cardsCreated] = new Card(Card.SPADE, i);
				cardsCreated++;
			}
			for (int i = 1; i <= 13; i++) {
				deck[cardsCreated] = new Card(Card.CLOVER, i);
				cardsCreated++;
			}
			for (int i = 1; i <= 13; i++) {
				deck[cardsCreated] = new Card(Card.HEART, i);
				cardsCreated++;
			}
			for (int i = 1; i <= 13; i++) {
				deck[cardsCreated] = new Card(Card.DIAMOND, i);
				cardsCreated++;
			}
		}
		deck[0].setCardImg("images/AS.png");
		deck[1].setCardImg("images/2S.png");
		deck[2].setCardImg("images/3S.png");
		deck[3].setCardImg("images/4S.png");
		deck[4].setCardImg("images/5S.png");
		deck[5].setCardImg("images/6S.png");
		deck[6].setCardImg("images/7S.png");
		deck[7].setCardImg("images/8S.png");
		deck[8].setCardImg("images/9S.png");
		deck[9].setCardImg("images/10S.png");
		deck[10].setCardImg("images/JS.png");
		deck[11].setCardImg("images/QS.png");
		deck[12].setCardImg("images/KS.png");
		deck[13].setCardImg("images/AC.png");
		deck[14].setCardImg("images/2C.png");
		deck[15].setCardImg("images/3C.png");
		deck[16].setCardImg("images/4C.png");
		deck[17].setCardImg("images/5C.png");
		deck[18].setCardImg("images/6C.png");
		deck[19].setCardImg("images/7C.png");
		deck[20].setCardImg("images/8C.png");
		deck[21].setCardImg("images/9C.png");
		deck[22].setCardImg("images/10C.png");
		deck[23].setCardImg("images/JC.png");
		deck[24].setCardImg("images/QC.png");
		deck[25].setCardImg("images/KC.png");
		deck[26].setCardImg("images/AH.png");
		deck[27].setCardImg("images/2H.png");
		deck[28].setCardImg("images/3H.png");
		deck[29].setCardImg("images/4H.png");
		deck[30].setCardImg("images/5H.png");
		deck[31].setCardImg("images/6H.png");
		deck[32].setCardImg("images/7H.png");
		deck[33].setCardImg("images/8H.png");
		deck[34].setCardImg("images/9H.png");
		deck[35].setCardImg("images/10H.png");
		deck[36].setCardImg("images/JH.png");
		deck[37].setCardImg("images/QH.png");
		deck[38].setCardImg("images/KH.png");
		deck[39].setCardImg("images/AD.png");
		deck[40].setCardImg("images/2D.png");
		deck[41].setCardImg("images/3D.png");
		deck[42].setCardImg("images/4D.png");
		deck[43].setCardImg("images/5D.png");
		deck[44].setCardImg("images/6D.png");
		deck[45].setCardImg("images/7D.png");
		deck[46].setCardImg("images/8D.png");
		deck[47].setCardImg("images/9D.png");
		deck[48].setCardImg("images/10D.png");
		deck[49].setCardImg("images/JD.png");
		deck[50].setCardImg("images/QD.png");
		deck[51].setCardImg("images/KD.png");
		
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
