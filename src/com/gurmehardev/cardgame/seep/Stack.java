package com.gurmehardev.cardgame.seep;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import com.gurmehardev.cardgame.*;

public class Stack {

	boolean doubled;
	boolean facePile;
	private int stackValue;
	private ArrayList<Card> cardStack;
	
	/**
	 * This constructor should only be called when creating a new Stack 
	 * or  when placing down cards because then stacks of the same value
	 * can exist
	 * 
	 * But when players start throwing cards down They must pick up 
	 * that card's existing stack unless stacking facecard on a stack.
	 * @param card
	 */
	public Stack(Card card) {
		cardStack = new ArrayList<Card>();
		cardStack.add(card);
		stackValue = card.getCardNumber();
		if (stackValue == 13) 
			doubled = true;
		else doubled = false;
		if (stackValue > 8) {
			facePile = true;
		}
		else facePile = false;
	}

	
	/**
	 * This Constructor is called when a player is building a
	 * stack that did not previously exist and now we are putting
	 * two or more new cards into this stack
	 * @param card
	 * @param stackVal
	 */
	public Stack(Card card, int stackVal) {
		cardStack = new ArrayList<Card>();
		cardStack.add(card);
		stackValue = stackVal;
		if (stackValue == 13) 
			doubled = true;
		else doubled = false;
		if (stackValue > 8) {
			facePile = true;
		}
		else facePile = false;
	}
	
	public void doubleStack(boolean doubling) {
		if (doubling) {
			doubled = doubling;
		}
	}
	
	/**
	 * This method checks for and returns the specific stackValue
	 * 
	 * @return the value of the Stack
	 */
	public int getStackValue() {
		return stackValue;
	}

	/**
	 * 
	 * @return an ArrayList of Cards from this stack
	 */
	public ArrayList<Card> getCardStack() {
		return cardStack;
	}
	
	/**
	 * 
	 * @returm int: amount of Cards stored in this stack
	 */
	public int getCardCount() {
		return cardStack.size();
	}

	/**
	 * 
	 * @param cardStack Setter I dont think i need this really
	 */
	public void setCardStack(ArrayList<Card> cardStack) {
		this.cardStack = cardStack;
	}
	
	/**
	 * this method will add card to the stack
	 * 
	 * Ideal for when needing to add card to an existing stack
	 * "i.e. Building a Stack"
	 * @param card
	 */
	public void addCard(Card card) {
		cardStack.add(card);
	}
	
	/**
	 * This method does a live check on the cards contained
	 * to see whether or not the Stack is Pakka or Final.
	 * It counts up the total value of all cards and checks if it
	 * a multiple of StackValue other than 1.
	 * 
	 * It checks 
	 * @return True if Stack is doubled or Pakka.
	 * 			False if Stack is not doubled or Kachi
	 * 			Throws error if the value of cards stored
	 * is not equal to a multiple of this stack's int StackValue
	 */
	public boolean isDoubled() {
		int count = 0;
		
		for ( int i = 0; i < cardStack.size(); i++) {
			count += cardStack.get(i).getCardNumber();
		}
		
		if (count != stackValue && count%stackValue == 0) {
			doubled = true;
		}
		else if (count == stackValue) {
			doubled = false;
		} else  if (count != stackValue && count%stackValue != 0){
			throw new RuntimeErrorException(new Error(), "The cards in stack" 
					+ stackValue + " don't add up correctly!");
		}
		return doubled;
	}
	
	/**
	 * Call this method to check if this stack
	 * contains more than one care.
	 * 
	 * If it does it cannot be added with a another stack to 
	 * make a larger number, to pick up.
	 * 
	 * This is not to say it cannot be broken.
	 * @return
	 */
	public boolean isBeingBuilt() {
		if (cardStack.size() > 1) {
			return true;
		}
		return false;
	}
}
