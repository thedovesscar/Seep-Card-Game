package com.gurmehardev.cardgame.seep;

import java.util.ArrayList;

import com.gurmehardev.cardgame.Card;

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
	
	private ArrayList<Stack> making;
	private ArrayList<Card> makeables;
	private ArrayList<Card> made;
	private ArrayList<Card> known;
	private ArrayList<Card> out;
	
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
	
	public static void addingStack() {
		
	}
	/**
	 * This method should be called at the end of every play-through
	 * to restart the collective memory for the AI players.
	 */
	public static void clearMemory() {
		
	}

}
