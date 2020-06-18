package com.gurmehardev.cardgame.seep;

import com.gurmehardev.cardgame.*;
import com.sun.istack.internal.FragmentContentHandler;

import java.util.ArrayList;


/**
 * This class should have extended hands.
 * @author Gurmehar
 *
 */
public class Table {

	private volatile static Table instance = null;
	
	
	private static ArrayList<Stack> stack;
	
	private Table() {
		if (instance != null) {
			throw new RuntimeException("Use getInstance() method "
					+ " to create SingletonExample instance");
		}
		
		
		stack = new ArrayList<Stack>();
	} // Table constructor

	public static Table getInstance() {
		if (instance == null) {
			synchronized (Table.class) {
				if (instance == null) {
					instance = new Table();
				}
			}
		}
		return instance;
	}
	
	/**
	 * currently used to set up table cards 
	 * Not sufficient when going to add on to a stack.
	 * @param card
	 */
	public void placeCard(Card card) {
		stack.add(new Stack(card));
		System.out.println("A stack of " + card.getCardNumber() + " has been created. addAskingCard(Card)");
		sortByStack();
	} //end of addCard to Table

	/**
	 * this checks if card can be added and will lead to whether or not add card can be called.
	 * Parameter card must belong directly to Parameter hand
	 * @param card
	 * @param hand
	 * @return
	 */
	public boolean canCardbeAdded(Card card, Hand hand) {
		for ( int ii = 0; ii < stack.size(); ii++) {
			
			//if the card being added already has stack equivalent then this branch will run
			if (card.getCardNumber() == stack.get(ii).getStackValue()) {
				for ( int jj = 0; jj < hand.getCardCount(); jj++) {
					Card cardInHand = hand.getCard(jj);
					if (card.getCardNumber() == cardInHand.getCardNumber() && 
						card.getCardSuit() != cardInHand.getCardSuit()) {
							return true;
					}
				} 
				return false;
			}
		}
		return true;
	}
	
	/**
	 * SHould only be called after calling and returning true 
	 * --canCardbeAdded();
	 * 
	 * This is the method from when player is throwing down a card
	 * but needs to have check that 1 there is no stack already of 
	 * that value.... 2 unless face card and player has another of that value
	 * 3 if there is then needs to be picked up or another card chosen 
	 * @param card
	 */
	public void addCard(Card card) {
		addCardtoStack(card);
		sortByStack();
	}
	
	/**
	 * this method is for adding a card to stack
	 * that has been built!.
	 * @param card
	 * @param stackVal
	 */
	public void addCard(Card card, int stackVal) {
		int stackSz = stack.size();
		for (int x = 0; x < stackSz; x++) {
			if (stack.get(x).getStackValue() == stackVal) {
				stack.get(x).addCard(card);
				System.out.println(card + " has been added to the stack of " + stackVal);
				return;
			}
		}
		stack.add(new Stack(card, stackVal));
		System.out.println("A stack of " + stackVal + " has been created. addCard(Card, int)");
		sortByStack();
	}
	
	/**
	 * 
	 * @param card
	 */
	private void addCardtoStack(Card card) {
		int cardNum = card.getCardNumber();
		boolean stackExists = false;
		/**
		 * if stack has more than 1 card then it will check 
		 * if that stack value exists on the table.
		 * Otherwise a new stack is made
		 */
		if (stack.size() != 0 ) {
			for (int i = 0; i < stack.size(); i++) {
				if (stack.get(i).getStackValue() == cardNum) {
					stackExists = true;
					stack.get(i).addCard(card);
					System.out.println(card + " has been added to the stack of " + cardNum);
				}
			} 
		}  //
		if (!stackExists) {
			stack.add(new Stack(card));
			System.out.println("A stack of " + cardNum + " has been created. addCardtoStack(Card)");
		}
		
	}
	
	public void removeCard(Card card) {
		removeCardfromStack(card);
	}
	
	/**
	 * This is expecting the card being removed is in the stack.
	 * And will attempt to only remove the card from the stack if
	 * there are more cards in the stack. until it is the last card;
	 * @param card
	 * @param stkVal
	 */
	public void removeCardfromStack(Card card, int stkVal) {
		int stackSz = stack.size();
		for (int st = 0; st < stackSz; st++) {
			if (stack.get(st).getStackValue() == stkVal) {
				int cardsInStk = stack.get(st).getCardStack().size();
				System.out.println("stack " + stack.get(st).getStackValue() 
						+ " has " + cardsInStk + " cards. removeCardfromStack(Card , int) from Table.class ");
				if (cardsInStk > 1) {

					for (int i = 0; i < cardsInStk; i++) {
						if (stack.get(st).getCardStack().get(i) == card) {
							stack.get(st).getCardStack().remove(i);
							return;
						}
					}
				}
				else if (cardsInStk == 1) {
					stack.get(st).getCardStack().remove(0);
					stack.remove(st);
					return;
				}
				else if (cardsInStk == 0) {
					System.out.println("some how the stack being emptied"
							+ " was already empty?? removeCardfromStack(Card, int) in Table.class");
					return;
				}
				
				
			}
		}
		
	}
	
	/**
	 * does not seem to be working Correctyl TODO
	 * @param card
	 */
	private void removeCardfromStack(Card card) {
		int cardNum = card.getCardNumber();
		
		for (int i = 0; i < stack.size(); i++) {
			
			if (stack.get(i).getStackValue() == cardNum) {
				System.out.println("Card is removed from stack of  " + stack.get(i).getStackValue() + ". removeCardfromStack(Card)");
				stack.remove(i);
				
			}
			
		}

	}
	
	public boolean hasCardStack(int stackVal) {
		if (stackVal != 0) {
			for (int i = 0; i < stack.size(); i++) {
				if (stack.get(i).getStackValue() == stackVal) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void clear() {
		clearStacks();
	}
	
	private void clearStacks() {
		stack.clear();
	}
	
	/**
	 * this method will check each stack and return
	 * the total amount of cards in all stacks.
	 * @return amount of Cards on table
	 */
	public int getCardCount() {
		int stacks = stack.size();
		int cards = 0;
		for ( int s = 0; s < stacks; s++) {
			cards += stack.get(s).getCardStack().size();
		}
		return cards;
	}

	
	public void sortByStack() {
        ArrayList<Stack> newStack = new ArrayList<Stack>();
        while (stack.size() > 0) {
            int pos = 0;  // Position of minimal card.
            Stack s = stack.get(0);  // Minimal card.
            for (int i = 1; i < stack.size(); i++) {
                Stack s1 = stack.get(i);
                if ( s1.getStackValue() > s.getStackValue()||
                        (s1.getStackValue() == s.getStackValue() /* && c1.getCardSuit() < c.getCardSuit() */)) {
                    pos = i;
                    s = s1;
                }
            }
            stack.remove(pos);
            newStack.add(s);
        }
        stack = newStack;
    }
	
//	public void sortByValue() {
//        ArrayList<Card> newTable = new ArrayList<Card>();
//        while (table.size() > 0) {
//            int pos = 0;  // Position of minimal card.
//            Card c = table.get(0);  // Minimal card.
//            for (int i = 1; i < table.size(); i++) {
//                Card c1 = table.get(i);
//                if ( c1.getCardNumber() < c.getCardNumber() ||
//                        (c1.getCardNumber() == c.getCardNumber() /* && c1.getCardSuit() < c.getCardSuit() */)) {
//                    pos = i;
//                    c = c1;
//                }
//            }
//            table.remove(pos);
//            newTable.add(c);
//        }
//        table = newTable;
//    }
	
	public Stack getStack(int i) {
		return stack.get(i);
	}
	public int getStackValue(int i) {
		return stack.get(i).getStackValue();
	}
	
	public ArrayList<Card> getStackofCards(int i) {
		return stack.get(i).getCardStack();
	}
	
	/**
	 * This method returns the amount of Stacks are currently on the table
	 * @return
	 */
	public int getStackCount() {
		return stack.size();
	}
	
	/** 
	 * This method checks how many stacks are being built on the Table
	 * @return int - amount of stacks being built.
	 */
	public int getBuiltStacks() {
		int count = 0;
		
		for (int s = 0; s < stack.size(); s++) {
			if (stack.get(s).isBeingBuilt())
				count++;
		}
		
		return count;
	}
	
	public int getNonBuiltValue() {
		int val = 0;
		for (int s = 0; s < getStackCount(); s++) {
			if (!stack.get(s).isBeingBuilt()) {
				val += getStackValue(s);
			}
		}
		return val;
	}
	
	/**
	 * This method is called from checkTableforStack() in CardMath
	 * 
	 * it requires the value of the stack you are looking for.
	 * It tells you whether or not that stack exists.
	 * 
	 * @param stackVal
	 * @return
	 */
	public boolean hasStack(int stackVal) {
		
		for ( int i = 0; i < stack.size(); i++) {
			if (stack.get(i).getStackValue() == stackVal) {
				return true;
			}
		}
		return false;
	}

	/**
	 * returns the total stack Vals of Table
	 * 
	 * @return
	 */
	public int getTotalStackValue() {
		int val = 0;
		
		for ( int s = 0; s < getStackCount(); s++) {
			val += getStackValue(s);
		}
			
		return val;
	}

	public int getfirstBuiltStack() {
		for (int s = 0; s < getStackCount(); s++) {
			if (stack.get(s).isBeingBuilt() ) 
				return stack.get(s).getStackValue();
		}
	
		return 0;
	}

	public void removeStack(int stackValue) {
for (int i = 0; i < stack.size(); i++) {
			
			if (stack.get(i).getStackValue() == stackValue) {
				stack.remove(i);
				
			}
			
		}
		
	}
	
	
}
