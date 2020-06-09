import sun.tools.jar.resources.jar;

public class Seep {

	private static volatile Seep instance = null;
	
	public static int startingPlayer;
	public static int currentPlayer;
	private int[] playerList;
	public Hand[] hand;
	public Table table;
	private Deck deck;
	private Team[] team;
	
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
		
		team = new Team[2];
		team[0] = new Team(); //User and P2
		team[1] = new Team(); //P1 and P3;
		
		table = Table.getInstance();
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
			
			//checking to see if user is ASKing before dealing to user
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
		
		
		// TODO need to throw exception and end the game.. to reshuffle and start again
		else System.out.println("There were no Face Cards");
	} //end of dealCards()
	
	
	public void firstTurn() {
		
		Card chosenCard = new Card();
		int chosenNumber = 0;
		Hand h = hand[startingPlayer];
		for (int i = 0; i < 4; i++) {
			if (h.getCard(i).getCardNumber() >= 9) {
				chosenCard = h.getCard(i);
				chosenNumber = chosenCard.getCardNumber();
				System.out.println("Card chosen was" + h.getCard(i));
				break;
			}
			else System.out.println("Card not chosen was" + h.getCard(i));
		} //end of for
		
		for (int i = 0; i < table.getCardCount(); i++) {
			
			if ( chosenNumber == table.getCard(i).getCardNumber()) {
				Card c = table.getCard(i);
				System.out.println("found it!!" + chosenCard + "  " + c);
				table.removeCard(c);
				hand[startingPlayer].removeCard(chosenCard);
				team[startingPlayer%2].addCard(c);
				team[startingPlayer%2].addCard(chosenCard);
				System.out.println("Cards " +  c +" and "+ chosenCard + " are in the stash");
				gameviewPanel = GameplayPanel2.getInstance();
				gameviewPanel.redrawTable();
				userPanel = UserPanel.getInstance();
				userPanel.dealCards(true);
			}
			
			else System.out.println("There was not a same card to pick up");
			
		}
		
		
		
		
		
		
		
	}
	
	/**
	 * this was just to demo test if the shuffling was working for the cards
	 */
	public void display() {
		int count = hand[0].getCardCount();
		int i = 0;
		for (i = 0; i < count; i++) {
			String cardString = "Card " + (i+1) + " is " + hand[0].getCard(i);
			System.out.println(cardString);
		} //end of For Loop
	} //test Display()
	
} //end of Seep class
