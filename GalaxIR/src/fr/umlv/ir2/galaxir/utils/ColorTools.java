package fr.umlv.ir2.galaxir.utils;

import java.awt.Color;
import java.util.Random;

/**
 * The class ColorTools is used for manipulating colors.
 * It implements static methods only.
 * @author Clément Lebreton, Jordane Marcel
 *
 */
public class ColorTools {
	
	/**
	 * Generate a color that is not dark
	 * (Average color above 20 out of 255)
	 * @return a random {@link Color}
	 */
	public static Color generateNotBlackRandomColor() {
		Random rand = new Random();
		Color color;
		do {
			int randomRGB = rand.nextInt(255*255*255);
			String randomColor = ""+randomRGB;
			color = Color.decode(randomColor);
		} while(getAverageRGB(color)<20);
		return color;
	}
	
	/**
	 * Get the average brightness of a {@link Color}
	 * @param color - the given {@link Color}
	 * @return the average brightness
	 */
	public static int getAverageRGB(Color color) {
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		int average = (red+green+blue)/3;
		return average;
	}
	
}
