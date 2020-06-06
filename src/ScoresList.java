import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.JOptionPane;


/**
 * 
 * @author Ervin & Gurmehar
 *
 */
public class ScoresList {

	private static ArrayList<PlayerScores> scoresList;
	
	TextFileIO scoresFile; //for accessing files
	
	String input;
	Scanner sc;
	/**
	 * Default Constructor
	 * Initializes PlayerScores ArrayList
	 */
	public ScoresList() {
		scoresFile = new TextFileIO();
		scoresList = new ArrayList<PlayerScores>();
		
		for (int i = 0; i < 5; i++) {
			PlayerScores player = scoresFile.readFile();
			scoresList.add(player);
		}
	}
	
	
	/**
	 * This is to add any current player's score into the 
	 * Arraylist
	 * @param friend
	 */
	public void addScore(PlayerScores player){
		scoresList.add(player);
	} //end addFriend
	
	/**
	 * this uses Collections sort method
	 */
	public void sortScores() {
		Collections.sort(scoresList, new ScoreComparator());
	} //end sortFriends Method
	
	/**
	 * This will show all top five scores.
	 */
	public void showScoresList() {
		sortScores();
		
		String message = "";
		
			for (int i = 0; i < scoresList.size() && i < 5; i++) {
				message += (i + 1) + ". " + scoresList.get(i).toString() + "\n";
			} 
		
		JOptionPane.showMessageDialog(null, message, "High Scores!", -1);
	}

	public void writeScores() {
		sortScores();
		for(int i = 0; i < 5 && i < scoresList.size(); i++) {
			 scoresFile.writeTextLine(scoresList.get(i));//end if
		} //end for
		scoresFile.close();
	}//end method : writeScores()
	
} //end of class ScoresList	
	
	
	
class ScoreComparator implements Comparator<PlayerScores>  {
	 	
		public int compare(PlayerScores Player1, PlayerScores Player2) {

			if (Player1.getScore() > Player2.getScore())
			  return -1;

			 if (Player1.getScore() < Player2.getScore())
			  return 1;

	 		return 0;
	 	} // end of compare
		
} //end of PlayerComparator

