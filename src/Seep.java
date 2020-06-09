
public class Seep {

	private static volatile Seep instance = null;
	
	private int currentPlayer;
	private int[] playerList;
	public Hand[] hand;
	public Table table;
	private Stash[] stash;
	private Deck deck;
	
	private Seep() {
		if (instance != null) {
			throw new RuntimeException("Use getInstance() method "
					+ " to create SingletonExample instance");
		}
		
		currentPlayer = 0;
		deck = Deck.getInstance();
		playerList = new int[4];
		
		hand = new Hand[4];
		for (int i = 0; i < 4; i++) {
			hand[i] =  new Hand();
		}
		
		stash = new Stash[2];
		for (int i = 0; i < 2; i++) {
			stash[i] =  new Stash();
		}
		
		table = new Table();
		
		dealCards();
	} //end of Seep Constructor
	
	public static Seep getInstance() {
		if (instance == null) {
			synchronized (Seep.class) {
				if (instance == null) {
					instance = new Seep();
				}
			}
		}
		return instance;
	}
	public void dealCards() {
		deck.shuffle();
		
		for (int i = 0; i < 4; i++) {
			table.addCard(deck.dealCard());
		} //end of Table dealing loop
		
		while (deck.cardsLeft() > 0) {
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 12; j++) {
					hand[i].addCard(deck.dealCard());
				} // end of for.inner loop
				hand[i].sortBySuit();
				hand[i].sortByValue();
			} // end of for.outer loop
		} //end of While
		
	} //end of dealCards()
	
	public void display() {
		int count = hand[0].getCardCount();
		int i = 0;
		for (i = 0; i < count; i++) {
			String cardString = "Card " + (i+1) + " is " + hand[0].getCard(i);
			System.out.println(cardString);
		} //end of For Loop
	} //test Display()
	
} //end of Seep class
