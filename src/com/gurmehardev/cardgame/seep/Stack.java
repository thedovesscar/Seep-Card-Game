import java.util.ArrayList;

public class Stack {

	boolean doubled;
	private int stackValue;
	
	/**
	 * This can be called during when laying down table cards
	 * Because it doesnt matter if same card comes down. 
	 * 
	 * But when players start throwing cards down They must pick up 
	 * that card's existing stack unless stacking facecard on a stack.
	 * @param card
	 */
	public Stack(Card card) {
		stackValue = card.getCardNumber();
		doubled = false;
	}
	
	/**
	 * This should be called later when players are throwing down cards
	 * @param card
	 * @param doubling
	 */
	public Stack(Card card, boolean doubling) {
		stackValue = card.getCardNumber();
		doubled = doubling;
	}
	
	public int getStackValue() {
		return stackValue;
	}
	
}
