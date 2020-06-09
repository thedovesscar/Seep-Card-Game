import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameplayPanel2 extends JPanel {

	Player1Panel p1p;
	MiddlePanel mp;
	Player3Panel p3p;
	
	/**
	 * default serial ID
	 */
	private static final long serialVersionUID = 1L;
	

	Dimension dim = new Dimension(1200,500);
	
	
	GameplayPanel2() {
		
		setPreferredSize(dim);
		p1p = new Player1Panel();
		mp = new MiddlePanel();
		p3p = new Player3Panel();
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(p3p);
		add(mp);
		add(p1p);
		
		
	} //end CONSTRUCTOR
	
	/**
	 * This method should be called whenever player answers a 
	 * question, regardless whether or not they answer correctly.
	 * 
	 * This will call RandomNumber generator method in order to decide
	 * what the next question should be and then call's setNewImage(int) method,
	 * which will call the corresponding image.
	 * 
	 */
	public static void setupNewQuestion() {
		RandomNumber.generateNumberOfObjects();
		int i = RandomNumber.getNumberOfObjects();
		setNewImage(i);
	} //end setupNewQuestion
	
	
	/**
	 * This method will be called using setupNewQuestion(),
	 * 
	 * @param number
	 */
	private static void setNewImage(int number) {
//		
//		switch (number) {
//		case 1:
//			applesDisplay.setIcon(apple1);
//			break;
//		case 2:
//			applesDisplay.setIcon(apple2);
//			break;
//		case 3:
//			applesDisplay.setIcon(apple3);
//			break;
//		case 4:
//			applesDisplay.setIcon(apple4);
//			break;
//		case 5:
//			applesDisplay.setIcon(apple5);
//			break;
//		case 6:
//			applesDisplay.setIcon(apple6);
//			break;
//		case 7:
//			applesDisplay.setIcon(apple7);
//			break;
//		case 8:
//			applesDisplay.setIcon(apple8);
//			break;
//		case 9:
//			applesDisplay.setIcon(apple9);
//			break;
//		default:
//			break;	
//		} 
//		
//		
	} //end setNewImage

} //end of GameplayPanel class
