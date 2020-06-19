import java.io.*;
import java.util.*;

/**
 * This class deals with reading and writing the High
 * Scores for the Counting game. 
 * It will record the top 5 scores and store them.
 * 
 * @author Gopa
 *
 */
public class TextFileIO {
	
	PrintWriter out;
	File file;
	Scanner sc;
	
	
	public TextFileIO() {
		
		
		try {
			//init File
			file = new File("HighScores.txt");
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		
		try {
			out = new PrintWriter( new FileWriter(file)); 
			//out will have automatic flushing because of true Boolean
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	} //end Constructor
	
	public PlayerScores readFile(){
		int score = 0;
		String name = "";
		try {
		
			BufferedReader in = new BufferedReader(new FileReader("HighScores.txt"));
			score = in.read();
			//score = Integer.parseInt(line);
			String line = in.readLine();
			name = line;
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return new PlayerScores(score,name);
	}
	
	/**
	 * this method will write each input as one line in the text file
	 * 
	 * @param score name
	 */
	public void writeTextLine(PlayerScores player) {
		out.println(player.getScore()); //printing data
		out.println(player.getName());
		out.close();
		try {
			out = new PrintWriter( new FileWriter(file, true)); 
			//out will have automatic flushing because of true Boolean
		} catch (IOException e) {
			e.printStackTrace();
		} 
	} // End of writeTextLine method


	
	public void close()	{
		out.close();
	}
	
	
	
	

}
