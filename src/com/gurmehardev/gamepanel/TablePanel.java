package com.gurmehardev.gamepanel;

import com.gurmehardev.cardgame.seep.*;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TablePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1678772327412945201L;

	
	
	// TODO For Now! table cannot be statically configured! can hold 0 - 52 cards!
	private ArrayList<JLabel> cardLabel;
	public Seep gameSeep;
	
	public TablePanel() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(670, 300));
		this.setBackground(new Color(111,11,111));
		
		gameSeep = Seep.getInstance();
		cardLabel = new ArrayList<JLabel>();

	}
	
	public void dealCards() {
		cardLabel.clear();
		
		for (int i = 0; i < gameSeep.table.getCardCount(); i++) {
			cardLabel.add(new JLabel(new ImageIcon(gameSeep.table.getCard(i).getCardImg())));
			add(cardLabel.get(i));
		}
		this.revalidate();
		this.repaint();
	}
	
	public void clearTable() {
		removeAll();
	}

}
