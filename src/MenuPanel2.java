import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuPanel2 extends JPanel implements ActionListener{
	
	/**
	 * default serial
	 */
	private static final long serialVersionUID = 1L;
	
	private static boolean gameIsOn;
 
	Seep gameSeep;
	GameplayPanel2 gpp;
	UserPanel uPanel;
	private static int yourScore, oppScore;
	private static JTextField yourScoreTextField, oppScoreTextField;
	private JLabel   yourScoreLabel, oppScoreLabel;
	private JButton  startButton, quitButton, stopGameButton, hiScoreButton;
	private JPanel   buttonPanel, scorePanel;
	

	public MenuPanel2 () {  // Constructor
		
		//scoresList.sortScores();
		//scoresList.showScoresList();
		//scoresList.writeScores();
		
		setupButtonPanel();
		setupScorePanel();
		setGameOn(false); //game hasn't started yet
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setPreferredSize(new Dimension(1200,100)); //EDIT added to fit Frame
		this.add(buttonPanel);
		this.add(scorePanel);
		gameSeep = Seep.getInstance();
		gpp = GameplayPanel2.getInstance();
		uPanel = UserPanel.getInstance();
	}

	// buttonPanel has BoxLayout horizontally.
	private void setupButtonPanel () {
		buttonPanel = new JPanel();

		buttonPanel.setBackground(new Color(238,84,84));
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
		scorePanel.setBackground(new Color(238,84,84));
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
		
		// EDIT: moving over score panel to center it more
		scorePanel.add(Box.createRigidArea(new Dimension(150,5)));
		buttonPanel.add(scorePanel);
	
		yourScoreLabel = new JLabel("Your Score: ");
		scorePanel.add(yourScoreLabel);

		yourScoreTextField = new JTextField(3);
		yourScoreTextField.setMaximumSize(new Dimension(50,40)); //EDIT
		yourScoreTextField.setEditable(false);
		yourScoreTextField.setFocusable(false);
		scorePanel.add(yourScoreTextField);
		
		oppScoreLabel = new JLabel("Opponent's Score: ");
		scorePanel.add(oppScoreLabel);
	
		oppScoreTextField = new JTextField(3);
		oppScoreTextField.setMaximumSize(new Dimension(50,40)); //EDIT
		oppScoreTextField.setEditable(false);
		oppScoreTextField.setFocusable(false);
		scorePanel.add(oppScoreTextField);
	
	 }

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
//		player = JOptionPane.showInputDialog("Please Enter Your Name: ");
		
	}
	
	public static void addYourScore(int score) {
		yourScore = score;
		yourScoreTextField.setText(Integer.toString(yourScore));
	}
	
	public static void addOppScore(int score) {
		oppScore = score;
		oppScoreTextField.setText(Integer.toString(oppScore));
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
		    	
		    	gameSeep.dealCards();
				setGameOn(true);
			} //end if for when game is On or Not.
		    
			return;  //???? why is this here?
			
		} //end startButton action
		
		if (source == stopGameButton) {
		    
			if (isGameOn()) {
				//TODO
				//TODO
				//TODO
//				Seep.endGame();
				//TODO 
				//TODO
				
				gpp.clearHands();
				uPanel.clearHand();
				setGameOn(false);
				
			} // will only trigger if game is actually ON
			
		} //end stopGameButton action
		
		if (source == hiScoreButton) {

			
			if (isGameOn()) {	
				gameSeep.checkTurn();
			}
			
		} //end hiScoreButton action
		
	} //end of Action Listener for MenuPanel Buttons

			  
} //end of MenuPanel Class
