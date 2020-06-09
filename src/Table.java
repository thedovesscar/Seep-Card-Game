import java.util.ArrayList;

public class Table {

	private ArrayList<Card> table;
	
	public Table() {
		table = new ArrayList<Card>();
	} // Table constructor

	public void addCard(Card card) {
		table.add(card);
	} //end of addCard to Table
	
	public void removeCard(Card card) {
		table.remove(card);
	}
	
	public void clear() {
		table.clear();
	}
	
	public Card getCard(int position) {
		return table.get(position);
	}
	
}
