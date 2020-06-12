package com.gurmehardev.cardgame.seep;

import com.gurmehardev.gamepanel.*;
import com.gurmehardev.cardgame.*;
import javax.swing.JOptionPane;


public class Seep {

	private static volatile Seep instance = null;
	
	public static int startingPlayer;
	public static int currentPlayer;
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
		
		startingPlayer = 3;
		currentPlayer = startingPlayer;
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
	
	public void dealCards() {
		turns = 0;
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
				table.addCard(deck.dealCard());
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
		if (turns == 0) {
			firstTurn();
		}
		else if (turns == 47) {
			endGame();
		}
		else nextTurn();
	}	
	
	/**
	 * This contains all the initial moves required during the asking phase
	 * TODO needs to be updated for all possible moves.
	 * TODO needs to be updated for what can be selected.
	 */
	public void firstTurn() {
		
		Card chosenCard = new Card();
		int chosenNumber = 0;
		
		chosenCard = CardMath.findAskingCard(hand[startingPlayer]);
		chosenNumber = chosenCard.getCardNumber();
		
		if (CardMath.checkForSeep(chosenCard)) {
			doTheSeep(chosenCard);
			return;
		}
		
		
		if (CardMath.isAskingCardonTable(chosenCard)) {
			
			for (int i = 0; i < table.getCardCount(); i++) {
			
				if ( chosenNumber == table.getCard(i).getCardNumber()) {
					Card foundCard = table.getCard(i);
					System.out.println("found it!!" + chosenCard + "  " + foundCard);
					
					table.removeCard(foundCard);
					chosenCard = CardMath.checkForSpadeVersion(hand[startingPlayer], chosenCard, true);
					hand[startingPlayer].removeCard(chosenCard);
				
					team[startingPlayer%2].pickupCard(foundCard);
					team[startingPlayer%2].pickupCard(chosenCard);
					JOptionPane.showMessageDialog(null, player[startingPlayer] + " picked up " + chosenCard + " and " + foundCard);
					System.out.println("Cards " +  foundCard +" and "+ chosenCard + " are in the stash");
				
					gameviewPanel = GameplayPanel2.getInstance();
					gameviewPanel.redrawTable();
				
					updatePanel(startingPlayer);
					hand[startingPlayer].sortBySuit();
					hand[startingPlayer].sortByValue();
					updateScores();

				
				} //TODO this does not pick up all suits of a given Card Number; need to fix and seperate Hand Card Removal and Table Clearance
			}
		}
		
		else {
			hand[startingPlayer].removeCard(chosenCard);
			table.addCard(chosenCard);
			gameviewPanel.redrawTable();
			updatePanel(startingPlayer);
			hand[startingPlayer].sortBySuit();
			hand[startingPlayer].sortByValue();
			JOptionPane.showMessageDialog(null, player[startingPlayer] + " threw down " + chosenCard);
			
		}
			currentPlayer++;
			turns++;
	} //end of First Turn()
	
	public void nextTurn() {
		if (currentPlayer >= 4) {
			currentPlayer = 0;
		}
		playTurn();
		turns++;
	}
	
	public void playTurn() {
		
		Card chosenCard = new Card();
		Card foundCard = new Card();
		if (CardMath.checkTableforCard(hand[currentPlayer])) {
			chosenCard = CardMath.handCard;
			foundCard = CardMath.tableCard;
			table.removeCard(foundCard);
			chosenCard = CardMath.checkForSpadeVersion(hand[currentPlayer], chosenCard);
			hand[currentPlayer].removeCard(chosenCard);
		
			team[currentPlayer%2].pickupCard(foundCard);
			team[currentPlayer%2].pickupCard(chosenCard);
			JOptionPane.showMessageDialog(null, player[currentPlayer] + " picked up " + chosenCard + " and " + foundCard);
			System.out.println("Cards " +  foundCard +" and "+ chosenCard + " are in the stash");
			
			gameviewPanel = GameplayPanel2.getInstance();
			gameviewPanel.redrawTable();
		
			updatePanel(currentPlayer);
			hand[currentPlayer].sortBySuit();
			hand[currentPlayer].sortByValue();
			updateScores();
		
			
		} else {
			chosenCard = CardMath.throwDownCard(hand[currentPlayer]);
			hand[currentPlayer].removeCard(chosenCard);
			table.addCard(chosenCard);
			gameviewPanel.redrawTable();
			updatePanel(currentPlayer);
			hand[currentPlayer].sortBySuit();
			hand[currentPlayer].sortByValue();
			JOptionPane.showMessageDialog(null, player[currentPlayer] + " threw down " + chosenCard);
			
		}
		currentPlayer++;
	}
	public void endGame() {
		
	}
	
	public void doTheSeep(Card card) {
		int cardsOnTable = table.getCardCount();
		Card currentCard;
		for(int i = 0; i < cardsOnTable; i++) {
			currentCard = table.getCard(i);
			team[currentPlayer%2].pickupCard(currentCard);
			table.removeCard(currentCard);
		}
		team[currentPlayer%2].pickupCard(card);
		hand[currentPlayer].removeCard(card);
		updatePanel(currentPlayer);
		gameviewPanel.redrawTable();
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
