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
	
	private static ArrayList<Stack> team1making;
	private static ArrayList<Stack> team2making;
	private static ArrayList<Card> player1has;
	private static ArrayList<Card> player2has;
	private static ArrayList<Card> player3has;
	private static ArrayList<Card> player4has;
	private static ArrayList<Card> team1has;
	private static ArrayList<Card> team2has;
	private static ArrayList<Card> inPlay;
	private static ArrayList<Card> out;
	
	private Memory() {
		if (instance != null) {
			throw new RuntimeException("Use getInstance() method "
					+ "to create a Memory Instance...");
		}
		team1making = new ArrayList<Stack>();
		team2making = new ArrayList<Stack>();
		team1has = new ArrayList<Card>();
		team2has = new ArrayList<Card>();
		inPlay = new ArrayList<Card>();
		out = new ArrayList<Card>();
 	}
	
	public static Memory getInstance() {
		if (instance == null) {
			synchronized (Memory.class) {
				if (instance == null) {
					instance = new Memory();
				}
			}
		}
		return instance;
	}
	
	public static void addCardtoMemory(Card card) {
		inPlay.add(card);
	}
	
	public static void buildingStack(int player, int stkVal) {
		
	}
	
	/**
	 * This will check whether or not the Spade version
	 * of the given value is still in play
	 * 
	 * @param int cardVal
	 * @return boolean; true - if Spade is still in play...
	 */
	public static boolean isSpadeStillInPlay(int cardVal) {
		for (Card card: out) {
			if (card.getCardSuit() == Card.SPADE && card.getCardNumber() == cardVal) { 
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Once a stack is picked up players will be able to remember 
	 * which card was picked up...which 
	 * @param stk
	 * @param c
	 */
	public static void stackPickedUp(Stack stk, Card c) {
		out.add(c);
		ArrayList<Card> stack = stk.getCardStack();
		for (Card card: stack) {
			out.add(card);
		}
	}
	
	/**
	 * This method will let player know how many card of
	 * a certain value are left in play. 
	 * 
	 * @param int n - the value player is thinking about
	 * @return - the amount of cards that are already out.
	 */
	public static int stillInPlay(int n) {
		int picked = 0;
		for (Card card: out) {
			if (card.getCardNumber() == n) {
				picked++;
			}
		}
		return picked;
	}
	
	/**
	 * This method should be called at the end of every play-through
	 * to restart the collective memory for the AI players.
	 */
	public static void clearMemory() {
		team1making.clear();
		team2making.clear();
		team1has.clear();
		team2has.clear();
		inPlay.clear();
		out.clear();
	}

}
