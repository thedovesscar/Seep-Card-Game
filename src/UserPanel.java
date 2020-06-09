import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserPanel extends JPanel implements ActionListener {

	private static volatile UserPanel instance = null;
	/**
	 * default serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel card1Label;
	JLabel card2Label;
	JLabel card3Label;
	JLabel card4Label;
	JLabel card5Label;
	JLabel card6Label;
	JLabel card7Label;
	JLabel card8Label;
	JLabel card9Label;
	JLabel card10Label;
	JLabel card11Label;
	JLabel card12Label;
	
	JLabel pointsInHandJLabel;
	
	
	Seep gameSeep;
	
	
	
	///////////////////////
	static String wrongFile;
	static String rightFile;
	
	AudioInputStream audioInputStream;
	
	Toolkit tk;
	
	Dimension butDim = new Dimension(100,100);
	
	AudioClips wrongGuess;
	AudioClips rightGuess;  //these are the soundclips for answers
	
	/**
	 * Constructor
	 */
	private UserPanel() {
		 
		setPreferredSize(new Dimension(1200,200)); //EDIT added to fit Frame
		gameSeep = Seep.getInstance();
		
		
	} //END of ButtonPanel Constructor
	public void dealCards(boolean asking) {

		if (asking == true) {
			card1Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(0).getCardImg()));
			card2Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(1).getCardImg()));
			card3Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(2).getCardImg()));
			card4Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(3).getCardImg()));
			
			this.add(card1Label);
			this.add(card2Label);
			this.add(card3Label);
			this.add(card4Label);
			
			this.revalidate();
			this.repaint();
		}
		
		else {card1Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(0).getCardImg()));
		card2Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(1).getCardImg()));
		card3Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(2).getCardImg()));
		card4Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(3).getCardImg()));
		card5Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(4).getCardImg()));
		card6Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(5).getCardImg()));
		card7Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(6).getCardImg()));
		card8Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(7).getCardImg()));
		card9Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(8).getCardImg()));
		card10Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(9).getCardImg()));
		card11Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(10).getCardImg()));
		card12Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(11).getCardImg()));
		
		int pointsInHand = 0;
		for (int i = 0; i < gameSeep.hand[0].getCardCount(); i++) {
			pointsInHand += gameSeep.hand[0].getCard(i).getCardPoints();
		}
		
		pointsInHandJLabel = new JLabel("Points in Hand: " + pointsInHand);
		
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
		this.add(pointsInHandJLabel);
		this.revalidate();
		this.repaint();
	}
	
	}
	
	public void dealCards() {

		card1Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(0).getCardImg()));
		card2Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(1).getCardImg()));
		card3Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(2).getCardImg()));
		card4Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(3).getCardImg()));
		card5Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(4).getCardImg()));
		card6Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(5).getCardImg()));
		card7Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(6).getCardImg()));
		card8Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(7).getCardImg()));
		card9Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(8).getCardImg()));
		card10Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(9).getCardImg()));
		card11Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(10).getCardImg()));
		card12Label = new JLabel(new ImageIcon(gameSeep.hand[0].getCard(11).getCardImg()));
		
		int pointsInHand = 0;
		for (int i = 0; i < gameSeep.hand[0].getCardCount(); i++) {
			pointsInHand += gameSeep.hand[0].getCard(i).getCardPoints();
		}
		
		pointsInHandJLabel = new JLabel("Points in Hand: " + pointsInHand);
		
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
		this.add(pointsInHandJLabel);
		this.revalidate();
		this.repaint();
	
	}
	
	public static UserPanel getInstance() {
		if (instance == null) {
			synchronized (UserPanel.class) {
				if (instance == null) {
					instance = new UserPanel();
				}
			}
		}
		return instance;
	}
	
	public void clearHand() {
		removeAll();
		revalidate();
		repaint();
	}
	
	
	/**
	 * actionPerformend Event handling 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {}
	
	
	private void wrong() {
		try  //this try catch block deals with getting looping sound playing
		{ 
			wrongFile = "sounds/Incorrect.wav"; 
			wrongGuess = new AudioClips(wrongFile,true); 
			wrongGuess.play();
		
		} catch (Exception ex) { 
			System.out.println("Error with playing sound."); 
			ex.printStackTrace(); 
		}  //end of try catch
		
	} //end of method Wrong
	
	private void right() {
		try  //this try catch block deals with getting looping sound playing
		{ 
			rightFile = "sounds/Correct.wav"; 
			rightGuess = new AudioClips(rightFile,true); 
			rightGuess.play();
		
		} catch (Exception ex) { 
			System.out.println("Error with playing sound."); 
			ex.printStackTrace(); 
		}  //end of try catch
		
	} //end of method  right
	
}

