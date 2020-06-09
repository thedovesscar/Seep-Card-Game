import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player3Panel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6486536503039196559L;

	private JLabel p3Label;
	private JLabel card1Label;
	private JLabel card2Label;
	private JLabel card3Label;
	private JLabel card4Label;
	private JLabel card5Label;
	private JLabel card6Label;
	private JLabel card7Label;
	private JLabel card8Label;
	private JLabel card9Label;
	private JLabel card10Label;
	private JLabel card11Label;
	private JLabel card12Label;
	public Seep gameSeep;
	
	public Player3Panel() {
		setPreferredSize(new Dimension(265, 500));
		this.setBackground(new Color(91,99,111));

		gameSeep = Seep.getInstance();
		
		p3Label = new JLabel("Player 3.");
		add(p3Label);

	}
	
	public void dealCards(boolean asking) {
		
		if (asking) {

			card1Label = new JLabel((new ImageIcon(gameSeep.hand[3].getCard(0).getCardImg())));
			card2Label = new JLabel(new ImageIcon(gameSeep.hand[3].getCard(1).getCardImg()));
			card3Label = new JLabel(new ImageIcon(gameSeep.hand[3].getCard(2).getCardImg()));
			card4Label = new JLabel(new ImageIcon(gameSeep.hand[3].getCard(3).getCardImg()));
			this.add(card1Label);
			this.add(card2Label);
			this.add(card3Label);
			this.add(card4Label);
		}
		
		else {

			card1Label = new JLabel((new ImageIcon(gameSeep.hand[3].getCard(0).getCardImg())));
			card2Label = new JLabel(new ImageIcon(gameSeep.hand[3].getCard(1).getCardImg()));
			card3Label = new JLabel(new ImageIcon(gameSeep.hand[3].getCard(2).getCardImg()));
			card4Label = new JLabel(new ImageIcon(gameSeep.hand[3].getCard(3).getCardImg()));
			card5Label = new JLabel(new ImageIcon(gameSeep.hand[3].getCard(4).getCardImg()));
			card6Label = new JLabel(new ImageIcon(gameSeep.hand[3].getCard(5).getCardImg()));
			card7Label = new JLabel(new ImageIcon(gameSeep.hand[3].getCard(6).getCardImg()));
			card8Label = new JLabel(new ImageIcon(gameSeep.hand[3].getCard(7).getCardImg()));
			card9Label = new JLabel(new ImageIcon(gameSeep.hand[3].getCard(8).getCardImg()));
			card10Label = new JLabel(new ImageIcon(gameSeep.hand[3].getCard(9).getCardImg()));
			card11Label = new JLabel(new ImageIcon(gameSeep.hand[3].getCard(10).getCardImg()));
			card12Label = new JLabel(new ImageIcon(gameSeep.hand[3].getCard(11).getCardImg()));
			this.add(card1Label);
			this.add(card2Label);
			this.add(card3Label);
			this.add(card4Label);
			this.add(card5Label);
			this.add(card6Label);
			this.add(card7Label);
			this.add(card8Label);
			this.add(card9Label);
			this.add(card10Label);
			this.add(card11Label);
			this.add(card12Label);
		}
		
	}
	public void clearHand() {
		removeAll();
		revalidate();
		repaint();
	}
}
