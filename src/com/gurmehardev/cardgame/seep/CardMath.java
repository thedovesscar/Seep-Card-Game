package com.gurmehardev.cardgame.seep;

import com.gurmehardev.cardgame.*;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;

import java.util.ArrayList;

import javax.swing.JOptionPane;


public class CardMath {

	static Seep gameSeep = Seep.getInstance();
	static Table table = Table.getInstance();
	static Card handCard = new Card();
	static Card tableCard = new Card();
	private static int stack;
	static int stackSize;
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
		
		for ( int s = 0; s < stacks; s++) {
			if  ( table.getStack(s).isBeingBuilt() )
				stacksBeingBuilt++;
		}
		
		if (stacksBeingBuilt > 1) {
			return 0;
		}
		
		int totalCards = 0;
		int totalStkVal = 0;
		for ( int s = 0; s < stacks; s++) {
			totalCards += table.getStack(s).getCardCount();
			totalStkVal += table.getStack(s).getStackValue();
		}
		
		if (stacksBeingBuilt == 0) {
			return totalStkVal;
		}
		
		if (stacksBeingBuilt == 1) {
			
			int stkBeingBuilt = 0;
			for ( int s = 0; s < stacks; s++) {
				if (table.getStack(s).isBeingBuilt()) {
					stkBeingBuilt = table.getStackValue(s);
				}
			}
			
			if (totalStkVal%stkBeingBuilt == 0) {
				return stkBeingBuilt;
			}
		}
		
		return 0; //TODO
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
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This should be called beginning of turn to check if Seep is possible.
	 * @param card
	 * @return
	 */
	static boolean checkForSeep(Card card) {
		return false;
	}
	
	
	/**
	 *  Should be called when there are 3 Stacks
	 * @param Card c 
	 */
	static void checkForCombo(Card c) {
		
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
		
		
		//Will go through all possible combos of existing stacks
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
			System.out.println("It's working!!");
			Card c = table.getStack(stack).getCardStack().get(0);
			gameSeep.pickupCard(c);
			table.removeCardfromStack(c, card.getCardNumber());
			tableCard = c;
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
		for (int hc = 0; hc < hand.getCardCount(); hc++) {
			if (hand.getCard(hc).getCardSuit() == Card.SPADE) {
				for ( int s = 0; s < table.getStackCount(); s++) {
					if ( hand.getCard(hc).getCardNumber() == table.getStackValue(s) ) {
						handCard = hand.getCard(hc);
						stack = s;
						stackSize = table.getStackofCards(s).size();
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
			if ( hand.getCard(i).getCardNumber() > 8 ) {
				if ( hand.getCard(i).getCardSuit() == Card.SPADE) {
					val = hand.getCard(i).getCardNumber();
					break;
				}
			}
		}
		if (val == 0) {
			for (int i = hand.getCardCount(); i >= 0; i--) {
				if ( hand.getCard(i-1).getCardNumber() > 8 ) {
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
	
	
	
	
	
}
