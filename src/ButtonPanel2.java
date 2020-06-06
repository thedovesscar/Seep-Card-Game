import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel2 extends JPanel implements ActionListener {

	/**
	 * default serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	static String wrongFile;
	static String rightFile;
	
	AudioInputStream audioInputStream;
	
	private JButton 
				oneButton,
				twoButton,
				threeButton,
				fourButton,
				fiveButton,
				sixButton,
				sevenButton,
				eightButton,
				nineButton;
	Toolkit tk;
	
	Dimension butDim = new Dimension(100,100);
	
	ImageIcon num1 = new ImageIcon("images//num1.gif");
	ImageIcon num2 = new ImageIcon("images//num2.gif");
	ImageIcon num3 = new ImageIcon("images//num3.gif");
	ImageIcon num4 = new ImageIcon("images//num4.gif");
	ImageIcon num5 = new ImageIcon("images//num5.gif");
	ImageIcon num6 = new ImageIcon("images//num6.gif");
	ImageIcon num7 = new ImageIcon("images//num7.gif");
	ImageIcon num8 = new ImageIcon("images//num8.gif");
	ImageIcon num9 = new ImageIcon("images//num9.gif");
	
	AudioClips wrongGuess;
	AudioClips rightGuess;  //these are the soundclips for answers
	
	/**
	 * Constructor
	 */
	ButtonPanel2() {
		 
		setPreferredSize(new Dimension(1000,200)); //EDIT added to fit Frame
		
		JPanel NumButtonPanel = new JPanel();
		NumButtonPanel.setBackground(Color.white);
		
		/**
		 * EDITED== 
		 * 
		 * 		-changed to "new java.awt.Dimension(50, 50)" to custom Dimension obj "butDim"
		 * 
		 *      -changed "new ImageIcon("images/gif#.gif"" to custom ImgIcon obj
		 * 
		 */
		
		oneButton = new JButton(num1);
		NumButtonPanel.add(oneButton);
		oneButton.addActionListener(this);
		oneButton.setPreferredSize(butDim);

		twoButton = new JButton(num2);
		NumButtonPanel.add(twoButton);
		twoButton.addActionListener(this);
		twoButton.setPreferredSize(butDim);
		
		threeButton = new JButton(num3);
		NumButtonPanel.add(threeButton);
		threeButton.addActionListener(this);
		threeButton.setPreferredSize(butDim);
		
		fourButton = new JButton(num4);
		NumButtonPanel.add(fourButton);
		fourButton.addActionListener(this);
		fourButton.setPreferredSize(butDim);
		
		fiveButton = new JButton(num5);
		NumButtonPanel.add(fiveButton);
		fiveButton.addActionListener(this);
		fiveButton.setPreferredSize(butDim);
		
		sixButton = new JButton(num6);
		NumButtonPanel.add(sixButton);
		sixButton.addActionListener(this);
		sixButton.setPreferredSize(butDim);
		
		sevenButton = new JButton(num7);
		NumButtonPanel.add(sevenButton);
		sevenButton.addActionListener(this);
		sevenButton.setPreferredSize(butDim);
		
		eightButton = new JButton(num8);
		NumButtonPanel.add(eightButton);
		eightButton.addActionListener(this);
		eightButton.setPreferredSize(butDim);
		
		nineButton = new JButton(num9);
		NumButtonPanel.add(nineButton);
		nineButton.addActionListener(this);
		nineButton.setPreferredSize(butDim);
		
		add(NumButtonPanel);
		
	} //END of ButtonPanel Constructor
	
	/**
	 * actionPerformend Event handling 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (MenuPanel.isGameOn()) { //this makes sure buttons only work when Game is active
			if (source == oneButton) {

				if (RandomNumber.getNumberOfObjects() == 1) {
					MenuPanel.incrementRightCount();
					right();
				} else {
					MenuPanel.incrementWrongCount();
					wrong();
				}
				GameplayPanel.setupNewQuestion();
			} else if (source == twoButton) {

				if (RandomNumber.getNumberOfObjects() == 2) {
					MenuPanel.incrementRightCount();
					right();
				} else {
					MenuPanel.incrementWrongCount();
					wrong();
				}

				GameplayPanel.setupNewQuestion();

			} else if (source == threeButton) {

				if (RandomNumber.getNumberOfObjects() == 3) {
					MenuPanel.incrementRightCount();
					right();
				} else {
					MenuPanel.incrementWrongCount();
					wrong();
				}
				GameplayPanel.setupNewQuestion();
			} else if (source == fourButton) {

				if (RandomNumber.getNumberOfObjects() == 4) {
					MenuPanel.incrementRightCount();
					right();
				} else {
					MenuPanel.incrementWrongCount();
					wrong();
				}
				GameplayPanel.setupNewQuestion();

			} else if (source == fiveButton) {

				if (RandomNumber.getNumberOfObjects() == 5) {
					MenuPanel.incrementRightCount();
					right();
				} else {
					MenuPanel.incrementWrongCount();
					wrong();
				}
				GameplayPanel.setupNewQuestion();

			} else if (source == sixButton) {

				if (RandomNumber.getNumberOfObjects() == 6) {
					MenuPanel.incrementRightCount();
					right();
				} else {
					MenuPanel.incrementWrongCount();
					wrong();
				}
				GameplayPanel.setupNewQuestion();

			} else if (source == sevenButton) {

				if (RandomNumber.getNumberOfObjects() == 7) {
					MenuPanel.incrementRightCount();
					right();
				} else {
					MenuPanel.incrementWrongCount();
					wrong();
				}
				GameplayPanel.setupNewQuestion();
				

			} else if (source == eightButton) {

				if (RandomNumber.getNumberOfObjects() == 8) {
					MenuPanel.incrementRightCount();
					right();
				} else {
					MenuPanel.incrementWrongCount();
					wrong();
				}
				GameplayPanel.setupNewQuestion();

			} else if (source == nineButton) {

				if (RandomNumber.getNumberOfObjects() == 9) {
					MenuPanel.incrementRightCount(); right();
				} else {
					MenuPanel.incrementWrongCount();
					wrong();
				}
				GameplayPanel.setupNewQuestion();

			} //end of button9
			
		} // end of if ( isGameOn() ){}
		

    } //end of ActionListener
	
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

