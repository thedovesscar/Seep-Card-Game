import java.util.ArrayList;

/**
 * creating this class because i believe it will make more sense to keep track of 
 * stats and gametime variables here versus within the Seep class.
 * Seep can then utilize this information later on.
 * 
 * @author Gurmehar
 *
 */
public class Team {

	private int realtimeScore;
	private int cumultiveScore;
	private Stash stash;
	
	//Maybe i can get rid of the Array List of Cards and put something entirely else
	//TODO maybe another class??? one that deals with simply the logic of making TERIYAAN
	private ArrayList<Card> making;
	private ArrayList<Card> makeables;
	private ArrayList<Card> made;
	private ArrayList<Card> known;
	private ArrayList<Card> out;
	
	public Team() {
		realtimeScore = 0;
		cumultiveScore = 0;
		stash = new Stash();
		
		making = new ArrayList<Card>();
		makeables = new ArrayList<Card>();
		made = new ArrayList<Card>();
		known = new ArrayList<Card>();
		out = new ArrayList<Card>();
	}


	public int getRealtimeScore() {
		realtimeScore = stash.countPoints();
		cumultiveScore += realtimeScore;
		return realtimeScore;
	}

	public int getCumultiveScore() {
		return cumultiveScore;
	}
	
	public void newGame() {
		realtimeScore = 0;
		stash.clear();
	}

	public void addCard(Card card) {
		stash.addCard(card);
	}
	

}
