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
	private ArrayList<JLabel> stackLabel;
	public Seep gameSeep;
	
	public TablePanel() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(670, 250));
		this.setBackground(new Color(111,11,111));
		
		gameSeep = Seep.getInstance();
		cardLabel = new ArrayList<JLabel>();
		stackLabel = new ArrayList<JLabel>();

	}
	
	public void dealCards() {
		stackLabel.clear();
		cardLabel.clear();
		int l = 0;
		int stacks = gameSeep.table.getStackCount();
		for (int i = 0; i < stacks; i++) {
			stackLabel.add(new JLabel("Stack of " + gameSeep.table.getStackValue(i)));
			add(stackLabel.get(i));
			for (int j = 0; j < gameSeep.table.getStack(i).getCardStack().size(); j++) {
				cardLabel.add(new JLabel(new ImageIcon(gameSeep.table.getStackofCards(i).get(j).getCardImg())));
				add(cardLabel.get(l));
				l++;
			}
		}
		
		this.revalidate();
		this.repaint();
	}
	
	public void clearTable() {
		removeAll();
	}

}
