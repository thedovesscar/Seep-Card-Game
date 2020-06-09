import java.util.ArrayList;

import sun.security.jca.GetInstance.Instance;

public class Table {

	private volatile static Table instance = null;
	
	private ArrayList<Card> table;
	
	private Table() {
		if (instance != null) {
			throw new RuntimeException("Use getInstance() method "
					+ " to create SingletonExample instance");
		}
		
		
		table = new ArrayList<Card>();
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
	
	public void addCard(Card card) {
		table.add(card);
	} //end of addCard to Table
	
	public void removeCard(Card card) {
		table.remove(card);
	}
	
	public void clear() {
		table.clear();
	}
	
	public int getCardCount() {
		return table.size();
	}
	
	public Card getCard(int position) {
		return table.get(position);
	}
	
}
