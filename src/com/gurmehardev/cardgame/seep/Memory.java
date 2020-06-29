package com.gurmehardev.cardgame.seep;

/**
 * TODO
 * This class will/should contain a static hold of the gameplay
 * memory that will be shared by all members of the game.
 * 
 * The "collective memory" as it were.
 * 
 * @author Gurmehar
 *
 */
public class Memory {

	private volatile static Memory instance = null;
	
	private Memory() {
		if (instance != null) {
			throw new RuntimeException("Use getInstance() method "
					+ "to create a Memory Instance...");
		}
		
 	}
	
	public Memory getInstance() {
		if (instance == null) {
			synchronized (Memory.class) {
				if (instance == null) {
					instance = new Memory();
				}
			}
		}
		return instance;
	}
	
	/**
	 * This method should be called at the end of every play-through
	 * to restart the collective memory for the AI players.
	 */
	public static void clearMemory() {
		
	}

}
