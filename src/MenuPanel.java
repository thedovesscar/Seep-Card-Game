
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

//import java.net.*;
//import java.io.*;
//MenuPanel.java    part of  CS170 Final Project

//
//By  Bradly Wei
//
//May 13, 2019


/******************
* MenuPanel.java provides a panel on top of the screen with the
* artistically designed buttons,
*
*  "START",
*  "STOP",
*  "QUIT",  
*  "HISCORE" buttons,
*
* to start, stop and quit the game, as well as game scores textfields,
*
*  "Right:"
*  "Wrong:"
*
* for the number of correct answers, and wrong answers of the
* current play.
*
* Design:
*
*  MenuPanel is implemented as a class, and has two component JPanels,
*  buttonPanel, and scorePanel, as shown below.
*
*  +---------------------------------------------------------------+
*  | MenuPanel           (BoxLayout)                               |
*  | +---------------------------+  +----------------------------+ |
*  | | buttonPanel (BoxLayout)   |  |  scorePanel (FlowLayout)   | |
*  | | +------+ -------+ +------+|  | +--------+ +----------+    | |
*  | | |      | |      | |      ||  | |Label + | |Label +   |    | |
*  | | |Button| |Button| |Button||  | |TextArea| | TextArea |    | |  //added hiScoreButton
*  | | |START | | STOP | |QUIT  ||  | | Right: | | Wrong:   |    | | 
*  | | +------+ +------+ +------+|  | +--------+ +----------+    | |
*  | +---------------------------+  +----------------------------+ |
*  +---------------------------------------------------------------+
*  ...
*  Rest of game JPanel's
*  
*  other variables - Boolean gameIsOn
*  
*  This class also houses the boolean gameIsOn which allows methods to know whether
*  there is an active game.
*  
*  other Objects - TextFileIO scoresFile
*  				 - ScoresTreeMap scores
*  
*  scoresFile -contains methods for the Game to read and write the highScores
*  			  -only called methods at the beginning and end of application
*  
*  scores -contains the TreeMap that will hold active top 5 scores
*  		  -has methods dealing with comparing user score with top scores
*  
*
* 
*    images/start.gif,
*            stop.gif,
*            quit.gif,
*            strawberry.gif:  Images for the Start/Stop/Quit buttons.
*                            Create an "images" directory in the same
*                            directory where the .java files reside and
*                            put these image files under.
*
*/
class MenuPanel extends JPanel implements ActionListener {
	
	/**
	 * default serial
	 */
	private static final long serialVersionUID = 1L;
	
	//EDITED : made them private and static
	private static int  rightCount = 0,   
         			  	wrongCount = 0;   // Game Scores
	
	private static String player;
	
	private static boolean gameIsOn;
 
	private static JTextField rightCountTextField, wrongCountTextField;
	private JLabel   rightClickLabel, wrongClickLabel;
	private JButton  startButton, quitButton, stopGameButton, hiScoreButton;
	private JPanel   buttonPanel, scorePanel;
	

	public MenuPanel () {  // Constructor
		
		//scoresList.sortScores();
		//scoresList.showScoresList();
		//scoresList.writeScores();
		
		setupButtonPanel();
		setupScorePanel();
		setGameOn(false); //game hasn't started yet
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setPreferredSize(new Dimension(1000,100)); //EDIT added to fit Frame
		this.add(buttonPanel);
		this.add(scorePanel);
	}

	// buttonPanel has BoxLayout horizontally.
	private void setupButtonPanel () {
		buttonPanel = new JPanel();

		buttonPanel.setBackground(new Color(60,60,200));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		// Add some blank space on the left edge so the START button
		// does not rest right on the left edge of the Panel.
		buttonPanel.add(Box.createRigidArea(new Dimension(60,5)));
	 

		startButton = new JButton(new ImageIcon("images/start.gif"));
		//EDIT from java.awt.Dimension to Dimension
		startButton.setPreferredSize(new Dimension(100,50));
		startButton.addActionListener(this);    // Add event handler
	 	buttonPanel.add(startButton);
	 
	 	stopGameButton = new JButton(new ImageIcon("images/stop.gif"));
	 	//EDIT from java.awt.Dimension to Dimension
		stopGameButton.setPreferredSize(new Dimension(100,50)); 
		stopGameButton.addActionListener(this);  // Add event handler
		buttonPanel.add(stopGameButton);

		quitButton = new JButton(new ImageIcon("images/quit.gif"));
		//EDIT from java.awt.Dimension to Dimension
		quitButton.setPreferredSize(new Dimension(100,50));
		quitButton.addActionListener(this);      // Add event handler
		buttonPanel.add(quitButton);
		 
		//EDIT added hiScoreButton                                      !!!!!!  requires IMAGE !!!!!!
		hiScoreButton = new JButton(new ImageIcon("images/topScore.png"));
		hiScoreButton.setPreferredSize(new Dimension(100,50));
		hiScoreButton.addActionListener(this);    // Add event handler
		buttonPanel.add(hiScoreButton);
	}

    // use FlowLayout
	private void setupScorePanel() {
		scorePanel = new JPanel();
		
		//EDIT
		scorePanel.setBackground(new Color(60,60,200));
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
		
		// EDIT: moving over score panel to center it more
		scorePanel.add(Box.createRigidArea(new Dimension(150,5)));
		buttonPanel.add(scorePanel);
	
		rightClickLabel = new JLabel("Right: ");
		scorePanel.add(rightClickLabel);
	
		rightCountTextField = new JTextField(3);
		rightCountTextField.setMaximumSize(new Dimension(50,40)); //EDIT
		rightCountTextField.setEditable(false);
		rightCountTextField.setFocusable(false);
		scorePanel.add(rightCountTextField);
	
		wrongClickLabel = new JLabel(" Wrong: ");
		scorePanel.add(wrongClickLabel);
	
	
		wrongCountTextField = new JTextField(3);
		wrongCountTextField.setMaximumSize(new Dimension(50,40)); //EDIT
		wrongCountTextField.setEditable(false);
		wrongCountTextField.setFocusable(false);
		scorePanel.add(wrongCountTextField);
	
	 }

	/**
	 *  will add when player gets right answer!
	 */
	public static void incrementRightCount () {
		rightCount++;
		rightCountTextField.setText(Integer.toString(rightCount));
	}
	/**
	 * @param count
	 */
	public static void setRightCount (int count) {
		rightCount = count;
		rightCountTextField.setText(Integer.toString(rightCount));
	}
 
	/**
	 * @return RightCount
	 */
	public static int getRightCount() {
		return rightCount;
	}

	/**
	 * will add 1 to wrongCount when user gets question wrong 
	 */
	public static void incrementWrongCount () {
		wrongCount++;
		wrongCountTextField.setText(Integer.toString(wrongCount));
	}
 
	/**
	 * settingWrongCount - initializing it to 0
	 * @param count
	 */
	public static void setWrongCount (int count) {
		wrongCount = count;
		wrongCountTextField.setText(Integer.toString(wrongCount));
	}
 
	/**
	 * @return wrongCount
	 */
	public static int getWrongCount() {
		return wrongCount;
	}
	
	/**
	 *  will let calling function know if there is an active game.
	 * @return boolean
	 */
	public static boolean isGameOn() {
		return gameIsOn;
	}
	
	/**
	 * will allow calling function to turn on game
	 * @param gameIsOn
	 */
	public void setGameOn(boolean settingGame) {
		gameIsOn = settingGame;
	}
		
	public void setPlayer() {
		player = JOptionPane.showInputDialog("Please Enter Your Name: ");
		
	}
	
 
	public void actionPerformed (ActionEvent e) {
		Object source = e.getSource();
		
		if (source == quitButton) {
			if (gameIsOn) {
//				scoresList.addScore(new PlayerScores(rightCount, player));
//				scoresList.sortScores();
//				scoresList.writeScores();
			}
			System.exit(0);
		} //end quit Button action.
		
		if (source == startButton) {
			
		    if (!isGameOn()) {
		    	
		    	setPlayer();
				rightCount = 0;
				wrongCount = 0;
				//EDITED : "rightCountTextField.setText(Integer.toString(rightCount));" 
				setRightCount(rightCount);
				//EDITED : "wrongCountTextField.setText(Integer.toString(wrongCount));" 
				setWrongCount(wrongCount);
				/***********
				 * TODO: Start the game here, start the game thread which will
				 *       throw the strawberries onto the screen now...
				 * TODO: if available, start the game music too.
				 *
				 * TODO: The following statement is here to test the MenuPanel.
				 *       Please remove after integration.
				 */
				GameplayPanel.setupNewQuestion();
				setGameOn(true);
			} //end if for when game is On or Not.
		    
			return;  //???? why is this here?
			
		} //end startButton action
		
		if (source == stopGameButton) {
		    
			if (isGameOn()) {
				
				//recording scores
//				scoresList.addScore(new PlayerScores(rightCount, player));
//				scoresList.sortScores();
				JOptionPane.showMessageDialog(null, "Thanks "  + player//can replace
						+ " for playing!\nYou got " + rightCount + " correct and " 
						+ wrongCount+ " wrong!");
				rightCount = 0;
				wrongCount = 0;
				setRightCount(rightCount);
				setWrongCount(wrongCount);
				//rightCountTextField.setText(Integer.toString(rightCount)); EDITED 
				//wrongCountTextField.setText(Integer.toString(wrongCount)); EDITED
				setGameOn(false);
				
			} // will only trigger if game is actually ON
			
		} //end stopGameButton action
		
		if (source == hiScoreButton) {
			
			//button will only activate when the game is off
			if (!isGameOn()) {	
//				scoresList.showScoresList();
			}
			
		} //end hiScoreButton action
		
	} //end of Action Listener for MenuPanel Buttons

			  
} //end of MenuPanel Class