package com.gurmehardev.cardgame.seep;

import com.gurmehardev.cardgame.*;

import java.util.ArrayList;


/**
 * This class should have extended hands.
 * @author Gurmehar
 *
 */
public class Table {

	private volatile static Table instance = null;
	
	private ArrayList<Card> table;
	private static ArrayList<Stack> stack;
	
	private Table() {
		if (instance != null) {
			throw new RuntimeException("Use getInstance() method "
					+ " to create SingletonExample instance");
		}
		
		table = new ArrayList<Card>();
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
	public void addAskingCard(Card card) {
		table.add(card);
		addCardtoStack(card);
		sortByValue();
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
		table.add(card);
		addCardtoStack(card);
		sortByValue();
	}
	
	public void addCard(Card card, int stackVal) {
		table.add(card);
		int stackSz = stack.size();
		for (int x = 0; x < stackSz; x++) {
			if (stack.get(x).getStackValue() == stackVal) {
				stack.get(x).addCard(card);
				return;
			}
		}
		stack.add(new Stack(card, stackVal));
		sortByValue();
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
			stack.add(new Stack(card, false));
			System.out.println("A stack of " + cardNum + " has been created");
		}
		
	}
	
	public void removeCard(Card card) {
		table.remove(card);
		removeCardfromStack(card);
	}
	
	private void removeCardfromStack(Card card) {
		int cardNum = card.getCardNumber();
		
		for (int i = 0; i < stack.size(); i++) {
			
			if (stack.get(i).getStackValue() == cardNum) {
				System.out.println("Stack of " + stack.get(i).getStackValue() + " is removed.");
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
		table.clear();
		clearStacks();
	}
	
	private void clearStacks() {
		stack.clear();
	}
	
	public int getCardCount() {
		return table.size();
	}
	
	public Card getCard(int position) {
		return table.get(position);
	}
	
	public void sortByStack() {
        ArrayList<Stack> newStack = new ArrayList<Stack>();
        while (table.size() > 0) {
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
	public void sortByValue() {
        ArrayList<Card> newTable = new ArrayList<Card>();
        while (table.size() > 0) {
            int pos = 0;  // Position of minimal card.
            Card c = table.get(0);  // Minimal card.
            for (int i = 1; i < table.size(); i++) {
                Card c1 = table.get(i);
                if ( c1.getCardNumber() < c.getCardNumber() ||
                        (c1.getCardNumber() == c.getCardNumber() /* && c1.getCardSuit() < c.getCardSuit() */)) {
                    pos = i;
                    c = c1;
                }
            }
            table.remove(pos);
            newTable.add(c);
        }
        table = newTable;
    }
	
	public Stack getStack(int i) {
		return stack.get(i);
	}
	public int getStackValue(int i) {
		return stack.get(i).getStackValue();
	}
	
	public ArrayList<Card> getStackofCards(int i) {
		return stack.get(i).getCardStack();
	}
	public int getStackCount() {
		return stack.size();
	}
	
}
