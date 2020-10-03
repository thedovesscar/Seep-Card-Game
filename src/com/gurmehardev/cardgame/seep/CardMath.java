package com.gurmehardev.cardgame.seep;

import com.gurmehardev.cardgame.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import org.omg.CORBA.PRIVATE_MEMBER;


public class CardMath {

	static Seep gameSeep = Seep.getInstance();
	static Table table = Table.getInstance();
	static Card handCard = new Card();
	static Card tableCard = new Card();
	static int stack;
	static int stackSize;
	private static Set<Integer> stkValues = new HashSet<Integer>(0);	
	
	public CardMath() {
		
	}
	
	/**
	 * This method should check generically if seep is possible
	 * and should return the value that will allow the seep to occur
	 * then method to check if user has the card value to commit the seep
	 * 
	 * 
	 * @return the value of Card required for a Seep 
	 * -!! returns 0 if no Seep is possible
	 */
	static int checkForSeep() {
		int stacks = table.getStackCount();
		int stacksBeingBuilt = 0;
		int totalStkVal = 0;
		
		for ( int s = 0; s < stacks; s++) {
			if  ( table.getStack(s).isBeingBuilt() )
				stacksBeingBuilt++;
		}
		for ( int s = 0; s < stacks; s++) {
			totalStkVal += table.getStack(s).getStackValue();
		}
		
		if (stacksBeingBuilt >= 2) {
			return 0;
		}
		
		if (stacksBeingBuilt == 1) {
			
			int stkBeingBuilt = 0;
			for ( int s = 0; s < stacks; s++) {
				if (table.getStack(s).isBeingBuilt()) {
					stkBeingBuilt = table.getStackValue(s);
				}
			}
			
			// This if-statement makes sure that the 
			// built stack is the largest denominator
			if (table.getLargestStack().getStackValue() != stkBeingBuilt) {
				return 0;
			}
			
			if (totalStkVal%stkBeingBuilt == 0) {
				return stkBeingBuilt;
			}
		}
		
		if (stacksBeingBuilt == 0) {
			if (totalStkVal % table.getLargestStack().getStackValue() 
					== 0) {
				return table.getLargestStack().getStackValue();
			}
		return totalStkVal;
		}

		return 0;
	}
	
	
	
	/**
	 * This method will create a HashSet of all the stacks that
	 * are currently on the field.
	 */
	static int  getStackValSet() {
		stkValues.clear();
		
		for (int s = 0; s < table.getStackCount(); s++) {
			stkValues.add(table.getStackValue(s));
			
		}
		return stkValues.size();
	}
	
	
	static void testRemoving1StackValSet() {
		
	}
	/**
	 * This method is meant to be called only once 
	 * checkForSeep() has been called and return a int val
 	 * 
	 * @param reqVal - value gotten from checkForSeep();
	 * @param hand - currrentPlayer's hand to check for Card
	 * with required Val
	 * @return - will return true and make static handCard
	 * the card going to be used as Card for SEEP
	 */
	static boolean hasCardforSeep(int reqVal, Hand hand) {
		
		for (int c = 0; c < hand.getCardCount(); c++ ) {
			if (hand.getCard(c).getCardNumber() == reqVal) {
				handCard = hand.getCard(c);
				handCard = checkForSpadeVersion(hand, handCard);
				gameSeep.pickupCard(handCard);
				gameSeep.pickupAllCards(Seep.currentPlayer);
				gameSeep.hand[Seep.currentPlayer].removeCard(handCard);
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean throwOnTop() {
		
		int p = Seep.currentPlayer;
		Hand hd = gameSeep.hand[p];
		for ( int s = 0; s < table.getStackCount(); s++) {
			Card c = new Card();
			for (int hc = 0; hc < hd.getCardCount(); hc++) {
		
				if (hd.getCard(hc).getCardNumber() == c.getCardNumber()) {
					if ( c.getCardNumber() == table.getStackValue(s)) {
						if (table.getStack(s).isBeingBuilt() || table.getStackValue(s) > 8) {
							handCard = c;
							stack = s;
							gameSeep.hand[Seep.currentPlayer].removeCard(handCard);
							table.addCard(handCard);
							JOptionPane.showMessageDialog(null, gameSeep.player[Seep.currentPlayer]
									+ " threw their " + handCard + " onto the stack of " + handCard.getCardNumber() );
							return true;
						}
					}
				}
				c = hd.getCard(hc);
			}
		}
		
		
		return false;
		
	}
	
	public static boolean pickUpSingle(Hand hand) {	
		
		for (int s = 0; s < table.getStackCount(); s++) {
			if (!table.getStack(s).isBeingBuilt()) {
				stack = s;
				for (int c = 0; c < hand.getCardCount(); c++) {
				
					if ( hand.getCard(c).getCardNumber() == table.getStackValue(s) ) {
						handCard = hand.getCard(c);
						tableCard = table.getStackofCards(s).get(0);
						gameSeep.pickupCard(handCard);
						gameSeep.hand[Seep.currentPlayer].removeCard(handCard);
						pickupStack(handCard);
						JOptionPane.showMessageDialog(null, gameSeep.player[Seep.currentPlayer] 
								+ " picked up "+ tableCard + " with their "+ handCard );
						return true;
					}
				}
			}
		}
		
		return false;
	}
	/**
	 * 
	 * 
	 * @param hand
	 * @return
	 */
	static Card findAskingCard(Hand hand) {
		Card chosenCard;
		Card tempCard = new Card();
		for (int i = 3; i >= 0; i--) {
			Card c = hand.getCard(i);
			if (c.getCardNumber() >= 9) {
				if (c.getCardSuit() != Card.SPADE) {
					chosenCard = c;
					JOptionPane.showMessageDialog(null, "Player is asking for " + chosenCard.getCardNumberString());
					return chosenCard;
				}
				else  {
					 tempCard = c;
				}
			}
		} //end of for searching for Card to ask.
		JOptionPane.showMessageDialog(null, "Player is asking for " + tempCard.getCardNumberString());
		return tempCard;
	}
	
	/**
	 * This is called during FirstTurn() and checks if
	 * there is a way to build the asking card.
	 * 
	 * to be used in an IF statement.
	 * 
	 * @param card
	 * @param hand
	 * @return
	 */
	static boolean canAskingCardBeBuilt(Card card, Hand hand) {
		
		int stkSz = table.getStackCount();
		int reqValue = card.getCardNumber();
		
		for ( int x = 0 ; x < 4; x++ ) {
			for ( int y = 0; y < stkSz; y++) {
				for ( int z = 0; z < table.getStackofCards(y).size(); z++) {
					if (!table.getStack(y).isDoubled()) {
						if (hand.getCard(x).getCardNumber() + table.getStackofCards(y).get(z).getCardNumber() == reqValue) {
							if (hand.getCard(x) == card) {
								return false;
							}
							else {
								handCard = hand.getCard(x);
								tableCard = table.getStackofCards(y).get(z);
								return true;
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Once the player builds a stack that
	 * they were asking for, the rest of the table
	 * is scanned to check if there is another combo available to be
	 * put into the stack
	 * @param stackVal
	 * @return
	 */
	static boolean areThereMoreCombos(int stackVal) {
		int stackSz = table.getStackCount();
		
		
		//Will go through all possible combos of existing stacks
		for (int i = 0; i < stackSz; i++) {
			for (int j = 0; j < stackSz; j++) {
				if (i != j) {
					if ((table.getStack(i).getStackValue() != stackVal 
							&& !table.getStack(i).isBeingBuilt() )
							&& (table.getStack(j).getStackValue() != stackVal)
							&& !table.getStack(j).isBeingBuilt()) {
						if (table.getStack(i).getStackValue() 
								+ table.getStack(j).getStackValue() == stackVal) {
							ArrayList<Card> stack1 = table.getStackofCards(i);  //this should be a single card
							ArrayList<Card> stack2 = table.getStackofCards(j);  //this should be a single card
							Card card1 = stack1.get(0);
							Card card2 = stack2.get(0);
							table.removeCard(card1);
							table.removeCard(card2);
							table.addCard(card1, stackVal);
							table.addCard(card2, stackVal);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 *  This method is being called during first Turn (at least for now)
	 *  to pick up any stacks that add up to the Parameter Card.number
	 *   
	 * @param card
	 * @return
	 */
	static boolean areThereMoreCombos(Card card) {
		int stackSz = table.getStackCount();
		
		//will go thru all possible combos for 3 stacks!
		for (int i = 0; i < stackSz; i++) {
			for (int j = 0; j < stackSz; j++) {
				for ( int l = 0; l < stackSz; l ++) {
					if ((i != j && i!= l) && l != j) {
						Stack s1 = table.getStack(i);
						Stack s2 = table.getStack(j);
						Stack s3 = table.getStack(l);
						
						
						if ( (s1.getStackValue() != card.getCardNumber()
								&& !s1.isBeingBuilt() )
								&& ( s2.getStackValue() != card.getCardNumber()
								&& !s2.isBeingBuilt() ) ) {
							
							if (s3.getStackValue() != card.getCardNumber() && !s3.isBeingBuilt() ) {
								if (s1.getStackValue() 
										+ s2.getStackValue() + s3.getStackValue()== card.getCardNumber()) {
									ArrayList<Card> stack1 = table.getStackofCards(i);  //this should be a single card
									ArrayList<Card> stack2 = table.getStackofCards(j);  //this should be a single card
									ArrayList<Card> stack3 = table.getStackofCards(l);  //this should be a single card
									Card card1 = stack1.get(0);
									Card card2 = stack2.get(0);
									Card card3 = stack3.get(0);
									table.removeCard(card1);
									table.removeCard(card2);
									table.removeCard(card3);
									JOptionPane.showMessageDialog(null, card1 + ", " + card2 + " and "+ card3+" was also picked up.");
									gameSeep.pickupCard(card1);
									gameSeep.pickupCard(card2);
									gameSeep.pickupCard(card3);
									return true;
								}
							}
						}
					}
				}
			}
		}
		
		//Will go through all possible combos of 2 stacks
		for (int i = 0; i < stackSz; i++) {
			for (int j = 0; j < stackSz; j++) {
				if (i != j) {
					if ( (table.getStack(i).getStackValue() != card.getCardNumber()
							&& !table.getStack(i).isBeingBuilt() )
							&& ( table.getStack(j).getStackValue() != card.getCardNumber()
							&& !table.getStack(j).isBeingBuilt() ) ) {
						if (table.getStack(i).getStackValue() 
								+ table.getStack(j).getStackValue() == card.getCardNumber()) {
							ArrayList<Card> stack1 = table.getStackofCards(i);  //this should be a single card
							ArrayList<Card> stack2 = table.getStackofCards(j);  //this should be a single card
							Card card1 = stack1.get(0);
							Card card2 = stack2.get(0);
							table.removeCard(card1);
							table.removeCard(card2);
							JOptionPane.showMessageDialog(null, card1 + " and " + card2 + " was also picked up.");
							gameSeep.pickupCard(card1);
							gameSeep.pickupCard(card2);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	static boolean isThereAnotherCard(Card card) {
		int stackSz = table.getStackCount();
		
		
		//Will go through all possible combos of existing stacks
		for (int i = 0; i < stackSz; i++) {
			if (card.getCardNumber() == table.getStackValue(i) ) {
				pickupStack(card);
				return true;
			}
		}
		return false;
	}
	
	
	static boolean isAskingCardonTable(Card card) {
		int tableStacksSize = table.getStackCount();
		if (table.getCardCount() != 0) {
			for (int tS = 0; tS < tableStacksSize; tS++) {
				if (card.getCardNumber() == table.getStackValue(tS)) {
					stackSize = table.getStackofCards(tS).size();
					stack = tS;
					handCard = card;
					tableCard = table.getStackofCards(tS).get(0);
					return true;
				
				}				
			}
		}
		
		return false;
	}

	/**
	 * TODO
	 * 
	 * @return True when player can add a card from their hand
	 * to build on top of existing stack
	 */
	static boolean canCardBeBuilt(int cVal, Hand hand) {
		int stkSz = table.getStackCount();
		
		for ( int x = 0 ; x < hand.getCardCount(); x++ ) {
			for ( int y = 0; y < stkSz; y++) {
				if (!table.getStack(y).isDoubled()) {
					if (hand.getCard(x).getCardNumber() + table.getStackValue(y) == cVal) {
						if (hand.getCard(x).getCardNumber() == cVal) {
						}
						else {
							handCard = hand.getCard(x);
							tableCard = table.getStackofCards(y).get(0);
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	/**
	 * This currently checks for a pickable stack but also attempts to pick them
	 * up
	 * TODO currently having an error at picking up the cards correctly
	 * 
	 * @param hand
	 * @return
	 */
	static boolean checkTableforStack(Hand hand) {
		int tableStacksSize = table.getStackCount();
		int handSize = hand.getCardCount();
		
		if (table.getCardCount() != 0) {
			for (int tS = 0; tS < tableStacksSize; tS++) {
				for (int hC = 0; hC < handSize; hC++) {
					
					if (hand.getCard(hC).getCardNumber() == table.getStackValue(tS)) {
						stackSize = table.getStackofCards(tS).size();
						stack = tS;
						handCard = hand.getCard(hC);
						
						gameSeep.pickupCard(handCard);
						gameSeep.hand[Seep.currentPlayer].removeCard(handCard);
						if (stackSize == 1) {
							JOptionPane.showMessageDialog(null, gameSeep.player[Seep.currentPlayer] 
									+ " picked up the card " + table.getStackValue(tS) + " with their " + handCard);
						} else {
							JOptionPane.showMessageDialog(null, gameSeep.player[Seep.currentPlayer] 
									+ " picked up the stack of " + table.getStackValue(tS) + " with their " + handCard);
						}
						pickupStack(handCard);
						return true;
						
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * TODO
	 * This method should be called whenever picking up cards or stacks
	 * to check whether or not the resulting stacks left will cause a seep
	 * to occur.
	 * 
	 * @return
	 */
	static boolean causesSeep() {
		
		return false;
	}
	
	static void pickupStack(Card card) {
		if (card.getCardNumber() == table.getStackValue(stack)) {
			int n = table.getStackofCards(stack).size();
			for (int i = 0; i < n; i++) {
				if (table.getStack(stack).getCardCount() != 0 ){
					tableCard = table.getStack(stack).getCardStack().get(0);
					gameSeep.pickupCard(tableCard);
					table.removeCardfromStack(tableCard, card.getCardNumber());
				}
				else {
					
				}
			}
		}
	}
	
	/**
	 * This is similar to the regular checkForSpadeVersion(hand, card)
	 * excpet this should be called only when it is the asking players turn;
	 * @param hand
	 * @param card
	 * @param asking
	 * @return
	 */
	
	/**
	 * This is simple test for which card to throw down
	 * will throw lowest card that isnt a spade
	 * and then throw lowest card if all spades
	 * 
	 * @param hand
	 * @return
	 */
	static Card throwDownCard(Hand hand) {
		
		//will check for lowest card not on table, and not spade first.
		for(int ii = 0; ii < hand.getCardCount(); ii++) {

			if ( hand.getCard(ii).getCardSuit() != Card.SPADE) {
				
				if ( !doesStackExist(hand.getCard(ii).getCardNumber())) {
					return hand.getCard(ii);
				}
			}
		}
		//will check for lowest card not on table of any suit
		for(int ii = 0; ii < hand.getCardCount(); ii++) {
		
			if ( !doesStackExist(hand.getCard(ii).getCardNumber())) {
				return hand.getCard(ii);				
			}			
		}
		
		//will throw regardless of consequence 
		//TODO maybe should be highest card since seep is possible
		return hand.getCard(0);
	}
	
	/** 
	 * This method runs most of the same checks as the base throwDownCard();
	 * method above except will check that there will be no seep afterwards.
	 * 
	 * 1 Built  stack, total 2 Stacks
	 * @param hand
	 * @return
	 */
	static Card throwDownCard1B2S(Hand hand) {
		//first check which stack is being built.
		int stk;
		int oppStk;
		int stkVal;
		
		if ( table.getStack(0).isBeingBuilt()) {
			stk = 0;		
			oppStk = 1;
			stkVal = table.getStack(0).getStackValue();
			}
		else {
			stk = 1;	
			oppStk = 0;
			stkVal = table.getStack(1).getStackValue();
		}
		
		
		//will check for lowest card not on table, and not spade first.
		for(int ii = 0; ii < hand.getCardCount(); ii++) {

			if ( hand.getCard(ii).getCardSuit() != Card.SPADE) {						
				if ( !doesStackExist(hand.getCard(ii).getCardNumber())) {
					if (hand.getCard(ii).getCardNumber() 
							+ table.getStackValue(oppStk)
							!= stkVal)
						return hand.getCard(ii);
				}
			}
		}
		//will check for lowest card not on table of any suit
		for(int ii = 0; ii < hand.getCardCount(); ii++) {
				
			if ( !doesStackExist(hand.getCard(ii).getCardNumber())) {
				if (hand.getCard(ii).getCardNumber() 
						+ table.getStackValue(oppStk)
						!= stkVal)
					return hand.getCard(ii);				
			}			
		}
				
		//will throw regardless of consequence 
		//TODO maybe should be highest card since seep is possible
		return hand.getCard(0);
		
		
	}
	
	/**
	 * This method should be called when there are now 
	 * built stacks. This will attempt to throw a card that 
	 * results in a total value on the field above 13
	 * 
	 * @param current Player's hand
	 * @return which Card is playable and will not result in a SEEP
	 */
	static Card throwDownCard0B(Hand hand) {
		
		int totalStkVal = 0;
		
		for ( int i = 0; i < table.getStackCount(); i++) {
			totalStkVal += table.getStackValue(i);
		}
		
		if (totalStkVal > 13) {
			Card card = throwDownCard(hand);
			return card ;
		}
	
		//will check for lowest card not on table, and not spade first.
		for(int ii = 0; ii < hand.getCardCount(); ii++) {

			if ( hand.getCard(ii).getCardSuit() != Card.SPADE) {
				
				if ( !doesStackExist(hand.getCard(ii).getCardNumber())) {
					if ( hand.getCard(ii).getCardNumber() 
							+ totalStkVal > 13)
						return hand.getCard(ii);
				}
			}
		}
		//will check for lowest card not on table of any suit
		for(int ii = 0; ii < hand.getCardCount(); ii++) {
		
			if ( !doesStackExist(hand.getCard(ii).getCardNumber())) {
				if ( hand.getCard(ii).getCardNumber() 
						+ totalStkVal > 13)
					return hand.getCard(ii);				
			}			
		}
		
		Card card; 
		card = throwLeastLikelySEEP(hand);
		return card;
		//will throw regardless of consequence 
		//TODO maybe should be highest card since seep is possible
	}
	
	/**
	 * This method does what it implies
	 * 
	 * if checks for what card the user has the most of. 
	 * @param hand
	 * @return
	 */
	static Card throwLeastLikelySEEP(Hand hand) {
		
		int totalStkVal = 0;
		
		for ( int i = 0; i < table.getStackCount(); i++) {
			totalStkVal += table.getStackValue(i);
		}
		int prev = 0;
		int curr = 0;
		int twice = 0;
		int thrice = 0;
		int quad = 0;
		
		//TODO maybe i should save hand at beginning of round so layer "remembers"
		//their hand
	
		for (int i = hand.getCardCount(); i > 0; i--) {
			curr = hand.getCard(i-1).getCardNumber();
			if (thrice == curr) {
				quad = curr;
			}
			
			if (twice == curr) {
				thrice = twice;
			}
			if ( curr == prev) {
				twice = curr;
			}
			
			prev = curr;
			
		}
		System.out.println("least likely seep" +quad+thrice+twice+curr);
		
		
		if (quad != 0) {
			curr = quad;
		}
		else if (thrice != 0) {
			curr = thrice;
		}
		else if (twice != 0) {
			curr = twice;
		}
		else  {
			return hand.getCard(0);
		}
		
		curr = curr - totalStkVal;
		
		
		//will check for lowest card not on table, and not spade first.
		for(int ii = 0; ii < hand.getCardCount(); ii++) {

			if ( hand.getCard(ii).getCardSuit() != Card.SPADE) {
				
				if ( !doesStackExist(hand.getCard(ii).getCardNumber())) {
					if ( hand.getCard(ii).getCardNumber() == curr)
						return hand.getCard(ii);
				}
			}
		}
		//will check for lowest card not on table of any suit
		for(int ii = 0; ii < hand.getCardCount(); ii++) {
		
			if ( !doesStackExist(hand.getCard(ii).getCardNumber())) {
				if ( hand.getCard(ii).getCardNumber() == curr)
					return hand.getCard(ii);				
			}			
		}
		//will throw regardless of consequence 
		//TODO maybe should be highest card since seep is possible
		return hand.getCard(0);
	}
	
	/**
	 * This method checks if there is any stack that currently has the 
	 * value submitted as parameter.
	 * 
	 * @param val
	 * @return - boolean - whether or not a stack with that value already exists
	 */
	static boolean doesStackExist(int val) {
		
		for ( int i = 0; i < table.getStackCount(); i++) {
			if (val == table.getStackValue(i)) {
				return true;
			}
		}
		
		return false;
		
	}
	/**
	 * First turn version of checkForSpadeVersion 
	 * 
	 *  //TODO wait isnt it redundant if First turn has two of the same card
	 *  //TODO cus then they should build, not pick up
	 *  
	 * @param player's hand
	 * @param card
	 * @param asking
	 * 
	 * @return
	 */
 	static Card checkForSpadeVersion(Hand hand, Card card, boolean asking) {
		Card cardFound = card;
		int cardNum = card.getCardNumber();
		
		for (int i = 0; i < 4; i++) {
			if (hand.getCard(i).getCardNumber() == cardNum) {
				if(hand.getCard(i).getCardSuit() == Card.SPADE) {
					cardFound = hand.getCard(i);
				}
			}
		}
		return cardFound;
	}
	
	/**
	 * This Method is to be called when Card is found on Table 
	 * and player is about to pick it up. 
	 * THis checks player hand to see if they have the spade 
	 * to pick up the points
	 * 
	 * @param the current Player's hand.
	 * @param the Chosen Card to pick up with
	 * @return
	 */
	static Card checkForSpadeVersion(Hand hand, Card card) {
		Card cardFound = card;
		int cardNum = card.getCardNumber();
		
		int handSize = hand.getCardCount();
		
		for (int i = 0; i < handSize; i++) {
			if (hand.getCard(i).getCardNumber() == cardNum) {
				if(hand.getCard(i).getCardSuit() == Card.SPADE) {
					cardFound = hand.getCard(i);
				}
			}
		}
		
		
		return cardFound;
	}

	/**
	 * This method is called to check if user can pick up Spade of any stack
	 * if so then player should pick up that card. and stack.
	 * 
	 * @param hand
	 * @return
	 */
	public static boolean hasSpadeToPickUp(Hand hand) {
		int totC = hand.getCardCount();
		totC--;
		for (int hc = totC; hc >= 0; hc--) {
			if (hand.getCard(hc).getCardSuit() == Card.SPADE) {
				for ( int s = 0; s < table.getStackCount(); s++) {
					if (hand.getCard(hc).getCardNumber() == table.getStackValue(s) ) {
						handCard = hand.getCard(hc);
						gameSeep.pickupCard(handCard);
						stack = s;
						stackSize = table.getStackofCards(s).size();
						gameSeep.hand[Seep.currentPlayer].removeCard(handCard);
						if (stackSize == 1) {
							JOptionPane.showMessageDialog(null, gameSeep.player[Seep.currentPlayer] 
									+ " picked up the card " + table.getStackValue(s) + " with their " + handCard);
						} else {
							JOptionPane.showMessageDialog(null, gameSeep.player[Seep.currentPlayer] 
									+ " picked up the stack of " + table.getStackValue(s) + " with their " + handCard);
						}
						pickupStack(handCard);
						return true;
					}
				}
			}
		}
		
		return false;
	}

	/**
	 * THis method will run when player must build Stack and check which
	 * stack Value can be built with which card.
	 * 
	 * FIrst the highest spade face card will be chosen
	 * then the highest non spade.
	 * 
	 * then it will be checked if it can be built
	 * 
	 * @param hand
	 * @return - val = the stack val that can be built with handCard and tableCard.
	 */
	public static int chooseStacktoBuild(Hand hand) {
		int val = 0;
		
		for (int i = 0; i < hand.getCardCount(); i++) {
			if ( hand.getCard(i).isBuildable()) {
				if ( hand.getCard(i).getCardSuit() == Card.SPADE) {
					val = hand.getCard(i).getCardNumber();
					break;
				}
			}
		}
		if (val == 0) {
			for (int i = hand.getCardCount(); i > 0; i--) {
				if ( hand.getCard(i-1).isBuildable()) {
					val = hand.getCard(i-1).getCardNumber();
					break;	
				}
			}
		}
		
		if (canCardBeBuilt(val, hand)) {
			return val;
		}
		return 0;
	}

	//** this will check if picking up a certain can risk a simple <14 SEEP
	public static boolean causesSeep0B(int cV) {
		
		int totStkVal = table.getTotalStackValue();
		// TODO Auto-generated method stub
		
		if (totStkVal - cV >= 14) 
			return false;
		else 
			return true;
	}

	/**
	 * This method will try to build any stack possible!
	 * with which ever card is on the ground
	 * 
	 * @param hand
	 * @return
	 */
	public static boolean buildStack(Hand hand) {
		// TODO Auto-generated method stub
		
		int max = hand.getCardCount();
		max--;
		
		for ( int c = 0; c < hand.getCardCount(); c++) {
			for ( int s = 0; s < table.getStackCount(); s++) {
				for ( int t = 0; t < table.getStackCount(); t++) {
					for ( int bC = max; bC >= 0; bC--) {
						if (s != t) {
							if ( !table.getStack(s).isBeingBuilt()) {
								if ( !table.getStack(t).isBeingBuilt()) {
									if ( hand.getCard(c).getCardNumber() + table.getStackValue(s) 
										+ table.getStackValue(t) == hand.getCard(bC).getCardNumber()) {
										if (hand.getCard(bC).isBuildable()) {
											if ( hand.getCard(bC).getCardNumber() != table.getfirstBuiltStack()) {
												handCard = hand.getCard(c);
												stack = hand.getCard(bC).getCardNumber();
												
												tableCard = table.getStack(s).getCardStack().get(0);
												table.removeCard(tableCard);
												table.addCard(tableCard, stack);
												
												tableCard = table.getStack(t).getCardStack().get(0);
												table.removeCard(tableCard);
												table.addCard(tableCard, stack);
												
												gameSeep.hand[Seep.currentPlayer].removeCard(handCard);
												table.addCard(handCard, stack);
												
												return true;
											}
										}
									}
								}
							}
						}
						
					}
				}
			}
		}
		
		for ( int c = 0; c < hand.getCardCount(); c++) {
			for ( int s = 0; s < table.getStackCount(); s++) {
				for ( int bC = max; bC >= 0; bC--) {
					if ( !table.getStack(s).isBeingBuilt()) {
						if ( hand.getCard(c).getCardNumber() + table.getStackValue(s) 
						== hand.getCard(bC).getCardNumber()) {
							if (hand.getCard(bC).isBuildable()) {
								if ( hand.getCard(bC).getCardNumber() != table.getfirstBuiltStack()) {
									handCard = hand.getCard(c);
									tableCard = table.getStack(s).getCardStack().get(0);
									table.removeCard(tableCard);
									stack = hand.getCard(bC).getCardNumber();
									table.addCard(tableCard, stack);
									gameSeep.hand[Seep.currentPlayer].removeCard(handCard);
									table.addCard(handCard, stack);
									return true;
								}
							}
						}
					}
					
					
				}
			}
		}
		
		return false;
	}

	/**
	 * This method will try to build into an existing Stack
	 * 
	 * TODO it only tries to build into the first existing stack unfortunately!!
	 * 
	 * @param hand
	 * @return True if stack is added to
	 */
	public static boolean buildExistingStack(Hand hand) {
		
		for ( int c = 0; c < hand.getCardCount(); c++) {
			for ( int s = 0; s < table.getStackCount(); s++) {
				for ( int bC = 0; bC < hand.getCardCount(); bC++) {
					if ( !table.getStack(s).isBeingBuilt()) {
						if ( hand.getCard(c).getCardNumber() + table.getStackValue(s) 
						== hand.getCard(bC).getCardNumber()) {
							if (hand.getCard(bC).isBuildable()) {
								if ( hand.getCard(bC).getCardNumber() == table.getfirstBuiltStack()) {
									handCard = hand.getCard(c);
									tableCard = table.getStack(s).getCardStack().get(0);
									table.removeCard(tableCard);
									stack = hand.getCard(bC).getCardNumber();
									table.addCard(tableCard, stack);
									gameSeep.hand[Seep.currentPlayer].removeCard(handCard);
									table.addCard(handCard, stack);
									return true;
								}
							}
						}
					}
					
					
				}
			}
		}
		
		return false;
	}
	
	/**
	 * THis method will check if a stack on the ground is breakable
	 * 
	 * @param hand
	 * @return
	 */
	public static boolean breakStack(Hand hand) {
		int max = hand.getCardCount();
		
		
		for ( int s = 0; s < table.getStackCount(); s++) {
			if(table.getStack(s).isBeingBuilt() && !table.getStack(s).isDoubled()) {
				for (int c = 0; c < max; c++) {
					BUILD_CARD_LOOP:
					for (int bC = max-1; bC >= 0; bC--) {
						if (c != bC) {
							for (int s2 = 0; s2 < table.getStackCount(); s2++) {
								if (hand.getCard(bC).getCardNumber() == table.getStackValue(s2)) {
									continue BUILD_CARD_LOOP;
								}
									
							}
							if (hand.getCard(c).getCardNumber() + table.getStackValue(s) 
							== hand.getCard(bC).getCardNumber()) {
								
								handCard = hand.getCard(c); 
								int stackVal = hand.getCard(bC).getCardNumber();
								int oldStk = table.getStackValue(s);
								ArrayList<Card> tempCards = table.getStack(s).getCardStack();

								table.removeStack(oldStk);
								stackSize = tempCards.size();
								for (int tc =0; tc < stackSize; tc++) {
									table.addCard(tempCards.get(tc), stackVal);
								}
								
								table.addCard(handCard, stackVal);
								gameSeep.hand[Seep.currentPlayer].removeCard(handCard);
								JOptionPane.showMessageDialog(null, gameSeep.player[Seep.currentPlayer] +" just broke " + oldStk
										+ " and made it into " + stackVal);
								return true;
							}
						}
					}
				}
			}
				
				
		}

		return false;
	}
	
}
