package com.gurmehardev.cardgame.seep;

import com.gurmehardev.gamepanel.*;
import com.gurmehardev.cardgame.*;
import javax.swing.JOptionPane;


public class Seep {

	private static volatile Seep instance = null;
	
	public static int startingPlayer;
	public static int currentPlayer;
	private int lastPicker;
	public String[] player;
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
	 * will reshuffle and deal cards for a new game if 
	 * asking player does not receive face Card
	 * 
	 * Currently does not reshuffle if any other player does
	 * not receive at least one face card 
	 * Haven't come across that issue. 
	 * TODO will need to keep above in MIND
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
		
		else dealCards();
		
		updateScores();
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
		
		int seepCard = CardMath.checkForSeep();
		if (chosenNumber == seepCard) {
			JOptionPane.showMessageDialog(null, "WOW! " + player[currentPlayer] 
						+ " can Seep with their " + chosenCard + "!");
			pickupCard(chosenCard);
			pickupAllCards(currentPlayer);
			finishTurn(currentPlayer);
			return;
				
			
		}
		
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
				
			}
			
			finishTurn(startingPlayer);
			return;
			
		}
		
		// this will pick up if same card is on ground
		else if (CardMath.isAskingCardonTable(chosenCard)) {
			
			table.removeCard(CardMath.tableCard);
			chosenCard = CardMath.checkForSpadeVersion(hand[currentPlayer], chosenCard, true);
			hand[startingPlayer].removeCard(chosenCard);
			pickupCard(chosenCard);
			pickupCard(CardMath.tableCard);
			System.out.println(player[startingPlayer]+ " picked up " + chosenCard + " and " + CardMath.tableCard);
			
			if (CardMath.areThereMoreCombos(chosenCard)) {
				
			}

			CardMath.isThereAnotherCard(chosenCard);
			finishTurn(startingPlayer);
			return;
			
		}
		
		else {
			
			// this will run when Asking card cannot be built 
			// & the same card is not on the ground
			if (CardMath.areThereMoreCombos(chosenCard)) {
				hand[startingPlayer].removeCard(chosenCard);
				finishTurn(startingPlayer);
				return;
			}
			
			
			// Last option is throwing down the card with no other action. 
			// NO combo to pick up nor same card to pick up
			hand[startingPlayer].removeCard(chosenCard);
			table.addCard(chosenCard);
			finishTurn(startingPlayer);
			JOptionPane.showMessageDialog(null, player[startingPlayer] + " threw down " + chosenCard);
			
		}
			
	} //end of First Turn()
	
	/**
	 * This method is called on everyturn except for the first
	 * and except the last ???
	 * TODO  should last turn be with this as well? I 
	 * am starting to think so. maybe endGame should just clear
	 * everything up on its own.
	 */
	public void playTurn() {
		
		for (int i = 0; i < table.getStackCount(); i++) {
			System.out.println("Stack " + table.getStack(i).getStackValue() 
					+ " built by " + table.getStack(i).wasBuiltBy());
		}
		
		Card chosenCard = new Card();
		Card foundCard = new Card();
		
		if (table.getStackCount() != 0) {
			int seepCard = CardMath.checkForSeep();
			
			//checks if player has the card that will result in Seep!
			if (CardMath.hasCardforSeep(seepCard, hand[currentPlayer])) {
				chosenCard = CardMath.handCard;
				JOptionPane.showMessageDialog(null, "WOW! " + player[currentPlayer] 
						+ " can Seep with their " + chosenCard + "!");
				finishTurn(currentPlayer);
				return;
				
			}  //END of Check for SEEP Block
			
		}
		
		if (table.getStackCount() >= 5 ) {
		// runs this only if there are more than 2 stacks or risk seep!
			
			if (CardMath.hasSpadeToPickUp(hand[currentPlayer])) {
				chosenCard = CardMath.handCard;
				if (CardMath.areThereMoreCombos(chosenCard)) {	
				}
				if (CardMath.isThereAnotherCard(chosenCard)) {
				}
				finishTurn(currentPlayer);
				return;
			}
			
			if (CardMath.checkTableforStack(hand[currentPlayer])) {
				chosenCard = CardMath.handCard;
				if (CardMath.areThereMoreCombos(chosenCard)) {	
				}
				if (CardMath.isThereAnotherCard(chosenCard)) {
				}
				finishTurn(currentPlayer);
				return;
			}	
			
			//TODO
			/*
			 * Currently players do not pick up combos of their chosen card
			 * if they don't already exist specifically.
			 * such as player did not pick up 9 and 3 when Q was in hand.
			 * Need to add separate if block for picking up only combos.
			 * 
			 */
			
			//Throw Down Card final Option!
			else {
				
				chosenCard = CardMath.throwDownCard(hand[currentPlayer]);
				hand[currentPlayer].removeCard(chosenCard);
				table.addCard(chosenCard);
				finishTurn(currentPlayer);
				JOptionPane.showMessageDialog(null, player[currentPlayer] + " threw down " + chosenCard);
				return;
			}
			
		} //end of Greater than 2 Stacks if statment block
		
		else if (table.getStackCount() == 4) {
			
			if (CardMath.hasSpadeToPickUp(hand[currentPlayer])) {
				chosenCard = CardMath.handCard;
				if (CardMath.areThereMoreCombos(chosenCard)) {	
				}
				if (CardMath.isThereAnotherCard(chosenCard)) {
				}
				finishTurn(currentPlayer);
				return;
			}
			
			if (CardMath.checkTableforStack(hand[currentPlayer])) {
				chosenCard = CardMath.handCard;
				if (CardMath.areThereMoreCombos(chosenCard)) {	
				}
				if (CardMath.isThereAnotherCard(chosenCard)) {
				}
				finishTurn(currentPlayer);
				return;
				
			}
			
			else {
				
				chosenCard = CardMath.throwDownCard(hand[currentPlayer]);
				hand[currentPlayer].removeCard(chosenCard);
				table.addCard(chosenCard);
				finishTurn(currentPlayer);
				JOptionPane.showMessageDialog(null, player[currentPlayer] + " threw down " + chosenCard);
				return;
			}
		}
		
		else if (table.getStackCount() == 3) {
		
			if (table.getBuiltStacks() == 3) {
				
				if (CardMath.getStackValSet() > 2) {
					if (CardMath.hasSpadeToPickUp(hand[currentPlayer])) {
						chosenCard = CardMath.handCard;
						if (CardMath.areThereMoreCombos(chosenCard)) {	
						}
						if (CardMath.isThereAnotherCard(chosenCard)) {
						}
						finishTurn(currentPlayer);
						return;
					}
					
					else if (CardMath.checkTableforStack(hand[currentPlayer])) {
						chosenCard = CardMath.handCard;
						if (CardMath.areThereMoreCombos(chosenCard)) {	
						}
						if (CardMath.isThereAnotherCard(chosenCard)) {
						}
						finishTurn(currentPlayer);
						return;
						
					}
				}
				
				
				else {
					
					chosenCard = CardMath.throwDownCard(hand[currentPlayer]);
					hand[currentPlayer].removeCard(chosenCard);
					table.addCard(chosenCard);
					finishTurn(currentPlayer);
					JOptionPane.showMessageDialog(null, player[currentPlayer] + " threw down " + chosenCard);
					return;
				}
			}
			
			else if (table.getBuiltStacks() == 2) {
				
				if (CardMath.getStackValSet() > 2) {
					if (CardMath.hasSpadeToPickUp(hand[currentPlayer])) {
						chosenCard = CardMath.handCard;
						if (CardMath.areThereMoreCombos(chosenCard)) {	
						}
						if (CardMath.isThereAnotherCard(chosenCard)) {
						}
						finishTurn(currentPlayer);
						return;
					}
				}
				
				else if (CardMath.buildExistingStack(hand[currentPlayer])) {
					JOptionPane.showMessageDialog(null, "Added to an Existing Stack!");
					finishTurn(currentPlayer);
					return;
				}
				
			
				else if (CardMath.getStackValSet() > 2) {
					if (CardMath.pickUpSingle(hand[currentPlayer]) ) {
						finishTurn(currentPlayer);
						return;
					}
				}
				
				
				if (CardMath.getStackValSet() > 2) {
					if (CardMath.checkTableforStack(hand[currentPlayer])) {
						chosenCard = CardMath.handCard;
						if (CardMath.areThereMoreCombos(chosenCard)) {	
						}
						if (CardMath.isThereAnotherCard(chosenCard)) {
						}
						finishTurn(currentPlayer);
						return;
						
					}
				}
				
				else if (CardMath.buildStack(hand[currentPlayer])) {
					JOptionPane.showMessageDialog(null, "Built a new stack of " + CardMath.stack);
					finishTurn(currentPlayer);
					return;
				}
			
				else {chosenCard = CardMath.throwDownCard0B(hand[currentPlayer]);
					hand[currentPlayer].removeCard(chosenCard);
					table.addCard(chosenCard);
					finishTurn(currentPlayer);
					JOptionPane.showMessageDialog(null, player[currentPlayer] + " threw down " + chosenCard);
					return;
				}
			}
			
			else if (table.getBuiltStacks() == 1) {

				if (CardMath.getStackValSet() > 2) {
					if (CardMath.hasSpadeToPickUp(hand[currentPlayer])) {
						chosenCard = CardMath.handCard;
						if (CardMath.areThereMoreCombos(chosenCard)) {	
						}
						if (CardMath.isThereAnotherCard(chosenCard)) {
						}
						finishTurn(currentPlayer);
						return;
					}
				}

				else if (CardMath.buildExistingStack(hand[currentPlayer])) {
					JOptionPane.showMessageDialog(null, "Added to an Existing Stack!");
					finishTurn(currentPlayer);
					return;
				}
				
				
				else if (CardMath.getStackValSet() > 2) {
					if (CardMath.pickUpSingle(hand[currentPlayer]) ) {
						finishTurn(currentPlayer);
						return;
					}
				}
				

				else if (CardMath.buildStack(hand[currentPlayer])) {
					JOptionPane.showMessageDialog(null, "Built a new Stack of " + CardMath.stack);
					finishTurn(currentPlayer);
					return;
				}
				
				else if (CardMath.getStackValSet() > 2) {
					if (CardMath.checkTableforStack(hand[currentPlayer])) {
						chosenCard = CardMath.handCard;
						if (CardMath.areThereMoreCombos(chosenCard)) {	
						}
						if (CardMath.isThereAnotherCard(chosenCard)) {
						}
						finishTurn(currentPlayer);
						return;
						
					}
				}
				chosenCard = CardMath.throwDownCard0B(hand[currentPlayer]);
				hand[currentPlayer].removeCard(chosenCard);
				table.addCard(chosenCard);
				finishTurn(currentPlayer);
				JOptionPane.showMessageDialog(null, player[currentPlayer] + " threw down " + chosenCard);
				return;
				
				
			}
			
			// ***********************************
			//  ZER0 Built stacks*****
			else {
				
				//will check if Card can be picked up without seep!
				if (CardMath.hasSpadeToPickUp(hand[currentPlayer])) {
					
				if (!CardMath.causesSeep0B(chosenCard.getCardNumber())) {
						
						finishTurn(currentPlayer);
						return;
					}
 				}
				
				if (CardMath.buildStack(hand[currentPlayer])) {
					JOptionPane.showMessageDialog(null, "Built a new Stack!");
					finishTurn(currentPlayer);
					return;
				}
				
				chosenCard = CardMath.throwDownCard0B(hand[currentPlayer]);
				hand[currentPlayer].removeCard(chosenCard);
				table.addCard(chosenCard);
				finishTurn(currentPlayer);
				JOptionPane.showMessageDialog(null, player[currentPlayer] + " threw down " + chosenCard);
				return;
				
				
			}
		}
		
			//if there are 2 stacks
		else if (table.getStackCount() == 2) {
			
			//First need to check if anything can be built!!!
			//TODO = if no buildable then else will choose card to throw down!
			//if both are being built then normal throwdown card method.
				if (table.getBuiltStacks() == 2) {
					
					
					if (CardMath.breakStack(hand[currentPlayer])) {
						finishTurn(currentPlayer);
						return;
					}
					
					if (CardMath.throwOnTop()) {
						finishTurn(currentPlayer);
						return;
					}
					
					
					
					chosenCard = CardMath.throwDownCard(hand[currentPlayer]);
					hand[currentPlayer].removeCard(chosenCard);						table.addCard(chosenCard);
					finishTurn(currentPlayer);
					JOptionPane.showMessageDialog(null, player[currentPlayer] + " threw down " + chosenCard);
					return;
				}
					
				if (table.getBuiltStacks() == 1) {
					//need to figure out which stack is being built and make sure card thrown 
					//down does not add up to it.
					if (CardMath.buildStack(hand[currentPlayer])) {
						JOptionPane.showMessageDialog(null, "Built a new Stack!");
						finishTurn(currentPlayer);
						return;
					}
					
					if (CardMath.breakStack(hand[currentPlayer])) {
						finishTurn(currentPlayer);
						return;
					}
					
					if (CardMath.throwOnTop()) {
						finishTurn(currentPlayer);
						return;
					}
					
					
					chosenCard = CardMath.throwDownCard1B2S(hand[currentPlayer]);
					hand[currentPlayer].removeCard(chosenCard);
					table.addCard(chosenCard);
					finishTurn(currentPlayer);
					JOptionPane.showMessageDialog(null, player[currentPlayer] + " threw down " + chosenCard);	
					return;
				}
					
				if (table.getBuiltStacks() == 0) {
					
					if (CardMath.buildStack(hand[currentPlayer])) {
						JOptionPane.showMessageDialog(null, "Built a new Stack!");
						finishTurn(currentPlayer);
						return;
					}
					
					chosenCard = CardMath.throwDownCard0B(hand[currentPlayer]);
					hand[currentPlayer].removeCard(chosenCard);
					table.addCard(chosenCard);
					finishTurn(currentPlayer);
					JOptionPane.showMessageDialog(null, player[currentPlayer] + " threw down " + chosenCard);
					return;
				}
			
		} //end of 2Stacks
			
			//if there is one stack (which player does not have since no SEEP)
		else if (table.getStackCount() == 1) {
				
			chosenCard = CardMath.throwDownCard0B(hand[currentPlayer]);
			hand[currentPlayer].removeCard(chosenCard);
			table.addCard(chosenCard);
			finishTurn(currentPlayer);
			JOptionPane.showMessageDialog(null, player[currentPlayer] + " threw down " + chosenCard);
			return;
			
		}
			
		else if (table.getStackCount() == 0) {
				
			chosenCard = CardMath.throwLeastLikelySEEP(hand[currentPlayer]);
			hand[currentPlayer].removeCard(chosenCard);
			table.addCard(chosenCard);
			finishTurn(currentPlayer);
			JOptionPane.showMessageDialog(null, player[currentPlayer] + " threw down " + chosenCard);
			return;
		}
		//TODO eventually the following 6 lines of code will not need to be here!
		
	} // end of playTurn();
	
	
	
	
	public void finishTurn(int p) {
		hand[p].sortBySuit();
		hand[p].sortByValue();
		updatePanel(p);
		gameviewPanel.redrawTable();
		updateScores();
	}

	public void endGame() {
		System.out.println(team[0].getRealtimeScore() + "  " + team[0].countCards());
		System.out.println(team[1].getRealtimeScore() + "  " + team[1].countCards());
		
		//TODO need to do everything required when ending game. 
		//pick up remaining cards to last pickeruppper.
		//such as resetting cards runtimeScores
		//calculating who will start the game next time
	}
	
	/**
	 * This method is called when picking up a card to 
	 * add to the player team's stack
	 * @param card - usually chosenCard but can be any other
	 * that needs to be picked up immediately and directly
	 */
	public void pickupCard(Card card) {
		team[currentPlayer%2].pickupCard(card);
		lastPicker = currentPlayer;
	}
	
	/**
	 *  * This method should only be called when committing
	 * a SEEP of the cards. THEREFORE should only
	 * be called within a true CheckForSeep() block
	 * 
	 * @param p -- currentPlayer
	 */
	public void pickupAllCards(int p) {
		Card card = new Card();
		
		for ( int s = 0; s < table.getStackCount(); s++) {
			for ( int c = 0; c < table.getStackofCards(s).size(); c++) {
				card = table.getStackofCards(s).get(c);
				team[p%2].pickupCard(card);
			}
		}
		table.clear();
		team[p%2].hitAseep();
		lastPicker = p;
	}
	
	/**
	 * This method is used when game is ended
	 * TODO "Not sure if this is used only for
	 * premature ending i.e. pressing STOP. 
	 * Or for when a game is totally finishes 
	 * Needs to be cleared up.
	 */
	public void clearScores() {
		team[0].newGame();
		team[1].newGame();
		MenuPanel2.addYourScore(0);
		MenuPanel2.addOppScore(0);
	}
	
	/**
	 * This method calculates the team[] scores
	 * and send them to MenuPanel to be updated
	 */
	public void updateScores() {
		MenuPanel2.addYourScore(team[0].getRealtimeScore());
		MenuPanel2.addOppScore(team[1].getRealtimeScore());
	}
	
	/**
	 * This method will automatically update the 
	 * current player's panel 
	 * @param player
	 */
	public void updatePanel(int player) {
		userPanel.dealCards(true);
		gameviewPanel.deal(1);
		gameviewPanel.deal(2);
		gameviewPanel.deal(3);
	}
	
	/**
	 * this was just to demo test if the shuffling was working for the cards
	 * @deprecated
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
