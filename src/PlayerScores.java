
public class PlayerScores {

	private int score;
	private String name;
	
	public PlayerScores() {
		score = 0;
		name = "";
	}
	
	public PlayerScores(int score, String name) {
		this.score = score;
		this.name = name;
	}
	
	/**
	 * getter
	 * @return score 
	 */
	public int getScore() {
		return score;
	}

	/**
	 * setter
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * getter
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * setter
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This will be the result of the two string method
	 */
	public String toString() {
		
		String message = "";
		try {
			message = getName().toUpperCase() + ": " + String.valueOf(getScore());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return message;
		
	} //end of toString
	
}
