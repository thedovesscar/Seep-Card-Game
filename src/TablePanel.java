import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TablePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1678772327412945201L;

	
	
	// TODO For Now! table cannot be statically configured! can hold 0 - 52 cards!
	private JLabel card1Label;
	private JLabel card2Label;
	private JLabel card3Label;
	private JLabel card4Label;
	private Dimension cardDimension = new Dimension(10, 50);
	public Seep gameSeep;
	
	public TablePanel() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(600, 300));
		this.setBackground(new Color(111,11,111));
		
		gameSeep = Seep.getInstance();
		
		//TODO this is what final might look like once each picture has an associated picture.
//		card1Label = new JLabel(new ImageIcon(gameSeep.table.getCard(0).getCardImg()));
		card1Label = new JLabel(new ImageIcon(gameSeep.table.getCard(0).getCardImg()));
		card2Label = new JLabel(new ImageIcon(gameSeep.table.getCard(1).getCardImg()));
		card3Label = new JLabel(new ImageIcon(gameSeep.table.getCard(2).getCardImg()));
		card4Label = new JLabel(new ImageIcon(gameSeep.table.getCard(3).getCardImg()));
		card1Label.setSize(cardDimension);
		card2Label.setSize(cardDimension);
		card3Label.setSize(cardDimension);
		card4Label.setSize(cardDimension);
		this.add(card1Label);
		this.add(card2Label);
		this.add(card3Label);
		this.add(card4Label);

	}

}
