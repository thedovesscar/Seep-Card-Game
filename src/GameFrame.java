import javax.swing.JFrame;

/**
 * This class is only concerned with creating the GameFrame
 * using a single call to the GamePanel.
 * @author Ervin & Gurmehar
 *
 */
public class GameFrame extends JFrame{
	
	/**
	 * default serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	GamePanel gamePanel; 
	AudioClips audio;
	
	GameFrame(){
		
		super("Let's Play Some Bhabi!"); 
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1005, 805);						 //gives a little padding
		
		gamePanel = new GamePanel();
		add(gamePanel);
		
		setResizable(false);
		
		try  //this try catch block deals with getting looping sound playing
		{ 
			String filePath = "sounds//Celesta.wav"; 
			audio = new AudioClips(filePath); 
			audio.play();
		
		} catch (Exception ex) { 
			System.out.println("Error with playing sound."); 
			ex.printStackTrace(); 
		}  //end of try catch
		
		setVisible(true);
	}
}
