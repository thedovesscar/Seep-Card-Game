package com.gurmehardev.cardgame.seep;

import com.gurmehardev.cardgame.*;
import java.util.ArrayList;

public class Stash {

	private int cardCount;
	private int points;
	private ArrayList<Card> stash;
	private int seep;
	
	public Stash() {
		cardCount = 0;
		points = 0;
		stash = new ArrayList<Card>();
		seep = 0;
	}
	
	/**
	 * Clearing Stash to give fresh start
	 */
	public void clear() {
		stash.clear();
		seep = 0;
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
		points = 0;
		for (Card c: stash) {
			points += c.getCardPoints();
		}
		int cardAmount = getCardCount();
		
		if (cardAmount > 26) {
			points += 4;
		} else if (cardAmount == 26) {
			points += 2;
		}
		
		if (seep != 0) {
			for (int s = seep; s > 0; s--) {
				points += 50;
			}
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

	public void clearEverything() {
		
	}
	
	public void seep() {
		seep++;
	}
}
