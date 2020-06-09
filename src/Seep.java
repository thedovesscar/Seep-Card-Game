
public class Seep {

	private static volatile Seep instance = null;
	
	public static int startingPlayer;
	public static int currentPlayer;
	private int[] playerList;
	public Hand[] hand;
	public Table table;
	private Deck deck;
	
	GameplayPanel2 gameviewPanel;
	UserPanel userPanel;
	
	private Seep() {
		if (instance != null) {
			throw new RuntimeException("Use getInstance() method "
					+ " to create SingletonExample instance");
		}
		
		startingPlayer = 0;
		currentPlayer = startingPlayer;
		deck = Deck.getInstance();
		playerList = new int[4];
		
		hand = new Hand[4];
		for (int i = 0; i < 4; i++) {
			hand[i] =  new Hand();
		}
		
		
		table = new Table();
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
		for(Hand h : hand) {
			if (h.getCardCount() > 0) {
				h.clear();
			}
		}
		
		table.clear();
		deck.shuffle();
		
		for (int i = 0; i < 4; i++) {
			table.addCard(deck.dealCard());
		} //end of Table dealing loop

		gameviewPanel = GameplayPanel2.getInstance();
		gameviewPanel.setupTable();
		
		for (int i = 0; i < 4; i++) {
			hand[startingPlayer].addCard(deck.dealCard());
		} 

		
//		else userPanel.dealCards(false);
		
		boolean hasFaceCard = hand[startingPlayer].hasFaceCard();
		
		if (hasFaceCard) {
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j< 4; j++) {
					if (startingPlayer == j) continue;
					else {
						hand[j].addCard(deck.dealCard());
					}
				}
			}
			for (Hand h: hand) {
				h.sortBySuit();
				h.sortByValue();
			}
			
			//checking to see if user has 4 before dealing to user
			userPanel = UserPanel.getInstance();
			if (startingPlayer == 0) {
			userPanel.dealCards(true);
			} else userPanel.dealCards(false);
			for (int i = 1; i < 4; i++) {
				if(startingPlayer == i) {
					gameviewPanel.deal(i, true);
				} else gameviewPanel.deal(i, false);
			}
		} // end if Player has Valid faceCard
		
		else System.out.println("There were no Face Cards");
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
