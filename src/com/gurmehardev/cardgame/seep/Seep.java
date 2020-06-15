package com.gurmehardev.cardgame.seep;

import com.gurmehardev.gamepanel.*;
import com.gurmehardev.cardgame.*;
import javax.swing.JOptionPane;


public class Seep {

	private static volatile Seep instance = null;
	
	public static int startingPlayer;
	public static int currentPlayer;
	private int lastPicker;
	private String[] player;
	public Hand[] hand;
	public Table table;
	private Deck deck;
	private Team[] team;
	private int turns;
	
	GameplayPanel2 gameviewPanel;
	UserPanel userPanel;
	
	private Seep() {
		if (instance != null) {
			throw new RuntimeException("Use getInstance() method "
					+ " to create SingletonExample instance");
		}
		
		startingPlayer = 0;
		deck = Deck.getInstance();
		player = new String[]{"You" , "Player 1", "Player 2", "Player 3"};
		turns = 0;
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
	
	/**
	 * called at start of every new game or a restart
	 * will reshuffle and deal cards for a new game
	 */
	public void dealCards() {
		turns = 0;
		currentPlayer = startingPlayer;
		for(Hand h : hand) {
			if (h.getCardCount() > 0) {
				h.clear();
			}
		}
		
		table.clear();
		deck.shuffle();
		
		for (int i = 0; i < 4; i++) {
			hand[startingPlayer].addCard(deck.dealCard());
		} 

		boolean hasFaceCard = hand[startingPlayer].hasFaceCard();
		
		if (hasFaceCard) {
			for (int i = 0; i < 4; i++) {
				table.placeCard(deck.dealCard());
			} //end of Table dealing loop

			gameviewPanel = GameplayPanel2.getInstance();
			gameviewPanel.setupTable();
			
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
					gameviewPanel.deal(i);
				} else gameviewPanel.deal(i);
			}
			
			while (deck.hasCardsLeft()) {
				hand[startingPlayer].addCard(deck.dealCard());
				
			}
		} // end if Player has Valid faceCard
		
		// TODO need to throw exception and end the game.. to reshuffle and start again
		else dealCards();
		
		
		
		
	} //end of dealCards()
	
	public void checkTurn() {
		if (currentPlayer >= 4) {
			currentPlayer = 0;
		}
		if (turns == 0) {
			firstTurn();
		}
		else if (turns >= 47) {
			endGame();
		}
		else {
			playTurn();
			}
		
		turns++;
		currentPlayer++;
	}	
	
	/**
	 * This contains all the initial moves required during the asking phase
	 * TODO needs to be updated for all possible moves.
	 * TODO needs to be updated for what can be selected.
	 */
	public void firstTurn() {
		
		Card chosenCard = new Card();
		Card buildTableCard = new Card();
		Card buildHandCard = new Card();
		int chosenNumber = 0;
		
		chosenCard = CardMath.findAskingCard(hand[startingPlayer]);
		chosenNumber = chosenCard.getCardNumber();
		
//		if (CardMath.checkForSeep(chosenCard)) {
//			doTheSeep(chosenCard);
//			return;
//		}
		
		//checking to see if Card can be built
		if (CardMath.canAskingCardBeBuilt(chosenCard, hand[startingPlayer]) ) {
			buildHandCard = CardMath.handCard;
			buildTableCard = CardMath.tableCard;
			JOptionPane.showMessageDialog(null, "Card can be built!!! " + buildHandCard+  " "+ buildTableCard);
			table.removeCard(buildTableCard);
			hand[startingPlayer].removeCard(buildHandCard);
			updatePanel(startingPlayer);
			
			table.addCard(buildHandCard, chosenNumber);
			table.addCard(buildTableCard, chosenNumber);
			gameviewPanel.redrawTable();
			
			// once card stack is built, checking to see if other single cards
			// add up to the built stack value
			if (CardMath.areThereMoreCombos(chosenNumber)) {
				updatePanel(startingPlayer);
				hand[startingPlayer].sortBySuit();
				hand[startingPlayer].sortByValue();
				gameviewPanel.redrawTable();
			}
			
			updateScores();
			return;
			
		}
		
		// this will pick up if same card is on ground
		else if (CardMath.isAskingCardonTable(chosenCard)) {
			
			table.removeCard(CardMath.tableCard);
			hand[startingPlayer].removeCard(chosenCard);
			updatePanel(startingPlayer);
			gameviewPanel.redrawTable();
			pickupCard(chosenCard);
			pickupCard(CardMath.tableCard);
			updateScores();
			System.out.println(player[startingPlayer]+ " picked up " + chosenCard + " and " + CardMath.tableCard);
			
			if (CardMath.areThereMoreCombos(chosenCard)) {
				updatePanel(startingPlayer);
				hand[startingPlayer].sortBySuit();
				hand[startingPlayer].sortByValue();
				gameviewPanel.redrawTable();
			}
			updateScores();
			return;
			
		}
		
		else {
			
			// this will run when Asking card cannot be built 
			// & the same card is not on the ground
			if (CardMath.areThereMoreCombos(chosenCard)) {
				updatePanel(startingPlayer);
				hand[startingPlayer].sortBySuit();
				hand[startingPlayer].sortByValue();
				gameviewPanel.redrawTable();
				updateScores();
				return;
			}
			
			
			// Last option is throwing down the card with no other action. 
			// NO combo to pick up nor same card to pick up
			hand[startingPlayer].removeCard(chosenCard);
			table.addCard(chosenCard);
			gameviewPanel.redrawTable();
			updatePanel(startingPlayer);
			hand[startingPlayer].sortBySuit();
			hand[startingPlayer].sortByValue();
			JOptionPane.showMessageDialog(null, player[startingPlayer] + " threw down " + chosenCard);
			
		}
			
	} //end of First Turn()
	
	
	public void playTurn() {
		
		Card chosenCard = new Card();
		Card foundCard = new Card();
		
		
		int seepCard = CardMath.checkForSeep();
		
		//checks if player has the card that will result in Seep!
		if (CardMath.hasCardforSeep(seepCard, hand[currentPlayer])) {
			chosenCard = CardMath.handCard;
			JOptionPane.showMessageDialog(null, "WOW! " + player[currentPlayer] 
					+ " can Seep with their " + chosenCard + "!");
			hand[currentPlayer].removeCard(chosenCard);
			pickupAllCards();
			pickupCard(chosenCard);
			updatePanel(currentPlayer);
			gameviewPanel.redrawTable();
			updateScores();
			return;
		}
		//TODO check to build! first
		
		//WIll look to pick up pile
		if (table.getStackCount() > 2 ) {
		// runs this only if there are more than 2 stacks or risk seep!
		
			
			
			
			
			
			
			if (CardMath.checkTableforStack(hand[currentPlayer])) {
			
				chosenCard = CardMath.handCard; 
				chosenCard = CardMath.checkForSpadeVersion(hand[currentPlayer], chosenCard);
			
				hand[currentPlayer].removeCard(chosenCard);
				pickupCard(chosenCard);
				updatePanel(currentPlayer);
				hand[currentPlayer].sortBySuit();
				hand[currentPlayer].sortByValue();
			
				int cardsLeft = CardMath.stackSize;
				while (cardsLeft > 0) {
					System.out.println();
					CardMath.pickupStack(chosenCard);
					foundCard = CardMath.tableCard;
					JOptionPane.showMessageDialog(null, player[currentPlayer] + " picked up " + chosenCard + " and " + foundCard);
					System.out.println(foundCard +" is in the stash");
					gameviewPanel.redrawTable();
					cardsLeft--;
				}
			
				System.out.println(chosenCard +" is in the stash");
				
				if (CardMath.areThereMoreCombos(chosenCard)) {
					updatePanel(startingPlayer);
					hand[startingPlayer].sortBySuit();
					hand[startingPlayer].sortByValue();
					gameviewPanel.redrawTable();
				}
				
				if (CardMath.areThereMoreCombos(chosenCard)) {
					updatePanel(startingPlayer);
					hand[startingPlayer].sortBySuit();
					hand[startingPlayer].sortByValue();
					gameviewPanel.redrawTable();
				}
				
				updateScores();
		
			}	
			
			else {
				
				
				chosenCard = CardMath.throwDownCard(hand[currentPlayer]);
				hand[currentPlayer].removeCard(chosenCard);
				table.addCard(chosenCard);
				gameviewPanel.redrawTable();
				updatePanel(currentPlayer);
				hand[currentPlayer].sortBySuit();
				hand[currentPlayer].sortByValue();
				JOptionPane.showMessageDialog(null, player[currentPlayer] + " threw down " + chosenCard);
				
			}
			
		}
		
		else {
			
			chosenCard = CardMath.throwDownCard(hand[currentPlayer]);
			hand[currentPlayer].removeCard(chosenCard);
			table.addCard(chosenCard);
			gameviewPanel.redrawTable();
			updatePanel(currentPlayer);
			hand[currentPlayer].sortBySuit();
			hand[currentPlayer].sortByValue();
			JOptionPane.showMessageDialog(null, player[currentPlayer] + " threw down " + chosenCard);
			
		}
		
	}
	
	

	public void endGame() {
		System.out.println(team[0].getRealtimeScore() + "  " + team[0].countCards());
		System.out.println(team[1].getRealtimeScore() + "  " + team[1].countCards());
		
		//TODO need to do everything required when ending game. 
		//pick up remaining cards to last pickeruppper.
		//such as resetting cards runtimeScores
		//calculating who will start the game next time
	}
	
	public void doTheSeep(Card card) {
		
	}
	
	public void pickupCard(Card card) {
		team[currentPlayer%2].pickupCard(card);
		lastPicker = currentPlayer;
	}
	
	private void pickupAllCards() {
		Card card = new Card();
		
		for ( int s = 0; s < table.getStackCount(); s++) {
			
			for ( int c = 0; c < table.getStackofCards(s).size(); c++) {
				
				card = table.getStackofCards(s).get(c);
				team[currentPlayer%2].pickupCard(card);
			}
		}
		table.clear();
		team[currentPlayer%2].hitAseep();
		lastPicker = currentPlayer;
	}
	public void clearScores() {
		team[0].newGame();
		team[1].newGame();
		MenuPanel2.addYourScore(0);
		MenuPanel2.addOppScore(0);
	}
	
	public void updateScores() {
		MenuPanel2.addYourScore(team[0].getRealtimeScore());
		MenuPanel2.addOppScore(team[1].getRealtimeScore());
	}
	
	public void updatePanel(int player) {
		if (player==0) {
			userPanel = UserPanel.getInstance();
			userPanel.dealCards(true);
		}
		
		if (player==1) {
			gameviewPanel.deal(1);;
		}
		
		if (player==2) {
			gameviewPanel.deal(2);;
		}
		
		if (player==3) {
			gameviewPanel.deal(3);;
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
