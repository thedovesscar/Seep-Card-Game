import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;



/**
 * 
 * NEEDED : Welcome Image! Layout for the Button
 * 
 * 
 * 
 * @author Ervin & Gurmehar
 *
 */
public class GameplayPanel extends JPanel {
	
	/**
	 * default serial ID
	 */
	private static final long serialVersionUID = 1L;
	

	Dimension dim = new Dimension(1000,400);
	
	static JButton applesDisplay = new JButton();
	private static Icon apple1 = new ImageIcon("images//1A.jpg");
	private static Icon apple2 = new ImageIcon("images//2A.jpg");
	private static Icon apple3 = new ImageIcon("images//3A.jpg");
	private static Icon apple4 = new ImageIcon("images//4A.jpg");
	private static Icon apple5 = new ImageIcon("images//5A.jpg");
	private static Icon apple6 = new ImageIcon("images//6A.jpg");
	private static Icon apple7 = new ImageIcon("images//7A.jpg");
	private static Icon apple8 = new ImageIcon("images//8A.jpg");
	private static Icon apple9 = new ImageIcon("images//9A.jpg");
	
	GameplayPanel() {
		
		setPreferredSize(dim);
		setLayout(new BorderLayout());
		
		applesDisplay.setLayout(new FlowLayout(FlowLayout.CENTER));
		applesDisplay.setBackground(new Color(100,255,10));
		applesDisplay.setIcon(new ImageIcon(""));
		add(applesDisplay);
		
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
		
		switch (number) {
		case 1:
			applesDisplay.setIcon(apple1);
			break;
		case 2:
			applesDisplay.setIcon(apple2);
			break;
		case 3:
			applesDisplay.setIcon(apple3);
			break;
		case 4:
			applesDisplay.setIcon(apple4);
			break;
		case 5:
			applesDisplay.setIcon(apple5);
			break;
		case 6:
			applesDisplay.setIcon(apple6);
			break;
		case 7:
			applesDisplay.setIcon(apple7);
			break;
		case 8:
			applesDisplay.setIcon(apple8);
			break;
		case 9:
			applesDisplay.setIcon(apple9);
			break;
		default:
			break;	
		} 
		
		
	} //end setNewImage

} //end of GameplayPanel class
