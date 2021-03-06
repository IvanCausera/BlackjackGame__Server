package tools;

import java.util.Random;

/**
 * This class contains static methods that will be used by other classes
 * 
 * @author Frank Vanegas, Jukka Rivas, Ivan Causera
 */
public class Tools {

	/**
	 * This method will return a random number in a range of numbers between a
	 * minimum and a maximum number
	 * 
	 * @param min The minimum number specified by the user
	 * @param max The maximum number specified by the user
	 * @return a number in a range of numbers
	 */
	public static int randomNumber(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
	
	
	/**
	 * This method returns randomly a boolean
	 * @return a boolean
	 */
	public static boolean randomBoolean() {
		return new Random().nextBoolean();
	}
}
