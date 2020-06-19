package com.gurmehardev.gamepanel;

import com.gurmehardev.cardgame.seep.*;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player3Panel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6486536503039196559L;

	private JLabel p3Label;
	private ArrayList<JLabel> cardLabel;
	public Seep gameSeep;
	
	public Player3Panel() {
		setPreferredSize(new Dimension(265, 500));
		this.setBackground(new Color(91,99,111));

		gameSeep = Seep.getInstance();
		cardLabel = new ArrayList<JLabel>();
		p3Label = new JLabel("Player 3.");
		add(p3Label);

	}
	
	public void dealCards() {
		this.removeAll();
		cardLabel.clear();
		JLabel myTurnJLabel = new JLabel("Player Three's Turn!");
		
		if (Seep.currentPlayer == 3) {
			add(myTurnJLabel);
		}
		for (int i = 0; i < gameSeep.hand[3].getCardCount(); i++) {
			cardLabel.add(new JLabel(new ImageIcon(gameSeep.hand[3].getCard(i).getCardImg())));
			add(cardLabel.get(i));
		}
		this.revalidate();
		this.repaint();
		
	}
	public void clearHand() {
		removeAll();
		revalidate();
		repaint();
	}
}