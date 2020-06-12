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
		
		setBackground(new Color(230,77,77));
		mpp.setBackground(new Color(238,84,84));
		gpp.setBackground(new Color(250,60,60));
		upp.setBackground(new Color(238,84,84));
		
		add(mpp);
		add(gpp);
		add(upp);
		
	} //end GAMEPANEL constructor
	
} //end class
