import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Player1Panel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel p1Label;
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
		setPreferredSize(new Dimension(265, 500));
		this.setBackground(new Color(144,144,144));

		gameSeep = Seep.getInstance();

		p1Label = new JLabel("Player 1!");
		card1Label = new JLabel(new ImageIcon(gameSeep.hand[1].getCard(0).getCardImg()));
		card2Label = new JLabel(new ImageIcon(gameSeep.hand[1].getCard(1).getCardImg()));
		card3Label = new JLabel(new ImageIcon(gameSeep.hand[1].getCard(2).getCardImg()));
		card4Label = new JLabel(new ImageIcon(gameSeep.hand[1].getCard(3).getCardImg()));
		card5Label = new JLabel(new ImageIcon(gameSeep.hand[1].getCard(4).getCardImg()));
		card6Label = new JLabel(new ImageIcon(gameSeep.hand[1].getCard(5).getCardImg()));
		card7Label = new JLabel(new ImageIcon(gameSeep.hand[1].getCard(6).getCardImg()));
		card8Label = new JLabel(new ImageIcon(gameSeep.hand[1].getCard(7).getCardImg()));
		card9Label = new JLabel(new ImageIcon(gameSeep.hand[1].getCard(8).getCardImg()));
		card10Label = new JLabel(new ImageIcon(gameSeep.hand[1].getCard(9).getCardImg()));
		card11Label = new JLabel(new ImageIcon(gameSeep.hand[1].getCard(10).getCardImg()));
		card12Label = new JLabel(new ImageIcon(gameSeep.hand[1].getCard(11).getCardImg()));
		this.add(p1Label);
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
