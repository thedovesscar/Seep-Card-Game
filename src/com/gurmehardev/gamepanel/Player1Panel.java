package com.gurmehardev.gamepanel;

import com.gurmehardev.cardgame.seep.*;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player1Panel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel p1Label;
	private ArrayList<JLabel> cardLabel;
	public Seep gameSeep;
	
	/**
	 * Constructing P1P Panel
	 */
	public Player1Panel() {

		// TODO Auto-generated constructor stub
		setPreferredSize(new Dimension(265, 500));
		this.setBackground(new Color(144,144,144));

		gameSeep = Seep.getInstance();

		p1Label = new JLabel("Player 1!");
		cardLabel = new ArrayList<JLabel>();
		this.add(p1Label);
	}
	
	public void dealCards() {
		this.removeAll();
		cardLabel.clear();
		
		JLabel myTurnJLabel = new JLabel("Player One's Turn!");
		
		if (Seep.currentPlayer == 1) {
			add(myTurnJLabel);
		}
		for (int i = 0; i < gameSeep.hand[1].getCardCount(); i++) {
			cardLabel.add(new JLabel(new ImageIcon(gameSeep.hand[1].getCard(i).getCardImg())));
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
