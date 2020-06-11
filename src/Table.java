import java.util.ArrayList;

import javax.swing.JOptionPane;

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
	 * currently used to set up table cards and when player throws down a Card
	 * Not sufficient when going to add on to a stack.
	 * @param card
	 */
	public void addCard(Card card) {
		table.add(card);
		addCardtoStack(card);
	} //end of addCard to Table

	/**
	 * Place HOLDer for when i want to add Card to stack
	 */
	public void addCard() {
//		table.add(card);
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
				}
			} 
		}  //
		if (!stackExists) {
			stack.add(new Stack(card));
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
				stack.remove(i);
			}
		}

		for (Stack s: stack) {
			System.out.println(s.getStackValue()); 
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
	
}
