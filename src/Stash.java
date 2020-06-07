import java.util.ArrayList;

public class Stash {

	private int cardCount;
	private int points;
	private ArrayList<Card> stash;
	
	public Stash() {
		cardCount = 0;
		points = 0;
		stash = new ArrayList<Card>();
	}
	
	/**
	 * Clearing Stash to give fresh start
	 */
	public void clear() {
		stash.clear();
	}
	
	/**
     * Add a card to the stash.
     * @param c the non-null card to be added.
     * @throws NullPointerException if the parameter c is null.
     */
	public void addCard(Card c) {
        if (c == null)
            throw new NullPointerException("Can't add a null card to a hand.");
        stash.add(c);
    }
	
	/**
	 * 
	 * @return team's points
	 */
	public int countPoints() {
		
		for (Card c: stash) {
			points += c.getCardPoints();
		}
		int cardAmount = getCardCount();
		
		if (cardAmount > 26) {
			points += 6;
		} else if (cardAmount == 26) {
			points += 3;
		}
			
		return points;
	}
	
	/**
	 * 
	 * @return amount of Cards team has gotten
	 */
	public int getCardCount() {
		cardCount = stash.size();
		return cardCount;
	}

}
