import java.util.Random;

/**
 * This class will house the variable and methods
 * dealing with the amount of objects that will
 * appear on screen 
 * 
 * @author Gurmehar
 *
 */
public class RandomNumber {
	
	//creating Random class object 
	private static Random rand = new Random();
	
	//creating private int that will hold the 
	//randomized amount of objects
	private static int numberOfObjects = 0;
	private static int typeOfObjects = 0;
	
	
	/**
	 * This will generate the value for 
	 * the amount of objects in a turn
	 */
	public static void generateNumberOfObjects() {
		
		int last = numberOfObjects; //storing last randomNum
		/*
		 * this loop will make sure the new 
		 * number does not equal the old number
		 */
		while(last == numberOfObjects) { 												//CHANGED
			numberOfObjects = rand.nextInt(9)+1;
		} //end while
	
	} //end of generating random number method
	
	/**
	 * this will generate 
	 * the value for the type of object
	 * that will be displayed for the following turn
	 */
	public static void generateTypeOfObject() {
		typeOfObjects = rand.nextInt(9)+1;
	}
	
	/**
	 * This will return the amount of objects that 
	 * have been generate in the current turn
	 * @return int numberOfObjects
	 */
	public static int getNumberOfObjects() {
		return numberOfObjects;
	}
	
	/**
	 * This will return the type of objects that 
	 * have been generate in the current turn
	 * @return int typeOfObjects
	 */
	public static int getTypeOfObjects() {
		return typeOfObjects;
	}

	
} //end of RandomNumber class
