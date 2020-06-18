package com.gurmehardev.gamepanel;

import com.gurmehardev.cardgame.seep.*;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player2Panel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel p2Label;
	private ArrayList<JLabel> cardLabel;
	public Seep gameSeep;
	
	public Player2Panel() {
		this.setPreferredSize(new Dimension(670, 250));
		this.setBackground(new Color(40,11,11));

		gameSeep = Seep.getInstance();
		p2Label = new JLabel("Player 2?");
		cardLabel = new ArrayList<JLabel>();
		add(p2Label);
		
	}
	
	public void dealCards() {
		this.removeAll();
		cardLabel.clear();
		
		for (int i = 0; i < gameSeep.hand[2].getCardCount(); i++) {
			cardLabel.add(new JLabel(new ImageIcon(gameSeep.hand[2].getCard(i).getCardImg())));
			add(cardLabel.get(i));
		}
		this.revalidate();
		this.repaint();

	}
	
	public void clearHand() {
		removeAll();
	}

}
