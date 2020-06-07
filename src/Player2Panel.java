import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player2Panel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	public Player2Panel() {
		this.setPreferredSize(new Dimension(600, 200));
		this.setBackground(new Color(141,11,11));

		gameSeep = Seep.getInstance();

		card1Label = new JLabel(gameSeep.hand[1].getCard(0).getCardName());
		card2Label = new JLabel(gameSeep.hand[1].getCard(1).getCardName());
		card3Label = new JLabel(gameSeep.hand[1].getCard(2).getCardName());
		card4Label = new JLabel(gameSeep.hand[1].getCard(3).getCardName());
		card5Label = new JLabel(gameSeep.hand[1].getCard(4).getCardName());
		card6Label = new JLabel(gameSeep.hand[1].getCard(5).getCardName());
		card7Label = new JLabel(gameSeep.hand[1].getCard(6).getCardName());
		card8Label = new JLabel(gameSeep.hand[1].getCard(7).getCardName());
		card9Label = new JLabel(gameSeep.hand[1].getCard(8).getCardName());
		card10Label = new JLabel(gameSeep.hand[1].getCard(9).getCardName());
		card11Label = new JLabel(gameSeep.hand[1].getCard(10).getCardName());
		card12Label = new JLabel(gameSeep.hand[1].getCard(11).getCardName());
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
