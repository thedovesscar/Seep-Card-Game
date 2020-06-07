import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Player1Panel extends JPanel{
	
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
	
	/**
	 * Constructing P1P Panel
	 */
	public Player1Panel() {

		// TODO Auto-generated constructor stub
		setPreferredSize(new Dimension(200, 500));
		this.setBackground(new Color(144,144,144));

		gameSeep = Seep.getInstance();

		card1Label = new JLabel(gameSeep.hand[0].getCard(0).getCardName());
		card2Label = new JLabel(gameSeep.hand[0].getCard(1).getCardName());
		card3Label = new JLabel(gameSeep.hand[0].getCard(2).getCardName());
		card4Label = new JLabel(gameSeep.hand[0].getCard(3).getCardName());
		card5Label = new JLabel(gameSeep.hand[0].getCard(4).getCardName());
		card6Label = new JLabel(gameSeep.hand[0].getCard(5).getCardName());
		card7Label = new JLabel(gameSeep.hand[0].getCard(6).getCardName());
		card8Label = new JLabel(gameSeep.hand[0].getCard(7).getCardName());
		card9Label = new JLabel(gameSeep.hand[0].getCard(8).getCardName());
		card10Label = new JLabel(gameSeep.hand[0].getCard(9).getCardName());
		card11Label = new JLabel(gameSeep.hand[0].getCard(10).getCardName());
		card12Label = new JLabel(gameSeep.hand[0].getCard(11).getCardName());
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
