import java.awt.Color;

import javax.swing.JPanel;

/**
 * @author Gurmehar
 */
public class GamePanel extends JPanel {
	
	/**
	 * default serial
	 */
	private static final long serialVersionUID = 1L;
	
//	MenuPanel mpp;
//	GameplayPanel gpp;
//	ButtonPanel bpp;
	
	MenuPanel2 mpp;
	GameplayPanel2 gpp;
	UserPanel upp;
	
	
	GamePanel(){
		
//		mpp = new MenuPanel();
//		gpp = new GameplayPanel();
//		bpp = new ButtonPanel();

		mpp = new MenuPanel2();
		gpp = GameplayPanel2.getInstance();
		upp = UserPanel.getInstance();
		
		setBackground(new Color(50,50,200));
		mpp.setBackground(new Color(60,60,200));
		gpp.setBackground(new Color(40,40,200));
		upp.setBackground(new Color(50,50,170));
		
		add(mpp);
		add(gpp);
		add(upp);
		
	} //end GAMEPANEL constructor
	
} //end class
