import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import sun.nio.cs.ext.TIS_620;

public class MiddlePanel extends JPanel{

	Player2Panel p2p;
	TablePanel tp;
	
	public MiddlePanel() {
		
		p2p = new Player2Panel();
		tp = new TablePanel();
		
		setPreferredSize(new Dimension(600,500));
		setBackground(new Color(4,44,244));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(p2p);
		add(tp);
	}
	
	public void setupTable() {
		tp.dealCards();
		this.revalidate();
		this.repaint();
	}
	public void dealCards(boolean asking) {
		if (asking)  p2p.dealCards(true);
		else  p2p.dealCards(false);
		this.revalidate();
		this.repaint();
	}
	
	public void clearHands() {
		tp.clearTable();
		p2p.clearHand();
		this.revalidate();
		repaint();
	}

}
