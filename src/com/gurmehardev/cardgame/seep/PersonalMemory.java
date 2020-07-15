package com.gurmehardev.cardgame.seep;

import java.util.ArrayList;

import com.gurmehardev.cardgame.Card;
import com.gurmehardev.cardgame.Hand;

/**
 * TODO 
 * This class should/will contain all of the static
 * information of the Collective Memory of the game in
 * Memory class.
 * It will then also contain the personal 'memory' of the 
 * player's hand in conjunction with the game history/memory.
 * 
 * @author Gurmehar
 *
 */
public class PersonalMemory {

	ArrayList<Card> myCards = new ArrayList<Card>();
	
	public PersonalMemory(Hand hand) {
		for (int i = 0; i < hand.getCardCount(); i++) {
			myCards.add(hand.getCard(i));
		}
	}

	/**
	 * Once a card is thrown by a player it enters 
	 * the collective memory and needs to be removed from personal memory
	 * 
	 * @param card
	 */
	public void playingCard(Card card) {
		if (myCards.remove(card)) {
			Memory.addCardtoMemory(card);
		}
		
	} //end of playingCard()
	
} //end of Personal Memory
