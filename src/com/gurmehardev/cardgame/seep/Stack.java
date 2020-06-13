package com.gurmehardev.cardgame.seep;

import java.util.ArrayList;

import com.gurmehardev.cardgame.*;

public class Stack {

	boolean doubled;
	boolean facePile;
	private int stackValue;
	private ArrayList<Card> cardStack;
	
	/**
	 * This can be called during when laying down table cards
	 * Because it doesnt matter if same card comes down. 
	 * 
	 * But when players start throwing cards down They must pick up 
	 * that card's existing stack unless stacking facecard on a stack.
	 * @param card
	 */
	public Stack(Card card) {
		cardStack = new ArrayList<Card>();
		cardStack.add(card);
		stackValue = card.getCardNumber();
		doubled = false;
		if (stackValue > 8) {
			facePile = true;
		}
		else facePile = false;
	}
	
	/**
	 * This should be called later when players are throwing down cards
	 * @param card
	 * @param doubling
	 */
	public Stack(Card card, boolean doubling) {
		cardStack = new ArrayList<Card>();
		cardStack.add(card);
		stackValue = card.getCardNumber();
		doubled = doubling;
		if (stackValue > 8) {
			facePile = true;
		}
		else facePile = false;
	}
	
	public Stack(Card card, int stackVal) {
		
		cardStack = new ArrayList<Card>();
		cardStack.add(card);
		stackValue = stackVal;
		doubled = false;
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
	
	public int getStackValue() {
		return stackValue;
	}

	public ArrayList<Card> getCardStack() {
		return cardStack;
	}

	public void setCardStack(ArrayList<Card> cardStack) {
		this.cardStack = cardStack;
	}
	
	public void addCard(Card card) {
		cardStack.add(card);
	}
	
	public void addCardtoStack(Card card, int i) {
		
	}
	
}
